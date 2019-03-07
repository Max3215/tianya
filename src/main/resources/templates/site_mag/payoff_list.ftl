<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>资产调节表</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>
<body class="mainbody">

<form name="form1" method="post" action="/Verwalter/payoff/list" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="">
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="">
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

function change1(){
	var type11 = $("#fenlei").val();
	var timeId = $("#timeId11").val();
	window.location.href="/Verwalter/assets/list/";
}

</script>

    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a><span>员工相关</span></a>
        <i class="arrow"></i>
            <span>结算表</span>
    </div>
    <!--/导航栏-->
    <!--工具栏-->
    <div class="toolbar-wrap">
        <div id="floatHead" class="toolbar">
            <div class="l-list">
                <ul class="icon-list">
                	<#--
                	<li>
                        <a class="add" href="/Verwalter/assets/edit" ><i></i><span>新增</span></a>
                    </li>
                    -->
                    <li>
                        <a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
                    </li>
                    <li>
                        <a onclick="return ExePostBack('btnDelete','删除后将无法恢复，是否继续？');" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除记录</span></a>
                    </li>
                    <li>
                        <a><span>收费人数：${totalPersonNum!'0'}</span></a>
                    </li>
                    <li>
                        <a><span>营业收入总额：${totalTotalIncome?string('0.00')}</span></a>
                    </li>
                    <li>
                        <a><span>支出合计：${totalTotalCost?string('0.00')}</span></a>
                    </li>
                    <li>
                        <a><span>营业收入净额：${totalPureIncome?string('0.00')}</span></a>
                    </li>
                    <#--
                    <li>
                    	<a class="all" href="javascript:__doPostBack('export','')"><span>导出本页</span></a>
                    </li>
                    -->
                </ul>
                   <#--
                   <div class="menu-list">
                    <div class="rule-single-select">
                        <select name="settle" id="fenlei" onchange="javascript:setTimeout(__doPostBack('settle',''), 0);">
                            <option value="" >结算情况</option>
                            <option value="yes" <#if isSettlement?? && isSettlement>selected="selected"</#if>>已结算</option>
                            <option value="no" <#if isSettlement?? && !isSettlement>selected="selected"</#if>>未结算</option>
                        </select>
                    </div>
                   </div>
                   -->
                    <ul class="icon-list">
                    <li>
                        <input name="start" type="text" value="<#if startime??>${startime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})">
                    </li>
                    <li><a>至</a></li>
                    <li>
                        <input name="end" type="text" value="<#if endTime??>${endTime?string('yyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})">
                    </li>
                </ul>
            </div>
            
            <div class="a-list" >
            	<#--
                <input name="keywords" type="text" class="keyword" value="<#if keywords??>${keywords!''}</#if>">
                -->
                <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('btnSearch','')">查询</a>
            </div>
        </div>
    </div>
    <!--/工具栏-->
    <!--列表-->
    
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
<tbody>
    <tr class="odd_bg">
        <th width="5%">
            选择
        </th>
        <th align="left">
           团队名称
        </th>
         <th align="left">
            合同编号
        </th>
        <th align="left"  >
            结算时间
        </th>
        <th align="left" >
            收费人数
        </th>
        <th align="left" >
            营业收入总额
        </th>
        <th align="left" >
            合计
        </th>
        <th align="left" >
            营业收入净额
        </th>
        <th align="left" >
            制表人
        </th>
        <th align="left">
            创建时间
        </th>
        <th align="left">
            记录编号
        </th>
        <th >
            操作
        </th>   
    </tr>

    <#if payoff_page??>
        <#list payoff_page.content as item>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${item_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${item.id?c}">
                </td>
                <td>
                    <a href="/Verwalter/costBalance/edit?id=${item.id?c}">${item.teamName!""}</a></td>
                <td>
                	<#if item.contactNum??>${item.contactNum!''}</#if>
                </td>
                <td >${item.fillYear!''}年${item.fillMonth!''}月${item.fillDay!''}</td>
                <td><#if item.personNum??>${item.personNum!"0"}</#if></td>
                <td><#if item.totalIncome??>${item.totalIncome?string('0.00')}</#if></td>
                <td><#if item.totalCost??>${item.totalCost?string('0.00')}</#if></td>
                <td><#if item.pureIncome??>${item.pureIncome?string('0.00')}</#if></td>
                <td><#if item.lister??>${item.lister!''}</#if></td>
                <td>
                	<#if item.createTime??>${item.createTime?string('yyyy-MM-dd')}</#if>
                </td>
                <td>
                	<#if item.costNumber??>${item.costNumber!''}</#if>
                </td>
                <td >
                    <a href="/Verwalter/costBalance/edit?id=${item.id?c}">修改</a>
                </td>
            </tr>
        </#list>
    </#if>
</tbody>
</table>
        
<!--/列表-->
<!--内容底部-->
<#assign PAGE_DATA=payoff_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>
