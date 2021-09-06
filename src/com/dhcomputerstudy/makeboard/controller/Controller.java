package com.dhcomputerstudy.makeboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dhcomputerstudy.makeboard.service.CommentsService;
import com.dhcomputerstudy.makeboard.service.UserService;
import com.dhcomputerstudy.makeboard.service.WritingService;
import com.dhcomputerstudy.makeboard.vo.Comments;
import com.dhcomputerstudy.makeboard.vo.Pagination;
import com.dhcomputerstudy.makeboard.vo.User;
import com.dhcomputerstudy.makeboard.vo.Writing;


@WebServlet("*.do")
public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		
		Writing writing = null;
		WritingService writingService = null;
		int w_no = 0;
		int w_group = 0;
		int w_depth = 0;
		int w_order = 0;
		int currentPage = 1;
		boolean isRedirected = false;
		ArrayList<Writing> list1 = null;
		ArrayList<Comments> list2 = null;
		Pagination pagination = null;
		String reqPage;
		
		HttpSession session = null;
		String id = null;
		String pw = null;
		User user = null;
		UserService userService = null;
		
		Comments comments = null;
		CommentsService commentsService = null;
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
	
		command = checkSession(request, response, command);

		switch (command) {
			case "/writing-main.do":
				
				
				reqPage = request.getParameter("currentPage");
				if(reqPage != null) {
					currentPage = Integer.parseInt(reqPage);
				}
				writingService = WritingService.getInstance();
				list1 = writingService.getWriting(currentPage);	
				pagination = new Pagination(currentPage);

				view = "writing/main";
				request.setAttribute("list", list1);
				request.setAttribute("pagination", pagination);
				
				break;
				
			case "/writing-write.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				view = "writing/write";
				
				break;
				
			case "/writing-backWrite.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				w_group = Integer.parseInt(request.getParameter("w_group"));
				w_depth = Integer.parseInt(request.getParameter("w_depth"));
				w_order = Integer.parseInt(request.getParameter("w_order"));
				
				request.setAttribute("w_group", w_group);
				request.setAttribute("w_depth", w_depth);
				request.setAttribute("w_order", w_order);
				
				view = "writing/write";
				
				break;
			
			case "/writing-successWrite.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				writing = new Writing();
				writing.setW_title(request.getParameter("title"));
				writing.setW_writer(user.getU_idx());
				writing.setW_content(request.getParameter("content"));
								
				if(request.getParameter("group") != "") {
					writing.setW_group(Integer.parseInt(request.getParameter("group")));
					writing.setW_order(Integer.parseInt(request.getParameter("order")));
					writing.setW_depth(Integer.parseInt(request.getParameter("depth")));
				}
				
				writingService = WritingService.getInstance();
				writingService.writeWriting(writing);
				
				view = "writing/successWrite";
				break;
				
			case "/writing-successEdit.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				writing = new Writing();
				writing.setW_no(Integer.parseInt(request.getParameter("no")));
				writing.setW_title(request.getParameter("title"));
				writing.setW_content(request.getParameter("content"));
				
				writingService = WritingService.getInstance();
				writingService.editWriting(writing);
				
				view = "writing/successWrite";
				break;
				
			case "/writing-readWriting.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				w_no = Integer.parseInt(request.getParameter("w_no"));
				
				writingService = WritingService.getInstance();
				writing = writingService.readWriting(w_no);
				request.setAttribute("writing", writing);
	
				commentsService = CommentsService.getInstance();
				list2 = commentsService.getComments(writing.getW_group());	
				
				view = "writing/readWriting";
				request.setAttribute("list", list2);
				request.setAttribute("pagination", pagination);
				
				break;
				
			case "/writing-editWriting.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				w_no = Integer.parseInt(request.getParameter("w_no"));
				
				writingService = WritingService.getInstance();
				writing = writingService.readWriting(w_no);
				
				view = "writing/editWriting";
				request.setAttribute("writing", writing);
				
				break;
				
			case "/writing-deleteWriting.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				w_no = Integer.parseInt(request.getParameter("w_no"));
				
				writingService = WritingService.getInstance();
				writingService.deleteWriting(w_no);
				
				view = "/firstboard/writing-main.do";
				isRedirected = true;
				break;	
				
			case "/user-login.do":
				view = "user/login";
				break;
				
			case "/user-login-process.do":
				id = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService = UserService.getInstance();
				user = userService.loginUser(id,pw);
							
				if(user != null) {
					session = request.getSession();
					session.setAttribute("user", user);

					view = "/firstboard/writing-main.do";
					isRedirected = true;
				} else {
					view = "user/login-fail";
				}			
				break;
				
			case "/comments-process-writeComments.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				comments = new Comments();
				comments.setC_content(request.getParameter("content"));
				comments.setC_master(Integer.parseInt(request.getParameter("master")));
				comments.setC_writer(user.getU_idx());
				String a = 		request.getParameter("group");
				
				if(request.getParameter("group") != null) {
					comments.setC_group(Integer.parseInt(request.getParameter("group")));
					comments.setC_order(Integer.parseInt(request.getParameter("order")));
					comments.setC_depth(Integer.parseInt(request.getParameter("depth")));
				} 
				
				commentsService = CommentsService.getInstance();
				commentsService.writeComments(comments);
				
				list2 = commentsService.getComments(comments.getC_master());
				view = "comments/comment-list";
				request.setAttribute("list2", list2);
				
				break;
				
			case "/user-insert-user.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				view = "user/insert-user";
				break;
				
			case "/user-insert-result.do":
				user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
						
				view = "user/insert-result";
				break;	
				
			case "/writing-logout.do":
				session = request.getSession();
				session.invalidate();
				
				view = "/firstboard/writing-main.do";
				isRedirected = true;
				break;	
				
			case "/access-denied.do":
				view = "user/access-denied";
				break;
		}
		
		if (!isRedirected) { 
			RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(view);
		}
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command ) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				 "/writing-write.do"
				, "/writing-successWrite.do"
				, "/writing-successEdit.do"
				, "/writing-editWriting.do"
				, "/writing-deleteWriting.do"
		};
		
		for(String item : authList) {
			if(item.equals(command)) {
				if(session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		
		return command;
	}
}
