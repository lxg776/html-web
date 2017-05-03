//console.log("123123")
$(function(){
	
	var navFla;//显示标识
	

	
	$(".sc-fenlei .nav").on({
		mouseenter:function(){
					$(".sc-fenlei .nav li").on("mouseenter",function(){
							navFla = true;
							$(".sc-fenlei .nav-show").fadeIn(300);
							var $index = $(this).index();
							$(".sc-fenlei .nav-show .item").hide();
							$(".sc-fenlei .nav-show .item:eq("+$index+")").show();
						
						})
			},
			mouseleave:function(){
				navFla = false;
				 //$('.sc-fenlei .nav-show').delay(2000).fadeOut(100);
					setTimeout(function(){
						if(navFla){
			//$(".sc-fenlei .nav-show").fadeIn(300);
					}else{
						$(".sc-fenlei .nav-show").fadeOut(100);
					}
					},1000);
				 //$(".sc-fenlei .nav-show").fadeOut(100);
				}
	}
	
	
	)
	//console.log("999999")
	
	
	$(".sc-fenlei .nav-show").on({
		mouseenter:function(){
			       navFla = true;
					$(".sc-fenlei .nav-show").show();
			 
			},
			mouseleave:function(){
				navFla = false;
				 			setTimeout(function(){
						if(navFla){
			//$(".sc-fenlei .nav-show").fadeIn(300);
					}else{
						$(".sc-fenlei .nav-show").fadeOut(100);
					}
					},1000);
				//setTimeout(navShow(),10000);
				
			}
	}
	
	
	)
	
})


