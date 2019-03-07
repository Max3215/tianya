<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑客户信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script src="/mag/js/jquery.cityselect.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form_customer").initValidform();

	 $("#address").citySelect({
        nodata:"none",
        <#if customer?? && customer.province??>prov: "${customer.province!''}",</#if>
        <#if customer?? && customer.city??>city: "${customer.city!''}",</#if>
        <#if customer?? && customer.disctrict??>dist: "${customer.disctrict!''}",</#if>
        required:false
    });

    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
})
</script>
</head>

<body class="mainbody">
<form name="form_user" method="post" action="/Verwalter/customer/save" id="form_user">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden"  name="id" value="<#if customer??>${customer.id?c!""}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/customer/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>客户管理</span>
  <i class="arrow"></i>
  <span>编辑客户信息</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
    <div class="content-tab-ul-wrap">
      <ul>
        <li><a href="javascript:;" onclick="tabs(this);" class="selected">基本资料</a></li>
        <#--
        <li><a href="javascript:;" onclick="tabs(this);">安全设置</a></li>
        <li><a href="javascript:;" onclick="tabs(this);">账户信息</a></li>
        -->
      </ul>
    </div>
  </div>
</div>

<!--基本资料-->
<div class="tab-content">
  <dl>
        <dt>姓名：</dt>
        <dd>
            <input name="username" value="<#if customer??>${customer.username!''}</#if>" type="text" maxlength="200" class="input normal" datatype="s6-20"  sucmsg=" " minlength="2">
            <span class="Validform_checktip">*
        </span></dd>
    </dl>
  <dl>
  <dl>
        <dt>手机号：</dt>
        <dd>
            <input name="mobile" value="<#if customer??>${customer.mobile!''}</#if>" type="text" maxlength="200" class="input normal" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/"  sucmsg=" " minlength="2">
            <span class="Validform_checktip">*
        </span></dd>
   </dl>
   <dl>
	    <dt>用户性别</dt>
	    <dd>
	      <div class="rule-multi-radio multi-radio">
	        <span style="display: none;">
	        <input type="radio" name="sex" value="保密" <#if !customer?? || !customer.sex?? || customer.sex=="保密">checked="checked"</#if>>
	        <label>保密</label>
	        <input type="radio" name="sex" value="男" <#if customer?? && customer.sex?? && customer.sex=="男">checked="checked"</#if>>
	        <label>男</label>
	        <input type="radio" name="sex" value="女" <#if customer?? && customer.sex?? && customer.sex=="女">checked="checked"</#if>>
	        <label>女</label>
	        </span>
	      </div>
	    </dd>
  </dl>
  <dl>
    <dt>出生日期</dt>
    <dd>
      <div class="input-date">
        <input name="birthday" type="text" value="<#if customer?? && customer.birthday??>${customer.birthday?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
        <i>*日期</i>
      </div>
    </dd>
  </dl>
  <dl>
    <dt>籍贯</dt>
    <dd>
        <div id="address">
           <select name="province" class="prov" style="width: 100px;" datatype="*"></select>*
           <select name="city" class="city" style="width: 100px;" datatype="*"></select>*
           <select name="disctrict" class="dist" style="width: 100px;" datatype="*0-10"></select>*
        </div>
    </dd>
  </dl>
  <dl>
        <dt>地址：</dt>
        <dd>
            <input name="detailAddress" value="<#if customer??>${customer.detailAddress!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
         </dd>
   </dl>
  <dl>
    <dt>客户类型</dt>
    <dd>
      <div class="rule-multi-radio">
        <span id="rblStatus">
            <input type="radio" name="type" value="天涯"  <#if !customer?? || customer.statusId?? && customer.statusId=="天涯">checked="checked"</#if>>
            <label>天涯</label>
            <input type="radio" name="type" value="商旅"  <#if customer?? && customer.statusId?? && customer.statusId=="商旅">checked="checked"</#if>>
            <label>商旅</label>
        </span>
      </div>
      <span class="Validform_checktip"></span>
    </dd>
  </dl>
   <dl>
    <dt>婚姻状况</dt>
    <dd>
      <div class="rule-multi-radio">
        <span id="rblStatus">
            <input type="radio" name="maritalStatus" value="未婚"  <#if !customer?? || customer.maritalStatus?? && customer.maritalStatus=="未婚">checked="checked"</#if>>
            <label>未婚</label>
            <input type="radio" name="maritalStatus" value="已婚"  <#if customer?? && customer.maritalStatus?? && customer.maritalStatus=="已婚">checked="checked"</#if>>
            <label>已婚</label>
        </span>
      </div>
      <span class="Validform_checktip"></span>
    </dd>
  </dl>
  <dl>
    <dt>政治面貌</dt>
    <dd>
      <div class="rule-multi-radio">
        <span id="rblStatus">
            <input type="radio" name="political" value="群众"  <#if !customer?? || customer.political?? && customer.political=="群众">checked="checked"</#if>>
            <label>群众</label>
            <input type="radio" name="political" value="团员"  <#if customer?? && customer.political?? && customer.political=="团员">checked="checked"</#if>>
            <label>团员</label>
            <input type="radio" name="political" value="党员"  <#if customer?? && customer.political?? && customer.political=="党员">checked="checked"</#if>>
            <label>党员</label>
        </span>
      </div>
      <span class="Validform_checktip"></span>
    </dd>
  </dl>
  <dl>
        <dt>学历：</dt>
        <dd>
            <input name="education" value="<#if customer??>${customer.education!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
        </dd>
   </dl>
  <dl>
        <dt>身份证：</dt>
        <dd>
            <input name="identity" value="<#if customer??>${customer.identity!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
        </dd>
   </dl>
   <dl>
        <dt>QQ：</dt>
        <dd>
            <input name="qq" value="<#if customer??>${customer.qq!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
        </dd>
   </dl>
   <dl>
        <dt>邮箱：</dt>
        <dd>
            <input name="email" value="<#if customer??>${customer.email!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
        </dd>
   </dl>
   <dl>
        <dt>工作单位：</dt>
        <dd>
            <input name="company" value="<#if customer??>${customer.company!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
        </dd>
   </dl>
    <dl>
        <dt>民族：</dt>
        <dd>
            <input name="nation" value="<#if customer??>${customer.nation!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
        </dd>
   </dl>
   <dl>
        <dt>爱好：</dt>
        <dd>
            <input name="hobby" value="<#if customer??>${customer.hobby!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2">
            <span class="Validform_checktip"></span>
        </dd>
   </dl>
   <dl>
    <dt>备注</dt>
    <dd>       
        <textarea name="remark" rows="2" cols="20" class="input normal"><#if customer??>${customer.remark!''}</#if></textarea>
        <span class="Validform_checktip"></span>
    </dd>
  </dl> 
  
