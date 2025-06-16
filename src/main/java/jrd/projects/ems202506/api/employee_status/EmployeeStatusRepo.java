package jrd.projects.ems202506.api.employee_status;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeStatusRepo extends JpaRepository<EmployeeStatus, Long> {

	@Query("SELECT es FROM EmployeeStatus es JOIN FETCH es.employee ORDER BY es.timestamp DESC")
	List<EmployeeStatus> findTopWithEmployee(PageRequest pageRequest);
}
