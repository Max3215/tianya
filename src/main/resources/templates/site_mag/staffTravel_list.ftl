<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>资产调节表</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
</head>
<body class="mainbody">

<form name="form1" method="post" action="/Verwalter/staffTravel/list" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="">
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
</div>

<script type="text/javascript">
var theForm = document.forms['form1'];
if (!theForm) {
    theForm = document.form1;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}

function change1(){
	var type11 = $("#fenlei").val();
	var timeId = $("#timeId11").val();
	window.location.href="/Verwalter/scstl/list/";
}

</script>

    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a><span>旅游线路</span></a>
    </div>
    <!--/导航栏-->
    <!--工具栏-->
    <div class="toolbar-wrap">
        <div id="floatHead" class="toolbar">
            <div class="l-list">
                <ul class="icon-list">
                	<li>
                        <a class="add" href="/Verwalter/staffTravel/edit" ><i></i><span>新增</span></a>
                    </li>
                    <li>
                        <a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
                    </li>
                    <li>
                        <a class="save" href="javascript:__doPostBack('btnCopy','')" ><i></i><span>复制</span></a>
                    </li>
                    <li>
                        <li><a onclick="return ExePostBack('btnDelete');" id="btnDelete" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除</span></a></li>
                    </li>
                    <li>
                        <li><a  id="btnPayoff" onclick="return ExePostBack('btnPayoff','是否结算选择线路？');" href="javascript:__doPostBack('btnPayoff','')"><i></i><span>结算</span></a></li>
                    </li>
                    <li>
                        <a class="all" href="javascript:__doPostBack('export','')"><span>导出本页</span></a>
                    </li>
                    <li>
                        <a><span>合同价格：${totalContractPrice?c!'0'}</span></a>
                    </li>
                    <li>
                        <a><span>应优惠金额：${totalDiscountAmount?c!'0'}</span></a>
                    </li>
                    <li>
                        <a><span>本次余额：${totalCurrentBalance?c!'0'}</span></a>
                    </li>
                </ul>
                	<div class="menu-list">
	                    <div class="rule-single-select">
	                        <select name="unit_s">
	                        	<option value="">所有单位</option>
                                <option value="1" <#if unit_s?? && unit_s==1>selected="selected"</#if>>重庆交通运业有限责任公司</option>
	                            <option value="2" <#if unit_s?? && unit_s==2>selected="selected"</#if>>重庆交通运业有限责任公司渝北双凤桥汽车站</option>
	                            <option value="3" <#if unit_s?? && unit_s==3>selected="selected"</#if>>重庆交通运业有限责任公司重庆汽车站</option>
	                            <option value="4" <#if unit_s?? && unit_s==4>selected="selected"</#if>>重庆交通运业有限责任公司陈家坪汽车站</option>
	                            <option value="5" <#if unit_s?? && unit_s==5>selected="selected"</#if>>重庆交通运业有限责任公司龙头寺汽车站</option>
	                            <option value="6" <#if unit_s?? && unit_s==6>selected="selected"</#if>>重庆交通运业有限责任公司富丽大酒店</option>
	                            <option value="7" <#if unit_s?? && unit_s==7>selected="selected"</#if>>重庆交通运业有限责任公司站务中心汽车北站</option>
	                            <option value="8" <#if unit_s?? && unit_s==8>selected="selected"</#if>>重庆交通运业有限责任公司站务中心南坪汽车站</option>
	                            <option value="9" <#if unit_s?? && unit_s==9>selected="selected"</#if>>重庆市公路客运联网售票中心有限公司</option>
	                            <option value="10" <#if unit_s?? && unit_s==10>selected="selected"</#if>>重庆交通运业有限责任公司站务中心长途汽车站</option>
	                            <option value="11" <#if unit_s?? && unit_s==11>selected="selected"</#if>>重庆迅为四公里交通换乘枢纽有限公司</option>
	                            <option value="12" <#if unit_s?? && unit_s==12>selected="selected"</#if>>重庆天涯国际旅行社有限公司</option>
	                            <option value="13" <#if unit_s?? && unit_s==13>selected="selected"</#if>>重庆交通运业有限责任公司商旅服务分公司</option>
	                            <option value="14" <#if unit_s?? && unit_s==14>selected="selected"</#if>>重庆交通运业有限责任公司稽查大队</option>
	                            <option value="15" <#if unit_s?? && unit_s==15>selected="selected"</#if>>重庆交通运业有限责任公司交运快递</option>
	                            <option value="16" <#if unit_s?? && unit_s==16>selected="selected"</#if>>重庆交通运业有限责任公司富苑宾馆</option>
	                            <option value="18" <#if unit_s?? && unit_s==18>selected="selected"</#if>>重庆交通运业有限责任公司巴南龙洲湾汽车站</option>
	                            <option value="17" <#if unit_s?? && unit_s==17>selected="selected"</#if>>散客</option>
	                        </select>
	                    </div>
                   	</div>
                   <div class="menu-list">
                    <div class="rule-single-select">
                        <select name="isSettle_s">
                            <option value="">结算情况</option>
                            <option value="true" <#if isSettle_s?? && isSettle_s>selected="selected"</#if>>已结算</option>
                            <option value="false" <#if isSettle_s?? && !isSettle_s>selected="selected"</#if>>未结算</option>
                        </select>
                    </div>
                   </div>
                    <ul class="icon-list">
                    <li>
                        <input name="calendar1_s" type="text" value="<#if calendar1_s??>${calendar1_s}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})">
                    </li>
                    <li><a>至</a></li>
                    <li>
                        <input name="calendar2_s" type="text" value="<#if calendar2_s??>${calendar2_s}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})">
                    </li>
                </ul>
            </div>
            
            <div class="a-list" >
                <input name="keywords" type="text" class="keyword" value="<#if keywords??>${keywords!''}</#if>">
                <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('btnSearch','')">查询</a>
            </div>
        </div>
    </div>
    <!--/工具栏-->
    <!--列表-->
    
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
<tbody>
    <tr class="odd_bg">
        <th width="5%">
            选择
        </th>
        <th align="left">
           团队编号
        </th>
        <th align="left" width="15%">
           单位
        </th>
         <th align="left">
            职工
        </th>
        <th align="left"  >
            家属
        </th>
        <th align="left" >
            出行时间
        </th>
        <th align="left" >
            合同总价
        </th>
        <th align="left" >
            预付款
        </th>
        <th align="left" >
            尾款
        </th>
        <th align="left" >
            签字人
        </th>
        <th align="left" >
            创建时间
        </th>
        <th align="left" >
            结算表编号
        </th>
        <th align="left" >
            操作
        </th>
    </tr>

    <#if scstl_page??>
        <#list scstl_page.content as scstl>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${scstl_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${scstl.id?c}">
                </td>
                <td>
                    <a href="/Verwalter/staffTravel/edit?id=${scstl.id?c}">${scstl.teamNumber!''}</a>
                </td>
                <td>
                    <a href="/Verwalter/staffTravel/edit?id=${scstl.id?c}">${scstl.unit!""}</a>
                </td>
                <td><#if scstl.staffName??>${scstl.staffName!""}</#if></td>
                <td><#if scstl.staffName??>${scstl.family!"0"}</#if></td>
                <td>
                	<#if scstl.travelTime??>${scstl.travelTime?string('yyyy-MM-dd')}</#if>
                </td>
                <td><#if scstl.contractPrice??>${scstl.contractPrice?c!"0.0"}</#if></td>
                <td><#if scstl.cashMoney??>${scstl.cashMoney?c!"0.0"}</#if></td>
                <td><#if scstl.bankMoney??>${scstl.bankMoney?c!"0.0"}</#if></td>
                <td><#if scstl.siger??>${scstl.siger!""}</#if></td>
                <td>
                	<#if scstl.createTime??>${scstl.createTime?string('yyyy-MM-dd')}</#if>
                </td>
                <td>
                	<#if scstl.costNumber??>${scstl.costNumber!''}</#if>
                </td>
                <td >
                    <a href="/Verwalter/staffTravel/edit?id=${scstl.id?c}">编辑</a>
                </td>
            </tr>
        </#list>
    </#if>
</tbody>
</table>
        
<!--/列表-->
<!--内容底部-->
<#assign PAGE_DATA=scstl_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>