</div>
<!--/基本资料-->

<!--安全设置-->
<#--
<div class="tab-content" style="display:none;">  
  <dl>
    <dt>登录密码</dt>
    <dd><input name="password" type="password" value="<#if customer??>${customer.password!''}</#if>" class="input normal" datatype="*6-20" nullmsg="请设置密码" errormsg="密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*登录的密码，至少6位</span></dd>
  </dl>
  <dl>
    <dt>确认密码</dt>
    <dd><input name="password1" type="password" value="<#if customer??>${customer.password!''}</#if>" class="input normal" datatype="*" recheck="password" nullmsg="请再输入一次密码" errormsg="两次输入的密码不一致" sucmsg=" " value=""> <span class="Validform_checktip">*再次输入密码</span></dd>
  </dl>
  <dl>
    <dt>邮箱账号</dt>
    <dd><input name="email" type="text" value="<#if customer??>${customer.email!""}</#if>" id="txtEmail" class="input normal" ignore="ignore" datatype="e" sucmsg=" " > <span class="Validform_checktip">*取回密码时用到</span></dd>
  </dl>
  <dl>
    <dt>手机号码</dt>
    <dd><input name="mobile" type="text" value="<#if customer??>${customer.mobile!""}</#if>" class="input normal" ignore="ignore" datatype="m" sucmsg=" " ><span class="Validform_checktip">*</span></dd>
  </dl>
  <dl>
    <dt>累计消费额</dt>
    <dd><span><#if customer??>${customer.totalSpendCash!""}</#if></span></dd>
  </dl>
  <dl>
    <dt>用户等级</dt>
    <dd><input name="customerLevelId" type="text" value="<#if customer??>${customer.customerLevelId!""}</#if>" class="input normal" datatype="n0-2" errormsg="请输入正确的等级" sucmsg=" " > <span class="Validform_checktip"></span></dd>
  </dl>
  <dl>
    <dt>用户等级名称</dt>
    <dd><span><#if customer??>${customer.customerLevelTitle!"0"}</#if></span></dd>
  </dl>
  <dl>
    <dt>上次登录时间</dt>
    <dd><span><#if customer??>${customer.lastLoginTime!""}</#if></span></dd>
  </dl>
  <dl>
    <dt>上次登录IP</dt>
    <dd><span><#if customer??>${customer.lastLoginIp!""}</#if></span></dd>
  </dl>
  <dl>
    <dt>咨询总数</dt>
    <dd><span><#if customer??>${customer.totalConsults!"0"}</#if></span></dd>
  </dl>
  <dl>
    <dt>评论总数</dt>
    <dd><span><#if customer??>${customer.totalComments!"0"}</#if></span></dd>
  </dl>
  <dl>
    <dt>退换货总数</dt>
    <dd><span><#if customer??>${customer.totalReturns!"0"}</#if></span></dd>
  </dl>
  <dl>
    <dt>订单数量</dt>
    <dd><span><#if customer??>${customer_total_orders!"0"}</#if></span></dd>
  </dl>
</div>-->
<!--/安全设置-->
<#--
<div class="tab-content" style="display:none;">
  <dl>
    <dt>下级用户总数</dt>
    <dd><input name="totalLowercustomers" type="text" id="txtPay_Password" class="input normal" nullmsg="请设置支付密码" errormsg="支付密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*平台内支付的密码，至少6位</span></dd>
  </dl>
  <dl>
    <dt>用户返现总数</dt>
    <dd><input name="totalCashRewards" type="text" id="txtPay_Password" class="input normal" nullmsg="请设置支付密码" errormsg="支付密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*平台内支付的密码，至少6位</span></dd>
  </dl>
  <dl>
    <dt>银行卡号</dt>
    <dd><input name="bankCardCode" type="text" id="txtPay_Password" class="input normal" nullmsg="请设置支付密码" errormsg="支付密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*平台内支付的密码，至少6位</span></dd>
  </dl>
  <dl>
    <dt>银行名称</dt>
    <dd><input name="bankTitle" type="text" id="txtPay_Password" class="input normal" nullmsg="请设置支付密码" errormsg="支付密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*平台内支付的密码，至少6位</span></dd>
  </dl>
  <dl>
    <dt>银行卡已验证</dt>
    <dd><input name="txtPay_Password" type="text" id="txtPay_Password" class="input normal" nullmsg="请设置支付密码" errormsg="支付密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*平台内支付的密码，至少6位</span></dd>
  </dl>
</div>
-->
<!--/账户信息-->


<!--借款标的-->

<!--/借款标的-->

<!--投资标的-->

<!--/投资标的-->

<!--资金明细-->

<!--/资金明细-->

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