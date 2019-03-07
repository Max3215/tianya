<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><#if setting??>${setting.seoTitle!''}-</#if>汽车租赁</title>
	<meta name="keywords" content="<#if setting??>${setting.seoKeywords!''}</#if>">
	<meta name="description" content="<#if setting??>${setting.seoDescription!''}</#if>">
	<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
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
<script type=text/javascript src="/client/js/f_aboutjs.js"></script>
<script type=text/javascript src="/mag/js/WdatePicker.js"></script>
<!--价格日历控件样式-->
<link href="/client/css/datepicker.css" rel="stylesheet" />
<!--价格日历控件-->
<script src="/client/js/zlDate.js"></script>
<script type="text/javascript">
	window.onload = function(){
		banner_go('my');//焦点图
		become_big('act_main li a',30);//图片发放大
		box_out();//图片滑出
		act_nav();

		$("#date1").timepicki();
		$("#date2").timepicki();
	};

	
	//手机号
	var phoneFormat = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	
	function goReserve(){
		var name = $("#name").val();
		var phone = $("#phone").val();
		var takeCarTime = $("#takeCarTime").val();
		var backCarTime = $("#backCarTime").val();
		var getCarPlace = $("#getCarPlace").val();
		var backCarPlace = $("#backCarPlace").val();
		
		if(name==""){
			alert("请输入姓名！");
	        return ;
		}
		
		if(phone==""){
			alert("请输入手机号！");
	        return ;
		}else if(!phoneFormat.test(phone)){
	        alert("手机号格式错误，请重新填写！");
	        return ;
    	}
    	
    	if(takeCarTime==""){
			alert("请选择取车时间！");
	        return ;
		}
		if(backCarTime==""){
			alert("请选择还车时间！");
	        return ;
		}
		if(getCarPlace==""){
			alert("请输入取车地点！");
	        return ;
		}
		if(backCarPlace==""){
			alert("请输入还车地点！");
	        return ;
		}
    	
		$.ajax({
			type: "POST",
			url: "/reserveCar/save",
			data: "name="+name+"&phone="+phone+"&takeCarTime="+takeCarTime+"&backCarTime="+backCarTime+"&getCarPlace="+getCarPlace+"&backCarPlace=" + backCarPlace,
			success: function(backInfo){
				if(backInfo.success){
					alert("约租成功，请保持电话畅通！");
					//window.location.href="/search_list?keyword="+place;
				}else{
					alert(backInfo.msg);				
				}
			}
		});
	}
</script>


<body>


<#include "/client/common_header.ftl" />

<style type="text/css">
.mymember_page{
		float:right;
		overflow: hidden;
	    margin-right:50px;
	    font-size: 16px;
	    color: #033a7a;
	    margin-top:10px;
	}
	.mymember_page:after{
		content:"clear";
		clear:both;
		height:0px;
		display:block;
		overflow:hidden;
	}
	.mymember_page dd{
		float:left;
	    width: 30px;
	    height: 30px;
	    line-height: 30px;
	    margin-left:10px;
	    text-align: center;
	    border: 1px solid #033a7a;
	}
	.banner_positiontxt {
		padding:20px;
		position:absolute;
		top:50%;
		left:9%;
		font-size:16px;
		line-height:24px;
		color:#666;
		z-index:500;
		background: url(/client/images/bgpic.png) repeat;
	}
</style>

<!-- banner固定文字 -->
<div class="banner_positiontxt">
	<p>商旅服务中心隶属重庆交运集团 ，于2013年4月正式成立。</p>
    <p>积极整合交运集团内部和其他从事道路运输企业车辆资源。</p>
    <p>搭建个性化、系统化、规范化的综合运输平台。主要开展</p>
    <p>业务有汽车租赁、包车业务、代驾服务。</p>
    <p>汽车租赁：杨经理 13996265070</p>
</div>

<!-- 轮播图 -->
<div class="index_banner">
	<div class="banner_scrool">
		<#if big_scroll_ad_list??>
			<#list big_scroll_ad_list as item>
				<a href="${item.linkUri!''}"><img src="${item.fileUri!''}" /> </a>
			</#list>
		</#if>
	</div>
	<div class="move_btn">
		<ul>
			<#if big_scroll_ad_list??>
				<#list big_scroll_ad_list as item>
					<li><a></a></li>
				</#list>
			</#if>
		</ul>
	</div>
