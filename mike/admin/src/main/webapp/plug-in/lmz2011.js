
var lmz={
    version:"2.0",
	autor:"lmz",
	
   //坐标
	xyO:{
		  x:0,
		  y:0,
		  init:function(x,y){
		    this.x=x;
		    this.y=y;
			return this;
		  },
		  add:function(x,y){
			this.x=this.x+x;
			this.y=this.y+y;
			return this;
		  }
		},
		
   //页面宽度高度	
   docO:{
	     ww:function(){return Math.max((document.compatMode == "CSS1Compat"?document.documentElement.scrollWidth:document.body.scrollWidth),$(window).width());},
		 hh:function(){return Math.max((document.compatMode == "CSS1Compat"?document.documentElement.scrollHeight:document.body.scrollHeight),$(window).height());}
	   },


   //可见区域宽高
   bodyO:{ww:function(){return $(window).width();},hh:function(){return $(window).height();}},
   
   //鼠标
   mouseO:{
	      position:function(e){
		    var posx = 0;
		    var posy = 0;
		    if (!e) var e = window.event;
		    if (e.pageX || e.pageY) {
			  posx = e.pageX;
			  posy = e.pageY;
		    }else if (e.clientX || e.clientY) {
			  posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
			  posy = e.clientY + document.body.scrollTop  + document.documentElement.scrollTop;
		    }
		    return { 'x': posx, 'y': posy };			  			 		
		  },

		  lClick:function(o,fn){
	         o.bind('mousedown',function(){
		       if (event.button==1) {
			    fn($(this));
		       }
	         });			  
		  },
		  rClick:function(o,fn){
	         o.bind('mousedown',function(){
		       if (event.button==2) {
			    fn($(this));
		       }
	         });			  			  
		  } 
   },

  //滚动条
  scrollO:{
	ll:function(){
	   var ll=0;
       if(document.body && document.body.scrollLeft){
          ll=document.body.scrollLeft;
        }
        if(document.documentElement && document.documentElement.scrollLeft){
           ll=document.documentElement.scrollLeft;
        }
		return ll;
    },
	tt:function(){
	    var tt=0;
        if(document.body && document.body.scrollTop){
		   tt=document.body.scrollTop;
        }
        if(document.documentElement && document.documentElement.scrollTop){
		   tt=document.documentElement.scrollTop;
        }
	    return tt;	
     }
	  
  },
  
  //获取事件e
  evt:function(){return window.event || arguments.callee.caller.arguments[0];}
  

	
};


/*******************************lmzDrag===S**********************************/
/*

 lmzDrag($("#drag"),$("#drag h3"),{

    onMove:function(o,bar,p){
	 if(p.x<0||p.y<0){
	   if(p.x<0){p.x=0;bar.html("已到最左边！");}
	   if(p.y<0){p.y=0;bar.html("已到最上边！");}
	  }else{
	    bar.html("dddd");
	  }
	   o.css({left:p.x,top:p.y});

	}
 });


*/

function lmzDrag(o,bar,options){
  
  var opts=$.extend({},{
		cursor:"pointer",
		onDown:function(o,bar,m){},
		onUp:function(o,bar,m){},
		onMove:function(o,bar,p){},
		Wdisable:false,
		Hdisable:false
		
  },options||{});
  
  var lastMx;
  var lastMy;
  var lastEx;
  var lastEy;
  o.css({position:"absolute"});
  bar.css('cursor',opts.cursor);
  bar.mousedown(function(e){
	var m=lmz.mouseO.position(e);
	lastMx=m.x;
	lastMy=m.y;
	lastEx=o.offset().left;
	lastEy=o.offset().top;
	opts.onDown(o,bar,m);
    $(document).bind("mousemove",function(e){
      var m=lmz.mouseO.position(e);
	  var sx=m.x-lastMx;
	  var sy=m.y-lastMy;
	  var nextEx=lastEx+sx;
	  var nextEy=lastEy+sy;
	  
	  if(opts.Wdisable){nextEx=lastEx;}
	  if(opts.Hdisable){nextEy=lastEy;}
	  
	  o.css({left:nextEx,top:nextEy});  
	  opts.onMove(o,bar,{'x':nextEx,'y':nextEy});
    }).bind("mouseup",function(){
      $(document).unbind("mousemove").unbind("mouseup");
	  opts.onUp(o,bar,m);
   });
 });	

}






/******************************lmzDrag===E**********************************/


/**-----------------------------sizeImg==S---------------------------------------**/
//等比例缩放

jQuery.fn.sizeImg=function(x,y){
	var imgs=$(this);
	imgs.parent().css({width:x,height:y,padding:"0"});
	imgs.css({margin:"0"});	
	imgs.each(function(){
	  var img=$(this);
     if(img.height()/img.width()>y/x){
		 if(img.height()>y){
			 img.height(y);
			 var mx=(x-img.width())/2;
			 img.css({marginLeft:mx,marginRight:mx});
		  }else{
			 var mx=(x-img.width())/2;
			 var my=(y-img.height())/2;
			 img.css({marginLeft:mx,marginRight:mx,marginTop:my,marginBottom:my});			 
		  }
	  }else{
		if(img.width()>x){
		   img.width(x);
		   var my=(y-img.height())/2;	
		   img.css({marginTop:my,marginBottom:my});	
		 }else{
			 var mx=(x-img.width())/2;
			 var my=(y-img.height())/2;
			 img.css({marginLeft:mx,marginRight:mx,marginTop:my,marginBottom:my});				 
		}
		  
	 }
     img.click(function(){
    	 var a = $(this).parents('A:eq(0)');
    	 if(a&&a.attr('href')){
    		 window.location.href = a.attr('href');
    	 }
     });
	 
	});
	
};


/**-----------------------------sizeImg==E---------------------------------------**/

/**-----------------------------equalH==S---------------------------------------**/
/**
**case:equalH("#ssss,.dddd");
**
**/
function equalH(paramstr){
 var dd=paramstr.split(',');
 var div=[];
 var height=0;
 for(var i=0;i<dd.length;i++){
    div[i]=$(""+$.trim(dd[i]));
    if(div[i].height()>height){height=div[i].height();}
  }
 $(div).each(function(){$(this).height(height);});
}
/**-----------------------------equalH==E---------------------------------------**/

/**-----------------------------closeWindow==S---------------------------------------**/
/**
**case:closeWindow();
**discription:close the window without Warnings
**/
function closeWindow(){window.opener=null;window.open('','_self');window.close();}
/**-----------------------------closeWindow==E---------------------------------------**/

/**-----------------------------showDate==S---------------------------------------**/
/**
**case:
** $("#date1").showDate({
     id:"lmzdate1",
    language:"english",
	formate:"Y M D,hh:mm:ss,W"
 });
**
**/




