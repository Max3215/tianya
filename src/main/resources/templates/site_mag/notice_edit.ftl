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
	    
	    var s =  $("#mNotNull").val();
		if(s == "no"){
			SimplePop.alert("请选择下属!");
		}
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
	
		
	
</script>
</head>

<body class="mainbody">
    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
      <a class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>发通知</span>
    </div>
    <!--/导航栏-->
    
    <!--内容-->
  		<form method="post" action="/Verwalter/notice/save" id="form1">
  			<input type="text" id="mNotNull" value="${mNotNull!''}" hidden="hidden">
  			<h3>选择下属：</h3>
  			<#if tdManagerList??>
  				<#list tdManagerList as m>
  					<#if m_index%10==0>
  						<br/>
  						<input type=text name="listId" value="${m.id}" hidden="hidden">
  						<label><input type="checkbox" name="listChkId" value="${m_index}"> ${m.realName!""}</label>
  					<#else>
  						<input type=text name="listId" value="${m.id}" hidden="hidden">
  						<label><input type="checkbox" name="listChkId" value="${m_index}"> ${m.realName!""}</label>
  					</#if>
  				</#list>
  			</#if>
	  		<br>
	  		<br>
	  		<br>
	  		<div class="form-group">
	    		<label for="exampleInputEmail1">申请内容:</label><br>
	  			<textarea placeholder="请输入申请类容....." cols="200" rows="15" style="font-size:15px;" name="content" datatype="*" sucmsg=" "></textarea><span class="Validform_checktip">*</span>
	  		</div>
	  		<button type="submit" class="btn btn-primary">提交</button>
		</form>
    	
	<!--/内容-->
</body>
</html>