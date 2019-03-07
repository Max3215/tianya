<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>个人中心</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/client/js/luyi.js"></script>

<style type="text/css">
	.mymember_page{
		float:right;
		overflow: hidden;
	    margin-right:50px;
	    font-size: 16px;
	    color: #033a7a;
	}
	.mymember_page:after{
		content:"clear";
		clear:both;
		height:0px;
		display:block;
		overflow:hidden;
	}
	.mymember_page li{
		float:left;
	    width: 30px;
	    height: 30px;
	    line-height: 30px;
	    margin-left:10px;
	    text-align: center;
	    border: 1px solid #033a7a;
	}

</style>

<body>
<#include "/client/common_header.ftl" />

<script type="text/javascript">
$(document).ready(function(){
    $("#1").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").removeClass("staryes");
    	$("#3").removeClass("staryes");
    	$("#4").removeClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("非常不满");
    	$("#id2").attr("value",1);
    	
    	
    });
    $("#2").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").removeClass("staryes");
    	$("#4").removeClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("不满意");
    	$("#id2").attr("value",2);
    });
    $("#3").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").addClass("staryes");
    	$("#4").removeClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("一般");
    	$("#id2").attr("value",3);
    });
    $("#4").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").addClass("staryes");
    	$("#4").addClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("满意");
    	$("#id2").attr("value",4);
    });
    $("#5").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").addClass("staryes");
    	$("#4").addClass("staryes");
    	$("#5").addClass("staryes");
    	$("#sp").html("非常满意");
    	$("#id2").attr("value",5);
    });
    $("#11").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").removeClass("staryes");
    	$("#33").removeClass("staryes");
    	$("#44").removeClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("非常不满");
    	$("#id3").attr("value",1);
    });
    $("#22").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").removeClass("staryes");
    	$("#44").removeClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("不满意");
    	$("#id3").attr("value",2);
    });
    $("#33").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").addClass("staryes");
    	$("#44").removeClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("一般");
    	$("#id3").attr("value",3);
    });
    $("#44").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").addClass("staryes");
    	$("#44").addClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("满意");
    	$("#id3").attr("value",4);
    });
    $("#55").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").addClass("staryes");
    	$("#44").addClass("staryes");
    	$("#55").addClass("staryes");
    	$("#kf").html("非常满意");
    	$("#id3").attr("value",5);
    });
    $("#111").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").removeClass("staryes");
    	$("#333").removeClass("staryes");
    	$("#444").removeClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("非常不满");
    	$("#id4").attr("value",1);
    });
    $("#222").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").removeClass("staryes");
    	$("#444").removeClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("不满意");
    	$("#id4").attr("value",2);
    });
    $("#333").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").addClass("staryes");
    	$("#444").removeClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("一般");
    	$("#id4").attr("value",3);
    });
    $("#444").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").addClass("staryes");
    	$("#444").addClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("满意");
    	$("#id4").attr("value",4);
    });
    $("#555").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").addClass("staryes");
    	$("#444").addClass("staryes");
    	$("#555").addClass("staryes");
    	$("#wl").html("非常满意");
    	$("#id4").attr("value",5);
    });
    
    $("#appraise").click(function(){
    	var mingchen = $("#mingchen").html();
    	$("#mingchen2").html(mingchen);
    })
});

function chuan(id){
	var mingchen = $("#mingchen").html();
    $("#mingchen2").html(mingchen);
	$("#id1").attr("value",id);
	$("#now_valuetion").css("display","block");
}

</script>


<form class="now_valuetion" id="now_valuetion" action="/user/order/comment" method="post">
	<input type="hidden" name="id" id="id1"/>
	<input type="hidden" name="goodsStar" id="id2"/>
	<input type="hidden" name="serviceStar" id="id3"/>
	<input type="hidden" name="skillStar" id="id4"/>
	<input type="hidden" name="type" value="${code!''}"/>
	<input type="hidden" name="type2" value="111"/>
	<dl>
		<dt>商品信息<a class="closei" id="closei">关闭 X</a></dt>
		<dd class="dd1" ></dd>
		<dd class="dd2">
			<img src="/client/images/specialty.png">
			<label id="mingchen2"></label>
		</dd>
		<dd class="dd3">评分：</dd>
		<dd class="dd4">
			<label>产品描述：</label>
			<a id="1"></a>
			<a id="2"></a>
			<a id="3"></a>
			<a id="4"></a>
			<a id="5"></a>
			<label class="la2" id="sp"></label>
		</dd>
		<dd class="dd5">
			<label>客服服务：</label>
			<a id="11"></a>
			<a id="22"></a>
			<a id="33"></a>
			<a id="44"></a>
			<a id="55"></a>
			<label class="la2" id="kf"></label>
		</dd>
		<dd class="dd6">
			<label>配送物流：</label>
			<a id="111"></a>
			<a id="222"></a>
			<a id="333"></a>
			<a id="444"></a>
			<a id="555"></a>
			<label class="la2" id="wl"></label>
		</dd>
		<dd class="dd7">评价：</dd>
		<dd class="dd8">
			<textarea name="content" id="content" cols="30" rows="10"></textarea>
		</dd>
		<dd class="dd9"><input type="submit" value="评价"></dd>
	</dl>
