package jrd.projects.ems202506.api.gallery;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jrd.projects.ems202506.api.gallery_attachment.GalleryAttachment;
import jrd.projects.ems202506.api.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="galleries")
@Data
@NoArgsConstructor
public class Gallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GalleryAttachment> attachmentList;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@ManyToOne
	private User createdBy;

}
