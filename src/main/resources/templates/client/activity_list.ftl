<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title><#if setting??>${setting.seoTitle!''}-</#if>主题</title>
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
<script type="text/javascript">
window.onload = function(){
	banner_go('my');//焦点图
	function $(id){return document.getElementById(id);}
	if($("lli01")){
		var llia=$("lli01").getElementsByTagName("a");
		for(i=0;i<llia.length;i++){
			llia[i].index=i;
			llia[i].onclick=function(){
				for(i=0;i<llia.length;i++){
					llia[i].style.fontSize="14px";
				}
				llia[this.index].style.fontSize="16px";
				if(llia[this.index].innerHTML=="综合排序 &darr;"){
					llia[this.index].innerHTML="综合排序 &uarr;"
				}
				else if(llia[this.index].innerHTML=="综合排序 &uarr;"){
					llia[this.index].innerHTML="综合排序 &barr;"
				}
			}
		}
	}
};

function setprice() {
    var p1 = $.trim($('#priceLow').val()), p2 = $.trim($('#priceHigh').val())
        
    if (isNaN(p1) || p1=="") { p1 = 0 }
    if (isNaN(p2) || p2== "") { p2 = 0 }
    
	var price = p1 + '-' + p2;
    var url = '/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx>-${pidx?c}</#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if>';
    if (price != "0-0") { url += "_" + price; }
    location.href = url;
}

$('#priceLow,#priceHigh').keypress(function (e) {
    if (e.keyCode == 13) {
        setprice();
    }
});


