<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta charset="UTF-8"><title>邮轮——选择舱房</title><link rel="shortcut icon" href="/client/images/tianya.ico"><link rel="stylesheet" type="text/css" href="/client/css/base.css">    <link rel="stylesheet" type="text/css" href="/client/css/common.css">    <link rel="stylesheet" type="text/css" href="/client/css/f_cruise.css">    <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>    <script src="/client/js/rich_lee.js" type="text/javascript"></script>    <script type="text/javascript">        window.onload = function () {            act_nav();        };        $(function(){            $(".txt_onlynumber").onblur(function(){                $(this).val($(this).val.replace(/\D/g,''));});//            .onkeydown(function(){//                $(this).value=$(this).value.replace(/\D/g,'')//            });//        });    </script></head><body><!-- ********************头部******************** --><#include "/client/common_header.ftl" /><!-- ********************头部-结束******************** --><!-- ********************中间内容Start******************** --><div class="wrapper">    <!-- 进度条-->    <div class="progress_warp">        <div class="progress_line p_line_1"></div>        <div class="progress_txt">            <label>选择舱房</label>            <label>可选服务</label>            <label>填写信息</label>            <label>提交并支付</label>        </div>      </div>    <!-- 产品简述-->    <div class="product_desc" id="cart">        <#include "/client/order_ship_1_cart.ftl">    </div>	    <!-- 内舱房-->    <div class="item_info">        <h1>内舱房</h1>        <div class="item_warp">            <ul class="content_top">                <li class="li01"><label>选择</label></li>                <li class="li03"><label>房型</label></li>                <li class="li03"><label>人数</label></li>                <li class="li04"><label>价格</label></li>            </ul><script type="text/javascript">function changeNumber(pgid){    var num1 = $("#num1-"+pgid).val();    var num2 = $("#num2-"+pgid).val();        var price1 = $("#price1-"+pgid).html();    var price2 = $("#price2-"+pgid).html();        $("#totalPrice"+pgid).html(num1*price1+num2*price2);}function change(gpId){    var num1 = $("#num1-"+gpId).val();    var num2 = $("#num2-"+gpId).val();    var cartId = $("#cartId").val();    changeNumber(gpId);        $.ajax({        type : "post",        url : "/order/goodsPs/select",        data :{"cartId":cartId,"gpId":gpId,"num1":num1,"num2":num2,"in":"chk"},        success:function(res){            $("#cart").html(res);        }    })}function isSelect(gpId){    var num1 = $("#num1-"+gpId).val();    var num2 = $("#num2-"+gpId).val();    var cartId = $("#cartId").val();        $.ajax({        type : "post",        url : "/order/goodsPs/select",        data :{"cartId":cartId,"gpId":gpId,"num1":num1,"num2":num2},        success:function(res){            $("#cart").html(res);        }    })}</script>                       				<#if goods.psList??>				<#list goods.psList as item>					<#if item.type=="内舱房">						<ul class="info_normal">							<li class="li01"><label><input type="checkbox" class="radio" onclick="isSelect(${item.id?c})" value="${item.id?c}"  name="chk_room" /></label></li>			                <li class="li03"><label><span id="title${item.id?c}">${item.title!''}</span></label></li>			                <li class="li03">			                    <div class="people_num">			                         <label>成人</label><input type="text" value="0" id="num1-${item.id?c}" class="txt_onlynumber" onblur="change(${item.id?c})">			                         <label>人</label>			                    </div>			                    <div class="people_num">			                         <label>儿童</label><input type="text" value="0" id="num2-${item.id?c}" class="txt_onlynumber" onblur="change(${item.id?c})">			                         <label>人</label>			                    </div>			                </li>			                <li class="li04">			                    <label>			                        单价：<#if item.price1??>￥<span id="price1-${item.id?c}">${item.price1?string("0.00")}</span>/成人</#if> 			                              <#if item.price2??>￥<span id="price2-${item.id?c}">${item.price2?string("0.00")}</span>/儿童</#if>			                    </label>			                    <label class="total_price">			                        总计：<font>￥<span id="totalPrice${item.id?c}">0</span></font>			                    </label>			                </li>		                </ul>					</#if>				</#list>			</#if>        </div>    </div>    <!-- 阳台房-->    <div class="item_info">        <h1>阳台房</h1>        <div class="item_warp">            <ul class="content_top">                <li class="li01"><label>选择</label></li>                <li class="li02"><label>房型</label></li>                <li class="li03"><label>人数</label></li>                <li class="li04"><label>价格</label></li>            </ul>            <#if goods.psList??>				<#list goods.psList as item>					<#if item.type=="阳台房">						<ul class="info_normal">							<li class="li01"><label><input type="checkbox" class="radio" onclick="isSelect(${item.id?c})" value="${item.id?c}" name="chk_room"/></label></li>			                <li class="li03"><label>${item.title!''}</label></li>			                <li class="li03">			                    <div class="people_num">                                     <label>成人</label><input type="text" value="0" id="num1-${item.id?c}" class="txt_onlynumber" onblur="change(${item.id?c})">                                     <label>人</label>                                </div>                                <div class="people_num">                                     <label>儿童</label><input type="text" value="0" id="num2-${item.id?c}" class="txt_onlynumber" onblur="change(${item.id?c})">                                     <label>人</label>                                </div>			                </li>			                <li class="li04">			                    <label>			                        单价：<#if item.price1??>￥<span id="price1-${item.id?c}">${item.price1?string("0.00")}</span>/成人</#if>                                           <#if item.price2??>￥<span id="price2-${item.id?c}">${item.price2?string("0.00")}</span>/儿童</#if>			                    </label>			                    <label class="total_price">			                        总计：<font>￥<span id="totalPrice${item.id?c}">0</span></font>			                    </label>			                </li>		                </ul>					</#if>				</#list>			</#if>        </div>    </div>    <!-- 清除页面浮动-->    <span class="clear"></span></div><!-- ********************中间内容End******************** --><!-- ********************底部******************** --><#include "/client/common_footer.ftl" /></body></html>