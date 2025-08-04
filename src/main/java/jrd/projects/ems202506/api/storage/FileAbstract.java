package jrd.projects.ems202506.api.storage;

import java.time.LocalDate;

public abstract class FileAbstract {

	public String generateFolderPath(String domain) {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		return domain + "/" + year + "/" + month;
	}
}
