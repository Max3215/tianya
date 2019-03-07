<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>重设密码</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<link rel="stylesheet" type="text/css" href="/client/css/add_page.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>

<script>
	$(document).ready(function(){
		//初始化表单验证
		$("#form1").Validform({
	        tiptype: 3
	    });
	});
</script>
<body>
<!-- ********************头部******************** -->
<!-- ********************头部-结束******************** -->


<!-- ********************导航******************** -->
<#include "/client/common_header.ftl" />
<!-- ********************导航-结束******************** -->

<!-- ********************中间内容******************** -->
<div class="content_bg">
	<div class="h100"></div>
	<div class="rewrite_password">
		<div class="c_left">
			<h1>找回密码：</h1>
			<div class="h260"></div>
			<p class="p2"><a href="javascript:history.go(-1);">返回</a>&nbsp;|<a href="/">首页</a></p>
		</div>
		<form class="c_right" id="form1" method="POST" action="/user/saveResetPassword">
			<div class="div2">
				<label>新密码：</label>
				<input class="text" type="password" name="password" datatype="/\w{5,17}$/i" errormsg="不包含空格"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div4">
				<label>确认密码：</label>
				<input class="text" type="password" name="password2" datatype="/\w{5,17}$/i" recheck="password"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div6">
				<input type="submit" value="确认修改">
			</div>
		</form>
	</div>
</div>
<!-- ********************中间内容-结束******************** -->


<!-- ********************底部******************** -->
<div class="foot_bgcolor">
	<div class="foot">
		<ul class="foot1">
			<li><a href="#">网站首页</a></li>
			<li><a href="#">关于我们</a></li>
			<li><a href="#">加入我们</a></li>
			<li><a href="#">商务合作</a></li>
		</ul>
		<ul class="foot2">
			<li><a href="#">常见问题</a></li>
			<li><a href="#">支付方式</a></li>
			<li><a href="#">预定流程</a></li>
			<li><a href="#">售后服务</a></li>
		</ul>
		<div class="foot3">
			<p><label>联系方式：</label></p>
			<p>
				<label>定制热线：<span>400-888-8888</span></label>
				<label>售后服务：<span>400-888-8888</span></label>
			</p>
			<p><label>邮箱：<span>info@sparklechina.com</span></label></p>
			<p><label>联系地址：<span>重庆市渝北区黄泥磅俊朗中心3栋5楼</span></label></p>
		</div>
		<div class="foot4">
			<img src="images/qrcode.png">
		</div>
	</div>
</div>
<div class="flast">版权所有：2015© copyright 天涯国际旅行社</div>
<!-- ********************底部-结束******************** -->
</body>
</html>