jQuery.fn.showDate=function(options){


 var opts=$.extend({},{
    id:"lmzdate",
    language:"chinese",
	formate:"Y年M月D日,hh:mm:ss,W", //Y：年 ，M：月， D：日 ，hh:时，mm：分 ，ss:秒，W 星期
    monthen:["January","February","March","April","May","June","July","August","September","October","November","December"],
    dayen:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],
    monthch:["1","2","3","4","5","6","7","8","9","10","11","12"],
    daych:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"]
  },options||{});
  var obj=$(this);

  opts.getLMZDate=function(){
	var tt=opts.formate;
	var d=new Date();
	
	var month=[];
    var day=[];
	
    if(opts.language=="english"){
      month=opts.monthen;day=opts.dayen; 
    }else{
      month=opts.monthch;day=opts.daych;
    }
	
	tt=tt.replace(/Y/g,d.getYear());
	tt=tt.replace(/M/g,month[d.getMonth()]);
	tt=tt.replace(/D/g,d.getDate());
	tt=tt.replace(/hh/g,d.getHours());
	tt=tt.replace(/mm/g,d.getMinutes());
	tt=tt.replace(/ss/g,d.getSeconds());
	tt=tt.replace(/W/g,day[d.getDay()]);

    return tt;
   };
   
  opts.init=function(){
    obj.append("<span id='"+opts.id+"'></span>");
    var tt=setInterval(function(){$("#"+opts.id).html(opts.getLMZDate());},1000);
  };
  opts.init();
  
  
};


/**-----------------------------showDate==E---------------------------------------**/


/**-----------------------------switchBar==S---------------------------------------**/

/**
**
** 
 $("#switch_s span").switchBar({
     changeObj:$("#switch_inner div"),
	 barOnCss:{},
	 barOutCss:{},
	 objOnCss:{},
	 objOutCss:{}
  });
**/

jQuery.fn.switchBar=function(options){      //触发对象
  var opts=$.extend({},{
     event:"click",          //事件 click  ,mouseover 
     changeObj:null,           //需要改变的对象
	 barOnCss:{},                       //触发的对象   触发时样式  都是json格式 例如 {color:"red",border:"1px solid red"}
	 barOutCss:{},                      //触发的对象   触发结束样式
	 objOnCss:{},                         //改变的对象   事件触发样式
	 objOutCss:{},                        //改变的对象事件 事件离开样式
     isHide:true,                        //是否隐藏对象
	 eventFun:function(thisBar,thisObj){}          //触发事件时调用函数，thisBar触发对象，thisObj改变的对象	 
	 
  },options||{});
  var oo=this;
  var obj=$(this);
  var cObj=opts.changeObj;
  
  obj.bind(opts.event,function(){
     var index=obj.index($(this));
	 var thisObj=cObj.eq(index);
	 if(opts.isHide){
	   cObj.hide();
	   thisObj.show();
	  }
	 obj.css(opts.barOutCss);
	 $(this).css(opts.barOnCss);
	 cObj.css(opts.objOutCss);
	 thisObj.css(opts.objOnCss);	 
	  
	 opts.eventFun($(this),cObj.eq(index));
	 
  });
   
};

/**-----------------------------switchBar==E---------------------------------------**/





/**------------------------floatQQ==S------------------------------------**/
/**
**version:2.5;
**floatQQ({options});
**case:
 floatQQ({
   qq:"客服1:281247137,客服2:234234234,客服3:38945334",
   left:0,
   top:200,
   topbg:'Template/images/qq_top2.gif',
   middlebg:'Template/images/qq_middle2.gif',
   bottombg:'Template/images/qq_bottom2.gif',
   closecolor:'red'
 });
**
**interface:
**http://wpasig.qq.com/msgrd?v=1&uin=1455647316&site=qq&menu=yes

** docO scrollO lmzDrag 
**/

/*---------------------------------------------------------------------*/

function floatQQ(options){
  var options=$.extend({},{
    hoverable:true, 
    moveable:true,
    closeable:true,
    floatable:true,
    width:120,
    topbg:'/Template/images/qq_top2.gif',
    middlebg:'/Template/images/qq_middle2.gif',
    bottombg:'/Template/images/qq_bottom2.gif',
    topheight:76,
    hoverouth:56,
    bottomheight:16,
	pageW:1003,
    closecolor:'#FF955D',
    left:0,
    top:200,
    qqtype:16,
    id:'floatbar',
    qq:''
  },options);
  
  this.init=function(options){
	 var qqHTML=QQ.init(options);
	 $("body").append(qqHTML);
	 
     var obj=$("#"+options.id);
     var FLOATTOP=options.top;
     var QQT=$("#"+options.id+"top");
     var QQM=$("#"+options.id+"middle");
     var QQB=$("#"+options.id+"bottom");
     var QQclose=$("#"+options.id+"close");


    if(options.hoverable){
       QQT.height(options.hoverouth);
       QQM.hide();
       QQB.hide();
       obj.hover(function(){
         QQT.height(options.topheight);QQM.show();QQB.show();
       },function(){
         QQT.height(options.hoverouth);QQM.hide();QQB.hide();
       });

     }

    if(options.moveable){
      lmzDrag(obj,QQT,{
	    onMove:function(o,bar,p){
		  FLOATTOP=p.y-lmz.scrollO.tt();		 
	    }	  
      }); 

     }

     if(options.closeable){
       if(QQclose){
         QQclose.bind('click mousedown',function(){obj.remove();});
       }
     }

     if(options.floatable){
        var hh=lmz.docO.hh;
        obj.css({visibility:"visible",left:(window.screen.width-15-options.pageW)/2+options.left,top:FLOATTOP});
        $(window).scroll(function(){
	       var ttp=$(this).scrollTop()+FLOATTOP;
	       if(ttp+obj.height()>hh){ttp=hh-obj.height();}
           obj.css({top:ttp});
        });
      }	  
  };

  //qqO
  var QQ={

	init:function(o){	
       var qq_str=o.qq.split(',');
       var qqht=[];
       for(var i=0;i<qq_str.length;i++){
         var qq_in=qq_str[i].split(':');
         qqht[i]=this.qqTmp(qq_in[1],qq_in[0],o.qqtype);
       }
       var html=this.floatTmp(o,qqht);
       return html;	
	},
	
	floatTmp:function(o,qqht){
       var closecss="display:block;width:40px;height:20px;line-height:20px;margin-right:5px;text-decoration:none;";
	   closecss+="padding:0px;margin-top:2px;font-size:12px;font-weight:bold;float:right;";
       var ht='';
       ht+='<div id="'+o.id+'" style="position:absolute;width:'+o.width+'px;top:'+o.top+'px;">';
	   ht+=   '<table  border="0" cellspacing="0" cellpadding="0" width="'+o.width+'px">';
       ht+=     '<tr id="'+o.id+'top" height="'+o.topheight+'px">';
       ht+=       '<td style="width:'+o.width+'px;background:url('+o.topbg+') no-repeat top;text-align:right;line-height:20px;vertical-align:top;">';
	   ht+=         '<a id="'+o.id+'close" style="'+closecss+'color:'+o.closecolor+';" href="#">关闭</a>';
	   ht+=       '</td>';
	   ht+=     '</tr>';
	   ht+=     '<tr>';
	   ht+=        '<td id="'+o.id+'middle" style="background:url('+o.middlebg+')  repeat-y;"> ';
       ht+=           '<table class="QQmiddletb" style="width:82%;filter: alpha(opacity=100);" border="0"; cellspacing="0" cellpadding="0"> '; 
                         for(var i=0;i<qqht.length;i++){
							 ht+=qqht[i];
						 }
       ht+=           '</table>';
	   ht+=         '</td>';
	   ht+=      '</tr>';
	   ht+=      '<tr>';
	   ht+=         '<td id="'+o.id+'bottom" style="heigh:'+o.bottomheight+'px;background:url('+o.bottombg+') no-repeat top;"> ';
       ht+=           '&nbsp;';
	   ht+=         '</td>';
	   ht+=       '</tr>';
	   ht+=    '</table>';
	   ht+='</div>';
	   return ht;
	},
	qqTmp:function(qqnum,qqtxt,qqtype){
      var qqht='';
      qqht+='<tr style="padding-bottom:2px;">';
	  qqht+=  '<td style="padding-left:0px;text-align:left;">';
	  qqht+=    '<a style="display:block;height:16px;width:24px;line-height:16px;background-postion:right;" target="_blank" title="交谈" ';
      qqht+=    'href=\"http://wpasig.qq.com/msgrd?V=1&Uin='+qqnum+'&Site=QQ&Menu=yes\" style=\"';
      qqht+=    'background-image:url(\'http://wpa.qq.com/pa?p=1:'+qqnum+':'+qqtype+'\')\">';
      qqht+=    '</a>';
	  qqht+=  '</td>';
	  qqht+=  '<td>';
	  qqht+=    '<a style="text-decoration:none;font-size:12px;color:black;" target="blank" ';
      qqht+=    'href=\"http://wpasig.qq.com/msgrd?V=1&Uin='+qqnum+'&Site=QQ&Menu=yes\"> ';
      qqht+=      qqtxt;
	  qqht+=    '</a>';
	  qqht+=   '</td>';
	  qqht+='</tr>';
      return qqht;		
	}
	
  };
  
  this.init(options);


}
/**------------------------floatQQ==E------------------------------------**/



