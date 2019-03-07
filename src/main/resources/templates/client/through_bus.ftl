<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><#if setting??>${setting.seoTitle!''}-</#if>旅游直通车</title>
	<meta name="keywords" content="<#if setting??>${setting.seoKeywords!''}</#if>">
	<meta name="description" content="<#if setting??>${setting.seoDescription!''}</#if>">
	<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<link rel="stylesheet" type="text/css" href="/client/css/rich_css.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/client/js/f_aboutjs.js"></script>
<script type=text/javascript src="/mag/js/WdatePicker.js"></script>
<!--价格日历控件样式-->
<link href="/client/css/datepicker.css" rel="stylesheet" />
<!--价格日历控件-->
<script src="/client/js/zlDate.js"></script>

<script type="text/javascript">
	window.onload = function(){
		banner_go('my');//焦点图
		become_big('act_main li a',30);//图片发放大
		box_out();//图片滑出
		act_nav();
		$("#date1").timepicki();
		$("#date2").timepicki();
	};

	function tiaozhuan(){
			alert("该车的车票已经卖完！请您选择其他车辆。");
	}
	
</script>

<body>
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
	.mymember_page dd{
		float:left;
	    width: 30px;
	    height: 30px;
	    line-height: 30px;
	    margin-left:10px;
	    text-align: center;
	    border: 1px solid #033a7a;
	}
</style>
<#include "/client/common_header.ftl" />

<!-- 轮播图 -->
<div class="index_banner">
	<div class="banner_scrool">
		<#if big_scroll_ad_list??>
			<#list big_scroll_ad_list as item>
				<a href="${item.linkUri!''}"><img src="${item.fileUri!''}" /> </a>
			</#list>
		</#if>
	</div>
	<div class="move_btn">
		<ul>
			<#if big_scroll_ad_list??>
				<#list big_scroll_ad_list as item>
					<li><a></a></li>
				</#list>
			</#if>
		</ul>
	</div>
</div>
<!-- 轮播图-结束 -->
<div class="h20"></div>
<!-- ********************中间内容******************** -->
<div class="direct_train">
	<!-- 直通车 -->
	<dl class="dl1">
		<dt id="rent_cardl1_dt">
			<a href="/through/bus/51" class="<#if type?? && type==53>a1</#if>">景点直通车</a>
			<a href="/through/bus/52" class="<#if type?? && type==54>a1</#if>">城际直通车</a>
		</dt>
		<dd>
			<form action="/through/search" method="post">
			     <input type="hidden" value="<#if type??>${type!'0'}</#if>" name="type">
				<div class="dd1_left">
					<p>
						Tips: 重庆商旅服务有限公司，分布重庆各地及景点的旅游车，随时随地为您的出行提供方便，欢迎您的订购咨询。
					</p>
					<div class="left_time">
						<div>
							<input type="text" id="calendar1" class="in1" name="onSaleTime" <#if onSaleTime??>value="${onSaleTime!''}"</#if> onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" />
						</div>
					</div>
				</div>
				<div class="dd1_right">
					<input type="text" class="in1" name="leavePort" <#if leavePort??>value="${leavePort!''}"</#if> onfocus="if($(this).val()=='出发地点'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('出发地点');}" <#if leavePort??>value="${leavePort!''}"<#else>value="出发地点"</#if>>
					<input type="text" class="in2" name="reachPort" <#if reachPort??>value="${reachPort!''}"</#if> onfocus="if($(this).val()=='目标地点'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('目标地点');}" <#if reachPort??>value="${reachPort!''}"<#else>value="目标地点"</#if>>
					<div>
						<input type="submit" class="search" value="搜索" >
					</div>
				</div>
			</form>
		</dd>
	</dl>
	<dl class="dl2">
		<dt>
			<span class="span1"></span>
			<label>最新班次</label>
			<span class="span2"></span>
		</dt>
		<dd>
			<ul>
			<#if through_bus_page??>
			<#list through_bus_page.content as item>
				<li>
					<a href="/through/order?goodsId=${item.id!c}"  target="_blank"><img src="${item.coverImageUri!''}"></a>
					<div class="car_infor">
						<p class="p1"><a href="javascript:;" onclick="tiaozhuan(${item.id!c},${item.leftNumber!c});">${item.title!''}</a></p>
						<#--<p class="p1"><a href="/detail/${item.id?c}">${item.title!''}</a></p>-->
						<p>车型：${item.subTitle!''}</p>
						<p>载量：${item.returnPoints!0}人</p>
						<p>出发时间：<#if item.leaveDate??>${item.leaveDate?string("yyyy-MM-dd HH:mm:ss")}</#if></p>
						<p>出发地点：${item.leavePort!''}</p>
						<p>到达地点：${item.reachPort!''}</p>
						<div class="rob_bill">
							<p>余票：${item.leftNumber!0}</p>
							<#if item.leftNumber?? && item.leftNumber gt 0>
							<a href="/through/order?goodsId=${item.id!c}" target="_blank">立即抢票</a>
							<#else>
							 <a href="javascript:;" onclick="tiaozhuan();">立即抢票</a>
							</#if>
						</div>
					</div>
				</li>
			</#list>
			</#if>
			</ul>
		</dd>
	</dl>
	<#if through_bus_page??>
        <#assign continueEnter=false>
        <#if through_bus_page.totalPages gt 0>
           	<ul class="mymember_page">
            <#list 1..through_bus_page.totalPages as page>
                <#if page <= 3 || (through_bus_page.totalPages-page) < 3 || (through_bus_page.number+1-page)?abs<3 >
                    <#if page == through_bus_page.number+1>
                        <dd>
                            <a class="mysel" href="javascript:;">${page}</a>
                        </dd>
                    <#else>
                        <dd>
                            <a href="/search_list?page=${page-1}">${page}</a>
                        </dd>
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
</div>
<div class="h50"></div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

</body>
</html>