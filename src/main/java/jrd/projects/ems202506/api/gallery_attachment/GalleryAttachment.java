package jrd.projects.ems202506.api.gallery_attachment;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jrd.projects.ems202506.api.gallery.Gallery;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="gallery_attachments")
public class GalleryAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gallery_id")
	private Gallery gallery;

	private String originalName;

	private String storedName;

	private String path;

	private String fullPath;

	private String extension;

	private String contentType;

	private Long size;

}
