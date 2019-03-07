<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>用户管理</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>

<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form name="form1" method="post" action="/Verwalter/user/list" id="form1">
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
    
    $(function () {
            $("#btnOrderChangeStatus").click(function () { ChangeOrderStatus(); }); //普通发放优惠劵
            $("#btnOrderChangeStatus1").click(function () { ChangeOrderStatus1(); }); //等级发放优惠劵
            $("#levelSmsSend").click(function () { levelSmsSend(); }); // 等级发送短信
            $("#smsSend").click(function () { smsSend(); }); // 选择发送短信
        });
    
    //发放优惠劵
     function ChangeOrderStatus() {

          var dialog = $.dialog({
                title: '优惠劵',
                content: '<div class="rule-single-select single-select">'
                            +'<select name="typeId" id="changeOrderStatus">'                           
                               +'<option value="">未选择</option>'
                               +'<#if coupon_type_list??><#list coupon_type_list as item><option value="${item.id!c}">${item.title!''}</option></#list></#if>'             
                            +'</select>'
                          +'</div>',
                min: false,
                max: false,
                lock: true,
                ok: function () {
                    var changeOrderStatusId = $("#changeOrderStatus", parent.document).val();
                    
	                  var sign = "";
					  var inputs = document.getElementsByTagName('input');//获取所有的input标签对象。
					  for(var i=0;i<inputs.length;i++){
					    var obj = inputs[i];
					    if(obj.type=='checkbox'){
					     if(obj.checked==true){
					     	var aa = $("#"+obj.value).val();
					      	sign += aa;
					      	sign +=",";
					      }
					    } 
					  }
                    
                    var listId = $(".22").val();
                    if (changeOrderStatusId == "") {
                        $.dialog.alert('对不起，请选择优惠劵类型', function () { }, dialog);
                        return false;
                    }
                    var postData = {"listChkId1": sign, "listId": listId, "typeId": changeOrderStatusId};
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/user/coupon/send");
                    return false;
                },
                cancel: true
          });
      }
      
       function levelSmsSend() {
          var dialog = $.dialog({
                title: '编辑短信',
                content: '<div class="rule-single-select single-select">'
                            +'<select name="levelId" id="changeOrderStatus">'                           
                               +'<option value="">未选择</option>'
                               +'<#if user_level_list??><#list user_level_list as item><option value="${item.id!c}">${item.title!''}</option></#list></#if>'             
                            +'</select>'
                          +'</div>'
                          +'<textarea id="message" name="txtOrderRemark" rows="2" cols="20" class="input"></textarea>',
                min: false,
                max: false,
                lock: true,
                ok: function () {
                    var levelId = $("#changeOrderStatus", parent.document).val();
                    var message = $("#message", parent.document).val();
                    
                     if (levelId == "") {
                        $.dialog.alert('对不起，请选择发送会员等级', function () { }, dialog);
                        return false;
                    }
                    if (message == "") {
                        $.dialog.alert('对不起，请输入短信内容！', function () { }, dialog);
                        return false;
                    }
                    
                    var postData = {"levelId": levelId, "message": message, "type": "LevelSend"};
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/user/smsSend");
                    return false;
                },
                cancel: true
          });
      }
      
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
                                if(i+2<inputs.length)
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
                    
                    var postData = {"listId": listId, "listChkId": listChkId, "message": message, "type": "sendSMS"};
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/user/smsSend");
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
    
    
    //等级发放优惠劵
     function ChangeOrderStatus1() {

          var dialog = $.dialog({
                title: '优惠劵',
                content: '<div class="rule-single-select single-select">'
                            +'<select name="type" id="changeOrderStatus1">'                           
                               +'<option value="">未选择</option>'
                               +'<#if user_level_list??><#list user_level_list as item><option value="${item.id!c}">${item.title!''}</option></#list></#if>'             
                            +'</select>'
                            +'<select name="typeId" id="changeOrderStatus2">'                           
                               +'<option value="">未选择</option>'
                               +'<#if coupon_type_list??><#list coupon_type_list as item><option value="${item.id!c}">${item.title!''}</option></#list></#if>'             
                            +'</select>'
                          +'</div>',
                min: false,
                max: false,
                lock: true,
                ok: function () {
                    var changeOrderStatusId1 = $("#changeOrderStatus1", parent.document).val();
                    var changeOrderStatusId2 = $("#changeOrderStatus2", parent.document).val();
                    
                    
                    var listId = $(".22").val();
                    if (changeOrderStatusId1 == "") {
                        $.dialog.alert('对不起，请选择会员类型', function () { }, dialog);
                        return false;
                    }
                    if (changeOrderStatusId2 == "") {
                        $.dialog.alert('对不起，请选择优惠劵类型', function () { }, dialog);
                        return false;
                    }
                    var postData = {"listId": listId, "type": changeOrderStatusId1,"typeId": changeOrderStatusId2, "code1":"11"};
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/user/coupon/send");
                    return false;
                },
                cancel: true
          });
      }
      
    
