$(function () {
	$("#adultNumber").change(function(){
		countPrice();
    });
	
	$("#childNumber").change(function(){
		countPrice();
    });
	
	$("#aduitCost").change(function(){
		countPrice();
    });
	
	$("#childCost").change(function(){
		countPrice();
    });
	
	$("#previousBalance").change(function(){
		countPrice();
    });
	
	$("#discountAmount").change(function(){
		countPrice();
    });
	
	$("#cashMoney").change(function(){
		changePrice();
    });
	
});

function countPrice(){
	
	var aduNum = $.trim($("#adultNumber").val()); // 成人书
	var chdNum = $.trim($("#childNumber").val()); // 儿童数
	var aduCost = $.trim($("#aduitCost").val()); // 成人价
	var chdCost = $.trim($("#childCost").val()); // 儿童价
	
	var ractPrice = $.trim($("#contractPrice").val()); // 合同总额
	var preBalance = $.trim($("#previousBalance").val()); // 前期余额
	var disAmount = $.trim($("#discountAmount").val()); // 优惠金额
	var curBalance = $.trim($("#currentBalance").val()); // 本次余额
	var cashMoney = $.trim($("#cashMoney").val()); // 预付款
	var bankMoney = $.trim($("#bankMoney").val()); // 尾款
	
	if (isNaN(aduNum) || aduNum=="") { aduNum = 0 }
	if (isNaN(chdNum) || chdNum=="") { chdNum = 0 }
	if (isNaN(aduCost) || aduCost=="") { aduCost = 0 }
	if (isNaN(chdCost) || chdCost=="") { chdCost = 0 }
	
	if (isNaN(ractPrice) || chdCost=="") { ractPrice = 0 }
	if (isNaN(preBalance) || preBalance=="") { preBalance = 0 }
	if (isNaN(disAmount) || disAmount=="") { disAmount = 0 }
	if (isNaN(curBalance) || curBalance=="") { curBalance = 0 }
	if (isNaN(cashMoney) || cashMoney=="") { cashMoney = 0 }
	if (isNaN(bankMoney) || bankMoney=="") { bankMoney = 0 }
	
	
	var radionum = document.getElementsByName("type");
    var type =0;
    for(var i=0;i<radionum.length;i++){
        if(radionum[i].checked){
             type = radionum[i].value
         }
    }
    
    // 合同总价=成人书*成人价+儿童数*儿童价
    ractPrice = FloatAdd(FloatMul(aduNum,aduCost),FloatMul(chdNum,chdCost));
    
    // 如果是散客，前期余额、优惠金额、本次月为0
    if(type==1)
    {
    	preBalance = 0;
    	disAmount = 0;
    	curBalance = 0;
    }
    // 职工优惠总额= 优惠金额+前期余额
    p1 = FloatAdd(preBalance,disAmount);
    console.debug(p1)
    // 职工记录，判断优惠总额与合同价格，计算余额 
    if(type=0){
    	if(p1 < ractPrice){
    		$("#currentBalance").val(FloatSub(ractPrice,p1));
    	}
    }
    
	if(p1 > ractPrice){
		$("#currentBalance").val(FloatSub(p1,ractPrice));
		$("#bankMoney").val(0);
	}else{
		$("#bankMoney").val(FloatSub(ractPrice,p1));
		$("#currentBalance").val(0);
	}
    $("#contractPrice").val(ractPrice); // 合同总价
    $("#cashMoney").val(p1); // 预付款
}

function changePrice(){
	var ractPrice = $.trim($("#contractPrice").val()); // 合同总额
	var cashMoney = $.trim($("#cashMoney").val()); // 预付款
	
	if (isNaN(ractPrice) || ractPrice=="") { ractPrice = 0 }
	if (isNaN(bankMoney) || bankMoney=="") { bankMoney = 0 }
	
	$("#bankMoney").val(parseFloat(ractPrice-cashMoney))
}

//浮点数加法运算
function FloatAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    return (arg1*m+arg2*m)/m;
}

//浮点数减法运算
function FloatSub(arg1,arg2){
    var r1,r2,m,n;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    //动态控制精度长度
    n=(r1=r2)?r1:r2;
    return ((arg1*m-arg2*m)/m).toFixed(n);
}

//浮点数乘法运算
function FloatMul(arg1,arg2)
{
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}


 //浮点数除法运算
function FloatDiv(arg1,arg2){
    var t1=0,t2=0,r1,r2;
    try{t1=arg1.toString().split(".")[1].length}catch(e){}
    try{t2=arg2.toString().split(".")[1].length}catch(e){}
    with(Math){
        r1=Number(arg1.toString().replace(".",""));
        r2=Number(arg2.toString().replace(".",""));
        return (r1/r2)*pow(10,t2-t1);
    }
}