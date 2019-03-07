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

<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
});

    //窗口API
    var api = frameElement.api, W = api.opener;
    api.button({
        name: '确定',
        focus: true,
        callback: function () {
            submitForm();
            return false;
        }
    }, {
        name: '取消'
    });

//提交表单处理
    function submitForm() {
        //验证表单
        var goodsId = $.trim($('#goodsId').val());
        var cateId = $.trim($('#categoryId').val());
        var id = $.trim($('#id').val());
        var price = $.trim($('#price').val());
        var childPrice = $.trim($('#childPrice').val());
        var start = $.trim($('#stime').val());
        var end = $.trim($('#ntime').val());
        
        var num = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
        if (cateId=="") {
            W.$.dialog.alert('请选择商品类型！', function () { $("#categoryId").focus(); }, api);
            return false;
        }
        if (goodsId=="") {
            W.$.dialog.alert('请选择商品！', function () { $("#goodsId").focus(); }, api);
            return false;
        }
        if (price=="" || !num.test(price)) {
            W.$.dialog.alert('请设置正确的价格！', function () { $("#price").focus(); }, api);
            return false;
        }
        
        if (price=="" || !num.test(childPrice)) {
            W.$.dialog.alert('请设置正确的价格！', function () { $("#childPrice").focus(); }, api);
            return false;
        }
        
        $.ajax({
            type:"post",
            data : {"id":id,"cateId":cateId,"goodsId":goodsId,"childPrice":childPrice,
                    "price":price,"start":start,"end":end},
            url:"/Verwalter/timePrice/add",
            success:function(res){
                // 成功
                if (res.code==0)
                {
                    W.$.dialog.tips('成功设置价格', 1, "confirm.gif",function () { api.close();  });
                }
                else
                {
                    W.$.dialog.alert(res.message, function () { }, api);
                }
            }
        });
        
    }
    
    function selectCategory(){
        var catElem = $("#categoryId");
        var catid = catElem.val();
        if(catid!=""){
            initgoodslist(catid);
        }
    }
    function initgoodslist(cid){
        var url = "/Verwalter/timePrice/goodslist";
        var data = {"cateid":cid};
        $("#goodsId").load(url,data,loadcallback);
    }
    function loadcallback(){
        $("#goodsdiv").ruleSingleSelect();
    }
</script>
</head>
<body>
<form name="form1" method="post" action="" id="form1">
<input type="hidden" name="id" id="id" value="<#if timePrice??>${timePrice.id?c}</#if>">
<div class="div-content">
    <#--
    <dl>
      <dt>类型<b style="color:red;">*</b></dt>
      <dd>
        <div class="rule-single-select single-select">
            <select name="categoryId" id="categoryId" style="display: none;" datatype="n" <#if sale??>disabled</#if> onchange="selectCategory()" sucmsg=" ">
                <option <#if !timePrice??>selected="selected"</#if> value="">商品类型</option>
                <option value="1" <#if timePrice?? &&timePrice.cateId?? && timePrice.cateId==1>selected="selected"</#if> >邮轮俱乐部</option>
                <option value="2" <#if timePrice?? &&timePrice.cateId?? && timePrice.cateId==2>selected="selected"</#if> >目的地</option>
                <option value="5" <#if timePrice?? &&timePrice.cateId?? && timePrice.cateId==5>selected="selected"</#if> >主题活动</option>
            </select>
        </div>    
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    -->
    <dl>
        <dt>类型<b style="color:red;">*</b></dt>
        <dd>
            <div class="rule-single-select">
                <select name="categoryId" id="categoryId" style="display: none;" datatype="n" <#if sale??>disabled</#if> onchange="selectCategory()" sucmsg=" ">
                    <#if !timePrice??>
                    <option value="">请选择类别...</option>
                    </#if>
                    <#if category_list??>
                        <#list category_list as c>
                            <#if c.parentTree?contains("[1]") || c.parentTree?contains("[2]") || c.parentTree?contains("[5]")>
                            <option value="${c.id!""}" <#if timePrice?? && timePrice.cateId==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                            </#if>
                        </#list>
                    </#if>
                </select>
            </div>
        </dd>
    </dl> 
    <dl>
      <dt>商品<b style="color:red;">*</b></dt>
      <dd>
        <div class="rule-single-select single-select" id="goodsdiv">
            <select name="goodsId" id="goodsId" style="display: none;" <#if sale??>disabled</#if> datatype="n" sucmsg=" ">
                <option <#if !timePrice??>selected="selected"</#if> value="">商品名称</option>
                <#if goods_list??>
                    <#list goods_list as goods>
                        <option value="${goods.id}" <#if timePrice?? && timePrice.goodsId==goods.id>selected="selected"</#if> >${goods.subTitle!""}</option>
                    </#list>
                </#if>
            </select>
        </div>    
        <span class="Validform_checktip">*</span>
      </dd>
    </dl> 
    <dl>
      <dt>成人价格<b style="color:red;">*</b></dt>
      <dd>
        <input type="text" <#if sale??>readonly</#if>  name="price" id="price" value="<#if timePrice?? && timePrice.price??>${timePrice.price?string('0.00')}</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" "> 元
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>儿童价格<b style="color:red;">*</b></dt>
      <dd>
        <input type="text" name="childPrice" <#if sale??>readonly</#if> id="childPrice" value="<#if timePrice?? && timePrice.childPrice??>${timePrice.childPrice?string('0.00')}</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" "> 元
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>时间</dt>
      <dd>
    		<input name="stime" id="stime"  type="text" <#if sale??>readonly</#if> value="<#if timePrice?? && timePrice.startTime??>${timePrice.startTime?string('yyyy-MM-dd')}</#if>" class="input date" <#if !sale??>onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})"</#if> datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}/" errormsg="请选择正确的日期" sucmsg=" " nullmsg="请选择时间">
    		～<input name="ntime" id="ntime" type="text" <#if sale??>readonly</#if> value="<#if timePrice?? && timePrice.endTime??>${timePrice.endTime?string('yyyy-MM-dd')}</#if>" class="input date" <#if !sale??>onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})"</#if> datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}/" errormsg="请选择正确的日期" sucmsg=" " nullmsg="请选择时间"> 
        	<span class="Validform_checktip">*</span>
      </dd>
    </dl>    
</div>
</form>

</body>
</html>