/**---------------------------floatAD==S----------------------------------------**/
/**
**version:2.5;
**floatAD({options});
**case:
 floatAD({
   url:"ad.html",
   left:0,
   top:200,
   centercss:"background:#ccc;height:100px;padding:0px;"
 });
//or
 floatAD({text:"内容 内容"});
**
**/
/*------------------------------------------------------------------------*/

function floatAD(options){

var options=$.extend({},{
   id:"floatAD",
   left:20,
   top:200,
   width:200,
   css:"border:1px solid #666; ",
   topheight:20,
   title:"公告:",
   titlecss:"font-size:14px;font-weight:bold;color:red;",
   topcss:"background:#666; ",
   closecolor:"#000",
   centercss:"background:#ccc;height:100px;",
   url:"",
   pageW:1003,
   frameh:100,
   text:"内容",
   hoverable:true,
   closeable:true,
   floatable:true,
   moveable:true
},options);

  this.init=function(options){
	  var adHTML=AD.init(options);
	  $("body").append(adHTML);
	  
      var obj=$("#"+options.id);
      var FLOATTOP=options.top;
      var ADtop=$("#"+options.id+"top");
      var ADinner=$("#"+options.id+"inner");
      var ADclose=$("#"+options.id+"close");
	  
      if(options.hoverable){
        ADinner.hide();
        obj.hover(function(){ADinner.show();},function(){ADinner.hide();});
       }

       if(options.closeable){
         ADclose.bind('click mousedown',function(){obj.hide();});
       }

       if(options.floatable){
          var hh=lmz.docO.hh;
          obj.css({visibility:"visible",left:(window.screen.width-15-options.pageW)/2+options.left,top:FLOATTOP});
          $(window).scroll(function(){
	         var ttp=$(this).scrollTop()+FLOATTOP;
	         if(ttp+obj.height()>hh){ttp=hh-obj.height();}
             obj.css({top:ttp});
          });
        }

       if(options.moveable){
          lmzDrag(obj,ADtop,{
	        onMove:function(o,bar,p){
		    FLOATTOP=p.y-lmz.scrollO.tt();		 
	       }	  
           });  
       }	  
  };



  var AD={

	init:function(o){	
       return this.floatTmp(o);
	},
	
	floatTmp:function(o){
		var closecss="display:block;width:30px;height:20px;right:2px;line-height:20px;text-decoration:none;padding:0px;top:1px;font-size:12px;";
		var aht='';
		aht+='<div id="'+o.id+'" style="position:absolute;width:'+o.width+'px;top:'+o.top+'px; '+o.css+'">';
        aht+=    '<div style="height:'+o.topheight+'px;width:'+o.width+'px;position:relative;';
		aht+=     'line-height:'+o.topheight+'px;'+o.topcss +'" id="'+o.id+'top">';
	    aht+=      '<span style="'+o.titlecss+'">'+o.title+'</span>';
        aht+=      '<a id="'+o.id+'close" style="'+closecss+'font-weight:bold;color:'+o.closecolor+';position:absolute;" href="#"> ';
        aht+=         '关闭';
		aht+=      '</a>';
		aht+=    '</div>';
		aht+=    '<div style="width:'+(parseInt(o.width)-10)+'px;padding:5px;height:auto;overflow:hidden;'+o.centercss+'" id="'+o.id+'inner">';
        if(o.url!=""){
          aht+=     '<iframe src="'+o.url+'" style="height:'+o.frameh+';width:'+o.width+'" frameborder="0" scrolling="no" style="overflow:hidden;">';          aht+=     '</iframe> ';
        }else{
          aht+=o.text;
        }
        aht+=    '</div>';
		aht+='</div> ';
        return aht;
	}

	
  };
  
  
  this.init(options);

}


/**-----------------------------floatAD==E---------------------------------------**/



/**-----------------------------hoverImg==S---------------------------------------**/
//用到的对象或方法  mouseO  xyO docO  sizeImg



