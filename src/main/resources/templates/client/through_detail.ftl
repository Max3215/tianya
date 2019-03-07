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
<script type=text/javascript src="/mag/js/WdatePicker.js"></script>

<link rel="stylesheet" type="text/css" href="/client/css/jquery.ad-gallery.css">
<script type="text/javascript" src="/client/js/jquery.ad-gallery.js"></script>
<script>
$(function(){
	//图片详情js
	$('.ad-gallery').adGallery();
});


<!-- 
function ajaxtime(){
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
 
-->
	
		
pickerEvent.setPriceArr(eval(d1));
		pickerEvent.Init("calendar");


}
</script>

<script type="text/javascript">
$(document).ready(function(){
	$("#button1").click(function(){
		$.ajax({
			type: "post",
            url: "/user/collect",               
            data: {"goodsId": ${goods.id!c}},
            dataType: "json",
            success: function (data) { 
                if (data.code == 0){
                	alert("收藏成功！");
                    window.location.reload();
                }else if(data.code == 3){
                	alert("请先登录！");
                	window.location.href="/login";
                }else{
                	alert(data.msg);
                	window.location.reload();
                }     
            }
		});
	});
});
$(document).ready(function(){
    $("#1").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").removeClass("staryes");
    	$("#3").removeClass("staryes");
    	$("#4").removeClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("非常不满");
    	$("#id2").attr("value",1);
    	
    	
    });
    $("#2").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").removeClass("staryes");
    	$("#4").removeClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("不满意");
    	$("#id2").attr("value",2);
    });
    $("#3").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").addClass("staryes");
    	$("#4").removeClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("一般");
    	$("#id2").attr("value",3);
    });
    $("#4").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").addClass("staryes");
    	$("#4").addClass("staryes");
    	$("#5").removeClass("staryes");
    	$("#sp").html("满意");
    	$("#id2").attr("value",4);
    });
    $("#5").click(function(){
    	$("#1").addClass("staryes");
    	$("#2").addClass("staryes");
    	$("#3").addClass("staryes");
    	$("#4").addClass("staryes");
    	$("#5").addClass("staryes");
    	$("#sp").html("非常满意");
    	$("#id2").attr("value",5);
    });
    $("#11").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").removeClass("staryes");
    	$("#33").removeClass("staryes");
    	$("#44").removeClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("非常不满");
    	$("#id3").attr("value",1);
    });
    $("#22").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").removeClass("staryes");
    	$("#44").removeClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("不满意");
    	$("#id3").attr("value",2);
    });
    $("#33").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").addClass("staryes");
    	$("#44").removeClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("一般");
    	$("#id3").attr("value",3);
    });
    $("#44").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").addClass("staryes");
    	$("#44").addClass("staryes");
    	$("#55").removeClass("staryes");
    	$("#kf").html("满意");
    	$("#id3").attr("value",4);
    });
    $("#55").click(function(){
    	$("#11").addClass("staryes");
    	$("#22").addClass("staryes");
    	$("#33").addClass("staryes");
    	$("#44").addClass("staryes");
    	$("#55").addClass("staryes");
    	$("#kf").html("非常满意");
    	$("#id3").attr("value",5);
    });
    $("#111").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").removeClass("staryes");
    	$("#333").removeClass("staryes");
    	$("#444").removeClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("非常不满");
    	$("#id4").attr("value",1);
    });
    $("#222").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").removeClass("staryes");
    	$("#444").removeClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("不满意");
    	$("#id4").attr("value",2);
    });
    $("#333").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").addClass("staryes");
    	$("#444").removeClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("一般");
    	$("#id4").attr("value",3);
    });
    $("#444").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").addClass("staryes");
    	$("#444").addClass("staryes");
    	$("#555").removeClass("staryes");
    	$("#wl").html("满意");
    	$("#id4").attr("value",4);
    });
    $("#555").click(function(){
    	$("#111").addClass("staryes");
    	$("#222").addClass("staryes");
    	$("#333").addClass("staryes");
    	$("#444").addClass("staryes");
    	$("#555").addClass("staryes");
    	$("#wl").html("非常满意");
    	$("#id4").attr("value",5);
    });
    
    $("#appraise").click(function(){
    	var mingchen = $("#mingchen").html();
    	$("#mingchen2").html(mingchen);
    })
});

function chuan(id){
	var mingchen = $("#mingchen").html();
    $("#mingchen2").html(mingchen);
	$("#id1").attr("value",id);
	$("#now_valuetion").css("display","block");
}

</script>


