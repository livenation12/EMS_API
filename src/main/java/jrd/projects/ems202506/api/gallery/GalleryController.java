package jrd.projects.ems202506.api.gallery;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.gallery.dto.GalleryDto;
import jrd.projects.ems202506.api.gallery.dto.GalleryRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/galleries")
public class GalleryController {


	private final GalleryService galleryService;

	@GetMapping
	public ApiResponse<List<GalleryDto>> readGallery(){
		List<GalleryDto> galleryList = galleryService.readGalleryList();
		return ApiResponse.success(galleryList);
	}


	@PostMapping
	public ApiResponse<?> uploadGalleryFiles(@ModelAttribute @Valid GalleryRequest request){
		galleryService.uploadFiles(request);
		return ApiResponse.success("Uploaded successfully");
	}
}
