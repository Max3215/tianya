<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>注册</title>

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
	//初始化表单验证
	$("#form1").Validform({
        tiptype: 3
    });
    
    $("#isCheck").change(function(){
        var check = document.getElementById("isCheck");
        if(check.checked){
            $("#btn_reg").removeAttr("disabled");
            $("#btn_reg").css("background","#182742");
        }else{
            $("#btn_reg").attr("disabled","true");
            $("#btn_reg").css("background","#999999");
        }
     });
    
    
});

                       // 弹出窗口
function checkwindowshow()
{
    
    $("#tanchuang").css("display", "block");
    $("#tanchuangbackgroud").addClass("thickdiv"); 
    
    //$('html,body').animate({scrollTop:0},500);
}

// 窗口隐藏
function checkwindowhide()
{

    $("#tanchuang").css("display", "none");
    $("#tanchuangbackgroud").removeClass("thickdiv"); 
    
}
</script>
<body>

<iframe class="thickframe" id="" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" style="display:none"></iframe>
<div  id="tanchuangbackgroud"></div>
<div class="thickbox" id="tanchuang" style="width: 1056px; height: 826px; left: 22%; top: 67px; display:none">
    <div class="thicktitle" id="" style="width:922"><span>天涯国旅用户注册协议</span></div>
    <div class="thickcon" id="" style="width: 1054px; height: 825px; padding-left: 0px; padding-right: 0px; border-left-width: 1px; border-right-width: 1px;">
        <div class=" regist-2013">
            <div class="regist-bor">
                <div class="mc">
                    <div id="protocol-con">
                        <#if setting??>${setting.registerNego!''}</#if>
                    </div>
                    <div class="btnt">
                        <input class="btn-img" type="submit" value="同意并继续" onclick="checkwindowhide();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a href="javascript:checkwindowhide();" id="closeBox" class="thickclose">×</a></div>


    <div class="w1065">
    <div class="denglutop">
<style>
.thickbox {
    position: absolute;
    z-index: 10000002;
    overflow: hidden;
    padding: 0;
    border: 4px solid rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
}
.thicktitle {
    height: 27px;
    padding: 0 10px;
    border: solid #C4C4C4;
    border-width: 1px 1px 0;
    background: #F3F3F3;
    line-height: 27px;
    font-family: arial, "\5b8b\4f53";
    font-size: 14px;
    font-weight: bold;
    color: #333;
}
.thickcon {
    overflow: auto;
    background: #fff;
    border: solid #C4C4C4;
    border-width: 1px;
    padding: 10px;
}
.regist-2013 .btnt .btn-img {
    width: 322px;
    height: 34px;
    line-heiht: 34px;
    background: #e4393c;
    color: #FFF;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    font-family: "微软雅黑";
    font-size: 16px;
    font-weight: 800;
}
.regist-2013 .btnt {
    width: 322px;
    margin: 20px auto 0;
}
.thickclose:link, .thickclose:visited {
    display: block;
    position: absolute;
    z-index: 100000;
    top: 7px;
    right: 12px;
    overflow: hidden;
    width: 15px;
    height: 15px;
    background: url(/client/images/bg_thickbox.gif) no-repeat 0 -18px;
    font-size: 0;
    line-height: 100px;
}
#protocol-con {
    height: 702px;
    overflow: auto;
    padding: 10px 20px 0 10px;
}
.btn-img {
    cursor: pointer;
    overflow: hidden;
    margin: 0;
    padding: 0;
    border: 0;
    text-align: center;
}
.thickframe {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10000000;
    width: 100%;
    height: 100%;
    background: #000;
    border: 0;
    filter: alpha(opacity = 0);
    opacity: 0;
}
.thickdiv {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10000001;
    width: 100%;
    height: 100%;
    background: #000;
    border: 0;
    filter: alpha(opacity = 15);
    opacity: .15;
}
</style>
    


<#include "/client/common_header.ftl" />
<div class="content_bg">
	<div class="h100"></div>
	<div class="register">
		<div class="c_left">
			<h1>会员注册：</h1>
			<p class="p1">已有账号？立即 <a href="/login">登录</a></p>
			<div class="h260"></div>
			<p class="p2"><a href="javascript:history.go(-1);">返回上一页</a> <a href="/">首页</a></p>
		</div>
		
		<form class="c_right" id="form1" action="/reg" method="POST">
			<div class="div1">
				<label>会员名：</label>
				<#--<input class="text" type="text" name="username" datatype="s6-20" ajaxurl="/reg/check/username" />-->
				<input class="text" type="text" name="username" datatype="/^[0-9a-zA-Z_]{1,}$/i" ajaxurl="/reg/check/username" errormsg="不能包含中文"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div2">
				<label>密 码：</label>
				<input class="text" type="password" name="password" datatype="/\w{5,17}$/i" errormsg="不包含空格"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div3">
				<label>确认密码：</label>
				<input class="text" type="password" name="password2" datatype="/\w{5,17}$/i" recheck="password"/>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div4">
				<label>手机：</label>
				<input class="text" type="text" name="mobile" datatype="m" ajaxurl="/reg/check/mobile" />
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div5">
				<label>邮箱：</label>
				<input type="text" name="email"  />
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div6">
				<label>验证码：</label>
				<input name="code" type="text" style="width:20%;" datatype="*" ajaxurl="/reg/check/code"/>
				<span class="Validform_checktip" style="color: red;"></span>
                <img src="/code" onclick="this.src = '/code?date='+Math.random();" id="yzm" height="37" style="padding-left: 0px;"/>
			</div>
			<div class="div7">
				<input type="submit" value="注 册" id="btn_reg">
			</div>
			<div class="div8">
				<input type="checkbox" name="checkbox" id="isCheck" checked="checked"/>
				我已阅读并同意<a href="javascript:checkwindowshow();">《天涯国旅用户注册使用协议》</a>
			</div>
		</form>
	</div>
</div>
<#include "/client/common_footer.ftl" />
</body>

</html>