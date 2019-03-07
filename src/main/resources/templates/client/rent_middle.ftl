<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>汽车信息</title>
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
<script type="text/javascript" src="/client/js/layout.js"></script>
<body>
<script type="text/javascript">
// 日期格式为yyyy-MM-dd的字符串
function ymdCompare(date1, date2){
	var ymd1 = date1.replace(/-/g, "");
	var ymd2 = date2.replace(/-/g, "");
	if(parseInt(ymd1) - parseInt(ymd2) > 0){
		return 1;
	}else if(parseInt(ymd1) - parseInt(ymd2) < 0){
		return -1;
	}else{
		return 0;
	} 
}

function submit123(){
	var qcdd = $("#qc").val();
	var hcdd = $("#hc").val();
	$("#qcdd").val(qcdd);
	$("#hcdd").val(hcdd);
	
	

	var zhengshu = $("#123").val();
	var riqi = $("#456").val();
	//alert(riqi);
	var riqi2 = (riqi.split(" "))[0];
	//alert("riqi2 = " + riqi2);
	var c_to_num = 0;
	var c_arrary = riqi2.split("-");
	for(var i=0; i < c_arrary.length; i ++){
		c_to_num = c_to_num + parseInt(c_arrary[i]);
	}
	var now_date = new Date();
	var year = now_date.getFullYear();
	//alert(year);
	var month = now_date.getMonth() + 1;
	//alert(month);
	if(month < 10){
		month = "0" + month;
	}
	var day = now_date.getDate();
	//alert(day);
	if(day < 10){
		day = "0" + day;
	}
	var ymd = year + "-" + month + "-" + day;
	
	var partern_zhengshu= /^[0-9]*[1-9][0-9]*$/;
	
	var qcdz = $("#qcdz").val();
	var hcdz = $("#hcdz").val();
	
	if(partern_zhengshu.test(zhengshu)){
		if(zhengshu==""||riqi==""){
			alert("请选择天数和取车时间！");
			return;
		}
		if(ymdCompare(riqi, ymd) < 0){
			alert("请选择未来时间！");
			return;
		}
		if(qcdz == ""){
			alert("取车地址不能为空！");
			return;
		}
		if(hcdz == ""){
			alert("还车地址不能为空！");
			return;
		}
		
		document.getElementById('form11').submit();
		
	}else{
		alert("天数必须为整数！");
	}
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
				<#--<p>取车时间：<#if goods.groupSaleStartTime??>${goods.groupSaleStartTime?string("yyyy-MM-dd HH:mm")}</#if></p>-->
			</div>
		</dd>
		<dd class="dd2">
			<form class="bdiv" id="form11" action="/rent/order" method="get">
			<input type="text" id="qcdd" name="qcdd" value="" hidden="hidden">
			<input type="text" id="hcdd" name="hcdd" value="" hidden="hidden">
			<#if goods??><input type="hidden" name="goodsId" value="${goods.id!c}"></#if>
				<div class="bdiv_left">
					<p class="p1">价格：￥<span>${goods.salePrice?string("0.00")}</span>/天</p>
					<p>你需要租几天：<input type="text" id="123" name="days" onkeydown="return checkNumber(event);" placeholder="点击输入（整数）" style="width:150px">天<span style="color: red;">*</span></p>
					<p>选择取车时间：<input type="text" id="456" name="groupSaleStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" readonly="true" placeholder="点击选择时间" style="width:150px"><span style="color: red;">*</span></p>
					<p>取车地址：<input type="text" id="qcdz" name="qcdz"  placeholder="请输入取车详细地址" style="width:250px"><span style="color: red;">*</span></p>
					<p>还车地址：<input type="text" id="hcdz" name="hcdz"  placeholder="请输入还车详细地址" style="width:250px"><span style="color: red;">*</span></p>
				</div>
				<div class="bdiv_right"><a href="javascript:void(0);" name="submit" onclick="submit123();">立&nbsp;即订&nbsp;购</a></div>
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