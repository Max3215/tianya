<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑管理员</title>
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
	});
</script>
</head>
<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form method="post" action="/Verwalter/manager/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="id" value="<#if tdManager??>${tdManager.id?c}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/manager/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>系统用户</span>
  <i class="arrow"></i>
  <span>编辑管理员</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap" >
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑管理员</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
    <dl>
        <dt>用户名</dt>
        <dd>
            <input id="username" value="<#if tdManager??>${tdManager.username!''}</#if>" name="username" type="text" class="input normal" datatype="*6-100" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>密码</dt>
        <dd>
            <input name="password" id="password" value="<#if tdManager??>${tdManager.password!''}</#if>" type="password" class="input normal" datatype="*6-100" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>重复密码</dt>
        <dd>
            <input type="password" class="input normal" value="<#if tdManager??>${tdManager.password!''}</#if>" datatype="*6-100" recheck="password" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>是否启用</dt>
        <dd>
            <div class="rule-multi-radio">
                <span>
                    <input type="radio" name="isEnable" value="1" <#if tdManager?? && tdManager.isEnable>checked="checked"</#if>>
                    <label>启用</label>
                    <input type="radio" name="isEnable" value="0" <#if !tdManager?? || !tdManager.isEnable>checked="checked"</#if>>
                    <label>禁用</label>
                </span>
            </div>
        </dd>
    </dl>
    <#if tdManager?? && tdManager.parentId?? && tdManager.parentId==0>
    	<div style="display: none;">
	    	<dl>
		        <dt>上下架权限</dt>
		        <dd>
		            <div class="rule-multi-radio">
		                <span>
		                    <input type="radio" name="canUpDown" value="1" checked="checked">
		                    <label>启用</label>
		                    <input type="radio" name="canUpDown" value="0">
		                    <label>禁用</label>
		                </span>
		            </div>
		        </dd>
		    </dl>
		</div>
    <#else> 
	    <dl>
	        <dt>上下架权限</dt>
	        <dd>
	            <div class="rule-multi-radio">
	                <span>
	                    <input type="radio" name="canUpDown" value="1" <#if tdManager?? && tdManager.canUpDown?? && tdManager.canUpDown>checked="checked"</#if>>
	                    <label>启用</label>
	                    <input type="radio" name="canUpDown" value="0" <#if !tdManager?? || (tdManager.canUpDown?? && !tdManager.canUpDown)>checked="checked"</#if>>
	                    <label>禁用</label>
	                </span>
	            </div>
	        </dd>
	    </dl>    	
    </#if> 
    <dl>
        <dt>角色分配</dt>
        <dd>
                <div class="rule-single-select">
                    <select name="roleId" id="roleId" datatype="*" sucmsg=" ">
                        <#if !tdManager??>
                        <option value="">请选择类型...</option>
                        </#if>
                        <#if role_list??>
                            <#list role_list as c>
                                <option value="${c.id?c!""}" <#if tdManager?? && tdManager.roleId?? && tdManager.roleId==c.id>selected="selected"</#if>>${c.title!""}</option>
                            </#list>
                        </#if>
                    </select>*
                </div>
        </dd>
        <br>
        <dt>上级领导</dt>
        <dd>
            <div class="rule-single-select">
                <select name="parentId" id="parentId" >
                    <option value="0" <#if tdManager?? && tdManager.parentId?? && tdManager.parentId==0>selected="selected"</#if>>无父级分类</option>
                    <#if tdManagerList_parentList??>
                        <#list tdManagerList_parentList as p>
                            <option value="${p.id?c!""}" <#if tdManager?? && tdManager.parentId?? && tdManager.parentId==p.id>selected="selected"</#if>><#if p.layerCount?? && p.layerCount gt 1><#list 1..(p.layerCount-1) as a>　</#list>├ </#if>${p.username!""}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </dd>
    </dl>
    <dl>
        <dt>姓名</dt>
        <dd>
            <input name="realName" type="text" value="<#if tdManager??>${tdManager.realName!""}</#if>" class="input normal" datatype="*" sucmsg=" ">
            <span class="Validform_checktip">*</span>
        </dd>
    </dl>
    <dl>
        <dt>电话</dt>
        <dd>
            <input name="telephone" type="text" value="<#if tdManager??>${tdManager.telephone!""}</#if>" class="input normal" datatype="n0-100" sucmsg=" ">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>邮箱</dt>
        <dd>
            <input name="email" type="text" value="<#if tdManager??>${tdManager.email!""}</#if>" class="input normal">
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt>排序数字</dt>
        <dd>
            <input name="sortId" type="text" value="<#if tdManager??>${tdManager.sortId?c}<#else>99</#if>" class="input txt100" datatype="n" sucmsg=" ">
            <span class="Validform_checktip">*数字，越小越向前</span>
        </dd>
    </dl>
    <dl>
        <dt>签名印章</dt>
	    <dd>
	        <input id="txtImgUrl" name="signatureImgUri" type="text" value="<#if tdManager??>${tdManager.signatureImgUri!""}</#if>" class="input normal upload-path">
	        <div class="upload-box upload-img"></div>
	        <div id="thumb_ImgUrl_show1" class="photo-list thumb_ImgUrl_show">
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
    <div class="clear">
    </div>
</div>
<!--/工具栏-->
</form>
<script>
	$(document).ready(function(){
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
	        $("#thumb_ImgUrl_show1").hide();
	    }
	    else {
	        $("#thumb_ImgUrl_show1").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
	        $("#thumb_ImgUrl_show1").show();
	    }
	    
	});
</script>
</body>

</html>
