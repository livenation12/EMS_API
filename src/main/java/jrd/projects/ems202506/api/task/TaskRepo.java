package jrd.projects.ems202506.api.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jrd.projects.ems202506.api.employee.Employee;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	List<Task> findAllByAssignedTo(Employee employee);

	@Query("SELECT MAX(position) FROM Task t WHERE t.status.id = :statusId AND t.assignedTo.id = :assignedToId")
	Float findMaxPosition(@Param("statusId") Long statusId, @Param("assignedToId") Long assignedToId);

}
