
var api={};
api.tmap={};
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
		error:function(e){
			alert("ajax error!"+uri+","+data+",e:"+e);
			if(fe){
				fe(e);
			}
		}
	});
};
api.posts=function(uri,data,fn,fe){
    $.ajax({
        url:uri,
        data:data,
        type: 'POST',
        async: false,
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
            alert("ajax error!"+uri+","+data);
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
	api.openW(api.code.add,url);
	if(fn){api.Wcallback=fn;}
};

api.toAddgd=function(t,fn,jid,type){
	var url=api.path+'/'+t+'/toAddgd.action?id='+jid+'&type='+type;
	api.openW(api.code.add,url);
};
api.toUpdategd=function(t,fn,jid,type){
	if(jid){
		var url=api.path+'/'+t+'/toUpdategd.action?id='+jid+"&type="+type;
		api.openW(api.code.edit,url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('Please select the column you want to edit');
	}	
};


api.toUpdate1=function(t,fn,jid,pid){
	if(jid){
		var url=api.path+'/'+t+'/toUpdate.action?id='+jid+"&pid="+pid;
		api.openW(api.code.edit,url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('Please select the column you want to edit');
	}	
};
api.toUpdate=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		var url=api.path+'/'+t+'/toUpdate.action?id='+jid;
		api.openW(api.code.edit,url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('Please select the column you want to edit');
	}	
};
api.toUpdateDis=function(t,fn,jid,type){
	if(jid){
		var url=api.path+'/'+t+'/toUpdate.action?pid='+jid;
		if(type){
			url=api.path+'/'+t+'/toUpdate.action?id='+jid+'&distype='+type;
		}
		api.openW(api.code.edit,url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('Please select the column you want to edit');
	}	
};

api.toSendGoods=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		var url=api.path+'/'+t+'/toSendGoods.action?id='+jid;
		api.openW(api.code.edit,url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('Please select the column you want to edit');
	}	
};

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
		api.openW(api.code.edit,url);
		if(fn){api.Wcallback=fn;}
	}else{
		alert('Please select the column you want to edit');
	}	
};
api.del=function(t,fn,jid,fun){
	if(jid&&fun){
		jid=api.getSelect(jid,fun).id;
	}
	if(jid){
		var url=api.path+'/'+t+'/del.action';
		//alert(url+"--"+jid);
		$.dialog.confirm("Confirm the deletion?", function(){
			api.post(url,{id:jid},function(d){
				if(fn){fn()}
			});
		}, function(){});
	}else{
		alert('Please select the column you want to delete');
	}
};

api.upStatus=function(t,fn,jid,status,name){
	if(jid){
		var url=api.path+'/'+t+'/upStatus.action';
		$.dialog.confirm("Confirm "+name+"?", function(){
			api.post(url,{id:jid,status:status},function(d){
				if(fn){fn();}
			});
		}, function(){});
	}else{
		alert('Please select the column you want to '+name);
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

	//api.W.max();
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
	api.openU('upload',api.path+url);
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


var l={};
l.setLan=function(lan){
	//alert(lan);
    var url=api.path+'/lan.action';
	api.posts(url,{"lan":lan},function(){
		window.location.reload();
	},function(e){
		alert("err:"+url+'--'+lan);
	})
}
l.toAdd=function(t){
	var tb = $(t).parents('div[name="tb"]').attr("id");
    var url=api.path+'/'+tb+'/toAdd.action';
    api.openW(api.code.add,url);
    api.Wcallback=function(){$("#"+tb).find(".dg_list").datagrid('reload');}
}

l.del=function(t){
    var tb = $(t).parents('div[name="tb"]').attr("id");
    var dg = $("#"+tb).find(".dg_list");
    var url=api.path+'/'+tb+'/del.action';

    var jid = dg.datagrid("getSelected").id;
    if(jid){
		$.dialog.confirm("Confirm the deletion?", function(){
			api.post(url,{id:jid},function(d){
				dg.datagrid("reload");
			});
		}, function(){});
    }else{
        alert('Please select the column you want to delete');
    }
}

l.delItems=function(t){
    var tb = $(t).parents('div[name="tb"]').attr("id");
    var dg = $("#"+tb).find(".dg_list");

    var selectRows = dg.datagrid("getSelections");
    //如果没有选中行的话，提示信息
    if (selectRows.length < 1) {
        alert("Please select the column you want to delete");
        return;
    }
    //如果选中行了，则要进行判断
    $.dialog.confirm("Confirm the deletion?", function () {
        //如果为真的话
		//定义变量值
		var strIds = "";
		//拼接字符串，这里也可以使用数组，作用一样
		for (var i = 0; i < selectRows.length; i++) {
			strIds += selectRows[i].id + ",";
		}
		//循环切割
		strIds = strIds.substr(0, strIds.length - 1);
		var url=api.path+'/'+tb+'/delItems.action';
		api.post(url,{ids:strIds},function(d){
			dg.datagrid('reload');
		},function(){});

    });
}

l.toUpdate=function(t,jid){
    var tb = $(t).parents('div[name="tb"]').attr("id");
    var dg = $("#"+tb).find(".dg_list");

    if(jid){
        var url=api.path+'/'+tb+'/toUpdate.action?id='+jid;
        api.openW(api.code.edit,url);
        api.Wcallback=function(){dg.datagrid('reload');}
    }else{
        alert('Please select the column you want to delete');
    }
}

l.search=function(t){
	var tbo = $(t).parents('div[name="tb"]');
    var dg = tbo.find(".dg_list");
	var p = l.dg_search_params(tbo);
    dg.datagrid('load',{
    	ids:p
	});
}


l.dg_params= {
	width: function () { return document.body.clientWidth * 0.9 },
    height: function () { return document.body.clientheight - $("#toolbar").height() * 0.9 },
    nowrap: true,
    autoRowHeight: false,
    striped: true,
    collapsible: true,
    pagination: true,
    singleSelect: false,
    pageSize: 10,
    fit: true,
    fitColumns:true,
    pageList: [5, 10, 15],
    rownumbers: true
}

l.dg_search_params=function(dgo){
    var jsonStr = "{";
    dgo.find(".dg_search input.textbox-value").each(function(){
        var name = $(this).attr('name');
        var val = $(this).val();
        jsonStr +='"' + name + '":"' + val + '",';
    });
    if(jsonStr.length>1) {
        jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    }
    jsonStr += '}';
	return jsonStr;
}

l.dg=function(id,op) {
    var tb = $("#" + id);
    op = $.extend(l.dg_params, op || {});
    op.queryParams=function(){
        return l.dg_search_params(tb);
    };
    op.url=api.path+'/'+id+'/apage.action';
    tb.find(".dg_list").datagrid(op);
}
l.btn=function(id,tid){
    var url=api.path+'/menu/getBtn.action';
    var ath='';
	api.posts(url,{id:tid},function(d){
		//alert(d.obj);
        ath = d.obj;
	},function(){});

    var f= {field:'action',title:api.code.edit,align:'center'}

    if(ath.indexOf('edit')!=-1){
        f={field:'action',title:api.code.edit,align:'center',
            formatter:function(value,row,index){
                var u = "<a href=\"#\" onclick=\"l.toUpdate(this,'"+row.id+"')\" ><img src=\""+api.path+"/plug-in/easyui1.5/themes/icons/pencil.png\" /></a> ";
                return u;
            }
        }
    }

    var btnhtml='';
    if(ath.indexOf('add')!=-1){
        btnhtml+="<a href=\"javascript:void(0)\" onclick=\"l.toAdd(this);\" name=\"add\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\">"+top.api.code.add+"</a>";
    }
    if(ath.indexOf('remove')!=-1){
        btnhtml+="<a href=\"javascript:void(0)\" onclick=\"l.delItems(this);\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\">"+top.api.code.remove+"</a>";
    }
    if(ath.indexOf('import')!=-1){
        btnhtml+="<a href=\"javascript:void(0)\" onclick=\"importDrvices(this);\" id=\"batchimport\" class=\"easyui-linkbutton\" iconCls=\"icon-undo\" plain=\"true\">"+top.api.code.import+"</a>";
    }
    $("#"+id+" .dg_btn").html(btnhtml);

    return f;
}