</script>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>会员管理</span>
  <i class="arrow"></i>
  <span>会员列表</span>  
</div>
<!--/导航栏-->

<!--工具栏-->
<div class="toolbar-wrap">
  <div id="floatHead" class="toolbar" style="position: static; top: 42px;">
    <div class="l-list">
      <ul class="icon-list">
        <li><a class="add" href="/Verwalter/user/edit"><i></i><span>新增</span></a></li>
        <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
        <#if tdManagerRole?? && tdManagerRole.isSys>
        <li><a onclick="return ExePostBack('btnDelete');" id="btnDelete" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a></li>
        </#if>
        <input type="button" id="btnOrderChangeStatus" value="选择发放优惠劵" class="btn" style="background:#fafafa;color: #333;font-size: 12px;border: solid 1px #e1e1e1;">
        <input type="button" id="btnOrderChangeStatus1" value="等级发放优惠劵" class="btn" style="background:#fafafa;color: #333;font-size: 12px;border: solid 1px #e1e1e1;">
        <input type="button" id="levelSmsSend" value="等级发送短信" class="btn" style="background:#fafafa;color: #333;font-size: 12px;border: solid 1px #e1e1e1;" >
        <input type="button" id="smsSend" value="选择发送短信" class="btn" style="background:#fafafa;color: #333;font-size: 12px;border: solid 1px #e1e1e1;" >
      </ul>
    </div>
    <div class="r-list">
      <input name="keywords" type="text" class="keyword" value="${keywords!""}">
      <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('btnSearch','')">查询</a>
    </div>
  </div>
</div>
<!--/工具栏-->

<!--列表-->

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
  <tbody>
  <tr class="odd_bg">
    <th width="8%">选择</th>
    <th align="left" colspan="2">用户名</th>
    <th align="left" width="6%">用户等级</th>
    <th align="center" width="12%">邮箱</th>
    <th width="12%">手机号</th>
    <th width="8%"><a href="javascript:__doPostBack('btnLastLoginOrder','${orderType!'asc'}')">最近登录</a></th>
    <th width="8%">积分</th>
    <th width="6%">状态</th>
    <th width="6%">操作</th>
    </tr>
    <#if user_page??>
    <#list user_page.content as user>
        <tr>
            <td align="center">
                <span class="checkall" style="vertical-align:middle;">
                    <input id="listChkId" class="11" type="checkbox" name="listChkId" value="${user_index}" >
                </span>
                <input type="hidden" class="22" name="listId" id="listId" value="${user.id?c}">
                <input type="hidden" class="33" name="listId1" id="${user_index}" value="${user.id?c}">
            </td>
            <td width="64">
              <a href="/Verwalter/user/edit?id=${user.id?c}">
                <img width="64" height="64" src="${user.headImageUri!"/mag/style/user_avatar.png"}">
              </a>
            </td>
            <td>
              <div class="user-box">
                <h4><b>${user.username!""}</b> (姓名：${user.realName!""})</h4>
                <i>注册时间：${user.registerTime!""}</i>
                <span>
                  <a class="amount" href="/Verwalter/user/point/list?userId=${user.id?c}" title="积分">积分</a>
                  <a class="point" href="/Verwalter/user/collect/list?userId=${user.id?c}" title="收藏商品">收藏商品</a>
                  <a class="msg" href="/Verwalter/user/recent/list?userId=${user.id?c}" title="浏览历史">浏览历史</a>
                  <#if user.roleId?? && user.roleId==1>
                      <a class="sms" href="/Verwalter/user/reward/list?userId=${user.id?c}" title="返现记录">返现记录</a>
                  </#if>
                </span>
              </div>
            </td>
            <td><#if user.userLevelTitle??>${user.userLevelTitle!''}</#if></td>
            <td align="center">${user.email!""}</td>
            <td align="center"><#if user.mobile??>${user.mobile!""}</#if></td>
            <td align="center">${user.lastLoginTime!""}</td>
            <td align="center">${user.totalPoints!""}                    
            </td>
            <td align="center"><#if user.statusId??><#if user.statusId==0>待审核<#elseif user.statusId==1>正常<#else>禁用</#if></#if></td>
            <td align="center">
                <a href="/Verwalter/user/edit?id=${user.id?c}&roleId=${roleId!""}">修改</a> | 
                <a href="/Verwalter/user/edit?id=${user.id?c}&roleId=${roleId!""}&action=view">查看</a></td>
          </tr>
    </#list>
</#if>
     
</tbody>
</table>

<!--/列表-->

<!--内容底部-->
<#assign PAGE_DATA=user_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>