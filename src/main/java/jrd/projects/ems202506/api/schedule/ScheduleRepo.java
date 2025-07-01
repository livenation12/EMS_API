package jrd.projects.ems202506.api.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

	List<Schedule> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
