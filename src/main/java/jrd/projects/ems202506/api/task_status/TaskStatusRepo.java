package jrd.projects.ems202506.api.task_status;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepo extends JpaRepository<TaskStatus, Long> {

	Boolean existsByIsDefault(Boolean isDefault);

	Optional<TaskStatus> findByIsDefault(Boolean isDefault);

	@Query("SELECT MAX(position) FROM TaskStatus")
	Float findMaxPosition();

	@Modifying
	@Query("UPDATE TaskStatus t SET t.isDefault = false")
	Integer setNewDefault();
}