</div>
<!-- 轮播图-结束 -->
<div class="h20"></div>
<!-- ********************中间内容******************** -->
<div class="rent_car">
	<!-- 直通车 -->
	<dl class="dl1">
		<dt id="rent_cardl1_dt">
			<a href="/rent/car_short">临租服务</a>
			<a href="/rent/car_long">长租服务</a>
			<a href="/rent/car2">包车服务</a>
			<a href="/rent/car3" class="a1">约租</a>
			<a href="/rent/driveStead" >代驾</a>
		</dt>
		
		<!-- 约租-->
		<style>
            .bookingwrap {
                float: left;
                width: 410px;
                margin-left: 90px;
                padding-right: 80px;
                border-right: 1px dashed #fff;
            }

            .bookingwrap input[type='text'] {
                float: left;
                width: 372px;
                margin-top: 10px;
                height: 39px;
                line-height: 40px \9;
                border: 1px solid #dddddd;
                background: #fff url("/client/images/f_txtbgicon.png") no-repeat;
                padding-left: 90px;
            }

            .bookingwrap input.inppeople {
                padding-left: 36px;
                background: #fff url("/client/images/f_txtbgicon1.png") 10px 3px no-repeat;
            }

            .bookingwrap input.inpdate {
                padding-left: 90px;
                width: 317px;
                margin-right: 10px;

            }
            .bookingwrap input.inptime {
                float: left;
                padding-left: 36px;
                width: 100px;
                background-position: 0 -156px;
            }
            .bookingwrap input.inpaddress {
                width: 318px;
            }
            .bookingwrap input.inptelephone {
                padding-left: 36px;
                background: #fff url("/client/images/f_txtbgicon1.png") 10px -59px no-repeat;
            }
            .bookingwrap input.ipn1{
                background-position: 0 -310px;
            }
            .bookingwrap input.ipn2{
                background-position: 0 0;
            }
            .bookingwrap input.ipn3{
                background-position: 0 -39px;
            }
            .bookingwrap input.ipn4{
                background-position: 0 -349px;
            }
            .bookingwrap input.ipn5{
                background-position: 0 -388px;
            }
            .bookingwrap.right{
                margin-right: 100px;
                margin-left: 0;
                float: right;
                border: 0;
                padding: 0;
            }
            .bookingwrap .searc{
                width: 410px;
                height:40px;
                line-height:40px\9;
                display:block;
                float:left;
                margin-top:10px;
                border:1px solid #00ccff;
                background:#00ccff;
                font-size:14px;
                color:#ffffff;
            }
        </style>
        <dd style="height: auto; display: inline-block">
            <div class="bookingwrap">
                <input type="text" style=" margin-top: 30px;" class="inppeople" placeholder="您的称呼" name="name" id="name">
                <input type="text" class="inpdate ipn1" name="takeCarTime" id="takeCarTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" readonly="true" placeholder="点击选择时间">
                <#--<input type="text" class="inpdate ipn1" value="2015-12-30">-->
                <#--<input type="text" class="inptime" placeholder="16:00">-->
                <input type="text" class="inpaddress ipn2" placeholder="取车地点" name="getCarPlace" id="getCarPlace">
            </div>
            <div class="bookingwrap right">
                <input type="text" class="inptelephone" placeholder="您的电话" name="phone" id="phone">
                <input type="text" class="inpdate ipn4" name="backCarTime" id="backCarTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" readonly="true" placeholder="点击选择时间">
                <#--<input type="text" class="inpdate ipn3" value="2015-12-30">-->
                <#--<input type="text" class="inptime" placeholder="16:00">-->
                <input type="text" class="inpaddress ipn5" placeholder="请输入车型" name="backCarPlace" id="backCarPlace">
                <input type="button" value="提交" onclick="goReserve()" class="searc">
            </div>
        </dd>
	</dl>
</div>
<div class="h50"></div>


<!--选择取车还车地点的隐藏div-->
<div id="jsContainer" class="jsContainer" style="height:0">
	<div id="tuna_alert" style="position:absolute;z-index:999;overflow:hidden;"></div>
	<div id="tuna_jmpinfo" style="visibility:hidden;position:absolute;z-index:120;"></div>
</div>

<script type="text/javascript" src="js/fixdiv.js"></script>
<script type="text/javascript" src="js/address.js"></script>

<!--选择取车还车地点的隐藏div-->

<!-- ********************中间内容-结束******************** -->
<#include "/client/common_footer.ftl" />
</body>
</html>