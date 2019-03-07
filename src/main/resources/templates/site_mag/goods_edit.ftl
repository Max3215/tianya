<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/goods.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">


<link rel="stylesheet" href="/mag/style/simplepop.css">
<script src="/mag/js/simplepop.js"></script>

<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
    
    $("#techanYes_recommend").hide();
    $("#techanYes_adultPrice").hide();
    $("#qicheYes").hide();
    
    var gcid="[" + $("#gcId").val() + "]";
 
    var productBigTypeIdsStr = "";
    productBigTypeIdsStr = $("#productBigTypeIdsStr").val();
    if(productBigTypeIdsStr != ""){
    	productBigTypeIdsStr = productBigTypeIdsStr.split("_");
    	for(var i=0; i < productBigTypeIdsStr.length; i ++){
    		//alert($(this).val());
    		if(productBigTypeIdsStr[i].indexOf(gcid) > -1){
    			var s = productBigTypeIdsStr[i].split(",");
    			s = s[s.length-1]
    			//alert(s);
    			gcid = s;
    			$("#gcId").val(gcid);
    			//alert($("#gcId").val());
    			break;
    		}
    	}
    	
    }
    
    
    if(gcid=="[30]"){
        	$("#techanNo_recommend").hide();
        	$("#techanYes_recommend").show();
        	$("#techanNo_adultPrice").hide();
        	$("#techanYes_adultPrice").show();
        	$("#ertongqijia").hide();
        	$("#qicheYes").hide();
        }else if(gcid=="[29]"){
        	$("#qicheYes").show();
        	$("#techanNo_recommend").show();
        	$("#techanYes_recommend").hide();
        	$("#techanNo_adultPrice").hide();
        	$("#techanYes_adultPrice").hide();
        	$("#ertongqijia").hide();
        }else{
        	$("#techanNo_recommend").show();
        	$("#techanYes_recommend").hide();
        	$("#techanNo_adultPrice").show();
        	$("#techanYes_adultPrice").hide();
        	$("#ertongqijia").show();
        	$("#qicheYes").hide();
        }

    //初始化编辑器
    var editor = KindEditor.create('.editor', {
        width: '98%',
        height: '350px',
        resizeType: 1,
        uploadJson: '/Verwalter/editor/upload?action=EditorFile',
        fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
        allowFileManager: true
    });
    
    
    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
    
    $(".upload-show360").each(function () {
        $(this).InitSWFUpload_show360({ 
            btntext: "批量上传", 
            btnwidth: 66, 
            single: false, 
            water: true, 
            thumbnail: true, 
            filesize: "5120", 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf", 
            filetypes: "*.jpg;*.jpge;*.png;*.gif;" 
        });
    });

    //（缩略图）
    var txtPic = $("#txtImgUrl").val();
    if (txtPic == "" || txtPic == null) {
        $("#thumb_ImgUrl_show1").hide();
    }
    else {
        $("#thumb_ImgUrl_show1").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $("#thumb_ImgUrl_show1").show();
    }

    $("#txtImgUrl").blur(function () {
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $("#thumb_ImgUrl_show1").hide();
        }
        else {
            $("#thumb_ImgUrl_show1").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $("#thumb_ImgUrl_show1").show();
        }
    });
    
    //（行程图片缩略图）
    var txtPic = $("#txtImgUrl2").val();
    if (txtPic == "" || txtPic == null) {
        $("#thumb_ImgUrl_show2").hide();
    }
    else {
        $("#thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $("#thumb_ImgUrl_show2").show();
    }

    $("#txtImgUrl2").blur(function () {
        var txtPic = $("#txtImgUrl2").val();
        if (txtPic == "" || txtPic == null) {
            $("#thumb_ImgUrl_show2").hide();
        }
        else {
            $("#thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $("#thumb_ImgUrl_show2").show();
        }
    });
    
    //（签证资料缩略图）
    var txtPic = $("#txtImgUrl3").val();
    if (txtPic == "" || txtPic == null) {
        $("#thumb_ImgUrl_show3").hide();
    }
    else {
        $("#thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $("#thumb_ImgUrl_show3").show();
    }

    $("#txtImgUrl3").blur(function () {
        var txtPic = $("#txtImgUrl3").val();
        if (txtPic == "" || txtPic == null) {
            $("#thumb_ImgUrl_show3").hide();
        }
        else {
            $("#thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $("#thumb_ImgUrl_show3").show();
        }
    });
    
    //（抢拍缩略图）
    var flashPic = $("#flashSaleImage").val();
    if (flashPic == "" || flashPic == null) {
        $("#thumb_ImgUrl_show3").hide();
    }
    else {
        $("#thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='" + flashPic + "' bigsrc='" + flashPic + "' /></div></li></ul>");
        $("#thumb_ImgUrl_show3").show();
    }

    $("#flashSaleImage").blur(function () {
        var flashPic = $("#flashSaleImage").val();
        if (flashPic == "" || flashPic == null) {
            $("#thumb_ImgUrl_show3").hide();
        }
        else {
            $("#thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='" + flashPic + "' bigsrc='" + flashPic + "' /></div></li></ul>");
            $("#thumb_ImgUrl_show3").show();
        }
    });
    
    //（团购缩略图）
    var groupPic = $("#groupSaleImage").val();
    if (groupPic == "" || groupPic == null) {
        $("#thumb_ImgUrl_show2").hide();
    }
    else {
        $("#thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='" + groupPic + "' bigsrc='" + groupPic + "' /></div></li></ul>");
        $("#thumb_ImgUrl_show2").show();
    }

    $("#groupSaleImage").blur(function () {
        var groupPic = $("#groupSaleImage").val();
        if (groupPic == "" || groupPic == null) {
            $("#thumb_ImgUrl_show2").hide();
        }
        else {
            $("#thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='" + groupPic + "' bigsrc='" + groupPic + "' /></div></li></ul>");
            $("#thumb_ImgUrl_show2").show();
        }
    });
    
    //设置封面图片的样式
    $(".photo-list ul li .img-box img").each(function () {
        if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
            $(this).parent().addClass("selected");
        }
    });
    
    // 根据类型载入参数
    $("#categoryId").change(function(){
        $.ajax({
            url : '/Verwalter/goods/edit/parameter/'+$(this).val(),
            type : 'POST',
            success : function(res) {
                $("#id-param-sec").html(res);
            }
        });
        if($(this).val()==29)
        {
            $("#carType").show();
            $(".flag_29").show();
            $(".flag2_29").hide();
            
            //$("#flag_29").text("<#assign sel_29=1>");
            //window.location.reload();
        }else{
            $("#carType").hide();
            $(".flag_29").hide();
            $(".flag2_29").show();
            //$("#flag_29").text("<#assign sel_29=0>");
            //window.location.reload();
        }
        if ($(this).val()==1)
        {
        	$("#more-price").html();
        }
        //gl
        var bt = "["+$(this).val()+"]";
        var productBigTypeIdsStr = "";
        productBigTypeIdsStr = $("#productBigTypeIdsStr").val();
        if(productBigTypeIdsStr != ""){
        	productBigTypeIdsStr = productBigTypeIdsStr.split("_");
        	for(var i=0; i < productBigTypeIdsStr.length; i ++){
        		//alert($(this).val());
        		if(productBigTypeIdsStr[i].indexOf("["+$(this).val()+"]") > -1){
        			var s = productBigTypeIdsStr[i].split(",");
        			s = s[s.length-1]
        			//alert(s);
        			bt = s;
        			break;
        		}
        	}
        }
        
        
        if(bt=="[30]"){
        	$("#techanNo_recommend").hide();
        	$("#techanYes_recommend").show();
        	$("#techanNo_adultPrice").hide();
        	$("#techanYes_adultPrice").show();
        	$("#ertongqijia").hide();
        	$("#qicheYes").hide();
        }else if(bt=="[29]"){
        	$("#qicheYes").show();
        	$("#techanNo_recommend").show();
        	$("#techanYes_recommend").hide();
        	$("#techanNo_adultPrice").hide();
        	$("#techanYes_adultPrice").hide();
        	$("#ertongqijia").hide();
        }else{
        	$("#techanNo_recommend").show();
        	$("#techanYes_recommend").hide();
        	$("#techanNo_adultPrice").show();
        	$("#techanYes_adultPrice").hide();
        	$("#ertongqijia").show();
        	$("#qicheYes").hide();
        }
        
    });
    
    // 添加行程
    $("#addRoom3").click(function(){
        showDialogGift();
    });
    
    
    var totalPs = 0;
    <#if goods?? && goods.psList??>
    	totalPs = ${goods.psList?size};
    </#if>
    
    // 添加房型
    $("#addRoom").click(function(){
    	
	    var trHtml = '<tr class="td_c">'
            + '<td><input name="psList[' + totalPs + '].id?c" id="id" type="hidden" value=""></td>'
            + '<td><select name="psList[' + totalPs + '].type"><option>请选择</option><option value="内舱房">内舱房</option><option value="阳台房">阳台房</option><option value="岸上观光">岸上观光</option><option value="签证">签证</option></select></td>'
            + '<td><input type="text" name="psList[' + totalPs + '].title" class="td-input" value="" style="width:90%;"></td>'
            + '<td><input type="text" name="psList[' + totalPs + '].price1" class="td-input" value="" style="width:90%;"></td>'
            + '<td><input type="text" name="psList[' + totalPs + '].price2" class="td-input" value="" style="width:90%;"></td>'
            + '<td><i class="icon" style="text-indent:-9999999px;"></i>'
            + '<a title="删除" class="img-btn del operator" onclick="del_room(this);">删除</a>'
            + '</td>'
            + '</tr>';
        $("#var_box_ps").append(trHtml);
        totalPs = totalPs + 1;
    });
    
    $("#addRoom1").click(function(){
    	
	    var trHtml = '<tr class="td_c">'
            + '<td><input name="psList[' + totalPs + '].id?c" id="id1" type="hidden" value=""></td>'
            + '<td><select name="psList[' + totalPs + '].type"><option>请选择</option><option value="酒店">酒店</option><option value="交通方式">交通方式</option><option value="可选项目">可选项目</option><option value="签证">签证</option></select></td>'
            + '<td><input type="text" name="psList[' + totalPs + '].title" class="td-input" value="" style="width:90%;"></td>'
            + '<td><input type="text" name="psList[' + totalPs + '].price1" class="td-input" value="" style="width:90%;"></td>'
            + '<td><input type="text" name="psList[' + totalPs + '].price2" class="td-input" value="" style="width:90%;"></td>'
            + '<td><i class="icon" style="text-indent:-9999999px;"></i>'
            + '<a title="删除" class="img-btn del operator" onclick="del_room1(this);">删除</a>'
            + '</td>'
            + '</tr>';
        $("#var_box_ps1").append(trHtml);
        totalPs = totalPs + 1;
    });
    
   $("#addRoom2").click(function(){
   	
	    var trHtml = '<tr class="td_c">'
           + '<td><input name="psList[' + totalPs + '].id?c" id="id2" type="hidden" value=""></td>'
           + '<td><select name="psList[' + totalPs + '].type"><option>请选择</option><option value="酒店">酒店</option><option value="交通方式">交通方式</option><option value="可选项目">可选项目</option><option value="签证">签证</option></select></td>'
           + '<td><input type="text" name="psList[' + totalPs + '].title" class="td-input" value="" style="width:90%;"></td>'
           + '<td><input type="text" name="psList[' + totalPs + '].price1" class="td-input" value="" style="width:90%;"></td>'
           + '<td><input type="text" name="psList[' + totalPs + '].price2" class="td-input" value="" style="width:90%;"></td>'
           + '<td><i class="icon" style="text-indent:-9999999px;"></i>'
           + '<a title="删除" class="img-btn del operator" onclick="del_room2(this);">删除</a>'
           + '</td>'
           + '</tr>';
       $("#var_box_ps1").append(trHtml);
       totalPs = totalPs + 1;
   });
    
    var totalXc = 0;
    <#if goods?? && goods.xcList??>
    	totalXc = ${goods.xcList?size};
    </#if>
    
//    $("#addRoom3").click(function(){
//    	
//	    var trHtml = '<tr class="td_c">'
//            + '<td><input name="xcList[' + totalXc + '].id?c" id="id3" type="hidden" value=""></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].day" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].port" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].reachTime" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].leaveTime" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].title" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].description" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].eat" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><input type="text" name="xcList[' + totalXc + '].live" class="td-input" value="" style="width:90%;"></td>'
//            + '<td><i class="icon" style="text-indent:-9999999px;"></i>'
//            + '<a title="删除" class="img-btn del operator" onclick="del_room3(this);">删除</a>'
//            + '</td>'
//            + '</tr>';
//        $("#var_box_ps3").append(trHtml);
//        totalXc = totalXc + 1;
//    });
    
});
    //创建促销赠品窗口
    function showDialogGift(obj) {
        var objNum = arguments.length;
        var giftDialog = $.dialog({
            id: 'giftDialogId',
            lock: true,
            max: false,
            min: false,
            title: "行程",
            content: 'url:/Verwalter/goods/list/dialog/route?total=' + $(".soute").length,
            width: 850,
            height: 550
        });
        
        //如果是修改状态，将对象传进去
        if (objNum == 1) {
            giftDialog.data = obj;
        }
    }
    function showDialog(id) {
        var objNum = arguments.length;
        
        var giftDialog = $.dialog({
            id: 'giftDialogId',
            lock: true,
            max: false,
            min: false,
            title: "行程",
            content: 'url:/Verwalter/goods/list/dialog/route?id='+id+'&total=' +$(".soute").length,
            width: 850,
            height: 550
        });
        
    }    
    //删除促销赠品节点
    function delGiftNode(obj) {
        $(obj).parent().parent().remove();
    }

//删除房型
function del_room(obj) {
	var psid = $(obj).parent().parent().find("#id").eq(0).val();
	
	if ("" != psid)
	{
		$.ajax( {  
		    url:'/Verwalter/goods/ps/del',
		    data:{  
            	id : psid
		    },  
		    type:'post', 
		    dataType:'json',  
		    success:function(data) {  
		        if(data.status =="y" ){  
		            SimplePop.alert("删除成功！");  
		            $(obj).parent().parent().remove();
		        }else{  
		            SimplePop.alert(data.info);  
		        }  
		     } 
		});
	}
	else
	{
		$(obj).parent().parent().remove();
	}
}

//删除房型
function del_room1(obj) {
	var psid = $(obj).parent().parent().find("#id1").eq(0).val();
	
	if ("" != psid)
	{
		$.ajax( {  
		    url:'/Verwalter/goods/ps/del',
		    data:{  
            	id : psid
		    },  
		    type:'post', 
		    dataType:'json',  
		    success:function(data) {  
		        if(data.status =="y" ){  
		            SimplePop.alert("删除成功！");  
		            $(obj).parent().parent().remove();
		        }else{  
		            SimplePop.alert(data.info);  
		        }  
		     } 
		});
	}
	else
	{
		$(obj).parent().parent().remove();
	}
}
//删除房型
function del_room2(obj) {
	var psid = $(obj).parent().parent().find("#id2").eq(0).val();
	
	if ("" != psid)
	{
		$.ajax( {  
		    url:'/Verwalter/goods/ps/del',
		    data:{  
            	id : psid
		    },  
		    type:'post', 
		    dataType:'json',  
		    success:function(data) {  
		        if(data.status =="y" ){  
		            SimplePop.alert("删除成功！");  
		            $(obj).parent().parent().remove();
		        }else{  
		            SimplePop.alert(data.info);  
		        }  
		     } 
		});
	}
	else
	{
		$(obj).parent().parent().remove();
	}
}
//删除行程
function del_room3(obj) {
	var psid = $(obj).parent().parent().find("#id3").eq(0).val();
	
	if ("" != psid)
	{
		$.ajax( {  
		    url:'/Verwalter/goods/xc/del',
		    data:{  
            	id : psid
		    },  
		    type:'post', 
		    dataType:'json',  
		    success:function(data) {  
		        if(data.status =="y" ){  
		            SimplePop.alert("删除成功！");  
		            $(obj).parent().parent().remove();
		        }else{  
		            SimplePop.alert(data.info);  
		        }  
		     } 
		});
	}
	else
	{
		$(obj).parent().parent().remove();
	}
}

//删除商品组合节点
function del_goods_comb(obj) {
    $(obj).parent().parent().remove();
    $("#totalComb").val(parseInt($("#totalComb").val())-1);
}

  
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/goods/save" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" />
</div>
<input name="menuId" type="text" value='${mid!""}' style="display:none;">
<input name="channelId" type="text" value='${cid!""}' style="display:none">
<input name="id" id="goodsId" type="text" value='<#if goods??>${goods.id?c}</#if>' style="display:none">
<!--导航栏-->
<div class="location">
    <a href="/Verwalter/goods/list" class="back"><i></i><span>
        返回列表页</span></a> 
    <a href="/Verwalter/center" class="home">
    <i></i><span>首页</span></a>
    <i class="arrow"></i>
    <span>编辑信息</span>
</div>
<div class="line10">
</div>
<!--/导航栏-->
    <!--内容-->
    <input id="gcId" type="text" value="<#if goods??>${goods.categoryId}<#else>-1</#if>" hidden="hidden">
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab">
            <div class="content-tab-ul-wrap" >
                <ul>
                    <li><a href="javascript:;" onclick="tabs(this);" class="selected">基本信息</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">扩展选项</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">详细描述</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">价格</a></li>
                    <#if goods?? && (goods.categoryIdTree?contains("[1]")||goods.categoryIdTree?contains("[2]")||goods.categoryIdTree?contains("[5]"))>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">价目表</a></li>
                    </#if>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">SEO选项</a></li>
                </ul>
            </div>
        </div>
    </div>
    <#--<label id="flag_29"><#assign sel_29=0></label>-->
    <input type="text" value="${productBigTypeIdsStr!''}" hidden="hidden" id="productBigTypeIdsStr">
    <div id="id-first-tab" class="tab-content" style="display: block;">
        <dl>
            <dt>所属类别</dt>
            <dd>
                <div class="rule-single-select">
                    <select name="categoryId" id="categoryId" datatype="*" sucmsg=" ">
                        <#if !goods??>
                        <option value="">请选择类别...</option>
                        </#if>
                        <#if category_list??>
                            <#list category_list as c>
                                <option value="${c.id?c}" <#if goods?? && goods.categoryId==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </dd>
        </dl>
        <#if isAdd?? && isAdd>
        	<#if canUpdown?? && canUpdown>
		        <dl>
		            <dt>状态</dt>
		            <dd>
		                <div class="rule-multi-radio multi-radio">
		                    <span>
		                        <input type="radio" name="isOnSale" value="1" <#if goods??==false || goods.isOnSale==true>checked="checked"</#if>>
		                        <label>上架</label>
		                        <input type="radio" name="isOnSale" value="0" <#if goods?? && goods.isOnSale?? && goods.isOnSale==false>checked="checked"</#if>>
		                        <label>下架</label>
		                    </span>
		                </div>
		            </dd>
		        </dl>        
	        <#else>
	        	<dl>
		            <dt>状态</dt>
		            <dd>
		                <div class="rule-multi-radio multi-radio">
		                    <span>
		                        <input type="radio" disabled="disabled">
		                        <label>上架</label>
		                        <input type="radio" checked="checked" disabled="disabled">
		                        <label>下架</label>
		                    </span>
		                    
		                </div>
		                <div style="display: none;">
		                        <input type="radio" name="isOnSale" value="1">
		                        <label>上架</label>
		                        <input type="radio" name="isOnSale" value="0" checked="checked">
		                        <label>下架</label>
		                 </div>
		            </dd>
		        </dl>
	        </#if>	
        <#else>
        	<#if canUpdown?? && canUpdown>
		        <dl>
		            <dt>状态</dt>
		            <dd>
		                <div class="rule-multi-radio multi-radio">
		                    <span>
		                        <input type="radio" name="isOnSale" value="1" <#if goods??==false || goods.isOnSale==true>checked="checked"</#if>>
		                        <label>上架</label>
		                        <input type="radio" name="isOnSale" value="0" <#if goods?? && goods.isOnSale?? && goods.isOnSale==false>checked="checked"</#if>>
		                        <label>下架</label>
		                    </span>
		                </div>
		            </dd>
		        </dl>        
	        <#else>
	        	<dl>
		            <dt>状态</dt>
		            <dd>
		                <div class="rule-multi-radio multi-radio">
		                    <span>
		                       	<input type="radio" <#if ixj?? && !ixj>checked="checked"</#if> disabled="disabled">
		                        <label>上架</label>
		                        <input type="radio" <#if ixj?? && ixj>checked="checked"</#if> disabled="disabled">
		                        <label>下架</label>
		                    </span>
		                </div>
	                    <div style="display: none;">
	                        <input type="radio" name="isOnSale" value="1" <#if goods??==false || goods.isOnSale==true>checked="checked"</#if>>
	                        <label>上架</label>
	                        <input type="radio" name="isOnSale" value="0" <#if goods?? && goods.isOnSale?? && goods.isOnSale==false>checked="checked"</#if>>
	                        <label>下架</label>
	                    </div>
		            </dd>
		        </dl>
	        </#if>
        </#if>
        
        <#--
        <#if site_list??>
        <dl>
            <dt>所属站点</dt>
            <dd>
                <div class="rule-single-select">
                    <select name="siteId" datatype="*0-100" sucmsg=" ">
                        <option value="" <#if !site_list??>selected="selected"</#if>>请选择...</option>
                        <#list site_list as w>
                            <option value="${w.id?c!""}" <#if goods?? && goods.siteId?? && goods.siteId==w.id>selected="selected"</#if>>${w.title!""}</option>
                        </#list>
                    </select>
                </div>
            </dd>
        </dl>
        </#if>
        -->
        <dl>
            <dt>推荐类型</dt>
            <dd>
                <div class="rule-multi-checkbox multi-checkbox">
                    <input id="cblItem_2" type="checkbox" name="isHot" <#if goods?? && goods.isHot?? && goods.isHot==true>checked="checked"</#if>>
                    <label for="cblItem_2">热门</label>
                </div>
                <div class="rule-multi-checkbox multi-checkbox" id="techanNo_recommend">
                	<input id="cblItem_1" type="checkbox" name="isRecommendType" <#if goods?? && goods.isRecommendType?? && goods.isRecommendType==true>checked="checked"</#if>>
                	<label for="cblItem_1">精选</label>
                </div>
                <div class="rule-multi-checkbox multi-checkbox" id="techanYes_recommend">
              
                    <input id="cblItem_4" type="checkbox" name="isSpecialPrice" <#if goods?? && goods.isSpecialPrice?? && goods.isSpecialPrice==true>checked="checked"</#if>>
                    <label for="cblItem_4">特价</label>
                </div>
                
            </dd>
        </dl>
        
	        <dl id="carType" style="display:none">
	            <dt>车辆类型</dt>
	            <dd>
	                <div class="rule-multi-radio multi-radio">
	                    <span>
	                        <input type="radio" name="carType" value="1" <#if goods?? && goods.carType?? && goods.carType==true>checked="checked"</#if>>
	                        <label>汽车</label>
	                        <input type="radio" name="carType" value="0" <#if goods?? && goods.carType?? && goods.carType==false>checked="checked"</#if>>
	                        <label>客车</label>
	                    </span>
	                </div>
	                <span class="Validform_checktip">*汽车租赁选择类型</span>
	            </dd>
	        </dl>
        <dl>
            <dt>排序数字</dt>
            <dd>
                <input name="sortId" type="text" value="<#if goods??>${goods.sortId!""}<#else>99</#if>" id="txtSortId" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">*数字，越小越向前</span>
            </dd>
        </dl>
        
        <dl>
            <dt>封面图片</dt>
            <dd>
                <input id="txtImgUrl" name="coverImageUri" type="text" value="<#if goods??>${goods.coverImageUri!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <div id="thumb_ImgUrl_show1" class="photo-list thumb_ImgUrl_show">
                </div>
            </dd>
        </dl>
        <#--<#if goods?? && (goods.categoryIdTree?contains("[1]")||goods.categoryIdTree?contains("[2]")||goods.categoryIdTree?contains("[5]"))>-->
        <dl>
            <dt>行程图片</dt><label style="color: red;">(仅邮轮、目的地、主题活动设置)</label>
            <dd>
                <input id="txtImgUrl2" name="xcImageUri" type="text" value="<#if goods??>${goods.xcImageUri!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <div id="thumb_ImgUrl_show2" class="photo-list thumb_ImgUrl_show">
                </div>
            </dd>
        </dl>
        <#--</#if>-->
        
        <div id="id-param-sec">
            <#if goods??>
                <#include "/site_mag/goods_category_param_list.ftl" />
            </#if>
        </div>
    </div>
    <#if goods?? && goods.categoryIdTree?contains("[23]") && goods.categoryTitle?contains("签证")>
	    <div class="tab-content" style="display: none;">
	        
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>副标题</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>出签率</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*仅签证填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>办理天数</dt>
	            <dd>
	                <textarea name="groupSaleLeftNumber" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.groupSaleLeftNumber!""}</#if></textarea>
	                <span class="Validform_checktip">*仅签证填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>逗留天数</dt>
	            <dd>
	                <textarea name="groupSaleHundredLeftNumber" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.groupSaleHundredLeftNumber!""}</#if></textarea>
	                <span class="Validform_checktip">*仅签证填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>有效期限</dt>
	            <dd>
	                <textarea name="totalGift" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.totalGift!""}</#if></textarea>
	                <span class="Validform_checktip">*仅签证填写</span>
	            </dd>
	        </dl>
	        
	        <#--<#if goods?? && goods.categoryIdTree?contains("[23]")>--> 
		        <dl>
		            <dt>签证资料</dt>
		            <dd>
		                <input id="txtImgUrl3" name="visaDataUri" type="text" value="<#if goods??>${goods.visaDataUri!""}</#if>" class="input normal upload-path">
		                <div class="upload-box upload-img"></div>
		                <div id="thumb_ImgUrl_show3" class="photo-list thumb_ImgUrl_show">
		                </div>
		                <span class="Validform_checktip">*仅签证上传(请把所有的签证资料打包后上传。如：zip,rar等)</span>
		            </dd>
		        </dl>
	        <#--</#if>-->
	        
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--		注销理由：签证类23不可能包含旅游直通车28
	         <#if goods?? && goods.categoryIdTree?contains("[28]")>
		        <dl>
		            <dt>出发时间</dt>
		            <dd>
		                <div class="input-date">
		                    <input name="leaveDate" type="text" value="<#if goods??>${goods.leaveDate!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
		                    <i>日期</i>
		                </div>
		                <span class="Validform_checktip">不选择默认为当前时间</span>
		            </dd>
		        </dl>
	        </#if>
	        -->
	    </div>
	    <#elseif goods?? && goods.categoryTitle?contains("私家定制")><#--上传之前请先修改成52-->
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>副标题</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <#--
	        <dl>
	            <dt>邮轮公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        -->
	        <dl>
	            <dt>出发地点</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>到达地点</dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>途径地点</dt>
	            <dd>
	                <textarea name="passPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.passPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--	注销理由：进入此代码块的前提是排除所有前面的类型包括汽车租赁类29，这里是多此一举
	        <#if goods?? && goods.categoryIdTree?contains("[29]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
	 <#elseif goods?? && (goods.categoryIdTree?contains("[2]")||goods.categoryIdTree?contains("[5]"))>
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>副标题</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>交通公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>出发地点</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>到达地点</dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>途径地点</dt>
	            <dd>
	                <textarea name="passPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.passPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--	注销理由：目的地[2]和主题活动[5]不可能包含汽车租赁[29]
	        <#if goods?? && goods.categoryIdTree?contains("[29]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
	    <#elseif goods?? && goods.categoryTitle?contains("代驾服务")><#--上传之前请先修改成52-->
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>司机</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>驾龄</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <#--
	        <dl>
	            <dt>邮轮公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        -->
	        <dl>
	            <dt>驾照类型</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <#--
	        <dl>
	            <dt>到达地点</dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        -->
	        <dl>
	            <dt>代驾说明</dt>
	            <dd>
	                <textarea name="passPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.passPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--	注销理由：进入此代码块的前提是排除所有前面的类型包括汽车租赁类29，这里是多此一举
	        <#if goods?? && goods.categoryIdTree?contains("[29]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
	    <#elseif goods?? && goods.categoryTitle?contains("私家定制")><#--上传之前请先修改成52-->
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>副标题</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <#--
	        <dl>
	            <dt>邮轮公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        -->
	        <dl>
	            <dt>出发地点</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>到达地点</dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>途径地点</dt>
	            <dd>
	                <textarea name="passPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.passPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--	注销理由：进入此代码块的前提是排除所有前面的类型包括汽车租赁类29，这里是多此一举
	        <#if goods?? && goods.categoryIdTree?contains("[29]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
	 <#elseif goods?? && goods.categoryIdTree?contains("[29]")>
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>车辆型号</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>汽车公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*车辆相关产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>取车地点</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*车辆相关产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车地点</dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*车辆相关产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <!--		注销理由：取车时间和换车时间只是方便前台保存数据，与后台汽车租赁设置无关
	        <#if goods?? && goods.categoryIdTree?contains("[29]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
	    <#elseif goods?? && goods.categoryIdTree?contains("[28]")>
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>车辆型号</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>客车公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*车辆相关产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt><#if goods?? && goods.categoryIdTree?contains("[28]")>出发地点<#else>取车地点</#if></dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*车辆相关产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt><#if goods?? && goods.categoryIdTree?contains("[28]")>达到地点<#else>还车地点</#if></dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*车辆相关产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--<#if goods?? && goods.categoryIdTree?contains("[28]")> 词句注销理由：前面的进入该代码块的前提是已经是旅游直通车28，这是多此一举-->
		        <dl>
		            <dt>出发时间</dt>
		            <dd>
		                <div class="input-date">
		                    <input name="leaveDate" type="text" value="<#if goods??>${goods.leaveDate!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
		                    <i>日期</i>
		                </div>
		                <span class="Validform_checktip">不选择默认为当前时间</span>
		            </dd>
		        </dl>
	        <#--</#if>-->
	    </div>
	    <#elseif goods?? && goods.categoryIdTree?contains("[30]")>
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>副标题</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>产品单位(如瓶、盒、斤等)</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip"></span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>特产产地</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip"></span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--  		注销理由：进入该代码块的前提是特产商城类30，不可能包含汽车租赁
	        <#if goods?? && goods.categoryIdTree?contains("[30]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
	    <#elseif goods?? && goods.categoryIdTree?contains("[29],[50]")>
	    <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>司机</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>驾龄</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <#--
	        <dl>
	            <dt>邮轮公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        -->
	        <dl>
	            <dt>出发地点</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>到达地点</dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>代驾说明</dt>
	            <dd>
	                <textarea name="passPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.passPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--	注销理由：进入此代码块的前提是排除所有前面的类型包括汽车租赁类29，这里是多此一举
	        <#if goods?? && goods.categoryIdTree?contains("[29]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
	<#else>
		 <div class="tab-content" style="display: none;">
	        <dl>
	            <dt>商品标题</dt>
	            <dd>
	                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多100个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>副标题</dt>
	            <dd>
	                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" ">
	                <span class="Validform_checktip">*标题最多255个字符</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>邮轮公司</dt>
	            <dd>
	                <input name="shipCompany" type="text" value="<#if goods??>${goods.shipCompany!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>出发港口</dt>
	            <dd>
	                <textarea name="leavePort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.leavePort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>到达港口</dt>
	            <dd>
	                <textarea name="reachPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.reachPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>途径港口</dt>
	            <dd>
	                <textarea name="passPort" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.passPort!""}</#if></textarea>
	                <span class="Validform_checktip">*仅邮轮产品填写</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>上架时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <#--	注销理由：进入此代码块的前提是排除所有前面的类型包括汽车租赁类29，这里是多此一举
	        <#if goods?? && goods.categoryIdTree?contains("[29]")> 
	        <dl>
	            <dt>取车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        <dl>
	            <dt>还车时间</dt>
	            <dd>
	                <div class="input-date">
	                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	                    <i>日期</i>
	                </div>
	                <span class="Validform_checktip">不选择默认为当前时间</span>
	            </dd>
	        </dl>
	        </#if>
	        -->
	    </div>
    </#if>
    <div class="tab-content" style="display: none;">
    <#--<#if goods?? && !goods.categoryIdTree?contains("[23]")>-->
        <dl id="div_show360_container">
            <dt>展示图片</dt>
            <dd>
                <div class="upload-box upload-show360"></div>
                <div class="photo-list_show360">
                    <ul>
                        <#if goods?? && goods.showPictures??>
                            <#list goods.showPictures?split(",") as uri>
                                <#if uri != "">
	                                <li>
	                                    <input type="hidden" name="hid_photo_name_show360" value="0|${uri!""}|${uri!""}">
	                                    <div class="img-box">
	                                        <img src="${uri!""}" bigsrc="${uri!""}">
	                                    </div>
	                                    <a href="javascript:;" onclick="delImg(this);">删除</a>
	                                </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </dd>
        </dl>
      <#--</#if>-->
     <#--<#if goods?? && !goods.categoryIdTree?contains("[30]")>-->
        <#--<#if goods?? &&goods.categoryIdTree?contains("[28]")||goods.categoryIdTree?contains("[29]")||goods.categoryIdTree?contains("[2]")||goods.categoryIdTree?contains("[5]")>-->
      		<#--
	        <dl>
	            <dt>产品介绍</dt>
	            <dd>
	                <span class="Validform_checktip">目的地或门票产品填写</span>
	                <textarea name="shipDescription" class="editor"><#if goods??>${goods.shipDescription!""}</#if></textarea>
	            </dd>
	        </dl>
	        -->
	    <#--</#if>-->
	    <#--<#if goods?? &&goods.categoryIdTree?contains("[1]")>-->
	    	<dl>
	            <dt>邮轮/产品介绍</dt>
	            <dd>
	                <span class="Validform_checktip"><#--仅邮轮产品填写--></span>
	                <textarea name="shipDescription" class="editor" ><#if goods??>${goods.shipDescription!""}</#if></textarea>
	            </dd>
	        </dl>
        <#--</#if>-->
        <dl>
            <dt>费用说明</dt>
            <dd>
                <textarea name="feeDescription" class="editor"><#if goods??>${goods.feeDescription!""}</#if></textarea>
            </dd>
        </dl>
        <#--<#if goods?? && !goods.categoryIdTree?contains("[28]")>-->
	        <dl>
	            <dt>签证/签注</dt>
	            <dd>
	                <textarea name="visaDescription" class="editor"><#if goods??>${goods.visaDescription!""}</#if></textarea>
	            </dd>
	        </dl>
        <#--</#if>-->
        <dl>
            <dt>预定须知</dt>
            <dd>
                <textarea name="bookDescription" class="editor"><#if goods??>${goods.bookDescription!""}</#if></textarea>
            </dd>
        </dl>
    </div>
    
    
    <div class="tab-content" style="display: none;">
        <#--<#if goods?? && goods.categoryIdTree?contains("[29]")>-->
        <dl class="flag_29" <#if goods?? && goods.categoryIdTree?contains("[29]")><#else>style="display: none;"</#if>>
            <dt>押金</dt>
            <dd>
                <input name="includePrice" type="text" value="<#if goods?? && goods.includePrice??>${goods.includePrice?string("#.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">电话卡销售时包含的话费</span>
            </dd>
        </dl>
        <dl class="flag_29" <#if goods?? && goods.categoryIdTree?contains("[29]")><#else>style="display: none;"</#if>>
            <dt>代驾费</dt>
            <dd>
                <input id="outFactoryPrice" name="outFactoryPrice" type="text" value="<#if goods?? && goods.outFactoryPrice??>${goods.outFactoryPrice?string("0.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">*商品供货价即批发价</span>
            </dd>
        </dl>
       <#--</#if>-->
       <#--<#if goods?? && (goods.categoryIdTree?contains("[29]")||goods.categoryIdTree?contains("[30]")||goods.categoryIdTree?contains("[23]"))>-->
	        <dl>
	            <dt id="qicheYes" class="flag_29" <#if goods?? && (goods.categoryIdTree?contains("[29]")||goods.categoryIdTree?contains("[30]")||goods.categoryIdTree?contains("[23]"))><#else>style="display: none;"</#if>>销售起价</dt>
	            <dt id="techanNo_adultPrice" class="flag2_29" <#if goods?? && (goods.categoryIdTree?contains("[29]")||goods.categoryIdTree?contains("[30]")||goods.categoryIdTree?contains("[23]"))>style="display: none;"</#if>>成人起价</dt>
	            <dt id="techanYes_adultPrice" class="flag2_29" <#if goods?? && (goods.categoryIdTree?contains("[29]")||goods.categoryIdTree?contains("[30]")||goods.categoryIdTree?contains("[23]"))>style="display: none;"</#if>>商品价格</dt>
	            <dd>
	                <input id="idComputeSalePrice" name="salePrice" type="text" value="<#if goods?? && goods.salePrice??>${goods.salePrice?string("0.00")}<#else>0</#if>" class="input normal" sucmsg="" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/">
	                <span class="Validform_checktip"><#--*销售价--></span>
	                <span class="Validform_checktip"><#--*成人价（没有儿童价的商品，都默认为成人价，儿童价可以不写。）--></span>
	            </dd>
	        </dl>
        <#--<#else>-->
        <#--
	        <dl class="flag2_29" <#if goods?? && (goods.categoryIdTree?contains("[29]")||goods.categoryIdTree?contains("[30]")||goods.categoryIdTree?contains("[23]"))>style="display: none;"</#if>>
	            <dt>成人起价</dt>
	            <dd>
	                <input id="idComputeSalePrice" name="salePrice" type="text" value="<#if goods?? && goods.salePrice??>${goods.salePrice?string("0.00")}<#else>0</#if>" class="input normal" sucmsg="" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/">
	                <span class="Validform_checktip">*成人价（没有儿童价的商品，都默认为成人价，儿童价可以不写。）</span>
	            </dd>
	        </dl>
	        -->
	        <dl id="ertongqijia" class="flag2_29" <#if goods?? && (goods.categoryIdTree?contains("[29]")||goods.categoryIdTree?contains("[30]")||goods.categoryIdTree?contains("[23]"))>style="display: none;"</#if>>
	            <dt>儿童起价</dt>
	            <dd>
	                <input id="idComputeSalePrice1" name="salePrice1" type="text" value="<#if goods?? && goods.salePrice1??>${goods.salePrice1?string("0.00")}<#else>0</#if>" class="input normal" sucmsg="" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/">
	                <span class="Validform_checktip">*儿童价，特产商城请勿设置此价格</span>
	            </dd>
	        </dl>
        <#--</#if>-->
        <dl>
            <dt>赠送积分</dt>
            <dd>
                <input id="returnPoints" name="returnPoints" type="text" value="<#if goods?? && goods.returnPoints??>${goods.returnPoints?c!"0"}<#else>0</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">购买该商品赠送的积分</span>
            </dd>
        </dl>
        <dl>
            <dt>库存余量</dt>
            <dd>
                <input name="leftNumber" type="text" value="<#if goods?? && goods.leftNumber??>${goods.leftNumber?c!"99"}<#else>99</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">库存为0时显示为缺货</span>
            </dd>
        </dl>
        <#if goods?? && goods.categoryIdTree?contains("[28]")> 
	        <dl>
	            <dt>客车载量</dt>
	            <dd>
	                <input id="returnPoints" name="returnPoints" type="text" value="<#if goods?? && goods.returnPoints??>${goods.returnPoints?c!"0"}<#else>0</#if>" class="input normal" datatype="n" sucmsg=" ">
	                <span class="Validform_checktip">车辆最大载客量</span>
	            </dd>
	        </dl>
        </#if>
        <dl>
            <dt>销量</dt>
            <dd>
                <input name="soldNumber" type="text" value="<#if goods?? && goods.soldNumber??>${goods.soldNumber?c!"0"}<#else>0</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">商品已销售数量</span>
            </dd>
        </dl>
        <div id="more-price">
        <#if goods?? && (goods.categoryIdTree?contains("[1]")||goods.categoryIdTree?contains("[2]")||goods.categoryIdTree?contains("[5]"))>
	        <dl>
	            <dt>保险信息</dt>
	            <dd>
	                <textarea name="insurance" rows="5" cols="100" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.insurance!""}</#if></textarea>
	                <span class="Validform_checktip">*关系到保险的产品填写</span>
	            </dd>
		    </dl>
		</#if>
	    <#if goods?? && goods.categoryIdTree?contains("[1]")> 
	        <dl>
	            <dt>增值服务</dt>
	            <dd>
	                <a id="addRoom" class="icon-btn add"><i></i><span>添加增值服务</span></a>
	                <span class="Validform_checktip"></span>
	            </dd>
	        </dl>
	        <dl>
	            <dt></dt>
	            <dd>
	                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
	                    <thead>
	                        <tr>
	                        	<th width="5%">
	                                ID
	                            </th>
	                            <th width="10%">
	                                类型
	                            </th>
	                            <th width="20%">
	                                描述
	                            </th>
	                            <th width="10%">
	                                成人价
	                            </th>
	                            <th width="10%">
	                                儿童价
	                            </th>
	                            <th width="6%">
	                                操作
	                            </th>
	                        </tr>
	                    </thead>
	                    <tbody id="var_box_ps">
	                        <#if goods?? && goods.psList??>
	                            <#list goods.psList as item>
	                                <tr class="td_c">
	                                	<td>
	                                        <input name="psList[${item_index}].id?c" id="id" type="hidden" value="${item.id?c}">
	                                        <span>${item.id?c}</span>
	                                    </td>
	                                    <td>
	                                    	<select name="psList[${item_index}].type">
	                                    		<option>请选择</option>
	                                    		<option value="内舱房" <#if item.type?? && item.type=="内舱房">selected="selected"</#if>>内舱房</option>
	                                    		<option value="阳台房" <#if item.type?? && item.type=="阳台房">selected="selected"</#if>>阳台房</option>
	                                    		<option value="岸上观光" <#if item.type?? && item.type=="岸上观光">selected="selected"</#if>>岸上观光</option>
	                                    		<option value="签证" <#if item.type?? && item.type=="签证">selected="selected"</#if>>签证</option>
	                                    	</select>
	                                    </td>
	                                    <td>
	                                        <input type="text" id="title" name="psList[${item_index}].title" class="td-input" value="${item.title!''}" style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="price1" name="psList[${item_index}].price1" class="td-input" value="<#if item.price1??>${item.price1?string("0.00")}</#if>" style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="price2" name="psList[${item_index}].price2" class="td-input" value="<#if item.price2??>${item.price2?string("0.00")}</#if>" style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <i class="icon" style="text-indent:-9999999px;"></i>
	                                        <a title="删除" class="img-btn del operator" onclick="del_room(this);">删除</a>
	                                    </td>
	                                </tr>
	                            </#list>
	                        </#if>
	                    </tbody>
	                </table>
	            </dd>
	        </dl>
        </#if>
        
        
        <#if goods?? && (goods.categoryIdTree?contains("[1]")||goods.categoryIdTree?contains("[2]")||goods.categoryIdTree?contains("[5]"))> 
	        <dl>
	            <dt>行程介绍</dt>
	            <dd>
	                <a id="addRoom3" class="icon-btn add"><i></i><span>添加行程介绍</span></a>
	                <span class="Validform_checktip"></span>
	            </dd>
	        </dl>
	        <dl>
	            <dt></dt>
	            <dd>
	                <table border="0" cellspacing="0" cellpadding="0" id="var_pox_route" class="border-table" width="98%">
	                    <thead>
	                        <tr>
	                        	<th width="10%">
	                                时间
	                            </th>
	                            <th width="15%">
	                                主题
	                            </th>
	                            <th >
	                                行程安排
	                            </th>
	                            
	                            <th width="10%">
	                                住宿
	                            </th>
	                            <th width="10%">
	                                到达时间
	                            </th>
	                            <th width="10%">
                                    离开时间
                                </th>
                                <th width="7%">
                                    操作
                                </th>
	                        </tr>
	                    </thead>
	                    <tbody id="var_box_ps3">
	                        <#if goods?? && goods.xcList??>
	                            <#list goods.xcList as item>
	                                <tr class="td_c soute">
	                                	<td>
	                                        <input type="hidden" name="xcList[${item_index}].id?c" id="souteId${item.id?c}"  value="${item.id?c}">
	                                        <input type="text" name="xcList[${item_index}].day" id="souteDay${item.id?c}" class="td-input"  value="${item.day!''}">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="souteTitle${item.id?c}" name="xcList[${item_index}].title" class="td-input" value="${item.title!''}" style="width:90%;">
	                                    </td>
	                                    <td>
	                                       <textarea id="description${item.id?c}" name="xcList[${item_index}].description" rows="2" cols="100" class="input" >${item.description!''}</textarea>
	                                       <!--
                                            <input type="text" id="description" name="xcList[${item_index}].description" class="td-input" value="${item.description!''}" style="width:90%;">
                                            -->
                                        </td>
	                                    <td>
	                                        <input type="text" id="live${item.id?c}" name="xcList[${item_index}].live" class="td-input" value="${item.live!''}" style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="soutereach${item.id?c}" name="xcList[${item_index}].reachTime" class="td-input" value="${item.reachTime!''}" style="width:90%;">
	                                        <input type="hidden" id="eat${item.id?c}" name="xcList[${item_index}].eat" class="td-input" value="${item.eat!''}" style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="souteleave${item.id?c}" name="xcList[${item_index}].leaveTime" class="td-input" value="${item.leaveTime!''}" style="width:90%;">
                                            <input type="hidden" id="souteimgUrl${item.id?c}" name="xcList[${item_index}].imgUrl" class="td-input" value="${item.imgUrl!''}" style="width:90%;">
                                        </td>
	                                    <td>
	                                       <i class="icon"></i>
                                          <!--   <a title="编辑" class="img-btn edit operator" onclick="showDialogGift(this);">编辑</a>-->
                                            <a title="编辑" class="img-btn edit operator" onclick="showDialog(${item.id?c});">编辑</a>
                                            <a title="删除" class="img-btn del operator" onclick="delGiftNode(this);">删除</a>
	                                    </td>
	                                </tr>
	                            </#list>
	                        </#if>
	                    </tbody>
	                </table>
	            </dd>
	        </dl>
        </#if>
        <#if goods?? && goods.categoryIdTree?contains("[2]")> 
	        <dl>
	            <dt>增值服务</dt>
	            <dd>
	                <a id="addRoom1" class="icon-btn add"><i></i><span>添加增值服务</span></a>
	                <span class="Validform_checktip"></span>
	            </dd>
	        </dl>
	        <dl>
	            <dt></dt>
	            <dd>
	                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
	                    <thead>
	                        <tr>
	                        	<th width="5%">
	                                ID
	                            </th>
	                            <th width="10%">
	                                类型
	                            </th>
	                            <th width="20%">
	                                描述
	                            </th>
	                            <th width="10%">
	                                成人价
	                            </th>
	                            <th width="10%">
	                                儿童价
	                            </th>
	                            <th width="6%">
	                                操作
	                            </th>
	                        </tr>
	                    </thead>
	                    <tbody id="var_box_ps1">
	                        <#if goods?? && goods.psList??>
	                            <#list goods.psList as item>
	                                <tr class="td_c">
	                                	<td>
	                                        <input name="psList[${item_index}].id?c" id="id1" type="hidden" value="${item.id?c}">
	                                        <span>${item.id?c}</span>
	                                    </td>
	                                    <td>
	                                    	<select name="psList[${item_index}].type">
	                                    		<option>请选择</option>
	                                    		<option value="酒店" <#if item.type?? && item.type=="酒店">selected="selected"</#if>>酒店</option>
	                                    		<option value="交通方式" <#if item.type?? && item.type=="交通方式">selected="selected"</#if>>交通方式</option>
	                                    		<option value="可选项目" <#if item.type?? && item.type=="可选项目">selected="selected"</#if>>可选项目</option>
	                                    		<option value="签证" <#if item.type?? && item.type=="签证">selected="selected"</#if>>签证</option>
	                                    	</select>
	                                    </td>
	                                    <td>
	                                        <input type="text" id="title1" name="psList[${item_index}].title" class="td-input" value="${item.title!''}" style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="price1" name="psList[${item_index}].price1" class="td-input"  <#if item.price1??>value="${item.price1?string("0.00")}"</#if> style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="price2" name="psList[${item_index}].price2" class="td-input"  <#if item.price2??>value="${item.price2?string("0.00")}"</#if> style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <i class="icon" style="text-indent:-9999999px;"></i>
	                                        <a title="删除" class="img-btn del operator" onclick="del_room1(this);">删除</a>
	                                    </td>
	                                </tr>
	                            </#list>
	                        </#if>
	                    </tbody>
	                </table>
	            </dd>
	        </dl>
        </#if>
        <#if goods?? && goods.categoryIdTree?contains("[5]")> 
	        <dl>
	            <dt>增值服务</dt>
	            <dd>
	                <a id="addRoom2" class="icon-btn add"><i></i><span>添加增值服务</span></a>
	                <span class="Validform_checktip"></span>
	            </dd>
	        </dl>
	        <dl>
	            <dt></dt>
	            <dd>
	                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
	                    <thead>
	                        <tr>
	                        	<th width="5%">
	                                ID
	                            </th>
	                            <th width="10%">
	                                类型
	                            </th>
	                            <th width="20%">
	                                描述
	                            </th>
	                            <th width="10%">
	                                成人价
	                            </th>
	                            <th width="10%">
	                                儿童价
	                            </th>
	                            <th width="6%">
	                                操作
	                            </th>
	                        </tr>
	                    </thead>
	                    <tbody id="var_box_ps1">
	                        <#if goods?? && goods.psList??>
	                            <#list goods.psList as item>
	                                <tr class="td_c">
	                                	<td>
	                                        <input name="psList[${item_index}].id?c" id="id2" type="hidden" value="${item.id?c}">
	                                        <span>${item.id?c}</span>
	                                    </td>
	                                    <td>
	                                    	<select name="psList[${item_index}].type">
	                                    		<option>请选择</option>
	                                    		<option value="酒店" <#if item.type?? && item.type=="酒店">selected="selected"</#if>>酒店</option>
	                                    		<option value="交通方式" <#if item.type?? && item.type=="交通方式">selected="selected"</#if>>交通方式</option>
	                                    		<option value="可选项目" <#if item.type?? && item.type=="可选项目">selected="selected"</#if>>可选项目</option>
	                                    		<option value="签证" <#if item.type?? && item.type=="签证">selected="selected"</#if>>签证</option>
	                                    	</select>
	                                    </td>
	                                    <td>
	                                        <input type="text" id="title2" name="psList[${item_index}].title" class="td-input" value="${item.title!''}" style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="price11" name="psList[${item_index}].price1" class="td-input"  <#if item.price1??>value="${item.price1?string("0.00")}"</#if> style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <input type="text" id="price22" name="psList[${item_index}].price2" class="td-input"  <#if item.price2??>value="${item.price2?string("0.00")}"</#if> style="width:90%;">
	                                    </td>
	                                    <td>
	                                        <i class="icon" style="text-indent:-9999999px;"></i>
	                                        <a title="删除" class="img-btn del operator" onclick="del_room2(this);">删除</a>
	                                    </td>
	                                </tr>
	                            </#list>
	                        </#if>
	                    </tbody>
	                </table>
	            </dd>
	        </dl>
        </#if>
        </div>
        
    </div>
   <#if goods?? && (goods.categoryIdTree?contains("[1]")||goods.categoryIdTree?contains("[2]")||goods.categoryIdTree?contains("[5]"))>
  <link rel="stylesheet" href="/mag/style/simplepop.css">
  <script src="/mag/js/simplepop.js"></script>
  <script type="text/javascript" src="/mag/js/price.js"></script>
  <script>
   $(function () {
        searchPrice(${goods.id?c});
   });
   
   function deleteAllPrice(){
        var listChkId ="";
        var listId ="";
        var chkInput =document.getElementsByName("listChkId");
        var listinput =document.getElementsByName("listId");
        
        $(chkInput).each(function(){
            if(this.checked){
            　　listChkId += this.value +",";    
            }
        });
        
        $(listinput).each(function(){
            　　listId += this.value + ',';    
        });
        console.debug(listChkId);
        console.debug(listId);
        if(undefined == listChkId || listChkId.length ==""){
            SimplePop.alert("请选择要删除的内容");
            return ;
        }
        var url ="/Verwalter/timePrice/deleteAll";
        var loadData={"listChkId":listChkId,"listId":listId};
        $.post(url,loadData,delPriceAllCallback,"text");
    }
    
    function delPriceAllCallback(data){
        var result = eval("("+data+")");
        if(result.code==1){
            refreshList(${goods.id?c});
        }else{
            SimplePop.alert('删除失败!');
        }
    }
   </script>
    <div class="tab-content" style="display: none;">
            <dl>
                <dt></dt>
                <dd>
                    <#if !goods?? || !goods.isOnSale?? || goods.isOnSale ==false || (managerRole?? && managerRole.isSys)>
                    <a href="javascript:;" onclick="editPrice(${goods.id?c},'add')" class="icon-btn add"><i></i><span>添加价目表</span></a>
                    <a onclick="deleteAllPrice();"  class="icon-btn del"><i></i><span>批量删除</span></a>
                    <a onclick="searchPrice(${goods.id?c});"  class="icon-btn del"><i></i><span>刷新价目表</span></a>
                    </#if>
                </dd>
            </dl>
            <dl>
                <dt></dt>
                <dd id="priceList">
                    
                </dd>
            </dl>
    </div>
    </#if>
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>SEO标题</dt>
            <dd>
                <input name="seoTitle" type="text" maxlength="255" id="txtSeoTitle" value="<#if goods??>${goods.seoTitle!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO关健字</dt>
            <dd>
                <textarea name="seoKeywords" rows="2" cols="20" id="txtSeoKeywords" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.seoKeywords!""}</#if></textarea>
                <span class="Validform_checktip">以“,”逗号区分开，255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO描述</dt>
            <dd>
                <textarea name="seoDescription" rows="2" cols="20" id="txtSeoDescription" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.seoDescription!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
    </div>
    
    
    
    <!--/内容-->
    <!--工具栏-->
    <div class="page-footer">
        <div class="btn-list">
            <#if !goods?? || !goods.isOnSale?? || goods.isOnSale ==false>
                <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
            <#elseif managerRole?? && managerRole.isSys>
                <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
            </#if>
            <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
        </div>
        <div class="clear">
        </div>
    </div>
    <!--/工具栏-->
    </form>
</body>
</html>