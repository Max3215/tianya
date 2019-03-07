<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>我的积分</title>
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
<div class="percenter_com my_score">
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right" style="float:right;margin-left:0;">
		<div class="right_title">我的积分</div>
		<div class="h20"></div>
		<div class="now_score">
			<label>当前积分：<span><#if total??>${total!''}</#if></span></label>
			<a href="/user/point/rule" target="_blank">积分规则</a>
		</div>
		<dl class="coupon">
			<dt>
				<div class="d1">来源/用途</div>
				<div class="d2">积分变化</div>
				<div class="d3">日期</div>
				<div class="d4">备注</div>
			</dt>
			<#if point_page??>
				<#list point_page.content as point>
					<dd class="coudd">
						<ul>
							<li>
								<div class="d1">${point.detail!''}</div>
								<div class="d2">${point.point!c}</div>
								<div class="d3">
									<label>${point.pointTime!''}</label>
									<!--<label>19:20</label>-->
								</div>
								<div class="d4">${point.remark!''}</div>
							</li>
						</ul>
					</dd>
				</#list>
			</#if>
		</dl>
		
                <#if point_page??>
                <br>
                <#assign continueEnter=false>
                <#if point_page.totalPages gt 0>
                <ul class="mymember_page">
                    <#list 1..point_page.totalPages as page>
                        <#if page <= 3 || (point_page.totalPages-page) < 3 || (point_page.number+1-page)?abs<3 >
                            <#if page == point_page.number+1>
                            	<li>
                                	<a class="mysel" href="javascript:;">${page}</a>
                            	</li>
                            <#else>
	                            <li>
	                                <a href="/user/point/list?page=${page-1}">${page}</a>
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