<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><#if goods??>${goods.seoTitle!''}-</#if>详情</title>
	<meta name="keywords" content="<#if goods??>${goods.seoKeywords!''}</#if>">
	<meta name="description" content="<#if goods??>${goods.seoDescription!''}</#if>">
	<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<link rel="stylesheet" type="text/css" href="/client/css/rich_css.css">
<link href="/client/css/datepicker.css" rel="stylesheet" />
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/client/js/luyi.js"></script>
<script src="/client/js/zlDate.js"></script>
<script type="text/javascript" src="/client/js/layout.js"></script>

<link rel="stylesheet" type="text/css" href="/client/css/jquery.ad-gallery.css">
<script type="text/javascript" src="/client/js/jquery.ad-gallery.js"></script>
<script type=text/javascript src="/mag/js/WdatePicker.js"></script>

<script src="/client/js/Calendar.js"></script>
<script src="/client/js/zlDate.js"></script>
<script src="/client/js/fun.js"></script>
<script type="text/javascript">
// 日期 格式为yyyy-MM-dd的字符串
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





</script>

<script type="text/javascript">

</script>

<body>
<!-- ********************头部******************** -->
<#include "/site_mag/common_header.ftl" />
<!-- ********************头部-结束******************** -->


<!-- ********************中间内容******************** -->
<div class="percenter_com detail_page">
	<!-- 商品详情 -->
	<div class="page_adress">
		<a href="/">天涯国旅首页</a>
		<#if category_tree_list??>
	        <#list category_tree_list as category>
	            &gt;
				<a href="/list/${category.id?c}">${category.title!""}</a>
	        </#list>
	    </#if>
		<#if goods??>
			&gt;
			<a href="/detail/${goods.id?c}">${goods.title!''}</a>
		</#if>
	</div>
	<dl class="goods_detail">
		<dt>
			<div class="ad-gallery">
			<div class="ad-image-wrapper"></div>
			<div class="ad-nav">
				<div class="ad-thumbs">
					<ul class="ad-thumb-list">
						<#if goods.showPictures??>
			                <#list goods.showPictures?split(",") as uri>
			                    <#if ""!=uri>
			                    	<li>
										<a href="${uri!''}">
											<img src="${uri!''}" >
										</a>
									</li>
			                    </#if>
			                </#list>
			            </#if>
					</ul>
				</div>
			</div>
		</dt>
		<dd>
			<p class="p1">${goods.title!''}</p>
			<p class="p2">${goods.subTitle!''}</p>
			<p class="p3" >
				<label class="la1">网上支付：成人价：<span id="chengrenjia"><#if goods.salePrice??>${goods.salePrice?string("0.00")}</#if></span>起&nbsp;&nbsp;&nbsp;儿童价：<span id="ertongjia"><#if goods.salePrice1??>${goods.salePrice1?string("0.00")}</#if></span>起</label>
				<a href="#"></a>
			</p>
			<#if !goods.categoryTitle?contains("私家定制")>
				<p class="p4">
					<label class="la1">出发港口：<span>${goods.leavePort!''}</span></label>
					<label class="la2">到达港口：<span>${goods.reachPort!''}</span></label>
				</p>
				<p class="p5">
					<label class="la1">途径港口：<span>${goods.passPort!''}</span></label>
				</p>			
			<#else>
				<p class="p4">
					<label class="la1">出发地点：<span>${goods.leavePort!''}</span></label>
				</p>
				<p class="p5">
					<label class="la2">到达地点：<span>${goods.reachPort!''}</span></label>
				</p>
				<p class="p5">
					<label class="la1">途径地点：<span>${goods.passPort!''}</span></label>
				</p>
			</#if>
			<#if !goods.categoryTitle?contains("私家定制")>
			<p class="p3" id="showNum" >
				<label class="la1">选择人数：成人：<input type="number" min="0" id="chengrenshu" style="width:35px" onchange="hanshu();" onkeydown="return checkNumber(event);" value="0">儿童：<input type="number" min="0" id="ertongshu" onchange="hanshu();" onkeydown="return checkNumber(event);" value="0" style="width:35px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价：<span id="zongjia">0.00</span>元</label>
			</p>
			<form class="sele_time">
				<label>出发时间：</label>
				<#--
				<center>
					<input type="text" id="calendar" name="calendar" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" />
				</center>
				-->
				<senter>
                    <input type="text" onclick="AjaxTimes(${goods.id?c})" class="calen" id="calendar${goods.id?c}" name="calendar"  readonly="readonly"/>
			    </center>
				<span id="timeError"></span>
				<input type="button" value="收藏"  class="saves">
				<!--<a onclick="window.location='/order/${goods.id?c}?date='+document.querySelector('#calendar').value;">立即预定</a>-->
				<a href="javascript:;">立即预定</a>
			</form>
			</#if>
		</dd>
	</dl>
	<!-- 商品详情-结束 -->
