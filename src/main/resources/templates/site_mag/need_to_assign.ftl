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
  <span>员工管理</span>
  <i class="arrow"></i>
  <span>待签字(结算表)</span>  
</div>
<!--/导航栏-->

<!--工具栏-->
<!--/工具栏-->

<!--列表-->

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
  <tr class="odd_bg">
    <th align="center">编号</th>
    <th align="center">营业收入净额</th>
    <th align="center">制表人</th>
    <th align="center">操作</th>
  </tr>

    <#if need_assign_page??>
        <#list need_assign_page.content as item>
            <tr>
                <td align="center">${item.contactNum!""}</td>
                <td align="center">${item.pureIncome!""}</td>
                <td align="center">${item.lister!""}</td>
                <td align="center"><a href="/Verwalter/costBalance/edit?id=${item.id?c}&type=ass">查看</a></td>
            </tr>
        </#list>
    </#if>
</tbody>
</table>

<!--/列表-->

<!--内容底部-->
<#assign PAGE_DATA=need_assign_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>
</body></html>