jQuery.fn.hoverImg=function(options){
	
  var objI=$(this);
  var oo=this;
  var opts=$.extend({},{
       id:"imgshowBox",
       event:"hover",             //hover  click
	 
       width:"auto",              //约束宽度
       height:"auto",             //约束高度
	   
	   position:"center",         //中心点 center：屏幕中间,auto:与触发图片中心点一致 ,
       left:0,                    //中心点位置， left 为左右偏移量，top 上下偏移量
       top:0,
	   
	   
       ishref:false,//是否给弹出的图片加链接，且链接为触发图片的链接。
	   isreposition:true,          //是否要超出屏幕自动调整
	   
	   leftBtnsrc:"images/hoverImg/leftBtn.png",
	   rightBtnsrc:"images/hoverImg/rightBtn.png",
	   
	   boxcss:"",                 //显示框样式
	   closeBtnsrc:"",
	   leftcss:"",
	   rightcss:"",
	   closecss:"",
	   imgcss:"",
	   type:0                //0： 只显示图片 无左右按钮功能  ，1： 有按钮功能

  },options||{});

 
  var imgobjf;
  var leftBtn;
  var rightBtn;
  var closeBtn;
  var img;
  
  var index;
  var htt;
  var len;
  
  var cpoint;



  this.init=function(){
     oo.initshowf();
     oo.initData();
	 oo.bindEvent();
  };
  
  this.initData=function(){
     img.css({"height":opts.height,"width":opts.width});
	 len=objI.length;
  };
 
  this.bindEvent=function(){

   if(opts.event!="click"){
	   
    objI.hover(function(){
      if(htt){clearTimeout(htt);}
	  index=objI.index($(this)); 
	  oo.goto(index);
     },function(){
       htt=setTimeout(function(){imgobjf.hide();},500);
    });
  
    imgobjf.hover(function(){
        if(htt){clearTimeout(htt);}
	    $(this).show();
	 },function(){
		$(this).hide();
		if(rightBtn){rightBtn.hide();}
		if(leftBtn){leftBtn.hide();}
    });
	
   }else{
	  $('body').not(objI).click(function(){
		     imgobjf.hide();	
		});
	    objI.click(function(){
	       index=objI.index($(this)); 
	       oo.goto(index);
		   return false;
	     });		
	     imgobjf.click(function(){
		   imgobjf.show();	
		   return false;
	     });	  
   }

    if(leftBtn&&rightBtn){
		
     imgobjf.bind('mousemove',function(e){
		var x=lmz.mouseO.position(e).x;           //mouseO 获取鼠标left

		if(x>cpoint.x&&x<cpoint.x+imgobjf.width()/2){
			rightBtn.show();
		    if(index>=len-1){rightBtn.hide();}
			leftBtn.hide();
	    }else if(x<cpoint.x&&x>cpoint.x-imgobjf.width()/2){
			leftBtn.show();
		    if(index<=0){leftBtn.hide();}		
		    rightBtn.hide();
	    }																	
      });
	 leftBtn.bind('click',function(){oo.showPrev();oo.goto(index);});
	 rightBtn.bind('click',function(){oo.showNext();oo.goto(index);});
    }	

  };
  
  
  this.initshowf=function(){
	  
    var boxcss="width:auto;height:auto;position:absolute;z-index:1000;padding:5px;";
        boxcss+="background:#fff;border:2px solid #ccc;top:-1000px;left:-1000px;display:none;";
    var leftcss="position:absolute;z-index:1200;left:5px;top:80px;width:80px;display:none;";
	var rightcss="position:absolute;z-index:1200;right:5px;top:80px;width:80px;display:none;";
	var closecss="position:absolute;z-index:1200;right:-5px;top:0px;";
	var imgcss="position:relative;";
	
  var boxhtml='<div id="'+opts.id+'" style="'+boxcss+"width:"+opts.width+";height:"+opts.height+";"+opts.boxcss+';" >';
	  boxhtml+='<img id="'+opts.id+'img" style="'+imgcss+opts.imgcss+'" src="" />';
	  
	 if(opts.type!=0){
	  if(opts.leftBtnsrc!=""){
	    boxhtml+='<img style="'+leftcss+opts.leftcss+'" id="'+opts.id+'leftBtn" src="'+opts.leftBtnsrc+'" />';
	  }
	  if(opts.rightBtnsrc!=""){
	    boxhtml+='<img style="'+rightcss+opts.rightcss+'" id="'+opts.id+'rightBtn" src="'+opts.rightBtnsrc+'" />';
	  }
	  if(opts.closeBtnsrc!=""){
	    boxhtml+='<img style="'+closecss+opts.closecss+'" id="'+opts.id+'closeBtn" src="'+opts.closeBtnsrc+'" />';
	  }
	 }
	 
	  boxhtml+='</div>' ;

    $("body").append(boxhtml);
	
	imgobjf=$("#"+opts.id);
    leftBtn=$("#"+opts.id+"leftBtn");

    rightBtn=$("#"+opts.id+"rightBtn");
    closeBtn=$("#"+opts.id+"closeBtn");
    img=$("#"+opts.id+"img");
  };
  

  this.setCpoint=function(o){
	  
	 if(opts.position!="center"){
	    var x=o.offset().left+o.width()/2+opts.left;
	    var y=o.offset().top+o.height()/2+opts.top;
	    cpoint=new lmz.xyO.init(x,y);	  		 
	 }else{	  
		cpoint=new lmz.xyO.init(lmz.bodyO.ww/2+lmz.scrollO.ll()+opts.left,lmz.bodyO.hh/2+lmz.scrollO.tt()+opts.top);	
	 }
    
  };
  
  this.sizeImg=function(){
	if(opts.width!="auto"&&opts.height!="auto"){
      img.css({height:"auto",width:"auto"});
      img.sizeImg(opts.width,opts.height); //等比例缩放
	}    
  };
  
  this.goto=function(ii){
	var thisimg=objI.eq(ii);
    img.remove();
    imgobjf.append('<img id="'+opts.id+'img" src="'+thisimg.attr("src")+'"/>');
	img=$("#"+opts.id+"img");
    imgobjf.show();

	 oo.sizeImg();	

	 oo.setCpoint(thisimg);


	  var xy=oo.getXYO();
      if(opts.isreposition){
        xy=oo.rePosition(xy);
	  }	  
	oo.setPosition(xy);
    this.setBtnP();
	closeBtn.show();

     if(opts.ishref){
        oo.bindHref(thisimg.parents("a").eq(0).attr('href'));
     }	
	
  };
  
  this.showPrev=function(){	  
     if(index>0){
	   index=index-1;	 
	 }else{
	   index=len-1;
	 }
  };
  
  this.showNext=function(){
	if(index<len-1){
	   index=index+1;	
	 } else{
		index=0;
	} 
  };
  
  this.rePosition=function(xy){
        if(xy.x+imgobjf.width()>window.screen.width-20){xy.x=window.screen.width-imgobjf.width()-20;}
        if(xy.x<0){xy.x=0;}
        if(xy.y<0){xy.y=0;}
		return xy;
  };
  
  this.getXYO=function(){
	  var x=cpoint.x-imgobjf.width()/2;
	  var y=cpoint.y-imgobjf.height()/2;

	  return new lmz.xyO.init(x,y);
  };
  
  this.setPosition=function(xy){
	  imgobjf.css({left:xy.x,top:xy.y});
  };
  
  this.setBtnP=function(){
    if(leftBtn){leftBtn.css({top:(imgobjf.height()-leftBtn.height())/2});}
    if(rightBtn){rightBtn.css({top:(imgobjf.height()-rightBtn.height())/2});}	  
  };
  
  this.bindHref=function(href){
    img.bind('click',function(){window.location.href=href});	  	  
  };
  
   
   oo.init();

};



