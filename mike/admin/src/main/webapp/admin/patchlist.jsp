<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#patchlist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/patch/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'数据加载中请稍后……',  
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'id',title:'ID',width:'5%',align: 'center'},
    	{field:'vname',title:'Vname',width:'5%',align: 'center'},
    	{field:'okupdata',title:'OkUpdata',width:'5%',align: 'center'},
    	{field:'url',title:'Url',width:'20%',align: 'center'},
    	{field:'remark',title:'Explain',width:'40%',align: 'center'},
    	{field:'cby',title:'create_user',width:'10%',align: 'center'},
    	{field:'ctm',title:'create_time',width:'10%',align: 'center'},
    	{field:'action',title:'Action',width:'5%',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('patch',function(){$('#patchlist').datagrid('reload');},'"+row.id+"')\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });   
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('patch',function(){$('#patchlist').datagrid('reload');},'patchlist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('patch',function(){$('#patchlist').datagrid('reload');},'patchlist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="patchlist"></table>
 </div>
</div>