</form>


<div class="percenter_com person_center">
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<#if !isQQLogin??>
	<div class="body_right">
		<div class="right1">
			<div class="headp_left">
				<#if user??>
				<img src="${user.headImageUri!''}">
				</#if>
				<p><a href="/user/header/img">编辑头像</a></p>
			</div>
			<dl class="headp_right">
				<dt>
					<div class="headp_right1">
						<p><label class="la0">用户名：<#if user??>${user.username!''}</#if> <span>（注册时间：<#if user??>${user.registerTime!''}）, 等级: ${user.userLevelTitle!''}</#if></span></label></p>
						<p>
							<label class="la1">手   机：<#if user??>${user.mobile!''}</#if> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></label>
							<label  class="la2">邮   箱：<#if user??>${user.email!''}</#if> </label>
						</p>
						<p>
							<label class="la1">生日：<#if user.birthday??>${user.birthday?string("yyyy-MM-dd")}</#if></label>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="la1">QQ：<#if user??>${user.qq!''}</#if></label>
						</p>
					</div>
					<div class="headp_right2"><a href="/user/information/edit">修改</a></div>
				</dt>
				<dd>
				</dd>
			</dl>
		</div>
		<div class="h20"></div>
		<div class="right2">
			<div class="right2_title">
				<label>近期订单：</label>
				<a href="/user/2">待付款（<span><#if total_unpayed??>${total_unpayed!0}</#if></span>）</a>
				<a href="/user/4">待评价（<span><#if total_unreceived??>${total_unreceived!0}</#if></span>）</a>
			</div>
			<dl>
				<dt>
					<div class="div1">产品名称</div>
					<div class="div2">金额</div>
					<div class="div3">订单状态</div>
					<div class="div4">操作</div>
				</dt>
				<dd>
					<ul>
						<#if order_page??>
							<#if order_page.content??>
								<#list order_page.content as content>
								<#if content.orderType!="客车包租">
									<li>
										<div class="div1" id="mingchen">
											${content.goodsTitle!''}
										</div>
										<div class="div2">${content.totalPrice!''}元  </div>
										<div class="div3">
											<#if content.statusId??>
												<#if content.statusId==1>
													待确认
												<#elseif content.statusId==2>
													待付款
												<#elseif content.statusId==3>
													待服务
												<#elseif content.statusId==4>
													待评价
												<#elseif content.statusId==5>
													已完成
												<#elseif content.statusId==6>
													已取消
												</#if>
											</#if>
										</div>
										<div class="div4">
										<#if content.statusId??>
											<#if content.statusId=2>
												<a href="/order/dopay/${content.id!c}" style="color:red" target="_blank">支付</a>
											<#elseif content.statusId=5>
												<a href="javascript:;"  style="color:red" onclick="chuan(${content.id!c});" title='评价'>评价</a>
											<#else>
											 	| <a href="/user/look/order?orderId=${content.id}" style="color:red" target="_blank">查看</a>
											</#if>
											<#if content.statusId==6||content.statusId==7||content.statusId==8>
												| <a href="/user/look/order?orderId=${content.id}" target="_blank">查看详情</a>
											</#if>
											<#if content.statusId==3||content.statusId==4>
												 
											</#if>
											<#if content.statusId!=5&&content.statusId!=4&&content.statusId!=3&&content.statusId!=10>
												 | <a href="/user/order/delete/${code!''}?id=${content.id!c}">删除</a>
											</#if>
										</#if>
										</div>
									</li>
								</#if>
								</#list>
							</#if>
						</#if>
					</ul>
				</dd>
			</dl>
			
                <#if order_page??>
                <#assign continueEnter=false>
                <#if order_page.totalPages gt 0>
                <ul class="mymember_page">
                    <#list 1..order_page.totalPages as page>
                        <#if page <= 3 || (order_page.totalPages-page) < 3 || (order_page.number+1-page)?abs<3 >
                            <#if page == order_page.number+1>
	                            <li>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </li>
                            <#else>
	                            <li>
	                                <a href="/user<#if code??>/${code}</#if>?page=${page-1}">${page}</a>
	                            </li>
                            </#if>
                            <#assign continueEnter=false>
                        <#else>
                            <#if !continueEnter>
                                <b class="pn-break">&hellip;</b>
                                <#assign continueEnter=true>
                            </#if>
                        </#if>
                    </#list>
                    </ul>
                </#if>
                </#if>
        
			<div class="prompt">
			</div>
		</div>
	</div>
	</#if>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>