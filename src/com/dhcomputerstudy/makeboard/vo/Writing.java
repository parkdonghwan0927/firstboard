package com.dhcomputerstudy.makeboard.vo;

public class Writing {
	private int w_no;
	private String w_title;
	private String w_content;
	private String w_time;
	private String tmpU_name;
	private int w_writer;
	private int w_views;
	private int w_group;
	private int w_order;
	private int w_depth;
	private int rownum;
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getW_no() {
		return w_no;
	}
	public void setW_no(int w_no) {
		this.w_no = w_no;
	}
	public String getW_title() {
		return w_title;
	}
	public void setW_title(String w_title) {
		this.w_title = w_title;
	}
	public String getW_content() {
		return w_content;
	}
	public void setW_content(String w_content) {
		this.w_content = w_content;
	}
	public int getW_writer() {
		return w_writer;
	}
	public void setW_writer(int w_wirter) {
		this.w_writer = w_wirter;
	}
	public String getW_time() {
		return w_time;
	}
	public void setW_time(String w_time) {
		this.w_time = w_time;
	}
	public int getW_views() {
		return w_views;
	}
	public void setW_views(int w_views) {
		this.w_views = w_views;
	}
	public int getW_group() {
		return w_group;
	}
	public void setW_group(int w_group) {
		this.w_group = w_group;
	}
	public int getW_order() {
		return w_order;
	}
	public void setW_order(int w_order) {
		this.w_order = w_order;
	}
	public int getW_depth() {
		return w_depth;
	}
	public void setW_depth(int w_depth) {
		this.w_depth = w_depth;
	}
	public String getTmpU_name() {
		return tmpU_name;
	}
	public void setTmpU_name(String tmpU_name) {
		this.tmpU_name = tmpU_name;
	}	
}
