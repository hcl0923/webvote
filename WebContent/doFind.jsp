<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="com.yc.biz.VoteBiz"%>
<%@page import="com.yc.bean.Vote"%>
<%@page import="com.yc.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	WebUtil.setCharacterEncoding(request, response);
	Vote bean=WebUtil.parseRequest(request, Vote.class);
	VoteBiz biz=new VoteBiz();
	System.out.println(biz.findByTrem(bean).getVname());
	String vid=bean.getVid();
	Map<String,Object> option=biz.findByVid(vid);
	option.put("vname", biz.findByTrem(bean).getVname());
	Gson gson=new Gson();
	String info=gson.toJson(option);
	out.print(info);
%>