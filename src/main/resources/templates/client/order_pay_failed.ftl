<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=${charset!'UTF-8'}" />
<title>订单支付失败</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
    <link rel="stylesheet" type="text/css" href="/client/css/base.css">
    <link rel="stylesheet" type="text/css" href="/client/css/common.css">
    <link rel="stylesheet" type="text/css" href="/client/css/f_cruise.css">
    
    <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
    <script src="/client/js/rich_lee.js" type="text/javascript"></script>

    <script type="text/javascript">
        window.onload = function () {
            act_nav();
        };
    </script>
</head>
<body>
<#include "/client/common_header.ftl" />
  <div class="main">
    <#if order??>
    <h3>订单号:${order.orderNumber!''}</h3>
    <h3>支付金额:${order.totalPrice?string('0.00')}</h3>
    <h3>支付方式:${order.payTypeTitle!''}</h3>
    </#if>
    <h3>订单状态</h3>
    <div class="clear h20"></div>
    <div id="trans-status">
        <div class="notice-title status-failed">
            <img class="notice-icon" title="付款失败" src="/client/img/transfailed.png"></img>
            <span class="notice-content"><#if order??>付款失败，或者支付结果验证失败，如果订单已经成功支付，请联系客服处理！<#else>找不到对应的订单！</#if></span>
        </div>
    </div>
    <div class="clear h40"></div>
  </div>
<#include "/client/common_footer.ftl" />
</body>
</html>
<!--结束-->
