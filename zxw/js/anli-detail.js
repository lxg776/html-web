//console.log("123123")
$(function(){
	
	$(".top-menu li").click(function(){
			
			
		var $index = $(this).index();
		console.log($index);	
						
		  $(".green-switch .show-content .content").hide();
		 $(".green-switch .show-content .content:eq("+$index+")").show();
		 //$(".xq-part2 .top-menu .item").removeClass(".xq-part2 .top-menu .item-selected");
		// $(this).removeClass(".item");
		 $(".top-menu li").removeClass("item-selected");
		
		 $(this).addClass("item-selected");
		 
	});
	
	
	
	
	

	
})


