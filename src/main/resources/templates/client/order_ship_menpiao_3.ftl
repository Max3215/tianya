
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title><#if goods??>${goods.seoTitle!''}-</#if>填写信息</title>
<meta name="keywords" content="<#if goods??>${goods.seoKeywords!''}</#if>">
<meta name="description" content="<#if goods??>${goods.seoDescription!''}</#if>">
<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
<link rel="shortcut icon" href="/client/images/tianya.ico"><link rel="stylesheet" type="text/css" href="/client/css/base.css"><link rel="stylesheet" type="text/css" href="/client/css/common.css"><link rel="stylesheet" type="text/css" href="/client/css/f_cruise.css"><script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script><script src="/client/js/rich_lee.js" type="text/javascript"></script><script src="/client/js/Validform_v5.3.2_min.js" type="text/javascript"></script><script type="text/javascript">    window.onload = function () {        act_nav();    };
    
    function chbox11()
	 {
	  var sign = 0;
	  var inputs = document.getElementsByTagName('input');//获取所有的input标签对象。
	  for(var i=0;i<inputs.length;i++){
	    var obj = inputs[i];
	    if(obj.type=='checkbox'){
	     if(obj.checked==true){
	      sign=1;
	      }
	    } 
	  }
	  if(sign==0)//没有被选择项
	  {
	    alert("亲，请您选择旅客哦。");
		return;
	   }else
	   {
	   	window.location.href="/order/ship4";
	   }
	 }
    
    </script></head><body><!-- ********************头部******************** --><#include "/client/common_header.ftl" /><!-- ********************头部-结束******************** --><!-- ********************中间内容Start******************** --><div class="wrapper">    <!-- 进度条-->    <div class="progress_warp">
        <div class="progress_line p_line_3" style="width: 50%;"></div>        <div class="progress_txt">            <#--<label>选择舱房</label>            <label>可选服务</label>-->            <label>填写信息</label>            <label>提交并支付</label>        </div>    </div>    <!-- 产品简述-->    <div class="product_desc">        <div class="left_desc">            <div class="d_title">                <a href="/detail/${goods.id?c}" class="title">${goods.title!''} </a>
                <a href="/detail/${goods.id?c}" class="check_detail">查看产品详情 >></a>            </div>            <div class="d_items">
            	<#if goods.categoryIdTree?? && goods.categoryIdTree?index_of("[1]",0) gt -1>
                <div class="d_item">
                    <label>已选航期：<font color="ff9100"><#if leaveDate??>${leaveDate!''}</#if></font></label>
                </div>
                </#if>
                <div class="d_item">
                    <label>出发日期：<font><#if leaveDate??>${leaveDate!''}</#if></font></label>
                </div>
            </div>
            <div class="d_items">
                <div class="d_item">                    <label>人数：<font>成人${chengrenshu!'0'}人、儿童${ertongshu!'0'}人</font></label>                </div>            </div>        </div>        <div class="right_price">
            <#--<#assign price=0.0>
            <#if totalPrice?? && price1??>
                <#assign price = totalPrice+price1>
            </#if>-->            <label>总计：<font>￥${price1?string('0.00')}元</font></label>            <a href="javascript:;" onclick="chbox11();">下一步</a>        </div>    </div><script type="text/javascript">$(document).ready(function(){    //初始化表单验证    $("#form1").Validform({        tiptype:4,     });})function subAddr(){   $("#form1").submit();}</script>    <!-- 旅客身份信息-->    <div class="passenger_info">        <h1>旅客身份信息</h1>        <div class="passengers_warp">            <ul class="content_top">                <li class="li01"><label>选择</label></li>                <li class="li02"><label>姓名</label></li>                <li class="li02"><label>性别</label></li>                <li class="li03"><label>证件类型</label></li>                <li class="li04"><label>身份证号</label></li>                <li class="li04"><label>手机号码</label></li>                <li class="li05"><label>操作</label></li>            </ul>            <form  action="/order/visitor/edit" id="form1">            <input type="hidden" value="<#if visitor??>${visitor.id?c}</#if>" name="id" >            <ul class="p_normal">                <li class="li01"><label><input type="checkbox" name="chk_passenger"/></label></li>                <li class="li02"><label><input type="text" class="p_name" value="<#if visitor??>${visitor.visitorName!''}</#if>" datatype="*2-6" sucmsg=" " errormsg=" " nullmsg=" "  name="visitorName"/></label></li>                <li class="li02"><label class="lbl_sex">                                        <input type="radio" checked name="sex"value="男" <#if visitor?? && visitor.sex=='男'>checked="checked"</#if>/>男</label>                                    <label class="lbl_sex"><input type="radio" name="sex" value="女" <#if visitor?? && visitor.sex=='女'>checked="checked"</#if>/>女</label>                </li>                <li class="li_double3"><label>                    <select name="certificateType" class="p_paper">                        <option value="护照" <#if visitor?? && visitor.certificateType=='护照'>selected="selected"</#if>>护照</option>                        <option value="身份证" <#if visitor?? && visitor.certificateType=='身份证'>selected="selected"</#if>>身份证</option>                    </select>                </label></li>                <li class="li04"><label><input type="text" name="certificateCardCode" class="txt_long" value="<#if visitor??>${visitor.certificateCardCode!''}</#if>" datatype="*"  sucmsg=" " errormsg=" " nullmsg=" "></label></li>                <li class="li04"><label><input type="text" name="telephone" class="txt_short" value="<#if visitor??>${visitor.telephone!''}</#if>" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/" sucmsg=" " errormsg=" " nullmsg=" "></label></li>                <li class="li05"><a href="javascript:;" onclick="subAddr();" class="p_btn">保存</a></li>            </ul>            </form>            <div id="address">                <#include "/client/order_ship_3_addr.ftl">            </div>        </div>        <div class="add_passenger">
        </div>    </div>    <!-- 清除页面浮动-->    <span class="clear"></span></div><!-- ********************中间内容End******************** --><!-- ********************底部******************** --><#include "/client/common_footer.ftl" /><!-- ********************底部-结束******************** --></body></html>
