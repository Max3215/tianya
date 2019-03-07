<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>发表旅行推荐</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>
<script type=text/javascript src="/client/js/luyi.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>

<script type="text/javascript">
	function checkContent(){
		var c = $("#content").val();
		var t = $("#title").val();
		if(t.length < 2){
			alert("标题不能低于两个字符");
			return false;
		}		
		if(c.length < 10){
			alert("内容不能低于10个字符")
			return false;
		}
		return true;
	}

    $(function () {
    	//初始化编辑器
        var editor = KindEditor.create('.editor', {
            width: '98%',
            height: '350px',
            resizeType: 1,
            uploadJson: '/user/editor/upload?action=EditorFile',
            fileManagerJson: '/user/editor/upload?action=EditorFile',
            allowFileManager: true
        })
        
        //初始化上传控件
        $(".upload-img").each(function () {
            $(this).InitSWFUpload({ 
                sendurl: "/user/upload", 
                flashurl: "/mag/js/swfupload.swf"
            });
        })
        
        //（缩略图）
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }

        $("#txtImgUrl").blur(function () {
            var txtPic = $("#txtImgUrl").val();
            if (txtPic == "" || txtPic == null) {
                $(".thumb_ImgUrl_show").hide();
            }
            else {
                $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
                $(".thumb_ImgUrl_show").show();
            }
        });
        
        //设置封面图片的样式
        $(".photo-list ul li .img-box img").each(function () {
            if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
                $(this).parent().addClass("selected");
            }
        });
        
    });
</script>
<body>

<#include "/client/common_header.ftl" />


<!-- ********************中间内容******************** -->
<div class="percenter_com send_article">
	<#include "/client/common_user_header.ftl" />
	
	<#include "/client/common_user_menu.ftl" />

	
	<!-- **********中间-右边********** -->
	<dl class="body_right">
		<dt>发表旅行推荐</dt>
		<dd>
			<form action="/user/notes4/save" id="form1" method="post" onsubmit="return checkContent()">
				<#if tdRecommendPictureNotes??><input type="hidden" name="id" value="${tdRecommendPictureNotes.id!c}"></#if>
				<div class="div1">
					<label style="font-size:16px;">文章标题：</label>
					<input type="text" name="title" id="title" <#if tdRecommendPictureNotes??>value="${tdRecommendPictureNotes.title!''}"</#if>/>
				</div>
				<div class="div1">
					<label>文章分类：</label>
					<!--<input type="text" name="title" <#if tdRecommendPictureNotes??>value="${tdRecommendPictureNotes.title!''}"</#if>/>-->
					<select name="classify" style="margin:20px 0 0 10px">
						<#--<option value="">请选择</option>-->
					   <option value="国内旅游" <#if tdRecommendPictureNotes??><#if tdRecommendPictureNotes.classify=='国内旅游'>selected</#if></#if>>国内导游</option>
					   <option value="境外旅游" <#if tdRecommendPictureNotes??><#if tdRecommendPictureNotes.classify=='境外旅游'>selected</#if></#if>>境外旅游</option>
					   <option value="邮轮俱乐部" <#if tdRecommendPictureNotes??><#if tdRecommendPictureNotes.classify=='邮轮俱乐部'>selected</#if></#if>>邮轮俱乐部</option>
					</select>
				</div>
				<div class="div2">
					<label>封面图片：</label>
					<section style="width:764px;float:left;;overflow:hidden;">
                		<input name="imgUrl" type="text" id="txtImgUrl" value="<#if tdRecommendPictureNotes??>${tdRecommendPictureNotes.imgUrl!''}</#if>" class="input normal upload-path">
		                <div class="upload-box upload-img" ></div>
		                <div class="photo-list thumb_ImgUrl_show" style="display: none;width:946px;float:left;">
		                    <ul>
		                        <li>
		                            <div class="img-box1"></div>
		                        </li>
		                    </ul>
		                </div>
            		</section>
				</div>
				<div class="div3"><textarea name="content" id="content" class="editor" style="visibility:hidden;"><#if tdRecommendPictureNotes??>${tdRecommendPictureNotes.content!''}</#if></textarea></div>
				<div class="div4"><input type="submit" value="发表"></div>
			</form>
		</dd>
	</dl>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->


<div class="h20"></div>
<#include "/client/common_footer.ftl" />
</body>
</html>