$(function(){
	nav_down();//首页nav

});
/////////////////////////////////////////////////nav下拉菜单
function nav_down(){
	$('.nav_ul li').each(function(i){
		var chi = $('.nav_ul li').eq(i).children().eq(1);
		chi.width($('.nav_ul li').eq(i).width());
		$(this).hover(
			function(){
				
				chi.stop(false,true).slideDown()
			},
			function(){
				chi.stop(false,true).slideUp()
			}
		)
		/*$('.nav_ul li').eq(0).mouseover(function(){
				$('.nav_hover').stop(false,true).slideUp()
		})*/
	});
};


/////////////////////////////////////////////////////////////////////////banner焦点图
	function banner_go(my_style){
	var n = 0;
	var timer = null;
	var len= $('.banner_scrool img').length;
		
	//初始化
	$('.banner_scrool img').eq(0).show();
	$('.move_btn li a').eq(0).addClass(my_style);
	
	$('.move_btn li').each(function(i){
		$(this).mouseover(function(){
			//alert(i)
			if($('.banner_scrool img').eq(i).css('display') == 'none'){
				if(!$('.banner_scrool img').stop(false,true).is(':animated')){
					$('.banner_scrool img').fadeOut(1000);
					$('.banner_scrool img').eq(i).fadeIn(1000);
					$('.move_btn li a').removeClass(my_style)
					$('.move_btn li a').eq(i).addClass(my_style);
				};	
			};
			n=i;
		});
	});
	//自动走
	timer = setInterval(function(){	
		//alert($('.banner_move img').length)
	if($('.banner_scrool img').eq(n).css('display') == 'none'){
		if(!$('.banner_scrool img').is(':animated')){
			$('.banner_scrool img').fadeOut(2000);
			$('.banner_scrool img').eq(n).fadeIn(2000);
			$('.move_btn li a').removeClass(my_style)
			$('.move_btn li a').eq(n).addClass(my_style);
		};	
	};
		n++;
		n%=len;
		//alert(n)
	},5000);	
};

//////////////////////////////////////////////////////////////////图片放大 封装
function become_big(obj,big){
				
			var my_obj = $('.'+ obj)
			
			my_obj.each(function(i){
				var wi = my_obj.eq(i).width();
				var he = my_obj.eq(i).height();
				var wi_a = wi+2*big;
				var he_a = he+2*big;
				$(this).hover(
					function(){
						my_obj.eq(i).stop(false,true).animate({left:-big,width:wi_a,top:-big,height:he_a},200)
						my_obj.eq(i).css({zIndex:'99999'})
					},
					function(){
						my_obj.eq(i).stop(false,true).animate({left:'0px',width:wi,top:'0px',height:he},200)
						my_obj.eq(i).css({zIndex:'0'})
					}
				)
			})

				/*my_obj.mouseover(function(){
					my_obj.stop(false,true).animate({left:-big,width:wi_a,top:-big,height:he_a})
					
				})
				my_obj.mouseout(function(){
					my_obj.stop(false,true).animate({left:'0px',width:wi,top:'0px',height:he})
					
				})*/
};
////////////////////////////////////////////////act_nav
function act_nav(){
	$('.act_main').hide();
	$('.act_main').eq(0).show();
	$('.act_nav a').each(function(i){			
		$(this).mouseover(function(){
			$('.act_main').hide();
			$('.act_main').eq(i).show();
		})				
	});
};

///////////////////////////////////////////////////////index_onway  
function box_out(){
	$('.onway ul li').each(function(i){
		$(this).hover(
			function(){
				$('.onway ul li a').eq(i).stop(false,true).show(2000);
			},
			function(){
				$('.onway ul li a').eq(i).stop(false,true).hide(2000);
			}
		)
	})	
};
////////////////////
function rich_lee(){
	var aUl = $('.bote_nav ul');
	var len = 0;
	for(var i=0;i<aUl.length;i++){
		nav_go(aUl[i]);
	};
	
	function nav_go(obj){
		var aLi = obj.children;	
		var last = obj.children[obj.children.length-1].children;
		for(var k=0;k<last.length;k++){
			last[k].style.border = 'none';
		};
	};	
};








































