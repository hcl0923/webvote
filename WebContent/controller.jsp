<%@page import="com.yc.biz.UserBiz"%>
<%@page import="com.yc.bean.User"%>
<%@page import="com.yc.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User user=(User)session.getAttribute("user");
	if(null==user){
		response.sendRedirect("login.jsp");
		return ;
	}
%>
<div class="profile">
	您好，a <span class="return"><a href="index.jsp">返回列表</a></span> 
	<span class="addnew"><a href="add.jsp">添加新投票</a></span>
	<span class="modify"><a href="manage.jsp">维护</a></span>

</div>