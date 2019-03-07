<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>游客定制管理</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>

<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/setting/reserveCar/list" id="form1">
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
<div class="location" style="position: static; top: 0px;">
  <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>系统管理</span>
  <i class="arrow"></i>
  <span>约租列表</span>  
</div>
<!--/导航栏-->

<!--工具栏-->
<div class="toolbar-wrap">
  <div id="floatHead" class="toolbar" style="position: static; top: 42px;">
    <div class="l-list">
      <ul class="icon-list">
      	<li><a class="all" href="javascript:__doPostBack('export','')"><span>导出本页</span></a></li>
        <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
        <#if tdManagerRole?? && tdManagerRole.isSys>
            <li><a onclick="return ExePostBack('btnDelete');" id="btnDelete" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a></li>
        </#if>
      </ul>
    </div>
  </div>
</div>
<!--/工具栏-->

<!--列表-->

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
  <tr class="odd_bg">
    <th width="2%">选择</th>
    <th width="5%">姓名</th>
    <th align="center" width="10%">电话</th>
    <th align="center" width="15%">取车时间</th>
    <th align="center">取车地点</th>
    <th align="center" width="15%">还车时间</th>
    <th align="center" width="8%">车型</th>
    <th align="center" width="7%">操作</th>
    <th align="center" width="7%">处理人</th>
  </tr>

    <#if reserve_car_page??>
        <#list reserve_car_page.content as item>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${item_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${item.id?c}">
                </td>
                <td align="center">${item.name!""}</td>
                <td align="center">${item.phone!""}</td>
                <td align="center">${item.takeCarTime!""}</td>
                <td align="center">${item.getCarPlace!""}</td>
                <td align="center">${item.backCarTime!""}</td>
                <td align="center">${item.backCarPlace!""}</td>
                <td align="center">
                	<#--状态操作-->
                	<#if item.status?? && item.status==1>
                		<a href="javascript:changeStatus('修改后状态变为已联系，是否继续？', '${item.id?c}');"  class="button">${item.statusStr!''}</a>
                	<#elseif item.status?? && item.status==2>
                		<a href="javascript:changeStatus('修改后状态变为已完成，是否继续？', '${item.id?c}');"  class="button">${item.statusStr!''}</a>
            		<#elseif item.status?? && (item.status==3 || item.status==4)>
                		<a href="#"  class="button">${item.statusStr!''}</a>
                	</#if>
                	<#--取消操作-->
                	
                	<#if item.status??>
                		<#if item.status != 4 && item.status != 3>
                			|&nbsp;<a href="javascript:killDemand('修改后状态变为已取消，是否继续？', '${item.id?c}');"  class="button">取消</a>
                		</#if>
                	</#if>
                </td>
                <td align="center">${item.dealManager!""}</td>
            </tr>
        </#list>
    </#if>
</tbody>
</table>
<script>
	function changeStatus(tip, data){
		$.dialog.confirm(tip, function(){
			window.location.href="/Verwalter/setting/reserveCar/nextStatus?id="+data;
		});
	}
	function killDemand(tip, data){
		$.dialog.confirm(tip, function(){
			window.location.href="/Verwalter/setting/reserveCar/endSatus?id="+data;
		});
	}
</script>

<!--/列表-->

<!--内容底部-->
<#assign PAGE_DATA=reserve_car_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>
</body></html>