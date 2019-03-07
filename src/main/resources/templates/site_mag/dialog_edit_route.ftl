<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑资料</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js?skin=idialog"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">

<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	//初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
	
	//（缩略图）
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic+ "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }
	
    //初始化编辑器
    var editor = KindEditor.create('textarea[id="detail"]', {
        width: '600',
        height: '450px',
        resizeType: 1,
        uploadJson: '/Verwalter/editor/upload?action=EditorFile',
        fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
        allowFileManager: true,
        afterBlur:function(){this.sync();}
    });
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
    

 //   //页面加载完成执行
 //   $(function () {
 //       if ($(api.data).length > 0) {
 //           var parentObj = $(api.data).parent().parent(); //取得节点父对象            
 //           //开始赋值
 //           $("#dialog_day").val($(parentObj).find("input[id='souteDay']").val()); 
 //           $("#dialog_title").val($(parentObj).find("input[id='souteTitle']").val()); 
 //           $("#dialog_live").val($(parentObj).find("input[id='live']").val()); 
 //           $("#dialog_eat").val($(parentObj).find("input[id='eat']").val()); 
 //          // $("#dialog_detail").val($(parentObj).find("input[id='description']").val()); 
 //           $("#dialog_detail").val($(parentObj).find("textarea[id='description']").value); 
 //           $("#txtImgUrl").val($(parentObj).find("input[id='souteimgUrl']").val()); 
 //       }
 //   });

    //创建促销赠品窗口
    function show_goods_select_dialog(obj) {
        var objNum = arguments.length;
        var zengpinDialog = $.dialog({
            id: 'zengpinhDialogId',
            lock: true,
            max: false,
            min: false,
            title: "促销赠品",
            content: 'url:/Verwalter/goods/list/dialog/gift',
            width: 850,
            height: 550
        });
        //如果是修改状态，将对象传进去
        if (objNum == 1) {
            zengpinDialog.data = obj;
        }
    }
    
    //删除促销赠品节点
    function del_goods_gift(obj) {
        $(obj).parent().parent().remove();
    }
    
    //提交表单处理
    function submitForm() {
        //验证表单
        if ($("#dialog_day").val() == "") {
            W.$.dialog.alert('请填写完整信息！', function () { $("#dialog_day").focus(); }, api);
            return false;
        }
        
        var edit = false;
        <#if route??>
            edit = true;
        </#if>
        //添加或修改
        if (edit) {
        
            var parentObj = $("#var_pox_route", W.document);
		 
            parentObj.find("input[id='souteDay<#if route??>${route.id?c}'</#if>]").val($("#dialog_day").val());
            parentObj.find("input[id='souteTitle<#if route??>${route.id?c}'</#if>]").val($("#dialog_title").val());
           // parentObj.find("input[id='description<#if route??>${route.id?c}'</#if>]").val(document.getElementById('detail').value);
            parentObj.find("textarea[id='description<#if route??>${route.id?c}</#if>']").html(document.getElementById('detail').value);
            parentObj.find("input[id='live<#if route??>${route.id?c}</#if>']").val($("#dialog_live").val());
            parentObj.find("input[id='soutereach<#if route??>${route.id?c}</#if>']").val($("#dialog_reach").val());
            parentObj.find("input[id='eat<#if route??>${route.id?c}</#if>']").val($("#dialog_eat").val());
            parentObj.find("input[id='souteleave<#if route??>${route.id?c}</#if>']").val($("#dialog_leave").val());
            parentObj.find("input[id='souteimgUrl<#if route??>${route.id?c}</#if>']").val($("#txtImgUrl").val());
        } else {
		 
            var trHtml = '<tr class="td_c soute">'
            + '<td><input name="xcList[${total!'0'}].id" type="hidden" value="" />'
            + '<input type="text" name="xcList[${total!'0'}].day" id="souteDay" class="td-input" value="' + $("#dialog_day").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="souteTitle" name="xcList[${total!'0'}].title" class="td-input" value="' + $("#dialog_title").val() + '" style="width:90%;" /></td>'
      //      + '<td><input type="text" id="description" name="xcList[${total!'0'}].description" class="td-input" value="' + document.getElementById('detail').value + '" style="width:90%;" /></td>'
            + '<td><textarea id="description" name="xcList[${total!'0'}].description" rows="2" cols="100" class="input" >' + document.getElementById("detail").value + '</textarea></td>'
            + '<td><input type="text" id="live" name="xcList[${total!'0'}].live" class="td-input" value="' + $("#dialog_live").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="soutereach" name="xcList[${total!'0'}].reachTime" class="td-input" value="' + $("#dialog_reach").val() + '" style="width:90%;" />'
            + '<input type="hidden" id="eat" name="xcList[${total!'0'}].eat" class="td-input" value="' + $("#dialog_eat").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="souteleave" name="xcList[${total!'0'}].leaveTime" class="td-input" value="' + $("#dialog_leave").val() + '" style="width:90%;" />'
            + '<input type="hidden" id="souteimgUrl" name="xcList[${total!'0'}].imgUrl" class="td-input" value="' + $("#txtImgUrl").val() + '" style="width:90%;" /></td>'
            + '<td>'
            + '<i class="icon"></i>'
           // + '<a title="编辑" class="img-btn edit operator" onclick="show_goods_gift_dialog(this);">编辑</a>'
            + '<a title="删除" class="img-btn del operator" onclick="delGiftNode(this);">删除</a>'
            + '</td>'
            + '</tr>'
            //如果是窗口调用则添加到窗口
            if (!api.get('dialogChannel') || !api.get('dialogChannel')) {
                $("#var_pox_route", W.document).append(trHtml);
                $("#totalGift", W.document).val(parseInt($("#totalGift", W.document).val())+1);
            } else {
                $("#var_pox_route", api.get('dialogChannel').document).append(trHtml);
                $("#totalGift", W.document).val(parseInt($("#totalGift", W.document).val())+1);
            }
        }
        api.close();
    }
    
    $(function () {
        $(".itemzengpin_select").click(function () {

            var itemzengpin_title = $(this).attr("itemzengpin_title");
            var itemzengpin_id = $(this).attr("itemzengpin_id");


            $("#dialog_day").val(itemzengpin_title);
            $("#txtItemtdFinance_Id").val(itemzengpin_id);
        });
    });
    

