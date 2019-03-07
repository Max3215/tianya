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
	    ptsq();
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
	
	function ptsq(){
		$("#unsale").hide();
		$("#sale").hide();
	}
	
	function sjsq(){
		$("#unsale").show();
		$("#sale").hide();
	}
	
	function xjsq(){
		$("#sale").show();
		$("#unsale").hide();
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
	
</script>
</head>

<body class="mainbody">
    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
      <a class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>申请审核</span>
    </div>
    <!--/导航栏-->
    
    <!--内容-->
  		<form method="post" action="/Verwalter/managerApply/save" id="form1" onsubmit="return checkForm()">
  			<h3 style="font-size: 18px;">选择审核人：</h3>
  			<#if checkManagerList??>
  				<#list checkManagerList as m>
  					<#if m_index%10==0 && m_index != 0>
  						<br/>
  					<#else>
  						<input type=text name="checkId" value="${m.id}" hidden="hidden">
  						<label><input id="listChkId_${m_index}" type="checkbox" name="listChkId" value="${m_index}" onclick="orderSelect(${m_index})"> ${m.realName!""}</label>
  					</#if>
  				</#list>
  			</#if>
	  		<br>
	  		<h3 style="font-size: 18px;">申请类型:</h3>
	  		<input type="radio" name="applyType" value="0" checked="true" onclick="ptsq()">普通申请
			<input type="radio" name="applyType" value="1" onclick="sjsq()">上架申请
			<input type="radio" name="applyType" value="2" onclick="xjsq()">下架申请
			<div id="unsale">
				<select name="unsaleGoodsId" multiple="multiple" style="height:150px">
					<option value="-1">请选择待上架商品</option>
					<#if unsaleGoodsList??>
						<#list unsaleGoodsList as ug>
						  <option value="${ug.id?c}">${ug.subTitle!''}</option>						
						</#list>
					</#if>
				</select>
			</div>
			<div id="sale">
				<select name="saleGoodsId" multiple="multiple" style="height:150px">
					<option value="-1">请选择待下架商品</option>
					<#if saleGoodsList??>
						<#list saleGoodsList as sg>
						  <option value="${sg.id?c}">${sg.subTitle!''}</option>						
						</#list>
					</#if>
				</select>
			</div>
			<br><br>
	  		<div class="form-group">
	    		<label for="exampleInputEmail1">申请标题:</label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" placeholder="请输入申请标题......" name="title" datatype="*" sucmsg=" "><span class="Validform_checktip">*</span><br>
	    		<label for="exampleInputEmail1">申请内容:</label><br>
	  			<textarea placeholder="请输入申请类容....." cols="200" rows="15" style="font-size:15px;" name="content" datatype="*" sucmsg=" "></textarea><span class="Validform_checktip">*</span>
	  		</div>
	  		<#--
	  		<#if checkManagerList??>
	  			<#list checkManagerList as m>
	  				<#if (m_index%8 == 0)><br></#if>
	  				<label>${m.realName!""}</label><input type="hidden" name="listId" id="listId" value="${m.id?c}">
	  				<input id="listChkId_${m_index}" type="checkbox" name="listChkId" value="${m_index}" onclick="test('listChkId_'+${m_index})">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  			</#list>
	  		</#if>
	  		<br><br><br>
	  		<input type=text hidden="hidden" id="listChkIdStr" name="listChkIdStr">
	  		-->
	  		<input type=text hidden="hidden" id="order" name="order">
	  		<button type="submit" class="btn btn-primary">提交申请</button>
		</form>
    	
	<!--/内容-->


</body>
</html>