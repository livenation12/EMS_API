package jrd.projects.ems202506.api.employee_status_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeStatusTypeRepo extends JpaRepository<EmployeeStatusType, Long> {

}
