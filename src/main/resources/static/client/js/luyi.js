
	window.onload = function(){
		banner_go('my');//焦点图
		become_big('act_main li a',30);//图片发放大
		box_out();//图片滑出
		act_nav();
	function $(id){return document.getElementById(id);}
	if($("cllick_morea")){
		var more_li = $("click_moreul").getElementsByTagName("li");
		
		$("cllick_morea").onclick=function(){
			if($("cllick_morea").innerHTML=="点击加载更多&gt;&gt;"){
				this.innerHTML="点击隐藏&gt;&gt;"
				for(var i=2;i<more_li.length;i++){
					more_li[i].style.display="block";
				}
			}
			else{
				this.innerHTML="点击加载更多&gt;&gt;"
				for(var i=2;i<more_li.length;i++){
					more_li[i].style.display="none";
				}
			}
		}
				
	}
	if($("steamship_ul")){
		var steamship_li=$("steamship_ul").getElementsByTagName("li")
		for(i=0;i<steamship_li.length;i++){
			steamship_li[i].onclick=function(){
				for(i=0;i<steamship_li.length;i++){
					steamship_li[i].className="";
				}
				this.className="li1";
			}
		}
	}
	if($("form_leftp1")){
		var p1_a=$("form_leftp1").getElementsByTagName("a")
		for(i=0;i<p1_a.length;i++){
			p1_a[i].onclick=function(){
				for(i=0;i<p1_a.length;i++){
					p1_a[i].className=""
				}
				this.className="colora";
			}
		}
	}
	if($("ping")){
		$("ping").onclick=function(){
			$("now_valuetion").style.display="block";
		}
	}
	if($("closei")){
		$("closei").onclick=function(){
			$("now_valuetion").style.display="none";
		}
	}
	if($("loadpic")){
		$("loadpic").onclick=function(){
			$("loadpic_input").click();
		}
	}
	if($("lli01")){
		var llia=$("lli01").getElementsByTagName("a");
		for(i=0;i<llia.length;i++){
			llia[i].onclick=function(){
				for(i=0;i<llia.length;i++){
					llia[i].className="";
				}
				this.className="span01";
			}
		}
	}
//	if($('obj_assess')){
//		var obj_li=$('obj_assess').getElementsByTagName('li');
//		for(i=0;i<obj_li.length;i++){
//			var obj_div=obj_li[i].getElementsByTagName('div');
//			var ovj_a=obj_div[obj_div.length-1].getElementsByTagName('a');
//			if(ovj_a[0].title=='评价'){
//				ovj_a[0].onclick=function(){
//					$('now_valuetion').style.display="block";
//				}
//			}
//		}
//	}

}