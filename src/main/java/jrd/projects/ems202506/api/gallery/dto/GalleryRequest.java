package jrd.projects.ems202506.api.gallery.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GalleryRequest {

	@NotBlank(message="Title is required")
	private String title;

	private String description;

	private MultipartFile attachment;

	private List<MultipartFile> attachments;

}
