<%@page import="com.yc.biz.VoteBiz"%>
<%@page import="com.yc.bean.User"%>
<%@page import="com.yc.biz.UserBiz"%>
<%@page import="com.yc.bean.Vote"%>
<%@page import="com.yc.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	WebUtil.setCharacterEncoding(request, response);
	Vote bean=WebUtil.parseRequest(request, Vote.class);
	//选项
	String []options=request.getParameterValues("options");
	VoteBiz biz=new VoteBiz();
	int i=biz.add(bean,options);
	if(i>0){
		response.sendRedirect("index.jsp");
	}else{
		response.sendRedirect("add.jsp");
	}
%>