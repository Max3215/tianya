<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><#if setting??>${setting.seoTitle!''}-</#if>豪华邮轮</title>
	<meta name="keywords" content="<#if setting??>${setting.seoKeywords!''}</#if>">
	<meta name="description" content="<#if setting??>${setting.seoDescription!''}</#if>">
	<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/rich_css.css">
<link rel="stylesheet" type="text/css" href="/client/css/home.css">
<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>	
<script src="/client/js/rich_lee.js" type="text/javascript"></script>
<script src="/client/js/layout.js" type="text/javascript"></script>
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
	.mymember_page li{
		float:left;
	    width: 30px;
	    height: 30px;
	    line-height: 30px;
	    margin-left:10px;
	    text-align: center;
	    border: 1px solid #033a7a;
	}

</style>	
<script type="text/javascript">
	window.onload = function(){
		banner_go('my');//焦点图
	};
	
	//手机号
	var phoneFormat = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	//天数（正整数）
	var zzs =  /^[1-9]\d*$/;
	function goCustom(){
		var month = $("#inputmonth").val();
		var place = $("#inputwhither").val();
		var totalDays = $("#inputday").val();
		var totalPersons = $("#inputpeonum").val();
		var telephone = $("#inputphone").val();
		
		if(place==""){
			alert("请填写旅游地点！");
	        return ;
		}
		


		if(!zzs.test(totalDays)){
			alert("请输入正整数！");
	        return ;
		}
		if(telephone==""){
			alert("请输入手机号！");
	        return ;
		}else if(!phoneFormat.test(telephone)){
	        alert("手机号格式错误，请重新填写！");
	        return ;
    	}
    	
		$.ajax({
			type: "POST",
			url: "/visitorCustom/save",
			data: "month="+month+"&place="+place+"&totalDays="+totalDays+"&totalPersons="+totalPersons+"&telephone="+telephone,
			success: function(backInfo){
				if(backInfo.success){
					alert("定制成功，请保持电话畅通！");
					window.location.href="/search_list?keyword="+place;
				}else{
					alert(backInfo.msg);				
				}
			}
		});
	}
</script>
<body>
<#include "/client/common_header.ftl" />

<div class="index_banner">
	<div class="banner_scrool">
		<#if big_scroll_ad_list6??>
			<#list big_scroll_ad_list6 as item>
				<a href="${item.linkUri!''}"  target="_blank"><img src="${item.fileUri!''}" /> </a>
			</#list>
		</#if>
	</div>
	<div class="move_btn">
		<ul>
			<#if big_scroll_ad_list6??>
				<#list big_scroll_ad_list6 as item>
					<li><a></a></li>
				</#list>
			</#if>
		</ul>
	</div>
</div>

<!--游客定制-->
<style>
    .bookingwrap {
        width: 100%;
        display: inline-block;
        min-width: 1440px;
        background-color: #000;
        height: 48px;
    }
    .bookingwrap .inbookwrap{
        width: 1200px;
        text-align: center;
        line-height: 48px;
    }
    .bookingwrap .inputlong{
        width: 150px;
        height: 20px;
        line-height: 20px;
    }
    .bookingwrap .inputshort{
        width: 67px;
        height: 20px;
        line-height: 20px;
    }
    .bookingwrap .inputbutton{
        padding: 0 5px;
        height: 24px;
        line-height: 20px;
        background: #1e3c7f;
        color: #fff;
        border: 0;
    }
    .bookingwrap .inbookwrap label {
        margin: 0 3px;
    }