</script>
</head>

<body>
<div class="div-content">
    <dl>
      <dt>时间</dt>
      <dd>
        <input type="text" id="dialog_day" value="<#if route??>${route.day!''}</#if>" class="input normal">
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>主题</dt>
      <dd>
        <input type="text" id="dialog_title" name="title" value="<#if route??>${route.title!''}</#if>" class="input normal" > 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>住宿</dt>
      <dd>
        <input type="text" id="dialog_live" value="<#if route??>${route.live!''}</#if>" class="input normal" > 
      </dd>
    </dl>
    <dl>
      <dt>餐食</dt>
      <dd>
        <input type="text" id="dialog_eat" value="<#if route??>${route.eat!''}</#if>" class="input normal"> 
      </dd>
    </dl>
    <dl>
      <dt>到达时间</dt>
      <dd>
        <input type="text" id="dialog_reach" value="<#if route??>${route.reachTime!''}</#if>" class="input normal"> 
      </dd>
    </dl>
    <dl>
      <dt>离开时间</dt>
      <dd>
        <input type="text" id="dialog_leave" value="<#if route??>${route.leaveTime!''}</#if>" class="input normal"> 
      </dd>
    </dl>
    <dl>
        <dt>图片展示</dt>
        <dd>
            <input id="txtImgUrl" name="imgUri" type="text"  value="<#if route??>${route.imgUrl!''}</#if>" class="input normal upload-path">
            <div class="upload-box upload-img"></div>
            <div class="photo-list thumb_ImgUrl_show">
                <ul>
                    <li>
                        <div class="img-box1"></div>
                    </li>
                </ul>
            </div>
        </dd>
    </dl>
     <dl>
            <dt>详细描述</dt>
            <dd>
                <textarea name="detail" id="detail" class="editor"><#if route??>${route.description!''}</#if></textarea>
            </dd>
        </dl>    
</div>

</body>
</html>