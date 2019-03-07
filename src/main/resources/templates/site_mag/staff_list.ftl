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

<form name="form1" method="post" action="/Verwalter/staff/list" id="form1">
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
	window.location.href="/Verwalter/staff/list/";
}

</script>

    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a><span>职工管理表</span></a>
    </div>
    <!--/导航栏-->
    <!--工具栏-->
    <div class="toolbar-wrap">
        <div id="floatHead" class="toolbar">
            <div class="l-list">
                <ul class="icon-list">
                	<li>
                        <a class="add" href="/Verwalter/staff/edit" ><i></i><span>新增</span></a>
                    </li>
                    <#if tdManagerRole?? && !tdManagerRole.title?contains('员工')>
                    <li>
                        <a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
                    </li>
                    <li>
                        <a  onclick="return ExePostBack('recoverAll','是否复制所选类容？');" class="save" href="javascript:__doPostBack('btnCopy','')" ><i></i><span>复制</span></a>
                    </li>
                    <li>
                        <a onclick="return ExePostBack('recoverAll','是否恢复初始状态？');"  href="javascript:__doPostBack('btnRecover','')" ><i></i><span>恢复</span></a>
                    </li>
                    <li>
                        <a onclick="return ExePostBack('recoverAll','是否全部恢复初始状态？');" href="javascript:__doPostBack('recoverAll','')" ><i></i><span>全部恢复</span></a>
                    </li>
                    </#if>
                    <#if tdManagerRole?? && tdManagerRole.isSys>
                    <li>
                        <a onclick="return ExePostBack('btnDelete','删除后将无法恢复，是否继续？');" class="del" href="javascript:__doPostBack('btnDelete','')"><i></i><span>删除记录</span></a>
                    </li>
                    </#if>
                    <li>
                        <a><span>优惠金额：<#if discount??>${discount?string('0.00')}<#else>0.0</#if></span></a>
                    </li>
                    <li>
                    	<a class="all" href="javascript:__doPostBack('export','')"><span>导出记录</span></a>
                    </li>
                </ul>
                   <div class="menu-list">
                    <div class="rule-single-select">
                        <select name="companyId" id="companyId" onchange="javascript:setTimeout(__doPostBack('companyId',''), 0);">
                            <option value="" >归属公司</option>
                            <option value="1" <#if companyId?? && companyId==1>selected="selected"</#if>>重庆交通运业有限责任公司</option>
                            <option value="2" <#if companyId?? && companyId==2>selected="selected"</#if>>重庆交通运业有限责任公司渝北双凤桥汽车站</option>
                            <option value="3" <#if companyId?? && companyId==3>selected="selected"</#if>>重庆交通运业有限责任公司重庆汽车站</option>
                            <option value="4" <#if companyId?? && companyId==4>selected="selected"</#if>>重庆交通运业有限责任公司陈家坪汽车站</option>
                            <option value="5" <#if companyId?? && companyId==5>selected="selected"</#if>>重庆交通运业有限责任公司龙头寺汽车站</option>
                            <option value="6" <#if companyId?? && companyId==6>selected="selected"</#if>>重庆交通运业有限责任公司富丽大酒店</option>
                            <option value="7" <#if companyId?? && companyId==7>selected="selected"</#if>>重庆交通运业有限责任公司站务中心汽车北站</option>
                            <option value="8" <#if companyId?? && companyId==8>selected="selected"</#if>>重庆交通运业有限责任公司站务中心南坪汽车站</option>
                            <option value="9" <#if companyId?? && companyId==9>selected="selected"</#if>>重庆市公路客运联网售票中心有限公司</option>
                            <option value="10" <#if companyId?? && companyId==10>selected="selected"</#if>>重庆交通运业有限责任公司站务中心长途汽车站</option>
                            <option value="11" <#if companyId?? && companyId==11>selected="selected"</#if>>重庆迅为四公里交通换乘枢纽有限公司</option>
                            <option value="12" <#if companyId?? && companyId==12>selected="selected"</#if>>重庆天涯国际旅行社有限公司</option>
                            <option value="13" <#if companyId?? && companyId==13>selected="selected"</#if>>重庆交通运业有限责任公司商旅服务分公司</option>
                            <option value="14" <#if companyId?? && companyId==14>selected="selected"</#if>>重庆交通运业有限责任公司稽查大队</option>
                            <option value="15" <#if companyId?? && companyId==15>selected="selected"</#if>>重庆交通运业有限责任公司交运快递</option>
                            <option value="16" <#if companyId?? && companyId==16>selected="selected"</#if>>重庆交通运业有限责任公司富苑宾馆</option>
                            <option value="18" <#if companyId?? && companyId==18>selected="selected"</#if>>重庆交通运业有限责任公司巴南龙洲湾汽车站</option>
                        </select>
                    </div
                   <div class="menu-list">
                    <div class="rule-single-select">
                        <select name="isClose" id="fenlei" onchange="javascript:setTimeout(__doPostBack('settle',''), 0);">
                            <option value="" >结算情况</option>
                            <option value="yes" <#if isClose?? && isClose>selected="selected"</#if>>已结算</option>
                            <option value="no" <#if isClose?? && !isClose>selected="selected"</#if>>未结算</option>
                        </select>
                    </div>
                   </div>
                    <ul class="icon-list">
                        <li>
                            <input name="start" type="text" value="<#if startime??>${startime?string('yyyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})">
                        </li>
                        <li><a>至</a></li>
                        <li>
                            <input name="end" type="text" value="<#if endTime??>${endTime?string('yyy-MM-dd')}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})">
                        </li>
                    </ul>
            </div>
            
            <div class="r-list" >
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
        <th align="left" width="20%">
           公司名称
        </th>
        <th align="left">
           姓名
        </th>
         <th align="left">
            入职时间
        </th>
        <th align="left"  >
            工龄
        </th>
        <th align="left" >
            休假天数
        </th>
        <th align="left" >
            优惠金额
        </th>
        <th align="left" >
            可使用优惠金额
        </th>
        <th align="left" >
            结余（挂账）
        </th>
        <th align="left" >
            是否结算
        </th>
        <th align="left" >
            结算时间
        </th>
        <th align="left" >
            备注
        </th>
        <th align="left" >
            操作
        </th>
    </tr>

    <#if staff_page??>
        <#list staff_page.content as staff>
            <tr>
                <td align="center">
                    <span class="checkall" style="vertical-align:middle;">
                        <input id="listChkId" type="checkbox" name="listChkId" value="${staff_index}" >
                    </span>
                    <input type="hidden" name="listId" id="listId" value="${staff.id?c}">
                </td>
                <td><a href="/Verwalter/staff/edit?id=${staff.id?c}">${staff.company!''}</a></td>
                <td>
                    <a href="/Verwalter/staff/edit?id=${staff.id?c}">${staff.username!""}</a></td>
                <td>
                	<#if staff.entryTime??>${staff.entryTime?string('yyyy-MM-dd')}</#if>
                </td>
                <td ><#if staff.workYear??>${staff.workYear!"0"}</#if></td>
                <td><#if staff.leaveDay??>${staff.leaveDay!""}</#if></td>
                <td >
                    <font color="#C30000">￥<#if staff.discount??>${staff.discount?string('0.00')}</#if></font>
                </td>
                <td >
                    <font color="#15AE52">￥<#if staff.surDiscount??>${staff.surDiscount?string('0.00')}</#if></font>
                </td>
                <td >
                    <font color="#C30000">￥<#if staff.surplus??>${staff.surplus?string('0.00')}</#if></font>
                </td>
                <td><#if staff.isClose?? && staff.isClose>已结算<#else>未结算</#if></td>
                <td>
                    <#if staff.accountTime??>${staff.accountTime?string('yyyy-MM-dd')}</#if>
                </td>
                <td style="max-width:130px"><#if staff.remark??>${staff.remark!''}</#if></td>
                <td >
                    <a href="/Verwalter/staff/edit?id=${staff.id?c}">修改</a>
                </td>
            </tr>
        </#list>
    </#if>
</tbody>
</table>
        
<!--/列表-->
<!--内容底部-->
<#assign PAGE_DATA=staff_page />
<#include "/site_mag/list_footer.ftl" />
<!--/内容底部-->
</form>


</body></html>
