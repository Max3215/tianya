<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)http://localhost:2105/site_mag/center.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理首页</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style>
.default a, div.default span {height: 30px;  }
.location span{font-size:25px; color:#red;}
.h {height:40px;}
.h b{position:relative;top:10px;}
.u b{color:#333;font-size:25px;}
</style>
<script>
	function addApply(){
		window.location.href="/Verwalter/managerApply/edit";
	}
</script>
</head>
<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/managerApply/list?type=${type!""}" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}">
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
</div>

<script type="text/javascript">
var theForm = document.forms['form1'];
    if (!theForm) {
        theForm = document.form1;
    }
    function __doPostBack(eventTarget, eventArgument) {
        if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
            theForm.__EVENTTARGET.value = eventTarget;
            theForm.__EVENTARGUMENT.value = eventArgument;
            theForm.submit();
        }
    }
</script>




    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span style="font-size:100%;">返回上一页</span></a>
      <a class="home"><i></i><span style="font-size:100%;">首页</span></a>
      <i class="arrow"></i>
      <span style="font-size:100%;">通知管理</span>
    </div>
    <!--导航栏-->
    
    <!--内容-->
    	<div class="u" style="font-size:250%;"><center style="color: blue;"><h1><b>${typeTilte!""}</b></h1></center></div>
    	<div class="row">
    		<div class="col-md-12">
  				<ul>
  					<#if notice_page2??>
  						<#list notice_page2.content as item><li><label>${item_index + 1}.</label><a href="/Verwalter/notice/detail?id=${item.id}"><#if item.content?? && item.content?length gt 10>${item.content?substring(0,10)}...<#else>${item.content!''}</#if>『${item.createTime}』</a></li></#list>
  					</#if>
      			</ul>
  			</div>
  		</div>
    		
  			
    	
	<!--/内容-->
	<!--内容底部-->
		<#assign PAGE_DATA=notice_page2 />
		<#include "/site_mag/list_footer.ftl" />
	<!--/内容底部-->
</form>


</body>
</html>