<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>${head_title!""}</title>
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

<style type="text/css">
	.mymember_page{
		overflow: hidden;
	    display: inline-block;
	    margin-left: 4px;
	    width: 30px;
	    height: 30px;
	    font-size: 16px;
	    line-height: 30px;
	    color: #033a7a;
	    text-align: center;
	    vertical-align: top;
	    border: 1px solid #033a7a;
	    -webkit-transition: all .4s ease-in-out;
	    -moz-transition: all .4s ease-in-out;
	    -ms-transition: all .4s ease-in-out;
	    -o-transition: all .4s ease-in-out;
	    transition: all .4s ease-in-out;
	}

</style>

<script type=text/javascript>
	function dianzan(){
		var id = ${tdUserTravelNotes.id!c};
		$.ajax({
			type: "post",
            url: "/user/travel/praise2",               
            data: {"id": id},
            dataType: "json",
            success: function (data) { 
            <!-- 修改 -->
                if (data.code == 0){
                    window.location.reload();
                }    
                else if(data.code ==2){
                    alert(data.msg);
           		}else{
           			alert(data.msg);
           		}
           	}
		});
	}

</script>

<body>
<#include "/client/common_header.ftl" />



<!-- ********************中间内容******************** -->
<div class="h20"></div>
<div class="strategy_article" style="margin-top:50px;">
	<!-- 大标题 -->
	<div class="big_title">
		<img src="<#if tdUserTravelNotes??>${tdUserTravelNotes.imgUrl!'/client/images/nav1.png'}</#if>">
		<p>
			<label><#if tdUserTravelNotes??>${tdUserTravelNotes.title!''}</#if></label>
			<label class="la2">作者：<#if tdUserTravelNotes??>${tdUserTravelNotes.username!''}</#if></label>
		</p>
	</div>
	<!-- 大标题-结束 -->

	<div class="h20"></div>
	<!-- 文字左 -->
	<div class="art_left">
		<div class="art_word">
			<p>
				<#if tdUserTravelNotes??>${tdUserTravelNotes.content!'这篇文章作者没有些什么内容...'}</#if>
			</p>
		</div>
		<div class="h50"></div>
		<div class="nice">
			<a href="javascript:;" onclick="dianzan();"><img src="/client/images/heart.png"></a>
			<p>赞：<#if tdUserTravelNotes??>${tdUserTravelNotes.countPraise!0}</#if></p>
		</div>
		<!-- 文字左-点评 -->
		<form action="/user/travel/comment" method="post">
			<input type="hidden" name="id" <#if tdUserTravelNotes??>value="${tdUserTravelNotes.id}"</#if>>
			<input type="hidden" name="title" <#if tdUserTravelNotes??>value="${tdUserTravelNotes.title}"</#if>>
			<dl class="commont">
				<dt>驴友点评：</dt>
				<dd class="dd1"><textarea name="content"></textarea></dd>
				<dd class="dd2"><input type="submit" value="点评"></dd>
			</dl>
		</form>
		<!-- 文字左-点评-结束 -->
		<ul class="pcom">
			<#if travel_comment_page??>
				<#list travel_comment_page.content as comment>
					<li class="li1">
						<dl>
							<dt><a href="#"><img src="${comment.userHeadUri!''}"/></a></dt>
							<dd>
								<p class="p1">
									<a href="#">${comment.username!''}</a>  
									<label>${comment.commentTime!''}</label> 
								</p>
								<p>${comment.content!''}</p>
								<#if comment.reply??><p><label style="float: right;">管理员回复：${comment.reply!""}</label></p></#if>
							</dd>
						</dl>
					</li>
				</#list>
			</#if>
		</ul>
		<#if travel_comment_page??>
            <#assign continueEnter=false>
            <#if travel_comment_page.totalPages gt 0>
	            <div class="mymember_page" style="float:right;margin-right:50px">
	                <#list 1..travel_comment_page.totalPages as page>
	                    <#if page <= 3 || (travel_comment_page.totalPages-page) < 3 || (travel_comment_page.number+1-page)?abs<3 >
	                        <#if page == travel_comment_page.number+1>
	                            <a class="mysel" href="javascript:;">${page}</a>
	                        <#else>
	                            <a href="/user/travel/search?page=${page-1}">${page}</a>
	                        </#if>
	                        <#assign continueEnter=false>
	                    <#else>
	                        <#if !continueEnter>
	                            <b class="pn-break">&hellip;</b>
	                            <#assign continueEnter=true>
	                        </#if>
	                    </#if>
	                </#list>
	            </div>
            </#if>
         </#if>
		<div class="h50"></div>
	</div>


	<!-- 文字左-结束 -->


	<!-- 文字右 -->
	<div class="art_right">
		<div class="right_title">最新产品</div>
		<ul>
			<#if goods_list111??>
			<#list goods_list111 as item>
			<#if item_index &lt; 5>
				<li>
					<a href="/detail/${item.id?c}">
						<img src="${item.coverImageUri!''}">
						<div>
							<p class="p1">${item.title!''}</p>
							<p class="p2">￥${item.salePrice?string("0.00")}</p>
						</div>
					</a>
				</li>
			</#if>
			</#list>
			</#if>
		</ul>
	</div>
	<!-- 文字右-结束 -->

</div>
<div class="h50"></div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

</body>
</html>