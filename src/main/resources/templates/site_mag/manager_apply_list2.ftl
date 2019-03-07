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
      <span style="font-size:100%;">申请管理</span>
    </div>
    <!--导航栏-->
    
    <!--内容-->
    	<div class="u" style="font-size:250%;"><center style="color: blue;"><h1><b>审核管理</b></h1></center></div>
    	<#if tdManager?? && tdManager.parentId != 0><#--此处设置超人-->
  			<button type="button" class="btn btn-info" onclick="addApply()">发起申请</button><br><br>
  		</#if>
  		
  		<#if type?? && (type=="passList_page" || type=="unpassList_page")>
	  		<div class="h" style="background-color: #ddd;"><center><label></label><b>我发出的</b></center></div><br/>
	  	<#else>
	  		<div style="background-color: #00FFFF;"><center><label class="glyphicon glyphicon-pushpin"></label><b>我审核的</b></center></div><br/>		
  		</#if>
  		
  		
  		
    	<div class="row">
    		<#if type?? && type=="unpassList_page">
    			<div class="col-md-12">
	  				<label class="glyphicon glyphicon-remove-circle"></label>未通过
	  			</div>
	  			<div class="col-md-12">
	  				<ul>
	  					<#if unpassList_page??>
	  						<#list unpassList_page.content as item><li><label>${item_index + 1}.</label><a href="/Verwalter/managerApply/detail?id=${item.id}">${item.title}『${item.applyTime}』</a>&nbsp;&nbsp;&nbsp;<a href="/Verwalter/managerApply/delete?id=${item.id}"><font color="red">删除</font></a></li></#list>
	  					</#if>
	      			</ul>
	  			</div>
    		<#elseif type?? && type=="passList_page">
    			<div class="col-md-12">
	  				<label class="glyphicon glyphicon-ok-circle"></label>已通过
	  			</div>
	  			<div class="col-md-12">
	  				<ul>
	  					<#if passList_page??>
	  						<#list passList_page.content as item><li><label>${item_index + 1}.</label><a href="/Verwalter/managerApply/detail?id=${item.id}">${item.title}『${item.applyTime}』</a></li></#list>
	  					</#if>
	      			</ul>
	  			</div>
    		<#elseif type?? && type=="myCheckedList_page">
    			<div class="col-md-12">
	  				<label class="glyphicon glyphicon-check"></label>已审核
	  			</div>
	  			<div class="col-md-12">
					<ul>
						<#if myCheckedList_page??>
	  						<#list myCheckedList_page.content as item><li><label>${item_index + 1}.</label><a href="/Verwalter/managerApply/detail?id=${item.id}">${item.title}『${item.applyTime}』</a></li></#list>
	  					</#if>
	      			</ul> 			
	  			</div>
    		<#elseif type?? && type=="myUncheckList_page">
    			<div class="col-md-12">
	  				<label class="glyphicon glyphicon-edit"></label>未审核
	  			</div>
	  			<div class="col-md-12">
					<ul>
						<#if myUncheckList_page??>
	  						<#list myUncheckList_page.content as item><li><label>${item_index + 1}.</label><a href="/Verwalter/managerApply/detail?id=${item.id!''}">${item.title}『${item.applyTime}』</a></li></#list>
	  					</#if>
	      			</ul> 			
	  			</div>
    		</#if>
    	</div>	
  			
    	
	<!--/内容-->
	<!--内容底部-->
		<#if type?? && type="unpassList_page">
			<#assign PAGE_DATA=unpassList_page />
		<#elseif type?? && type="passList_page">
			<#assign PAGE_DATA=passList_page />
		<#elseif type?? && type="myCheckedList_page">
			<#assign PAGE_DATA=myCheckedList_page />
		<#elseif type?? && type="myUncheckList_page">
			<#assign PAGE_DATA=myUncheckList_page />
		</#if>
		
		<div class="line20"></div>
        <div class="pagelist">
          <div class="l-btns">
            <span>显示</span><input name="size" type="text" value="${size!""}" onchange="javascript:setTimeout(__doPostBack('btnSize',''), 0)" onkeypress="if (WebForm_TextBoxKeyHandler(event) == false) return false;" class="pagenum" onkeydown="return checkNumber(event);"><span>条/页</span>
            <input name="page" type="hidden" value="<#if PAGE_DATA.number??>${PAGE_DATA.number?c}<#else>0</#if>" />
          </div>
          <div id="PageContent" class="default"><span>共<#if PAGE_DATA??>${PAGE_DATA.totalElements}</#if>数据,分<#if PAGE_DATA??>${PAGE_DATA.totalPages}</#if>页显示,当前为第<#if PAGE_DATA??>${PAGE_DATA.number+1}</#if>页</span>
                <#if PAGE_DATA??>
                    <#assign continueEnter=false>
                    <#if PAGE_DATA.number+1 == 1>
                        <span class="disabled">&lt;&lt;上一页</span>
                    <#else>
                        <a href="javascript:__doPostBack('btnPage','${(PAGE_DATA.number-1)?c}')" style="height:30px;">&lt;&lt;上一页</a>
                    </#if>
                    
                    <#if PAGE_DATA.totalPages gt 0>
                        <#list 1..PAGE_DATA.totalPages as page>
                            <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                                <#if page == PAGE_DATA.number+1>
                                    <span class="current">${page?c}</span>
                                <#else>
                                    <a href="javascript:__doPostBack('btnPage','${(page-1)?c}')" style="height:30px;">${page?c}</a>
                                </#if>
                                <#assign continueEnter=false>
                            <#else>
                                <#if !continueEnter>
                                    ...
                                    <#assign continueEnter=true>
                                </#if>
                            </#if>
                        </#list>
                    </#if>
                    
                    <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>
                        <span class="disabled">下一页&gt;&gt;</span>
                    <#else>
                        <a href="javascript:__doPostBack('btnPage','${(PAGE_DATA.number+1)?c}')" style="height:30px;">下一页&gt;&gt;</a>
                    </#if>
                </#if>
          </div>
        </div>
	<!--/内容底部-->
</form>


</body>
</html>