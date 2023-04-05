package com.ttu.blogapplication.payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse {
    private List<PostDTO> postDTOS;
    private int pageNumber;
    private long totalItems;
    private int totalPages;
    private boolean isLastPage;
	public List<PostDTO> getPostDTOS() {
		return postDTOS;
	}
	public void setPostDTOS(List<PostDTO> postDTOS) {
		this.postDTOS = postDTOS;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
    
    
}
