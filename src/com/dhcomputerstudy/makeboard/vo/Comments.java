package com.dhcomputerstudy.makeboard.vo;

public class Comments {
	private int c_no;
	private int c_group;
	private int c_order;
	private int c_depth;
	private int c_master;
	private int c_writer;
	private String c_content;
	private String c_time;
	private String tmpU_name;

	public int getC_no() {
		return c_no;
	}
	
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	
	public int getC_group() {
		return c_group;
	}
	
	public void setC_group(int c_group) {
		this.c_group = c_group;
	}
	
	public int getC_order() {
		return c_order;
	}
	
	public void setC_order(int c_order) {
		this.c_order = c_order;
	}
	
	public int getC_depth() {
		return c_depth;
	}
	
	public void setC_depth(int c_depth) {
		this.c_depth = c_depth;
	}
	
	public int getC_master() {
		return c_master;
	}
	public void setC_master(int c_master) {
		this.c_master = c_master;
	}
	
	public String getC_content() {
		return c_content;
	}
	
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	
	public String getC_time() {
		return c_time;
	}
	
	public void setC_time(String c_time) {
		this.c_time = c_time;
	}

	public String getTmpU_name() {
		return tmpU_name;
	}

	public void setTmpU_name(String tmpU_name) {
		this.tmpU_name = tmpU_name;
	}

	public int getC_writer() {
		return c_writer;
	}

	public void setC_writer(int c_writer) {
		this.c_writer = c_writer;
	}
}
