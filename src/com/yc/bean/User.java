package com.yc.bean;

import java.io.Serializable;
/**
 * 用户实体类
 * @author hp
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = -7721794040704314282L;
	private Integer usid;
	private String uname;
	private String pwd;
	public Integer getUsid() {
		return usid;
	}
	public void setUsid(Integer usid) {
		this.usid = usid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
