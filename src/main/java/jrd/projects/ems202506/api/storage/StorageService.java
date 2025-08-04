package jrd.projects.ems202506.api.storage;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import jrd.projects.ems202506.api.common.FileMetadata;

public interface StorageService {

	/**
	 * Delete all files from a specific folder (domain and subfolder).
	 */
	void deleteAllFromFolder(String folderName);

	/**
	 * Initialize the root storage location by creating the necessary directories.
	 */
	void init();

	/**
	 * Load a file by filename from a specific folder (domain/subfolder).
	 */
	Path load(String filename, String subFolder);

	/**
	 * Load all files from a specific domain folder.
	 */
	Stream<Path> loadAll(String folder);

	/**
	 * Load a file as a resource (e.g., for serving via HTTP).
	 */
	Resource loadAsResource(String filename, String path);

	/**
	 * Store a file in a specific domain folder (domain/subfolder).
	 */
	FileMetadata store(MultipartFile file, String path);

	/**
	 * Store multiple files in a specific domain folder.
	 */
	List<FileMetadata> storeAll(List<MultipartFile> files, String path);

	/**
	 * Store a file in a specific domain folder (domain/subfolder).
	 */


}
