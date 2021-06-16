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
		<h2>投票列表</h2>
		<ul class="list">
		<!-- 显示主题 -->
		</ul>
	</div>
	<div id="footer" class="wrap">源辰信息 &copy; 版权所有</div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	//发送ajax
	$.get('doFindAll.jsp', function(data) {
		var str='';
		for(var i=0;i<data.length;i++){//view查看详情
			str+='<li class="odd"><h4><a href="view.jsp#vid='+data[i].vid+'">'+data[i].vname+'</a></h4>'
				+'<div class="join"><a href="vote.jsp#vid='+data[i].vid+'">'+data[i].status+'</a></div>'
				+'<p class="info">共有'+data[i].options+'个选项，已有'+data[i].users+' 个网友参与了投票。</p></li>';
		}
		$('#vote .list').html(str);
	}, 'json');
</script>
</html>
