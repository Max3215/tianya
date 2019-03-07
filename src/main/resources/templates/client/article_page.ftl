<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>文章页</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>

<!-- 客服弹窗 -->
<style>
	.talkdialog {
		position: fixed;
		float: left;
		top:0;
		left: 0;
		width: 500px;
		height: 240px;
		border-radius: 7px;
		overflow: hidden;
		background: url("/client/images/talkdialogbg.jpg") no-repeat;
		z-index: 888;
	}
	.talkdialog .dialogclose {
		float: right;
		color: #fff;
		background: transparent;
		width: 54px;
		height: 28px;
		line-height: 28px;
		border-radius: 0 0 0 5px;
		box-shadow: 0 0 5px;
		text-align: center;
		text-decoration: none;
	}
	.talkdialog .indialog {
		line-height: 42px;
		float: left;
		width: 230px;
		margin-left: 265px;
		margin-top: 55px;
	}
	.talkdialog .indialog .talkforqq {
		float: left;
		padding: 0 40px 0 70px;
		border: 1px solid #fff;
		border-radius: 4px;
		line-height: 38px;
		background: transparent url("/client/images/talkforqqicon.png") 35px center no-repeat;
		color: #fff;
		text-decoration: none;
		margin-top: 5px;
	}
</style>
<body>

<#include "/client/common_header.ftl" />

<!-- ********************中间内容******************** -->
<div class="percenter_com">
	<div class="page_adress">
		<a href="/">天涯国旅首页</a>
	</div>
	<!-- **********中间-左边********** -->
	<ul class="frame_left">
		<#--<li class="active"><a href="#">关于我们</a></li>-->
		<#if info_list??>
			<#list info_list as item>
				<li><a href="/content/${item.id?c}?mid=${mid!'10'}">${item.title!''}</a></li>
			</#list>
		</#if>
	</ul>
	<!-- **********中间-左边-结束********** -->

	
	<!-- **********中间-右边********** -->
	<div class="frame_right">
	<input id="popFlag" type="text" hidden="hidden" value="${info.title!''}"> <#--用于js判断是否需要弹窗-->
	<#if info??>
		<h2>${info.title!''}</h2>
			${info.content!''}
	</#if>
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->

<div class="talkdialog" id="talkdialog" style="display: none;">
	<a href="javascript:;" title="" class="dialogclose">关闭</a>
	<div class="indialog">
		<p style="font-size: 22px; color: #1e3c7f;">酒店、机票代理</p>
		<p style="font-size: 18px; color: #fff;">咨询热线：${setting.telephone!''}</p>
		<a href="tencent://message/?uin=${popQQ!''}&Site=&menu=yes" title="" class="talkforqq">QQ在线咨询</a>
	</div>
</div>

<script>
	$(function(){
		//只有酒店代理和机票代理弹窗
		var popFlag = $("#popFlag").val();
		if(popFlag=="酒店代理" || popFlag=="机票代理"){
		
		  var time1=setTimeout(function(){
		  	showdialog();
		  	var time =1000*20;
		  	var timer = setInterval(showdialog,time);
		  },5000);
		}
	});
	function boxfix(){
		var oBox = $("#talkdialog");
		var wi = ($(window).width() - oBox.width()) / 2;
		var hei = ($(window).height() - oBox.height()) / 2;
		hei = hei < 0 ? 0 : hei;
		wi = wi < 0 ? 0 : wi;
		oBox.css({left: wi, top: hei, margin: 0});
	}
	function showdialog(){
		var timer=setInterval(boxfix,100);
		$("#talkdialog").fadeIn(500);
		$('#talkdialog .dialogclose').bind('click',function(){
			$('#talkdialog').fadeOut(500);
		});
	}
</script>
<div class="h20"></div>


<#include "/client/common_footer.ftl" />
</body>
</html>