<script>
function AjaxTimes(id) {
    $.get("/detail/showprice?id=" + id, function (data) {
        //    alert(data);
        pickerEvent.setPriceArr(eval("(" + data + ")"));
        pickerEvent.Init("calendar" + id);
    });
}
</script>

	<div class="h20"></div>
	<!-- 轮船介绍 -->
	<div class="steamship">
		<ul id="steamship_ul">
			<li class="li1"><a href="#ta1">主题介绍</a></li>
			<li><a href="#ta2">行程介绍</a></li>
			<li><a href="#ta3">费用说明</a></li>
			<li><a href="#ta4">签证/签注</a></li>
			<li><a href="#ta5">预定须知</a></li>
			<li><a href="#ta6">旅友点评</a></li>
		</ul>
		
		<p class="ship_title"><a name="ta1">主题介绍</a></p>
		<p class="ship_word">
			${goods.shipDescription!''}
		</p>
		
	</div>
	<!-- 轮船介绍-结束 -->
	<!-- 行程介绍 -->
	<div class="travel_introduce">
		<div class="tr_intro_title"><a 	name="ta2">行程介绍</a></div>
		<div class="dd1"><img src="${goods.xcImageUri!''}"></div>
		<div class="dd2">
			<div class="day_title">
				<p>时间</p>
                <p>主题</p>
                <p>住宿</p>
                <p>餐食</p>
			</div>
			<ul class="travel_day">
			<#if goods?? && goods.xcList??>
			<#list goods.xcList as item>
				<li>
					<p>${item.day!''}</p>
                    <p>${item.title!''}</p>
                    <p>${item.live!''}</p>
                    <p>${item.eat!''}</p>
				</li>
			</#list>
			</#if>
			</ul>
			<div class="think"><span>*</span> 以上时间仅供参考，具体请以出团通知为准</div>
		</div>
		<div class="dd3">
			<ul class="dd3_left">
				<#if goods?? && goods.xcList??>
				<#list goods.xcList as item>
					<li><a href="#tday${item_index}">${item.day!''}</a></li>
				</#list>
				</#if>
			</ul>
			<ul class="dd3_right">
				<#if goods?? && goods.xcList??>
				<#list goods.xcList as item>
					<li>
						<img src="/client/images/circle.png" class="photo">
						<p class="p1"><a name="tday${item_index}">${item.title!''}</a></p>
						<p class="p2">${item.description!''}</p>
						<#if item.imgUrl?? && item.imgUrl != ''>
                        <div class="pic">
                            <img src="${item.imgUrl!''}" >
                        </div>
                        </#if>
                        <div class="meal">
                            <p>住宿：${item.live!''}</p>
                            <p>餐食：${item.eat!''}</p>
                        </div>
					</li>
				</#list>
				</#if>
			</ul>
		</div>
	</div>
	<!-- 行程介绍-结束 -->
	<!-- 费用说明 -->
	<div class="cost">
		<div class="cost_title"><a name="ta3">费用说明</a></div>
		<div class="cost_div1">
			${goods.feeDescription!''}
		</div>
	</div>
	<!-- 费用说明-结束 -->
	<!-- 签证/签注 -->
	<div class="visa">
		<div class="visa_title"><a name="ta4">签证/签注</a></div>
		<div class="visa_word">
			${goods.visaDescription!''}
		</div>
	</div>
	<!-- 签证/签注-结束 -->
	<!-- 预定须知 -->
	<div class="predeter">
		<div class="predeter_title"><a name="ta5">预定须知</a></div>
		<div class="predeter_word">
			${goods.bookDescription!''}
		</div>
	</div>
	<!-- 预定须知-结束 -->
	<!-- 旅友点评 -->
	<div class="people_conment">
		<div class="peo_title"><a name="ta6">旅友点评</a></div >
		<div class="dd1">
			<div class="div1">
				<p class="p1"><span>99%</span>满意度</p>
				<p class="p2">来自<span><#if count??>${count!356}</#if></span>名天涯旅客的真实评价</p>
			</div>
			<div class="div2">
				<p class="p1">
					<label>餐饮娱乐（4.9）</label>
				</p>
				<p class="p2">
					<label>主题娱乐（4.8）</label>
				</p>
				<p class="p3">
					<label>主题服务（4.9）</label>
				</p>
			</div>
			<div class="div3"><input type="button" value="立即评价"  title='评价'></div>
		</div>
		<div class="dd2">
			<div class="h50"></div>
			<ul id="click_moreul">
				<#if comment_page??>
				<#list comment_page.content as item>
					<li>
						<div class="li_left">
							<img src="${item.userHeadUri!''}">
							<p>${item.username!''}</p>
						</div>
						<div class="li_right">
							<div class="div1">
								<label>餐饮住宿: 
									<#if item.goodsStar??>
										<#if item.goodsStar==5>
											非常满意
										<#elseif item.goodsStar==4>
											满意
										<#elseif item.goodsStar==3>
											一般
										<#elseif item.goodsStar==2>
											不满意
										<#else>
											非常满意
										</#if>
									</#if>
								</label>
								<label>主题娱乐: 
									<#if item.serviceStar??>
										<#if item.serviceStar==5>
											非常满意
										<#elseif item.serviceStar==4>
											满意
										<#elseif item.serviceStar==3>
											一般
										<#elseif item.serviceStar==2>
											不满意
										<#else>
											非常满意
										</#if>
									</#if>
								</label>
								<label>主题服务: 
									<#if item.skillStar??>
										<#if item.skillStar==5>
											非常满意
										<#elseif item.skillStar==4>
											满意
										<#elseif item.skillStar==3>
											一般
										<#elseif item.skillStar==2>
											不满意
										<#else>
											非常满意
										</#if>
									</#if>
								</label>
							</div>
							<div class="div2">
								<p>${item.content!''}</p>
							</div>
							<div class="div4">${item.commentTime!''}</div>
							<#if item.reply??>
							<div class="div5">
								<label>天涯回复：<span>${item.reply!''}</span></label>
							</div>
							</#if>
						</div>
					</li>
				</#list>
				</#if>
			</ul>
			<p class="cllick_more"><a >点击加载更多&gt;&gt;</a></p>
		</div>
	</div>
	<!-- 旅友点评-结束 -->
</div>
<div class="h50"></div>
<script>
$(function(){
	//图片详情js
	$('.ad-gallery').adGallery();
});



</script>
<!-- ********************中间内容-结束******************** -->


<!-- ********************底部******************** -->
<#include "/site_mag/common_footer.ftl" />
<!-- ********************底部-结束******************** -->
</body>
</html>
