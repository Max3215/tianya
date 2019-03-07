<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>客车信息</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/mag/js/WdatePicker.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/client/js/layout.js"></script>
<body>
<script type="text/javascript">
$(document).ready(function(){
	//初始化表单验证
	$("#form11").Validform({
        tiptype: 3
    });
});
function checkAndSubmit(){

	var qcdd = $("#qc").val();
	var hcdd = $("#hc").val();
	$("#qcdd").val(qcdd);
	$("#hcdd").val(hcdd);
	
	// 电话号码
	var pattern_phone = /^1[34578]\d{9}$/;
	// 电子邮箱
	var pattern_email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
	var d = $("#days").val();
	var cn = $("#clientName").val();
	var m = $("#mobile").val(); 
	var e = $("#email").val();
	
	var qcdz = $("#qcdz").val();
	var hcdz = $("#hcdz").val();
	if(d==""){
		alert("请输入天数");
		return;
	}else if(cn == ""){
		alert("请输入姓名");
		return;
	}else if(m == ""){
		alert("请输入手机号");
		return;
	}else if(!pattern_phone.test(m)){
		alert("手机号码格式错误");
		return;
	}else if(qcdz == ""){
		alert("取车地址不能为空！");
		return;
	}else if(hcdz == ""){
		alert("还车地址不能为空！");
		return;
	}else if(e != "" && e != "请输入邮箱"){
		//alert(e);
		if(!pattern_email.test(e)){
			alert("邮箱格式错误");
			return;		
		}
	}
	document.getElementById('form11').submit();
}

</script>


<#include "/client/common_header.ftl" />

<div class="h20"></div>
<!-- ********************中间内容******************** -->
<div class="classes_infor">
	<dl class="dl1">
		<dt>取车信息：</dt>
		<dd class="dd1">
			<img src="${goods.coverImageUri!''}">
			<div>
				<p class="p1">${goods.title!''}</p>
				<p>取车地区：
					<select id="qc">
						<option value="渝中区">渝中区</option>
						<option value="大渡口区">大渡口区</option>
						<option value="江北区">江北区</option>
						<option value="南岸区">南岸区</option>
						<option value="沙坪坝区">沙坪坝区</option>
						<option value="九龙坡区">九龙坡区</option>
						<option value="北碚区">北碚区</option>
						<option value="渝北区">渝北区</option>
						<option value="巴南区">巴南区</option>
					</select>
				</p>
				<p>还车地区：
					<select id="hc">
						<option value="渝中区">渝中区</option>
						<option value="大渡口区">大渡口区</option>
						<option value="江北区">江北区</option>
						<option value="南岸区">南岸区</option>
						<option value="沙坪坝区">沙坪坝区</option>
						<option value="九龙坡区">九龙坡区</option>
						<option value="北碚区">北碚区</option>
						<option value="渝北区">渝北区</option>
						<option value="巴南区">巴南区</option>
					</select>
				</p>
				<#--<p>取车地点：${goods.leavePort!''}</p>-->
				<#--<p>还车地点：${goods.reachPort!''}</p>-->
			</div>
		</dd>
		<dd class="dd2">
			<form class="bdiv" id="form11" action="/rent/order1" method="get">
			<input type="text" id="qcdd" name="qcdd" value="" hidden="hidden">
			<input type="text" id="hcdd" name="hcdd" value="" hidden="hidden">
			<#if goods??><input type="hidden" id="goodsId11" name="goodsId" value="${goods.id!c}"></#if>
				<div class="bdiv_left">
					<p class="p1">价格(面议)：￥<span>${goods.salePrice?string("0.00")}</span>/天起</p>
					<p>您需要租几天：<input type="text" id="days" name="days" datatype="n" placeholder="点击输入（整数）" onkeydown="return checkNumber(event);" style="width:150px"><span class="Validform_checktip" style="color: red;">*</span></p>
					<p>输入您的姓名：<input type="text" id="clientName" name="clientName" datatype="s1-20" placeholder="请输入姓名" style="width:150px"><span class="Validform_checktip" style="color: red;">*</span></p>
					<p>输入您的电话：<input type="text" id="mobile" name="mobile" datatype="m" placeholder="请输入电话" style="width:150px"><span class="Validform_checktip" style="color: red;">*</span></p>
					<p>输入您的邮箱：<input type="text" id="email" name="email" <#--datatype="e"--> placeholder="请输入邮箱" style="width:150px"><span class="Validform_checktip" style="color: red;"></span></p>
					<p>取车地址：<input type="text" id="qcdz" name="qcdz" datatype="*" placeholder="请输入取车地址" style="width:250px"><span class="Validform_checktip" style="color: red;">*</span></p>
					<p>还车地址：<input type="text" id="hcdz" name="hcdz" datatype="*" placeholder="请输入还车地址" style="width:250px"><span class="Validform_checktip" style="color: red;">*</span></p>
				</div>
				<div class="bdiv_right"><a href="javascript:void(0);" name="submit" onclick="checkAndSubmit()">立&nbsp;即提&nbsp;交</a></div>
				<#--<div ><input type="submit" class="bdiv_right"  value="立&nbsp;即提&nbsp;交"></div>-->
			</form>
		</dd>
	</dl>
	<div class="h20"></div>
	<dl class="dl2">
		<dt>汽车信息：</dt>
		<dd>
			<img src="${goods.coverImageUri!''}">
			<div>
				<p><b>汽车公司：</b>${goods.shipCompany!''}</p>
				<p><b>汽车型号：</b>${goods.subTitle!''}</p>
				<p><b>车辆描述：</b>${goods.shipDescription!''}</p>
			</div>
		</dd>
	</dl>
</div>
<div class="h50"></div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

</body>
</html>