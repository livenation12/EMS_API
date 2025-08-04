package jrd.projects.ems202506.api.task_log;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jrd.projects.ems202506.api.enums.ActionType;
import jrd.projects.ems202506.api.task.Task;
import jrd.projects.ems202506.api.user.User;
import lombok.Data;

@Data
@Entity
@Table(name="task_logs")
public class TaskLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Task task;

	@Enumerated(EnumType.STRING)
	private ActionType type;

	@ManyToOne
	private User createdBy;

	@CreationTimestamp
	private LocalDateTime createdAt;

}
