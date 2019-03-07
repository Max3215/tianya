<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)http://localhost:2105/site_mag/center.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核管理</title>

<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<style>
.q p{font-size:25px;text-align:center;margin-top:20px;}
.w p{margin-top:8px;}
.r{padding:0 10px 0 10px;}
.r li {color:#666; margin-top:5px;}
.col-md-6s{padding:0 10 0 10px;}
.t li {color:#666; margin-top:5px;}
.col-md-6 span{font-size:14px; }
</style>
<script>
	function addNotice(){
		window.location.href="/Verwalter/notice/edit";
	}
</script>
<style>
		.all{width:1700px;height:100%;background-color:ddd;margin-top:20px;}
		.t{text-align:center;}
		.all a{text-decoration: none;color:#666;}
		.all a:hover{color:#eb5e0c;}
		.t{font-size:25px;}
		.fqsq{width:70px;height:30px;}
		.fqsq a{color:white;text-decoration: none;}
		.wfcd{background-color:#ddd;width:1700px;height:40px;}
		.wfcd p{position:relative;top:10px;text-align:center;}
		.ytg p{position:relative;top:4px;left:5px;}
		.z a{margin-left:245px;}
		.c a{margin-left:245px;}
		.r p{line-height:25px;margin-top:10px;margin-left:-8px;}
		.ytg{float:left;background-color:#eff0f4;height:30px;width:380px;border: 1px solid #dcdcdc;margin-top:20px;margin-left:300px;}	
		.wtg p{position:relative;top:4px;left:5px;}
		.x a{margin-left:245px; }
		.l p{line-height:25px;margin-top:10px;}
		.u p{margin-top:10px;}
		.y p{margin-top:10px;}
		.wtg{float:right;background-color:#eff0f4;height:30px;width:380px;border: 1px solid #dcdcdc;margin-top:20px;margin-right:300px;}
		.wshd{background-color:#ddd;width:1700px;height:40px;margin-top:250px;}
		.wshd p{position:relative;top:10px;text-align:center;}
		.b a{margin-left:265px;}
		.j {background:url(an.png);width:96px;height:36px;border:none; }	
	</style>
</head>

<body class="mainbody">

<form name="form1" method="post" action="" id="form1">
    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
      <a class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>通知管理</span>
    </div>
    <!--/导航栏-->
    
    <#if headNews?? && headNews.title?? && headNews.title?length lt 15>   
					<a href="/touch/news/detail?id=${headNews.id!''}"/>${headNews.title!''}</a>
				<#else>
					<#if headNews?? && headNews.title??>
						<a href="/touch/news/detail?id=${headNews.id!''}"/>${headNews.title?substring(0,15)}...</a>
					</#if>
				</#if>
    
    
    <div class="all">
			<div class="t"><p>通知管理</p></div>
			<div class="fqsq">
				<#if isLowestManager?? && isLowestManager=="no">
  					<button type="button" class="btn btn-info" style="position:relative;top:-10px;" onclick="addNotice()">发通知</button><br><br>
  				</#if>
			</div>
			<div class="ytg">
				<div class="z"><p><span>我发出的</span> <a href="/Verwalter/notice/list?type=out">[更多...]</a></p></div>
				<div class="r">
				<p><#if notice_out_page??><#list notice_out_page.content as item><#if item_index lt 10>${item_index + 1}.<a href="/Verwalter/notice/detail?id=${item.id}"><#if item.content?? && item.content?length gt 10>${item.content?substring(0,10)}...<#else>${item.content!''}</#if> 『${item.createTime}』</a><br/></#if></#list></#if></p>			
				</div>
			</div>
			
			<div class="wtg">
				<div class="x"><p><span>我收到的</span> <a href="/Verwalter/notice/list?type=in">[更多...]</a></p></div>
				<div class="l">
					<p>
						<#if notice_in_page??>
	  						<#list notice_in_page.content as item><#if item_index lt 5>${item_index + 1}.<a href="/Verwalter/notice/detail?id=${item.id}"><#if item.content?? && item.content?length gt 10>${item.content?substring(0,10)}...<#else>${item.content!''}</#if>『${item.createTime}』</a><br/></#if></#list>
	  					</#if>
					</p>			
				</div>
			</div>
	</div>    	
	<!--/内容-->
</form>


</body>
</html>