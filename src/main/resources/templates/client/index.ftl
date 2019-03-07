<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><#if setting??>${setting.seoTitle!''}-</#if>首页</title>
	<meta name="keywords" content="<#if setting??>${setting.seoKeywords!''}</#if>">
	<meta name="description" content="<#if setting??>${setting.seoDescription!''}</#if>">
	<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
	<meta property="qc:admins" content="465702165764127526516375" />
</head>
<link rel="shortcut icon" href="/client/images/tianya.ico">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
<link rel="stylesheet" type="text/css" href="/client/css/common.css">
<link rel="stylesheet" type="text/css" href="/client/css/index.css">
<link rel="stylesheet" type="text/css" href="/client/css/rich_css.css">
<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>	
<script src="/client/js/rich_lee.js" type="text/javascript"></script>	
<script src="/client/js/layout.js" type="text/javascript"></script>
<style type="text/css">
	.mymember_page{
		float:right;
		overflow: hidden;
	    margin-right:50px;
	    font-size: 16px;
	    color: #033a7a;
	}
	.mymember_page:after{
		content:"clear";
		clear:both;
		height:0px;
		display:block;
		overflow:hidden;
	}
	.mymember_page dd{
		float:left;
	    width: 30px;
	    height: 30px;
	    line-height: 30px;
	    margin-left:10px;
	    text-align: center;
	    border: 1px solid #033a7a;
	}

</style>


<body>
<#include "/client/common_header.ftl" />

<div class="index_banner">
	<div class="banner_scrool">
		<#if big_scroll_ad_list??>
			<#list big_scroll_ad_list as item>
				<a href="${item.linkUri!''}" target="_blank"><img src="${item.fileUri!''}" /> </a>
			</#list>
		</#if>
	</div>
	<div class="move_btn">
		<ul>
			<#if big_scroll_ad_list??>
				<#list big_scroll_ad_list as item>
					<li><a></a></li>
				</#list>
			</#if>
		</ul>
	</div>
</div>

<!-- ********************中间内容******************** -->
<style>
    .bookingwrap {
        width: 100%;
        display: inline-block;
        min-width: 1440px;
        background-color: #000;
        height: 48px;
    }
    .bookingwrap .inbookwrap{
        width: 1200px;
        text-align: center;
        line-height: 48px;
    }
    .bookingwrap .inputlong{
        width: 150px;
        height: 20px;
        line-height: 20px;
    }
    .bookingwrap .inputshort{
        width: 67px;
        height: 20px;
        line-height: 20px;
    }
    .bookingwrap .inputbutton{
        padding: 0 5px;
        height: 24px;
        line-height: 20px;
        background: #1e3c7f;
        color: #fff;
        border: 0;
    }
    .bookingwrap .inbookwrap label {
        margin: 0 3px;
    }
</style>
<script type="text/javascript">

</script>
<div class="bookingwrap">
    <div class="inbookwrap">
        <label for="inputmonth">我想在</label>
        <select name="month" id="inputmonth"  class="inputshort">
            <option value="0">月份</option>
            <option value="1">一月</option>
            <option value="2">二月</option>
            <option value="3">三月</option>
            <option value="4">四月</option>
            <option value="5">五月</option>
            <option value="6">六月</option>
            <option value="7">七月</option>
            <option value="8">八月</option>
            <option value="9">九月</option>
            <option value="10">十月</option>
            <option value="11">十一月</option>
            <option value="12">十二月</option>
        </select>
        <label for="inputwhither">去</label>
        <input type="text" id="inputwhither" class="inputlong" value="" name="place"/>
        <label for="inputday">预计玩</label>
        <input type="number" id="inputday" class="inputshort" min="1" value="" name="totalDays" onkeydown="return checkNumber(event);"/>天
        <label for="inputpeonum">总共</label>
        <input type="text" id="inputpeonum" class="inputshort" value="" name="totalPersons" onkeydown="return checkNumber(event);" onfocus="if(value=='1'||value=='0') {value=''}" onblur="if (value==''||value=='0') {value='1'}" onkeyup="value=value.replace(/[^0-9]/g,'')"/>人
        <label for="inputphone">电话</label>
        <input type="text" id="inputphone" class="inputlong" value="" name="telephone" onkeydown="return checkNumber(event);"/>
        <input type="button" style="cursor:pointer" onclick="goCustom()" value="开始定制" class="inputbutton"/>
    </div>
</div>

<div class="content">

