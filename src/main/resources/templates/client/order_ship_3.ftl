
<!DOCTYPE html>
<meta name="keywords" content="<#if goods??>${goods.seoKeywords!''}</#if>">
<meta name="description" content="<#if goods??>${goods.seoDescription!''}</#if>">
<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
<link rel="shortcut icon" href="/client/images/tianya.ico">
		// 手机号
		var pattern_phone = /^1[34578]\d{9}$/;
		// 身份证
		var pattern_id = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		// 护照格式1
		var pattern_passport1 = /^[a-zA-Z]{5,17}$/;
		// 护照格式2
		var pattern_passprot2 = /^[a-zA-Z0-9]{5,17}$/;
        // 监听身份证号和护照键盘事件
        $("#idCode").on("keydown", function(e){
        	//alert(e.keyCode);
        	if((e.keyCode != 88 && e.keyCode < 96) || e.keyCode > 105){
        		alert("请输入数字或字母x");
        		$("#idCode").val("");
        		return;
        	}
			
		});
		// 监听手机号键盘事件 
		 $("#phoneId").on("keydown", function(e){
        	if(e.keyCode < 96 || e.keyCode > 105){
        		alert("请输入数字");
        		$("#phoneId").val("");
        		return;
        	}
			
		});
		
		
		$("#phoneId").on("focusout", function(){
			if($("#phoneId").val() == "" || $("#phoneId").val() == null){
				alert("手机号码不能为空");
				return;
			}
			if(!pattern_phone.test($("#phoneId").val())){
				alert("手机号码格式错误");
				return;
			}
		});
		
		$("#idCode").on("focusout", function(){
			var idType = checkType();
			// alert(idType);
			if(idType == "身份证"){
				if($("#idCode").val() == "" || $("#idCode").val() == null){
					alert("身份证号码不能为空");
					return;
				}
				if(!pattern_id.test($("#idCode").val())){
					alert("身份证号码格式错误");
					return;
				}	
			}else{
				if($("#idCode").val() == "" || $("#idCode").val() == null){
					alert("护照号码不能为空");
					return;
				}
				if(!pattern_id.test($("#idCode").val())){
					alert("护照号码格式错误");
					return;
				}
			}
			
		});
		
		
		
    };
    
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
	 
	 function checkType(){
	 	var idType = $("#idNum").val();
	 	return idType;
	 }
    
    
	        <#if goods.categoryTitle?? &&  goods.categoryTitle=="船票">
	        	<label>填写信息</label>
	            <label>提交并支付</label>
	        <#else>
	            <label>选择舱房</label>
	        </#if>
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
                <#--
            	<#if goods.categoryIdTree?? && goods.categoryIdTree?index_of("[1]",0) gt -1>
                <div class="d_item">
                    <label>已选择房型：<font>标准內舱双人间</font><font>标准內舱双人间</font></label>
                </div>
                <div class="d_item">
                    <label>甲板：<font>8层</font></label>
                </div>
                </#if>
                -->
                    <label>人数：<font>成人${chengrenshu}人、儿童${ertongshu}人</font></label>
            <#assign price=0.0>
            <#if totalPrice?? && price1??>
                <#assign price = totalPrice+price1>
            </#if>
            <#if totalPrice??>
                <#assign price = totalPrice>
            </#if>
            
        </div>
</html>
