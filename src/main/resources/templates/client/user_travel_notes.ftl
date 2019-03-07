<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>旅行感受</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>

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

</style>

<script type="text/javascript">

</script>

<body>

<#include "/client/common_header.ftl" />


<!-- ********************中间内容******************** -->
<div class="percenter_com my_travelnote">
	
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">旅行感受</div>
		<div class="find">
			<form action="/user/notes/search?cate=0" method="POST">
				<div class="find1">
					<label>标题：</label>
					<input id="title" name="title" type="text" <#if title??>value="${title!''}"</#if>>
				</div>
				<div class="find2">
					<label>分类：</label>
					<input id="classify" name="classify" type="text" <#if classify??>value="${classify!''}"</#if>>
				</div>
				<div class="find3"><input id="submit1" type="submit" value="查询" style="background-color: #ff8000;"></div>
			</form>
			<div class="find4"><a href="/user/travel/write" style="background-color: #ff8000;">发表旅行感受</a></div>
		</div>
		<div class="travel_note">
			<div class="travelnote_title">
				<div class="t1">旅行感受标题</div>
				<div class="t2">旅行感受状态</div>
				<div class="t3">所属分类</div>
				<div class="t4">操作</div>
			</div>
			<ul>
				<#if user_travel_notes_page??>
					<#list user_travel_notes_page.content as notes>
						<li>
							<div class="tli1">《${notes.title!''}》</div>
							<div class="tli2">
								<label class="la1">评论（<span>${notes.countComment!0}</span>）</label>
								<label>赞（<span>${notes.countPraise!0}</span>）</label>
							</div>
							<div class="tli3">${notes.classify!''}</div>
							<div class="tli4">
								<a href="/user/travel/search?id=${notes.id!c}">查看</a> |
								<a href="/user/travel/write?id=${notes.id!c}">编辑</a> |
								<a href="/user/notes/delete?id=${notes.id!c}">删除</a> 
							</div>
						</li>
					</#list>
				</#if>
			</ul>
			
                <#if user_travel_notes_page??>
                <#assign continueEnter=false>
                <#if user_travel_notes_page.totalPages gt 0>
                   	<ul class="mymember_page">
                    <#list 1..user_travel_notes_page.totalPages as page>
                        <#if page <= 3 || (user_travel_notes_page.totalPages-page) < 3 || (user_travel_notes_page.number+1-page)?abs<3 >
                            <#if page == user_travel_notes_page.number+1>
	                            <dd>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </dd>
                            <#else>
	                            <dd>
	                                <a href="/user/travel/notes?page=${page-1}<#if title??>&title=${title!''}</#if><#if classify??>&classify=${classify!''}</#if>">${page}</a>
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
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>