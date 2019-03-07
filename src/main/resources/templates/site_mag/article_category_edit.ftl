<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑类别</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet"></head>

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
        
        //初始化编辑器
        var editorMini = KindEditor.create('.editor-mini', {
            width: '98%',
            height: '250px',
            resizeType: 1,
            uploadJson: '/Verwalter/editor/upload?action=EditorFile',
            items: [
				'source', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'image', 'link', 'fullscreen']
        });
    });

    function change2cn(en, cninput) {
        var channel_name = "news_";
        cninput.value = channel_name + getSpell(en, "");
    }
    var totalWt = 0;
    <#if cat?? && cat.wtList??>
    	totalWt = ${cat.wtList?size};
    </#if>
    $(function(){
    	$("#addRoom2").click(function(){
    	
		    var trHtml = '<tr class="td_c">'
	            + '<td><input name="wtList[' + totalWt + '].id?c" id="id2" type="hidden" value=""></td>'
	            + '<td><input type="text" name="wtList[' + totalWt + '].question" class="td-input" value="" style="width:90%;"></td>'
	            + '<td><input type="text" name="wtList[' + totalWt + '].content" class="td-input" value="" style="width:90%;"></td>'
	            + '<td><i class="icon" style="text-indent:-9999999px;"></i>'
	            + '<a title="删除" class="img-btn del operator" onclick="del_room2(this);">删除</a>'
	            + '</td>'
	            + '</tr>';
	        $("#var_box_ps1").append(trHtml);
	        totalWt = totalWt + 1;
	    });
    })
    
    
    //删除房型
function del_room2(obj) {
	var psid = $(obj).parent().parent().find("#id2").eq(0).val();
	
	if ("" != psid)
	{
		$.ajax( {  
		    url:'/Verwalter/wt/del',
		    data:{  
            	id : psid
		    },  
		    type:'post', 
		    dataType:'json',  
		    success:function(data) {  
		        if(data.status =="y" ){  
		            SimplePop.alert("删除成功！");  
		            $(obj).parent().parent().remove();
		        }else{  
		            SimplePop.alert(data.info);  
		        }  
		     } 
		});
	}
	else
	{
		$(obj).parent().parent().remove();
	}
}
   
</script>

<body class="mainbody">
<div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;">
<table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form method="post" action="/Verwalter/category/save" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" />
</div>
<input name="menuId" type="text" value='${mid!""}' style="display:none;">
<input name="channelId" type="text" value='${cid!""}' style="display:none">
<input name="id" type="text" value='<#if cat??>${cat.id?c!""}</#if>' style="display:none">
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/category/list?cid=${cid!""}&mid=${mid!""}" class="back"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <a href="/Verwalter/category/list?cid=${cid!""}&mid=${mid!""}"><span>内容类别</span></a>
  <i class="arrow"></i>
  <span>编辑分类</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
    <div class="content-tab-ul-wrap">
      <ul>
        <li><a href="javascript:;" onclick="tabs(this);" class="selected">基本信息</a></li>
        <#--
        <li><a href="javascript:;" onclick="tabs(this);">扩展选项</a></li>
        -->
      </ul>
    </div>
  </div>
</div>

