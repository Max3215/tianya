<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>用户注册协议</title>
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
<div class="percenter_com">
	
	<!-- **********中间-右边********** -->
	<div class="frame_right" style="width:1170px;margin-top: 70px;">
		<#if article??>
				${article.registerNego!''}
		</#if>
		<hr>
		<div class="c_right" style="float:right">
			<p class="p2" ><a href="javascript:history.go(-1);" style="color:blue">返回注册</a></p>
		</div>
	</div>
	
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>


<#include "/client/common_footer.ftl" />
</body>
</html>