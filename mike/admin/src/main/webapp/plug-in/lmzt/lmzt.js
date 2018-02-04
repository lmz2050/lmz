function tnode(o){
  this.id=o.id;
  this.pid=o.pid;
  this.url=o.url;
  this.name=o.name;
  this.c=-1;
  this.io=false;
  this.il=false;
  this.hc=false;
  this.oimg='';
  this.cimg='';
  this.limg='';
  this.o=o;
}

var troot='';

function lmzt(o){

  var cfg=$.extend({},{
     id:"lmzt",
     nid:"node1",
     type:1,
     rid:0
  },o||{});
  
  this.icon = {
        join	 : troot+'/lmztimg/join.gif',
        join1	 : troot+'/lmztimg/join1.gif',
        join2	 : troot+'/lmztimg/join2.gif',
        joinbtm  : troot+'/lmztimg/joinbottom.gif',
        empty    : troot+'/lmztimg/empty.gif',
        line     : troot+'/lmztimg/line.gif',
        plus     : troot+'/lmztimg/plus.gif',
        nl_plus  : troot+'/lmztimg/nl_plus.gif',
        plusbtm  : troot+'/lmztimg/plusbottom.gif',
        minus    : troot+'/lmztimg/minus.gif',
        nl_minus : troot+'/lmztimg/nl_minus.gif',
        minusbtm : troot+'/lmztimg/minusbottom.gif'
  };
  
  var t=this;
  var nid=cfg.nid;
  var tlist = [];
  var root=new tnode({id:cfg.rid,pid:cfg.rid,url:"",name:""});
  
  this.add = function(o){
     tlist.push(new tnode(o));
  };
  this.addAll = function(ol){
     for(var i=0;i<ol.length;i++){
				tlist.push(new tnode(ol[i]));
     }
  };
  
  this.getH = function(){
    var str = '';
    str+='<div id="'+cfg.id+'">';
    str+=t.getCN(root);
    str+='</div>';
    return str;
  };
  
  this.getCN=function(node){
    var str ='';
    var k=0; 
    var l=null; 
    for(var i=0;i<tlist.length;i++){
       var n=tlist[i];
       if(n.pid==node.id){
          n.c=node.c+1;
          k++;
          l=n;
          str+='<div id="'+nid+n.id+'" class="'+nid+'" pid="'+n.pid+'" ii="'+i+'">'; 
          var url=n.url;
          if(!url||url=='undefined'){
        	  url="javascript:void(0)";
          }
          str +='<a href="'+url+'">'+n.name+'</a></div>';
          str+=t.getCN(n);                     
       }      
    }
    if(k>0){node.hc=true;}
    if(l){l.il=true;} 
    return str;   
  };
  
  this.get=function(i){
    return tlist[i];
  };
  
  this.initN=function(){
    for(var i=0;i<tlist.length;i++){
       var n=tlist[i];
       n.limg=t.icon.empty;
       if(n.c==root.c+1){
         n.cimg=t.icon.nl_plus;
         n.oimg=t.icon.nl_minus;
       }else{
         if(n.hc){
           if(n.il){
             n.cimg=t.icon.plusbtm;
             n.oimg=t.icon.minusbtm;
           }else{
             n.cimg=t.icon.plus;
             n.oimg=t.icon.minus;
             n.limg=t.icon.line;
           }
         }else{
           if(n.il){
             n.cimg=t.icon.joinbtm;
             n.oimg=t.icon.joinbtm;
           }else{
             n.cimg=t.icon.join;
             n.oimg=t.icon.join;
           }
         }
       }
     }  
  };
  
  this.setCss=function(){
    for(var i=0;i<tlist.length;i++){
       var n=tlist[i];
       var h="";
       var nd=$("#"+nid+n.id);
       for(var ii=0;ii<n.c;ii++){
           var pn=t.pc(n,ii);
           h+='<img src="'+pn.limg+'" />';
       }
       if(!n.hc&&n.c>(root.c+1)){
         h+='<img src="'+n.cimg+'" />';
       }else{
         h+='<img src="'+n.cimg+'" class="'+nid+'even" />';
       }       
       h+=nd.html();
       nd.html(h);
    }
    var jj=0;
    $("#"+cfg.id).find("."+cfg.nid).each(function(){
    	var backcolor=(jj%2==0)? "#fff":"#eee";
    	$(this).css({backgroundColor:backcolor});
    	$(this).attr('csbak',backcolor);
    	jj++;
    });
    $("#"+cfg.id).find("."+cfg.nid).hover(function(){
    	$(this).css({backgroundColor:"#ddd"});
    },function(){
    	var backcolor = $(this).attr("csbak");
    	$(this).css({backgroundColor:backcolor});
    })
    
  };
  this.fmat=function(fn){
    for(var i=0;i<tlist.length;i++){
       var n=tlist[i];
       var nd=$("#"+nid+n.id);
       fn(n,nd);
    }     
  };
  this.pc=function(n,c){
     if(n.c==c||n.c==root.c+1){
        return n;
     }else{
        var pid=$("#"+nid+n.id).attr('pid');
        var pn=t.get($("#"+nid+pid).attr('ii'));
        return t.pc(pn,c);
     }
  };
  
  this.open=function(n){
     $("#"+nid+" div[pid="+n.id+"]").show();
     $("#"+nid+n.id).find("."+nid+"even").eq(0).attr('src',n.oimg);
     n.io=true;
  };
  
  this.close=function(n){ 
    $("#"+nid+" div[pid="+n.id+"]").hide();
     $("#"+nid+n.id).find("."+nid+"even").eq(0).attr('src',n.cimg);
     n.io=false;  
     $("#"+nid+" div[pid="+n.id+"]").each(function(){
        var nn=t.get($(this).attr('ii'));
        if(nn.hc){t.close(nn);}
     });
  };
  
  this.closeAll=function(){
     for(var i=0;i<tlist.length;i++){
       var n=tlist[i];
       if(n.c==root.c+1){
         t.close(n);
       }
     }
  };
  
  this.openAll=function(){
     for(var i=0;i<tlist.length;i++){
       var n=tlist[i];
       if(n.hc){t.open(n);}
       else if(n.c==root.c+1){t.open(n);}
     }     
  };
  
  this.setEve=function(){
     $("."+nid+"even").click(function(){
        var n=t.get($(this).parents('div[ii]').eq(0).attr('ii'));
        if(n.io==false){
          t.open(n);
        }else{
          t.close(n);
        }
     });
  };
  
  this.show=function(ii,fn){
    var str=t.getH();
    $("#"+cfg.id).html(str);
    t.initN();
    t.setCss();
    if(fn){
    	this.fmat(fn);	
    }
    t.setEve();
    if(ii){cfg.type=ii;}
    if(cfg.type==1){
      t.closeAll();
    }else{
      t.openAll();
    }
    //alert($("#"+cfg.id).html());
  };

}

var scripts = document.getElementsByTagName("script");
if(scripts!=null&&scripts.length>0){
	for(var i=0;i<scripts.length;i++){
		if(scripts[i].src.indexOf('lmzt.js')!=-1){
			troot=scripts[i].src.substring(0,scripts[i].src.lastIndexOf("/"));
		}
	}
}