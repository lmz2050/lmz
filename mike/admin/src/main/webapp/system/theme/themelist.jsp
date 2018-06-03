<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
function upOrd1(id){
	var ord=$("#"+id+"ordaa").val();
	if(!(/^\d*$/.test(ord))){
		alert("排序只能为数字格式");
	}else{
		api.post('${pageContext.request.contextPath}/theme/upOrd.action',{ord:ord,id:id},function(){
			$('#themelist').datagrid('reload');
		});
	}
}

$(function() {

$('#themelist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/theme/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'loadding......',
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'id',title:'ID',width:'10%',align: 'center'},
    	{field:'name',title:'name',width:'10%',align: 'center'},
    	{field:'cname',title:'名称',width:'20%',align: 'center'},
    	{field:'imgg',title:'图片',width:'20%',align: 'center',
    		formatter:function(value,row,index){
    			var u='<span style="display: block; height: 50px;" class="cart-product-img"><img height="50" src="${pageContext.request.contextPath}'+row.img+'"  style="cursor: pointer;" /></span>';
    			return u;
            }
    	}, 
    	{field:'def',title:'默认',width:'10%',align: 'center'},
    	{field:'upord',title:'排序',width:'15%',align: 'center',
    		formatter:function(value,row,index){
    			var u='<input type="text" id="'+row.id+'ordaa" name="ord" value="'+row.ord+'" style="width:50px;" />&nbsp;&nbsp;<a href=\"#\" class=\"easyuit\" onclick=\"upOrd1(\''+row.id+'\')\" class=\"easyui-linkbutton\" iconcls=\"icon-add\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/filesave.png\" /></a>';
    			return u;
            }
    	}, 
    	{field:'action',title:'修改',width:'15%',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('theme',function(){$('#themelist').datagrid('reload');},'"+row.id+"')\" class=\"easyui-linkbutton\" iconcls=\"icon-add\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });  
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('theme',function(){$('#themelist').datagrid('reload');},'themelist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('theme',function(){$('#themelist').datagrid('reload');},'themelist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="themelist"></table>
 </div>
</div>