/**-----------------------------hoverImg==E---------------------------------------**/


/*******************************LfocusImg===S**********************************/

jQuery.fn.LfocusImg=function(options){
  var opts=$.extend({},{
	     id:"cccc",
		 direct:"left",
		 len:0.15,
		 delay:100,
		 timeout:4000,
		 width:1003,
		 height:262,
		 hasBtn:true,
		 btnPosition:{right:"5px",bottom:"2px"},
		 btnSizeCss:{width:"20px",height:"20px",lineHeight:"20px",marginLeft:"2px"},
		 btnCss:{background:"#666666",color:"#cccccc",fontFamily:"Verdana",fontSize:"12px",opacity:0.8},
		 btnCurrentCss:{background:"#cc0000",color:"#000000"}
	  
	
	},options||{});
  
  var oo=this;
  var obj=$(this);
  
  var Jpicli;
  var Jbtnli;
  
  var tt;
  var ttn;

  var type=(opts.direct=="right" || opts.direct=="down")? 1:-1;
  var DD= (opts.direct=="left" || opts.direct=="right") ? true:false;
  
  var WW= DD ? opts.width:opts.height;
  var L=WW*opts.len*type;
  
  var N=1/opts.len;
  var nn=0;
  
  var fii=0;
  var ii=0;

  
  this.init=function(){
      if(obj.length==0){return false;}
	  oo.initImg();
	  if(opts.hasBtn){
		  oo.addBtn();
		  oo.bindBtnClick();
	   }

	  Jpicli.eq(0).show();
	  Jpicli.find('img').css({"width":opts.width+"px","height":opts.height+"px"});
	  oo.addCurrentCss();
	  fii=Jpicli.length-1;
	  oo.startMove();

   };
   
   this.initImg=function(){
	  Jpicli=obj;
	  
	  var objulcss={width:opts.width+'px',height:opts.height+'px',position:"relative",overflow:"hidden",margin:"0px",padding:"0px"};
	  var objlicss={width:opts.width+'px',height:opts.height+'px',position:"absolute",overflow:"hidden",margin:"0px",padding:"0px",display:"none"};
	  var objimgcss={width:opts.width+'px',height:opts.height+'px',margin:"0px",border:"none",position:'static'};
      obj.parent().css(objulcss);
	  
	  Jpicli.css(objlicss);
	  Jpicli.find('img').css(objimgcss);
   };
  
   this.addBtn=function(){
	 var btn='';

	 btn='<div id="'+opts.id+'btn" style="position:absolute;z-index:2000;right:5px;bottom:2px;"> ';
	 btn+='<ul class="'+opts.id+'btnul" style="margin:0px;padding:0px;" >';
      for(var i=0;i<Jpicli.length;i++){
          btn+='<li>'+(i+1)+'</li>';
       }	 		   		   
	  btn+='</ul></div>';	 
      obj.parent().append(btn);
	  Jbtnli=$("#"+opts.id+"btn li");
	  Jbtnli.find('a').css({border:'1px solid black',display:'inline',padding:'0px',margin:'0px'});
	  var btnlicss={position:"static",float:"left",display:"inline",textAlign:"center",
	  cursor:"pointer",fontWeight:"bold",width:"16px",height:"16px",lineHeight:"16px",marginLeft:"3px"};
	  
	  $("#"+opts.id+"btn").css(opts.btnPosition);

	  Jbtnli.css(btnlicss);
	  Jbtnli.css(opts.btnSizeCss);
	  Jbtnli.css(opts.btnCss);
  
	};
	
	this.bindBtnClick=function(){
	  Jbtnli.bind('click',function(){
		 if(ttn){clearTimeout(ttn);}	 
		 oo.endMove();
		 oo.getNextII(parseInt($(this).html())-1);
		 oo.movePic();
	  });	
		
	};
	
	
	
	this.startMove=function(){
		 ttn=setTimeout(function(){
		   oo.getNextII(-1);
		   oo.movePic();
		  },opts.timeout);						 
     };
	
	
	this.movePic=function(){
	  var e1=Jpicli.eq(fii);
	  var e2=Jpicli.eq(ii);
	  oo.initMove(e1,e2);
	  
	 tt=setInterval(function(){
		oo.moveFun(e1);
		oo.moveFun(e2);

		if(nn<N-2){nn++;}else{
            oo.endMove();
			oo.startMove();
		   }
		},opts.delay);
       
	};
	
	this.endMove=function(){
		 if(tt){clearInterval(tt);}	
		 nn=0;
		 oo.toZP(Jpicli.eq(ii));
		 Jpicli.eq(fii).hide();  	
	};
	
	this.initMove=function(e1,e2){
		e1.show();
		e2.show();
		e2.find('img').css({width:opts.width+'px',height:opts.height+'px',margin:'0px'});
		oo.toZP(e1);
		oo.setStartP(e2,opts.direct);
		oo.addCurrentCss();
	};
	
	this.setStartP=function(e,d){
		if(DD){
		  e.css({left:-WW*type,top:0});
		}else{
		  e.css({left:0,top:-WW*type});
		}
	};
	
	
	this.moveFun=function(e){
	  var dir=DD? "left":"top";
	  var sp=parseInt(e.css(dir).replace('px',''));
	  e.css(dir,sp+L);	  
	};
	
	this.toZP=function(e){
	  	e.css({left:"0px",top:"0px"});
	};

	this.getNextII=function(nii){
	   if(nii!=ii){fii=ii;}
	   if(nii!=-1){ii=nii;}else{
	    (ii<Jpicli.length-1) ? ii=ii+1 : ii=0;
	   }
	};
	
	this.addCurrentCss=function(){
	  if(Jbtnli){
	  Jbtnli.css(opts.btnCss);
	  Jbtnli.eq(ii).css(opts.btnCurrentCss);
	  }
	  
	};

	
	this.init();
   
};


/*******************************focusImg1===E**********************************/






/**-----------------------------LMarquee==S-----------------------------------------------**/

