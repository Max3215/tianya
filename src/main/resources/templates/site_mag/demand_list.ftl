<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>私家定制管理</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>

<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form name="form1" method="post" action="/Verwalter/setting/demand/list" id="form1">
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
  <span>定制信息</span>  
</div>
<!--/导航栏-->

<!--工具栏-->
<div class="toolbar-wrap">
  <div id="floatHead" class="toolbar" style="position: static; top: 42px;">
    <div class="l-list">
      <ul class="icon-list">
        <#--<li><a onclick="return ExePostBack('btnVerify','审核通过后将在前台显示，是否继续？');" id="btnVerify" class="save" href="javascript:__doPostBack('btnVerify','')"><i></i><span>审核</span></a></li>-->
        <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
        <li><a href="javascript:__doPostBack('export','')"><span>导出本页</span></a></li>
        <#if tdManagerRole?? && tdManagerRole.isSys>
            <li><a onclick="return ExePostBack('btnDelete');" id="btnDelete" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a></li>
        </#if>
      </ul>
       <div class="menu-list">
       <div class="rule-single-select single-select">
	        <select name="statusId" onchange="javascript:__doPostBack('statusId','')" style="display: none;">
	            <option <#if !statusId??>selected="selected"</#if> value="">定制类型</option>
	            <option <#if statusId?? && statusId==0>selected="selected"</#if> value="0">普通定制</option>
	            <option <#if statusId?? && statusId==1>selected="selected"</#if> value="1">邮轮定制</option>
	        </select>
        </div>
   
      </div>
    </div>
    <div class="r-list">
    <#--
      <input name="keywords" type="text" class="keyword" value="${keywords!""}">
      <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('btnSearch','')">查询</a>
    -->
    </div>
  </div>
</div>
<!--/工具栏-->

<!--列表-->

<table width="100%" border="3" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
  	<tr>
  		<th width="2%">选择</th>
  		<th width="4%">定制类型</th>
  		<th width="4%">会员名</th>
  		<th>目的地</th>
  		<th width="3%">出行人数</th>
  		<th width="4%">出发日期</th>
  		<th width="4%">返程日期</th>
  		<th width="4%">出发方式</th>
  		<th width="4%">酒店级别</th>
  		<th width="4%">预支付金额</th>
  		<th width="7%">备注</th>
  		<th width="4%">电话</th>
  		<th width="7%">邮箱</th>
  		<th width="7%">定制日期</th>
  		<th width="7%">操作</th>
  		<th width="7%">处理人</th>
  		<th width="7%">完成时间</th>
  	</tr>
  

    <#if demand_page??>
        <#list demand_page.content as demand>
            <tr <#if demand_index % 2 == 0>bgcolor="#e5ffff"</#if>>	
            	<td align="center">
	                <span class="checkall" style="vertical-align:middle;">
	                    <input id="listChkId" type="checkbox" name="listChkId" value="${demand_index}" >
	                </span>
	                <input type="hidden" name="listId" id="listId" value="${demand.id?c}">
                </td>
                <td align="center">
                <#--
                	<#if demand.statusId?? && demand.statusId==0>
                                                          普通定制
                    <#else>
                                                          游轮定制 	
                    </#if>
                 -->
                    ${demand.privateType!""}
                </td>
                <td align="center">${demand.name!""}</td>
                <td align="center">${demand.reachPort!''}</td>
                <td align="center">${demand.totalPeople!0}</td>
                <td align="center">${demand.groupSaleStartTime!''}</td>
                <td align="center">${demand.groupSaleStopTime!''}</td>
                <td align="center">${demand.way!''}</td>
                <td align="center">${demand.hotel!''}</td>
                <td align="center">${demand.money!''}</td>
                <td align="center">${demand.remark!''}</td>
                <td align="center">${demand.mobile!''}</td>
                <td align="center">${demand.email!''}</td>
                <td align="center">${demand.time!""}</td>
                <td align="center">
                	<#--状态操作-->
                	<#if demand.status?? && demand.status==1>
                		<a href="javascript:changeStatus('修改后状态变为已联系，是否继续？', '${demand.id?c}');"  class="button">${demand.statusStr!''}</a>
                	<#elseif demand.status?? && demand.status==2>
                		<a href="javascript:changeStatus('修改后状态变为已完成，是否继续？', '${demand.id?c}');"  class="button">${demand.statusStr!''}</a>
            		<#elseif demand.status?? && (demand.status==3 || demand.status==4)>
                		<a href="javascript:;"  class="button">${demand.statusStr!''}</a>
                	</#if>
                	<#--取消操作-->
                	
                	<#if demand.status??>
                		<#if demand.status != 4 && demand.status != 3>
                			|&nbsp;<a href="javascript:killDemand('修改后状态变为已取消，是否继续？', '${demand.id?c}');"  class="button">取消</a>
                		</#if>
                	</#if>
                </td>
                <td align="center">${demand.dealManager!""}</td>
                <td align="center">
					${demand.replyTime!""}            		
                </td>
            </tr>
        </#list>
    </#if>
     
</tbody>
</table>

<!--/列表-->

<!--内容底部-->
<#assign PAGE_DATA=demand_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body>
<script>
	function changeStatus(tip, data){
		$.dialog.confirm(tip, function(){
			window.location.href="/Verwalter/setting/demand/nextStatus?id="+data;
		});
	}
	function killDemand(tip, data){
		$.dialog.confirm(tip, function(){
			window.location.href="/Verwalter/setting/demand/endSatus?id="+data;
		});
	}
</script>
</html>