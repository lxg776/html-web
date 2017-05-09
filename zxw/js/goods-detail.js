//console.log("123123")
$(function(){
	
	$(".top-menu li").click(function(){
			
			
		var $index = $(this).index();
		console.log($index);	
						
		  $(".xq-part2 .show-content div").hide();
		 $(".xq-part2 .show-content div:eq("+$index+")").show();
		 //$(".xq-part2 .top-menu .item").removeClass(".xq-part2 .top-menu .item-selected");
		// $(this).removeClass(".item");
		 $(".top-menu li").removeClass("item-selected");
		
		 $(this).addClass("item-selected");
		 
	});
	
	
	
	
	

	
})


