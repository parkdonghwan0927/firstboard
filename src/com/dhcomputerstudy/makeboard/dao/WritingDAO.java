package com.dhcomputerstudy.makeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dhcomputerstudy.makeboard.database.DBConnection;
import com.dhcomputerstudy.makeboard.vo.Writing;

public class WritingDAO {
	private static WritingDAO dao = null;
	
	private WritingDAO() {
		
	}

	public static WritingDAO getInstance() {
		if(dao == null) {
			dao = new WritingDAO();
		}
		return dao;
	}
	
	public ArrayList<Writing> getWriting(int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Writing> list = null;
		int pageNum = (page-1) * 5;
		
		try {
			conn = DBConnection.getConnection();
			String sql = new StringBuilder()
					.append("SELECT 	@ROWNUM := @ROWNUM -1 AS ROWNUM \n")
					.append("			 , ta.*\n")
					.append("			 , tc.u_name\n")
					.append("			 , CONCAT(REPEAT('ㄴ', ta.w_depth-1), w_title) w_title_all\n")
					.append("FROM			writing ta\n")
					.append("LEFT JOIN	(SELECT			@rownum := (SELECT COUNT(*) - ? + 1 FROM writing ta),\n")
					.append("									ta.w_no\n")
					.append("				FROM 				writing ta\n")
					.append("				) tb ON ta.w_no = tb.w_no\n")
					.append("LEFT JOIN	user tc ON ta.w_writer = tc.u_idx\n")
					.append("ORDER BY w_group desc, w_order ASC\n")
					.append("LIMIT ?, 5\n")
					.toString();
	       	pstmt = conn.prepareStatement(sql);
	       	pstmt.setInt(1, pageNum);
	       	pstmt.setInt(2, pageNum);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Writing>();

	        
	        while(rs.next()){     
	        	Writing writing = new Writing();
	        	
	        	writing.setRownum(rs.getInt("ROWNUM"));
	        	writing.setW_no(rs.getInt("w_no"));
	        	writing.setW_title(rs.getString("w_title_all"));
	        	writing.setW_content(rs.getString("w_content"));
	        	writing.setTmpU_name(rs.getString("u_name"));
	        	writing.setW_time(rs.getString("w_time"));
	        	writing.setW_views(rs.getInt("w_views"));
	        	writing.setW_group(rs.getInt("w_group"));
	        	writing.setW_order(rs.getInt("w_order"));
	        	writing.setW_depth(rs.getInt("w_depth"));
       	       	list.add(writing);
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
	
	public void writeWriting(Writing writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(writing.getW_group() == 0) {
			try {
				conn = DBConnection.getConnection();
				String sql = "insert into writing(w_title, w_content, w_writer, w_group, w_order, w_depth) values(?, ?, ?, 0, 1, 1);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, writing.getW_title());
				pstmt.setString(2, writing.getW_content());
				pstmt.setInt(3, writing.getW_writer());	
				pstmt.executeUpdate();
				pstmt.close();
				sql = "update writing set w_group = last_insert_id() where w_no = last_insert_id()";
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
				String sql = "update writing set w_order = w_order + 1 where w_group = ? AND w_order > ?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, writing.getW_group());
				pstmt.setInt(2, writing.getW_order());
				pstmt.executeUpdate();
				pstmt.close();
				
				sql = "insert into writing(w_title, w_content, w_writer, w_group, w_order, w_depth) values(?, ?, ?, ?, ?, ?);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, writing.getW_title());
				pstmt.setString(2, writing.getW_content());
				pstmt.setInt(3, writing.getW_writer());
				pstmt.setInt(4, writing.getW_group());
				pstmt.setInt(5, writing.getW_order());
				pstmt.setInt(6, writing.getW_depth() + 1);
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
	
	public void editWriting(Writing writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE writing SET w_title = ?, w_content = ? WHERE w_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writing.getW_title());
			pstmt.setString(2, writing.getW_content());
			pstmt.setInt(3, writing.getW_no());
			pstmt.executeUpdate();
		} catch( Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Writing readWriting(int w_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Writing wrt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE writing set w_views = w_views + 1 WHERE w_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, w_no);
			pstmt.executeUpdate();
			pstmt.close();
			
			String query = "SELECT * FROM writing LEFT JOIN user ON writing.w_writer = user.u_idx WHERE w_no = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, w_no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				wrt = new Writing();
				wrt.setW_no(rs.getInt("w_no"));
				wrt.setW_title(rs.getString("w_title"));
				wrt.setTmpU_name(rs.getString("u_name"));
				wrt.setW_content(rs.getString("w_content"));
				wrt.setW_views(rs.getInt("w_views"));
				wrt.setW_time(rs.getString("w_time"));
				wrt.setW_writer(rs.getInt("w_writer"));
				wrt.setW_group(rs.getInt("w_group"));
			}
			
		} catch( Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return wrt;
	}
	
	public void deleteWriting(int w_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from writing where w_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, w_no);
			pstmt.executeUpdate();			
		} catch( Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getWritingCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM writing ";
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
}
