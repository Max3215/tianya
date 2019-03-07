<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑资产</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
    
    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
    
    //（缩略图）
    var txtPic = $("#txtImgUrl").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show").hide();
    }
    else {
        $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show").show();
    }
});
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/assets/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="id" value="<#if assets??>${assets.id?c!""}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/assets/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>资产管理</span>
  <i class="arrow"></i>
  <span>编辑资产记录</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap" >
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑资产记录</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
	<dl>
        <dt>添加人</dt>
        <dd>
            <input name="manager" type="text" readonly="readinly" value="<#if assets??>${assets.manager!""}<#elseif manager??>${manager.realName!''}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>编号</dt>
        <dd>
            <input name="assNumber" type="text" value="<#if assets??>${assets.assNumber!""}</#if>" class="input normal" readonly>
            <span class="Validform_checktip">自动生成</span>
        </dd>
    </dl>
    <dl>
        <dt>用车单位</dt>
        <dd>
            <#--
            <input name="company" type="text" value="<#if assets??>${assets.company!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
            -->
            <textarea name="company" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if assets??>${assets.company!""}</#if></textarea>
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>用车时间</dt>
        <dd>
            <div class="input-date">
                <input name="useDate" type="text" value="<#if assets??>${assets.useDate?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                <i>日期</i>
            </div>～
            <div class="input-date">
                <input name="endDate" type="text" value="<#if assets?? && assets.endDate??>${assets.endDate?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                <i>日期</i>
            </div>
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>车型</dt>
        <dd>
            <input name="models" type="text" value="<#if assets??>${assets.models!""}</#if>" class="input normal" datatype="*1-100" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>行程</dt>
        <dd>
            <textarea name="trip" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if assets??>${assets.trip!""}</#if></textarea>
            <#--
            <input name="trip" type="text" value="<#if assets??>${assets.trip!""}</#if>" class="input normal" datatype="*1-100" sucmsg=" ">
            -->
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>用车台数</dt>
        <dd>
            <input name="number" type="text" value="<#if assets??>${assets.number!'0'}</#if>" class="input normal" datatype="n" sucmsg=" ">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>成本:</dt>
        <dd></dd>
    </dl>
    <dl>
        <dt>车费</dt>
        <dd>
            <input name="CFare" type="text" value="<#if assets?? && assets.CFare??>${assets.CFare?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>已开票</dt>
        <dd>
            <input name="CInvoiced" type="text" value="<#if assets?? && assets.CInvoiced??>${assets.CInvoiced?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>未开票</dt>
        <dd>
            <input name="CUnbilled" type="text" value="<#if assets?? && assets.CUnbilled??>${assets.CUnbilled?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>已付款</dt>
        <dd>
            <input name="CAlready" type="text" value="<#if assets?? && assets.CAlready??>${assets.CAlready?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
     <dl>
        <dt>营收：</dt>
        <dd></dd>
    </dl>
    <dl>
        <dt>车费</dt>
        <dd>
            <input name="RFare" type="text" value="<#if assets?? && assets.RFare??>${assets.RFare?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>已开票</dt>
        <dd>
            <input name="RInvoiced" type="text" value="<#if assets?? && assets.RInvoiced??>${assets.RInvoiced?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>未开票</dt>
        <dd>
            <input name="RUnbilled" type="text" value="<#if assets?? && assets.RUnbilled??>${assets.RUnbilled?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>未付款</dt>
        <dd>
            <input name="unpaid" type="text" value="<#if assets?? && assets.unpaid??>${assets.unpaid?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>已收款</dt>
        <dd>
            <input name="RAlready" type="text" value="<#if assets?? && assets.RAlready??>${assets.RAlready?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>结算</dt>
        <dd>
        <!--
            <input name="RSettlement" type="text" value="<#if assets?? && assets.RSettlement??>${assets.RSettlement?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
            -->
            <div class="rule-multi-radio multi-radio">
                <input type="radio" name="isSettlement" value="1" <#if assets??==false || assets.isSettlement==true>checked="checked"</#if>>
                <label>已结算</label>
                <input type="radio" name="isSettlement" value="0" <#if assets?? && assets.isSettlement?? && assets.isSettlement==false>checked="checked"</#if>>
                <label>未结算</label>
            </div>
        </dd>
    </dl>
    <dl>
        <dt>利润</dt>
        <dd>
            <input name="profit" type="text" value="<#if assets?? && assets.profit??>${assets.profit?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>车公司</dt>
        <dd>
            <input name="carCompany" type="text" value="<#if assets??>${assets.carCompany!''}</#if>" class="input normal" datatype="*" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>备注</dt>
        <dd>
            <textarea name="mark" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if assets??>${assets.mark!""}</#if></textarea>
            <span class="Validform_checktip">255个字符以内</span>
        </dd>
    </dl>
</div>
    
    
<!--/内容-->
<!--工具栏-->
<div class="page-footer">
    <div class="btn-list">
        <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
        <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
    </div>
    <div class="clear">
    </div>
</div>
<!--/工具栏-->
</form>
</body>
</html>