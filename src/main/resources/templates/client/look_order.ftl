<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>查看订单</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type="text/javascript" src="/client/js/luyi.js"></script>
<body>

<#include "/client/common_header.ftl" />


<!-- ********************中间内容******************** -->
<div class="percenter_com my_order">
	
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<!--
		<div class="right_title">
			<a href="#" class="thisa">全部订单</a>
			<a href="#">待支付（<span>0</span>）</a>
			<a href="#">待评价（<span>0</span>）</a>
		</div>
		<form class="find_order">
			<div class="d1">
				<label>订单号：</label>
				<input type="text">
			</div>
			<div class="d2">
				<label>产品名称：</label>
				<input type="text">
			</div>
			<div class="d3">
				<label>订单日期：</label>
				<input type="text">
			</div>
			<div class="d4"><input type="submit" value="查询"></div>
		</form>
		-->
		<dl class="see_orders">
			<dt>
				<label>订单详情</label>
				<a href="javascript:history.go(-1);">返回</a>
			</dt>
			<dd>
				<div class="obj_div1">
					<div class="div1_box1">
						<label class="rla1">订单号：<span>${order.orderNumber!''}</span></label>
						<label class="rla2">创建日期：<span>${order.orderTime!''}</span></label>
						<label class="rla3">状态：
						<span>
							<#if order??>
								<#if order.statusId==1>
									待确认
								<#elseif order.statusId==2>
									待付款
								<#elseif order.statusId==3>
									待服务
								<#elseif order.statusId==4>
									待评价
								<#elseif order.statusId==5>
									已完成
								<#elseif order.statusId==6>
									已取消
								</#if>
							</#if>
						</span></label>
						<!--
						<a href="#">立即支付</a>
						-->
					</div>
					<div class="div1_box2">
						<div class="box2_left">
							<#if goods?? && goods.coverImageUri??><img src="${goods.coverImageUri!''}"></#if>
							<div class="box2_left_detail">
								<p class="box2_left_p1">${order.goodsTitle!''}</p>
								<p class="box2_left_p2">产品类型：<span>${order.orderType!''}</span></p>
							</div>
						</div>
						<div class="box2_right">总价：<span>￥${order.totalPrice!''}</span></div>
					</div>
				</div>
				<div class="obj_div2">
					<#if goods?? && goods.categoryIdTree?contains("[1]")>
						<div class="obj_title">已选航线：</div>
						<div class="obj_body">
							<label>出发港口：<span>${goods.leavePort!''}</span></label>
							<label>到达港口：<span>${goods.reachPort!''}</span></label>
							<label>途径港口：<span>${goods.passPort!''}</span></label>
							<label>邮轮公司：<span>${goods.shipCompany!''}</span></label>
							<label>出发时间：<span>${order.leaveDate!''}</span></label>
						</div>
					<#elseif goods?? && goods.categoryIdTree?contains("[2]")>
					<div class="obj_title">已选地点：</div>
                        <div class="obj_body">
                            <label>出发地点：<span>${goods.leavePort!''}</span></label>
                            <label>到达地点：<span>${goods.reachPort!''}</span></label>
                            <label>途径地点：<span>${goods.passPort!''}</span></label>
                            <label>交通公司：<span>${goods.shipCompany!''}</span></label>
                            <label>出发时间：<span>${order.leaveDate!''}</span></label>
                        </div>
					<#elseif goods?? && goods.categoryIdTree?contains("[5]")>
						<div class="obj_title">已选地点：</div>
						<div class="obj_body">
							<label>出发地点：<span>${goods.leavePort!''}</span></label>
							<label>到达地点：<span>${goods.reachPort!''}</span></label>
							<label>途径地点：<span>${goods.passPort!''}</span></label>
							<label>交通公司：<span>${goods.shipCompany!''}</span></label>
							<label>出发时间：<span>${order.leaveDate!''}</span></label>
						</div>
					<#elseif goods?? && goods.categoryIdTree?contains("[23]")>
						<div class="obj_title">已选签证：</div>
						<div class="obj_body">
							<label>签证国家：<span>${goods.title!''}</span></label>
							<label>提交材料日期：<span>${order.orderTime?string("yyyy:MM:dd HH:mm:ss")}</span></label>
							<label>办理天数：<span>${goods.groupSaleLeftNumber!''}</span></label>
						</div>
					<#elseif goods?? && goods.categoryIdTree?contains("[29]")>
						<div class="obj_title">已租汽车：</div>
						<div class="obj_body">
							<label>租车天数：<span>${order.shopId!''}</span></label>
							<label>取车时间：<span>${order.appointmentTime!''}</span></label>
							<label>取车地点：<span>${goods.leavePort!''}</span></label>
							<label>还车地点：<span>${goods.reachPort!''}</span></label>
							<label>用车方式：<span>${order.deliveryPerson!''}</span></label>
							<label>押金：<span>${order.includePrice?string("0.00")}元</span></label>
						</div>
					<#elseif goods??>
						<div class="obj_title">已选直通车：</div>
						<div class="obj_body">
							<label>出发时间：<span><#if goods.onSaleTime??>${goods.onSaleTime?string("yyyy-MM-dd HH:mm:ss")}</#if></span></label>
							<label>上车地点：<span>${goods.leavePort!''}</span></label>
							<label>到达地点：<span>${goods.reachPort!''}</span></label>
						</div>
					</#if>
				</div>
				<#if goods?? && !goods.categoryIdTree?contains("[23]")>
				<div class="obj_div2">
					<div class="obj_title">已选择内容：</div>
					<#if order_goods_list??>
						<#list order_goods_list as item>
							<div class="obj_body">
								<label>${item.goodsTitle!''}</label>
							</div>
						</#list>
					</#if>
				</div>
				</#if>
				<#if goods?? && !goods.categoryIdTree?contains("[23]") && !goods.categoryIdTree?contains("[29]")>
				<div class="obj_div5">
					<div class="obj_title">旅客信息:</div>
					<ol class="obj_body">
						<#if order_visitor_list??>
							<#list order_visitor_list as item>
								<li>
									<div class="d_left1">${item.visitorName!''}</div>
									<div class="d_left2">${item.sex!''}</div>
									<div class="d_left3">
										<span>${item.certificateType!''}</span>
										-
										<span>${item.certificateCardCode!''}</span>
									</div>
									<div class="d_left4">
										${item.telephone!''}
									</div>
								</li>
							</#list>
						</#if>
					</ol>
				</div>
				</#if>
			</dd>
		</dl>
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>