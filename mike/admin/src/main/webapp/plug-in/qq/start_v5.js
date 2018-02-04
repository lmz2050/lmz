var QQOnline={
		init:function(){
			var a=this;
			a.bindQQOnline();
			a.bindScroll();
		},
		bindQQOnline:function(){
			$("#floatTrigger").bind("click",function(){
				if($("#online_qq_layer").attr("show")){
					$("#online_qq_layer").animate({right:"-140px"});
					$("#online_qq_layer").removeAttr("show")
				}else{
					$("#online_qq_layer").animate({right:"0px"});
					$("#online_qq_layer").attr("show","1")
				}
				return false
			});
			$("#online_qq_layer").animate({right:"-140px"});
			$("#online_qq_layer").removeAttr("show");
			$(document).bind("click",function(a){
				if($(a.target).isChildOf("#online_qq_layer")==false){
					$("#online_qq_layer").animate({right:"-140px"});
					$("#online_qq_layer").removeAttr("show")
				}
			});
			jQuery.fn.isChildAndSelfOf=function(a){
				return(this.closest(a).length>0)
			};
			jQuery.fn.isChildOf=function(a){
				return(this.parents(a).length>0)
			}
		},
		bindScroll:function(){
			$(window).scroll(function(){
				var b=$(window).scrollTop();
				$("#online_qq_layer").css({top:b+150});
			})
		}
};
$(function(){QQOnline.init()});

var GetLength = function (str) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};

//js截取字符串，中英文都能用  
//如果给定的字符串大于指定长度，截取指定长度返回，否者返回源字符串。  
//字符串，长度  

/** 
 * js截取字符串，中英文都能用 
 * @param str：需要截取的字符串 
 * @param len: 需要截取的长度 
 */
jQuery.fn.cutstr=function(len,b) {
	$(this).each(function(){
		var str=$(this).html();
		var str_length = 0;
	    var str_len = 0;
	    str_cut = String();
	    str_len = str.length;
	    var nstr=str;
	    for (var i = 0; i < str_len; i++) {
	        a = str.charAt(i);
	        str_length++;
	        if (escape(a).length > 4) {
	            //中文字符的长度经编码之后大于4  
	        	if(b){}else{
	            str_length++;
	        	}
	        }
	        str_cut = str_cut.concat(a);
	        if (str_length >= len) {
	            str_cut = str_cut.concat("...");
	            nstr=str_cut;
	            break;
	        }
	    }
	    //如果给定字符串小于指定长度，则返回源字符串；  
	    if (str_length < len) {
	    	nstr=str;
	    }
		$(this).html(nstr);
	})
};
$(function(){
	$(".i_box2_news li a span").cutstr(35);
	$(".foot dl a span").cutstr(13,true);
	$(".sideFloat a p").cutstr(9,true);
	
});