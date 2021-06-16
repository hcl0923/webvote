package com.yc.bean;

import java.io.Serializable;
/**
 * 主题实体类
 * @author hp
 *
 */
public class Vote implements Serializable{

	private static final long serialVersionUID = 7012061992997465704L;
	private String vid;
	private String vname;
	private Integer vtype;
	private String startDate;
	private String endDate;
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public Integer getVtype() {
		return vtype;
	}
	public void setVtype(Integer vtype) {
		this.vtype = vtype;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
