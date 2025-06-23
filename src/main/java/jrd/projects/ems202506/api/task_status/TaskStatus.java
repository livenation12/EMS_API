package jrd.projects.ems202506.api.task_status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TaskStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String label; // e.g. "To Do", "In Progress", "Done"
	private Float position; // For ordering columns
	private String colorCode; // Optional for frontend use
	private Boolean isDefault;
}
