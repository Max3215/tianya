<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>活动管理</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">



<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>
</head>

<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form name="form1" method="post" action="/Verwalter/bargain/participant/list" id="form1">
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
    
 // 修改当前金额
        function EditCurrentPrice(id) {
            var pop = $.dialog.prompt2('修改当前金额', '备注',
            function (val, info) {
                if (!checkIsMoney(val)) {
                    $.dialog.alert('对不起，请输入正确的金额！', function () { }, pop);
                    return false;
                }
                
                if (undefined == info || "" == info){
                    $.dialog.alert('请输入备注信息！', function () { }, pop);
                    return false;
                }
                               
                var postData = { "id": id, "type": "editCurrentPrice", "data": val, "info": info };
                //发送AJAX请求
                sendAjaxUrl(pop, postData, "/Verwalter/bargain/participant/edit");
                return false;
            },
            $.trim($("#spanRealAmountValue").text())
        );
        }
        
        //检查是否货币格式
        function checkIsMoney(val) {
            var regtxt = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
            if (!regtxt.test(val)) {
                return false;
            }
            return true;
        }
        
  //发送AJAX请求
        function sendAjaxUrl(winObj, postData, sendUrl) {
            $.ajax({
                type: "post",
                url: sendUrl,
                data: postData,
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.dialog.alert('尝试发送失败，错误信息：' + errorThrown, function () { }, winObj);
                },
                success: function (data) {
                    if (data.code == 0) {
                        winObj.close();
                        $.dialog.tips(data.msg, 2, '32X32/succ.png', function () { location.reload(); }); //刷新页面
                    } else {
                        $.dialog.alert('错误提示：' + data.message, function () { }, winObj);
                    }
                }
            });
        }
</script>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>活动管理</span>
  <i class="arrow"></i>
  <span>参与者记录</span>  
</div>
<!--/导航栏-->

<!--工具栏-->
<div class="toolbar-wrap">
  <div id="floatHead" class="toolbar" style="position: static; top: 42px;">
    <div class="l-list">
      <ul class="icon-list">       
        <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
        <#if tdManagerRole?? && tdManagerRole.isSys>
            <li><a onclick="return ExePostBack('btnDelete');" id="btnDelete" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a></li>
        </#if>
      </ul>
      <div class="menu-list">
   <#--     <div class="rule-single-select single-select">
       
        <select name="statusId" onchange="javascript:setTimeout(__doPostBack('statusId',''), 0)" style="display: none;">
            <option <#if !statusId??>selected="selected"</#if> value="">所有状态</option>
            <option <#if statusId?? && statusId==0>selected="selected"</#if> value="0">待审核</option>
            <option <#if statusId?? && statusId==1>selected="selected"</#if> value="1">已审核</option>
        </select>
        
        </div>
    -->
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

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
	<tr class="odd_bg">
	    <th width="8%">选择</th>
	    <th align="left" width="15%">姓名</th>
	    <th align="left" width="15%">电话</th>
	    <th width="8%">当前价格</th>
	    <th align="center">自砍价格</th>
	    <th align="left" width="12%">购买价格</th>	    
    </tr>
    <#if participant_page??>
        <#list participant_page.content as item>
           <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input type="checkbox" name="listChkId" value="${item_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${item.id?c}">
                </td>
                <td>
                	<div class="user-box">
		                <h4><b>${item.trueName!''}</b></h4>		                
		                <span>
		                  <a class="point" href="javascript:EditCurrentPrice(${item.id?c});" title="修改当前金额">修改当前金额</a>		               
		                </span>
		              </div>
                </td>              
                <td>${item.mobile!''}</td>
                <td ><#if item.currentPrice??>${item.currentPrice?string("0.00")}</#if></td>
                <td align="center"><#if item.cutPrice??>${item.cutPrice?string("0.00")}</#if></td>
                <td><#if item.getPrice??>${item.getPrice?string("0.00")}</#if></td>              
            </tr>
        </#list>
    </#if>
     
</tbody>
</table>

<!--/列表-->

<!--内容底部-->
<#assign PAGE_DATA=participant_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>