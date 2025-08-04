package jrd.projects.ems202506.api.gallery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.common.FileMetadata;
import jrd.projects.ems202506.api.common.Utils;
import jrd.projects.ems202506.api.gallery.dto.GalleryDto;
import jrd.projects.ems202506.api.gallery.dto.GalleryRequest;
import jrd.projects.ems202506.api.gallery_attachment.GalleryAttachment;
import jrd.projects.ems202506.api.storage.FileAbstract;
import jrd.projects.ems202506.api.storage.FileSystemStorageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GalleryService extends FileAbstract {

	private final String domain =  "gallery";
	private final GalleryRepo galleryRepo;
	private final FileSystemStorageService storageService;


	public List<GalleryDto> readGalleryList(){
		PageRequest pageRequest = PageRequest.of(0, 20);
		Page<Gallery> galleryPage = galleryRepo.findAll(pageRequest);
		return GalleryMapper.INSTANCE.toDtoList(galleryPage.getContent());
	}

	public void uploadFiles(GalleryRequest request){
		String folderPath = generateFolderPath(domain);
		Gallery gallery = GalleryMapper.INSTANCE.toEntity(request);
		gallery.setCreatedBy(Utils.getAuthUser());
		List<FileMetadata> uploadedFiles =  storageService.storeAll(request.getAttachments(), folderPath);
		List<GalleryAttachment> attachments = new ArrayList<>();
		for(FileMetadata uploadedFile: uploadedFiles) {
			GalleryAttachment galleryAttach = new GalleryAttachment();
			galleryAttach.setContentType(uploadedFile.getContentType());
			galleryAttach.setStoredName(uploadedFile.getStoredName());
			galleryAttach.setPath(uploadedFile.getStoredPath());
			galleryAttach.setExtension(uploadedFile.getExtension());
			galleryAttach.setOriginalName(uploadedFile.getOriginalFilename());
			galleryAttach.setSize(uploadedFile.getSize());
			galleryAttach.setFullPath(uploadedFile.getStoredFullPath());
			galleryAttach.setGallery(gallery);
			attachments.add(galleryAttach);
		}
		gallery.setAttachmentList(attachments);
		galleryRepo.save(gallery);
	}




}
