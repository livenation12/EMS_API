package jrd.projects.ems202506.api.schedule;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jrd.projects.ems202506.api.auth.User;
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

	@ManyToOne
	private User createdBy;

	@CreationTimestamp
	private LocalDateTime createdAt;
}
