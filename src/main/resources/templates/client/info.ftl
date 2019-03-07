<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>文章页</title>
</head>
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<body>
<!-- ********************头部******************** -->
    <#include "/client/common_header.ftl">
<!-- ********************导航-结束******************** -->



<!-- ********************中间内容******************** -->
<div class="percenter_com my_score">
	<div class="page_adress">
		<a href="#">天涯国旅首页</a>
		&gt;
		<a href="#">个人中心</a>
		&gt;
		<a href="#">我的订单</a>
	</div>
	<!-- **********中间-左边********** -->
	<div class="body_left">
		<div class="left_title">我的账户</div>
		<!-- 手风琴效果 -->
		 <div id="firstpane" class="menu_list">
		    <p class="menu_head current icon1">我的订单</p>
		    <div style="display:block" class=menu_body >
		      <a href="#">旅游</a>
		      <a href="#">机票</a>
		      <a href="#">酒店</a>
		      <a href="#">用车</a>
		      <a href="#">旅游定制</a>
		      <a href="#">其他</a>
		    </div>
		    <p class="menu_head icon2">我的积分</p>
		    <div style="display:none" class=menu_body >
		      <a href="#">应用经济学</a>
		      <a href="#">理论经济学</a>
		      <a href="#">国民经济学</a>
		      <a href="#">区域经济学</a>
		      <a href="#">产业经济学</a>
		      <a href="#">国际贸易学</a>
		      <a href="#">劳动经济学</a>
		      <a href="#">政治经济学</a>
		    </div>
		    <p class="menu_head icon3">我的优惠券</p>
		    <div style="display:none" class=menu_body >
		      <a href="#">马克思主义基本原理</a>
		      <a href="#">马克思主义发展史</a>
		      <a href="#">马克思主义中国化研究</a>
		      <a href="#">国外马克思主义研究</a>
		      <a href="#">思想政治教育</a>
		    </div>
		    <p class="menu_head icon4">我的收藏</p>
		    <div style="display:none" class=menu_body >
		      <a href="#">体育人文社会学</a>
		      <a href="#">体育教育训练学</a>
		      <a href="#">民族传统体育学</a>
		    </div>
		</div>
		<div class="password_fix ">
			<a href="#" class="icon5">登录密码修改</a>
			<a href="#" class="icon6">常用游客信息</a>
			<a href="#" class="icon7">管理收货地址</a>
		</div>
		<div class="others">
			<a href="#" class="icon8">我的点评</a>
			<a href="#" class="icon9">我的攻略</a>
			<a href="#" class="icon10">我的游记</a>
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#firstpane .menu_body:eq(0)").show();
			$("#firstpane p.menu_head").click(function(){
				$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
				$(this).siblings().removeClass("current");
			});
			$("#secondpane .menu_body:eq(0)").show();
			$("#secondpane p.menu_head").mouseover(function(){
				$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
				$(this).siblings().removeClass("current");
			});
			
		});
		</script>

		<!-- 手风琴效果-结束 -->
	</div>
	<!-- **********中间-左边-结束********** -->

	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">积分规则</div>
		<ul class="L_article">
		<li>
			<h5>1、如何获取积分</h5>
			<p>
				为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！
			</p>
		</li>
		<li>
			<h5>1、如何获取积分</h5>
			<p>
				为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！
			</p>
		</li>
		<li>
			<h5>1、如何获取积分</h5>
			<p>
				为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！
			</p>
		</li>
		</ul>
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<!-- ********************底部******************** -->
    <#include "/client/common_footer.ftl">
<!-- ********************底部-结束******************** -->
</body>
</html>