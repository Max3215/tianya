<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>打印订单窗口</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js?skin=idialog"></script>
<script type="text/javascript">
    //窗口API
    var api = frameElement.api, W = api.opener;
    api.button({
        name: '确认打印',
        focus: true,
        callback: function () {
            printWin();
        }
    }, {
        name: '取消'
    });
    //打印方法
    function printWin() {
        var oWin = window.open("", "_blank");
        oWin.document.write(document.getElementById("content").innerHTML);
        oWin.focus();
        oWin.document.close();
        oWin.print()
        oWin.close()
    }
</script>
</head>
<body style="margin: 0;">
    <form name="form1" method="post" action="dialog_print.aspx?order_no=B15041121411832" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="">
</div>

<div>

    <input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="86EF5C69">
</div>
    <div id="content">
        <table width="800" border="0" align="center" cellpadding="3" cellspacing="0" style="font-size: 12px; font-family: '微软雅黑'; background: #fff;">
            <tbody><tr>
                <td width="346" height="50" style="font-size: 20px;">
                    商品订单</td>
                <td width="216">订单号：${order.orderNumber!''}<br>
                    日&nbsp;&nbsp; 期：${order.orderTime?string("yyyy-MM-dd")}</td>
                <td width="220">操&nbsp;作&nbsp;人：${manager!''}<br>
                    打印时间：${now?string("yyyy-MM-dd HH:mm:ss")}</td>
            </tr>
            <tr>
                <td colspan="3" style="padding: 10px 0; border-top: 1px solid #000;">
                    
                    <table width="100%" border="0" cellspacing="0" cellpadding="5" style="font-size: 12px; font-family: '微软雅黑'; background: #fff;">
                        <tbody><tr>
                            <td align="left" style="background: #ccc;">商品总名称</td>
                            <td align="left" style="background: #ccc;">商品副名称</td>
                            <td width="10%" align="left" style="background: #ccc;">成人数量</td>
                            <td width="10%" align="left" style="background: #ccc;">成人价/人</td>
                            <td width="10%" align="left" style="background: #ccc;">儿童数量</td>
                            <td width="10%" align="left" style="background: #ccc;">儿童价/人</td>
                            <td width="12%" align="left" style="background: #ccc;">金额合计</td>
                        </tr>
                    <#if order??>
                    	
                            <tr>
                            	<td>
                           			 ${order.goodsTitle!''}
                           		 </td>
                           	<#if order.orderGoodsList??>
                        	<#list order.orderGoodsList as og>
                                <td>
                                    ${og.goodsTitle!''}
                                </td>
                                <td>${og.quantity1!'0'}</td>
                                <td>${og.price1?string("#.00")}</td>
                                <td>${og.quantity2!'0'}</td>
                                <td>${og.price2?string("#.00")}</td>
                                <td>${((og.price1*og.quantity1)+(og.price2*og.quantity2))?string("#.00")}</td>
                            </#list>
                        	</#if>
                            </tr>
                        
                    </#if>
                    </tbody>
                </table>
                        
                </td>
            </tr>
            <tr>
                <td colspan="3" style="border-top: 1px solid #000;">
                    <table width="100%" border="0" cellspacing="0" cellpadding="5" style="margin: 5px auto; font-size: 12px; font-family: '微软雅黑'; background: #fff;">
                        <tbody>
                        <tr>
                            <td width="44%">会员账户：
                                ${order.username}
                            </td>
                            </td>
                          	<#if order.shippingName??><td width="56%">收货姓名：${order.shippingName!''}<br>                          
                            </td>
                            </#if>
                        </tr>
                        <tr>
                            <td valign="top">支付方式：${order.payTypeTitle!''}</td>
                            <#if order.shippingAddress??>
                            <td>送货地址：
                                ${order.shippingAddress!''}<br>
                            </td>
                            </#if>
                        </tr>
                        <tr>
                            <#if order.shopId??><td valign="top">特产商品数量：${order.shopId!''}/斤、盒、件或人</td></#if>
                            <#if goods??><td valign="top">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：<#if goods.salePrice??>${goods.salePrice?string("#.00")}</#if></td></#if>
                        </tr>
                        <tr>
                            <td valign="top">订单留言：${order.remarkInfo!''}</td>
                            <td valign="top">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${order.shippingPhone!''}</td>
                        </tr>
                    </tbody></table>
                    <table width="100%" border="0" align="center" cellpadding="5" cellspacing="0" style="border-top: 1px solid #000; font-size: 12px; font-family: '微软雅黑'; background: #fff;">
                        <tbody><tr>
                            <td align="right">商品金额：￥<#if order.totalPrice??>${order.totalPrice?string("0.00")}</#if>
                                </td>
                        </tr>
                    </tbody></table>
                </td>
            </tr>
        </tbody></table>
    </div>
    </form>


</body></html>