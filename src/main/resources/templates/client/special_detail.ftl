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
				<#--<label class="la1">商城价：<span>￥${goods.salePrice?string("0.00")} </span><#if goods?? && goods.categoryIdTree?contains("[31]")||goods.categoryIdTree?contains("[32]")> /斤<#elseif goods.categoryIdTree?contains("[33]")||goods.categoryIdTree?contains("[34]")> /盒或瓶<#else>/件</#if></label>-->
				<label class="la1">商城价：<span>￥${goods.salePrice?string("0.00")} </span>/${goods.shipCompany!""}</label>
			</p>
			<p class="p5">
				<label class="la1">种类：<span>${goods.categoryTitle!''}</span></label>
			</p><p class="p6">
				<label class="la1">产地：<span>${goods.leavePort!''}</span></label>
			</p>
			<form class="sele_time" >
				<input type="button" id="button1" value="收藏" class="saves">
				<a onclick="window.location='/order/${goods.id?c}'">立即预定</a>
			</form>
		</dd>
	</dl>
	<!-- 商品详情-结束 -->


	<div class="h20"></div>
	<!-- 轮船介绍 -->
	<div class="steamship">
		<ul id="steamship_ul">
			<li class="li1"><a href="#ta1">商品详情</a></li>
			<li><a href="#ta2">预定须知</a></li>
			<li><a href="#ta3">旅游点评</a></li>
		</ul>
	</div>
	<!-- 轮船介绍-结束 -->
	
	<!-- 商品详情 -->
	<div class="cost">
		<div class="cost_title"><a name="ta1">商品详情 </a></div>
		<div class="cost_div1">
			${goods.shipDescription!''}
		</div>
	</div>
	<!-- 商品详情-结束 -->
	<!-- 预定须知 -->
	<div class="predeter">
		<div class="predeter_title"><a name="ta2">预定须知</a></div>
		<div class="predeter_word">
			${goods.bookDescription!''}
		</div>
	</div>
	<!-- 预定须知-结束 -->
	<!-- 旅友点评 -->
	<div class="people_conment">
		<div class="peo_title"><a name="ta3">旅友点评</a></div >
		<div class="dd1">
			<div class="div1">
				<p class="p1"><span>99%</span>满意度</p>
				<p class="p2">来自<span><#if count??>${count!356}</#if></span>名天涯旅客的真实评价</p>
			</div>
			<div class="div2">
				<p class="p1">
					<label>商品娱乐（4.9）</label>
					<!--
					<a href="#" class="staryes"></a>
					<a href="#"></a>
					<a href="#"></a>
					<a href="#"></a>
					<a href="#"></a>
					-->
				</p>
				<p class="p2">
					<label>商品样式（4.9）</label>
				</p>
				<p class="p3">
					<label>商品服务（4.9）</label>
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
								<label>商品娱乐: 
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
								<label>商品样式: 
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
								<label>商品服务: 
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
<script>
$(function(){
	//图片详情js
	$('.ad-gallery').adGallery();
});



</script>
<!-- ********************中间内容-结束******************** -->


<!-- ********************底部******************** -->
<#include "/client/common_footer.ftl" />
<!-- ********************底部-结束******************** -->
</body>
</html>