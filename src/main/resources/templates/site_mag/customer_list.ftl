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

<form name="form1" method="post" action="/Verwalter/customer/list" id="form1">
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
	window.location.href="/Verwalter/customer/list/";
}

$(document).ready(function(){
	$("#smsSend").click(function () { smsSend(); }); // 选择发送短信
});

function smsSend() {
          var dialog = $.dialog({
                title: '编辑短信',
                content: '<textarea id="message" name="txtOrderRemark" rows="2" cols="20" class="input"></textarea>',
                min: false,
                max: false,
                lock: true,
                ok: function () {
                    var message = $("#message", parent.document).val();
                    
                    if (message == "") {
                        $.dialog.alert('对不起，请输入短信内容！', function () { }, dialog);
                        return false;
                    }
                    
                    var listChkId = "";
                    var inputs = document.getElementsByName('listChkId');//获取所有的input标签对象。
                    for(var i=0;i<inputs.length;i++){
                        var obj = inputs[i];
                        if(obj.type=='checkbox'){
                            if(obj.checked==true){
                                var aa = obj.value;
                                
                                listChkId += aa;
                                if(i+1<inputs.length)
                                {
                                    listChkId +=",";
                                }
                             }
                          } 
                      }
                      
                      var listId = "";
                      var listIds = document.getElementsByName('listId');//获取所有的input标签对象。
                      for(var i=0;i<listIds.length;i++){
                           var bb = listIds[i].value;
                             listId += bb;
                             if(i+1<listIds.length)
                             {
                                 listId +=",";
                             }
                      }
                    
                    if("" == listChkId) {
                        $.dialog.alert('对不起，请选择发送账号！', function () { }, dialog);
                        return false;
                    }
                    
                    var postData = {"listId": listId, "listChkId": listChkId, "message": message};
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/customer/smsSend");
                    return false;
                },
                cancel: true
          });
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
                        $.dialog.alert('错误提示：' + data.msg, function () { }, winObj);
                    }
                }
            });
        }



</script>

    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a><span>资产调节表</span></a>
        <i class="arrow"></i>
            <span>资产调节列表</span>
    </div>
    <!--/导航栏-->
    <!--工具栏-->
    <div class="toolbar-wrap">
        <div id="floatHead" class="toolbar">
            <div class="l-list">
                <ul class="icon-list">
                	<li>
                        <a class="add" href="/Verwalter/customer/edit" ><i></i><span>新增</span></a>
                    </li>
                    <li>
                        <a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
                    </li>
                    <#if tdManagerRole?? && tdManagerRole.isSys>
                    <li>
                        <a onclick="return ExePostBack('btnDelete','删除后订单将无法恢复，是否继续？');" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除记录</span></a>
                    </li>
                    </#if>
                    <li>
                    	<a class="all" href="javascript:__doPostBack('export','')"><span>导出本页</span></a>
                    </li>
                    <li>
                    	<a class="all" href="javascript:;" id="smsSend"><span>选择发送短信</span></a>
                    </li>
                </ul>
                	<#--
                   <div class="menu-list">
                    <div class="rule-single-select">
                        <select name="typeId" id="fenlei" onchange="javascript:setTimeout(__doPostBack('typeId',''), 0);">
                            <option value="" <#if !typeId?? || typeId==10>selected="selected"</#if>>所有订单</option>
                            <option value="0" <#if typeId?? && typeId==0>selected="selected"</#if>>邮轮俱乐部订单</option>
                            <option value="1" <#if typeId?? && typeId==1>selected="selected"</#if>>目的地订单</option>
                            <option value="2" <#if typeId?? && typeId==2>selected="selected"</#if>>主题活动订单</option>
                            <option value="3" <#if typeId?? && typeId==3>selected="selected"</#if>>签证订单</option>
                            <option value="4" <#if typeId?? && typeId==4>selected="selected"</#if>>汽车租赁订单</option>
                            <option value="5" <#if typeId?? && typeId==5>selected="selected"</#if>>旅游直通车订单</option>
                            <option value="6" <#if typeId?? && typeId==6>selected="selected"</#if>>特产商城订单</option>
                            <option value="7" <#if typeId?? && typeId==7>selected="selected"</#if>>门票代售订单</option>
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
            姓名
        </th>
        <th align="left">
           籍贯
        </th>
         <th align="left">
            生日
        </th>
        <th align="left"  >
            QQ
        </th>
        <th align="left" >
            手机号
        </th>
        <th align="left" >
            学历
        </th>
        <th align="left" >
            民族
        </th>
        <th align="left" >
            政治面貌
        </th>
        <th align="left">
    单位        
        </th>
        <th >
            类型
        </th>
    </tr>

    <#if cus_page??>
        <#list cus_page.content as cus>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${cus_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${cus.id?c}">
                </td>
                <td><a href="/Verwalter/customer/edit?id=${cus.id?c}">${cus.username!""}</a></td>
                <td>${cus.province!''}-${cus.city!''}-${cus.disctrict!''}</td>
                <td><#if cus.birthday??>${cus.birthday?string('yyyy-MM-dd')}</#if></td>
                <td ><#if cus.qq??>${cus.qq!""}</#if></td>
                <td><#if cus.mobile??>${cus.mobile!''}</#if></td>
                <td><#if cus.education??>${cus.education!''}</#if></td>
                <td >${cus.nation!''}</td>
                <td >${cus.political!''}</td>
                <td><#if cus.company??>${cus.company!''}</#if></td>
                <td >${cus.type!''}</td>
            </tr>
        </#list>
    </#if>
</tbody>
</table>
        
<!--/列表-->
<!--内容底部-->
<#assign PAGE_DATA=cus_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>
