<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>游记攻略详情</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>

<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>

<script type="text/javascript">
$(function () {
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

function getType()
{
	var id = document.getElementById("type").value;
	
	 $.ajax({
                type: "post",
                url: "/Verwalter/coupon/getTitle",
                data: { "typeId": id },
                dataType: "json",
                success: function (data) { 
                <!-- 修改 -->                 
                    if (data.code == 0) {
                       document.getElementById("typetitle").value = data.typetitle;
                       var total = document.getElementById("total").value;
                       if(data.typetitle!="免费洗车券" && data.typetitle!="免费打蜡券"){
                      	    var number = parseInt(total) + 1;                       	                          		                       		                       		                     
                       		for(var i=1; i<=total; i++){
                       			$(".tab-content dl").eq(i).css("display", "none");
                       		}
                       		$(".tab-content dl").eq(number).css("display", "block");
                       }
                       else{
                           var number = parseInt(total) + 1;                                             	  
                       	   $(".tab-content dl").eq(number).css("display", "none");                      
                       	   for(var i=0; i<=total; i++){
                       			$(".tab-content dl").eq(i).css("display", "block");
                       	   }
                       }
                    } else {
                        SimplePop.alert(data.msg);
                    }
                }
            });
	 
}
</script>
</head>

<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/user/note/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}">
<input name="noteId" type="hidden" value='<#if note??>${note.id?c}</#if>'>
</div>

<!--导航栏-->
<div class="location">
  <a href="/Verwalter/user/diary/list?cid=0&mid=46" class="back"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <a href="/Verwalter/user/diary/list?cid=0&mid=46"><span>优惠券</span></a>
  <i class="arrow"></i>
  <span>游记攻略详情</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab">
    <div class="content-tab-ul-wrap">
      <ul>
        <li><a href="javascript:;" onclick="tabs(this);" class="selected">游记/攻略详情</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="tab-content">
  <dl>
    <dt>作者：</dt>
    <dd>
        <input name="username" type="text" value="<#if note??>${note.username!""}</#if>" id="txtTitle" readonly class="input normal" datatype="*2-100" sucmsg=" ">
        <span class="Validform_checktip"></span>
    	
    </dd>
  </dl>
  <dl>
    <dt>文章标题：</dt>
    <dd>
        <input name="title" type="text" value="<#if note??> ${note.title!""}</#if>" id="txtTitle" class="input normal" datatype="*2-100" sucmsg=" ">
        <span class="Validform_checktip"></span>
       
    </dd>
  </dl>
  <dl>
    <dt>评论条数：</dt>
    <dd>
        <input name="countComment" type="text" value="<#if note??> ${note.countComment!0}</#if>" id="txtTitle" class="input normal" datatype="n" sucmsg=" ">
        <span class="Validform_checktip"></span>
    </dd>
  </dl>
  <dl>
    <dt>点赞数：</dt>
    <dd>
        <input name="countPraise" type="text" value="<#if note??>${note.countPraise!0}</#if>" id="txtTitle" class="input normal" datatype="n" sucmsg=" ">
        <span class="Validform_checktip"></span>
       
    </dd>
  </dl>
  <dl>
        <dt>封面图片</dt>
        <dd>
            <input name="imgUrl" type="text" id="txtImgUrl" value="<#if note??>${note.imgUrl!""}</#if>" class="input normal upload-path">
            <div class="upload-box upload-img"></div>
            <div class="photo-list thumb_ImgUrl_show" style="display: none;">
                <ul>
                    <li>
                        <div class="img-box1"></div>
                    </li>
                </ul>
            </div>
        </dd>
    </dl>
  <dl>
    <dt>内容：</dt>
    <dd>
        <textarea name="content" class="editor" style="visibility:hidden;"><#if note??>${note.content!""}</#if></textarea>
    	
    </dd>
  </dl>
  <dl>
    <dt>排序：</dt>
    <dd>
        <input name="sortId" type="text" value="<#if note??>${note.sortId!"99"}<#else>99</#if>" id="txtSortId" class="input txt100" datatype="n" sucmsg=" ">
    </dd>
  </dl>
  <dl>
    <dt>状态：</dt>
    <dd>
        <div class="rule-multi-radio multi-radio">
            <span id="rblStatus" style="display: none;">
                <input type="radio" name="statusId" value="0" <#if note?? && note.statusId?? && note.statusId==0>checked="checked"</#if>><label>待审核</label>
                <input type="radio" name="statusId" value="1" <#if note?? && note.statusId?? && note.statusId==1>checked="checked"</#if>><label>已审核</label>
            </span>
        </div>
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


</body></html>