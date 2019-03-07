<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>提交订单（直通车）</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<link rel="stylesheet" type="text/css" href="/client/css/rich_css.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/client/js/layout.js"></script>

<script type="text/javascript">
	window.onload = function(){
		banner_go('my');//焦点图
		become_big('act_main li a',30);//图片发放大
		box_out();//图片滑出
		act_nav();
	};
	
	$(function(){
		//初始化表单验证
		$("#form11").Validform({
	        tiptype: 3
	    });
		
	});
	
	var price = ${goods.salePrice?string("0.00")};
	price = parseFloat(price);
	price = price.toFixed(2);
	
	function change1(){
		var ertongshu = $("#ertongshu").val();
		var ertongjia = $("#ertongjia").text();
		var zongjia = $("#1111").text();
		zongjia = parseFloat(ertongshu)*parseFloat(ertongjia);
		var iNum = 0;
		
		var couponFee = parseFloat($("#couponSelect option:selected").attr("fee"));
	    
	    if (undefined == couponFee)
	    {
	        couponFee = 0.00;
	    }
		var couponFeeee = parseFloat(couponFee);
		
		$(".111").each(function(index,obj){
			if($(obj).prop("checked")){
				iNum++;
			}
		})
		$("#chengrenshu").val(iNum);
		var totalPrice=iNum*price+zongjia-couponFeeee;
		totalPrice = parseFloat(totalPrice);
		totalPrice = totalPrice.toFixed(2);
		if(Number(totalPrice) < 0){
			$("#1111").html(0);
			$("#totalPrice").val(totalPrice);
			return;
		}
		$("#1111").html(totalPrice);
		$("#totalPrice").val(totalPrice);
	}
	
	
	var a=0;
	var code11=0;
	function couponChange()
	{	
	    var couponFee = parseFloat($("#couponSelect option:selected").attr("fee"));
	    couponFee = Math.round(couponFee*100)/100;
	    if (undefined == couponFee)
	    {
	        couponFee = 0.00;
	    }
		var couponFeeee = parseFloat(couponFee);
		couponFeeee = Math.round(couponFeeee*100)/100;
		var price111 = $("#1111").html();
		var price1111 = parseFloat(price111);
		price1111 = Math.round(price1111*100)/100;
		$("#couponFeee").html(couponFeeee);
		
		var chengrenshu = $("#chengrenshu").val();
		var chengrenjia = ${goods.salePrice?string("0.00")};
		chengrenjia = parseFloat(chengrenjia);
		chengrenjia = Math.round(chengrenjia*100)/100;
		
		var ertongshu = $("#ertongshu").val();
		var ertongjia = ${goods.salePrice1?string("0.00")};
		ertongjia = parseFloat(ertongjia);
		ertongjia = Math.round(ertongjia*100)/100;
		if(price1111 == 0){
			return;
		}
		
		if(couponFeeee == 0){
			var totalPrice000 = chengrenshu * chengrenjia + ertongshu * ertongjia;
			totalPrice000 = totalPrice000.toFixed(2);
			$("#1111").html(totalPrice000);  
		}else{
			var totalPrice000 = chengrenshu * chengrenjia + ertongshu * ertongjia - couponFeeee;
			totalPrice000 = totalPrice000.toFixed(2);
			$("#1111").html(totalPrice000); 
		}
		
	}
	
	
	function hanshu(){
		var ertongshu = $("#ertongshu").val();
		var ertongjia = $("#ertongjia").text();
		var chengrenshu = $("#chengrenshu").val();
		var chengrenjia = $("#chengrenjia").text();
		
		var zongjia = $("#1111").text();
		zongjia = parseFloat(ertongshu)*parseFloat(ertongjia) + parseFloat(chengrenshu)*parseFloat(chengrenjia);
		var iNum = 0;
		
		var couponFee = parseFloat($("#couponSelect option:selected").attr("fee"));
	    
	    if (undefined == couponFee)
	    {
	        couponFee = 0.00;
	    }
		var couponFeeee = parseFloat(couponFee);
		
		$(".111").each(function(index,obj){
			if($(obj).prop("checked")){
				iNum++;
			}
		})
		//$("#chengrenshu").val(iNum);
		//var totalPrice=iNum*price+zongjia-couponFeeee;
		var totalPrice=zongjia-couponFeeee;
		$("#1111").html(totalPrice);
		$("#totalPrice").val(totalPrice);
	}
	
</script>

<body>
<#include "/client/common_header.ftl" />


