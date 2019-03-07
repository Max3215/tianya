<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>价目表</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>
<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/timePrice/list" id="form1">
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
</script>
<script>
       
    //创建价格表窗口
    function showDialogItem(id) {
        var giftDialog = $.dialog({
            id: 'giftDialogId',
            lock: true,
            max: false,
            min: false,
            title: "商品",
            content: 'url:/Verwalter/timePrice/edit?id='+id,
            width: 700,
            height: 300
        });
    }
    
    function showDialog() {
        var giftDialog = $.dialog({
            id: 'giftDialogId',
            lock: true,
            max: false,
            min: false,
            title: "商品",
            content: 'url:/Verwalter/timePrice/edit',
            width: 700,
            height: 300
        });
    }
</script>
    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a><span>价目表管理</span></a>
        <i class="arrow"></i>
        <span>价目表列表</span>
          
    </div>
    <!--/导航栏-->
    <!--工具栏-->
    <div class="toolbar-wrap">
        <div id="floatHead" class="toolbar">
            <div class="l-list">
                <ul class="icon-list">
                    <li><a class="add" href="javascript:showDialog();" ><i></i><span>新增</span></a></li>
                    <li>
                        <a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
                    </li>
                    <li>
                        <a onclick="return ExePostBack('btnDelete','删除后将无法恢复，是否继续？');" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a>
                    </li>
                </ul>
                <div class="menu-list">
                    <div class="rule-single-select">
                        <select name="categoryId" onchange="javascript:setTimeout(__doPostBack('categoryId', ''), 0)">
                            <option <#if categoryId??><#else>selected="selected"</#if> value="">所有类别</option>
                            <#if category_list??>
                                <#list category_list as c>
                                    <#if c.parentTree?contains("[1]") || c.parentTree?contains("[2]") || c.parentTree?contains("[5]")>
                                    <option value="${c.id?c}" <#if categoryId?? && c.id==categoryId>selected="selected"</#if> ><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
            </div>
            <div class="r-list">
                <input name="keywords" type="text" class="keyword">
                <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('btnSearch','')">查询</a>
            </div>
        </div>
    </div>
    <!--/工具栏-->
    <!--列表-->
    
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
<tbody>
    <tr class="odd_bg">
        <th width="8%">
            选择
        </th>
        <th align="left">
            商品ID
        </th>
        <th align="left" width="">
            商品名称
        </th>
        <th align="left" width="">
           起始时间
        </th>
        <th align="left" width="">
           截止时间
        </th>
        <th align="left" width="">
           成人价格
        </th>
        <th align="left" width="">
           儿童价格
        </th>
        <th width="12%">操作</th>            
    </tr>

    <#if timePrice_page??>
        <#list timePrice_page.content as tprice>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${tprice_index?c}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${tprice.id?c}">
                </td>
                <td>
                    <#if tprice.goodsId??>${tprice.goodsId?c}</#if>
                </td>
                <td>${tprice.goodsTitle!""}</td>
                <td><#if tprice.startTime??>${tprice.startTime?string('yyyy-MM-dd')}</#if></td>
                <td><#if tprice.endTime??>${tprice.endTime?string('yyyy-MM-dd')}</#if></td>
                <td><#if tprice.price??>${tprice.price?string('0.00')}</#if></td>
                <td><#if tprice.childPrice??>${tprice.childPrice?string('0.00')}</#if></td>
	            <td align="center">
	                <a href="javascript:showDialogItem(${tprice.id?c});" >编辑价格</a>
	            </td>                
            </tr>
        </#list>
    </#if>
</tbody>
</table>
        
<!--/列表-->
<!--内容底部-->
<#assign PAGE_DATA=timePrice_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>