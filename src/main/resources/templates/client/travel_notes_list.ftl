<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>游记攻略</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>

<script type="text/javascript">
	window.onload = function(){
		banner_go('my');//焦点图
		become_big('act_main li a',30);//图片发放大
		box_out();//图片滑出
		act_nav();
		//默认展示旅行感受
		$(".listPage1").show();
		$(".listPage2").hide();
		$(".listPage3").hide();
	};
	
	function soushuo(){
		var keyword = $("#4444").val();
		window.location.href="/user/travel/list?keyword="+keyword;
	}
	
	function tf(){
		//alert("tf");
		$(".listPage1").show();
		$(".listPage2").hide();
		$(".listPage3").hide();
		return;
	}
	function tp(){
		//alert("tp");
		$(".listPage2").show();
		$(".listPage1").hide();
		$(".listPage3").hide();
		return;
	}
	function tr(){
		//alert("tr");
		$(".listPage3").show();
		$(".listPage1").hide();
		$(".listPage2").hide();
	}
	
</script>

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
	
	.strategy .travels dt span {
	    width: 482px;
	    height: 1px;
	    display: inline-block;
	    overflow: hidden;
	    margin-top: 60px;
	    background-color: #274b99;
	}
	
	.strategy .trate .trate_tile .spl {
	    float: left;
	}

	.strategy .travels dt span .spl {
	    width: 482px;
	    height: 1px;
	    display: inline-block;
	    overflow: hidden;
	    margin-top: 60px;
	    background-color: #274b99;
	}
	
	.strategy .trate .trate_title .spr {
	    float: right;
	}
	.strategy .travels dt span .spr {
	    width: 482px;
	    height: 1px;
	    display: inline-block;
	    overflow: hidden;
	    margin-top: 60px;
	    background-color: #274b99;
	}
	
	.strategy .trate .trate_title span {
	    width: 335px;
	    height: 1px;
	    display: block;
	    overflow: hidden;
	    float: left;
	    margin-top: 20px;
	    background-color: #274b99;
	}

</style>

<body>

<#include "/client/common_header.ftl" />