<div class="h20"></div>
<!-- ********************中间内容******************** -->
<form class="train_order" id="form11" action="/through/order/submit" method="post">
<#if goods??><input type="hidden" name="goodsId" value="${goods.id!c}"></#if>
<#if goods??><input type="hidden" name="goodsTitle" value="${goods.title!''}"></#if>
<#if goods??><input type="hidden" name="orderType" value="${goods.categoryTitle!''}"></#if>
<input type="hidden" name="totalPrice" id="totalPrice" value="">
	<!-- 订单信息 -->
	<dl class="dl1">
		<dt>订单信息：</dt>
		<dd class="dd1">
			<ul>
				<li class="li1"><p>${goods.title!''}</p></li>
				<li class="li2">
					<p class="p1">日期：</p>
					<p class="p2">${goods.onSaleTime?string("yyyy-MM-dd")}</p>
					<p class="p3">时间：</p>
					<p class="p4">${goods.onSaleTime?string("HH:mm")}</p>
				</li>
				<li class="li3">
					<p class="p1">乘车点：</p>
					<p class="p2">${goods.leavePort!''}</p>
					<p class="p3">到站：</p>
					<p class="p4">${goods.reachPort!''}</p>
				</li>
				<li class="li4">
					<p class="p1">票价：</p>
					<p class="p2">
						<label>&nbsp;&nbsp;成人￥<font id="chengrenjia">${goods.salePrice?string("0.00")}</font>元/张&nbsp;&nbsp;&nbsp;&nbsp;儿童￥<font id="ertongjia">${goods.salePrice1?string("0.00")}</font>元/张</label>   
					</p>
				</li>
			</ul>
		</dd>
		<dd class="dd2">
			<p>
				<label class="la1">选择人数：成人：<input type="number" onchange="hanshu();" onkeydown="return checkNumber(event);" min="0" id="chengrenshu" name="chengrenshu" style="width:35px" value="0">&nbsp;&nbsp;儿童：<input type="number" min="0" id="ertongshu" name="ertongshu" onchange="hanshu();" onkeydown="return checkNumber(event);" value="0" style="width:35px"></label>
			</p>
			<p>
				<font style="color:red;">注：1.2米以下算儿童，请在上面选择你所携带的儿童数量。</font>
			</p>
			<p>
			</p>
			<p class="p4"><label>总计：<span id="1111">0.00</span>元</label></p>
			<hr/><br/>
			<div class="car_pay">
	            <span class="mr10 inblock" style="width:100px;">优惠券</span>           
		            <select id="couponSelect" name="couponId" onchange="couponChange();">
		                <#if coupon_list??>
		                	<option value="" fee="0">不使用优惠券</option>
		                    <#list coupon_list as item>
		                        <option value="${item.id?c}" fee="${item.price!''}">${item.typeTitle!''}</option>
		                    </#list>
		                </#if>
		            </select>&nbsp;&nbsp;
	            <span class="red">抵用金额：￥<b id="couponFeee" style="color:red">0.00</b></span>
        	</div>
		</dd>
	</dl>
	<!-- 订单信息-结束 -->

	<div class="h20"></div>
	<!-- 旅客身份信息 -->
	<div class="traveler_infor">
		<div class="tra_title">旅客身份信息</div>
		<dl>
			<dt>
				<div class="div1">选择</div>
				<div class="div2">姓名</div>
				<div class="div3">性别</div>
				<div class="div4">证件类型</div>
				<div class="div5">证件号码</div>
				<div class="div6">手机号码</div>
			</dt>
			<dd>
				<ul>
					<#if visitor_page??>
					<#list visitor_page.content as item>
						<li>
							<div class="div1"><input type="checkbox" name="listChkId" class="111" onclick="change1();" value="${item_index}">
												<input type="hidden" name="listId" id="listId" value="${item.id?c}">
							</div>
							<div class="div2">${item.visitorName!''}</div>
							<div class="div3">${item.sex!''}</div>
							<div class="div4">${item.certificateType!''}</div>
							<div class="div5">${item.certificateCardCode!''}</div>
							<div class="div6">${item.telephone!''}</div>
						</li>
					</#list>
					</#if>
				</ul>
			</dd>
		</dl>
		<a class="add_traveler" href="/user/visitor" id="addVisitor">+添加游客信息</a>
	</div>
	<!-- 旅客身份信息-结束 -->

	<div class="h20"></div>
	<!-- 联系信息 -->
	<dl class="contact_infor">
		<dt>联系信息：</dt>
		<dd>
			<div class="div1">
				<label>姓名:</label>
				<input type="text" name="shippingName" datatype="s2-20" errormsg="亲，请输入一个以上的字段" nullmsg="请填写信息！">
			</div>
			<div class="div2">
				<label>手机号:</label>
				<input type="text" name="shippingPhone" datatype="m" errormsg="亲，请填写11位正确的手机号码！">
			</div>
			<div class="div3">
				<label>邮箱:</label>
				<input type="text" name="leavePort" datatype="e" errormsg="亲，邮箱格式不正确哟！">
			</div>
		</dd>
	</dl>
	<!-- 联系信息-结束 -->
	<div class="h50"></div>
	<div class="submits">
		<input type="submit" value="提交并支付">
	</div>
</form>
<div class="h50"></div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

</body>
</html>