/**
case: 
注意： $('#id')必须在样式里有宽度 高度
$('#id').LMarquee({ 
loop: 0,//循环滚动次数，0时无限 
direction: 'left',//滚动方向，'left','right','up','down' 
scrollAmount:1,//步长 
leftBtn:null, 
rightBtn:null, 
topBtn:null, 
bottomBtn:null, 
autoMove:false, //false : 不自动运动 ，"type1" : 间歇性运动， 其他 ： 匀速运动.
btnL:0.1, //按钮点击，每单次运动的步长 占图片宽度或高度的百分比 
btnN:10, //按钮点击，运动的次数 
btnDelay:10, //按钮点击，每单次运动时长 
btnDirect:false, //点击按钮后是否根据按钮改变方向 
scrollDelay:10//时长 
}); 

**/


jQuery.fn.LMarquee = function(options){
		var o=this;
		var opts = $.extend({},{

		loop: 0,//循环滚动次数，0时无限
		direction: 'left',//滚动方向，'left','right','up','down' 
		scrollAmount:1,//步长
		leftBtn:null,
		rightBtn:null,
		topBtn:null,
		bottomBtn:null,
		autoMove:false,  //false : 不自动运动 ，"type1" : 间歇性运动， 其他 ： 匀速运动. 
		btnL:0.1,    //按钮点击，每单次运动的步长 占图片宽度或高度的百分比
		btnN:10,     //按钮点击，运动的次数 
		btnDelay:10,    //按钮点击，每单次运动时长
		btnDirect:false,  //点击按钮后是否根据按钮改变方向
		scrollDelay:10//时长
	   }, options||{});

	   var marqueeO = $(this);//滚动元素容器
	   var _scrollObj = marqueeO.get(0);//滚动元素容器DOM
	   var scrollW = marqueeO.width();//滚动元素容器的宽度
	   var scrollH = marqueeO.height();//滚动元素容器的高度
	   var Lelement = marqueeO.children(); //滚动元素
	   var Lkids = Lelement.children();//滚动子元素

	   if(!Lkids||Lkids.length==0){return false;}
	   var scrollSize=0;//滚动元素尺寸		
	   var typeM="type1";
	   var _type = (opts.direction == 'left' || opts.direction == 'right') ? 1:0;//滚动类型，1左右，0上下
	   
	   var numMoved = 0;
	   var moveId;
	   var moveId2;
	   var inMove=false;  //是否在运动中 防止重复点击
	   var isBtnM=false;  //是否是鼠标点击的运动 
	   
	   //间歇性运动计算
	   var len=_type ? Lkids.width() : Lkids.height();    //每个单元的宽度或高度
       var nn=1/opts.btnL;                
       var zz=opts.btnN/nn;                 //运动次数
       var maxlen=zz*len;                   //总运动长度
       var yy=maxlen%opts.btnN;              //最后一次运动的量
       var ll=Math.floor(maxlen/opts.btnN);  //前 opts.btnN 次每次运动 的量	        
	
	
	   this.init=function(){
	     marqueeO.css({"position":"relative",overflow:"hidden"});
            Lelement.css({"position":"relative",margin:"0",padding:"0","list-style":"none"});
            Lkids.css({margin:"0",padding:"0","position":"relative"});
		   if(_type){
		      scrollSize=Lkids.length*(Lkids.width());
		      Lelement.css({"width" : 2*scrollSize+"px","height" : Lkids.height()+"px"});
		   }else{
		      scrollSize=Lkids.length*(Lkids.height());
		      Lelement.css({"height" : 2*scrollSize+"px","width" : Lkids.width()+"px"});		
		    }
			Lelement.css(_type?'width':'height',Lkids.length*Lkids.width());
			if (scrollSize<(_type?scrollW:scrollH)) return false; 
			//克隆滚动子元素将其插入到滚动元素后，并设定滚动元素宽度
			Lelement.append(Lkids.clone()).css(_type?'width':'height',scrollSize*2);
			return true;
	   };
	   
	   this.moveFun=function(amout,dir){
	       var _dir = (dir == 'left' || dir == 'right') ? 'scrollLeft':'scrollTop';
				if (opts.loop > 0) {
					numMoved+=amout;
					if(numMoved>scrollSize*opts.loop){
						_scrollObj[_dir] = 0;
						if(typeM!=opts.autoMove){
						  return clearInterval(moveId);
						}else{
						  return clearInterval(moveId1);
						}
					} 
				}

				if(dir == 'left' || dir == 'up'){
					var newPos = _scrollObj[_dir] + amout;
					if(newPos>=scrollSize){
						newPos -= scrollSize;
					}
					_scrollObj[_dir] = newPos;
				}else{
					var newPos = _scrollObj[_dir] - amout;
					if(newPos<=0){
						newPos += scrollSize;
					}
					_scrollObj[_dir] = newPos;
				}
	   
	   };
	   
	   
	  this.hoverFun=function(Lo){

		Lo.hover(function(){
		     if(moveId){clearInterval(moveId);}			
		   },
		    function(){
			  if(moveId){clearInterval(moveId);}
			  if(opts.autoMove!=typeM){
			     moveId = setInterval(function(){o.moveFun(opts.scrollAmount,opts.direction);}, opts.scrollDelay);
			  }else{     
				 moveId = setInterval(function(){o.type1Move(ll,yy,opts.direction);}, opts.scrollDelay);
			  }	
		 });
	
	   };
		
	 //间歇性运动 ll总长度 yy最后一次长度 dir 方向
	  this.type1Move=function(ll,yy,dir){
			var v=1;
			inMove=true;
		    if(moveId2){clearInterval(moveId2);}
		    moveId2 = setInterval(function(){
				o.moveFun(ll,dir);
				v+=1;
				if(v>opts.btnN){              //达到次数，结束运动 最后一次运动完剩下的部分
                    o.moveFun(Math.round(yy),dir);
					if(moveId2){clearInterval(moveId2);inMove=false;}
				}	
			}, opts.btnDelay);	
	  };
	  
	  this.btnBind=function(btn,dir){
		if(btn){				  
		   btn.bind('click',function(){
		      if(inMove){return false;}
			  if(opts.btnDirect){opts.direction=dir;}  //是否能够改变方向
              o.type1Move(ll,yy,dir);
		  });	
		 }						
	   };
	  
	  this.moveIMG=function(){
         if(!o.init()){return false;}
         if(opts.autoMove){
			
			 if(opts.autoMove!=typeM){
			    moveId = setInterval(function(){o.moveFun(opts.scrollAmount,opts.direction);}, opts.scrollDelay);
			  }else{     
				moveId = setInterval(function(){o.type1Move(ll,yy,opts.direction);}, opts.scrollDelay);
			  }	
			  
			  o.hoverFun(marqueeO);
			  if(opts.leftBtn){o.hoverFun(opts.leftBtn);}			  
			  if(opts.rightBtn){o.hoverFun(opts.rightBtn);}		
			  if(opts.topBtn){o.hoverFun(opts.topBtn);}			  
			  if(opts.bottomBtn){o.hoverFun(opts.bottomBtn);}			  		  
		}
			o.btnBind(opts.leftBtn,"left");
			o.btnBind(opts.rightBtn,"right");
			o.btnBind(opts.topBtn,"up");
			o.btnBind(opts.bottomBtn,"down");			
	 };
	 

	 
	 this.moveIMG();
	
};


