<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>我的优惠券</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>

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


<!-- ********************中间内容******************** -->
<div class="percenter_com my_coupon">
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">我的优惠券</div>
		<dl class="coupon">
			<dt>
				<div class="d1">优惠券</div>
				<div class="d2">使用条件</div>
				<div class="d3">操作</div>
			</dt>
			<dd class="coudd">
				<ul>
					<#if coupan_page??>
						<#list coupan_page.content as coupon>
							<li>
								<div class="d1"><img src="${coupon.typePicUri!''}"></div>
								<div class="d2">${coupon.typeDescription!''}</div>
								<div class="d3"><a href="/user/coupon/delete?id=${coupon.id!c}">删除</a></div>
							</li>
						</#list>
					</#if>
				</ul>
			</dd>
			
                <#if coupan_page??>
                <br>
                <#assign continueEnter=false>
                <#if coupan_page.totalPages gt 0>
					<ul class="mymember_page">
                    <#list 1..coupan_page.totalPages as page>
                        <#if page <= 3 || (coupan_page.totalPages-page) < 3 || (coupan_page.number+1-page)?abs<3 >
                            <#if page == coupan_page.number+1>
	                            <li>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </li>
                            <#else>
	                            <li>
	                                <a href="/user/coupon/list?page=${page-1}">${page}</a>
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
        
		</dl>
		<div class="prompt"><i>*</i> 优惠券不可提现，不可转移给他人，最终解释权归天涯国旅所有</div>
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>