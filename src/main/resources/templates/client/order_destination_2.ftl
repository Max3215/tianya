<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta charset="UTF-8"><title>目的地——选择服务页面</title><link rel="shortcut icon" href="/client/images/tianya.ico"><link rel="stylesheet" type="text/css" href="/client/css/base.css"><link rel="stylesheet" type="text/css" href="/client/css/common.css"><link rel="stylesheet" type="text/css" href="/client/css/f_cruise.css"><link rel="shortcut icon" href="/client/images/tianya.ico"><script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script><script src="/client/js/rich_lee.js" type="text/javascript"></script><script src="/client/js/angular.js" type="text/javascript"></script>  <script type="text/javascript">window.onload = function () {    act_nav();};var app = angular.module('myApp', []);app.controller('myCtrl', function($scope, $http) {    $scope.goodsPrice=(${totalPrice?string("0.00")}+${price1?string("0.00")});    $scope.goodsPrice2=${totalPrice?string("0.00")};        <#if goods?? && goods.psList??>        <#list goods.psList as item>            $scope.ps${item_index}check=false;            $scope.ps${item_index}adultNum=1;            $scope.ps${item_index}childNum=0;            $scope.ps${item_index}adultPrice=${item.price1?string("0.00")};            $scope.ps${item_index}childPrice=${item.price2?string("0.00")};        </#list>    </#if>    $scope.goNext = function() {    	    	        $http({            url:'/order/ship',            method:'POST',            params:{              // check : [<#list goods.psList as item>{id : ${item.id?c},check:$scope.ps${item_index}check, adultNum : $scope.ps${item_index}adultNum, childNum: $scope.ps${item_index}childNum},</#list>]               listIds : [<#list goods.psList as item>${item.id?c},</#list>],               check : [<#list goods.psList as item>$scope.ps${item_index}check,</#list>],               adultNum : [ <#list goods.psList as item>$scope.ps${item_index}adultNum,</#list>],               childNum : [ <#list goods.psList as item>$scope.ps${item_index}childNum,</#list>],               type : "sev"             }        }).success(function(data){            //响应成功            window.location.href="/order/ship3"        });    }});</script></head><body><!-- ********************头部******************** -->    <#include "/client/common_header.ftl"><!-- ********************导航-结束******************** --><!-- ********************中间内容Start******************** --><div class="wrapper" ng-app="myApp" ng-controller="myCtrl">    <!-- 进度条-->    <div class="progress_warp">        <div class="progress_line p_line_2"></div>        <div class="progress_txt">            <label>酒店交通</label>            <label>可选服务</label>            <label>填写信息</label>            <label>提交并支付</label>        </div>    </div>    <!-- 产品简述-->    <div class="product_desc">        <div class="left_desc">            <div class="d_title">                <a href="/detail/${goods.id?c}" class="title">${goods.title!''} </a><a href="/detail/${goods.id?c}" class="check_detail">查看产品详情 >></a>            </div>            <div class="d_items">            	<#if goods.categoryIdTree?? && goods.categoryIdTree?index_of("[1]",0) gt -1>                <div class="d_item">                    <label>已选航期：<font color="ff9100"><#if leaveDate??>${leaveDate!''}</#if></font></label> <a href="javascript:history.go(-1);" class="update_date">更改</a>                </div>                </#if>                <div class="d_item">                    <label>出发日期：<font><#if leaveDate??>${leaveDate!''}</#if></font></label>                </div>            </div>            <div class="d_items">            	<#if goods.categoryIdTree?? && goods.categoryIdTree?index_of("[1]",0) gt -1>                <div class="d_item">                    <label>已选择房型：<font>标准內舱双人间</font></label>                </div>                <div class="d_item">                    <label>甲板：<font>8层</font></label>                </div>                </#if>                <div class="d_item">                    <#--<label>人数：<font>成人${adiltNum!'0'}人、儿童${childNum!'0'}人</font></label>-->                    <label>人数：<font>成人${chengrenshu}人、儿童${ertongshu}人</font></label>                </div>            </div>        </div>        <div class="right_price"><#if goods.psList?? && goods.psList?size gt 0>1111</#if>            <label>总计：<font>￥<#if goods.psList?? && goods.psList?size gt 0>{{goodsPrice2+<#list goods.psList as item>+(ps${item_index}check?1:0) * (ps${item_index}adultNum * ps${item_index}adultPrice + ps${item_index}childNum * ps${item_index}childPrice)</#list>}}<#else>{{goodsPrice2}}</#if>元</font></label>            <a href="javascript:;" ng-click="goNext()">下一步</a>        </div>    </div>    <!-- 岸上观光-->    <div class="item_info">        <h1>可选项目</h1>        <div class="item_warp">            <ul class="content_top">                <li class="li01"><label>选择</label></li>                <li class="li05"><label>项目</label></li>                <li class="li03"><label>人数</label></li>                <li class="li04"><label>价格</label></li>            </ul>            <#if goods?? && goods.psList??>                <#list goods.psList as item>                    <#if item.type=="可选项目">                        <ul class="info_normal">                            <li class="li01"><label><input type="checkbox" name="chk_room" ng-model="ps${item_index}check"/></label></li>                            <li class="li05"><label>${item.title!''}</label></li>                            <li class="li03">                                <div class="people_num">                                    <label>成人</label><input type="number" min="0" max="${chengrenshu}" ng-model="ps${item_index}adultNum"><label>人</label>                                </div>                                <div class="people_num">                                    <label>儿童</label><input type="number" min="0" max="${ertongshu}" ng-model="ps${item_index}childNum"><label>人</label>                                </div>                            </li>                            <li class="li04">                                <label>                                    <#if item.price1??>￥${item.price1?string("0.00")}/成人</#if> <#if item.price2??>￥${item.price2?string("0.00")}/儿童</#if>                                </label>                                <label class="total_price">                                    总计：<font>￥{{ps${item_index}adultNum * ps${item_index}adultPrice + ps${item_index}childNum * ps${item_index}childPrice}}元</font>                                </label>                            </li>                        </ul>                     </#if>                </#list>            </#if>        </div>    </div>    <!-- 签证-->    <div class="item_info">        <h1>签证</h1>        <div class="item_warp">            <ul class="content_top">                <li class="li01"><label>选择</label></li>                <li class="li05"><label>类型</label></li>                <li class="li03"><label>人数</label></li>                <li class="li04"><label>价格</label></li>            </ul>            <#if goods?? && goods.psList??>                <#list goods.psList as item>                    <#if item.type=="签证">                        <ul class="info_normal">                            <li class="li01"><label><input type="checkbox" name="chk_room" ng-model="ps${item_index}check"/></label></li>                            <li class="li05"><label>${item.title!''}</label></li>                            <li class="li03">                                <div class="people_num">                                <label>成人</label><input type="number" min="0" max="${chengrenshu}" ng-model="ps${item_index}adultNum"><label>人</label>                            </div>                            <div class="people_num">                                <label>儿童</label><input type="number" min="0" max="${ertongshu}" ng-model="ps${item_index}childNum"><label>人</label>                            </div>                            </li>                            <li class="li04">                                <label>                                    <#if item.price1??>￥${item.price1?string("0.00")}/成人</#if> <#if item.price2??>￥${item.price2?string("0.00")}/儿童</#if>                                </label>                                <label class="total_price">                                    总计：<font>￥{{ps${item_index}adultNum * ps${item_index}adultPrice + ps${item_index}childNum * ps${item_index}childPrice}}元</font>                                </label>                            </li>                        </ul>                     </#if>                </#list>            </#if>        </div>    </div>    <!-- 保险-->    <div class="item_info">        <h1>保险信息</h1>        <div class="item_warp">                            <#--                            <li class="li01"><label><input type="checkbox" name="chk_room" ng-model="ps${item_index}check"/></label></li>                            <li class="li05">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${goods.insurance!''}</li>                            -->                            <p class="ship_word">								<span style="color:#666666;font-family:微软雅黑, Arial, Helvetica, sans-serif;font-size:14px;line-height:24px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${goods.insurance!''}</span>							</p>                            <#--                            <li class="li03">                                <div class="people_num">                                <label>成人</label><input type="number" min="0" ng-model="ps${item_index}adultNum"><label>人</label>                            </div>                            <div class="people_num">                                <label>儿童</label><input type="number" min="0" ng-model="ps${item_index}childNum"><label>人</label>                            </div>                            </li>                            <li class="li04">                                <label>                                    <#if item.price1??>￥${item.price1?string("0.00")}/成人</#if> <#if item.price2??>￥${item.price2?string("0.00")}/儿童</#if>                                </label>                                <label class="total_price">                                    总计：<font>￥{{ps${item_index}adultNum * ps${item_index}adultPrice + ps${item_index}childNum * ps${item_index}childPrice}}元</font>                                </label>                            </li>                            -->        </div>    </div>    <!-- 清除页面浮动-->    <span class="clear"></span></div><!-- ********************中间内容End******************** --><!-- ********************底部******************** -->    <#include "/client/common_footer.ftl"><!-- ********************底部-结束******************** --></body></html>