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

	<!--
	var d1 =[
		{
			"Date": "2015-10-10",
			"Price": "158"
		},
		{
			"Date": "2015-10-11",
			"Price": "158"
		},
		{
			"Date": "2015-10-12",
			"Price": "158"
		},
		{
			"Date": "2015-10-13",
			"Price": "158"
		},
		{
			"Date": "2015-10-14",
			"Price": "158"
		},
		{
		
			"Date": "2015-10-15",
			"Price": "158"
		},
		{
			"Date": "2015-10-16",
			"Price": "158"
		},
		{
			"Date": "2015-10-17",
			"Price": "158"
		},
		{
			"Date": "2015-11-15",
			"Price": "158"
		},
		{
			"Date": "2015-12-13",
			"Price": "158"
		},
		{
			"Date": "2015-12-14",
			"Price": "59"
		},
		{
			"Date": "2015-12-15",
			"Price": "158"
		},
		{
			"Date": "2015-12-16",
			"Price": "59"
		}
	];
	function AjaxTime(id){
		pickerEvent.setPriceArr(eval(d1));
		pickerEvent.Init(id);
	}
	-->
	
	//手机号
	var phoneFormat = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	// 电子邮箱
	var pattern_email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
	function goSteadCar(num){
		var name = $("#name_" + num).val();
		var driverName = $("#driverName_" + num).val();
		var phone = $("#phone_" + num).val();
		var email = $("#email_" + num).val();
		var qq = $("#qq_" + num).val();
		var note = $("#note_" + num).val();
		//alert(name + "," + driverName + "," + phone + "," + email + "," + qq + "," + note);
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
    	
    	if(email!=""){
			if(!pattern_email.test(email)){
		        alert("电子邮箱格式错误，请重新填写！");
		        return ;
    		}	
		}
		//alert(name + "," + driverName + "," + phone + "," + email + "," + qq + "," + note);
		$.ajax({
			type: "POST",
			url: "/steadCar/save",
			data: "name="+name+"&driverName="+driverName+"&phone="+phone+"&email="+email+"&qq="+qq+"&note=" + note,
			success: function(backInfo){
				if(backInfo.success){
					alert("预约代驾成功，请保持电话畅通！");
					window.location.reload();
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
			<a href="/rent/car3">约租</a>
			<a href="/rent/driveStead" class="a1">代驾</a>
		</dt>
		
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
                background: #fff url(images/f_txtbgicon.png) no-repeat;
                padding-left: 90px;
            }

            .bookingwrap input.inppeople {
                padding-left: 36px;
                background: #fff url("images/f_txtbgicon1.png") 10px 3px no-repeat;
            }

            .bookingwrap input.inpdate {
                padding-left: 90px;
                width: 170px;
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
            .bookingwrap input.inparea {
                width: 318px;
                background: #fff url("images/f_txtbgicon2.png") 0 0 no-repeat;
            }
            .bookingwrap input.inptxtdate {
                padding-left: 90px;
                width: 170px;
                margin-right: 10px;
                background: #fff url("images/f_txtbgicon2.png") 0 -61px no-repeat;
            }
            .bookingwrap input.inpmubiao {
                padding-left: 36px;
                background-position: 0 -80px;
            }
            .bookingwrap input.inptelephone {
                padding-left: 36px;
                background: #fff url("images/f_txtbgicon1.png") 10px -59px no-repeat;
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
            .bookingwrap input.inpnormal{
            	width: 394px;
                padding:0;
                padding-left:15px;
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
        <dd style="display: inline-block">
        	<form action="/rent/driveStead/search">
	            <div class="bookingwrap">
	                <input type="text" style=" margin-top: 35px;" class="inpnormal" placeholder="驾龄要求" name="subTitle" id="jialing">
	                <input type="text" class="inpnormal" placeholder="驾照类型" name="leavePort" id="jiazhao">
	            </div>
	            <div class="bookingwrap right">
	                <#--<input type="text" class="inpdate ipn1" name="steadTime" id="backCarTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" readonly="true" placeholder="选择代驾时间">-->
	                <input type="text" style=" margin-top: 35px;" class="inpnormal" name="title" id="siji" placeholder="司机姓名">
	                <input type="submit" value="搜索" class="searc">
	            </div>
			</form>
        </dd>
    </dl>
    <style>
        .rent_car .dl3{
            overflow:hidden;
        }
        .rent_car .dl3 dt{
            width:1200px;
            line-height:100px;
            text-align:center;
            font-size:16px;
            color:#274b99;
        }
        .rent_car .dl3 dt label{
            float:left;
        }

        .rent_car .dl3 ul{
            width:1200px;
            overflow:hidden;
            float: left;
            border: 1px solid #ddd;
            border-bottom: 0;
            background: #fff;
        }
        .rent_car .dl3 ul li{
            padding:35px 15px 0;
            border-bottom: 1px solid #ddd;
        }
        .masterwrap {
            width: 100%;
            margin-bottom: 30px;
            display: inline-block;
        }
        .masterwrap .masterimg {
            float: left;
            width: 120px;
            height: 90px;
            margin-right: 40px;
        }
        .masterwrap .masterinfo {
            float: left;
        }
        .masterwrap .a_booking {
            float: right;
            width: 145px;
            height: 34px;
            line-height: 34px;
            color: #fff;
            background: #ff9813;
            border-radius: 5px;
            text-align: center;
            margin-top: 30px;
            margin-right: 15px;
        }
        .masterwrap .masterinfo {
            float: left;
            width:800px ;
        }
        .masterwrap .masterinfo span {
            display: block;
            line-height: 45px;
        }
        .masterwrap .masterinfo span.name {
            font-size: 20px;
        }
        .masterwrap .masterinfo span.name font{
            font-size: 14px;
        }
        .bookingwrap1 {
            width: 100%;
            display: inline-block;
            border-top: 3px solid #fafafa;
            padding:15px 0;
        }
        .bookingwrap1 > span {
            float: left;
            width: 100%;
            line-height: 46px;
            font-size: 16px;
            color: #284485;
        }
        .bookingwrap1 .inwrap {
            float: left;
            line-height: 30px;
            margin-right: 35px;
            margin-bottom: 20px;
        }
        .bookingwrap1 .inwrap input[type='text']{
            width: 200px;
            border: 1px solid #eee;
            line-height: 29px;
            background: #fafafa;
        }
        .bookingwrap1 .inwrap input[type='text'].long{
            width: 765px;

        }
        .bookingwrap1 .btnsubmit{
            float: left;
            border: 0;
            width: 200px;
            line-height: 30px;
            color: #fff;
            background: #ff9813;
            border-radius: 5px;
            text-align: center;
            margin-left: 44px;

        }
    </style>
    <dl class="dl3">
    	<#if rent_car_page??>
    		<#if rent_car_page.content?size lt 1>
    			<dt><label style="color: red;">对不起，没有满足条件的代驾服务！</label></dt>
    		<#else>
    			<dt><label>可选择的代驾服务：</label></dt>		
    		</#if>
    	</#if>
        <dd>
            <ul >
	            <#if rent_car_page??>
					<#list rent_car_page.content as item>
					<li>
						<div class="masterwrap" >
		                    <a href="javascript:;" class="masterimg"><img class="masterimg" src="${item.coverImageUri!''}"></a>
		                    <div class="masterinfo">
		                        <span class="name">${item.title!''} <font>（驾龄：${item.subTitle!''}年）</font></span>
		                        <span>${item.passPort!''}</span>
		                    </div>
		                    <a href="javascript:;" onclick="$(this).parent().next().slideDown();$(this).hide();" class="a_booking" title="">预订</a>
		                </div>
		                <div class="bookingwrap1" style="display: none;">
		                    <span>请填写代驾信息：</span>
		                    <div class="inwrap">
		                        <label>称呼：</label>
		                        <input type="text" name="name" id="name_${item_index}"/>
		                        <input type="text" name="driverName" id="driverName_${item_index}" value="${item.title!''}" hidden="hiddens"/>
		                    </div>
		                    <div class="inwrap">
		                        <label>电话：</label>
		                        <input type="text" name="phone" id="phone_${item_index}"/>
		                    </div>
		                    <div class="inwrap">
		                        <label>邮箱：</label>
		                        <input type="text" name="email" id="email_${item_index}"/>
		                    </div>
		                    <div class="inwrap">
		                        <label>QQ：</label>
		                        <input type="text" name="qq" id="qq_${item_index}"/>
		                    </div>
		                    <div class="inwrap">
		                        <label>备注：</label>
		                        <input type="text" class="long" name="note" id="note_${item_index}"/>
		                    </div>
		                    <input type="button" class="btnsubmit" value="提交" onclick="goSteadCar(${item_index})"/>
		                </div>
		                </li>
					</#list>
				</#if>
            </ul>
        </dd>
    </dl>
    <#if rent_car_page??>
        <#assign continueEnter=false>
        <#if rent_car_page.totalPages gt 0>
           	<ul class="mymember_page" style="margin-top:15px;">
            <#list 1..rent_car_page.totalPages as page>
                <#if page <= 3 || (rent_car_page.totalPages-page) < 3 || (rent_car_page.number+1-page)?abs<3 >
                    <#if page == rent_car_page.number+1>
                        <dd>
                            <a class="mysel" href="javascript:;">${page}</a>
                        </dd>
                    <#else>
                        <dd>
                            <a href="/rent/driveStead?page=${page-1}">${page}</a>
                        </dd>
                    </#if>
                    <#assign continueEnter=false>
                <#else>
                    <#if !continueEnter>
                        <b class="pn-break">&hellip;</b>
                        <#assign continueEnter=true>
                    </#if>
                </#if>
            </#list>
           </ul>
		</#if>
    </#if>
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