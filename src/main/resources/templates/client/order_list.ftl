<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>我的订单</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/client/js/luyi.js"></script>
<script type=text/javascript src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>

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
	<input type="hidden" name="type" value="${statusId!'0'}"/>
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


<!-- ********************中间内容******************** -->
<div class="percenter_com my_order">
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">
			<a href="/user/order/list/0" <#if !statusId?? || statusId ==0>class="thisa"</#if>>全部订单</a>
			<a href="/user/order/list/2" <#if statusId?? && statusId==2>class="thisa"</#if>>待付款（<span><#if total_unpayed??>${total_unpayed!0}</#if></span>）</a>
			<a href="/user/order/list/3" <#if statusId?? && statusId==3>class="thisa"</#if>>待服务（<span><#if total_server??>${total_server!0}</#if></span>）</a>
			<a href="/user/order/list/4" <#if statusId?? && statusId==4>class="thisa"</#if>>待评价（<span><#if total_unreceived??>${total_unreceived!0}</#if></span>）</a>
			<a href="/user/order/list/5" <#if statusId?? && statusId==5>class="thisa"</#if>>已完成（<span><#if total_common??>${total_common!0}</#if></span>）</a>
			<a href="/user/order/list/6" <#if statusId?? && statusId==6>class="thisa"</#if>>已取消（<span><#if total_connel??>${total_connel!0}</#if></span>）</a>
		</div>
		<form class="find_order" action="/user/order/list/${statusId!'0'}" method="POST">
			<div class="d1">
				<label>订单号：</label>
				<input type="text" name="orderNumber" <#if orderNumber??>value="${orderNumber!''}"</#if>>
			</div>
			<!--
			<div class="d2">
				<label>产品类型：</label>
				<input type="text">
			</div>
			-->
			<div class="d3">
				<label>产品类型：</label>
				<!--<input name="orderTime" type="text" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" <#if orderTime??>value="${orderTime!''}"</#if> readonly="true">-->
				<input name="orderType" type="text" class="input date" <#if orderType??>value="${orderType!''}"</#if>>
			</div>
			<div class="d4"><input type="submit" value="查询"></div>
		</form>
		<dl>
			<dt>
				<div class="div1">订单号</div>
				<div class="div2">产品名称</div>
				<div class="div3">产品类型</div>
				<div class="div4">金额</div>
				<div class="div5">订单状态</div>
				<div class="div6">操作</div>
			</dt>
			<dd>
				<ul id="obj_assess">
					<#if order_page??>
						<#if order_page.content??>
							<#list order_page.content as content>
							<#if content.orderType!="客车包租">
								<li>
									<div class="div1"><a href="/user/look/order?orderId=${content.id}">${content.orderNumber!''}</a></div>
									<#--<div class="div1">${content.orderNumber!''}</div>-->
									<div class="div2" id="mingchen">${content.goodsTitle!''}  </div>
									<div class="div3">${content.orderType!''}</div>
									<div class="div4">${content.totalPrice!''}元 </div>
									<div class="div5">
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
									<div class="div6">
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
											 | <a href="/user/order/delete/${statusId!'0'}?id=${content.id!c}">删除</a>
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
	                                <a class="mysel" href="javascript:;">${page?c}</a>
	                            </li>
                            <#else>
	                            <li>
	                                <a href="/user/order/list/${statusId!'0'}?page=${(page-1)?c}<#if orderNumber??>&orderNumber=${orderNumber!''}</#if><#if orderType??>&orderType=${orderType!''}</#if>">${page?c}</a>
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
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>