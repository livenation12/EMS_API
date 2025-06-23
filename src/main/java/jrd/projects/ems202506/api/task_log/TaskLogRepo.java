package jrd.projects.ems202506.api.task_log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskLogRepo extends JpaRepository<TaskLog, Long> {

}