/**-----------------------------LMarquee==E---------------------------------------**/




/**-----------------------------viewimg==S---------------------------------------**/
/***
*version:1.5
*case:
      $("#smallimgbox").viewIMG({
            boxwidth:400,
            boxheight:400,
            boxleft:300,
            boxtop:0,
            viewbili:5,
            bigimgsrc:'Template/images/111big.jpg'
       });
*
**/
jQuery.fn.viewIMG=function(options){

//用到的其它类或方法: scrollO

  var options=$.extend({},{
     boxid:"bigimgbox",
     boxwidth:400,
     boxheight:400,
     boxleft:300,
     boxtop:0,
     boxcss:"border:1px solid #ccc;",
     viewid:"viewbox",
     viewbili:5,
     viewcss:"border:1px solid blue;",
     bigimgsrc:""

   },options);


  var imgbox=new imgBOX_viewIMG(options);


 $(this).hover(function(){
   var obj=$(this);
   var smallbox=obj;
   var smallimg=smallbox.find('img');
   var imgbox=$("#"+options.boxid);
   var bigimg=imgbox.find('img');

   obj.css({position:"relative",overflow:"hidden"});
   obj.addVIEW_viewIMG(options);
   var view=$("#"+options.viewid);
   obj.initBOX_viewIMG(options.boxid,options.boxleft,options.bigimgsrc,options.boxtop);

   $(this).bind('mousemove',function(event){
     var leftLen=event.clientX-obj.offset().left;
     var topLen=event.clientY-obj.offset().top;

     var aa=leftLen-(view.width()/2)+lmz.scrollO.ll();
     var bb=topLen-(view.height()/2)+lmz.scrollO.tt();

     view.css({left:aa,top:bb});
     view.show();
     imgbox.show();

     var bhh=view.position().top/smallimg.height();
     var bww=view.position().left/smallimg.width();
     var left=-bigimg.width()*bww;
     var top=-bigimg.height()*bhh;
     bigimg.css({left:left,top:top});

  });

 },function(){
   var smallbox=$(this);
   var imgbox=$("#"+options.boxid);
   var view=$("#"+options.viewid);
   view.remove();
   imgbox.hide();
   smallbox.unbind('mousemove');
 });

};

 jQuery.fn.addVIEW_viewIMG=function(options){
   this.id=options.viewid;
   this.css=options.viewcss;
   this.width=options.boxwidth/options.viewbili;
   this.height=options.boxheight/options.viewbili;
   var obj=$(this);
   var viewHTML='';
   viewHTML+='<div id="'+this.id+'" style="width:'+this.width+'px;height:'+this.height+'px; ';
   viewHTML+='position:absolute; '+this.css+'"></div>';
   obj.append(viewHTML);
 };


 var imgBOX_viewIMG=function(options){
   this.id=options.boxid;
   this.width=options.boxwidth;
   this.height=options.boxheight;
   this.css=options.boxcss;

   var box='';
   box+='<div id="'+this.id+'" style="position:absolute;display:none;';
   box+='overflow:hidden;width:'+this.width+'px;height:'+this.height+'px; '+this.css+' ">' ;
   box+='<img src="" style="position:absolute;"/></div>';
   $("body").append(box);
   return $("#"+this.id);
 };


 jQuery.fn.initBOX_viewIMG=function(boxid,len,src,tt){
   var obj=$(this);
   var ll=obj.position().left;
   var top=obj.position().top+tt;
   var left=ll+len+obj.width();
   if(src==""){
    //  src=obj.find('img').attr('src');
    //  var bb=src.split('.');
    //  if(bb.length>1){src=bb[0]+"big."+bb[1];}
    src=obj.find('img').attr('src');

   }

   $("#"+boxid).css({left:left,top:top});
   $("#"+boxid).find('img').attr('src',src);
};



/**-----------------------------viewimg==E---------------------------------------**/


/**-----------------------------showDailog==S---------------------------------------**/

/*

 var o=$.extend({},{
    id:"floatbox",
    width:510,
    title:'<div id="boxtopbar">用户登录</div><div id="boxtopbar1"><span id="tologin" class="spanon">登录</span> <span id="toreg" class="spanout">注册</span></div>',
    titlecss:'height:80px;line-height:29px;text-align:center;width:100%;font-size:14px;',
    closecss:'color:#fff;right:2px;font-size:14px;',
    css:"border:0px;",
    url:"/loginBox.jsp",
	bindFun:function(jframe){

		$("#tologin").bind('click',function(){
		  $("#toreg").attr('class','spanout');
		  $(this).attr('class','spanon');
		  jframe.attr('src','/loginBox.jsp');
		  //jframe.animate({height:'130px'},500);
		});
		$("#toreg").bind('click',function(){
		  $("#tologin").attr('class','spanout');
		  $(this).attr('class','spanon');
		  jframe.attr('src','/registerBox.jsp');
		 // jframe.animate({height:'175px'},500);
		});	 
	}  
  
  },oo);

  showDailog(o);


*/



