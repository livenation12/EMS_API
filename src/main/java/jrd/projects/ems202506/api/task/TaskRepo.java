package jrd.projects.ems202506.api.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jrd.projects.ems202506.api.employee.Employee;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	List<Task> findAllByAssignedTo(Employee employee);

}
