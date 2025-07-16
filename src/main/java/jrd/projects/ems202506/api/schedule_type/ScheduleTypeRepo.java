package jrd.projects.ems202506.api.schedule_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTypeRepo extends JpaRepository<ScheduleType, Long>  {
}
