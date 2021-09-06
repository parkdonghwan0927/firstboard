package com.dhcomputerstudy.makeboard.vo;

import com.dhcomputerstudy.makeboard.service.WritingService;

public class Pagination {
	int writingCount;
	int currentPage;
	int startPage;
	int endPage;
	int lastPage;
	int prevPage;
	int nextPage;
	public static final int pageUnit = 5;
	public static final int perPage = 5;
	public static final int perPage2 = 10;
	WritingService writingService = null;
	
	public Pagination() {
		
	}
	
	public Pagination(int page) {
		writingService = WritingService.getInstance();
		writingCount = writingService.getWritingCount();
		startPage = ((page - 1) / pageUnit)*pageUnit + 1;
		lastPage = (int)Math.ceil(writingCount / (float)perPage);
		endPage = startPage + pageUnit - 1;
		endPage = endPage < lastPage ? endPage : lastPage;
		prevPage = (endPage - pageUnit);
		nextPage = startPage + pageUnit;
	}
	
	public int getWritingCount() {
		return writingCount;
	}
	
	public void setWritingCount(int writingCount) {
		this.writingCount = writingCount;
	}
	
	public int getcurrentPage() {
		return currentPage;
	}
	
	public void setcurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public int getPerPage() {
		return perPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
}
