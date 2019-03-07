<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品挑选</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js?skin=idialog"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>

<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mag/js/price.js"></script>
<script type="text/javascript">
var api = frameElement.api, W = api.opener;
$(function () {
    //初始化表单验证
   // $("#form1").initValidform();
    
    $("#form1").Validform({
        tiptype:3, 
        ajaxPost:true,
        callback:function(data){
            $("#Validform_msg").css("display","none");
            if(data.code == 0)
            {
                W.$.dialog.tips(data.message, 1, "confirm.gif",function () { $("#closrApi").click();  });
            }else{
                W.$.dialog.alert(data.message, function () { }, api);
            }
        }
    });
});
function closeApi(){
    api.close();
}

var num = 0;
function addTime(){
    var dlhtml = '<dl id="newtime'+num+'">'
     +'<dt>时间</dt>'
     +'<dd>' 
     +'<input name="start"  type="text"  value="" class="input date" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',lang:\'zh-cn\'})" datatype="/^\\d{4}\\-\\d{1,2}\\-\\d{1,2}/" errormsg="请选择正确的日期" sucmsg=" " nullmsg="请选择时间">' 
     +'～<input name="end"  type="text"  value="" class="input date" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',lang:\'zh-cn\'})" datatype="/^\\d{4}\\-\\d{1,2}\\-\\d{1,2}/" errormsg="请选择正确的日期" sucmsg=" " nullmsg="请选择时间">'       
     +'<span class="Validform_checktip">*</span>'
     +'<a href="javascript:;" onclick="removeDl('+num+')">删除</a>'        
     +'</dd>'
     +'</dl>'
    $("#priceDiv").append(dlhtml);
    num ++;
}
function removeDl(id){
    $("newtime"+id).remove();
}
</script>
</head>
<body>
<form name="form1" method="post" action="/Verwalter/timePrice/add" id="form1">
<input type="hidden" name="id" id="id" value="<#if timePrice??>${timePrice.id?c}</#if>">
<input type="hidden" name="goodsId" id="goodsId" value="<#if goodsId??>${goodsId?c}</#if>">
<div class="div-content" id="priceDiv">
    <dl>
      <dt>成人价格<b style="color:red;">*</b></dt>
      <dd>
        <input type="text"  name="price" id="price" value="<#if timePrice?? && timePrice.price??>${timePrice.price?string('0.00')}</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" "> 元
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>儿童价格<b style="color:red;">*</b></dt>
      <dd>
        <input type="text" name="childPrice"  id="childPrice" value="<#if timePrice?? && timePrice.childPrice??>${timePrice.childPrice?string('0.00')}</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" "> 元
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>时间</dt>
      <dd>
            <input name="start" id="stime"  type="text"  value="<#if timePrice?? && timePrice.startTime??>${timePrice.startTime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\d{4}\-\d{1,2}\-\d{1,2}/" errormsg="请选择正确的日期" sucmsg=" " nullmsg="请选择时间">
            ～<input name="end" id="ntime" type="text"  value="<#if timePrice?? && timePrice.endTime??>${timePrice.endTime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\d{4}\-\d{1,2}\-\d{1,2}/" errormsg="请选择正确的日期" sucmsg=" " nullmsg="请选择时间"> 
            <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <#if !timePrice??>
    <dl>
        <dt></dt>
        <dd>
            <a href="javascript:;" onclick="addTime();" class="icon-btn add"><i></i><span>添加</span></a>
        </dd>
    </dl>
    </#if>    
</div>

    <div class="page-footer">
        <div class="btn-list">
            <input type="submit" name="btnSubmit" value="保存" id="btnSubmit" class="btn">
            <input name="btnReturn" type="button" value="关闭" id="closrApi" class="btn yellow" onclick="javascript:closeApi();">
        </div>
        <div class="clear">
        </div>
    </div>
</form>

</body>
</html>