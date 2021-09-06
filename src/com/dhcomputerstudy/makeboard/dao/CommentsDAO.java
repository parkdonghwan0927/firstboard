package com.dhcomputerstudy.makeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dhcomputerstudy.makeboard.database.DBConnection;
import com.dhcomputerstudy.makeboard.vo.Comments;
import com.dhcomputerstudy.makeboard.vo.Writing;

public class CommentsDAO {
	private static CommentsDAO dao = null;
	
	private CommentsDAO() {
		
	}

	public static CommentsDAO getInstance() {
		if(dao == null) {
			dao = new CommentsDAO();
		}
		return dao;
	}
	
	public ArrayList<Comments> getComments(int w_group) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comments> list = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = new StringBuilder()
					.append("SELECT 	*\n")
					.append("FROM		comments ta\n")
					.append("LEFT JOIN 	user tb ON ta.c_writer = tb.u_idx\n")
					.append("WHERE			c_master = ?\n")
					.append("ORDER BY c_group ASC, c_order asc\n")
					.toString();
	       	pstmt = conn.prepareStatement(sql);
	       	pstmt.setInt(1, w_group);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Comments>();

	        
	        while(rs.next()){     
	        	Comments comments = new Comments();
	        	comments.setC_content(rs.getString("c_content"));
	        	comments.setC_no(rs.getInt("c_no"));
	        	comments.setTmpU_name(rs.getString("u_name"));
	        	comments.setC_time(rs.getString("c_time"));
	        	comments.setC_group(rs.getInt("c_group"));
	        	comments.setC_order(rs.getInt("c_order"));
	        	comments.setC_depth(rs.getInt("c_depth"));
       	       	list.add(comments);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
					rs.close();
					pstmt.close();
					conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public int getCommentsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM comments";
	       	pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){     
	        	count = rs.getInt("count");
	        }
		} catch (Exception e) {
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}	
	
	public void writeComments(Comments comments) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(comments.getC_group() == 0) {
			try {
				conn = DBConnection.getConnection();
				String sql = "insert into comments(c_content, c_master, c_writer, c_group, c_order, c_depth) values( ?, ?, ?, 0, 1, 1);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, comments.getC_content());
				pstmt.setInt(2, comments.getC_master());
				pstmt.setInt(3, comments.getC_writer());	
				pstmt.executeUpdate();
				pstmt.close();
				sql = "update comments set c_group = last_insert_id() where c_no = last_insert_id()";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			} catch( Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				conn = DBConnection.getConnection();
				String sql = "update comments set c_order = c_order + 1 where (c_group = ? AND c_order > ?);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, comments.getC_group());
				pstmt.setInt(2, comments.getC_order());
				pstmt.executeUpdate();
				pstmt.close();
				sql = "insert into comments(c_content, c_master, c_writer, c_group, c_order, c_depth) values(?, ?, ?, ?, ?, ?);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, comments.getC_content());
				pstmt.setInt(2, comments.getC_master());
				pstmt.setInt(3, comments.getC_writer());
				pstmt.setInt(4, comments.getC_group());
				pstmt.setInt(5, comments.getC_order() + 1);
				pstmt.setInt(6, comments.getC_depth() + 1);
				pstmt.executeUpdate();					
			} catch( Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