<body>
<form class="now_valuetion" action="/user/goods/comment" method="post" id="now_valuetion">
	<input type="hidden" name="id" id="id1"/>
	<input type="hidden" name="goodsStar" id="id2"/>
	<input type="hidden" name="serviceStar" id="id3"/>
	<input type="hidden" name="skillStar" id="id4"/>
	<input type="hidden" name="type" value="${code!''}"/>

	<dl>
		<dt>商品信息<a class="closei" id="closei">关闭 X</a></dt>
		<dd class="dd1" ></dd>
		<dd class="dd2">
			<img src="${goods.coverImageUri!''}">
			<label  id="mingchen2"></label>
		</dd>
		<dd class="dd3">评分：</dd>
		<dd class="dd4">
			<label>产品描述：</label>
			<a id="1"></a>
			<a id="2"></a>
			<a id="3"></a>
			<a id="4"></a>
			<a id="5"></a>
			<label class="la2"  id="sp"></label>
		</dd>
		<dd class="dd5">
			<label>客服服务：</label>
			<a id="11"></a>
			<a id="22"></a>
			<a id="33"></a>
			<a id="44"></a>
			<a id="55"></a>
			<label class="la2"  id="kf"></label>
		</dd>
		<dd class="dd6">
			<label>交通服务：</label>
			<a id="111"></a>
			<a id="222"></a>
			<a id="333"></a>
			<a id="444"></a>
			<a id="555"></a>
			<label class="la2" id="wl"></label>
		</dd>
		<dd class="dd7">评价：</dd>
		<dd class="dd8">
			<textarea name="content" id="content" cols="30" rows="10"></textarea>
		</dd>
		<dd class="dd9"><input type="submit" value="评价"></dd>
	</dl>