<div class="main_act">
        <div class="act_title">
            <span></span>
            <a>限时特惠</a>
            <span></span>
        </div>
        <div class="act_box">
            <style>
                .onsale {
                    float: left;
                    width: 100%;
                    height: 570px;
                    overflow: hidden;
                    position: relative;
                }

                .onsale .saleimg {
                    float: left;
                    width: 100%;
                    position: relative;
                    height: 570px;
                }

                .onsale .saleimg a {
                    position: absolute;
                    top: 0;
                    left: 0;
                    z-index: 0;
                }

                .onsale .saleimg a.current {
                    z-index: 1;
                }

                .onsale .saletitle {
                    position: absolute;
                    bottom: 5px;
                    left: 0;
                    z-index: 20;
                    float: left;
                    width: 100%;
                }

                .onsale .saletitle > div {
                    position: relative;
                    float: left;
                    width: 347px;
                    height: 40px;
                    margin-left: 10px;
                }

                .onsale .saletitle a {
                    position: absolute;
                    left: 0;
                    top: 0;
                    float: left;
                    width: 347px;
                    height: 40px;
                    background: url("/client/images/saletitlebg1.png") repeat;
                    color: #fff;
                    text-align: center;
                    text-decoration: none;
                    line-height: 40px;
                }
            </style>

            <script>
                $(function () {
                    autoslideimg();
                });

                function autoslideimg() {
                    var imgwrap = $('.onsale .saleimg');
                    var imgnav = $('.saletitle > div a');
                    var imglength = $('.onsale .saleimg a').length;
                    var num = 0;

                    function tir() {
                        if (num >= imglength) {
                            
                            num = 0;
                        }
                        
                        var box = $('.onsale .saleimg a:eq(' + num + ')');
                        box.fadeIn();
                        box.css({'z-index': '2'});
                        
                        box.siblings().css({'z-index': '0'});
                        box.siblings().fadeOut(1000);
                        
                        var parent = $('.saletitle > div:eq('+num+')');
                        var thisnav = $('.saletitle > div:eq('+num+')').find('a');
                        
                        thisnav.css('background', 'url("/client/images/saletitlebg.png") repeat');
                        thisnav.stop(true).animate({'height': '50px', 'line-height': '50px', 'top': '-10px'}, 300);
                        
                        
                        parent.siblings().find('a').css('background', 'url("/client/images/saletitlebg1.png") repeat');
                        parent.siblings().find('a').stop(true).animate({'height': '40px', 'line-height': '40px', 'top': '0'}, 300);

                        num += 1;
                    }
                    var timerrr = setInterval(tir, 5000);
                    imgnav.hover(function () {
                        clearInterval(timerrr);
                        $(this).css('background', 'url("/client/images/saletitlebg.png") repeat');
                        $(this).stop(true).animate({'height': '50px', 'line-height': '50px', 'top': '-10px'}, 300);
                        var indexnum = $(this).parent().index();
                        imgwrap.find('a:eq(' + indexnum + ')').fadeIn(500);
                        imgwrap.find('a:eq(' + indexnum + ')').siblings().fadeOut(300);

                    }, function () {
                        timerrr = setInterval(tir, 3000);
                        $(this).css('background', 'url("/client/images/saletitlebg1.png") repeat');
                        $(this).stop(true).animate({'height': '40px', 'line-height': '40px', 'top': '0'}, 300);
                    });

                }

            </script>
            <div class="onsale">
                <div class="saleimg">
                	<#if limit_time_product_list??>
                	<#list limit_time_product_list as item>
                		<#if item_index gt 3>
							<#break>
						</#if>
	                    <a href="${item.linkUri}" class="current"><img width="1560" height="570" src="${item.fileUri!''}" alt="${item.title!''}"/></a>
                    </#list>
                    </#if>
                </div>
                <div class="saletitle">
                	<#if limit_time_product_list??>
	                <#list limit_time_product_list as item>
	                	<#if item_index gt 3>
							<#break>
						</#if>
	                    <div><a href="${item.linkUri}">${item.title!""}</a></div>
	                </#list>
	                </#if>
                </div>
            </div>

			<#--
            <div class="act_more">
                <a href="#">更多攻略游记>></a>
            </div>-->
        </div>
    </div>


	<#-- <div class="main_act">
		<div class="act_title">
			<span></span>
			<a href="/list/5" target="_blank"><#if index_product_category??>${index_product_category.title!''}</#if></a>
			<span></span>
		</div>
		<div class="act_box">
			<div class="act_nav">
				<#if activity_list??>
					<#list activity_list as item>
						<a href="${item.linkUrl!''}" target="_blank">
							<p>${item.title!''}</p>
							<span>${item.callIndex!''}</span>	
						</a>
						<#if item_index gt 2>
							<#break>
						</#if>
					</#list>
				</#if>
			</div>
			<ul class="act_main">
				<li class="li01">
					<#if f1_up_ad_list?? && f1_up_ad_list?size gt 0>
						<a href="${f1_up_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f1_up_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li02">
					<#if f1_right_ad_list?? && f1_right_ad_list?size gt 0>
						<a href="${f1_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f1_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f1_down_left_ad_list?? && f1_down_left_ad_list?size gt 0>
						<a href="${f1_down_left_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f1_down_left_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f1_down_right_ad_list?? && f1_down_right_ad_list?size gt 0>
						<a href="${f1_down_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f1_down_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
			</ul>
			
			<ul class="act_main">
				<li class="li01">
					<#if f2_up_ad_list?? && f2_up_ad_list?size gt 0>
						<a href="${f2_up_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f2_up_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li02">
					<#if f2_right_ad_list?? && f2_right_ad_list?size gt 0>
						<a href="${f2_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f2_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f2_down_left_ad_list?? && f2_down_left_ad_list?size gt 0>
						<a href="${f2_down_left_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f2_down_left_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f2_down_right_ad_list?? && f2_down_right_ad_list?size gt 0>
						<a href="${f2_down_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f2_down_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
			</ul>
			<ul class="act_main">
				<li class="li01">
					<#if f3_up_ad_list?? && f3_up_ad_list?size gt 0>
						<a href="${f3_up_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f3_up_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li02">
					<#if f3_right_ad_list?? && f3_right_ad_list?size gt 0>
						<a href="${f3_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f3_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f3_down_left_ad_list?? && f3_down_left_ad_list?size gt 0>
						<a href="${f3_down_left_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f3_down_left_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f3_down_right_ad_list?? && f3_down_right_ad_list?size gt 0>
						<a href="${f3_down_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f3_down_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
			</ul>
			<ul class="act_main">
				<li class="li01">
					<#if f4_up_ad_list?? && f4_up_ad_list?size gt 0>
						<a href="${f4_up_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f4_up_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li02">
					<#if f4_right_ad_list?? && f4_right_ad_list?size gt 0>
						<a href="${f4_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f4_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f4_down_left_ad_list?? && f4_down_left_ad_list?size gt 0>
						<a href="${f4_down_left_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f4_down_left_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
				<li class="li03">
					<#if f4_down_right_ad_list?? && f4_down_right_ad_list?size gt 0>
						<a href="${f4_down_right_ad_list[0].linkUri!''}" target="_blank">
							<img src="${f4_down_right_ad_list[0].fileUri!''}"  />
						</a>
					</#if>
				</li>
			</ul>
		</div>	
	</div> -->
	<div class="onway">
			<div class="onway_title">
				<span></span>
				<a class="a1" id="1111">在路上</a>
				<a class="a2" id="2222" href="/list/${specialtyId!c}" target="_blank">特产商城</a>
				<span></span>
			</div>
			<ul>
				<#if travel_notes_page??>
				<#list travel_notes_page.content as item>
					<li>
						<img src="${item.imgUrl!''}"  />
						<a href="/user/travel/search?id=${item.id!0}" target="_blank">
							<dl class="onway_box">
								<dt>
									<p>${item.title!''}</p>
									<span>${item.content!''}</span>
								</dt>
								<dd>
									<span class="span1">${item.countPraise!0}</span>
									<span class="span2">${item.countComment!0}</span>
									<span class="span3">${item.username!''}</span>
								</dd>
							</dl>
						</a>
					</li>
				</#list>
				</#if>
			</ul>
			
			<div class="act_more">
				<a href="/user/travel/list" target="_blank">更多攻略游记>></a>
			</div>
			
			<#--
			<#if travel_notes_page??>
                <#assign continueEnter=false>
                <#if travel_notes_page.totalPages gt 0>
                   	<ul class="mymember_page">
                    <#list 1..travel_notes_page.totalPages as page>
                        <#if page <= 3 || (travel_notes_page.totalPages-page) < 3 || (travel_notes_page.number+1-page)?abs<3 >
                            <#if page == travel_notes_page.number+1>
	                            <dd>
	                                <a class="mysel" href="javascript:;">${page}</a>
	                            </dd>
                            <#else>
	                            <dd>
	                                <a href="/?page=${page-1}">${page}</a>
	                            </dd>
                            </#if>
                            <#assign continueEnter=false>
                        <#else>
                            <#if !continueEnter>
                                <b class="pn-break">…</b>
                                <#assign continueEnter=true>
                            </#if>
                        </#if>
                    </#list>
                   </ul>
                </#if>
                </#if>
            -->
		</div>
