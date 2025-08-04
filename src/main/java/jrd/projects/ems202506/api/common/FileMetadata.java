package jrd.projects.ems202506.api.common;

import lombok.Data;

@Data
public class FileMetadata {

	private String originalFilename;

	//Includes the filename in the path
	private String storedFullPath;

	//Only the folder where it was located
	private String storedPath;
	private Long size;
	private String contentType;
	private String extension;
	private String storedName;
}
