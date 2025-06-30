package jrd.projects.ems202506.api.task_log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jrd.projects.ems202506.api.auth.User;

@Repository
public interface TaskLogRepo extends JpaRepository<TaskLog, Long> {

	Page<TaskLog> findAllByCreatedBy(User createdBy, Pageable pageable);

}
