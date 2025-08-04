package jrd.projects.ems202506.api.gallery_attachment.dto;

import jrd.projects.ems202506.api.user.dto.UserMinDto;
import lombok.Data;

@Data
public class GalleryAttachmentDto {

	private Long id;

	//	@JsonIgnore
	//	private GalleryDto gallery;

	private String originalName;

	private String storedName;

	private String path;

	private String fullPath;

	private String extension;

	private String contentType;

	private Long size;

	private UserMinDto createdBy;
}
