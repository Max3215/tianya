<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>我的收藏</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
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
	.mymember_page li{
		float:left;
	    width: 30px;
	    height: 30px;
	    line-height: 30px;
	    margin-left:10px;
	    text-align: center;
	    border: 1px solid #033a7a;
	}
	.body_right .div2 {
	width:33.3% !important;}

</style>

<#include "/client/common_header.ftl" />

<!-- ********************中间内容******************** -->
<div class="percenter_com my_colection">
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />

	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">客车包租</div>
		<dl>
			<dt>
				<div class="div2">商品总名称</div>
				<div class="div2">租几天</div>
				<div class="div2">价格(可面议)</div>
			</dt>
			<dd>
			<ul>
				<#if user_rentcar2_page??>
					<#list user_rentcar2_page.content as rentcar2>
						<li>
							<div class="div2">${rentcar2.goodsTitle!''}</div>
							<div class="div2">${rentcar2.shopId!''}</div>
							<div class="div2">${rentcar2.totalPrice?string("0.00")!''}元/天起</div>
						</li>
					</#list>
				</#if>
			</ul>
			</dd>
		</dl>
		
                <#if user_rentcar2_page??>
                <#assign continueEnter=false>
                <#if user_rentcar2_page.totalPages gt 0>
                <ul class="mymember_page">
                    <#list 1..user_rentcar2_page.totalPages as page>
                        <#if page <= 3 || (user_rentcar2_page.totalPages-page) < 3 || (user_rentcar2_page.number+1-page)?abs<3 >
                            <#if page == user_rentcar2_page.number+1>
	                            <li>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </li>
                            <#else>
	                            <li>
	                                <a href="/user/rentcar2/list?page=${page-1}">${page}</a>
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
        
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>