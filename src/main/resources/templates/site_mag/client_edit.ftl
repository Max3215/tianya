<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑管理员</title>
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
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
});

function delItem(c){
	if(c.indexOf("old_") >= 0){
		var itemId = c.split("_")[1];
		//alert(itemId);
		$.post("/Verwalter/outMoneyItem/delete", 
		{itemId: ""+itemId+""}, 
		function(data, status){
			
		});
	}
	$("#"+c+"").remove();
		
}

function delItem2(c){
	//alert("ok");
	if(c.indexOf("old2_") >= 0){
		var itemId = c.split("_")[1];
		//alert(itemId);
		$.post("/Verwalter/travelLineItem/delete", 
		{itemId: ""+itemId+""}, 
		function(data, status){
			
		});
	}
	$("#"+c+"").remove();
		
}



var itemNum = 0;
var itemNum2 = 0;

$(document).ready(function(){
	$("#extenstionItem").click(function(){
		var flag = "new_" + itemNum;
		$("#outItem").append("<dd id='"+flag+"'><b>项目名称:</b><input type='text' size='60' datatype='*' sucmsg=' ' style='height:25px;font-size:15px;' placeholder='请输入项目名称' name='itemName'>&nbsp;&nbsp;&nbsp;<b>现金支出:</b><input type='text' size='30' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' style='height:25px;font-size:15px;' placeholder='请输入现金支出额' name='itemCash'>&nbsp;&nbsp;&nbsp;<b>银行支出:</b><input type='text' size='30' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' style='height:25px;font-size:15px;' placeholder='请输入银行支出额' name='itemBank'>&nbsp;&nbsp;&nbsp;<label class='glyphicon glyphicon-remove' onclick=delItem("+"'"+flag+"'"+")>删除</label><input type='text' name='itemId' hidden='hidden' value='-1'><br></dd>");
		itemNum = itemNum + 1;
	});
	
	$("#extenstionTravelLine").click(function(){
		//alert("ok");
		var flag2 = "new2_" + itemNum2;
		$("#extenstionTravelLineItem").append(
			"<dd id='"+flag2+"'>"+
			"单位:<input type='text' placeholder='单位' size='20' style='height:25px;font-size:15px;' datatype='*' sucmsg=' ' name='unit'>"+
			"职工:<input type='text' placeholder='职工' size='10' style='height:25px;font-size:15px;' datatype='*' sucmsg=' ' name='staffName'>"+
			"家属(人数):<input type='text' placeholder='家属' size='10' style='height:25px;font-size:15px;' datatype='*' sucmsg=' ' name='family' onkeydown='return checkNumber(event);'>"+
			"出行时间:<input datatype='*' sucmsg=' ' name='travelTime' type='text' class='input date' onfocus='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;})' >"+
			"合同价格:<input type='text' class='moneytxt' placeholder='合同价格' size='10' style='height:25px;font-size:15px;' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='contractPrice'>"+
			"前期余额:<input type='text' class='moneytxt' placeholder='前期余额' size='10' style='height:25px;font-size:15px;' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='previousBalance'>"+
			"优惠金额:<input type='text' class='moneytxt' placeholder='应优惠金额' size='10' style='height:25px;font-size:15px;' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='discountAmount'>"+
			"本次余额:<input type='text' class='moneytxt' placeholder='本次余额' size='10' style='height:25px;font-size:15px;' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='currentBalance'>"+
			"<br/>"+
			"银行收入:<input type='text' class='moneytxt' placeholder='银行收入' size='10' style='height:25px;font-size:15px;' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='bankMoney'>"+
			"现金收入:<input type='text' class='moneytxt' placeholder='现金收入' size='10' style='height:25px;font-size:15px;' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='cashMoney'>"+
			"挂账收入:<input type='text' class='moneytxt' placeholder='挂账收入' size='10' style='height:25px;font-size:15px;' datatype='/^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='creditMoney'>"+
			"成本:<input type='text' class='moneytxt' placeholder='成本' size='10' style='height:25px;font-size:15px;' datatype='/^(([-1|1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$/' sucmsg=' ' name='costing'>"+
			"签字人:<input type='text' placeholder='签字人' size='10' style='height:25px;font-size:15px;' datatype='*' sucmsg=' ' name='siger'>"+
			"出行线路:<input type='text' placeholder='出行线路' size='70' style='height:25px;font-size:15px;' datatype='*' sucmsg=' ' name='travelLine'>"+
		    "<label class='glyphicon glyphicon-remove' onclick='delItem2("+"\""+flag2+"\""+")'>删除</label><input type='text' name='itemId2' value='-1' hidden='hidden'><br><br>"+
		    "<DIV style='BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px'></DIV><br/>"
		    +"</dd>"	
		);
		
		itemNum = itemNum + 1;
		itemNum2 = itemNum2 + 1;
	});
	
	
	
	replacemoneystr();

});



