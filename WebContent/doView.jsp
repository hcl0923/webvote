<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.biz.OptionBiz"%>
<%@page import="com.yc.bean.Option"%>
<%@page import="com.yc.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	WebUtil.setCharacterEncoding(request, response);
	Option bean=WebUtil.parseRequest(request, Option.class);
	OptionBiz biz=new OptionBiz();
	List<Option> options=biz.find(bean);
	Gson gson=new Gson();
	String info=gson.toJson(options);
	out.print(info);
%>