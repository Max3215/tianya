<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>提交订单（租车）</title>
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
	    
	    //换车时间计算
		if(${days}!=null){
			var days=${days};
		}else{
			var days=0;
		}
		var sta_str = $("#shijian").text();
		sta_str1 = (sta_str).replace(/-/g,"/");
		var start_date = new Date(sta_str1);
		if(days>=1){start_date=new Date(start_date.getTime()+86400000*days);}   
   		var yyyy=start_date.getFullYear(),mm=(start_date.getMonth()+1).toString(),dd=start_date.getDate().toString(),hh= start_date.getHours().toString(),mi= start_date.getMinutes().toString(),se= start_date.getSeconds().toString();
  		if(mm.length==1){mm='0'+mm;}
  		if(dd.length==1){dd='0'+dd;}
  		if(hh.length==1){hh='0'+hh;}
  		if(mi.length==1){mi='0'+mi;}
  		if(se.length==1){se='0'+se;}
  		$("#jiesushijian").text(yyyy+'-'+mm+'-'+dd+' '+hh+':'+mi+':'+se);
		
	});
	
	
	
	
	$(function(){
		var salePrice1=${goods.salePrice?string("0.00")};
		salePrice1 = parseFloat(salePrice1);
		salePrice1 = Math.round(salePrice1*100)/100;
		var yajin=${goods.includePrice?string("0.00")};
		yajin = parseFloat(yajin);
		yajin = Math.round(yajin*100)/100;
		var days = ${days!0};
		days = parseFloat(days);
		days = Math.round(days*100)/100;
		var daijiaPrice = ${goods.outFactoryPrice?string("0.00")};
		
		$("#radio22").click(function(){
			var couponFee = parseFloat($("#couponSelect option:selected").attr("fee"));
	    	couponFee = Math.round(couponFee*100)/100;
		    if (undefined == couponFee)
		    {
		        couponFee = 0.00;
		    }
			var couponFeeee = parseFloat(couponFee);
			couponFeeee = Math.round(couponFeeee*100)/100;
			$("#radio22").each(function(index,obj){
				if($(obj).prop("checked")){
					$("#333").css("display","inline");
					var totalPrice123 = 0;
					if(couponFeeee ==0 ){
						totalPrice123 = days * salePrice1 + yajin + daijiaPrice;
					}else{
						totalPrice123 = days * salePrice1 + yajin + daijiaPrice - couponFeeee;
					}
					totalPrice123 = totalPrice123.toFixed(2);
					$("#555").html(totalPrice123);
					$("#totalPrice11").val(totalPrice123);
				}
			})
		})
		
		$("#radio11").click(function(){
			var couponFee = parseFloat($("#couponSelect option:selected").attr("fee"));
	    	couponFee = Math.round(couponFee*100)/100;
		    if (undefined == couponFee)
		    {
		        couponFee = 0.00;
		    }
			var couponFeeee = parseFloat(couponFee);
			couponFeeee = Math.round(couponFeeee*100)/100;
			$("#radio11").each(function(index,obj){
				if($(obj).prop("checked")){
					$("#333").css("display","none");
					var totalPrice321 = 0;
					if(couponFeeee ==0 ){
						totalPrice321 = days * salePrice1 + yajin;
					}else{
						totalPrice321 = days * salePrice1 + yajin - couponFeeee;
					}
					totalPrice321 = totalPrice321.toFixed(2);
					$("#555").html(totalPrice321);
					$("#totalPrice11").val(totalPrice321);
				}
			})
		})
		var totalPrice=days*salePrice1+yajin;
		totalPrice = parseFloat(totalPrice);
		totalPrice = Math.round(totalPrice*100)/100;
		$("#555").html(totalPrice);
		$("#totalPrice11").val(totalPrice);
		var shijian = $("#shijian").text();
		$("#appointmentTime11").val(shijian);
		var jiesushijian = $("#jiesushijian").text();
		$("#returnTime").val(jiesushijian);
	})
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
		var price111 = $("#555").html();
		var price1111 = parseFloat(price111);
		price1111 = Math.round(price1111*100)/100;
		$("#couponFeee").html(couponFeeee);
		
		if(a==0){
			var totalPrice111=price1111-couponFeeee;
			code11 = couponFeeee;
			a=1;
		}
		else{
			var totalPrice111=price1111-couponFeeee+code11;
			code11 = couponFeeee;
		}
		$("#555").html(totalPrice111);    
	}
	
