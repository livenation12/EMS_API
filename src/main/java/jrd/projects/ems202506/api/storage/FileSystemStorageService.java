package jrd.projects.ems202506.api.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jrd.projects.ems202506.api.common.FileMetadata;
import jrd.projects.ems202506.api.exception.StorageException;
@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	public FileSystemStorageService(StorageProperties properties) {
		if (properties.getLocation().trim().length() == 0) {
			throw new StorageException("File upload location cannot be Empty.");
		}
		this.rootLocation = Paths.get(properties.getLocation());
	}

	/**
	 * Delete all files from a specific folder (domain and subfolder).
	 */
	@Override
	public void deleteAllFromFolder(String folderName) {
		try {
			Path folder = this.rootLocation.resolve(folderName);
			Files.walk(folder).sorted((path1, path2) -> path2.compareTo(path1)) // Delete files before directories
			.map(Path::toFile).forEach(file -> {
				if (!file.delete()) {
					System.err.println("Failed to delete: " + file.getAbsolutePath());
				}
			});
		} catch (IOException e) {
			throw new StorageException("Failed to delete files in folder", e);
		}
	}

	/**
	 * Initialize the root storage location by creating the necessary directories.
	 */
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	/**
	 * Load a file by filename from a specific folder (domain/subfolder).
	 */
	@Override
	public Path load(String filename, String folderPath) {
		Path folder = this.rootLocation.resolve(folderPath);
		return folder.resolve(filename).normalize().toAbsolutePath();
	}

	/**
	 * Load all files from a specific domain folder.
	 */
	@Override
	public Stream<Path> loadAll(String folderPath) {
		try {
			Path folder = this.rootLocation.resolve(folderPath);
			return Files.walk(folder, 1).filter(path -> !path.equals(folder)).map(folder::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
	}

	/**
	 * Load a file as a resource (e.g., for serving via HTTP).
	 */
	@Override
	public Resource loadAsResource(String filename, String folderPath) {
		try {
			Path file = load(filename, folderPath);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageException("Could not read file: " + filename);
			}
		} catch (IOException e) {
			throw new StorageException("Could not read file: " + filename, e);
		}
	}

	/**
	 * Store a file in a specific folderPath.
	 */
	@Override
	public FileMetadata store(MultipartFile file, String path) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			// Create paths if they don't exist
			//resolve method join the folder path to the rootlocation
			// normalize then cleans the path .normalize() // removes "..", ".", etc.
			//folderPath = /uploads/users/
			//newFilename = ../../hack.sh
			Path folderPath = this.rootLocation.resolve(path).normalize();
			Files.createDirectories(folderPath);

			// Resolve the destination file path
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String newFilename = UUID.randomUUID().toString() + "." + extension;

			///home/myapp/uploads/hack.sh
			Path destinationFile = folderPath.resolve(newFilename).normalize().toAbsolutePath();

			// Security check to prevent files from being stored outside the designated
			// directory
			if (!destinationFile.startsWith(folderPath.toAbsolutePath())) {
				throw new StorageException("Cannot store file outside the folder.");
			}

			// Copy the file to the destination
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}

			Path relativePath = this.rootLocation.relativize(destinationFile);
			String storedRelativePath = relativePath.toString().replace("\\", "/");
			String storedFolderPath = this.rootLocation.relativize(folderPath).toString().replace("\\", "/");

			FileMetadata metadata = new FileMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setOriginalFilename(file.getOriginalFilename());
			metadata.setExtension(extension);
			metadata.setStoredName(newFilename);
			metadata.setSize(file.getSize());

			metadata.setStoredPath(storedFolderPath);
			metadata.setStoredFullPath(storedRelativePath);

			return metadata;
		} catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	/**
	 * Store multiple files in a specific domain folder.
	 */
	@Override
	public List<FileMetadata> storeAll(List<MultipartFile> files, String subFolder) {
		if (files.isEmpty()) {
			throw new StorageException("Failed to store empty files.");
		}
		List<FileMetadata> uploadedFiles = new ArrayList<FileMetadata>();
		for (MultipartFile file : files) {
			uploadedFiles.add(store(file, subFolder));
		}
		return uploadedFiles;
	}
}
