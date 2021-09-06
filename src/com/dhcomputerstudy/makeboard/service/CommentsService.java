package com.dhcomputerstudy.makeboard.service;

import java.util.ArrayList;

import com.dhcomputerstudy.makeboard.dao.CommentsDAO;
import com.dhcomputerstudy.makeboard.vo.Comments;

public class CommentsService {
	private static CommentsService service = null;
	private static CommentsDAO dao = null;
	
	private CommentsService() {
		
	}
	
	public static CommentsService getInstance() {
		if(service == null) {
			service = new CommentsService();
			dao = CommentsDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<Comments> getComments(int w_group) {
		return dao.getComments(w_group);
	}

	public void writeComments(Comments comments) {
		dao.writeComments(comments);
		
	}

}
