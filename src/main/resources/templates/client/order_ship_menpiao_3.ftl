
<!DOCTYPE html>
<meta name="keywords" content="<#if goods??>${goods.seoKeywords!''}</#if>">
<meta name="description" content="<#if goods??>${goods.seoDescription!''}</#if>">
<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
<link rel="shortcut icon" href="/client/images/tianya.ico">
    
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
    
    
        <div class="progress_line p_line_3" style="width: 50%;"></div>
                <a href="/detail/${goods.id?c}" class="check_detail">查看产品详情 >></a>
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
                <div class="d_item">
            <#--<#assign price=0.0>
            <#if totalPrice?? && price1??>
                <#assign price = totalPrice+price1>
            </#if>-->
        </div>