</script>
</head>
<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;">
<table class="ui_border">
<tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog">
<tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr>
</tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr>
</tbody>
</table></div>
<form method="post" action="/Verwalter/client/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/client/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>客户管理</span>
  <i class="arrow"></i>
  <span>客户编辑</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap">
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">客户信息</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
    <dl>
        <dt>客户姓名:</dt>
        <dd>
        	<input type="text" hidden="hidden" name="clientId" value="<#if tdClient??>${tdClient.id!''}</#if>">
        	<input type="text" placeholder="请输入客户姓名" size="30"  style="height:25px;font-size:15px;" name="name" value="<#if tdClient??>${tdClient.name!''}</#if>" datatype="*" sucmsg=" ">
        </dd>
    </dl>
    <#--
    <dl>
        <dt>客户类别:</dt>
        <dd>
        	<input type="text" placeholder="请输入客户类别" size="30" style="height:25px;font-size:15px;" name="typeName" value="<#if tdClient??>${tdClient.typeName!''}</#if>" datatype="*" sucmsg=" ">
        </dd>
    </dl>
    -->
    <dl>
        <dt>客户电话:</dt>
       <dd>
        	<input type="text" placeholder="请输入客户电话" size="30" style="height:25px;font-size:15px;" name="phoneNum" value="<#if tdClient??>${tdClient.phoneNum!''}</#if>" datatype="m" sucmsg=" ">
        </dd>
    </dl>
    <#--
    <dl>
        <dt>客户描述:</dt>
        <dd>
        	<textarea placeholder="请输入客户备注" cols="50" rows="5" style="font-size:15px;" name="description"><#if tdClient??>${tdClient.description!''}</#if></textarea>
        </dd>
    </dl>
    -->
    <dl>
        <dt>所属员工:</dt>
        <dd>
        	<input type="text" size="30" style="height:25px;font-size:15px;" name="managerName" value="${managerName!''}" readonly="readonly">*只读
        </dd>
    </dl>
    <#--
    <dl>
        <dt>拥有名额的车站:</dt>
        <dd>
        	<input type="text" placeholder="请输入拥有金额的车站" size="60" style="height:25px;font-size:15px;" name="stationsWithMoney" value="<#if tdClient??>${tdClient.stationsWithMoney!''}</#if>">
        </dd>
    </dl>
    -->
    <dl>
        <dt>收入金额(银行汇总):</dt>
        <dd>
        	<input type="text" class="moneytxt" placeholder="请输入银行收入金额" size="40" style="height:25px;font-size:15px;" name="inMoneyFromBank" value="<#if tdClient??>${tdClient.inMoneyFromBank!0}</#if>" <#--datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"--> sucmsg=" " readonly="readonly">*只读
        </dd>
    </dl>
    <dl>
        <dt>收入金额(现金汇总):</dt>
        <dd>
        	<input type="text" class="moneytxt" placeholder="请输入现金收入金额" size="40" style="height:25px;font-size:15px;" name="inMoneyFromCash" value="<#if tdClient??>${tdClient.inMoneyFromCash!0}</#if>" <#--datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"--> sucmsg=" " readonly="readonly">*只读
        </dd>
    </dl>
    <dl>
        <dt>收入金额(挂账汇总):</dt>
        <dd>
        	<input type="text" class="moneytxt" placeholder="请输入挂账收入金额" size="40" style="height:25px;font-size:15px;" name="inMoneyFromTransfer" value="<#if tdClient??>${tdClient.inMoneyFromTransfer!0}</#if>" <#--datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"--> sucmsg=" " readonly="readonly">*只读
        </dd>
    </dl>
    <dl id="outItem">
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<label class="btn-primary glyphicon-plus" id="extenstionItem">添加支出项目</label><br>
    	<#if tdClient?? && tdClient.outMoneyItemList??>
    		<#list tdClient.outMoneyItemList as item>
    			<dd id="old_${item.id}"><b>项目名称:</b><input type='text' size='60' datatype="*" sucmsg=" " style='height:25px;font-size:15px;' placeholder='请输入项目名称' name='itemName' value="${item.title}">&nbsp;&nbsp;&nbsp;<b>现金支出:</b><input type='text' size='30' datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" " style='height:25px;font-size:15px;' class="moneytxt" placeholder='请输入现金支出额' name='itemCash' value="${item.outMoneyFromCash}">&nbsp;&nbsp;&nbsp;<b>银行支出:</b><input type='text' size='30' datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" " style='height:25px;font-size:15px;' class="moneytxt" placeholder='请输入银行支出额' name='itemBank' value="${item.outMoneyFromBank}" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">&nbsp;&nbsp;&nbsp;<label class="glyphicon glyphicon-remove" onclick="delItem('old_${item.id}')">删除</label><input type="text" name="itemId" value="${item.id}" hidden="hidden"><br></dd>
    		</#list>	
    	</#if>
    </dl>
    <dl>
        <dt>成本:</dt>
        <dd>
        	<input type="text" class="moneytxt" placeholder="请输入客户成本" size="40" style="height:25px;font-size:15px;" name="cost" value="<#if tdClient??>${tdClient.cost!0}</#if>" <#--datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"--> sucmsg=" " readonly="readonly">*只读
        </dd>
    </dl>
    <dl>
        <dt>利润:</dt>
        <dd>
        	<input type="text" class="moneytxt" placeholder="请输入客户利润" size="40" style="height:25px;font-size:15px;" name="profits" value="<#if tdClient??>${tdClient.profits!0}</#if>" <#--datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"--> sucmsg=" " readonly="readonly">*只读
        </dd>
    </dl>
    <dl>
        <dt>营业收入:</dt>
        <dd>
        	<input type="text" class="moneytxt" placeholder="请输入营业收入" size="40" style="height:25px;font-size:15px;" name="operationIncome" value="<#if tdClient??>${tdClient.operationIncome!0}</#if>" <#--datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"--> sucmsg=" " readonly="readonly">*只读
        </dd>
    </dl>
    <#--
    <dl>
        <dt>录入人:</dt>
        <dd>
        	<input type="text" placeholder="请输入客户录入人" style="height:25px;font-size:15px;" name="editor" value="<#if tdClient??>${tdClient.editor!''}</#if>" datatype="*" sucmsg=" ">
        </dd>
    </dl>
    -->
    
    <#--站务中心职工出游线路-->
    <br/><br/>
    <div id="extenstionTravelLineItem">
	    <label class="btn-primary glyphicon-plus" id="extenstionTravelLine">添加站务中心职工出游线路</label><br>
	    <br/>
	    
	    <#if tdClient?? && tdClient.tdStationCenterStaffTravelLineList??>
    		<#list tdClient.tdStationCenterStaffTravelLineList as item>
				<dd id="old2_${item.id}">
					单位:<input type="text" placeholder="单位" size="20" style="height:25px;font-size:15px;" name="unit" value="${item.unit}">
					职工:<input type="text" placeholder="职工" size="10" style="height:25px;font-size:15px;" name="staffName" value="${item.staffName}">        
					家属(人数):<input type="text" placeholder="家属" size="10" style="height:25px;font-size:15px;" name="family" value="${item.family}" onkeydown="return checkNumber(event);">
					出行时间:<input name="travelTime" type="text" value="${item.travelTime}" class="input date" onfocus="WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;})" >
					合同价格:<input type="text" class="moneytxt" placeholder="合同价格" size="10" style="height:25px;font-size:15px;" name="contractPrice" value="${item.contractPrice}" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
					前期余额:<input type="text" class="moneytxt" placeholder="前期余额" size="10" style="height:25px;font-size:15px;" name="previousBalance" value="${item.previousBalance}" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
					优惠金额:<input type="text" class="moneytxt" placeholder="应优惠金额" size="10" style="height:25px;font-size:15px;" name="discountAmount" value="${item.discountAmount}" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
					本次余额:<input type="text" class="moneytxt" placeholder="本次余额" size="10" style="height:25px;font-size:15px;" name="currentBalance" value="${item.currentBalance}" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" "><br/>
					银行收入:<input type="text" class="moneytxt" placeholder="银行收入" size="10" style="height:25px;font-size:15px;" name="bankMoney" value="${item.bankMoney!0}" datatype="/^(([-1|1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
					现金收入:<input type="text" class="moneytxt" placeholder="现金收入" size="10" style="height:25px;font-size:15px;" name="cashMoney" value="${item.cashMoney!0}" datatype="/^(([-1|1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
					挂账收入:<input type="text" class="moneytxt" placeholder="挂账收入" size="10" style="height:25px;font-size:15px;" name="creditMoney" value="${item.creditMoney!0}" datatype="/^(([-1|1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
					成本:<input type="text" class="moneytxt" placeholder="成本" size="10" style="height:25px;font-size:15px;" name="costing" value="${item.costing!0}" datatype="/^(([-1|1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
					签字人:<input type="text" placeholder="签字人" size="10" style="height:25px;font-size:15px;" name="siger" value="${item.siger}">
					出行线路:<input type="text" placeholder="出行线路" size="70" style="height:25px;font-size:15px;" name="travelLine" value="${item.travelLine}">
				    <label class="glyphicon glyphicon-remove" onclick="delItem2('old2_${item.id}')">删除</label><input type="text" name="itemId2" value="${item.id}" hidden="hidden"><br/><br/>
				    <#--<HR style="border:3 dashed #987cb9;" width="80%" color=#00ffff SIZE=1><br/>-->
				    <DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV><br/>
				</dd>  
    		</#list>	
    	</#if>
	    
    </div>
</div>
<br>


    
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

function replacemoneystr(){
	var strs = $(".moneytxt").each(function(){
		var str = $(this).val().replace(/,/g, "");
		$(this).val(str);
	});
}

</script> 

</body>
</html>