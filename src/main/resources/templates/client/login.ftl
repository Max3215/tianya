<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>登录</title>
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
<script type="text/javascript" src="/client/js/jquery.cookie.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	var cd = $("#cd").val();
	
	$("#form1").keydown(function(event){
		if(event.keyCode ==13){
   	 		$("#button1").trigger("click");
  		}
	});
	
	//初始化表单验证
	$("#form1").Validform({
        tiptype: 3
    });
    $("#bbb").click(function(){
		$("#aaa").hide();
	});
	
	//记住密码
    //if ($.cookie("rmbUser") == "true") { 
    //    $("#rmbUser").attr("checked", true); 
    //    $("#username").val($.cookie("userName")); 
    //    $("#password").val($.cookie("passWord")); 
    //}
	
	$("#button1").click(function(){
		var username = $("#username").val();
        var password = $("#password").val();
        //saveUserInfo();

        var isremember = $("#isremember").val();
        
        if($("#isremember").prop("checked")){
        	$.ajax({
                type: "post",
                url: "/login",
                data: { "username": username, "password": password, "isremember": isremember },
                dataType: "json",
                success: function (data) { 
                <!-- 修改 -->
                   if (data.code == 0) {
                        var url = document.referrer;   
                        //alert(url);       
                        if(undefined==url || ""==url || url.indexOf("login")>-1 || url.indexOf("logout")>-1 || url.indexOf("reg")>-1 || url.indexOf("password")>-1){
                            window.location.href="/user/0";
                        }else{
                            window.location.href = url;
                        }
                    } else {
                        alert(data.msg);
                        
                    }
                }
            });
        }else{
        	$.ajax({
                type: "post",
                url: "/login",
                data: { "username": username, "password": password},
                dataType: "json",
                success: function (data) { 
                <!-- 修改 -->
                   if (data.code == 0) {
                        var url = document.referrer;     
                        if(undefined==url || ""==url || url.indexOf("login")>-1 || url.indexOf("reg")>-1 || url.indexOf("password")>-1){
                        	//alert("恭喜您，登录成功！");
                            window.location.href="/user/0";
                        }else{
                        	//alert(url);
                       		//alert("恭喜您，登录成功！");
                       		//alert(cd);
                       		if(cd != ""){
                       			var ssss = url + "?customData=" + cd;
                       			//alert(ssss);
                       			window.location.href = url + "?customData=" + cd; 
                       		}else{
	                            window.location.href = url;                        		
                       		}
                        }
                    } else {
                        alert(data.msg);
                    }
                }
            });
        } 
		
	});
});

 //保存用户信息 
function saveUserInfo() { 
    if (document.getElementById("rmbUser").checked==true) { 
        var userName = $("#username").val(); 
        var passWord = $("#password").val(); 
        $.cookie("rmbUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie 
        $.cookie("userName", userName, { expires: 7 }); // 存储一个带7天期限的 cookie 
        $.cookie("passWord", passWord, { expires: 7 }); // 存储一个带7天期限的 cookie 
    } 
    else { 
        $.cookie("rmbUser", "false", { expires: -1 }); 
        $.cookie("userName", '', { expires: -1 }); 
        $.cookie("passWord", '', { expires: -1 }); 
    } 
}  


function showMsg()
{
    alert("功能开发调试中");
}
</script>
<body>

<#include "/client/common_header.ftl" />
<!-- ********************中间内容******************** -->
<div class="content_bg">
	<div class="h100"></div>
	<div class="signin">
		<div class="c_left">
			<h1>会员注册：</h1>
			<p class="p1">没有账号？立即 <a href="/reg">注册</a></p>
			<div class="h100"><p style="line-height: 24px;color: #333; margin-bottom: 10px;">使用第三方登录：</p>
				<a href="/qqLogin" title="" style="margin-right: 10px;"><img src="client/images/qqicon.png" alt=""/></a>
				<a href="/weixinLogin" title=""><img src="client/images/weiboicon.png" alt=""/></a>
			</div>
			<p class="p2"><a href="javascript:history.go(-1);">返回上一页</a> <a href="/">首页</a></p>
		</div>
		<form class="c_right" id="form1" action="/login">
		
			<div class="div1">
				<label>会员名：</label>
				<input class="text" type="text" name="username" id="username" datatype="s2-20" ajaxurl="/login/check/username" <#if username??>value="${username!''}"</#if>/>
				<span class="Validform_checktip"></span>
			</div>
            
			<div class="div2">
				<label>密 码：</label>
				<input class="text" type="password" id="password" name="password" datatype="s6-20" <#if password??>value="${password!''}"</#if>/>
				<span style="color: #f85b07;font-size:14px;"><#if msg??>${msg!''}</#if></span>
			</div>
			<div class="div3">
				<input type="button" id="button1" value="登 录">
			</div>
			<div class="div4">
				<input type="checkbox" id="isremember" name="isremember" value="yes">
				<span>记住密码</span>
				<a href="/user/forgetPassword" class="forget">忘记密码？</a>
			</div>
			<input type="text" id="cd" hidden="hidden" value="${customData!''}">
		</form>
	</div>
</div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

</body>
</html>
