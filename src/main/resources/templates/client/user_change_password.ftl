<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>登录密码修改</title>
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

<script type="text/javascript">
$(document).ready(function(){
    
   $("#form1").Validform({
        tiptype: 4,
        ajaxPost:true,
        callback: function(data) {
            if (data.code==0)
            {
                alert("亲，修改成功，请您重新登录！");
                window.location.href="/user/password/success";
            }
            else
            {
                alert(data.msg);
                window.location.href="/login";
            }
        }
    });
});
</script>
<body>
<#include "/client/common_header.ftl" />

<!-- ********************中间内容******************** -->
<div class="percenter_com signin_fixpassword">
	<#include "/client/common_user_header.ftl" />
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">登录密码修改</div>
		<div class="h50"></div>
		<form id="form1" action="/user/password" method="post">
		<input name="__STATE" type="hidden" value="${user.password}"/>
			<div class="di1">
				<label>当前密码：</label>
				<input type="password" name="oldPassword" value="" datatype="*" errormsg="原始密码不正确" recheck="__STATE"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="di2">
				<label>新密码：</label>
				<input type="password" name="newPassword" value="" datatype="*6-18"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="di3">
				<label>确认密码：</label>
				<input type="password" value="" datatype="*" recheck="newPassword"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="di4">
				<label>验证码：</label>
				<input name="code" type="text" style="width:20%;" datatype="s4-4" ajaxurl="/user/check/code"/>
				<span class="Validform_checktip" style="color: red;"></span>
                <img src="/code/1" onclick="this.src = '/code/1?date='+Math.random();" id="yzm" height="37" style="padding-left: 0px;"/>
			</div>
			<div class="di5"><input type="submit" value="确认修改"></div>
		</form>
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>