function search11(){
	var id = ${category.id?c};
	var keyword = $("#searchBox").val();
	window.location.href="/list/"+id+"?keyword="+keyword;
}

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
	.mymember_page .mysel{background:#033a7a;color:#fff;display:block;}
</style>



<body>
<#include "/client/common_header.ftl" />

<div class="index_banner">
		<div class="banner_scrool">
			<#if big_scroll_ad_list2??>
				<#list big_scroll_ad_list2 as item>
					<a href="${item.linkUri!''}"><img src="${item.fileUri!''}" /> </a>
				</#list>
			</#if>
		</div>
		<div class="move_btn">
			<ul>
				<#if big_scroll_ad_list2??>
					<#list big_scroll_ad_list2 as item>
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
	<div class="boat_list">
		<div class="boat_left">
			<div class="boat_left01">
				<div class="title">按条件筛选</div>
				<ul>
					<#if level2_cat_list??>
						<li>
							<span>主题：</span>
							<select onchange="window.location='/list/'+this.value;">
								<#list level2_cat_list as item>
									<option <#if item.id==category.id>selected="selected"</#if> value="${item.id?c}">${item.title!""}</option>
								</#list>
							</select>
						</li>
					</#if>
				
					<#if param_list??>
						<#list param_list as param>
							<li>
								<span>${param.title!""}：</span>
								<select onchange="window.location='/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx><#if pidx_index<param_index>-${pidx?c}</#if></#list></#if>-'+this.value+'<#if param_index_list??><#list param_index_list as pidx><#if pidx_index gt param_index>-${pidx?c}</#if></#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId>-<#if sortOrder==0>0<#else>1</#if><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if><#if priceLow?? && priceHigh??>_${priceLow?c}-${priceHigh?c}</#if>'">
									<option value="0">全部</option>
									<#if param.valueList??>
			                            <#list param.valueList?split(",") as value>
			                                <#if value!="">
												<option <#if param_index_list?? && param_index_list[param_index]?? && param_index_list[param_index]==value_index+1>selected="selected"</#if> value="${value_index+1}">${value?trim!""}</option>
											</#if>
										</#list>
									</#if>
								</select>
							</li>
						</#list>
					</#if>
				</ul>	
			</div>
			<div class="boat_left02">
				<div class="pri">按条件筛选</div>
				<ul>
					<li>
						<a href="/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx>-${pidx?c}</#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if>_1-1000">1-1000</a>
					</li>
					<li>
						<a href="/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx>-${pidx?c}</#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if>_1001-10000">1001-10000</a>
					</li>
					<li>
						<a href="/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx>-${pidx?c}</#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if>_10001-30000">10001-30000</a>
					</li>
					<li>
						<a href="/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx>-${pidx?c}</#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if>_30001-50000">30001-50000</a>
					</li>
					<li>
						<a href="/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx>-${pidx?c}</#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if>_50001-100000">50001-100000</a>
					</li>
					<li>
						<a href="/list/${category.id?c}<#if param_index_list??><#list param_index_list as pidx>-${pidx?c}</#list></#if><#if sortId??>-${sortId?c}</#if><#list 0..2 as sid><#if sid==sortId><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if>_100000-">100000以上</a>
					</li>
					<li class="li01">
						<input id="priceLow" type="text" value="<#if priceLow??>${priceLow?c}</#if>">
						<p>~</p>
						<input id="priceHigh" type="text" value="<#if priceHigh??>${priceHigh?c}</#if>">
						<a href="javascript:setprice();">确定</a>
					</li>
					<li class="li02">
						<input type="text" name="searchBox" id="searchBox" <#if keyword??>value="${keyword!''}"</#if> />
						<a href="javascript:;" onclick="search11();"></a>
					</li>
				</ul>	
			</div>
		</div>
			<div class="boat_right">
				<div class="boatright_title">
					<ul>
						<li class="li01" id="lli01">
							<a href="/list/${category.id?c}<#if param_index_list?? && param_index_list?size gt 0><#list param_index_list as pidx>-${pidx?c}</#list><#else>-0-0</#if>-0<#list 0..2 as sid><#if sid==sortId>-<#if sortOrder==0>1<#else>0</#if><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if><#if priceLow?? && priceHigh??>_${priceLow?c}-${priceHigh?c}</#if>" class="span01" id="lspan01">综合排序 <#if sortId?? && sortId==0 && sortOrder==0>&darr;<#elseif sortId?? && sortId==0 && sortOrder==1>&uarr;</#if></a>
							<a href="/list/${category.id?c}<#if param_index_list?? && param_index_list?size gt 0><#list param_index_list as pidx>-${pidx?c}</#list><#else>-0-0</#if>-1<#list 0..2 as sid><#if sid==sortId>-<#if sortOrder==0>1<#else>0</#if><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if><#if priceLow?? && priceHigh??>_${priceLow?c}-${priceHigh?c}</#if>">按价格 <#if sortId?? && sortId==1 && sortOrder==0>&darr;<#elseif sortId?? && sortId==1 && sortOrder==1>&uarr;</#if></a>
							<a href="/list/${category.id?c}<#if param_index_list?? && param_index_list?size gt 0><#list param_index_list as pidx>-${pidx?c}</#list><#else>-0-0</#if>-2<#list 0..2 as sid><#if sid==sortId>-<#if sortOrder==0>1<#else>0</#if><#else>-0</#if></#list><#if pageId??>-${pageId!''}</#if><#if priceLow?? && priceHigh??>_${priceLow?c}-${priceHigh?c}</#if>">按销量 <#if sortId?? && sortId==2 && sortOrder==0>&darr;<#elseif sortId?? && sortId==2 && sortOrder==1>&uarr;</#if></a>
						</li>
						<li class="li02">
							
							<a href="/">网站首页 </a>
							<#if category_tree_list??>
								<#list category_tree_list as item>
									&gt;<a href="/list/${item.id?c}">${item.title!''}</a>
								</#list>
							</#if>
						</li>
					</ul>
				</div>
				<#if goods_page??>
					<#list goods_page.content as item>
					<dl>
						<dt>
							<a href="/detail/${item.id?c}"  target="_blank"><img src="${item.coverImageUri!''}" /></a>
						</dt>
						<dd>
							<h3>${item.title!''}</h3>
							<p>${item.subTitle!''}</p>
							<span></span>
							<div class="div1">
								<div class="left">￥${item.salePrice?string("0.00")}起/人</div>
								<a href="/detail/${item.id?c}"  target="_blank">查看详情</a>
							</div>
							
						</dd>
					</dl>
					</#list>
				</#if>
			<#if goods_page??>
		        <#assign continueEnter=false>
		        <#if goods_page.totalPages gt 0>
		           	<ul class="mymember_page">
		            <#list 1..goods_page.totalPages as page>
		                <#if page <= 3 || (goods_page.totalPages-page) < 3 || (goods_page.number+1-page)?abs<3 >
		                    <#if page == goods_page.number+1>
		                        <dd>
		                            <a class="mysel" href="javascript:;">${page}</a>
		                        </dd>
		                    <#else>
		                        <dd>
		                            <a href="/list/${listStr}?page=${page-1}">${page}</a>
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
		</div>
	<div style="width: 100%;height: 130px;float: left;"></div>
</div>

<!-- ********************中间内容-结束******************** -->


<!-- ********************底部******************** -->
<#include "/client/common_footer.ftl" />
<!-- ********************底部-结束******************** -->
</body>
</html>