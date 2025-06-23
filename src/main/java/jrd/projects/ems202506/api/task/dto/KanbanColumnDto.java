package jrd.projects.ems202506.api.task.dto;

import java.util.List;

import lombok.Data;

@Data
public class KanbanColumnDto {

	private Long id;

	private String label;

	private List<TaskDto> tasks;

	private Float position;

	private String colorCode;

	private Boolean isDefault;
}
