package com.dhcomputerstudy.makeboard.service;

import java.util.ArrayList;

import com.dhcomputerstudy.makeboard.dao.WritingDAO;
import com.dhcomputerstudy.makeboard.service.WritingService;
import com.dhcomputerstudy.makeboard.vo.Writing;

public class WritingService {
	private static WritingService service = null;
	private static WritingDAO dao = null;
	
	private WritingService() {
		
	}
	
	public static WritingService getInstance() {
		if(service == null) {
			service = new WritingService();
			dao = WritingDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<Writing> getWriting(int page) {
		return dao.getWriting(page);
	}
	
	public void writeWriting(Writing writing) {
		dao.writeWriting(writing);
	}
	
	public Writing readWriting(int w_no) {
		return dao.readWriting(w_no);
	}
	
	public void deleteWriting(int w_no) {
		dao.deleteWriting(w_no);
	}
	
	public void editWriting(Writing writing) {
		dao.editWriting(writing);
	}
	
	public int getWritingCount() {
		return dao.getWritingCount();
	}
}