</script>

<body>
<#include "/client/common_header.ftl" />

<div class="h20"></div>
<!-- ********************中间内容******************** -->
<form class="rentcar_order" id="form11" action="/rent/order/submit" method="post">
<#if goods??><input type="hidden" name="goodsId" value="${goods.id!c}"></#if>
<#if days??><input type="hidden" name="days" value="${days!''}"></#if>
			<input type="hidden" name="totalPrice" id="totalPrice11" value="">
			<input type="hidden" name="appointmentTime" id="appointmentTime11" value="">
			<input type="hidden" name="returnTime" id="returnTime" value="">
			<input name="includePrice" value="${goods.includePrice?string("0.00")}" hidden="hidden">
	<!-- 订单信息 -->
	<dl class="dl1">
		<dt>订单信息：</dt>
		<dd class="dd1">
			<ul>
				<li class="li1">
					<p class="p1">车型：</p>
					<p class="p2">${goods.title!''}</p>
				</li>
				<li class="li2">
					<p class="p1">取车时间：</p>
					<p class="p2">
						<label id="shijian"><#if groupSaleStartTime??>${groupSaleStartTime!''}</#if></label>
					</p>
					<p class="p1">还车时间：</p>
					<p class="p2">
						<label id="jiesushijian"></label>
					</p>
				</li>
				<li class="li3">
					<p class="p1">取车地址：</p>
					<p class="p2">${goods.qcdz!''}</p>
					<p class="p3">还车地址：</p>
					<p class="p4">${goods.hcdz!''}</p>
				</li>
				<li class="li4">
					<p class="p1">用车方式：</p>
					<p class="p2">
						<input type="radio" id="radio11" checked name="rent1" value="自驾">
						<label>自驾</label>   
						<input type="radio" id="radio22" name="rent1" value="代驾">
						<label>代驾</label>
					</p>
					<p class="p3">价格</p>
					<p class="p4">${goods.salePrice?string("0.00")}元/天</p>
				</li>
			</ul>
		</dd>
		<dd class="dd2">
			<p>
				<label>￥${goods.salePrice?string("0.00")}*${days!0}</label>
				<i class="dot"></i>
				<label><font><#if days??>${days!0}</#if></font>天</label>
			</p>
			<p>
				<label>￥${goods.includePrice?string("0.00")}元</label>
				<i class="dot"></i>
				<label>押金</label>
			</p>
			<p >
			<span style="display:none" id="333">
				<label>￥${goods.outFactoryPrice?string("0.00")}起</label>
				<i class="dot"></i>
				<label>代驾费</label>
			</span>
			</p>
			<p class="p4"><label>总计：<span id="555"> </span>元</label></p>
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
	<!-- 联系信息 -->
	<dl class="contact_infor">
		<dt>联系信息：</dt>
		<dd>
			<div class="div1">
				<label>姓名:</label>
				<input type="text" name="shippingName" datatype="s1-20" errormsg="亲，请输入一个以上的字段" nullmsg="请填写信息！">
				<span class="Validform_checktip" style="color: red;">*</span>
			</div>
			<div class="div2">
				<label>手机号:</label>
				<input type="text" name="shippingPhone" datatype="m" errormsg="亲，请填写11位正确的手机号码！">
				<span class="Validform_checktip" style="color: red;">*</span>
			</div>
			<div class="div3">
				<label>邮箱:</label>
				<input type="text" name="leavePort" datatype="e" errormsg="亲，邮箱格式不正确哟！">
				<span class="Validform_checktip" style="color: red;">*</span>
			</div>
		</dd>
	</dl>
	<!-- 联系信息-结束 -->
	<div class="h50"></div>
	<div class="submits">
		<input type="submit" name="submit" id="submit" value="提交订单">
	</div>
</form>
<div class="h50"></div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

</body>
</html>