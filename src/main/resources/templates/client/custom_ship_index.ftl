<!DOCTYPE html><html lang="en"><head>   <meta http-equiv="X-UA-Conpatible" content="IE=Edge,chrome=1">   <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">	<title><#if setting??>${setting.seoTitle!''}-</#if>私人定制-邮轮定制</title>	<meta name="keywords" content="<#if setting??>${setting.seoKeywords!''}</#if>">	<meta name="description" content="<#if setting??>${setting.seoDescription!''}</#if>">	<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>"></head><link rel="shortcut icon" href="/client/images/tianya.ico"><link rel="stylesheet" type="text/css" href="/client/css/base.css">    <link rel="stylesheet" type="text/css" href="/client/css/common.css">    <link rel="stylesheet" type="text/css" href="/client/css/rich_css.css">    <link rel="stylesheet" type="text/css" href="/client/css/f_pirvatecustom.css">    <!-- 美化下拉列表样式-->    <link rel="stylesheet" type="text/css" href="/client/css/selectordie.css">    <link rel="stylesheet" type="text/css" href="/client/css/selectordie_theme.css">    <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>    <script src="/client/js/jquery.page.js" type="text/javascript"></script>    <script src="/client/js/f_aboutjs.js" type="text/javascript"></script>    <script src="/client/js/rich_lee.js" type="text/javascript"></script>    <!-- 美化下拉列表控件-->    <script src="/client/js/selectordie.js" type="text/javascript"></script>    <script type=text/javascript src="/mag/js/WdatePicker.js"></script>    <script src="/client/js/Validform_v5.3.2_min.js"></script>        <script type="text/javascript">    	function way(param){    		if(param==0){    			$("#000").css('border-right','none');    			$("#111").css('border-right','block');    			$("#222").css('border-right','block');    			$("#333").css('border-right','block');    			$("#444").css('border-right','block');    			$("#11").css('display','none');    			$("#22").css('display','none');    			$("#33").css('display','none');    			$("#44").css('display','none');    			$("#trip").val('不限');	    		$("#yincang").css('display','none');    		}    		else if(param==1){    			$("#11").css('display','inline-block');    			$("#111").css('border-right','none');    			$("#000").css('border-right','block');    			$("#222").css('border-right','block');    			$("#333").css('border-right','block');    			$("#444").css('border-right','block');    			$("#22").css('display','none');    			$("#33").css('display','none');    			$("#44").css('display','none');    		}    		else if(param==2){    			$("#22").css('display','inline-block');    			$("#222").css('border-right','none');    			$("#111").css('border-right','block');    			$("#444").css('border-right','block');    			$("#333").css('border-right','block');    			$("#000").css('border-right','block');    			$("#11").css('display','none');    			$("#33").css('display','none');    			$("#44").css('display','none');    		}    		else if(param==3){    			$("#33").css('display','inline-block');    			$("#333").css('border-right','none');    			$("#111").css('border-right','block');    			$("#222").css('border-right','block');    			$("#444").css('border-right','block');    			$("#000").css('border-right','block');    			$("#22").css('display','none');    			$("#11").css('display','none');    			$("#44").css('display','none');    		}    		else if(param==4){    			$("#44").css('display','inline-block');    			$("#444").css('border-right','none');    			$("#111").css('border-right','block');    			$("#222").css('border-right','block');    			$("#333").css('border-right','block');    			$("#000").css('border-right','block');    			$("#22").css('display','none');    			$("#33").css('display','none');    			$("#11").css('display','none');    		}    	}    	    	$(document).ready(function(){    		//初始化表单验证			$("#form1").Validform({		        tiptype: 3		    });    	    	    		$("#trip").click(function(){				$("#yincang").css('display','inline-block');    		    		});    		    		$("#button1").click(function(){    		    			// 正整数    			var positive_num = /^[0-9]*[1-9][0-9]*$/;    			// 电话号码    			var pattern_phone = /^1[34578]\d{9}$/;    			// 电子邮箱    			var pattern_email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;    			// 金额（两位小数）    			var pattern_money = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;    			    			//#b	出行人数    			//#c	出发日期    			//#d	返程日期    			//#e	出发方式    			//#g	您的联系电话    			//#h	您的邮箱    			//#j	请输入你的最大支付限额    			//#i	特殊要求：如：单人房、双人房    			    			if($("#b").val() == "" || $("#b").val() == null || $("#b").val()=="出行人数"){    				$("#error").text("提示：出行人数！");    				return;    			}else if(!positive_num.test($("#b").val())){    				$("#error").text("提示：出行人数必须是正整数！");    				return;    			}else if($("#c").val() == "出发日期" || $("#c").val() == ""){    				$("#error").text("提示：请选择出发日期");    				return;    			}else if($("#d").val() == "返程日期" || $("#d").val() == ""){    				$("#error").text("提示：请选择返程日期");    				return;    			}else if($("#e").val() == "出发方式"){    				$("#error").text("提示：请选择出发方式");    				return;    			}else if($("#g").val() == "" || $("#g").val() == null || $("#g").val() == "您的联系电话"){    				$("#error").text("提示：请输入手机号码");    				return;    			}else if(!pattern_phone.test($("#g").val())){    				$("#error").text("提示：输入的手机号码有误");    				return;    			}/*else if($("#h").val() == "" || $("#h").val() == null || $("#h").val() == "您的邮箱"){    				$("#error").text("提示：请输入您的邮箱");    				return;    			}else if(!pattern_email.test($("#h").val())){    				$("#error").text("提示：邮箱格式错误");    				return;    			}*/    			else if($("#h").val() != "" && $("#h").val() != "您的邮箱"){    				if(!pattern_email.test($("#h").val())){    					$("#error").text("提示：邮箱格式错误");    					return;    				}    			}else if($("#j").val() == "" || $("#j").val() == null || $("#j").val() == "请输入你的最大支付限额"){    				$("#error").text("提示：请输入你的最大支付限额");    				return;    			}else if(!pattern_money.test($("#j").val())){    				$("#error").text("提示：金额输入错误，可保留两位小数");    				return;    			}    		    		    		    			$("#yincang2").css('display','none');    			$("#yincang3").css('display','inline-block');    			$("#aa").html($('#a').val());    			$("#bb").html($('#b').val());    			$("#cc").html($('#c').val());    			$("#dd").html($('#d').val());    			$("#ee").html($('#trip').val());    			$("#ff").html($('#f').val());    			$("#gg").html($('#g').val());    			$("#hh").html($('#h').val());    			    			if($("#i").val() == "特殊要求：如：单人房、双人房"){    				$("#ii").html($('#i').val(""));    			}else{    				$("#ii").html($('#i').val());    			}    			    			$("#jj").html($('#j').val());    		});    	});    	    	function click1(){    		$("#yincang2").css('display','inline-block');    		$("#yincang3").css('display','none');    	}    	    	function change(){    		var value1 = $("input[name='radio']:checked").val();    		$("#trip").val(value1);    		$("#yincang").css('display','none');    	}    	        		function ajaxSubmit(){			var reachPort = $("#aa").text();			var totalPeople = $("#bb").text();			var groupSaleStartTime = $("#cc").text();			var groupSaleStopTime = $("#dd").text();			var way = $("#ee").text();			var hotel = $("#ff").text();			var mobile = $("#gg").text();			var email = $("#hh").text();			var money = $("#jj").text();			var remark = $("#ii").text();			$.ajax({		        type:"post",		        url:"/private/make1",		        data:{"reachPort": reachPort,"totalPeople": totalPeople,"groupSaleStartTime": groupSaleStartTime,"groupSaleStopTime": groupSaleStopTime,"way": way,"hotel": hotel,"mobile": mobile,"email": email,"money": money,"remark": remark},		        success:function(data){		        	if(data.code==0){		        		alert("保存成功，请保持电话通畅！并为你匹配到下面信息！");		        		window.location.href="/search_list?keyword="+reachPort+"&keyword2="+data.money;       		 		}else{       		 			alert("请先登录！");       		 			window.location.href="/login";       		 		}   				}			});		};				    </script><body><#include "/client/common_header.ftl"/><!-- ********************导航-结束******************** --><div class="index_banner">	<div class="banner_scrool">		<#if big_scroll_ad_list5??>			<#list big_scroll_ad_list5 as item>				<a href="${item.linkUri!''}"><img src="${item.fileUri!''}" /> </a>			</#list>		</#if>	</div>	<div class="move_btn">		<ul>			<#if big_scroll_ad_list5??>				<#list big_scroll_ad_list5 as item>					<li><a></a></li>				</#list>			</#if>		</ul>	</div></div><!-- ********************定制旅游提交Start******************** --><div class="from_warpper">    <div class="f_privatecustom_fromcontain">        <a href="javascript:void(0);" class="f_colse" onclick="$(this).parent('div').hide();">╳</a>        <div class="from_top"></div>        <div class="from_content">        <form id="form1">            <div class="item" id="yincang2" style="display:inline">                <h1>您的定制需求</h1><span style="color: red;display:block; text-align:center;" id="error"></span>                <div class="incontent">                    <ul>                        <li>                            <div class="item">                                <i class="hotelstar"></i>                                <select id="a">                                	<#if goods_list??>                                	<#list goods_list as item>  										  <option value="${item.title}">${item.title}</option>  									</#list>									</#if> 								</select>                              </div>								<span class="white_space"></span>                            <div class="item">                                <i class="numbers"></i>                                <input type="text" class="shorttext" id="b" datatype="n1-3" onfocus="if($(this).val()=='出行人数'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('出行人数');}" value="出行人数" value="" errormsg="请填写纯数字！"/>                            </div>                        </li>                        <li>                            <div class="item">                                <i class="date"></i>                                <input type="text" class="shorttext" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" id="c" onfocus="if($(this).val()=='出发日期'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('出发日期');}" value="出发日期"/>                            </div>                            <span class="white_space"></span>                            <div class="item">                                <i class="date"></i>                                <input type="text" class="shorttext" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" id="d" onfocus="if($(this).val()=='返程日期'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('返程日期');}" value="返程日期"/>                            </div>                        </li>                        <li>                            <div class="item">                                <i class="byway"></i>                                <input type="text" class="shorttext" onfocus="if($(this).val()=='出发方式'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('出发方式');}" value="出发方式" id="trip"/>                                <div class="way_warp" id="yincang" >                                    <div class="l_choose">                                        <ul>                                            <li class="selected" id="000" ><a href="javascript:void(0);" onclick="way(0);" id="0" selectval="0">不限</a></li>                                            <li id="111"><a href="javascript:void(0);" onclick="way(1);" id="1" selectval="1">邮轮</a></li>                                            <li id="222"><a href="javascript:void(0);" onclick="way(2);" id="2" selectval="2">飞机</a></li>                                            <li id="333"><a href="javascript:void(0);" onclick="way(3);" id="3" selectval="3">火车</a></li>                                            <li id="444"><a href="javascript:void(0);" onclick="way(4);" id="4" selectval="4">汽车</a></li>                                        </ul>                                    </div>                                    <div class="r_choose">                                        <div class="c_item" id="11">                                            <label><input type="radio" onclick="change();" name="radio" value="邮轮-经济舱" />经济舱</label>                                            <label><input type="radio" onclick="change();" name="radio" value="邮轮-商务舱" />商务舱</label>                                            <label><input type="radio" onclick="change();" name="radio" value="邮轮-头等舱" />头等舱</label>                                        </div>                                        <div class="c_item" id="22">                                            <label><input type="radio" onclick="change();" name="radio" value="飞机-经济舱" />经济舱</label>                                            <label><input type="radio" onclick="change();" name="radio" value="飞机-商务舱" />商务舱</label>                                            <label><input type="radio" onclick="change();" name="radio" value="飞机-头等舱" />头等舱</label>                                        </div>                                        <div class="c_item" id="33">                                            <label><input type="radio" onclick="change();" name="radio" value="火车-软卧" />软卧</label>                                            <label><input type="radio" onclick="change();" name="radio" value="火车-硬卧" />硬卧</label>                                            <label><input type="radio" onclick="change();" name="radio" value="火车-软座" />软座</label>                                            <label><input type="radio" onclick="change();" name="radio" value="火车-硬座" />硬座</label>                                        </div>                                        <div class="c_item" id="44">                                            <label><input type="radio" onclick="change();" name="radio" value="汽车-卧铺" />卧铺</label>                                            <label><input type="radio" onclick="change();" name="radio" value="汽车-座位" />座位</label>                                        </div>                                    </div>                                </div>                            </div>                            <span class="white_space"></span>                            <div class="item">                                <i class="hotelstar"></i>                                <select id="f">  								  <option selected value="内舱房">内舱房</option>  								  <option value="阳台房">阳台房</option>  								  								</select>                              </div>                        </li>                        <li>                            <div class="item">                                <i class="phone"></i>                                <input type="text" class="shorttext" id="g" datatype="m" errormsg="请填写手机号！" onfocus="if($(this).val()=='您的联系电话'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('您的联系电话');}" value="您的联系电话" />                            </div>                            <span class="white_space"></span>                            <div class="item">                                <i class="email"></i>                                <input type="text" class="shorttext" id="h" datatype="e" errormsg="邮箱格式不对！" onfocus="if($(this).val()=='您的邮箱'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('您的邮箱');}" value="您的邮箱" />                            </div>                        </li>                        <li>                        	<div class="item">                                <i class="money"></i>                                <input type="text" class="shorttext" id="j" datatype="n" onfocus="if($(this).val()=='请输入你的最大支付限额'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('请输入你的最大支付限额');}" value="请输入你的最大支付限额" />                            </div>                            <span class="white_space"></span>                            <div class="item">                                <i class="pen"></i>                                <input type="text" class="shorttext" id="i" onfocus="if($(this).val()=='特殊要求：如：单人房、双人房'){$(this).val('');}" onblur="if($(this).val()==''){$(this).val('特殊要求：如：单人房、双人房');}" value="特殊要求：如：单人房、双人房"/>                            </div>                        </li>                    </ul>                    <span class="warp_submit">                    <input type="button" class="btn_submit" id="button1" value="提交定制">                    </span>                </div>            </div>            </form>            <div class="item" id="yincang3" style="display: none;">                <h1>您定制的信息为：</h1>                <div class="private_info">                    <ul>                        <li>                            <div class="item">                                <label>邮轮：</label> <span id="aa"></span>                            </div>                            <div class="item">                                <label>出行人数：</label> <span id="bb"></span>                            </div>                        </li>                        <li>                            <div class="item">                                <label>出发日期：</label> <span id="cc"></span>                            </div>                            <div class="item">                                <label>返程日期：</label> <span id="dd"></span>                            </div>                        </li>                        <li>                            <div class="item">                                <label>出发方式：</label> <span id="ee"></span>                            </div>                            <div class="item">                                <label>房型：</label> <span id="ff"></span>                            </div>                        </li>                        <li>                            <div class="item">                                <label>您的电话：</label> <span id="gg"></span>                            </div>                            <div class="item">                                <label>您的邮箱：</label> <span id="hh"></span>                            </div>                        </li>                        <li>                            <div class="item">                                <label>预支出金额：</label> <span id="jj"></span>                            </div>                        </li>                        <li>                            <div class="item">                                <label>备注：</label> <span id="ii"></span>                            </div>                        </li>                    </ul>                    <span class="warp_submit">                        <input type="button" onclick="ajaxSubmit();" class="btn_submit" value="确认并提交">                        <a href="javascript:void(0);" onclick="click1();" class="btn_back" >返回重填</a>                    </span>                </div>            </div>            <div class="operation_success" id="success1" style="display: none;">                <span class="success_icon"></span>                <span>提交成功</span>                <label>提交成功后请保持联系方式畅通,并为你匹配到如下信息。</label>                <a href="javascript:void(0);" class="a_close" onclick="click22();">关闭</a>            </div>        </div>    </div></div><script>	window.onload = function () {            banner_go('my');//焦点图            act_nav();            //加载分页            $(".page").createPage({                pageCount: 6,                current: 1,                backFn: function (p) {                    console.log(p);                }            });            //            初始化排序下拉列表            //$('#a').selectOrDie();            //$('#f').selectOrDie();        };	</script><!-- ********************定制旅游提交End******************** --><!-- ********************中间内容Start******************** --><div class="wrapper">    </div><!-- ********************中间内容End******************** --><!-- ********************底部******************** --><#include "/client/common_footer.ftl" /><!-- ********************底部-结束******************** --></body></html>