</div>
<!-- ********************中间内容-结束******************** -->

<#include "/client/common_footer.ftl" />

<script type="text/javascript">
	window.onload = function(){
		banner_go('my');//焦点图
		become_big('act_main li a',30);//图片发放大
		box_out();//图片滑出
		act_nav();
	};
	
	//手机号
	var phoneFormat = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	
	//天数（正整数）
	var zzs =  /^[1-9]\d*$/;
	function goCustom(){
		var month = $("#inputmonth").val();
		var place = $("#inputwhither").val();
		var totalDays = $("#inputday").val();
		var totalPersons = $("#inputpeonum").val();
		var telephone = $("#inputphone").val();
		
		if(place==""){
			alert("请填写旅游地点！");
	        return ;
		}
		
		if(!zzs.test(totalDays)){
			alert("请输入正整数！");
	        return ;
		}
		
		if(telephone==""){
			alert("请输入手机号！");
	        return ;
		}else if(!phoneFormat.test(telephone)){
	        alert("手机号格式错误，请重新填写！");
	        return ;
    	}
    	
		$.ajax({
			type: "POST",
			url: "/visitorCustom/save",
			data: "month="+month+"&place="+place+"&totalDays="+totalDays+"&totalPersons="+totalPersons+"&telephone="+telephone,
			success: function(backInfo){
				if(backInfo.success){
					alert("定制成功，请保持电话畅通！");
					window.location.href="/search_list?keyword="+place;
				}else{
					alert(backInfo.msg);				
				}
			}
		});
	}
</script>
</body>
</html>
