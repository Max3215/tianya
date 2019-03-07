<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)http://localhost:2105/site_mag/center.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理首页</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
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


	
</head>

<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/manager/passwordchange"  id="form1">
    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
      <a class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>员工相关</span>
      <i class="arrow"></i>
      <span>修改密码</span>
    </div>
    <!--/导航栏-->
    
    <!--内容-->
    	
    	  <div class="content-tab-wrap">
			    <div id="floatHead" class="content-tab">
			        <div class="content-tab-ul-wrap" >
			            <ul>
			                <li><a href="javascript:;" onclick="tabs(this);" class="selected">修改密码</a></li>
			            </ul>
			        </div>
			    </div>
		  </div>
    		
		 <div class="tab-content" style="display: block;">
		    <dl>
		        <dt>原密码</dt>
		        <dd>
		            <input name="oldpass" id="oldpass" type="password" placeholder="请输入原密码"  class="input normal" datatype="*6-100" sucmsg=" " value="">
		            <span class="Validform_checktip">*</span>
		        </dd>
		    </dl>
		    <dl>
		        <dt>新密码</dt>
		        <dd>
		            <input name="newpass" id="newpass" type="password" placeholder="请输入新密码" class="input normal" datatype="*6-100" sucmsg=" ">
		            <span class="Validform_checktip">*</span>
		        </dd>
		    </dl>
		    <dl>
		        <dt>重复新密码</dt>
		        <dd>
		            <input name="newpass2" id="newpass2" placeholder="请重复新密码" type="password" class="input normal" datatype="*6-100" recheck="newpass" sucmsg=" ">
		            <span class="Validform_checktip">*</span>
		        </dd>
		    </dl>
  		  <input type="submit" class="btn btn-default" value="修改"><span name="error" id="error" style="color: red;"><#if error??>${error!""}</#if></span>
		  </div>
	<!--/内容-->
</form>
</body>
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#oldpass").val("");
			
		$("#form1").initValidform();
	
	});
</script>
</html>