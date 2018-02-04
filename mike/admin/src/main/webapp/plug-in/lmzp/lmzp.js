var lmzp={
  cfg:{
   root:'',
   id:"lmz",
   pname:"pg.intCurrentPage",
   currp:1,
   totalp:1,
   pnum:10,
   m:"get",
   o:{cp:null,lp:null,fp:null,np:null,pp:null,box:null,form:null,a:null	},   
   co:{
      cp:'-page',
      box:'-pageul',
      ip:'-pageul li',
      fp:'-pageul li.fpage',
      lp:'-pageul li.lpage',
      pp:'-pageul li.prevp',
      np:'-pageul li.nextp',
      lli:'-pageul li.lastli',
      a:'-pageul li a',
      cli:'-pageul li.currp a'   	
    },   
   css:{
   	box:"{margin:0px;padding:0px;height:18px;line-height:18px;margin:0px auto;}",
   	ip:"{list-style-type:none;margin:0px;padding:0px;width:18px;height:18px;border-right:1px solid #f1f1f1;float:left;text-align:center;}",
   	fp:"{background:url('${root}/lmzpimg/fpage.gif') no-repeat center;width:28px;border:none;margin:0px;padding:0px;}",
   	lp:"{background:url('${root}/lmzpimg/lpage.gif') no-repeat center;width:28px;border:none;margin:0px;padding:0px;}",
   	//pp:"{background:url('${root}/lmzpimg/prevp.gif') no-repeat center;width:28px;border:none;margin:0px;padding:0px;}",
   	//np:"{background:url('${root}/lmzpimg/nextp.gif') no-repeat center;width:28px;border:none;margin:0px;padding:0px;}",
    pp:"{width:40px;font-weight:bold;border:none;margin:0px;padding:0px;cursor:pointer;}",
   	np:"{width:40px;font-weight:bold;border:none;margin:0px;padding:0px;cursor:pointer;}",
   	lli:"{border:none;}",
   	a:"{color:#000;font-size:12px;cursor:pointer;}",
   	cli:"{color:red;font-size:12px;text-decoration:none;}"
       }
  }, 
  getLi:function(cfg){
       var s="";
       var cp=cfg.currp;
       var ep=Math.floor(cfg.pnum/2);
       var lp=0;
       var rp=0;      
       if(cp-1<ep){
         lp=cp-1;
         rp=cfg.totalp>cfg.pnum?cfg.pnum-lp-1:cfg.totalp-cp;	
       }else if(cfg.totalp-cp<ep){
       	 rp=cfg.totalp-cp;
       	 lp=cp-1>cfg.pnum?cfg.pnum-rp-1:cp-1;  	 
       	}else{
       	  lp=ep;
       	  rp=ep;
       	  if(cfg.pnum%2==0){rp=rp-1;}
       	}
       for(var i=lp;i>0;i--){
       	   s+='<li><a href="javascript:void(0)">'+(cp-i)+'</a></li>';	
       }
           s+='<li class="currp"><a href="javascript:void(0)">'+cp+'</a></li>';
       for(var i=1;i<rp+1;i++){
       	   s+='<li><a href="javascript:void(0)">'+(cp+i)+'</a></li>';	
       } 
       return s;
  }, 
  getH:function(cfg){
    var h='';
    var s='';
    if(cfg.m=="get"){
       s='<input type="hidden" id="'+cfg.id+'-page" name="" value="'+cfg.currp+'" />';
    }else{
       s='<input type="hidden" id="'+cfg.id+'-page" name="'+cfg.pname+'" value="'+cfg.currp+'" />';
    }
       h+='<ul id="'+cfg.id+'-pageul">';
       h+='<li style="display:none;" id="inputp">'+s+'</li>';
       h+='<li class="fpage"></li>';
       h+='<li class="prevp">上一页</li>';
       h+=this.getLi(cfg);
       h+='<li class="nextp">下一页</li>';
       h+='<li class="lpage"></li>';
       h+='</ul>';
       return h;
  },
  getC:function(cfg){
      var c='<style type="text/css">';
      c+='#'+cfg.id+cfg.co.box+cfg.css.box;
      c+='#'+cfg.id+cfg.co.ip+cfg.css.ip;
      c+='#'+cfg.id+cfg.co.fp+cfg.css.fp.replace('${root}',lmzp.cfg.root);
      c+='#'+cfg.id+cfg.co.lp+cfg.css.lp.replace('${root}',lmzp.cfg.root);
      c+='#'+cfg.id+cfg.co.pp+cfg.css.pp.replace('${root}',lmzp.cfg.root);
      c+='#'+cfg.id+cfg.co.np+cfg.css.np.replace('${root}',lmzp.cfg.root);
      c+='#'+cfg.id+cfg.co.lli+cfg.css.lli;
      c+='#'+cfg.id+cfg.co.a+cfg.css.a;
      c+='#'+cfg.id+cfg.co.cli+cfg.css.cli;
      c+='</style>';
      return c;
  },
  initH:function(cfg){
      var h=this.getH(cfg);
      var c=this.getC(cfg);
      return h+c;
      
  }, 
  setO:function(cfg){
      cfg.o.cp=$('#'+cfg.id+cfg.co.cp);
      cfg.o.fp=$('#'+cfg.id+cfg.co.fp);
      cfg.o.lp=$('#'+cfg.id+cfg.co.lp);
      cfg.o.np=$('#'+cfg.id+cfg.co.np);
      cfg.o.pp=$('#'+cfg.id+cfg.co.pp);
      cfg.o.box=$('#'+cfg.id+cfg.co.box);
      cfg.o.ip=$('#'+cfg.id+cfg.co.ip);
      cfg.o.a=$('#'+cfg.id+cfg.co.a);
      cfg.o.form=cfg.o.box.parents('form').eq(0); 
      var ii = cfg.totalp;
      if(ii>cfg.pnum){
    	  ii=cfg.pnum;
      }
      var w=cfg.o.lp.width()+cfg.o.fp.width()+cfg.o.pp.width()+cfg.o.np.width()+cfg.o.ip.width()*ii;
      //alert(w+"-"+cfg.o.lp.width()+"-"+cfg.o.fp.width()+"-"+cfg.o.pp.width()+"-"+cfg.o.np.width()+"-ip:"+cfg.o.ip.width());
      cfg.o.box.css('width',w+10);
      if(cfg.currp==1){
        cfg.o.fp.hide();
        cfg.o.pp.hide();	
      }else if(cfg.currp==cfg.totalp){
        cfg.o.lp.hide();
        cfg.o.np.hide();	
      }
      return cfg;      
  },  
  addP:function(a,p){
    if(a.indexOf('?')!=-1){
       return a+"&"+p;	
    }else{
       return a+"?"+p;
    }
  },   
  subFun:function(m,f,s){
      if(m=="post"){
    	 //window.location.href=f.attr('action');
         f.submit();
      }else if(m=="get"){
         var a=this.addP(f.attr('action'),s);
         f.attr('action',a);
         f.submit();
      }
  },
  bindFun:function(c){
     var t=this;
     //var c=new Object();
     //c=cf;
     c.o.pp.click(function(){
    	 if(parseInt(c.o.cp.val())>0){
         	c.o.cp.val(parseInt(c.o.cp.val())-1);
         	t.subFun(c.m,c.o.form,c.pname+"="+c.o.cp.val());
         }
     });
     c.o.np.click(function(){
    	 if(parseInt(c.o.cp.val())<c.totalp){
         	c.o.cp.val(parseInt(c.o.cp.val())+1);
         	t.subFun(c.m,c.o.form,c.pname+"="+c.o.cp.val());
         }
     });
     c.o.fp.click(function(){
         c.o.cp.val(1);	
         t.subFun(c.m,c.o.form,c.pname+"="+c.o.cp.val());
     });
     c.o.lp.click(function(){
         c.o.cp.val(c.totalp);
         t.subFun(c.m,c.o.form,c.pname+"="+c.o.cp.val());	
     });
     c.o.a.click(function(){
         c.o.cp.val($(this).html());
         t.subFun(c.m,c.o.form,c.pname+"="+c.o.cp.val());
    });          
  },    
  doPage:function(cf,obj){
     if(obj){
         cf=$.extend({},this.cfg,cf||{});
         var h=this.initH(cf);
         obj.html(h);	
         cf=this.setO(cf);
         this.bindFun(cf); 
     }    
  }	
};


var scripts = document.getElementsByTagName("script");
if(scripts!=null&&scripts.length>0){
	for(var i=0;i<scripts.length;i++){
		if(scripts[i].src.indexOf('lmzp.js')!=-1){
			lmzp.cfg.root=scripts[i].src.substring(0,scripts[i].src.lastIndexOf("/"));
		}
	}
}
