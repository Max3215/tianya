<meta http-equiv="X-UA-Conpatible" content="IE=Edge,chrome=1">
<div class="top">
	<a href="/"><img src="<#if setting??>${setting.logoUri!''}</#if>" style="width:353px;height:30px;"></a>
	<div class="top_right">
		<p class="trightp">
			<#--
			<#if Session.username?exists>
				欢迎 &nbsp;
				<#if isQQLogin??>
				<a href="/user/0" target="_blank">${Session.qqNickname!''}<i></i></a> |&nbsp;								
				<#else>
				<a href="/user/0" target="_blank">${Session.username!''}<i></i></a> |&nbsp;
				</#if>                    		
				<a href="/logout">退出<i></i></a>	
				-->
			<#if username??>
			 	欢迎 &nbsp;                   
                <a href="/user/0" target="_blank">${username!''}<i></i></a> |&nbsp;
                <a href="/logout">退出<i></i></a>
            <#else>
                <label><a href="/reg" target="_blank">注册<i></i></a></label> |   
                <label class="dter"><a href="/login" target="_blank">登录<i></i></a></label>         
            </#if>
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
							<a href="${item.linkUri!''}">
								<#if item.iconUri?? && item.iconUri!=""><img src="${item.iconUri!''}"></#if>
								<span>${item.title!''}</span>
							</a>
							<#if navi_bar_list[item_index+1]?? && navi_bar_list[item_index+1].parentId??>
								<div class="nav_hover">
									<#list 1..navi_bar_list?size as idx>
										<a href="${navi_bar_list[item_index+idx].linkUri!''}">${navi_bar_list[item_index+idx].title!''}</a>
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
		<form action="/search_list" method="get" id="subform" class="ksearch">
			<#if keyword2??><input type="hidden" id="keyword2" value="${keyword2!''}"></#if>
			<input type="text" name="keyword" style="display:block;" id="keyword1" <#if keyword??>value="${keyword!''}"</#if> placeholder="请输入关键字">
			<a href="javascript:void(0);" name="submit" onclick="document.getElementById('subform').submit();"></a>
		</form>
		<style type="text/css">
			.ksearch{position:relative;width: 188px;height:48px;float:left;}
			.ksearch a{position:absolute;top:10px;left:0px;display:block;width:38px;height:26px;}
		</style>
	</div>
</div>

<script type="text/javascript">
	function onchange(){
		var objS = document.getElementById("pid");
		var keyword = $("#keyword1").val();
		var keyword2 = $("#keyword2").val();
		var grade = objS.options[objS.selectedIndex].value;
		if(keyword2==null){
			window.location.href="/search?keyword="+keyword+"&grade="+grade;
		}else{
			window.location.href="/search?keyword="+keyword+"&grade="+grade+"&keyword2="+keyword2;
		}
	}
	
	
	function pages(page){
		var objS = document.getElementById("pid");
		var keyword = $("#keyword1").val();
		var keyword2 = $("#keyword2").val();
		var grade = objS.options[objS.selectedIndex].value;
		if(keyword2==null){
			window.location.href="/search?keyword="+keyword+"&grade="+grade+"&page="+page;
		}else{
			window.location.href="/search?keyword="+keyword+"&grade="+grade+"&keyword2="+keyword2+"&page="+page;
		}
	}
	
</script>

<div class="clear"></div>

