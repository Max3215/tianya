<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>管理收货地址</title>
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

<#include "/client/common_header.ftl" />


<!-- ********************中间内容******************** -->
<div class="percenter_com conduct_adress">
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">管理收货地址</div>
		<div class="tolerant">
			<div>默认收货地址：</div>
			<p>
				<#if defaultAddress??>
					<label>${defaultAddress.detailAddress!''}</label>
					<a href="/user/address/edit?id=${defaultAddress.id?c}">修改</a>
					<a href="/user/address/delete?id=${defaultAddress.id?c}">删除</a>
				<#else>
					<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;暂无默认地址...</label>
				</#if>
			</p>
		</div>
		<div class="other_adress">
			<label style="float:left">其他收货地址：</label>
			<a style="float:right;font-size:14px;color:#ff9813;text-decoration:none" href="/user/save/address">+&nbsp;添加地址</a>
		</div>
		<ul>
			<#if address_list??>
				<#list address_list as address>
					<li>
						<label>${address.detailAddress!''}</label>
						<a href="/user/address/default?id=${address.id?c}">设为默认地址</a> 
						<a href="/user/address/edit?id=${address.id?c}">修改</a>
						<a href="/user/address/delete?id=${address.id?c}">删除</a> 
						
					</li>
				</#list>
			</#if>
		</ul>
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>