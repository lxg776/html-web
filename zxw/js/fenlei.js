//console.log("123123")
$(function(){
	
	$(".fl-p1 .left .main i").click(function(){
		  var ulNode = $(this).parent().next(".hiden-ul");
		   var i = $(this);
		      ulNode.slideToggle(300,function(){
			  if ($(this).is(':hidden')) {
				   i.removeClass("rotate90");
			  }else{
				   i.addClass("rotate90");  
			  }
			  
		    });	
		 
		 
	});
	
	
	
	
	$(".fl-p1 .left .main .a02").click(function(){
		 $(this).parent().parent().parent().find(".a02").css("color", "#2b2b2b");
		 console.log($(this).parent().parent())
		
		  //$(this).parent().parent().find(".a02").css("color", "#2b2b2b");
		 $(this).css("color", "#e51c23");
	});
	
	$(".fl-p1 .left .main .a03").click(function(){
		 $(this).parent().parent().parent().find(".a03").css("color", "#2b2b2b");
		 console.log($(this).parent().parent())
		
		  //$(this).parent().parent().find(".a02").css("color", "#2b2b2b");
		 $(this).css("color", "#e51c23");
	});
	

	
})


