<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>修改资料</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/mag/js/WdatePicker.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	//初始化表单验证
	$("#form1").Validform({
        tiptype: 3
    });
});

</script>

<body>

<#include "/client/common_header.ftl" />


<!-- ********************中间内容******************** -->
<div class="percenter_com rewrite_history">
	
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<dl class="body_right">
		<dt>绑定手机号码</dt>
		<dd>
			<form id="form1" action="/user/information/save" method="post">
				<div class="div1">
					<img src="<#if user??>${user.headImageUri!'/client/images/defaultavartar.jpg'}</#if>">
					<p><a href="/user/header/img">编辑头像</a></p>
				</div>
				<div class="div2">
					<label>用户名：</label>
					<span><#if user??>${user.username!''}</#if></span>
					<span>（注册时间：<#if user??>${user.registerTime!''}</#if>）</span>
				</div>
				<div class="div3">
					<label>真实姓名：</label>
					<input type="text" name="realName" <#if user??>value="${user.realName!''}"</#if>/>
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>性别：
				<input type="radio" name="sex" <#if user?? && user.sex?? && user.sex=="男">checked</#if> value="男">男</input>
				<input type="radio" name="sex" <#if user?? && user.sex?? && user.sex=="女">checked</#if> value="女">女</input>
				<div>
					<label>出生日期：</label>
					<input type="text" name="birthday" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" <#if user.birthday??>value="${user.birthday?string("yyyy-MM-dd")}"</#if> readonly="true"/>
				</div>
				<div class="div4">
					<label>QQ号：</label>
					<input type="text" name="qq" datatype="n3-20" errormsg="请您填写正确的QQ号码！" <#if user??>value="${user.qq!''}"</#if>/>
					<span class="Validform_checktip" style="color: red;"></span>
				</div>
				<input type="submit" value="保存" class="save">
			</form>
		</dd>
	</dl>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>