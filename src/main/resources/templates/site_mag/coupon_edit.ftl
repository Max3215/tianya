<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑优惠券</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">


<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>

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
<form name="form1" method="post" action="/Verwalter/coupon/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}">
<input name="couponId" type="text" value='<#if coupon??>${coupon.id?c}</#if>' style="display:none">
</div>

<!--导航栏-->
<div class="location">
  <a href="/Verwalter/coupon/list" class="back"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <a href="/Verwalter/coupon/list"><span>优惠券</span></a>
  <i class="arrow"></i>
  <span>编辑优惠券</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab">
    <div class="content-tab-ul-wrap">
      <ul>
        <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑优惠券</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="tab-content">
  <dl>
    <dt>优惠券类型</dt>
    <dd>
        <div class="rule-single-select">
            <select id="type" name="typeId" datatype="*" sucmsg=" "  onchange="getType();" >
                <#if !coupon??>
                <option value="">请选择类型...</option>
                </#if>             
                <#if coupon_type_list??>
                    <#list coupon_type_list as c>
                        <option value="${c.id?c!""}" <#if coupon?? && coupon.typeId==c.id>selected="selected"</#if>>${c.title!""}</option>                                                                  
                    </#list>                                                                                
                </#if>
                <input type="hidden" name="typeTitle" id="typetitle" value="">
            </select> *
        </div>
    </dd>
  </dl>
  <dl>
    <dt>剩余数量</dt>
    <dd>
      <input name="leftNumber" type="text" value="<#if coupon??>${coupon.leftNumbers?c!""}<#else>1</#if>" class="input small" datatype="n" sucmsg=" ">
      <span class="Validform_checktip">*</span>
    </dd>
  </dl>
  <dl>
    <dt>排序数字</dt>
    <dd>
      <input name="sortId" type="text" value="<#if coupon??>${coupon.sortId!""}<#else>99</#if>" class="input small" datatype="n" sucmsg=" ">
      <span class="Validform_checktip">*数字，越小越向前</span>
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