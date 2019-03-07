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

<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>

<script type="text/javascript" src="/mag/js/stafftravel.js"></script>
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
    
    <#if staffTravel??>
        changeUnit(${staffTravel.type!'0'});
    </#if>
    
});


function findStaff(param)
{
    var radionum = document.getElementsByName("type");
    var type =0;
    for(var i=0;i<radionum.length;i++){
        if(radionum[i].checked){
             type = radionum[i].value
         }
    }
    
    if(type==1)
    {
        return ;
    }
    
    // 获取选择的单位，如果是散客，则跳过查找职工表
    var obj = document.getElementById("unit"); //定位id
    var value = obj.options[obj.options.selectedIndex].value;
    
    // 异步查询职工表，计算当前输入职工的优惠总额、前期余额
    $.ajax({
        type : "post",
        url : "/Verwalter/staffTravel/countAmount",
        data : {"param":param,"company":value} ,
        success : function(data)
        {
            if(data.code==1){
                $("#previousBalance").attr("value",data.previous);
                $("#discountAmount").attr("value",data.discount);
                
            }else{
                SimplePop.alert(data.msg);
            }
        }
    })
}
</script>
</head>

<body class="mainbody">
<form name="form_user" method="post" action="/Verwalter/staffTravel/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden"  name="id" value="<#if staffTravel??>${staffTravel.id?c!""}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/staffTravel/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>旅游线路</span>
  <i class="arrow"></i>
  <span>线路编辑</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
    <div class="content-tab-ul-wrap">
      <ul>
        <li><a href="javascript:;" onclick="tabs(this);" class="selected">出游线路</a></li>
      </ul>
    </div>
  </div>
</div>

