<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>在线投票系统</title>
<link type="image/x-icon" href="images/yc.png" rel="shortcut icon" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>

<body>
	<div id="header" class="wrap">
		<img src="images/logo.gif" />
	</div>
	<div id="navbar" class="wrap">
		<%@ include file="controller.jsp"%>
		<div class="search">
			<form method="post" action="Subject!search.action">
				<input type="text" name="keywords" class="input-text" value="" /><input
					type="submit" name="submit" class="input-button" value="" />
			</form>
		</div>
	</div>

	<div id="vote" class="wrap">
		<h2>查看投票</h2>
		<ul class="list">
			<li>
				<h4></h4>
				<p class="info"></p>
				<ol>
					<!--  -->
				</ol>
				<div class="goback">
					<a href="index.jsp">返回投票列表</a>
				</div>
			</li>
		</ul>
	</div>
	<div id="footer" class="wrap">源辰信息 &copy; 版权所有</div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	//获取地址
	var url=window.location.href;
	//判断  如何不合法  跳转到index.jsp
	var arr=url.split('#');
	if(arr.length!=2){
		window.location.href='index.jsp';
	}
	var vid=arr[1].split('=')[1];
	$.get('doFind.jsp',{vid:vid}, function(data) {
		var str=data.vname;
		var str1='共有'+data.options+'个选项，已有'+data.users+'个网友参与了投票。';
		$('.list li h4').html(str);
		$('.info').html(str1);
	}, 'json');
	
	$.get('doView.jsp',{vid:vid},function(data){
		show(data);
	},'json');//根据vid搜索options
	function show(arr){
		var str='';
		for(var i=0;i<arr.length;i++){
			str+='<li><div class="rate"><div class="ratebg"><div class="percent" style="width: '+arr[i].views+'%"></div></div><p>'+arr[i].views+'票<span>('+arr[i].views+'%)</span></p></div></li>'
		}
		//设置
		$('.list li ol').html(str);
	}
</script>
</html>
