<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)http://localhost:2105/site_mag/center.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理首页</title>

<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>


<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>

<script>
	$(document).ready(function(){
		var isRefuse = $("#isRefuse").val();
		if(isRefuse == "true"){
			$("#refuseReson").show();
			$("#sure").hide();
			$("#rr").attr("readonly", "readonly");
		}else{
			$("#refuseReson").hide();
		}
	});
	
	function refuse(){
		//alert("ok");
		//$("#operation").hide();
		//$("#refuseReson").show();
		$("#reject").val("reject");
	}
	
	function kankan(){
		SimplePop.alert($("rr").val());
		return false;
	}
</script>
</head>

<body class="mainbody">
    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
      <a class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>申请详情</span>
    </div>
    <!--/导航栏-->
    
    <!--内容-->
  		<form method="post" action="/Verwalter/managerApply/getCheck?id=${tdManagerApply.id}">
	  		<#if selectedCheckManagerList??>
	  			<#list selectedCheckManagerList as m>
	  				<#if m.id lt 0>
	  					<label class="btn-danger glyphicon glyphicon-hourglass">${m.realName!""}</label>
	  				<#else>
	  					<label class="btn-success glyphicon glyphicon-ok">${m.realName!""}</label>
	  				</#if>
	  				&nbsp;&nbsp;&nbsp;
	  			</#list>
	  		</#if>
	  		<p>状态:${tdManagerApply.state!""}</p>
	  		<p>申请人:${tdManagerApply.managerName!""}<p>
	  		<p>申请类型:
		  		<#if tdManagerApply.applyType?? && tdManagerApply.applyType==0>
		  			普通申请
		  		<#elseif tdManagerApply.applyType?? && tdManagerApply.applyType==1>
		  			上架申请&nbsp;|&nbsp;
		  			<#if tdManagerApply.unsaleGoodsUrl??>
                            <#list tdManagerApply.unsaleGoodsUrl?split(",") as url>
                                <#if url != "">
            		  			<a href="${url!''}" target="_blank"><font color="blue">查看申请上架商品详情</font></a>&nbsp;|&nbsp;
            		  			</#if>
            		  	   </#list>
		  			</#if>
	  			<#elseif tdManagerApply.applyType?? && tdManagerApply.applyType==2>
		  			下架申请&nbsp;|&nbsp;
		  			<#if tdManagerApply.saleGoodsUrl??>
                            <#list tdManagerApply.saleGoodsUrl?split(",") as url>
                                <#if url != "">
		  			             <a href="${url!''}" target="_blank"><font color="blue">查看申请下架商品详情</font></a>&nbsp;|&nbsp;
		  			             </#if>
                           </#list>
                    </#if>
		  		</#if>
	  		</p>
	  		
	  		<div class="form-group">
	    		<label for="exampleInputEmail1">申请标题:</label>
	    		<input type="text" readonly="readonly" class="form-control" name="title" value="<#if tdManagerApply??>${tdManagerApply.title!''}</#if>"><br>
	    		<label for="exampleInputEmail1">申请内容:</label><br>
	  			<textarea cols="200" rows="15" style="font-size:15px;" name="content" readonly="readonly"><#if tdManagerApply??>${tdManagerApply.content!''}</#if></textarea>
	  		</div>
	  		
	  		
	  		<br><br><br>
	  		<div id="operation">
	  		<#if commentList??>
				<h2>已评审意见：</h2>
				<#list commentList as c>
					${c.name!""}:&nbsp;&nbsp;${c.comment!""}<br/>
				</#list>
			</#if><br/>
	  		<#if tdManagerApply?? && !(tdManagerApply.isRefuse)>
		  		<#if canICheck?? && canICheck == "yes">
		  		<label for="exampleInputEmail1">审批意见:</label><br>
		  		<textarea cols="150" rows="5" style="font-size:15px;" name="refuseReson" id="rr"></textarea><br>
		  			<#--<a class="btn btn-info" href="/Verwalter/managerApply/getCheck?id=${tdManagerApply.id}">批准</a>-->
		  			<input class="btn btn-info" type="submit" value="批准">
		  			<#--
		  			<#if tdManager?? && tdManager.parentId != 0>
		  				<a class="btn btn-info" href="/Verwalter/managerApply/getCheck?id=${tdManagerApply.id}&agree=yes">同意并上报</a>
		  			</#if>
		  			-->
		  			<input type="text" hidden="hidden" id="reject" name="reject">
		  			<#--<a class="btn btn-info" onclick="refuse()">拒绝</a>-->
		  			<input class="btn btn-info" type="submit" onclick="refuse()" value="拒绝">
		  		</#if>
		  		<br>
	  		</#if>
	  		</div>
	  		
	  		<#--<input type="text" value="${tdManagerApply.isRefuse?c}" hidden="hidden" id="isRefuse"/>-->
	  		<#--<div id ="refuseReson">
		  		<label for="exampleInputEmail1">拒绝理由:</label><br>-->
		  		<#--<br><input type="submit" value="确定" class="btn btn-info" id="sure">-->
		  	<#--</div>-->
		  	
		</form><br>
		
    	
	<!--/内容-->


</body>
</html>