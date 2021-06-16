<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.biz.VoteBiz"%>
<%@page import="com.yc.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	WebUtil.setCharacterEncoding(request, response);
	VoteBiz biz=new VoteBiz();
	List<Map<String,Object>> list=biz.findAll();
	Gson gson=new Gson();
	String info=gson.toJson(list);
	out.print(info);
%>