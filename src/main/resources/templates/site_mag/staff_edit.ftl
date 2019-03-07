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
<form method="post" action="/Verwalter/staff/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="id" value="<#if staff??>${staff.id?c!""}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/staff/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>职工管理</span>
  <i class="arrow"></i>
  <span>编辑职工信息</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap" >
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑职工信息</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
	<dl>
        <dt>单位</dt>
        <dd>
            <div class="menu-list">
                <div class="rule-single-select">
                    <select name="companyId" datatype="n">
                        <option value="">请选择所属单位</option>
                        <option value="1" <#if staff?? && staff.companyId==1>selected="selected"</#if>>重庆交通运业有限责任公司</option>
                        <option value="2" <#if staff?? && staff.companyId==2>selected="selected"</#if>>重庆交通运业有限责任公司渝北双凤桥汽车站</option>
                        <option value="3" <#if staff?? && staff.companyId==3>selected="selected"</#if>>重庆交通运业有限责任公司重庆汽车站</option>
                        <option value="4" <#if staff?? && staff.companyId==4>selected="selected"</#if>>重庆交通运业有限责任公司陈家坪汽车站</option>
                        <option value="5" <#if staff?? && staff.companyId==5>selected="selected"</#if>>重庆交通运业有限责任公司龙头寺汽车站</option>
                        <option value="6" <#if staff?? && staff.companyId==6>selected="selected"</#if>>重庆交通运业有限责任公司富丽大酒店</option>
                        <option value="7" <#if staff?? && staff.companyId==7>selected="selected"</#if>>重庆交通运业有限责任公司站务中心汽车北站</option>
                        <option value="8" <#if staff?? && staff.companyId==8>selected="selected"</#if>>重庆交通运业有限责任公司站务中心南坪汽车站</option>
                        <option value="9" <#if staff?? && staff.companyId==9>selected="selected"</#if>>重庆市公路客运联网售票中心有限公司</option>
                        <option value="10" <#if staff?? && staff.companyId==10>selected="selected"</#if>>重庆交通运业有限责任公司站务中心长途汽车站</option>
                        <option value="11" <#if staff?? && staff.companyId==11>selected="selected"</#if>>重庆迅为四公里交通换乘枢纽有限公司</option>
                        <option value="12" <#if staff?? && staff.companyId==12>selected="selected"</#if>>重庆天涯国际旅行社有限公司</option>
                        <option value="13" <#if staff?? && staff.companyId==13>selected="selected"</#if>>重庆交通运业有限责任公司商旅服务分公司</option>
                        <option value="14" <#if staff?? && staff.companyId==14>selected="selected"</#if>>重庆交通运业有限责任公司稽查大队</option>
                        <option value="15" <#if staff?? && staff.companyId==15>selected="selected"</#if>>重庆交通运业有限责任公司交运快递</option>
                        <option value="16" <#if staff?? && staff.companyId==16>selected="selected"</#if>>重庆交通运业有限责任公司富苑宾馆</option>
                        <option value="18" <#if staff?? && staff.companyId==18>selected="selected"</#if>>重庆交通运业有限责任公司巴南龙洲湾汽车站</option>
                    </select>
                </div>
                <span class="Validform_checktip">*</span>
            </div>
        </dd>
    </dl>
    <dl>
        <dt>姓名</dt>
        <dd>
            <input name="username" type="text" value="<#if staff??>${staff.username!""}</#if>" class="input normal" datatype="*" ajaxurl="/Verwalter/staff/check<#if staff??>?id=${staff.id?c}</#if>">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>性别</dt>
        <dd>
          <div class="rule-multi-radio">
            <span id="rblStatus">
                <input type="radio" name="sex" value="0" datatype="n" <#if !staff?? || !staff.sex?? || staff.sex==0>checked="checked"</#if>>
                <label>保密</label>
                <input type="radio" name="sex" value="1" datatype="n" <#if staff?? && staff.sex?? && staff.sex==1>checked="checked"</#if>>
                <label>男</label>
                <input type="radio" name="sex" value="2" datatype="n" <#if staff?? && staff.sex?? && staff.sex==2>checked="checked"</#if>>
                <label>女</label>
            </span>
          </div>
          <span class="Validform_checktip"></span>
        </dd>
      </dl>
    <dl>
        <dt>入职时间</dt>
        <dd>
            <div class="input-date">
                <input name="entryTime" type="text" value="<#if staff?? && staff.entryTime??>${staff.entryTime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                <i>日期</i>
            </div>
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>工龄</dt>
        <dd>
            <input name="workYear" type="text" value="<#if staff??>${staff.workYear!"0"}</#if>" class="input normal" datatype="*1-100" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
     <dl>
        <dt>休假天数</dt>
        <dd>
            <input name="leaveDay" type="text" value="<#if staff??>${staff.leaveDay!'0'}</#if>" class="input normal" onblur="countdis(this.value);" datatype="n" sucmsg=" ">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
<script>
function countdis(num)
{
    $("#discount").attr("value",num*60);
}
</script>
    <dl>
        <dt>优惠金额</dt>
        <dd>
            <input name="discount" id="discount" type="text" value="<#if staff?? && staff.discount??>${staff.discount?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" " readonly>
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>可使用优惠金额</dt>
        <dd>
            <input name="surDiscount" id="surDiscount" type="text" value="<#if staff?? && staff.surDiscount??>${staff.surDiscount?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>挂账结余</dt>
        <dd>
            <input name="surplus" type="text" value="<#if staff?? && staff.surplus??>${staff.surplus?string('0.00')}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>结算</dt>
        <dd>
            <div class="rule-multi-radio multi-radio">
                <input type="radio" name="isClose" value="1" <#if staff?? && staff.isClose?? && staff.isClose>checked="checked"</#if>>
                <label>已结算</label>
                <input type="radio" name="isClose" value="0" <#if !staff?? || staff.isClose==false>checked="checked"</#if>>
                <label>未结算</label>
            </div>
        </dd>
    </dl>
    <dl>
        <dt>出行时间</dt>
        <dd>
            <div class="input-date">
                <input name="travelTime" type="text" value="<#if staff?? && staff.travelTime??>${staff.travelTime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" >
                <i>日期</i>
            </div>
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>结算时间</dt>
        <dd>
            <div class="input-date">
                <input name="accountTime" type="text" value="<#if staff?? && staff.accountTime??>${staff.accountTime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" >
                <i>日期</i>
            </div>
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>备注</dt>
        <dd>
            <textarea name="remark" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if staff??>${staff.remark!""}</#if></textarea>
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