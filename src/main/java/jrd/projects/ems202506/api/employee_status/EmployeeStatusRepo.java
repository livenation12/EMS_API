package jrd.projects.ems202506.api.employee_status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeStatusRepo extends JpaRepository<EmployeeStatus, Long> {

}