<!--基本资料-->
<div class="tab-content">
    <dl>
        <dt>团队类型：</dt>
        <dd>
          <div class="rule-multi-radio">
            <span id="rblStatus">
                <input type="radio" name="type" value="0" datatype="n" <#if !staffTravel?? || !staffTravel.type?? || staffTravel.type==0>checked="checked"</#if> onclick="changeUnit(0);">
                <label>内部职工</label>
                <input type="radio" name="type" value="1" datatype="n" <#if staffTravel?? && staffTravel.type?? && staffTravel.type==1>checked="checked"</#if> onclick="changeUnit(1);">
                <label>散客</label>
            </span>
          </div>
          <span class="Validform_checktip"></span>
        </dd>
  </dl>
 <script>
 function changeUnit(type){
    
    $(".staffuCost").each(function(){
        if(type==0)
        {
            $(this).fadeIn();
        }else if(type==1){
            $(this).fadeOut();
        }
    });         
    if(type==0){
        $("#unitSelect").css("display","block");
        $("#unitinput").css("display","none");
    }else if(type==1){
        $("#unitSelect").css("display","none");
        $("#unitinput").css("display","block");
    }
    
 }
 </script>
   <dl>
        <dt>团队编号：</dt>
        <dd>
            <input name="teamNumber" value="<#if staffTravel??>${staffTravel.teamNumber!''}</#if>" type="text" maxlength="200" class="input normal"   sucmsg=" " minlength="2" >
            <span class="Validform_checktip"></span>
        </dd>
  </dl>
  <dl>
        <dt>单位：</dt>
        <dd>
            <input name="unit" id="unitinput" <#if staffTravel?? && staffTravel.type==1>style="display: block;"<#else>style="display: none;"</#if>  value="<#if staffTravel??>${staffTravel.unit!''}</#if>" type="text" maxlength="200" class="input normal"  sucmsg=" " minlength="2">
            <div class="menu-list" id="unitSelect" <#if !staffTravel?? || staffTravel.type==0>style="display: block;"</#if>>
                <div class="rule-single-select">
                    <select name="unitId" id="unit">
                        <option value="1" <#if staffTravel?? && staffTravel.unitId==1>selected="selected"</#if>>重庆交通运业有限责任公司</option>
                        <option value="2" <#if staffTravel?? && staffTravel.unitId==2>selected="selected"</#if>>重庆交通运业有限责任公司渝北双凤桥汽车站</option>
                        <option value="3" <#if staffTravel?? && staffTravel.unitId==3>selected="selected"</#if>>重庆交通运业有限责任公司重庆汽车站</option>
                        <option value="4" <#if staffTravel?? && staffTravel.unitId==4>selected="selected"</#if>>重庆交通运业有限责任公司陈家坪汽车站</option>
                        <option value="5" <#if staffTravel?? && staffTravel.unitId==5>selected="selected"</#if>>重庆交通运业有限责任公司龙头寺汽车站</option>
                        <option value="6" <#if staffTravel?? && staffTravel.unitId==6>selected="selected"</#if>>重庆交通运业有限责任公司富丽大酒店</option>
                        <option value="7" <#if staffTravel?? && staffTravel.unitId==7>selected="selected"</#if>>重庆交通运业有限责任公司站务中心汽车北站</option>
                        <option value="8" <#if staffTravel?? && staffTravel.unitId==8>selected="selected"</#if>>重庆交通运业有限责任公司站务中心南坪汽车站</option>
                        <option value="9" <#if staffTravel?? && staffTravel.unitId==9>selected="selected"</#if>>重庆市公路客运联网售票中心有限公司</option>
                        <option value="10" <#if staffTravel?? && staffTravel.unitId==10>selected="selected"</#if>>重庆交通运业有限责任公司站务中心长途汽车站</option>
                        <option value="11" <#if staffTravel?? && staffTravel.unitId==11>selected="selected"</#if>>重庆迅为四公里交通换乘枢纽有限公司</option>
                        <option value="12" <#if staffTravel?? && staffTravel.unitId==12>selected="selected"</#if>>重庆天涯国际旅行社有限公司</option>
                        <option value="13" <#if staffTravel?? && staffTravel.unitId==13>selected="selected"</#if>>重庆交通运业有限责任公司商旅服务分公司</option>
                        <option value="14" <#if staffTravel?? && staffTravel.unitId==14>selected="selected"</#if>>重庆交通运业有限责任公司稽查大队</option>
                        <option value="15" <#if staffTravel?? && staffTravel.unitId==15>selected="selected"</#if>>重庆交通运业有限责任公司交运快递</option>
                        <option value="16" <#if staffTravel?? && staffTravel.unitId==16>selected="selected"</#if>>重庆交通运业有限责任公司富苑宾馆</option>
                        <option value="18" <#if staffTravel?? && staffTravel.unitId==18>selected="selected"</#if>>重庆交通运业有限责任公司巴南龙洲湾汽车站</option>
                    </select>
                </div>
           	</div>
           	<span class="Validform_checktip"></span>
        </dd>
  </dl>
  <dl>
        <dt>游客：</dt>
        <dd>
            <input name="staffName" value="<#if staffTravel??>${staffTravel.staffName!''}</#if>" type="text" maxlength="200" class="input normal" onblur="findStaff(this.value);"   sucmsg=" " minlength="2" >
            <span class="Validform_checktip">*内部职工请加相应单位后缀，多个名字用英文","隔开</span>
        </dd>
  </dl>
  <dl>
        <dt>家属(人数)：</dt>
        <dd>
            <input name="family" value="<#if staffTravel??>${staffTravel.family!''}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="n"  sucmsg=" " style="width:50px;">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl>
        <dt>实际收费人数：</dt>
        <dd>
            成人：<input name="adultNumber" id="adultNumber" value="<#if staffTravel??>${staffTravel.adultNumber!'0'}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="n"  sucmsg=" " style="width:50px;">
            儿童：<input name="childNumber" id="childNumber" value="<#if staffTravel??>${staffTravel.childNumber!'0'}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="n"  sucmsg=" " style="width:50px;">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl>
        <dt>收费标准：</dt>
        <dd>
            成人：<input name="aduitCost" id="aduitCost" value="<#if staffTravel?? && staffTravel.aduitCost??>${staffTravel.aduitCost?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"   sucmsg=" " style="width:50px;">￥
            儿童：<input name="childCost" id="childCost" value="<#if staffTravel?? && staffTravel.childCost??>${staffTravel.childCost?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"   sucmsg=" " style="width:50px;">￥
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl>
        <dt>出行线路：</dt>
        <dd>
            <textarea name="travelLine" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if staffTravel??>${staffTravel.travelLine!''}</#if></textarea>*
            <#--
            <input name="travelLine" value="<#if staffTravel??>${staffTravel.travelLine!''}</#if>" type="text" maxlength="200" class="input normal" datatype="*"  sucmsg=" " minlength="2" style="width:600px;">
            -->
            <span class="Validform_checktip"></span>
        </dd>
  </dl>
  <dl>
	    <dt>出行时间：</dt>
	    <dd>
	      <div class="input-date">
	        <input name="travelTime" datatype="*" type="text" value="<#if staffTravel?? && staffTravel.travelTime??>${staffTravel.travelTime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	        <i>日期</i>
	      </div>
	         <span class="Validform_checktip">*</span>
	    </dd>
  </dl>
  <dl>
        <dt>合同总价：</dt>
        <dd>
            <input id="contractPrice" name="contractPrice" value="<#if staffTravel?? && staffTravel.contractPrice??>${staffTravel.contractPrice?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  sucmsg=" "  style="width:100px;" readonly="readonly">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl class="staffuCost">
        <dt>前期余额(职工优惠)：</dt>
        <dd>
            <input id="previousBalance"  name="previousBalance" value="<#if staffTravel?? && staffTravel.previousBalance??>${staffTravel.previousBalance?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  sucmsg=" "  style="width:100px;">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl class="staffuCost">
        <dt>应优惠金额(职工优惠)：</dt>
        <dd>
            <input id="discountAmount"  name="discountAmount" value="<#if staffTravel?? && staffTravel.discountAmount??>${staffTravel.discountAmount?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  sucmsg=" "  style="width:100px;">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl class="staffuCost">
        <dt>本次余额(职工优惠)：</dt>
        <dd>
            <input id="currentBalance" name="currentBalance" value="<#if staffTravel?? && staffTravel.currentBalance??>${staffTravel.currentBalance?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  sucmsg=" "  style="width:100px;">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl>
        <dt>预付款：</dt>
        <dd>
            <input id="cashMoney" name="cashMoney"  value="<#if staffTravel?? && staffTravel.cashMoney??>${staffTravel.cashMoney?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  sucmsg=" "  style="width:100px;">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl>
        <dt>尾款：</dt>
        <dd>
            <input id="bankMoney" name="bankMoney"  value="<#if staffTravel?? && staffTravel.bankMoney??>${staffTravel.bankMoney?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  sucmsg=" "  style="width:100px;">
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
   <dl>
        <dt>成本：</dt>
        <dd>
            <input name="costing" value="<#if staffTravel?? && staffTravel.costing??>${staffTravel.costing?string('0.00')}<#else>0</#if>" type="text" maxlength="200" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  sucmsg=" "  style="width:100px;">
            <input name="premium" value="0" type="hidden" >
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl>
        <dt>签字：</dt>
        <dd>
            <input name="siger" value="<#if staffTravel??>${staffTravel.siger!'${manager}'}<#else>${manager}</#if>" type="text" maxlength="200" class="input normal" datatype="s0-20"  sucmsg=" " >
            <span class="Validform_checktip">*</span>
        </dd>
  </dl>
  <dl>
        <dt>备注</dt>
        <dd>
            <textarea name="remark" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if staffTravel??>${staffTravel.remark!""}</#if></textarea>
            <span class="Validform_checktip">255个字符以内</span>
        </dd>
    </dl>

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