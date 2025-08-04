package jrd.projects.ems202506.api.gallery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepo extends JpaRepository<Gallery, Long>{

	@Override
	@EntityGraph(attributePaths = {"attachmentList"})
	Page<Gallery> findAll(Pageable pageable);

}