</form>
<!-- ********************头部******************** -->
<#include "/client/common_header.ftl" />
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
			<p class="p1" id="mingchen">${goods.title!''}</p>
			<p class="p2">${goods.subTitle!''}</p>
			<p class="p3">
				<label class="la1">网上支付：<span><#if goods.salePrice??>${goods.salePrice?string("0.00")}</#if></span>起</label>
				<a href="#"></a>
			</p>
			<p class="p4">
				<label class="la1">出发港口：<span>${goods.leavePort!''}</span></label>
				<label class="la2">到达港口：<span>${goods.reachPort!''}</span></label>
			</p>
			<p class="p5">
				<label class="la1">途径港口：<span>${goods.passPort!''}</span></label>
			</p><p class="p6">
				<label class="la1">邮轮公司：<span>${goods.shipCompany!''}</span></label>
			</p>
			<form class="sele_time" >
				<label>出发时间：</label>
				<center>
					<input type="text" id="calendar" name="calendar" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"/>
				</center>
				<input type="button" id="button1" value="收藏" class="saves">
				<a onclick="window.location='/order/${goods.id?c}?date='+document.querySelector('#calendar').value;">立即订票</a>
			</form>
		</dd>
	</dl>
	<!-- 商品详情-结束 -->


	<div class="h20"></div>
	<!-- 轮船介绍 -->
	<div class="steamship">
		<ul id="steamship_ul">
			<li class="li1"><a href="#ta1">直通车介绍</a></li>
			<li><a href="#ta2">行程介绍</a></li>
			<li><a href="#ta3">费用说明</a></li>
			<li><a href="#ta4">签证/签注</a></li>
			<li><a href="#ta5">预定须知</a></li>
			<li><a href="#ta6">旅友点评</a></li>
		</ul>
		<p class="ship_title"><a name="ta1">MSC华丽号</a></p>
		<p class="ship_word">
			${goods.shipDescription!''}
		</p>
	</div>
	<!-- 轮船介绍-结束 -->
	<!-- 行程介绍 -->
	<div class="travel_introduce">
		<div class="tr_intro_title"><a 	name="ta2">行程介绍</a></div>
		<div class="dd1"><img src="/client/images/tokyo.png"></div>
		<div class="dd2">
			<div class="day_title">
				<p>天数</p>
				<p>停靠港口</p>
				<p>到港时间</p>
				<p>离岗时间</p>
			</div>
			<ul class="travel_day">
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
				<li>
					<p>第一天</p>
					<p>上海</p>
					<p>08:00</p>
					<p>10:30</p>
				</li>
			</ul>
			<div class="think"><span>*</span> 以上时间仅供参考，具体请以出团通知为准</div>
		</div>
		<div class="dd3">
			<ul class="dd3_left">
				<li class="li1"><a href="#tday1">第一天</a></li>
				<li><a href="#tday2">第二天</a></li>
				<li><a href="#tday3">第三天</a></li>
				<li><a href="#tday4">第四天</a></li>
				<li><a href="#tday5">第五天</a></li>
				<li><a href="#tday6">第六天</a></li>
				<li><a href="#tday7">第七天</a></li>
				<li><a href="#tday8">第八天</a></li>
			</ul>
			<ul class="dd3_right">
				<li>
					<img src="/client/images/circle.png" class="photo">
					<p class="p1"><a name="tday1">Day 1: 上海出发</a></p>
					<p class="p2">客人在指定时间到指定地点场集合办理登记、出境。搭乘专线大巴前往上海港口，08:00从上海出发。</p>
					<div class="meal">
						<p>用餐：早餐（自理） 午餐（包含） 晚餐（包含）</p>
						<p>住宿：华丽号</p>
					</div>
				</li>
				<li>
					<img src="/client/images/circle.png" class="photo">
					<p class="p1"><a name="tday3">Day 3: 釜山一日游</a></p>
					<p class="p2">
						上抵达凯恩斯，之后前往库兰达热带雨林自然公园，您可在热带雨林公园内，享用具北昆士兰风情的烧烤牛排自助餐，然后搭乘二战时期所向披靡的“水陆两栖车”，穿梭在古老的热带雨林内，导游风趣幽默的讲解，一一解读雨林的神秘莫测。前往热带雨林内的圆形剧场，观看精彩的帕玛吉利土著歌舞表演。之后的梦幻行土著人还会教您如何投掷飞镖，扔飞去来器，吹奏迪吉里杜管。之后前往艺术氛围浓厚的【库兰达热带雨林小镇】。库兰达小镇处在风景非常优美的热带雨林之中，从60年代起，这里即被推崇为另类生活的集散中心，此处更孕育了许多知名的土著艺术家。老建筑物改造的艺术餐厅，咖啡馆和酒吧遍布小镇，代表了当地人悠闲的生活方式，小镇内各种独特的手工艺品、画廊更是库伦达小镇远近驰名的主因，这里聚集了很多才华横溢的艺术人士，您可以欣赏到画家，陶瓷家，摄影师，及雕刻家的艺术作品。
					</p>
					<div class="pic">
						<img src="/client/images/pic1.png">
						<img src="/client/images/pic1.png">
						<img src="/client/images/pic1.png">
					</div>
					<div class="meal">
						<p>用餐：早餐（自理） 午餐（包含） 晚餐（包含）</p>
						<p>住宿：华丽号</p>
					</div>
				</li>
				<li>
					<img src="/client/images/circle.png" class="photo">
					<p class="p1"><a name="tday4">Day 4: 上海出发</a></p>
					<p class="p2">
						上抵达凯恩斯，之后前往库兰达热带雨林自然公园，您可在热带雨林公园内，享用具北昆士兰风情的烧烤牛排自助餐，然后搭乘二战时期所向披靡的“水陆两栖车”，穿梭在古老的热带雨林内，导游风趣幽默的讲解，一一解读雨林的神秘莫测。前往热带雨林内的圆形剧场，观看精彩的帕玛吉利土著歌舞表演。之后的梦幻行土著人还会教您如何投掷飞镖，扔飞去来器，吹奏迪吉里杜管。之后前往艺术氛围浓厚的【库兰达热带雨林小镇】。库兰达小镇处在风景非常优美的热带雨林之中，从60年代起，这里即被推崇为另类生活的集散中心，此处更孕育了许多知名的土著艺术家。老建筑物改造的艺术餐厅，咖啡馆和酒吧遍布小镇，代表了当地人悠闲的生活方式，小镇内各种独特的手工艺品、画廊更是库伦达小镇远近驰名的主因，这里聚集了很多才华横溢的艺术人士，您可以欣赏到画家，陶瓷家，摄影师，及雕刻家的艺术作品。
					</p>
					<div class="meal">
						<p>用餐：早餐（自理） 午餐（包含） 晚餐（包含）</p>
						<p>住宿：华丽号</p>
					</div>
				</li>
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
					<!--
					<a href="#" class="staryes"></a>
					<a href="#"></a>
					<a href="#"></a>
					<a href="#"></a>
					<a href="#"></a>
					-->
				</p>
				<p class="p2">
					<label>邮轮娱乐（4.9）</label>
				</p>
				<p class="p3">
					<label>邮轮服务（4.9）</label>
				</p>
			</div>
			<div class="div3"><input type="button" value="立即评价" onclick="chuan(${goods.id!c});" title='评价'></div>
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
								<label>直通车娱乐: 
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
								<label>直通车服务: 
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
			<p class="cllick_more"><a id="cllick_morea">点击加载更多&gt;&gt;</a></p>
		</div>
	</div>
	<!-- 旅友点评-结束 -->
</div>
<div class="h50"></div>
<!-- ********************中间内容-结束******************** -->


<!-- ********************底部******************** -->
<#include "/client/common_footer.ftl" />
<!-- ********************底部-结束******************** -->
</body>
</html>