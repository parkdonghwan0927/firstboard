package com.dhcomputerstudy.makeboard.service;

import java.util.ArrayList;

import com.dhcomputerstudy.makeboard.dao.UserDAO;
import com.dhcomputerstudy.makeboard.vo.User;

public class UserService {
	private static UserService service = null;
	private static UserDAO dao = null;
	
	private UserService() {
			
	}
		
	public static UserService getInstance() {
		if(service == null) {
			service = new UserService();
			dao = UserDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<User> getUsers() {
		return dao.getUsers();
	}
	
	public User loginUser(String id, String pw) {
		return dao.loginUser(id, pw);
	}
	
	public void insertUser(User user) {
		dao.insertUser(user);
	}
}
