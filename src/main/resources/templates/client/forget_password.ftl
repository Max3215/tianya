<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>找回密码</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<link rel="stylesheet" type="text/css" href="/client/css/add_page.css">
<link rel="stylesheet" type="text/css" href="/client/css/main.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/client/js/swipe.js"></script>

<script>
var seed=60;    //60秒  
var t1=null;
	$(document).ready(function(){
		//初始化表单验证
		$("#form1").Validform({
	        tiptype: 3,
	        ajaxPost:true,
	        callback:function(data){
	        	if(data.msg != undefined){
	        		alert(data.msg);
	        	}
	            if(data.code==1)
	            {
	                window.location.href="/user/resetPassword";
	            }
	        }
	    });
	});
	
	// 电话号码
	var pattern_phone = /^1[34578]\d{9}$/;
	
	function getMobileCode(){
		$("#mobileValideCode").val("");
		//获取手机号
		var m = $("#mobile").val();
		if(pattern_phone.test(m)){
			//获取用户名
			var n = $("#username").val();
			//获取手机验证码
			$.post("/user/getMobileValidCode", {username: n, mobile: m}, function(info){
				$("#error").text("");
				if(info.status=="no"){
					$("#error").text(info.data);
				}else{
					alert("获取手机验证码成功，请查看短信!");
				}
			});
		}
	}
	
	function getMobileCode2(){
		var mob = $("#mobile").val();
        
        var re = /^1\d{10}$/;
        if (!re.test(mob)) {
            alert("请输入正确的手机号");
            return;
        }
        
        //$("#smsCode").attr("disabled","disabled");
        $("#smsCode").attr("onclick","return false");
        
        
        $.ajax({
            url : "/user/check/mobileValidecode",
            async : true,
            type : "get",
            data : {"mobile":mob},
            success : function(data){
            console.debug(data)
                if(data.code == 1)
                {  
                    t1 = setInterval(tip, 1000);  
                }
                else
                {
                    //$("#smsCode").removeAttr("disabled");
                    $("#smsCode").attr("onclick", "getMobileCode2()");
                }
            },
            error : function(XMLHttpRequest, textStatus,  
                    errorThrown) {  
                alert("error");
            }
        });
    	
	}
	
	function enableBtn()
	{  
	    //$("#smsCode").removeAttr("disabled");   
	    $("#smsCode").attr("onclick", "getMobileCode2()");
	} 
	function tip() 
	{  
	    seed--;  
	    if (seed < 1) 
	    {  
	        enableBtn();  
	        seed = 60;  
	        $("#smsCode").text('获取激活码');  
	        var t2 = clearInterval(t1);  
	    } else {  
	        $("#smsCode").text(seed + "秒后重新获取");  
	    }  
	} 
	
	
	function checkMobileValidCode(){
		$("#error").text("");
		if($("#mobileValideCode").val()=="" || $("#mobileValideCode").val()==null){
			$("#error").text("请输入验证码");
			return false;
		}
		var mvc = $("#mobileValideCode").val();
		var s = false;	//验证码是否正确
		
		$.post("/user/check/mobileValidecode", {code: mvc}, function(backInfo){
			
			if(backInfo.status == "yes"){
				
				s = true;
			}else{
				$("#error").text(backInfo.info);
			}
		});
		return s;
	}
</script/>
<body>
<!-- ********************头部******************** -->
<#--
	<div class="top">
		<a href="#"><img src="images/logo.png"></a>
		<div class="top_right">
			<p class="trightp">
				<label class="dter"><a href="">注册</a></label> |
				<label><a href="#">登录</a></label>
			</p>
			<p>400-888-8888</p>
		</div>
	</div>
-->
<!-- ********************头部-结束******************** -->


<!-- ********************导航******************** -->
<#include "/client/common_header.ftl" />
<!-- ********************导航-结束******************** -->



<!-- ********************中间内容******************** -->
<div class="content_bg">
	<div class="h100"></div>
	<div class="find_password">
		<div class="c_left">
			<h1>找回密码：</h1>
			<div class="h260"></div>
			<p class="p2"><a href="javascript:history.go(-1);">返回</a>&nbsp;|<a href="/">首页</a></p>
		</div>
		<form class="c_right" id="form1" action="/user/resetPassword2" method="POST">
			<#--<div class="div1">
				<input type="radio" name="phone_or">
				<label>手机号验证</label>
				<input type="radio" name="phone_or">
				<label>邮箱验证</label>
			</div>-->
			<div class="div2">
				<label>会员名：</label>
				<input class="text" type="text" name="username" id="username" datatype="s2-20" ajaxurl="/login/check/username" <#if username??>value="${username!''}"</#if>/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div3">
				<label>绑定的手机号：</label>
				<input class="text" type="text" name="mobile" id="mobile" datatype="*" sucmsg=" "/>
				<span class="Validform_checktip" style="color: red;"></span>
				<a id="smsCode" style="width:150px;" onclick="getMobileCode2()">获取验证码</a>
			</div>
			<div class="div4">
				<label>验证码：</label>
				<input name="mobileValideCode" id="mobileValideCode" type="text" datatype="s4-4"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div5">
				<label>图形验证：</label>
				<input name="code" type="text" style="width:20%;" datatype="s4-4" ajaxurl="/user/check/code"/>
				<span class="Validform_checktip" style="color: red;"></span>
                <img src="/code/1" onclick="this.src = '/code/1?date='+Math.random();" id="yzm" height="37" style="padding-left: 0px;"/>
			</div>
			<div class="div6">
				<input type="submit" value="下一步">
			</div>
			<span id="error" style="color: red; float: right;" ></span>
		</form>
	</div>
</div>
<!-- ********************中间内容-结束******************** -->


<!-- ********************底部******************** -->

<!-- ********************底部-结束******************** -->

</body>
</html>