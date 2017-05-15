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
	
	
	function chageCountAndPrice(){
		
		select_count = 0;
		totalPrice = parseFloat("0.00");
		
		$(".car-list .radio-frame").each(function(index, element) {
				if($(this).is(':checked')){
					select_count=select_count+1;
					itemPrice = $(this).parent().parent().children(".pli").children(".item-price").text();
					itemPrice = parseFloat(price_replace(itemPrice,"¥"));
					totalPrice = totalPrice + itemPrice ;
				}else{
					$(".mycar-footer .select-all").prop("checked",false);
		$(".order-head .radio-frame").prop("checked",false);
				}
        });
		
		if(select_count>0){
			$(".mycar-footer .pay-btn").addClass("redbtn");
		}else{
			$(".mycar-footer .pay-btn").removeClass("redbtn");
			$(".mycar-footer .select-all").prop("checked",false);
		$(".order-head .radio-frame").prop("checked",false);
		}
		
		$(".mycar-footer .count").text(select_count);
		$(".mycar-footer .all-price").text(text2price(totalPrice));
	}
	
	$(".car-list .item-del").click(function(){
		
		$(this).parent().parent().parent().remove();
		chageCountAndPrice();
	});
	
	
	function selectAll(fla){
		if(fla){
			$(".car-list .radio-frame").prop("checked",true); 
		}else{
			$(".car-list .radio-frame").prop("checked",false); 
		}
		$(".mycar-footer .select-all").prop("checked",fla);
		$(".order-head .radio-frame").prop("checked",fla);  
		
		chageCountAndPrice();
	}
	
	function delectAll(){
			$(".car-list .radio-frame").each(function(index, element) {
				if($(this).is(':checked')){
					$(this).parent().parent().parent().remove();
				}
        });
		chageCountAndPrice();
		
	}
	
	
	
	$(".mycar-footer .delAll").click(function(){
		delectAll();
	});	
		
		
	$(".mycar-footer .select-all").change(function(){
		if($(this).is(':checked')){
			selectAll(true);
		}else{
			selectAll(false);
		}
	});	
		
	
	$(".car-list .radio-frame").change(function(){
		
		
		chageCountAndPrice();
	});
	
	
	$(".order-head .radio-frame").change(function(){
		
		
		if($(this).is(':checked')){
			selectAll(true);
		}else{
			selectAll(false);
		}
	});
	
	
	
	
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
					
					chageCountAndPrice();
				}
				
 
		}	
	);
	
	
	
		$(".my-part01 .car-list .c-sub").click(function(){
				//toFixed
				count = parseInt($(this).next(".c-count").text());
				price = price_replace($(this).parent().parent().prev("li").text(),"¥");
				
				
				
				//p1 = $(this).parent().parent().parent().next("li").html();
				//console.log(index)
				if(count>1){
					count=count-1;
					$(this).next(".c-count").text(count);
					//$(this).parent().parent().next("li > div").text());
					itemPrice = text2price(price)*count;
					$(this).parent().parent().next("li").children(".item-price").text("¥"+text2price(itemPrice+""));
					chageCountAndPrice();
				}
				
 
		}	
	);

	

	

	
	

	
	
	
})


