<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统设置</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">

<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>
<script type="text/javascript">
$(function () {
    <#if status??>
        SimplePop.alert("修改活动设置成功");
    </#if>
    //初始化表单验证
    $("#form1").initValidform();
    
    //初始化编辑器
    var editor = KindEditor.create('.editor', {
        width: '98%',
        height: '350px',
        resizeType: 1,
        uploadJson: '/Verwalter/editor/upload?action=EditorFile',
        fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
        allowFileManager: true
    });
    
    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
    
});
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/bargain/setting/save" id="form1">
    <div>
    <input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
    <input type="hidden" name="id" value="<#if bargain_setting??>${bargain_setting.id?c!""}</#if>" >
    </div>
    <!--导航栏-->
    <div class="location" style="position: static; top: 0px;">
      <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>系统管理</span>
      <i class="arrow"></i>
      <span>系统设置</span>
    </div>
    <div class="line10"></div>
    <!--导航栏-->  
    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab">
            <div class="content-tab-ul-wrap" >
                <ul>
                    <li><a href="javascript:;" onclick="tabs(this);" class="selected">活动基本信息</a></li>
                   
                </ul>
            </div>
        </div>
    </div>
    <div class="tab-content" style="display: block;">
        <dl>
            <dt>起始金额</dt>
            <dd>
                <input name="startPrice" type="text" value="<#if bargain_setting??>${bargain_setting.startPrice!""}<#else>2000</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">*</span>
            </dd>
        </dl>        
        <dl>
            <dt>总共销售数量</dt>
            <dd>
                <input name="totalSoldNumber" type="text" value="<#if bargain_setting??>${bargain_setting.totalSoldNumber!""}</#if>" class="input normal"  >
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>总共剩余数量</dt>
            <dd>
                <input name="totalLeftNumber" type="text" value="<#if bargain_setting??>${bargain_setting.totalLeftNumber!""}</#if>" class="input normal"  >
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>参与人数</dt>
            <dd>
                <input name="totalParticipant" type="text" value="<#if bargain_setting??>${bargain_setting.totalParticipant!""}</#if>" class="input normal" >
                <span class="Validform_checktip"></span>
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
        <div class="clear"></div>
    </div>
    <!--/工具栏-->
</form>
</body>
</html>