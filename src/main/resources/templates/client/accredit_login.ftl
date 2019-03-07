<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><#if setting??>${setting.seoTitle!''}-</#if>账号绑定</title>
    <meta name="keywords" content="<#if setting??>${setting.seoKeywords!''}</#if>">
    <meta name="description" content="<#if setting??>${setting.seoDescription!''}</#if>">
    <meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/add_page.css">

<link href="/client/style/style.css" rel="stylesheet" type="text/css" />

<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    //初始化表单验证
    $("#form").Validform({
        tiptype: 4,
        ajaxPost:true,
        callback:function(data){
            alert(data.msg);
            if(data.code==1)
            {
                window.location.href="/";
            }
        }
    });
    
     $("#form1").Validform({
        tiptype: 4
    });
 })
</script>
<body>
<!-- ********************头部******************** -->
	<#include "/client/common_header.ftl" />
<!-- ********************导航-结束******************** -->


<!-- ********************中间内容******************** -->
<div class="bing_sign">
	<div class="divv1">您将使用账号<span><#if qqName??>${qqName!''}</#if></span>登录本站</div>
	<div class="divv2">
		<form class="bing_form bing_sleft" action="/accred/user" method="post" id="form">
    	   <input type="hidden" value="<#if openId??>${openId}</#if>" name="openId">
    	   <input type="hidden" value="<#if type??>${type}</#if>" name="type">
			<div class="mms">绑定已有账号：</div>
			<div class="">
				<label>用户名：</label>
				<input type="text" name="username" />
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="">
				<label>密码：</label>
				<input type="password"name="password" />
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="kk"><input type="submit" class="bingok" value="立即绑定" /></div>
		</form>
		<form class="bing_form bing_sright" method="post" action="/accred/reg" id="form1">
		    <input type="hidden" value="<#if openId??>${openId}</#if>" name="openId">
		    <input type="hidden" value="<#if type??>${type}</#if>" name="type"> 
			<div class="mms">没有账号？立即注册：</div>
			<div class="reg">
				<label>用户名：</label>
				<input type="text" name="username" datatype="/^[0-9a-zA-Z_]{1,}$/i" ajaxurl="/reg/check/username" errormsg="不能包含中文"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="reg">
				<label>密码：</label>
				<input type="password" name="password" datatype="/\w{5,17}$/i" errormsg="不包含空格"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="reg">
				<label>确认密码：</label>
				<input type="password" datatype="/\w{5,17}$/i" recheck="password"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="reg">
				<label>手机：</label>
				<input type="text" datatype="m" name="mobile" ajaxurl="/reg/check/mobile"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="reg">
				<label>邮箱：</label>
				<input type="text" datatype="e" name="email"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="kk"><input type="submit" class="bingok" value="立即注册并绑定" /></div>
		</form>
	</div>
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<!-- ********************底部******************** -->
<#include "/client/common_footer.ftl" />
<!-- ********************底部-结束******************** -->
</body>
</html>