</style>
<div class="bookingwrap">
    <div class="inbookwrap">
        <label for="inputmonth">我想在</label>
        <select name="month" id="inputmonth" class="inputshort">
            <option value="0">月份</option>
            <option value="1">一月</option>
            <option value="2">二月</option>
            <option value="3">三月</option>
            <option value="4">四月</option>
            <option value="5">五月</option>
            <option value="6">六月</option>
            <option value="7">七月</option>
            <option value="8">八月</option>
            <option value="9">九月</option>
            <option value="10">十月</option>
            <option value="11">十一月</option>
            <option value="12">十二月</option>
        </select>
        <label for="inputwhither">去</label>
        <input type="text" id="inputwhither" class="inputlong" value="" name="place"/>
        <label for="inputday">预计玩</label>
        <input type="number" id="inputday" class="inputshort" min="1" value="" name="totalDays" onkeydown="return checkNumber(event);"/>天
        <label for="inputpeonum">总共</label>
        <input type="text" id="inputpeonum" class="inputshort" value="" name="totalPersons" onkeydown="return checkNumber(event);" onfocus="if(value=='1'||value=='0') {value=''}" onblur="if (value==''||value=='0') {value='1'}" onkeyup="value=value.replace(/[^0-9]/g,'')"/>人
        <label for="inputphone">电话</label>
        <input type="text" id="inputphone" class="inputlong" value="" name="telephone" onkeydown="return checkNumber(event);"/>
        <input type="button" style="cursor:pointer" onclick="goCustom()" value="开始定制" class="inputbutton"/>
    </div>
</div>
<!--游客定制结束-->

<!-- ********************中间内容******************** -->
<div class="content">
	<div class="expen_box">
		<div class="hot_line">
			<div class="hot_linetitle">
				<span></span>
				<a href="javascript:;">热门航线</a>
				<span></span>
			</div>
			<dl>
				<dt>
					<#if hot_ship_page?? && hot_ship_page.content?size gt 0>
						<a href="/detail/${hot_ship_page.content[0].id?c}"  target="_blank">
							<img src="${hot_ship_page.content[0].coverImageUri!''}"  title="${hot_ship_page.content[0].title!''}" />
						</a>
					</#if>
				</dt>
				<dd>
					<#if hot_ship_page?? && hot_ship_page.content?size gt 1>
						<#list hot_ship_page.content as item>
							<#if item_index gt 0>
							<a href="/detail/${item.id?c}"  target="_blank">
								<img src="${item.coverImageUri!''}" title="${item.title!''}"/>
							</a>
							</#if>
						</#list>
					</#if>
				</dd>
			</dl>
		</div>
		<div class="reco_box">
			<div class="reco_linetitle">
				<span></span>
				<a href="javascript:;">推荐航线</a>
				<span></span>
			</div>
			<div class="bote_nav">
			<ul style="width: 808px">
				<#if ship_cat_list??>
					<#list ship_cat_list as item>
						<li><a href="/list/${item.id?c}" style="display: inline-block;margin: 0px 10px;font-size: 16px;text-decoration: none;">${item.title!''}</a></li>
					</#list>
				</#if>
			</ul>
		</div>
		<div class="bote_box">
			<ul>
				<#if ship_list_page??>
				<#list ship_list_page.content as item>
					<li>
						<a href="/detail/${item.id?c}"  target="_blank">
							<img src="${item.coverImageUri!''}" title="${item.title!''}">
						</a>
					</li>
				</#list>
				</#if>
			</ul>
			
		</div>
		
		</div>
		<#if ship_list_page??>
                <#assign continueEnter=false>
                <#if ship_list_page.totalPages gt 0>
                <ul class="mymember_page">
                    <#list 1..ship_list_page.totalPages as page>
                        <#if page <= 3 || (ship_list_page.totalPages-page) < 3 || (ship_list_page.number+1-page)?abs<3 >
                            <#if page == ship_list_page.number+1>
	                            <li>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </li>
                            <#else>
	                            <li>
	                                <a href="/user/order/list<#if code??>/${code}</#if>?page=${page-1}<#if orderNumber??>&orderNumber=${orderNumber!''}</#if><#if orderType??>&orderType=${orderType!''}</#if>">${page}</a>
	                            </li>
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
		<div style="width: 100%;height: 130px;float: left;"></div>
</div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />
</body>
</html>