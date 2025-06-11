package jrd.projects.ems202506.api.common;

public class SearchPagingRequest {
	private String keyword = "";
	private String searchBy = "";
	private String sortBy = "id";
	private int pageNumber = 0;
	private int size = 10;

	public String getKeyword() {
		return keyword;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public int getSize() {
		return size;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
}
