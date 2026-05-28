package com.chandru.demo.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponseDTO<T> {

	private List<T> content;

	private int pageNumber;

	private int pageSize;

	private int totalPage;

	private boolean lastPage;

	private long totalCount;

//	public PageResponseDTO(List<T> content, int pageNumber, int pageSize, int totalPage, boolean lastPage,
//			long totalCount) {
//		this.content = content;
//		this.pageNumber = pageNumber;
//		this.pageSize = pageSize;
//		this.totalPage = totalPage;
//		this.lastPage = lastPage;
//		this.totalCount = totalCount;
//	}

//	public List<T> getContent() {
//		return content;
//	}
//
//	public void setContent(List<T> content) {
//		this.content = content;
//	}
//
//	public int getPageNumber() {
//		return pageNumber;
//	}
//
//	public PageResponseDTO() {
//		super();
//	}
//
//	public void setPageNumber(int pageNumber) {
//		this.pageNumber = pageNumber;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public int getTotalPage() {
//		return totalPage;
//	}
//
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//
//	public boolean isLastPage() {
//		return lastPage;
//	}
//
//	public void setLastPage(boolean lastPage) {
//		this.lastPage = lastPage;
//	}
//
//	public long getTotalCount() {
//		return totalCount;
//	}
//
//	public void setTotalCount(long totalCount) {
//		this.totalCount = totalCount;
//	}

}
