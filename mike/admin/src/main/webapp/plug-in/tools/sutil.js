

var api={};
var jj = document.getElementById("sutil");
api.path=jj.getAttribute("path");
api.i=1;
api.post=function(uri,data,fn,fe){
	$.ajax({
		url:uri,
		data:data,
		type: 'POST',
		success:function(data){
			var d = $.parseJSON(data);
			if(d.success){
				fn(d);
			}else{
				alert("msg:"+d.msg);
				if(fe){
					fe(d);
				}
			}
		},
		error:function(){
			alert("ajax error!");
			if(fe){
				fe({});
			}
		}
	});
};
api.get=function(uri,data,fn,fe){
	$.ajax({
		url:uri,
		data:data,
		type: 'POST',
		success:function(data){
			var d = $.parseJSON(data);
			fn(d);
		},
		error:function(){
			alert("ajax error!");
			if(fe){
				fe({});
			}
		}
	});
};
api.flushLeft=function(){
	$("#nav").accordion('reload');
};

api.getSelect=function(id,fun){
	var js;
	try{
		js=eval("$('#"+id+"')."+fun+"('getSelected')");
	}catch(e){}
	if(js){
		return js;
	}else{
		return {};
	}
};

api.toAdd=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(!jid){jid='';}
	var url=api.path+'/'+t+'/toAdd.action?id='+jid;
	api.openW("新增",url);
	if(fn){api.Wcallback=fn;}
};

api.toAddgd=function(t,fn,jid,type){
	var url=api.path+'/'+t+'/toAddgd.action?id='+jid+'&type='+type;
	api.openW("新增",url);
};
api.toUpdategd=function(t,fn,jid,type){
	if(jid){
		var url=api.path+'/'+t+'/toUpdategd.action?id='+jid+"&type="+type;
		api.openW("编辑",url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('请选择要编辑的栏目');
	}	
};


api.toUpdate1=function(t,fn,jid,pid){
	if(jid){
		var url=api.path+'/'+t+'/toUpdate.action?id='+jid+"&pid="+pid;
		api.openW("编辑",url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('请选择要编辑的栏目');
	}	
};
api.toUpdate=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		var url=api.path+'/'+t+'/toUpdate.action?id='+jid;
		api.openW("编辑",url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('请选择要编辑的栏目');
	}	
};
api.toUpdateDis=function(t,fn,jid,type){
	if(jid){
		var url=api.path+'/'+t+'/toUpdate.action?pid='+jid;
		if(type){
			url=api.path+'/'+t+'/toUpdate.action?id='+jid+'&distype='+type;
		}
		api.openW("编辑",url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('请选择要编辑的栏目');
	}	
};

api.toSendGoods=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		var url=api.path+'/'+t+'/toSendGoods.action?id='+jid;
		api.openW("编辑",url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('请选择要编辑的栏目');
	}	
};
/*
api.toAdd=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		var url=api.path+'/'+t+'/toAdd.action?id='+jid;
		api.openW("编辑",url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('请选择要编辑的栏目');
	}	
}
*/
api.toUrl=function(url,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		if(url.indexOf('?')!=-1){
			url+="&id="+jid;
		}else{
			url+="?id="+jid;
		}
		api.openW("编辑",url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('请选择要编辑的栏目');
	}	
};
api.del=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		var url=api.path+'/'+t+'/del.action';
		//alert(url+"--"+jid);
		$.dialog.confirm("确认删除?", function(){
			api.post(url,{id:jid},function(d){
				if(fn){fn()}
			});
		}, function(){});
	}else{
		alert('请选择要删除的栏目');
	}
};

api.upStatus=function(t,fn,jid,status,name){
	if(jid){
		var url=api.path+'/'+t+'/upStatus.action';
		$.dialog.confirm("确认"+name+"?", function(){
			api.post(url,{id:jid,status:status},function(d){
				if(fn){fn();}
			});
		}, function(){});
	}else{
		alert('请选择要"+name+"的栏目');
	}
};

api.tab=function(title,url,icon){
	addTab(title,url,icon);	
};

api.openW=function(title,url,width,height) {
	if(!width){width='auto';}
	if(!height){height='auto';}
	api.W=$.dialog({
			content: 'url:'+url,
			title : title,
			cache: false
			//lock : true,
			//width: width,
		    //height: height
	});
	api.W.max();
	//api.W.min();
	//api.W.size('auto','auto');
};

api.closeW=function(fn){
	api.W.close();
	if(fn){fn();}
	if(api.Wcallback){
		api.Wcallback();
	}
};



api.openU=function(title,url,width,height){
	if(!width){width='auto';}
	if(!height){height='auto';}
	api.U=$.dialog({
			content: 'url:'+url,
			title : title,
			cache: false,
			lock : true,
			width: width,
		    height: height
	});
};
api.closeU=function(name){
	api.U.close();
	if(api.Ucallback){
		api.Ucallback(name);
	}
};
api.upload=function(fn,ipath,action){
	var url='/system/upload/upload.jsp?a=1';
	if(ipath){
		url=url+'&ipath='+ipath;
	}
	if(action){
		url=url+'&action='+action;
	}
	url+'&t='+new Date();
	api.openU('上传',api.path+url);
	api.Ucallback=fn;
};

api.alert=function(txt){
	$.dialog.tips(txt);
};

api.confirm=function(t,url,type){
	$.dialog.confirm(t, function(){
			if(type==1){
				window.location.href=url;
			}else{
				api.get(url,{},function(d){});
			}
		}, function(){});
};


api.onPay=function(id,bank){
	$.ajax({
		url:api.path+"/pay/onPay.action",
		data:{id:id,bank:bank},
		type: 'POST',
		success:function(data){
			//alert(data);
		    if (!$("#payform").length) {
		        var dd = "<div id='payform'>" + data + "</div>";
		        $("body").append(dd);
		        
		    }else{
		    	$("#payform").html(data);
		    }
		},
		error:function(){
			//alert("ajax error!");
		}
	});
};
