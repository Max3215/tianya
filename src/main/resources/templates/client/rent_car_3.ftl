<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><#if setting??>${setting.seoTitle!''}-</#if>客车租赁</title>
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

	<!--
	var d1 =[
		{
			"Date": "2015-10-10",
			"Price": "158"
		},
		{
			"Date": "2015-10-11",
			"Price": "158"
		},
		{
			"Date": "2015-10-12",
			"Price": "158"
		},
		{
			"Date": "2015-10-13",
			"Price": "158"
		},
		{
			"Date": "2015-10-14",
			"Price": "158"
		},
		{
			"Date": "2015-10-15",
			"Price": "158"
		},
		{
			"Date": "2015-10-16",
			"Price": "158"
		},
		{
			"Date": "2015-10-17",
			"Price": "158"
		},
		{
			"Date": "2015-11-15",
			"Price": "158"
		},
		{
			"Date": "2015-12-13",
			"Price": "158"
		},
		{
			"Date": "2015-12-14",
			"Price": "59"
		},
		{
			"Date": "2015-12-15",
			"Price": "158"
		},
		{
			"Date": "2015-12-16",
			"Price": "59"
		}
	];
	function AjaxTime(id){
		pickerEvent.setPriceArr(eval(d1));
		pickerEvent.Init(id);
	}
	-->
</script>


<body>


<#include "/client/common_header.ftl" />

<style type="text/css">
.mymember_page{
		float:right;
		overflow: hidden;
	    margin-right:50px;
	    font-size: 16px;
	    color: #033a7a;
	    margin-top:10px;
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
	.banner_positiontxt {
		padding:20px;
		position:absolute;
		top:50%;
		left:9%;
		font-size:16px;
		line-height:24px;
		color:#666;
		z-index:500;
		background: url(/client/images/bgpic.png) repeat;
	}
	.mymember_page .mysel{background:#033a7a;color:#fff;display:block;}
</style>
<!-- banner固定文字 -->
<div class="banner_positiontxt">
	<p>商旅服务中心隶属重庆交运集团 ，于2013年4月正式成立。</p>
    <p>积极整合交运集团内部和其他从事道路运输企业车辆资源。</p>
    <p>搭建个性化、系统化、规范化的综合运输平台。主要开展</p>
    <p>业务有汽车租赁、包车业务、代驾服务。</p>
    <p>汽车租赁：杨经理 13996265070</p>
</div>

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
<div class="rent_car">
	<!-- 直通车 -->
	<dl class="dl1">
		<dt id="rent_cardl1_dt">
			<a href="/rent/car_short">临租服务</a>
			<a href="/rent/car_long">长租服务</a>
			<a href="/rent/car2" class="a1">包车服务</a>
			<a href="/rent/car3">约租</a>
		</dt>
		<dd>
			<form action="/rent/search2" method="post">
				<div class="dd1_left">
					<input type="text" class="inp4"size="15" onfocus="if($(this).val()=='输入您期望的最低金额'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('输入您期望的最低金额');}" <#if price1??>value="${price1!''}"<#else>value="输入您期望的最低金额"</#if> id="homecity_name" name="price1">

					<input type="text" size="15" class="inp5" onfocus="if($(this).val()=='输入您期望的最高金额'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('输入您期望的最高金额');}" <#if price2??>value="${price2!''}"<#else>value="输入您期望的最高金额"</#if> id="getcity_name" name="price2">
				</div>
				<div class="dd1_right">
					<div hidden="hidden">
						<input type="text" class="inp1 inp6" name="subTitle" <#if subTitle??&&subTitle!=''>value="${subTitle!''}"<#else>value="输入您想要的车型"</#if> onfocus="if($(this).val()=='输入您想要的车型'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('输入您想要的车型');}"/>
					</div>
					<div>
						<select name="id11" id="id11" style="height: 40px;margin-left: 98px;width: 425px;">
				            <option value="0" selected="selected">&nbsp;&nbsp;请选择车型</option>
				        	<option value="小型车" <#if subTitle?? && subTitle=="小型车">selected="selected"</#if>>小型车</option>
				        	<option value="商务车" <#if subTitle?? && subTitle=="商务车">selected="selected"</#if>>商务车</option>
				        	<option value="越野车" <#if subTitle?? && subTitle=="越野车">selected="selected"</#if>>越野车</option>
				        </select>
					</div>
					<input type="submit" value="搜索" class="searc">
				</div>
			</form>
		</dd>
	</dl>
	<dl class="dl2">
		<dt>
			<span class="span1"></span>
			<label>热门车型</label>
			<span class="span2"></span>
		</dt>
		<dd>
		<#if rent_car_page??>
		<#list rent_car_page.content as item>
			<a href="/rent/middle2?goodsId=${item.id?c}" class="a1"  target="_blank">
				<img src="${item.coverImageUri!''}">
				<div class="rendcar_detail">
					<p>
						<label class="label1">${item.title!''}</label>
						<label class="label2"><span>￥${item.salePrice!0}</span> 起</label>
					</p>
					<p>${item.subTitle!''}</p>
				</div>
			</a>
		</#list>
		</#if>
		</dd>
	</dl>
	<#if rent_car_page??>
        <#assign continueEnter=false>
        <#if rent_car_page.totalPages gt 0>
           	<ul class="mymember_page">
            <#list 1..rent_car_page.totalPages as page>
                <#if page <= 3 || (rent_car_page.totalPages-page) < 3 || (rent_car_page.number+1-page)?abs<3 >
                    <#if page == rent_car_page.number+1>
                        <dd>
                            <a class="mysel" href="javascript:;">${page}</a>
                        </dd>
                    <#else>
                        <dd>
                            <a href="/rent/car2?page=${page-1}">${page}</a>
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


<!--选择取车还车地点的隐藏div-->
<div id="jsContainer" class="jsContainer" style="height:0">
	<div id="tuna_alert" style="position:absolute;z-index:999;overflow:hidden;"></div>
	<div id="tuna_jmpinfo" style="visibility:hidden;position:absolute;z-index:120;"></div>
</div>

<script type="text/javascript" src="js/fixdiv.js"></script>
<script type="text/javascript" src="js/address.js"></script>

<!--选择取车还车地点的隐藏div-->

<!-- ********************中间内容-结束******************** -->
<#include "/client/common_footer.ftl" />
</body>
</html>