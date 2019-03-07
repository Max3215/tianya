<meta http-equiv="X-UA-Conpatible" content="IE=Edge,chrome=1">
<div class="top">
	<a href="javascript:;"><img src="<#if setting??>${setting.logoUri!''}</#if>" style="width:353px;height:30px;"></a>
	<div class="top_right">
		<p class="trightp">
                <label><a href="javascript:;" target="_blank">注册<i></i></a></label> |   
                <label class="dter"><a href="javascript:;" target="_blank">登录<i></i></a></label>         
		</p>
		<p><#if setting??>${setting.telephone!''}</#if></p>
	</div>
</div>


<div class="nav_bgcolor">
	<div class="nav">
		<ul class="nav_ul">
			<#if navi_bar_list??>
				<#list navi_bar_list as item>
					<#if !item.parentId??>
						<li>
							<a href="javascript:;">
								<#if item.iconUri?? && item.iconUri!=""><img src="${item.iconUri!''}"></#if>
								<span>${item.title!''}</span>
							</a>
							<#if navi_bar_list[item_index+1]?? && navi_bar_list[item_index+1].parentId??>
								<div class="nav_hover">
									<#list 1..navi_bar_list?size as idx>
										<a href="javascript:;">${navi_bar_list[item_index+idx].title!''}</a>
										<#if !navi_bar_list[item_index+idx+1]?? || !navi_bar_list[item_index+idx+1].parentId??>
											<#break>
										</#if>
									</#list>
								</div>
							</#if>
						</li>
					</#if>
				</#list>
			</#if>
		</ul>
		<form  method="get"  class="ksearch">
			<input type="text" name="keyword" style="display:block;"  placeholder="请输入关键字">
			<a href="javascript:void(0);" name="submit" ></a>
		</form>
		<style type="text/css">
			.ksearch{position:relative;width: 188px;height:48px;float:left;}
			.ksearch a{position:absolute;top:10px;left:0px;display:block;width:38px;height:26px;}
		</style>
	</div>
</div>


<div class="clear"></div>

