package com.yc.bean;

import java.io.Serializable;
/**
 * 选项实体类
 * @author hp
 *
 */
public class Option implements Serializable{

	private static final long serialVersionUID = 4099662453909221085L;
	private Integer opid;
	private String vid;
	private String opname;
	private Integer views;
	private String usid;
	public Integer getOpid() {
		return opid;
	}
	public void setOpid(Integer opid) {
		this.opid = opid;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getOpname() {
		return opname;
	}
	public void setOpname(String opname) {
		this.opname = opname;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	
}
