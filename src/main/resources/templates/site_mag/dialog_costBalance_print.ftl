<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head>
<title></title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js?skin=idialog"></script>
<script type="text/javascript">
 //   //窗口API
 //   var api = frameElement.api, W = api.opener;
 //   api.button({
 //       name: '确认打印',
 //       focus: true,
 //       callback: function () {
 //           printWin();
 //       }
 //   }, {
 //       name: '取消'
 //   });
 //   //打印方法
 //   function printWin() {
 //       var oWin = window.open("", "_blank");
 //       oWin.document.write(document.getElementById("content").innerHTML);
 //       oWin.focus();
 //       oWin.document.close();
 //       oWin.print()
 //       oWin.close()
 //   }
    
    function doPrint() {    
        bdhtml=window.document.body.innerHTML;    
        sprnstr="<!--startprint-->";    
        eprnstr="<!--endprint-->";    
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);    
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
        window.document.body.innerHTML=prnhtml; 
        window.print();    
}  
</script>
</head>
<body style="margin: 0;">
<!--startprint-->
    <form name="form1" method="post" action="dialog_print.aspx?order_no=B15041121411832" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="">
</div>

<div>

    <input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="86EF5C69">
</div>
    <div id="content">
    <script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
    <script>
    	$(document).ready(
			function(){
				replacemoneystr();	
			}
		);
		// 去掉数字的逗号
		function replacemoneystr(){
			var strs = $(".moneytxt").each(function(){
				var str = $(this).val().replace(/,/g, "");
				$(this).val(str);
			});
		}
	</script>
    <style>
	*{padding:0;margin:0;font-size:14px;color:#333;}
	ul{padding:0;margin:0;}
	li{list-style:none;}
	input{outline:none;}
	.fl{float:left;}
	.fr{float:right;}
	.tbox{
		overflow:hidden;
		margin:0 30px;
		padding:0;
		width:800px;
		text-align:center;
	}
	.tbox .ptitle1{
		height:24px;
		line-height:24px;
		font-size:12px;
		color:#000;
	}
	.tbox .ptitle2{
		height:24px;
		line-height:24px;
		font-size:20px;
		font-weight:bold;
		color:#000;
	}
	.sec_time{
		overflow:hidden;
		margin-top:12px;
	}
	.sec_time .as1{
		float:left;
		overflow:hidden;
		margin-left:260px;
	} 
	.sec_time .as1 p{
		overflow:hidden;
		width:64px;
	}
	.sec_time .as1 p input{
		float:left;
		width:40px;
		height:20px;
		line-height:20px;
		color:#333;
		text-align:center;
		border:none;
	}
	.sec_time .as1 p span{
		float:left;
	}
	.sec_time .as2{
		float:right;
	}
	.sec_time .as2 input{
		width:100px;
		border:none;
	}
	.ulbox{
		overflow:hidden;
		border:1px solid #000;
		border-top:none;
	}
	.ulbox li{
		float:left;
		overflow:hidden;
		border-top:1px solid #000;
	}
	.ulbox .li1{
		height:60px;
		/*line-height:60px;*/
	}
	.ulbox .li1 div{
	}
	.ulbox .li1 .div1,
	.ulbox .li1 .div3,
	.ulbox .li1 .div5{
		padding:14px 6px;
	}
	.ulbox .li1 .div2,
	.ulbox .li1 .div4,
	.ulbox .li1 .div6{
		width:120px;
	}
	.ulbox .li1 .div7{
		height:60px;
		line-height:30px;
	}
	.ulbox .li1 .div7 section{
		height:30px;
		line-height:30px;
	}
	.ulbox .li1 .div7 .sec1{
		overflow:hidden;
		height:29px;
		border-bottom:1px solid #000;
	}
	.ulbox .li1 .div7 aside{
		float:left;
		width:177px;
		height:30px;
		line-height:30px;
	}
	.ulbox .li1 .div7 .as1{
		width:120px;
		border-right:1px solid #000;
	}
	.ulbox .li1 .div7 input{
		height:30px;
		line-height:30px;
	}
	.ulbox .li1 input{
		height:60px;
		line-height:60px;
	}
	.ulbox li div{
		float:left;
		font-size:14px;
		border-left:1px solid #000;
	}
	.ulbox li div.div1{
		border-left:none;
	}
	.ulbox li div input{
		display:inline-block;
		float:left;
		width:100%;
		border:none;
		text-align:center;
	}
	.ulbox .li2{
		overflow:hidden;
	}
	.ulbox .li2 .div1{
		width:98px;
		height:30px;
		line-height:30px;
	}
	.ulbox .li2 .div2{
		width:400px;
		height:30px;
		line-height:30px;
	}
	.ulbox .li2 .div3{
		width:298px;
		height:30px;
		line-height:30px;
	}
	.ulbox .li2 input{
		float:left;
		margin-top:7px;
		width:40px;
	}
	.ulbox .li2 .ipt1{
		margin-left:50px;
	}
	.ulbox .li2 span{
		float:left;
	}
	.ulbox .li2 .sp1{
		margin-left:84px;
	}
	.ulbox .li3{
		overflow:hidden;
	}
	.ulbox .li3 div{
		height:30px;
		line-height:30px;
	}
	.ulbox .li3 .div1{
		width:98px;
	}
	.ulbox .li3 .div2{
		padding:0 40px;
	}
	.ulbox .li3 .div3{
		width:147px;
	}
	.ulbox .li3 .div4{
		width:160px;
	}
	.ulbox .li3 input{
		float:left;
		height:100%;
	}
	.ulbox .li3 .div2 input{
		width:60px;
	}
	.ulbox .li3 span{
		float:left;
	}
	.ulbox .li3 .sp1{
		margin-left:50px;
	}
	.ulbox .li4{
		overflow:hidden;
	}
	.ulbox .li4 div{
		height:30px;
		line-height:30px;
	}
	.ulbox .li4 .div1{
		width:60px;
	}
	.ulbox .li4 .div2{
		width:253px;
	}
	.ulbox .li4 .div3{
		width:120px;
	}
	.ulbox .li4 .div4{
		width:120px;
	}
	.ulbox .li4 .div5{
		width:120px;
	}
	.ulbox .li4 .div6{
		width:120px;
	}
	.ulbox .li4 .sp1{
		margin-left:54px;
	}
	.ulbox .li4 span{
		float:left;
	}
	.ulbox .li4 input{
		float:left;
		margin-top:7px;
		width:40px;
	}
	.ulbox .li5{
		overflow:hidden;
	}
	.ulbox .li5 .dleft{
		padding:20px 6px;
		width:20px;
		line-height:26px;
	}
	.ulbox .li5 .dright{
		width:765px;
	}
	.ulbox .li5 dl{
		overflow:hidden;
		border-top:1px solid #000;
	}
	.ulbox .li5 .dl1{
		border:none;
	}
	.ulbox .li5 .dd1{
		float:left;
		width:130px;
		height:30px;
		line-height:30px;
		border-left:1px solid #000;
	}
	.ulbox .li5 dt.dd1{
		border-left:none;
	}
	.ulbox .li5 .dd2{
		float:left;
		width:123px;
		height:30px;
		line-height:30px;
		border-left:1px solid #000;
	}
	.ulbox .li5 input{
		height:100%;
	}
	.ulbox .li6{
		overflow:hidden;
	}
	.ulbox .li6 div{
		height:30px;
		line-height:30px;
	}
	.ulbox .li6 .div1{
		width:120px;
	}
	.ulbox .li6 .div3{
		width:120px;
	}
	.ulbox .li6 .div4{
		width:113px;
	}
	.ulbox .li6 input{
		float:left;
		width:100%;
		height:100%;
	}
	.ulbox .li6 .ipt1{
		width:160px;
	}
	.ulbox .li6 .ipt2{
		width:240px;
	}
	.ulbox .li6 span{
		float:left;
	}
	.foot{
		overflow:hidden;
	}
	.foot dt{
		float:left;
		width:140px;
	}
	.foot dd{
		float:left;
		margin-left:20px;
		width:140px;
	}
	.foot img{
		max-width:100%;
		height:45px;
		width:120px;
	}
	.foot p{
		text-align:center;
		margin-top:10px;
	}
	.foot .pimg{
		padding-bottom:10px;
		border-bottom:1px solid #000;
	}
</style>
        <table width="700" border="0" align="center" cellpadding="3" cellspacing="0" style="float:left;font-size: 12px; font-family: '微软雅黑'; background: #fff;">
            <tbody><tr>
                <td width="220">操&nbsp;作&nbsp;人：${manager!''}<br>
                    打印时间：${now?string("yyyy-MM-dd HH:mm:ss")}</td>
            </tr>
            <tr>
                <#--结算表-->
                <div class="tbox" style="text-align:center;">
		<p class="ptitle1">重庆天涯旅行社有限公司</p>
		<p class="ptitle2">旅游组（接）团费用结算表</p>
		<section class="sec_time">
			<aside class="as1">
				<p class="p1 fl">
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="fillYear" value="<#if costBalance??>${costBalance.fillYear!''}</#if>"/>
					<span>年</span>
				</p>
				<p class="p2 fl">
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="fillMonth" value="<#if costBalance??>${costBalance.fillMonth!''}</#if>"/>
					<span>月</span>
				</p>
				<p class="p3 fl">
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="fillDay" value="<#if costBalance??>${costBalance.fillDay!''}</#if>"/>
					<span>日</span>
				</p>
			</aside>
			<aside class="as2">
				<input type="text" class="fr" name="theNum" value="<#if costBalance??>${costBalance.theNum!''}</#if>"/>
				<span class="fr">编号：</span>
			</aside>
		</section>
		<ul class="ulbox">
			<li class="li1">
				<div class="div1">团队<br />名称</div>
				<div class="div2"><input type="text" name="teamName" value="<#if costBalance??>${costBalance.teamName!''}</#if>"/></div>
				<div class="div3">合同<br />编号</div>
				<div class="div4"><input type="text" name="contactNum" value="<#if costBalance??>${costBalance.contactNum!''}</#if>"/></div>
				<div class="div5">地接<br />旅行社</div>
				<div class="div6"><input type="text" name="travelAgence" value="<#if costBalance??>${costBalance.travelAgence!''}</#if>"/></div>
				<div class="div7">
					<section class="sec1">
						<aside class="as1">陪同</aside>
						<aside class="as2"><input type="text" name="accompany" value="<#if costBalance??>${costBalance.accompany!''}</#if>"/></aside>
					</section>
					<section class="sec2">
						<aside class="as1">证件号码</aside>
						<aside class="as2"><input type="text" name="idNum" value="<#if costBalance??>${costBalance.idNum!''}</#if>"/></aside>
					</section>
				</div>
			</li>
			<li class="li2">
				<div class="div1">出发时间</div>
				<div class="div2">
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" class="ipt1" name="goYear" value="<#if costBalance??>${costBalance.goYear!''}</#if>"/>
					<span>年</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="goMonth" value="<#if costBalance??>${costBalance.goMonth!''}</#if>"/>
					<span>月</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="goDay" value="<#if costBalance??>${costBalance.goDay!''}</#if>"/>
					<span>日至</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="backMonth" value="<#if costBalance??>${costBalance.backMonth!''}</#if>"/>
					<span>月</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="backDay" value="<#if costBalance??>${costBalance.backDay!''}</#if>"/>
					<span>日</span>
				</div>
				<div class="div3">
					<span class="sp1">共</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="totalDay" value="<#if costBalance??>${costBalance.totalDay!''}</#if>"/>
					<span>天</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="totalNight" value="<#if costBalance??>${costBalance.totalNight!''}</#if>"/>
					<span>夜</span>
				</div>
			</li>
			<li class="li3">
				<div class="div1">实际人数</div>
				<div class="div2">
					<span class="sp1">成人：</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="adultNum" value="<#if costBalance??>${costBalance.adultNum}</#if>" class="moneytxt"/>
					<span>人，</span>
					&nbsp;
					<span>儿童：</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="childNum" value="<#if costBalance??>${costBalance.childNum!''}</#if>"/>
					<span>人</span>
				</div>
				<div class="div3">实际收费人数</div>
				<div class="div4"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="personNum" value="<#if costBalance??>${costBalance.personNum!''}</#if>"/></div>
			</li>
			<li class="li4">
				<div class="div1">报价</div>
				<div class="div2">
					<span class="sp1">成人：</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="adultPrice" value="<#if costBalance??>${costBalance.adultPrice!''}</#if>"/>
					<span class="spm">儿童：</span>
					<input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="childPrice" value="<#if costBalance??>${costBalance.childPrice!''}</#if>"/>
				</div>
				<div class="div3">地接确认时间</div>
				<div class="div4"><input type="text" style="width: 70px;" name="confirmTime" value="<#if costBalance??>${costBalance.confirmTime!''}</#if>" onfocus="WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;})"/></div>
				<div class="div5">营业收入总额</div>
				<div class="div6"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="totalIncome" value="<#if costBalance??>${costBalance.totalIncome!''}</#if>"/ class="moneytxt"></div>
			</li>
			<li class="li5">
				<div class="div1 dleft">成本费用</div>
				<div class="dright">
					<dl class="dl1">
						<dt class="dd1">一、交通费</dt>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="travelCost" value="<#if costBalance??>${costBalance.travelCost!''}</#if>"/></dd>
						<dd class="dd1">二、房费</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="houseCost" value="<#if costBalance??>${costBalance.houseCost!''}</#if>"/></dd>
						<dd class="dd1">七、旅游签证费</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="visiaCost" value="<#if costBalance??>${costBalance.visiaCost!''}</#if>"/></dd>
					</dl>
					<dl>
						<dt class="dd1">1、汽车</dt>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="carCost" value="<#if costBalance??>${costBalance.carCost!''}</#if>"/></dd>
						<dd class="dd1">三、餐费</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="eatCost" value="<#if costBalance??>${costBalance.eatCost!''}</#if>"/></dd>
						<dd class="dd1">八、陪同非及补助</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="accompanyAndAllowance" value="<#if costBalance??>${costBalance.accompanyAndAllowance!''}</#if>"/></dd>
					</dl>
					<dl>
						<dt class="dd1">2、火车</dt>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="trainCost" value="<#if costBalance??>${costBalance.trainCost!''}</#if>"/></dd>
						<dd class="dd1">四、门票</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="ticketCost" value="<#if costBalance??>${costBalance.ticketCost!''}</#if>"/></dd>
						<dd class="dd1">九、其他</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="otherCost" value="<#if costBalance??>${costBalance.otherCost!''}</#if>"/></dd>
					</dl>
					<dl>
						<dt class="dd1">3、轮船</dt>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="shipCost" value="<#if costBalance??>${costBalance.shipCost!''}</#if>"/></dd>
						<dd class="dd1">五、保险</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="insuranceCost" value="<#if costBalance??>${costBalance.insuranceCost!''}</#if>"/></dd>
						<dd class="dd1">十、地接综合费费</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="comprehensiveCost" value="<#if costBalance??>${costBalance.comprehensiveCost!''}</#if>"/></dd>
					</dl>
					<dl>
						<dt class="dd1">4、飞机</dt>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="airCost" value="<#if costBalance??>${costBalance.airCost!''}</#if>"/></dd>
						<dd class="dd1">六、代理费</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="agentCost" value="<#if costBalance??>${costBalance.agentCost!''}</#if>"/></dd>
						<dd class="dd1">合计</dd>
						<dd class="dd2"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="totalCost" value="<#if costBalance??>${costBalance.totalCost!''}</#if>"/></dd>
					</dl>
				</div>
			</li>
			<li class="li6">
				<div class="div1">营业收入净额</div>
				<div class="div2">
					<input type="text" onkeydown="return checkNumber(event);" class="ipt1 moneytxt" name="pureIncome" value="<#if costBalance??>${costBalance.pureIncome!''}</#if>"/>
					<span>大写：</span>
					<input type="text" class="ipt2" name="pureIncomeStr" value="<#if costBalance??>${costBalance.pureIncomeStr!''}</#if>"/>
				</div>
				<div class="div3">原始单据（张）</div>
				<div class="div4"><input type="text" onkeydown="return checkNumber(event);" class="moneytxt" name="receiptNum" value="<#if costBalance??>${costBalance.receiptNum!''}</#if>"/></div>
			</li>
		</ul>
		<dl class="foot">
                <dd>
                    <p>
                        负责人:${costBalance.responsiblePerson!""}
                    </p>
                    <p class="pimg">
                        <#if checkFlag?? && checkFlag[4] gt 0>
                            <img src="${fzr.signatureImgUri!''}">
                        <#else>
                            <label style="color: red;">(未签)</label>
                        </#if>
                    </p>
                </dd>
                
                <dd>
                    <p>
                        财务主管:${costBalance.financeSupervisor!""}
                    </p>
                    <p>
                        <#if checkFlag?? && checkFlag[3] gt 0>
                            <img src="${cwzg.signatureImgUri!''}">
                        <#else>
                            <label style="color: red;">(未签)</label>
                        </#if>
                    </p>
                </dd>
                 <dd>
                    <p>
                        出纳:${costBalance.cashier!""}
                    </p>
                    <p>
                        <#if checkFlag?? && checkFlag[2] gt 0>
                            <img src="${cn.signatureImgUri!''}">
                        <#else>
                            <label style="color: red;">(未签)</label>
                        </#if>
                    </p>
                </dd>
                <dd>
                    <p>
                        分管领导:${costBalance.fgLeader!""}
                    </p>
                    <p>
                        <#if checkFlag?? && checkFlag[1] gt 0>
                            <img src="${fgld.signatureImgUri!''}">
                        <#else>
                            <label style="color: red;">(未签)</label>
                        </#if>
                    </p>
                </dd>
                <dd>
                    <p>
                        制表人:${costBalance.lister!""}
                    </p>
                    <p>
                        <#if checkFlag?? && checkFlag[0] gt 0>
                            <img src="${zbr.signatureImgUri!''}">
                        <#else>
                            <label style="color: red;">(未签)</label>
                        </#if>
                    </p>
                </dd>
			</dl>
	</div>
                <#--结算表结束-->
                
            </tr>
        </tbody></table>
        
    </div>
    </form><!--endprint-->
    <input type="button" style="float:right; background:#993333; color:#fff; margin-top:10px; width:100px; height:30px; text-align:center;border:1px solid #000; border-radius:3px;" name=button_print value="打印" onclick="doPrint()"> 

</body></html>