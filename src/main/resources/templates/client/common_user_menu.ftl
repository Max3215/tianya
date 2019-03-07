<!-- **********中间-左边********** -->
	<div class="body_left">
		<div class="left_title"><a href="/user/0">我的账户</a></div>
		<!-- 手风琴效果 -->
		<#if !isQQLogin??>
		 <div id="firstpane" class="menu_list">
		    <p class="menu_head current icon1">我的订单</p>
		    <div style="display:block" class="menu_body" >
		      <a href="/user/order/list/0">全部订单</a>
		      <a href="/user/order/list/2">待付款</a>
		      <a href="/user/order/list/3">待服务</a>
		      <a href="/user/order/list/4">待评价</a>
		      <a href="/user/order/list/5">已完成</a>
		      <a href="/user/order/list/6">已取消</a>
		    </div>
		    
		    <p class="menu_head icon2"><a href="/user/point/list">我的积分</a></p>
		    <p class="menu_head icon3"><a href="/user/coupon/list">我的优惠券</a></p>
		    <p class="menu_head icon4"><a href="/user/collect/list">我的收藏</a></p>
		    <p class="menu_head icon4"><a href="/user/demand/list">私家定制</a></p>
		    <p class="menu_head icon4"><a href="/user/rentcar2/list">车辆管理</a></p>
		</div>
		<div class="password_fix ">
			<a href="/user/password" class="icon5">登录密码修改</a>
			<a href="/user/visitor" class="icon6">常用游客信息</a>
			<a href="/user/address/list" class="icon7">管理收货地址</a>
		</div>
		</#if>
		<div class="others">
			<a href="/user/my_comment" class="icon8">我的点评</a>
			<a href="/user/travel/notes" class="icon10">旅行感受</a>
			<a href="/user/travel/notes2" class="icon9">旅行攻略</a>
			<a href="/user/travel/notes3" class="icon3">旅行图片</a>
			<a href="/user/travel/notes4" class="icon1">旅行推荐</a>
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