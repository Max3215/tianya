<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>查看订单信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function () {
            $("#btnConfirm").click(function () { OrderConfirm(); });   //确认订单
            $("#btnPayment").click(function () { OrderPayment(); });   //确认付款
            $("#btnPaymentLeft").click(function () { OrderPaymentLeft(); });   //确认付尾款
            $("#btnService").click(function () { OrderService(); });   //确认到店消费  // 确认已评价 gl

            $("#btnHDFKPayment").click(function () { OrderHDFKPayment(); });   //确认货到付款已付款
            $("#btnOrderExpress").click(function () { OrderExpress(); });   //确认发货
            $("#btnOrderComplete").click(function () { OrderComplete(); }); //完成订单
            $("#btnCancel").click(function () { OrderCancel(); });     //取消订单
            // $("#btnInvalid").click(function () { OrderInvalid(); });   //作废订单
            $("#btnPrint").click(function () { OrderPrint(); });       //打印订单
            $("#btnOrderReceive").click(function () { OrderReceive(); }); //确认收货
            $("#btnHDFKReceive").click(function () { OrderHDFKReceive(); }); //货到付款确认收货
            $("#btnEditAcceptInfo").click(function () { EditAcceptInfo(); }); //修改收货信息
            $("#btnEditRemark").click(function () { EditOrderRemark(); });    //修改订单备注
            $("#btnEditRealAmount").click(function () { EditRealAmount(); }); //修改商品总金额
            $("#btnEditprepay").click(function () { Editprepay(); }); //修改预付金额
            $("#btnEditfinalPayment").click(function () { EditfinalPayment(); }); //修改尾款金额
            $("#btnEditExpressFee").click(function () { EditExpressFee(); }); //修改配送费用
            $("#btnEditPaymentFee").click(function () { EditPaymentFee(); }); //修改支付手续费
            $("#btnOrderChangeStatus").click(function () { ChangeOrderStatus(); }); //强制修改订单状态
            
        });

        //确认收货
        function OrderReceive() {
            var dialog = $.dialog.confirm('该步骤将确认收货，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderReceive" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });

        }
        //货到付款 确认收货
        function OrderHDFKReceive() {
            var dialog = $.dialog.confirm('该步骤将确认收货，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderReceive" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });

        }

        //确认订单
        function OrderConfirm() {
            var dialog = $.dialog.confirm('确认订单后将无法修改金额，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderConfirm" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }
        
        //确认付款
        function OrderPayment() {
            var dialog = $.dialog.confirm('操作提示信息：<br />1、该订单使用在线支付方式，付款成功后自动确认；<br />2、如客户确实已打款而没有自动确认可使用该功能；<br />3、确认付款后无法修改金额，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderPay" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }
        
        //确认付尾款
        function OrderPaymentLeft() {
            //var dialog = $.dialog.confirm('操作提示信息：<br />1、该订单使用在线支付方式，付尾款成功后自动确认；<br />2、如客户确实已打款而没有自动确认可使用该功能；<br />3、确认付款后无法修改金额，确认要继续吗？', function () {
            var dialog = $.dialog.confirm('操作提示信息：<br />修改后订单状态将变成<span style="color: red;">\"待评价\"</span>，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderPayLeft" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }
        
        //确认到店消费  // 确认已评价 gl
        function OrderService() {
            var dialog = $.dialog.confirm('操作提示信息：<br />修改后订单状态将变成<span style="color: red;">\"已完成\"</span>，确定要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderService" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }

        //确认货到付款已付款
        function OrderHDFKPayment() {
            var dialog = $.dialog.confirm('操作提示信息：<br />1、该订单使用货到付款方式，确定后，将完成订单；<br />确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderPayOffline" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }
        //确认发货
        function OrderExpress() {
            var orderNumber = $.trim($("#spanOrderNumber").text());
            var dialog = $.dialog({
                title: '确认发货',
                content: 'url:/Verwalter/order/dialog/delivery?orderNumber=' + orderNumber,
                min: false,
                max: false,
                lock: true,
                width: 450,
                height:350
            });
        }
        
        //确认完成
        function OrderComplete() {
             var dialog = $.dialog.confirm('确认后用户将不能进行评价，是否继续？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderFinish" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }

        //取消订单
        function OrderCancel() {
            var dialog = $.dialog({
                title: '取消订单',
                content: '操作提示信息：<br />1、该订单为线上支付订单，请先确定购买者是否真的不需要继续支付；<br />2、取消的订单，将不在购买流程中显示，您可以到取消的订单中查阅；<br />3、请单击相应按钮继续下一步操作！',
                min: false,
                max: false,
                lock: true,
                icon: 'confirm.gif',
                button: [{
                    name: '直接取消',
                    callback: function () {
                        var orderNumber = $.trim($("#spanOrderNumber").text());
                        var postData = { "orderNumber": orderNumber, "type": "orderCancel" };
                        //发送AJAX请求
                        sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                        return false;
                    },
                    focus: true
                }, {
                    name: '关闭'
                }]
            });

        }
        //作废订单
        function OrderInvalid() {
            var dialog = $.dialog({
                title: '取消订单',
                content: '操作提示信息：<br />1、设为作废订单，将不可逆转；<br />2、会员用户，自动检测退还金额或粮草到账户；<br />3、请单击相应按钮继续下一步操作！',
                min: false,
                max: false,
                lock: true,
                icon: 'confirm.gif',
                button: [{
                    name: '检测退还',
                    callback: function () {
                        var order_no = $.trim($("#spanOrderNumber").text());
                        var postData = { "order_no": order_no, "edit_type": "order_invalid", "check_revert": 1 };
                        //发送AJAX请求
                        sendAjaxUrl(dialog, postData, "../../tools/Verwalter_ajax.ashx?action=edit_order_status");
                        return false;
                    },
                    focus: true
                }, {
                    name: '直接作废',
                    callback: function () {
                        var order_no = $.trim($("#spanOrderNumber").text());
                        var postData = { "order_no": order_no, "edit_type": "order_invalid", "check_revert": 0 };
                        //发送AJAX请求
                        sendAjaxUrl(dialog, postData, "../../tools/Verwalter_ajax.ashx?action=edit_order_status");
                        return false;
                    }
                }, {
                    name: '关闭'
                }]
            });
        }
        //打印订单
        function OrderPrint() {
            var dialog = $.dialog({
                title: '打印订单',
                content: 'url:/Verwalter/order/dialog/print?orderNumber=' + $.trim($("#spanOrderNumber").text()),
                min: false,
                max: false,
                lock: true,
                width: 850//,
                // height: 500
            });
        }
        //修改收货信息
        function EditAcceptInfo() {
            var dialog = $.dialog({
                title: '修改收货信息',
                content: 'url:/Verwalter/order/dialog/contact',
                min: false,
                max: false,
                lock: true,
                width: 550,
                height: 320
            });
        }
        //修改订单备注
        function EditOrderRemark() {
            var dialog = $.dialog({
                title: '订单备注',
                content: '<textarea id="orderRemark" name="txtOrderRemark" rows="2" cols="20" class="input"><#if order.remarkInfo??>${order.remarkInfo?chop_linebreak}</#if></textarea>',
                min: false,
                max: false,
                lock: true,
                ok: function () {
                    var remark = $("#orderRemark", parent.document).val();
                    if (remark == "") {
                        $.dialog.alert('对不起，请输入订单备注内容！', function () { }, dialog);
                        return false;
                    }
                    var orderNumber = $.trim($("#spanOrderNumber").text());
                    var postData = { "orderNumber": orderNumber, "type": "editMark", "data": remark };
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                    return false;
                },
                cancel: true
            });
        }
        // 修改商品总金额
        function EditRealAmount() {
            var pop = $.dialog.prompt2('请修改商品总金额', '备注',
            function (val, info) {
                if (!checkIsMoney(val)) {
                    $.dialog.alert('对不起，请输入正确的金额！', function () { }, pop);
                    return false;
                }
                
                if (undefined == info || "" == info){
                    $.dialog.alert('请输入备注信息！', function () { }, pop);
                    return false;
                }
                
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "editTotalGoodsPrice", "data": val, "info": info };
                //发送AJAX请求
                sendAjaxUrl(pop, postData, "/Verwalter/order/param/edit");
                return false;
            },
            $.trim($("#spanRealAmountValue").text())
        );
        }
        
        // 修改预付金额
        function Editprepay() {
            var pop = $.dialog.prompt2('请修改预付金额', '备注',
            function (val, info) {
                if (!checkIsMoney(val)) {
                    $.dialog.alert('对不起，请输入正确的配送金额！', function () { }, pop);
                    return false;
                }
                
                if (undefined == info || "" == info){
                    $.dialog.alert('请输入备注信息！', function () { }, pop);
                    return false;
                }
                
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "Editprepay", "data": val, "info": info };
                //发送AJAX请求
                sendAjaxUrl(pop, postData, "/Verwalter/order/param/edit");
                return false;
            },
            $.trim($("#spanprepay").text())
        );
        }
        
        // 修改尾款金额
        function EditfinalPayment() {
            var pop = $.dialog.prompt2('请修改尾款金额', '备注',
            function (val, info) {
                if (!checkIsMoney(val)) {
                    $.dialog.alert('对不起，请输入正确的配送金额！', function () { }, pop);
                    return false;
                }
                
                if (undefined == info || "" == info){
                    $.dialog.alert('请输入备注信息！', function () { }, pop);
                    return false;
                }
                
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "EditfinalPayment", "data": val, "info": info };
                //发送AJAX请求
                sendAjaxUrl(pop, postData, "/Verwalter/order/param/edit");
                return false;
            },
            $.trim($("#spanfinalPayment").text())
        );
        }
        
        
        //修改配送费用
        function EditExpressFee() {
            var pop = $.dialog.prompt2('请修改配送费用', '备注',
            function (val) {
                if (!checkIsMoney(val)) {
                    $.dialog.alert('对不起，请输入正确的配送金额！', function () { }, pop);
                    return false;
                }
                
                if (undefined == info || "" == info){
                    $.dialog.alert('请输入备注信息！', function () { }, pop);
                    return false;
                }
                
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "editDeliveryPrice", "data": val, "info": info };
                //发送AJAX请求
                sendAjaxUrl(pop, postData, "/Verwalter/order/param/edit");
                return false;
            },
            $.trim($("#spanExpressFeeValue").text())
        );
        }
        //修改手续费用
        function EditPaymentFee() {
            var pop = $.dialog.prompt2('请修改支付手续费用', '备注',
            function (val) {
                if (!checkIsMoney(val)) {
                    $.dialog.alert('对不起，请输入正确的手续费用！', function () { }, pop);
                    return false;
                }
                
                if (undefined == info || "" == info){
                    $.dialog.alert('请输入备注信息！', function () { }, pop);
                    return false;
                }
                
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "editPayPrice", "data": val, "info": info };
                //发送AJAX请求
                sendAjaxUrl(pop, postData, "/Verwalter/order/param/edit");
                return false;
            },
            $.trim($("#spanPaymentFeeValue").text())
        );
        }

     //强制修改订单状态
     function ChangeOrderStatus() {

          var dialog = $.dialog({
                title: '订单状态',
                content: '<div class="rule-single-select single-select">'
                            +'<select name="changeOrderStatusId" id="changeOrderStatus">'                           
                               //+'<option value="">选择修改状态</option>'
                                 +'<option  value="2">待付款</option>'   
                                 +'<option  value="3">待服务 </option>' 
                                 +'<option  value="4">待评价 </option>' 
                                 +'<option  value="5">已完成</option>' 
                                 +'<option  value="6">已取消</option>'                 
                            +'</select>'
                          +'</div>',
                min: false,
                max: false,
                lock: true,
                ok: function () {
                    var changeOrderStatusId = $("#changeOrderStatus", parent.document).val();
                    if (changeOrderStatusId == "") {
                        $.dialog.alert('对不起，请选择修改状态！', function () { }, dialog);
                        return false;
                    }
                    var orderNumber = $.trim($("#spanOrderNumber").text());
                    var postData = {"orderNumber": orderNumber, "type": "changeOderStatus", "changeOrderStatusId": changeOrderStatusId };
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                    return false;
                },
                cancel: true
          });
      }

        //=================================工具类的JS函数====================================
        //检查是否货币格式
        function checkIsMoney(val) {
            var regtxt = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
            if (!regtxt.test(val)) {
                return false;
            }
            return true;
        }
        //发送AJAX请求
        function sendAjaxUrl(winObj, postData, sendUrl) {
            $.ajax({
                type: "post",
                url: sendUrl,
                data: postData,
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.dialog.alert('尝试发送失败，错误信息：' + errorThrown, function () { }, winObj);
                },
                success: function (data) {
                    if (data.code == 0) {
                        winObj.close();
                        $.dialog.tips(data.msg, 2, '32X32/succ.png', function () { location.reload(); }); //刷新页面
                    } else {
                        $.dialog.alert('错误提示：' + data.message, function () { }, winObj);
                    }
                }
            });
        }
    </script>
</head>
<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/order/save" id="form1">
    <!--导航栏-->
    <div class="location" style="position: fixed; top: 0px;">
        <a href="/Verwalter/order/list/${statusId!"0"}/0" class="back"><i></i><span>返回列表页</span></a>
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a href="/Verwalter/order/list/${statusId!"0"}/0"><span>订单管理</span></a>
        <i class="arrow"></i><span>订单详细</span>
    </div>
    <div class="line10">
    </div>
    <!--/导航栏-->
    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
            <div class="content-tab-ul-wrap">
                <ul>
                    <li>
                        <a href="javascript:;" onclick="tabs(this);" class="selected">订单详细信息</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="tab-content">
        <dl>
            <dd style="margin-left: 50px; text-align: center;">
                <div class="order-flow" style="width: 850px">
                    <#if order.statusId == 1>
                        <div class="order-flow-left">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">订单已生成</p>
                                <p><#if order.orderTime??>${order.orderTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待确认</p>
                            </span>
                        </div>
                        <div class="order-flow-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待付款</p>
                            </span>
                        </div>
                        <div class="order-flow-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待评价</p>
                            </span>
                        </div>
                        <div class="order-flow-right-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">未完成</p>
                                <p></p>
                            </span>
                        </div>
                    <#elseif order.statusId == 2>
                        <div class="order-flow-left">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">订单已生成</p>
                                <p>${order.orderTime!""}</p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已确认</p>
                                <p><#if order.checkTime??>${order.checkTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待付款</p>
                            </span>
                        </div>
                        <div class="order-flow-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待服务</p>
                            </span>
                        </div>
                        <div class="order-flow-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待评价</p>
                            </span>
                        </div>
                        <div class="order-flow-right-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">完成</p>
                                <p></p>
                            </span>
                        </div>
                    <#elseif order.statusId == 3>
                        <div class="order-flow-left">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">订单已生成</p>
                                <p>${order.orderTime!""}</p>
                            </span>
                        </div>
                         <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已确认</p>
                                <p><#if order.checkTime??>${order.checkTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已付款</p>
                                <p><#if order.payTime??>${order.payTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待服务</p>
                            </span>
                        </div>
                        <div class="order-flow-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待评价</p>
                            </span>
                        </div>
                        <div class="order-flow-right-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">完成</p>
                                <p></p>
                            </span>
                        </div>
                    <#elseif order.statusId == 4>
                        <div class="order-flow-left">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">订单已生成</p>
                                <p>${order.orderTime!""}</p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已确认</p>
                                <p><#if order.checkTime??>${order.checkTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已付款</p>
                                <p><#if order.payTime??>${order.payTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已服务</p>
                                <p><#if order.serviceTime??>${order.serviceTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">待评价</p>
                            </span>
                        </div>
                        <div class="order-flow-right-wait">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">完成</p>
                                <p></p>
                            </span>
                        </div>
                    <#elseif order.statusId == 5>
                        <div class="order-flow-left">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">订单已生成</p>
                                <p>${order.orderTime!""}</p>
                            </span>
                        </div>
                         <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已确认</p>
                                <p><#if order.checkTime??>${order.checkTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已付款</p>
                                <p><#if order.payTime??>${order.payTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已服务</p>
                                <p><#if order.serviceTime??>${order.serviceTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已评价</p>
                                <p><#if order.commentTime??>${order.commentTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-right-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已完成</p>
                                <p><#if order.finishTime??>${order.finishTime!""}</#if></p>
                            </span>
                        </div>
                    <#elseif order.statusId == 6>
                        <div class="order-flow-left">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">订单已生成</p>
                                <p><#if order.orderTime??>${order.orderTime!""}</#if></p>
                            </span>
                        </div>
                        <div class="order-flow-right-arrive">
                            <a class="order-flow-input"></a>
                            <span>
                                <p class="name">已取消</p>
                                <p><#if order.cancelTime??>${order.cancelTime!''}</#if></p>
                            </span>
                        </div>
                    </#if>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>订单号</dt>
            <dd>
                <span id="spanOrderNumber"><#if order.orderNumber??>${order.orderNumber!""}</#if></span>
            </dd>
        </dl>
        <dl>
            <dt>购买商品类型</dt>
            <dd>
                <span id="spanOrderType"><#if order.orderType??>${order.orderType!""}</#if></span>
            </dd>
        </dl>
        <dl>
            <dt>商品总名称</dt>
            <dd>
                <span id="spanOrderTitle"><#if order.goodsTitle??>${order.goodsTitle!""}</#if></span>
            </dd>
        </dl>
        
        <dl>
            <dt>商品旅客信息</dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <thead>
                        <tr>
                            <th width="12%">
           ID
                            </th>
                            <th>
                                旅客姓名
                            </th>
                            <th width="12%">
                                性别
                            </th>
                            <th width="20%">
                               电话
                            </th>
                            <th width="20%">
                               证件类型
                            </th>
                            <th width="20%">
                               证件号
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <#if order.orderVisitorList??>
                            <#list order.orderVisitorList as visitor>
                                <tr class="td_c">
                                    <td><#if visitor.id??>${visitor.id!""}</#if></td>
                                    <td style="text-align: left; white-space: normal;">
                                       <#if visitor.visitorName??> ${visitor.visitorName!""}</#if>
                                    </td>
                                    <td><#if visitor.sex??>${visitor.sex!''}</#if></td>
                                    <td><#if visitor.telephone??>${visitor.telephone!''}</#if></td>
                                    <td><#if visitor.certificateType??>${visitor.certificateType!''}</#if></td>
                                    <td><#if visitor.certificateCardCode??>${visitor.certificateCardCode!''}</#if></td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody> 
                </table> 
            </dd> 
        </dl>
        
        <dl>
            <dt>商品副列表</dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <thead>
                        <tr>
                            <th width="12%">
                                商品ID
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th width="12%">
                                成人价/人
                            </th>
                            <th width="10%">
                                成人数量
                            </th>
                            <th width="12%">
                                儿童价/人
                            </th>
                            <th width="10%">
                                儿童数量
                            </th>
                            <th width="12%">
                                金额合计
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <#if order.orderGoodsList??>
                            <#list order.orderGoodsList as goods>
                                <tr class="td_c">
                                    <td><#if order.goodsId??>${goods.goodsId!""}</#if></td>
                                    <td style="text-align: left; white-space: normal;">
                                       <#if goods.goodsTitle??> ${goods.goodsTitle!""} </#if>
                                    </td>
                                    <td><#if goods.price1??>${(goods.price1)?string("#.00")}</#if></td>
                                    <td><#if goods.quantity1??>${goods.quantity1!''}</#if></td>
                                    <td><#if goods.price2??>${(goods.price2)?string("#.00")}</#if></td>
                                    <td><#if goods.quantity2??>${goods.quantity2!''}</#if></td>
                                    <td>${((goods.quantity1*goods.price1)+(goods.quantity2*goods.price2))?string("#.00")}</td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody> 
                </table> 
            </dd> 
        </dl>
        
        <dl>
            <dt>下单用户信息</dt>
            <dd>
                
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <tbody>
                    
                    
                    <tr>
                        <th valign="top">
                            用户名
                        </th>
                        <td>
                            <div class="position">
                                <div><#if user.username??>${user.username!""}</#if></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top">
                            电话
                        </th>
                        <td>
                            <div class="position">
                                <div><#if user.mobile??>${user.mobile!""}</#if></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th valign="top">
                            邮箱
                        </th>
                        <td>
                            <div class="position">
                                <div><#if user.email??>${user.email!""}</#if></div>
                            </div>
                        </td>
                    </tr>
                </tbody></table>
            </dd>
        </dl>
        
        	<dl>
            <dt>订单统计</dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <tbody>
                    
                    
                   <#-- 
                    <#if order.totalLeftPrice?? && order.totalLeftPrice gt 0>
                  
                    <tr>
                        <th width="20%">
                            预付定金
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanprepay">
                                    <#if order.totalPrice??>${order.totalPrice?string("0.00")}</#if>
                                </span> 元
                                <#if  order.statusId==2>
                                <input name="btnEditRealAmount" type="button" id="btnEditprepay" class="ibtn" value="调价">
                                </#if>
                            </div>
                        </td>
                    </tr>
                    
                    <tr>
                        <th width="20%">
                            尾款
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanfinalPayment">
                                    <#if order.totalLeftPrice??>${order.totalLeftPrice?string("0.00")}</#if>
                                </span> 元
                                
                                <input name="btnEditRealAmount" type="button" id="btnEditfinalPayment" class="ibtn" value="调价">
                                
                            </div>
                        </td>
                    </tr>
                    -->
                    
                    <tr>
                        <th width="20%">
                            订单总金额
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanRealAmountValue">
                                    <#if order.totalPrice??>${order.totalPrice?string("0.00")}</#if>
                                </span> 元
                                <#if order.statusId==1 || order.statusId==2>
                                	<input name="btnEditRealAmount" type="button" id="btnEditRealAmount" class="ibtn" value="调价">
                                </#if>
                            </div>
                        </td>
                    </tr>    
                    <#--
                    <tr>
                        <th>
                            配送费用
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanExpressFeeValue"><#if order.deliverTypeFee??>${order.deliverTypeFee?string("0.00")}</#if></span> 元
                                <#if order.statusId==1 || order.statusId==2 && order.isOnlinePay>
                                <input type="button" id="btnEditExpressFee" class="ibtn" value="调价">
                                </#if>
                            </div>
                        </td>
                    </tr>
                    -->
                    <#--
                    <tr>
                        <th>
                            支付手续费
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanPaymentFeeValue"><#if order.payTypeFee??>${order.payTypeFee?string("0.00")}</#if></span> 元
                                <#if order.statusId==1 || order.statusId==2 && order.isOnlinePay?? && order.isOnlinePay>
                                <input type="button" id="btnEditPaymentFee" class="ibtn" value="调价">
                                </#if>
                            </div>
                        </td>
                    </tr>
                   -->
                    <tr>
                        <th>
                            可获取积分总计
                        </th>
                        <td>
                            <div class="position">
                                ${order.points!"0"}分
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            儿童人数
                        </th>
                        <td>
                            <div class="position">
                                <#if order.ertongshu??>${order.ertongshu!"0"}<#else>0</#if>人
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            订单总金额
                        </th>
                        <td>
                        <#if order.totalPrice??>
                            ${order.totalPrice?string('0.00')}元（积分：${order.pointUse!'0'}&nbsp;&nbsp;优惠券：${order.couponUse!'0'}）</td>
                        </#if>
                    </tr>
                    <tr>
                        <th>
                            出发时间
                        </th>
                        <td><#if order.leaveDate??>${order.leaveDate?string("yyyy-MM-dd")}</#if></td>
                    </tr>
                    <tr>
                        <th>
                            支付方式
                        </th>
                        <td><#if order.payTypeTitle??>${order.payTypeTitle!""}</#if></td>
                    </tr>
                    <#--
                    <tr>
                        <th>
                            商城收入
                        </th>
                        <td><#if order.platformService??>${order.platformService?string('0.00')}<#else>0</#if></td>
                    </tr>
                    
                    <tr>
                        <th>
                            所属同盟店
                        </th>
                        <td>${order.shopTitle!""}</td>
                    </tr>
                    <tr>
                        <th>
                            同盟店所获返利
                        </th>
                        <td><#if order.rebate??>${order.rebate?string('0.00')}<#else>0</#if></td>
                    </tr>
                    <tr>
                        <th>
                            同盟店订单收入
                        </th>
                        <td><#if order.orderIncome??>${order.orderIncome?string('0.00')}<#else>0</#if></td>
                    </tr>
                    <tr>
                        <th>
                            培训费
                        </th>
                        <td><#if order.trainService??>${order.trainService?string('0.00')}<#else>0</#if></td>
                    </tr>
                    -->
                </tbody>
                </table>
            </dd>
        </dl>
        
        <#if order.typeId??&&order.typeId==6>
        <dl>
            <dt>收货地址</dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                <tbody>
                <#--
                    <tr>
                        <th width="20%">
                            同盟店
                        </th>
                        <td>
                            <span id="spanArea"></span> 
                            <span id="spanAddress">${order.shopTitle!""}</span>
                        </td>
                    </tr>
                    -->
                    <tr>
                        <th width="20%">
                            姓名
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanAcceptName">${order.shippingName!""}</span>
                                <#if order.statusId lt 4>
                                	<input name="btnEditAcceptInfo" type="button" id="btnEditAcceptInfo" class="ibtn" value="修改">
                                </#if>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            电话
                        </th>
                        <td><span id="spanMobile">${order.shippingPhone!""}</span></td>
                    </tr>
                    <tr>
                        <th>
                            地址
                        </th>
                        <td>
                            <span id="carType"><#if order.shippingAddress??>${order.shippingAddress!""}</#if></span>
                        </td>
                    </tr>
                    <#--
                    <tr>
                        <th>
                            车牌
                        </th>
                        <td><span id="carCode">${order.carCode!""}</span></td>
                    </tr>
                    <tr>
                        <th>
                            预约时间
                        </th>
                        <td>
                            <span></span> 
                            <span><#if order.appointmentTime??>${order.appointmentTime?string("yyyy-MM-dd HH:mm:ss")}</#if></span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            短信验证码
                        </th>
                        <td>
                            <span></span> 
                            <span><#if order.smscode??>${order.smscode!''}</#if></span>
                        </td>
                    </tr>
                    -->
                    
                </tbody>
                </table>
            </dd>
        </dl>
        </#if>
                    <#--
        <dl>
            <dt>收货信息</dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <tbody>
                    
                    
                    <tr>
                        <th>
                            是否索要发票
                        </th>
                        
                        <td>
                            <span id="spanInvoice"><#if order.isNeedInvoice?? && order.isNeedInvoice>是<#else>否</#if></span>
                        </td>
                        </tr>
                        <tr>
                        <th>发票抬头</th>
                        <td>
                            <span id="spanInvoice">${order.invoiceTitle!""}</span>
                        </td>
                        
                    </tr>
                    -->
                </tbody></table>
            </dd>
        </dl>
        
        <dl>
            <dt>备注</dt>
            <dd>
                
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <tbody>
                    
                    
                    <tr>
                        <th valign="top">
                            订单备注
                        </th>
                        <td>
                            <div class="position">
                                <div><#if order.remarkInfo??>${order.remarkInfo!""}</#if></div>
                                <input name="btnEditRemark" type="button" id="btnEditRemark" class="ibtn" value="修改" style="margin-top: -3px;">
                            </div>
                        </td>
                    </tr>
                </tbody></table>
            </dd>
        </dl>
        
    </div>
    <!--/内容-->
    <!--工具栏-->
    <div class="page-footer">
        <div class="btn-list">
            <#if tdManagerRole??>
            <#if tdManagerRole.isSys || tdManagerRole.title?contains('财务') || tdManagerRole.title?contains('经理')>
                <#if order.statusId==1>
                    <input type="button" id="btnConfirm" value="确认订单" class="btn">
                    <input type="button" id="btnCancel" value="取消订单" class="btn green">
                <#elseif order.statusId==2>
                    <input type="button" id="btnPayment" value="确认付款" class="btn">
                <#elseif order.statusId==3>
                    <input type="button" id="btnPaymentLeft" value="确认已服务" class="btn">
                <#elseif order.statusId==4>
                    <input type="button" id="btnService" value="确认已评价" class="btn green">
                </#if>
                <input type="button" id="btnOrderChangeStatus" value="强制修改订单状态" class="btn">
            </#if>
            </#if>
            <#--<#if order.statusId != 6>-->
            <input type="button" id= "btnPrint" value="打印订单" class="btn violet">
            <#if order.statusId != 6>
            <input type="button" id="btnCancel" value="强制取消" class="btn yellow">
            </#if>
            <#--</#if>-->
            <input type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
        </div>
        <div class="clear">
        </div>
    </div>
    <!--/工具栏-->
    </form>


</body></html>
