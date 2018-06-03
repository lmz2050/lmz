<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">

$(function() {

$('#tpllist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/tpl/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'loadding......',
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'id',title:'ID',width:'10%',align: 'center'},
    	{field:'name',title:'name',width:'10%',align: 'center'},
    	{field:'cname',title:'名称',width:'10%',align: 'center'},
    	{field:'action',title:'action',width:'20%',align: 'center'},
    	{field:'url',title:'url',width:'20%',align: 'center'},
    	{field:'atype',title:'atype',width:'10%',align: 'center'},
    	{field:'ctype',title:'ctype',width:'10%',align: 'center'},
    	{field:'actiona',title:'修改',width:'10%',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('tpl',function(){$('#tpllist').datagrid('reload');},'"+row.id+"')\" class=\"easyui-linkbutton\" iconcls=\"icon-add\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });  
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('tpl',function(){$('#tpllist').datagrid('reload');},'tpllist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('tpl',function(){$('#tpllist').datagrid('reload');},'tpllist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="tpllist"></table>
 </div>
</div>