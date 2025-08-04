package jrd.projects.ems202506.api.schedule;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.schedule_type.ScheduleType;
import jrd.projects.ems202506.api.user.User;
import lombok.Data;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	private ScheduleType type;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="schedule_participants"
	, joinColumns = @JoinColumn(name="schedule_id")
	, inverseJoinColumns = @JoinColumn(name="participant_id"))
	private Set<Employee> participants;

	@ManyToOne
	private User createdBy;

	@CreationTimestamp
	private LocalDateTime createdAt;
}
