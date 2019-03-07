<div class="foot_bgcolor">
	<div class="foot">
		
		<ul class="foot1">
			<#if article_cat_list??>
				<#list article_cat_list as item>
					<#if item_index==0>
					<li style="color: white;"><a href="javascript:;">网站首页</a></li>
					</#if>
					<li style="color: white;"><a href="javascript:;" >${item.title!''}</a></li>
					<#if item_index==2>
						<#break>
					</#if>
				</#list>
			</#if>
		</ul>
		<ul class="foot2">
			<#if article_cat_list?? && article_cat_list?size gt 3>
				<#list article_cat_list as item>
					<#if item_index gt 2 && item_index < 7 >
						<li><a href="javascript:;" >${item.title!''}</a></li>
					</#if>
				</#list>
			</#if>
		</ul>
		
		
		
		<div class="foot3">
			<p><label>定制热线：<span><#if setting??>${setting.telephone!''}</#if></span></label></p>
			<p>
				<label>售后服务：<span><#if setting??>${setting.qq2!''}</#if></span></label>
			</p>
			<p><label>邮箱：<span><#if setting??>${setting.adminEmail!''}</#if></span></label></p>
			<p><label>联系地址：<span><#if setting??>${setting.address!''}</#if></span></label></p>
		</div>
		<div class="foot4">
			<img src="<#if setting??>${setting.wxQrCode!''}</#if>">
		</div>
	</div>
</div>
<div class="flast"><p><#if setting??>${setting.copyright!''}</#if></p></div>

<script>
    $(function(){
        //float_right_bar St
        $(".float-right-bar li").hover(function () {
            var bdshare = $(this).find(".js-bdshare");
             if (bdshare.length > 0) {
                bdshare.show().animate({"left": "-265px"}, 300);
            }else{
                 $(this).find(".tooltip").show().animate({"left": "-130px"}, 300);
             }
        }, function () {
            var bdshare = $(this).find(".js-bdshare");

             if (bdshare.length > 0) {
                bdshare.hide().animate({"left": "-284px"}, 300);
            }else{
                 $(this).find(".tooltip").hide().animate({"left": "-150px"}, 300);
             }
        })
        $(".bar-backtop").on("click", function () {
            $('body,html').animate({scrollTop: 0}, 300);
            return false;
        })
        //    float_right_bar End
    });
</script>

<style type="text/css">

</style>


<!-- 右侧导航条 -->
<ul class="float-right-bar ">
    <li class="bar-custom">
        <a rel="nofollow" target="_blank" href="javascript:;" class="bar-link " id="event-cs-cd"></a>
        <div class="tooltip fade in " style="display: none; left: -150px;">
            <div class="talk">
            	<#if service_qq_list??>
            	<#list service_qq_list as item>
	                <p>${item.department!''}：</p>
	                <a href="javascript:;"><img src="/client/images/talkonline.png"></a>
            	</#list>
            	</#if>
            </div>

        </div>
    </li>
    <li class="bar-share">
        <a href="javascript:void(0);"></a>
        <script>
            window._bd_share_config={
                "common":{"bdSnsKey":{},
                    "bdText":"",
                    "bdMini":"2",
                    "bdMiniList":false,
                    "bdPic":"",
                    "bdStyle":"0",
                    "bdSize":"32"},
                "share":{}};
            with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
        </script>
        <div class="bdsharebuttonbox  js-bdshare">
            <a href="#" class="bds_more" data-cmd="more"></a>
            <a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a>
            <a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a>
            <a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a>
            <a title="分享到人人网" href="#" class="bds_renren" data-cmd="renren"></a>
            <a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a>
        </div>
    </li>
    <li class="bar-backtop">
    </li>
</ul>