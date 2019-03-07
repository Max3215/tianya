<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)http://localhost:2105/site_mag/center.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理首页</title>

<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>

<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>

<script>
	$(function () {
	    //初始化表单验证
	    $("#form1").initValidform();
	});
</script>

<script>
	var str = "";
	function test(a){
		if($("#"+a).prop("checked")){
			//alert("选中了" + a);
			//alert(a.split("_")[1]);
			str = str + a.split("_")[1] + ","
			//alert(str);
		}else{
			//alert("没选中" + a);
			//str.replace(/Microsoft/, "W3School")
			str = str.replace(a.split("_")[1] + ",", "");
			//alert(str);
			
		}
		$("#listChkIdStr").val(str);
		
	}
	
	var s = "";
	
	function orderSelect(index){
		//alert(index);
		var indexStr = "-" + index + ",";
		if(s.indexOf(indexStr) > -1){
			s=s.replace(indexStr, "");
		}else{
			s = s + indexStr;
		}
		$("#order").val(s);
		//alert(s);
	}
	
	function checkForm(){
		if(s==""){
			SimplePop.alert("请选择审核人！");
			return false;
		}
		return true;
	}
	
	function goSure(){
		var id = $("#id").val();
		window.location.href = "/Verwalter/notice/goSure?id=" + id;
	}
	
</script>
</head>

<body class="mainbody">
    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
      <a class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>通知详情</span>
    </div>
    <!--/导航栏-->
    
    <!--内容-->
  		<form method="post" action="/Verwalter/notice/save" id="form1">
  			<input type="text" id="id" value="${id!''}" hidden="hidden">
  			<h3>选择下属：</h3>
  			<#if tdManagerList??>
  				<#list tdManagerList as m>
  					<#if m_index%10==0>
  						<br/>
  					<#else>
  						<input type=text name="listId" value="${m.id}" hidden="hidden">
  						<label><input type="checkbox" name="listChkId" value="${m_index}"> ${m.realName!""}</label>
  					</#if>
  				</#list>
  			</#if>
  			<p>
  				<font color="red">被通知人员：</font><br>
  				<#list allList as m>
  					<#if m_index%10==0>
  						<br/>
  						<label>${m.realName!""}</label> &nbsp;&nbsp;&nbsp;
  					<#else>
  						<label>${m.realName!""}</label> &nbsp;&nbsp;&nbsp;
  					</#if>
  				</#list>
  			</p>
  			<p>
  				<font color="green">已确认人员：</font><br>
  				<#list sureList as m>
  					<#if m_index%10==0>
  						<br/>
  						<label>${m.realName!""}</label> &nbsp;&nbsp;&nbsp;
  					<#else>
  						<label>${m.realName!""}</label> &nbsp;&nbsp;&nbsp;
  					</#if>
  				</#list>
  			</p>
	  		<br>
	  		<br>
	  		<br>
	  		<div class="form-group">
	    		<label for="exampleInputEmail1">申请内容:</label><br>
	  			<textarea placeholder="请输入申请类容....." cols="200" rows="15" style="font-size:15px;" name="content" datatype="*" sucmsg=" ">${tdNotice.content!""}</textarea>*
	  		</div>
	  		<#if canSure?? && canSure=="yes">
		  		<button type="button" onclick="goSure()" class="btn btn-primary">确认收到</button>
	  		</#if>
	  		<button type="button" onclick="javascript:history.back(-1);" class="btn btn-primary">返回</button>
		</form>
    	
	<!--/内容-->
</body>
</html>