<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>常用游客信息</title>
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/person_center.css">
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script type=text/javascript src="/client/js/jquery-1.11.0.js"></script>
<script src="/client/js/Validform_v5.3.2_min.js"></script>
<script type=text/javascript src="/client/js/rich_lee.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	//初始化表单验证
	 $("#form1").Validform({
        tiptype: 4,
        ajaxPost:true,
        callback: function(data) {
            if (data.code==1)
            {
                alert(data.msg);
                window.location.href="/user/visitor";
            }
            else
            {
                alert(data.msg);
                window.location.href="/login";
            }
        }
    });
	
});

</script>


<body>

<#include "/client/common_header.ftl" />


<!-- ********************中间内容******************** -->
<div class="percenter_com traveler_infor">
	<#include "/client/common_user_header.ftl" />
	<#include "/client/common_user_menu.ftl" />
	
	<!-- **********中间-右边********** -->
	<div class="body_right">
		<div class="right_title">其他联系人</div>
		<dl class="tole_traveler">
			<dt>
				<div class="pname">姓名</div>
				<div class="psex">性别</div>
				<div class="pcre">证件类型-<span>身份证</span></div>
				<div class="ptele">手机</div>
				<div class="prate">操作</div>
			</dt>
			<dd  class="otherdd">
				<ul>
					
					<#if visitor_user_list??>
						<#list visitor_user_list as visitor>
							<li>
								<div class="pname">${visitor.visitorName!''}</div>
								<div class="psex">${visitor.sex!''}</div>
								<div class="pcre">${visitor.certificateType!''}-<span>${visitor.certificateCardCode!''}</span></div>
								<div class="ptele">${visitor.telephone!''}</div>
								<div class="prate">
									<a href="/user/visitor/update?id=${visitor.id!''}" class="savea">修改</a> 
									<a href="/user/visitor/delete?id=${visitor.id!''}">删除</a>
								</div>
							</li>
						</#list>
					</#if>
				</ul>
			</dd>
		</dl>
		<a class="add_traveler" id="addVisitor">可以在下面直接添加或修改游客信息</a>
		<form class="obj_adds" id="form1" action="/user/visitor/add" method="post">
			<#if tdUserVisitor??><input type="hidden" name="id" value="${tdUserVisitor.id!c}"></#if>
			<div class="div1">
				<label class="name_la">姓名：</label>
				<input type="text" name="visitorName" datatype="s2-20" <#if tdUserVisitor??>value="${tdUserVisitor.visitorName!''}"</#if>>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div2">
				<label class="name_la">性别：</label>
				<input type="radio" <#if tdUserVisitor??&&tdUserVisitor.sex=="男">checked<#else></#if> name="sex" datatype="*" errormsg="请选择性别!" value="男">
				<span class="Validform_checktip" style="color: red;"></span>
				<label class="osex_lab">男</label>
				<input type="radio" <#if tdUserVisitor??&&tdUserVisitor.sex=="女">checked<#else></#if> name="sex" datatype="*" errormsg="请选择性别!" value="女">
				<span class="Validform_checktip" style="color: red;"></span>
				<label class="osex_lab">女</label>
			</div>
			<div class="div2">
				<label class="name_la">证件：</label>
				<select name="certificateType" style="margin:10px 0 0 0">
					<option value="二代身份证" <#if tdUserVisitor??&&tdUserVisitor.certificateType=="二代身份证">selected="selected"<#else></#if>>二代身份证</option>
					<option value="驾驶证" <#if tdUserVisitor??&&tdUserVisitor.certificateType=="驾驶证">selected="selected"<#else></#if>>驾驶证</option>
				</select>
			</div>
			<div class="div3">
				<label class="name_la">证件号：</label>
				<input type="text" name="certificateCardCode" datatype="n18-18|s10-12" errormsg="请输入正确证件证号!" <#if tdUserVisitor??>value="${tdUserVisitor.certificateCardCode!''}"</#if>>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div4">
				<label class="name_la">手机：</label>
				<input type="text" name="telephone" datatype="m" errormsg="请输入正确手机号!" <#if tdUserVisitor??>value="${tdUserVisitor.telephone!''}"</#if>>
				<span class="Validform_checktip" style="color: red;"></span>
			</div>
			<div class="div5"><input type="submit" value="保存"></div>
		</form>
		
	</div>
	<!-- **********中间-右边-结束********** -->
</div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />
<div class="h20"></div>

</body>
</html>