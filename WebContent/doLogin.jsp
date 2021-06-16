<%@page import="com.yc.biz.UserBiz"%>
<%@page import="com.yc.bean.User"%>
<%@page import="com.yc.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	WebUtil.setCharacterEncoding(request, response);
	if (request.getParameter("uname") == null || request.getParameter("pwd") == null
			|| "".equals(request.getParameter("uname")) || "".equals(request.getParameter("pwd"))) {
		response.sendRedirect("login.jsp");
		return;
	}
	User bean = WebUtil.parseRequest(request, User.class);
	UserBiz biz = new UserBiz();
	User user = biz.login(bean);
	if (null == user) {
		response.sendRedirect("login.jsp");
	} else {
		session.setAttribute("user", user);
		response.sendRedirect("index.jsp");
	}
%>