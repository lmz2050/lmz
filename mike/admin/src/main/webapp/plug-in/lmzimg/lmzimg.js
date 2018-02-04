

jQuery.fn.loadImg=function(){
	$(this).each(function(){
		var img=$(this);
		img.load(function(){
			$(this).sizeImg();	
		});
		img.attr('src',img.attr('src1'));
	});
};


jQuery.fn.sizeImg=function(){
	$(this).each(function(){
		var img=$(this);
		var x=img.attr('x');
		var y=img.attr('y');
		//alert(x+","+y+","+img.width()+","+img.height());
	    if(img.height()/img.width()>y/x){
			 if(img.height()>y){
				 img.height(y);
				 //var mx=(x-img.width())/2;
				 //img.css({marginLeft:mx,marginRight:mx});
			  }else{
				 var mx=(x-img.width())/2;
				 var my=(y-img.height())/2;
				 //img.css({marginLeft:mx,marginRight:mx,marginTop:my,marginBottom:my});			 
			  }
		  }else{
			if(img.width()>x){
			   img.width(x);
			   var my=(y-img.height())/2;	
			   //img.css({marginTop:my,marginBottom:my});	
			 }else{
				 var mx=(x-img.width())/2;
				 //var my=(y-img.height())/2;
				 //img.css({marginLeft:mx,marginRight:mx,marginTop:my,marginBottom:my});				 
			}
			  
		 }	
	});
};

$(function(){
	
	$(".limg").loadImg();
	
});