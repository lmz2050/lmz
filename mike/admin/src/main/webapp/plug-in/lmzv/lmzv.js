//lmz:lmzv 1.0 utf-8
var lmzv={	
//o
 o:{},
 cfg:{
	root:'',
 	formid:"form",
 	msgshow:"doc",
 	spanid:"_msg",
 	successfun:null,
 	errorfun:null,
 	slist:null,
 	msg:{ok_d:"",null_d:"不能为空!",len_d:"长度不正确!",type_d:"格式不正确!",compar_d:"输入不一致!",fun_d:"",info_d:"" },  
        error:{img:"/lmzvimg/error.gif",font:"color:red;"	},
 	info:{img:"/lmzvimg/reg01.jpg",font:"color:#333"},
 	ok:{img:"/lmzvimg/ok.gif",font:"color:green"},
 	ts:{phone:/^1[3|4|5|7|8][0-9]\d{8}$/,num:/^[0-9]+$/,user:/^[a-zA-Z_]+/,char:/^[a-zA-Z]+$/,email:/^[0-9a-zA-Z_]+@[0-9a-zA-Z_]+\.(([0-9a-zA-Z_]+)||([0-9a-zA-Z_]+)\.([0-9a-zA-Z_]+))$/,tel:/^[0-9](([0-9]{9})||([0-9]{3}-[0-9]{6}))[0-9]$/,cn:/./,en:/\w/ }
 },
 checkO:{},
//base fun
 trim:function(str){str=str.replace(/^(\s)+/,"#");str=str.replace(/(\s)+$/,"#");return str;},
 getForm:function(id){return $("#"+id);	},
 getSpan:function(o,spanid){return $("#"+o.attr('id')+spanid);},
 show:function(o,m,str,cfg){
     if(cfg.msgshow=="alert"){
        if(str!=""){alert(str);}
     }else{
        this.getSpan(o,cfg.spanid).html("<font style="+m.font+"><img src='"+lmzv.cfg.root+m.img+"'/> "+str+"</font>");
        //if(m.font==cfg.error.font){
	    	this.getSpan(o,cfg.spanid).show();
		//}else{
		//	this.getSpan(o,cfg.spanid).hide();
		//}
     }
  },
 getDef:function(str,str_d){
   if(undefined==str||null==str){
      return str_d;	
   }else{
      return str;
   }
 },
 getts:function(t,ts){
    return ts[t];
 },
//check fun
 isnull:function(str){
   if(str==null||this.trim(str)==""){
     return true;
   }
   return false;
 },
 len:function(str,len){  
   if(len.min){
     if(str.length<len.min){
       return false;
     }
   }
   if(len.max){
     if(str.length>len.max){
       return false;
     }
   }
   return true;
 }, 
 getCO:function(o){
   var cfgg=this.cfg;
   if(!this.checkO.o||o.attr('id')!=this.checkO.o.attr('id')){
     var oo={};
     oo.o=o;
     oo.t=true;
     oo.f=function(){};
     oo.s=function(){};
     oo.b=[];
     
     oo.fun=function(){
	var i=0;
        oo.t=true;
        while(oo.t&&oo.b[i]!=null){
           oo.t=oo.b[i](oo.o);
           i++;         
        }
        //alert(oo.t);
        if(oo.t){
          oo.s(oo.o);
        }
        return oo.t;     	
     };    
     oo.check=function(){
     	oo.f(oo.o);
     	if(cfgg.msgshow!="alert"){
     		oo.o.focus(function(){oo.f($(this));});
     		oo.o.blur(function(){oo.fun();});
     	}
     };
     oo.formV=function(){
        return oo.fun(); 
     };
     oo.addf=function(f){
     	oo.b.push(f);
     };
     this.checkO=oo;        
   }
   return this.checkO;	
 },
 checkfun:function(o,bl,str,cfg){ 
     if(bl==true){
     	this.show(o,cfg.error,str,cfg);
    	return false;
     }
     return true;
 },
//add jquery fn
 initfun:function(cfg){
   var t=this;   
   jQuery.fn.initV=function(desc,succ){
       succ=t.getDef(succ,cfg.msg.ok_d);
       desc=t.getDef(desc,cfg.msg.info_d);
       var c=t.getCO($(this));
       c.s=function(o){t.show(o,cfg.ok,succ,cfg);};
       c.f=function(o){t.show(o,cfg.info,desc,cfg);};
       return $(this); 
    };
    jQuery.fn.nullV=function(str){
      str=t.getDef(str,cfg.msg.null_d);
      var c=t.getCO($(this));
      var f=function(k){return t.checkfun(k,t.isnull(k.val())||k.val()==k.attr('placeholder'),str,cfg);};
      c.addf(f);
      return $(this);
    };
    jQuery.fn.lenV=function(json,str){
      str=t.getDef(str,cfg.msg.len_d);
      var c=t.getCO($(this));
      var f=function(k){return t.checkfun(k,!t.len(k.val(),json),str,cfg);};
      c.addf(f);
      return $(this);
    };
    jQuery.fn.typeV=function(tr,str){  //ts: 'num','user','char','email','tel','cn','en'
      str=t.getDef(str,cfg.msg.type_d);
      var c=t.getCO($(this));
      var ts=t.getts(tr,cfg.ts);
      
      var f=function(k){return t.checkfun(k,!((k.val()==null||k.val()=="")||ts.test(k.val())),str,cfg);};
      c.addf(f);
      return $(this);
    };    
    jQuery.fn.comparV=function(o,str){
      str=t.getDef(str,cfg.msg.compar_d);
      var c=t.getCO($(this));
      var f=function(k){return t.checkfun(k,o.val()!=k.val(),str,cfg);};
      c.addf(f);
      return $(this);   
    };
    jQuery.fn.funV=function(fun,str){
      str=t.getDef(str,cfg.msg.fun_d);
      var c=t.getCO($(this));
      var f=function(k){return t.checkfun(k,fun(k),str,cfg);};
      c.addf(f);
      return $(this); 
    };  
    jQuery.fn.startV=function(){
    	cfg.slist.push(t.checkO);
    	t.checkO.check();
    };
  },
  initsubmit:function(cfg){
     var t=this;     
     t.getForm(cfg.formid).submit(function(){
       return t.ck();
     });    	
  },
  ck:function(){
	   var t=this;
       var bl=true;   
       for(i=0;i<t.cfg.slist.length;i++){
       	  if(!t.cfg.slist[i].formV()){bl=false;}
       }
       if(!bl){
       	   if(t.cfg.errorfun){t.cfg.errorfun();}
       }else{    	   
       	   if(t.cfg.successfun){bl=t.cfg.successfun();}
       }
        return bl;
  },
 init:function(o){
    this.cfg=$.extend({},this.cfg,o||{});
    this.cfg.slist=[];
    this.initfun(this.cfg);
    this.initsubmit(this.cfg);
 }
};


var scripts = document.getElementsByTagName("script");
if(scripts!=null&&scripts.length>0){
	for(var i=0;i<scripts.length;i++){
		if(scripts[i].src.indexOf('lmzv.js')!=-1){
			lmzv.cfg.root=scripts[i].src.substring(0,scripts[i].src.lastIndexOf("/"));
		}
	}
}