function showDailog(op){
  var _this=this;

//用到的类或方法：docO
  var o=$.extend({},{
    btn:null,
    id:"floatpo",    /*弹出框id    */
    width:400,       /*弹出框宽度  */
    height:"auto",      /*弹出框高度  */
    left:"auto",        /*弹出宽初始左边距  auto时取屏幕中间*/
    top:"auto",         /*弹出框初始上边距  auto时去屏幕中间*/
	opacity:0.6,       //透明度
	mcolor:"#000000",  //透明层颜色
    url:"",          /*包含页面 url   如"aaa.html"  */
    textid:"",       /*包含某id 下的html内容  如"#aaa"  */
    title: "",        /*直接写内容 标题   */
    text:"",         /*直接写内容 内容   */
    titlecss:"",     /*标题样式          */
    innercss:"",     /*内容样式 限直接包含text使用  */
    closecss:"",     /*关闭按钮样式   */
    closeinner:"<span style='font-size:14px;'>╳&nbsp;</span>",   /*关闭按钮内容   */
    css:"",          /*弹出框 样式    */
    closeable:true,  /*是否能关闭     */
    moveable:true,   /*是否能拖动     */
    floatable:true,   /*是否能浮动     */
    dragid:"",
    closeid:"",
	bindFun:function(jframe,o){}
   },op||{});

  

  var jObj=null;
  var jClose=null;
  var jTitle=null;
  var jOverlay=null;

  
  this.close=function(){
    this.dailogBox.closeB();
  };
  
  this.show=function(){
	   this.dailogBox.init(o);          //弹出层对象 
	   this.overlay.init(o);         //遮罩层对象
       this.dailogBox.showB();
       if(o.closeable){this.dailogBox.bindClose(o);}
       if(o.floatable){this.dailogBox.bindFloat(o);}
       if(o.moveable){this.dailogBox.bindMove(o);}
       if(o.onShow){o.onShow();}
  };
 
  this.init=function(o){
     if(o.btn!=null){
       $(o.btn).bind('click',function(){
         _this.show();
       }); 
     }else{
        _this.show();  
     }	  
  };

/*生成弹出层-------------------S--------------------*/


 this.dailogBox={

	 Htemp:function(o){
	    var H='';
		var Hinner='';
        var closeH='<span id="'+o.id+'close" style="color:#ccc;position:absolute;right:0px;';
            closeH+='top:3px;height:18px;line-height:18px;display:blok;cursor:pointer;'+o.closecss+'"> ';
            closeH+=o.closeinner+'</span>';
		 
        var titleH='<div id="'+o.id+'title" style="posiiton:relative;font-size:16px;font-weight:bold;';
            titleH+='text-align:left;height:22px;line-height:22px;'+o.titlecss+'">' ;
            titleH+=o.title+closeH+'</div>';
			
			
        if(o.url!=""){
           Hinner='<div style="padding:10px;overflow:hidden;';
           if(o.height!="auto"&&o.height>0){
        	   Hinner+= "height:"+o.height+"px;";
           }   
           Hinner+= '"><iframe id="'+o.id+'frame" name="'+o.id+'frame" src="'+o.url+'" marginwidth="0" marginheight="0" style="padding:0px;margin:0px;width:'+(o.width-20)+'px;';
           if(o.height!="auto"&&o.height>0){
        	   Hinner+= "height:"+o.height+"px;";
           }
           Hinner+='overflow:hidden;" frameborder="0" scrolling="no" ></iframe></div>';
        }else{
           Hinner='<div id="'+o.id+'inner" style="padding:5px;text-align:left;' ;
           Hinner+=o.innercss+'">'+o.text+'</div>';
        }

        H+='<div id="'+o.id+'" style="position:absolute;width:'+o.width+'px;height:' ;
        H+=o.height+'px;display:none;z-index:1000;border:4px solid #ccc;overflow:hidden; ';
        H+='background:#fff;'+o.css+';"> ';
        H+=titleH;
        H+=Hinner;
        //H+=closeH;
        H+='</div>';
		return H;		
	 },
	 
	 bindFrameFun:function(jframe,o){
		var d=this;
        jframe.load(function(){
           //jframe.css({'height':'100px'});
           d.reinitIframe(o.id+"frame");
        }); 
		o.bindFun(jframe);	
		 
	 },
	 
	 reinitIframe:function(frameId){ 
       var iframe = document.getElementById(frameId); 
       try{ 
          var bHeight = iframe.contentWindow.document.body.scrollHeight; 
          var dHeight = iframe.contentWindow.document.documentElement.scrollHeight; 
          //alert(bHeight+":"+dHeight);
          var height = Math.max(bHeight, dHeight); 
          //alert(height);
          $(iframe).animate({height:height},500);
       }catch (ex){} 
     },
	 
	 getCenterP:function(){
       var ll=(lmz.bodyO.ww()-jObj.width())/2;
       var tt=(lmz.bodyO.hh()-jObj.height())/2;
       if(tt<0)tt=0;
       return {'ll':ll,'tt':tt};
     },

     setPosition:function(left,top){
       jObj.css({left:left,top:top});
     },
	 
     bindClose:function(){
	   var d=this;
       jClose.css('cursor','pointer');
       jClose.bind('click',function(){
         d.closeB();
       });
     },
	 
	 closeB:function(){
		 if(jObj){jObj.hide();}
		 if(jOverlay){jOverlay.hide();jOverlay.remove();}	 
	},
	showB:function(){
		 if(jObj){jObj.show();}
		 if(jOverlay){jOverlay.show();}		
    },
  
     bindFloat:function(o){
	   var d=this;
	   var hh=lmz.docO.hh();
       $(window).scroll(function(){
	     var ttp=lmz.scrollO.tt()+o.top;
	     if(ttp+jObj.height()>hh){ttp=hh-jObj.height();}
            d.setPosition(o.left,ttp);
       });
     },


    bindMove:function(o){
		
      lmzDrag(jObj,jTitle,{
	  onMove:function(oo,bar,p){
        o.top=p.y-lmz.scrollO.tt();
        o.left=p.x-lmz.scrollO.ll(); 
	  }	  
      });
    },	 

	 
	 init:function(o){
		 var h=this.Htemp(o);
         $('body').append(h);
         if(o.textid!=""){
        	 $(o.textid).children().appendTo($("#"+o.id+"inner"));
         }
         jObj=$("#"+o.id);
         jClose=$("#"+o.id+"close");
         if(o.closeid!=""){
        	 jClose=$("#"+o.closeid);
         }
         jTitle=$("#"+o.id+"title");
         if(o.dragid!=""){
        	 jTitle=$("#"+o.dragid);
         }
		 
         if($("#"+o.id+"frame")){
  	       this.bindFrameFun($("#"+o.id+"frame"),o); 
           $("#"+o.id+"frame").attr('src',o.url); 
         }
		 
		 if(o.left=="auto"){
            o.left=this.getCenterP().ll;
         }
         if(o.top=="auto"){
            o.top=this.getCenterP().tt;
         }
         //初始化位置

         this.setPosition(o.left+lmz.scrollO.ll(),o.top+lmz.scrollO.tt());

	}
  };
	 

    /*生成弹出层-------------------E--------------------*/


   /*生成遮罩层-------------------S--------------------*/
   
  this.overlay={
	
	  otemp:function(o){
	    var oh='<div id="'+o.id+'masks" style="position:absolute; ';
            oh+='filter:Alpha(Opacity='+o.opacity*100+');opacity:'+o.opacity+';top:0; ';
            oh+='left:0;z-index:999;background:'+o.mcolor+';display:none;"></div>';	
			return oh;
	  },
	  
	  init:function(o){
		  var oh=this.otemp(o);
		  $('body').append(oh);
		  jOverlay=$("#"+o.id+"masks");
		  jOverlay.css({width:lmz.docO.ww(),height:lmz.docO.hh()});
		  return this;
	  }
  
  }; 

  _this.init(o);
  return _this;


}


/**-----------------------------showDailog==E---------------------------------------**/