<div class="tab-content">
  <dl>
    <dt>所属父类别</dt>
    <dd>
      <div class="rule-single-select single-select">
        <select name="parentId" id="ddlParentId" style="display: none;">
            <option value="0" <#if cat?? && cat.parentId==0>selected="selected"</#if>>无父级分类</option>
        	<#if category_list??>
        	   <#list category_list as c>
        	       <option value="${c.id?c!""}" <#if cat?? && cat.parentId==c.id || fatherCat?? && fatherCat.id==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
        	   </#list>
        	</#if>
        </select>
      </div>
    </dd>
  </dl>
  <dl>
    <dt>排序数字</dt>
    <dd>
      <input name="sortId" type="text" value="<#if cat??>${cat.sortId!"99"}<#else>99</#if>" id="txtSortId" class="input small" datatype="n" sucmsg=" ">
      <span class="Validform_checktip">*数字，越小越向前</span>
    </dd>
  </dl>
  <dl>
    <dt>类别名称</dt>
    <dd><input name="title" type="text" id="txtTitle" value="<#if cat??>${cat.title!""}</#if>" class="input normal" onblur="change2cn(this.value, this.form.txtCallIndex)" datatype="*1-100" sucmsg=" "> <span class="Validform_checktip">*类别中文名称，100字符内</span></dd>
  </dl>
  <dl>
    <dt>调用别名</dt>
    <dd>
      <input name="callIndex" type="text" id="txtCallIndex" value="<#if cat??>${cat.callIndex!""}</#if>" class="input normal" datatype="/^\s*$|^[a-zA-Z0-9\-\_]{2,50}$/" errormsg="请填写正确的别名" sucmsg=" ">
      <span class="Validform_checktip">*类别的调用别名，只允许字母、数字、下划线</span>
    </dd>
  </dl>
  <dl>
    <dt>SEO标题</dt>
    <dd>
      <input name="seoTitle" type="text" maxlength="255" value="<#if cat??>${cat.seoTitle!""}</#if>" id="txtSeoTitle" class="input normal" datatype="s0-100" sucmsg=" ">
      <span class="Validform_checktip">255个字符以内</span>
    </dd>
  </dl>
  <dl>
    <dt>SEO关健字</dt>
    <dd>
      <textarea name="seoKeywords" rows="2" cols="20" id="txtSeoKeywords" class="input"><#if cat??>${cat.seoKeywords!""}</#if></textarea>
      <span class="Validform_checktip">以“,”逗号区分开，255个字符以内</span>
    </dd>
  </dl>
  <dl>
    <dt>SEO描述</dt>
    <dd>
      <textarea name="seoDescription" rows="2" cols="20" id="txtSeoDescription" class="input"><#if cat??>${cat.seoDescription!""}</#if></textarea>
      <span class="Validform_checktip">255个字符以内</span>
    </dd>
  </dl>
</div>
<#--
<div class="tab-content" style="display:none">
  <dl>
    <dt>URL链接</dt>
    <dd>
      <input name="linkUrl" type="text" maxlength="255" id="txtLinkUrl" value="<#if cat??>${cat.linkUrl!""}</#if>" class="input normal">
      <span class="Validform_checktip">填写后直接跳转到该网址</span>
    </dd>
  </dl>
  <dl>
        <dt>问题介绍</dt>
        <dd>
            <a id="addRoom2" class="icon-btn add"><i></i><span>添加新问题</span></a>
            <span class="Validform_checktip"></span>
        </dd>
    </dl>
    <dl>
        <dt></dt>
        <dd>
            <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                <thead>
                    <tr>
                    	<th width="4%">
                            ID
                        </th>
                        <th width="30%">
                            问题
                        </th>
                        <th width="60%">
                            解答
                        </th>
                        <th width="6%">
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody id="var_box_ps1">
                    <#if cat?? && cat.wtList??>
                        <#list cat.wtList as item>
                            <tr class="td_c">
                            	<td>
                                    <input name="wtList[${item_index}].id?c" id="id2" type="hidden" value="${item.id?c}">
                                    <span>${item.id?c}</span>
                                </td>
                                <td>
                                    <input type="text" id="title2" name="wtList[${item_index}].question" class="td-input" <#if item.content??>value="${item.question!''}"</#if> style="width:90%;">
                                </td>
                                <#--
                                <td>
                                    <input type="text" id="price11" name="wtList[${item_index}].content" class="td-input"  <#if item.content??>value="${item.content!''}"</#if> style="width:90%;">
                                </td>
                                --
                                <td>
                                	<text id="price11" name="wtList[${item_index}].content"><#if item.content??>value="${item.content!''}"</#if></text>
                                </td>
                                <td>
                                    <i class="icon" style="text-indent:-9999999px;"></i>
                                    <a title="删除" class="img-btn del operator" onclick="del_room2(this);">删除</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </dd>
    </dl>
</div>
-->



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