<!-- ********************中间内容******************** -->
<div class="strategy" style="margin-top:50px;">
	<!-- 旅行攻略 -->
	<dl class="travels">
		<dt>
			<span class="sp1"></span>
			<a href="javascript:;">旅行攻略</a>
			<span class="sp2"></span>
		</dt>
		<#if travel_strategy_page??>
			<dd class="dd1">
				<#if travel_strategy_page.content[0]??>
					<div class="div1">
						<a href="/user/travel/search?id=${travel_strategy_page.content[0].id!c}"><img src="${travel_strategy_page.content[0].imgUrl!''}"></a>
						<p>${travel_strategy_page.content[0].title!''}</p>
					</div>
				</#if>
				<#if travel_strategy_page.content[1]??>
					<div class="div2">
						<a href="/user/travel/search?id=${travel_strategy_page.content[1].id!c}"><img src="${travel_strategy_page.content[1].imgUrl!''}"></a>
						<p>${travel_strategy_page.content[1].title!''}</p>
					</div>
				</#if>
			</dd>
			<dd class="dd2">
				<#if travel_strategy_page.content[2]??>
					<div class="div1">
						<a href="/user/travel/search?id=${travel_strategy_page.content[2].id!c}"><img src="${travel_strategy_page.content[2].imgUrl!''}"></a>
						<p>${travel_strategy_page.content[2].title!''}</p>
					</div>
				</#if>
				<#if travel_strategy_page.content[3]??>
					<div class="div2">
						<a href="/user/travel/search?id=${travel_strategy_page.content[3].id!c}"><img src="${travel_strategy_page.content[3].imgUrl!''}"></a>
						<p>${travel_strategy_page.content[3].title!''}</p>
					</div>
				</#if>
				<#if travel_strategy_page.content[4]??>
					<div class="div3">
						<a href="/user/travel/search?id=${travel_strategy_page.content[4].id!c}"><img src="${travel_strategy_page.content[4].imgUrl!''}"></a>
						<p>${travel_strategy_page.content[4].title!''}</p>
					</div>
				</#if>
			</dd>
		</#if>
	</dl>
	<!-- 游记-结束 -->

	<div class="h50"></div>
	<!-- 旅行感受 -->
	<div class="trate">
		<div class="trate_title">
			<span class="spl"></span>
			<a href="javascript:;" style="text-decoration:none;"><label id="travelFeeling" onmouseover="tf()">旅行感受</label>&nbsp;&nbsp;&nbsp;&nbsp;<label id="tavelPicture" onmouseover="tp()">旅行图片</label>&nbsp;&nbsp;&nbsp;&nbsp;<label id="travelRecommend" onmouseover="tr()">旅行推荐</label>&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<span class="spr"></span>
		</div>
		<div class="trate_search">
			<input type="text" id="4444" <#if keyword??>value="${keyword!''}"</#if>>
			<a href="javascript:;" onclick="soushuo();"><img src="/client/images/search1.png"></a>
		</div>
		<ul class="listPage1">
			<#if travel_feeling_page??>
			<#list travel_feeling_page.content as item>
				<li>
					<div class="trate_left"><a href="/user/travel/search?id=${item.id!0}"><img src="${item.imgUrl!''}"></a></div>
					<div class="trate_right">
						<div class="h30"></div>
						<p class="p1"><a href="/user/travel/search?id=${item.id!0}">${item.title!''}</a></p>
						<p class="p2">${item.username!''}</p>
						<p class="p3">
							${item.content!''}
						</p>
						<div class="see">
							<a href="javascript:;" class="a1"><img src="/client/images/ticon4.png"></a>
							<a href="/user/travel/search?id=${item.id!0}" class="a2"><img src="/client/images/ticon3.png"></a>
						</div>
					</div>
				</li>
			</#list>
			</#if>
		</ul>
		<!-- 旅行图片 -->
		<ul class="listPage2">
			<#if travel_picture_page??>
			<#list travel_picture_page.content as item>
				<li>
					<div class="trate_left"><a href="/user/travel/search?id=${item.id!0}"><img src="${item.imgUrl!''}"></a></div>
					<div class="trate_right">
						<div class="h30"></div>
						<p class="p1"><a href="/user/travel/search?id=${item.id!0}">${item.title!''}</a></p>
						<p class="p2">${item.username!''}</p>
						<p class="p3">
							${item.content!''}
						</p>
						<div class="see">
							<a href="javascript:;" class="a1"><img src="/client/images/ticon4.png"></a>
							<a href="/user/travel/search?id=${item.id!0}" class="a2"><img src="/client/images/ticon3.png"></a>
						</div>
					</div>
				</li>
			</#list>
			</#if>
		</ul>
		<!--旅行推荐-->
		<ul class="listPage3">
			<#if travel_recommend_page??>
			<#list travel_recommend_page.content as item>
				<li>
					<div class="trate_left"><a href="/user/travel/search?id=${item.id!0}"><img src="${item.imgUrl!''}"></a></div>
					<div class="trate_right">
						<div class="h30"></div>
						<p class="p1"><a href="/user/travel/search?id=${item.id!0}">${item.title!''}</a></p>
						<p class="p2">${item.username!''}</p>
						<p class="p3">
							${item.content!''}
						</p>
						<div class="see">
							<a href="javascript:;" class="a1"><img src="/client/images/ticon4.png"></a>
							<a href="/user/travel/search?id=${item.id!0}" class="a2"><img src="/client/images/ticon3.png"></a>
						</div>
					</div>
				</li>
			</#list>
			</#if>
		</ul>
	</div>
	<!-- 攻略-结束 -->


	<!-- 页码 -->
	<!--旅行感受-->
	<div class="page listPage1" >
		<#if travel_feeling_page??>
                <#assign continueEnter=false>
                <#if travel_feeling_page.totalPages gt 0>
                   	<ul class="mymember_page">
                    <#list 1..travel_feeling_page.totalPages as page>
                        <#if page <= 3 || (travel_feeling_page.totalPages-page) < 3 || (travel_feeling_page.number+1-page)?abs<3 >
                            <#if page == travel_feeling_page.number+1>
	                            <dd>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </dd>
                            <#else>
	                            <dd>
	                                <a href="/user/travel/list?page1=${page-1}&cate=0&keyword=${keyword!''}">${page}</a>
	                            </dd>
                            </#if>
                            <#assign continueEnter=false>
                        <#else>
                            <#if !continueEnter>
                                <b class="pn-break">…</b>
                                <#assign continueEnter=true>
                            </#if>
                        </#if>
                    </#list>
                   </ul>
                </#if>
             </#if>
		</div>
		<!--旅行图片-->
		<div class="page listPage2" >
		<#if travel_picture_page??>
                <#assign continueEnter=false>
                <#if travel_picture_page.totalPages gt 0>
                   	<ul class="mymember_page">
                    <#list 1..travel_picture_page.totalPages as page>
                        <#if page <= 3 || (travel_picture_page.totalPages-page) < 3 || (travel_picture_page.number+1-page)?abs<3 >
                            <#if page == travel_picture_page.number+1>
	                            <dd>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </dd>
                            <#else>
	                            <dd>
	                                <a href="/user/travel/list?page2=${page-1}&cate=2&keyword=${keyword!''}">${page}</a>
	                            </dd>
                            </#if>
                            <#assign continueEnter=false>
                        <#else>
                            <#if !continueEnter>
                                <b class="pn-break">…</b>
                                <#assign continueEnter=true>
                            </#if>
                        </#if>
                    </#list>
                   </ul>
                </#if>
             </#if>
		</div>
		<!--旅行推荐-->
		<div class="page listPage3" >
		<#if travel_recommend_page??>
                <#assign continueEnter=false>
                <#if travel_recommend_page.totalPages gt 0>
                   	<ul class="mymember_page">
                    <#list 1..travel_recommend_page.totalPages as page>
                        <#if page <= 3 || (travel_recommend_page.totalPages-page) < 3 || (travel_recommend_page.number+1-page)?abs<3 >
                            <#if page == travel_recommend_page.number+1>
	                            <dd>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </dd>
                            <#else>
	                            <dd>
	                                <a href="/user/travel/list?page3=${page-1}&cate=3&keyword=${keyword!''}">${page}</a>
	                            </dd>
                            </#if>
                            <#assign continueEnter=false>
                        <#else>
                            <#if !continueEnter>
                                <b class="pn-break">…</b>
                                <#assign continueEnter=true>
                            </#if>
                        </#if>
                    </#list>
                   </ul>
                </#if>
             </#if>
		</div>
		
	<!-- 页码-结束 -->
</div>
<div class="h50"></div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

</body>
</html>