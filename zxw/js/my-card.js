//console.log("123123")
$(function(){
	
	function price_replace(src,pattern){
		var restring = src.replace(new RegExp(pattern),"");
		return restring;
	}
	
	function text2price(price){
		price = parseFloat(price).toFixed(2);
		return price; 
		//return price;	
	}
	
	
	
	$(".my-part01 .car-list .c-add").click(function(){
				//toFixed
				count = parseInt($(this).prev(".c-count").text());
				price = price_replace($(this).parent().parent().prev("li").text(),"¥");
				
				
				
				//p1 = $(this).parent().parent().parent().next("li").html();
				//console.log(index)
				if(count<100){
					count=count+1;
					$(this).prev(".c-count").text(count);
					//$(this).parent().parent().next("li > div").text());
					itemPrice = text2price(price)*count;
					$(this).parent().parent().next("li").children(".item-price").text("¥"+text2price(itemPrice+""));
				}
				
 
		}	
	);
	
	$(".my-part01 .car-list .c-sub").click(function(){
				count = parseInt($(this).next(".c-count").text());
				if(count>1){
					$(this).prev(".c-count").text(count-1);
					
				}
				
 
		}	
	);
	

	
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


