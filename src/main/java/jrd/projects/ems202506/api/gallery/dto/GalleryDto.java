package jrd.projects.ems202506.api.gallery.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jrd.projects.ems202506.api.gallery_attachment.dto.GalleryAttachmentDto;
import jrd.projects.ems202506.api.user.dto.UserMinDto;
import lombok.Data;

@Data
public class GalleryDto {

	private Long id;

	private String title;

	private String description;

	private List<GalleryAttachmentDto> attachmentList;

	@JsonFormat(pattern="MMM dd yyyy hh:mm a")
	private LocalDateTime createdAt;

	private UserMinDto createdBy;

}
