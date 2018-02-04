<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#ulevlist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/ulev/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'数据加载中请稍后……',  
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'name',title:'等级名称',width:'40%',align: 'center'},
    	{field:'def',title:'是否默认',width:'40%',align: 'center'},
    	{field:'action',title:'修改',width:'20%',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('ulev',function(){$('#ulevlist').datagrid('reload');},'"+row.id+"')\" class=\"easyui-linkbutton\" iconcls=\"icon-add\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });  
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('ulev',function(){$('#ulevlist').datagrid('reload');},'ulevlist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('ulev',function(){$('#ulevlist').datagrid('reload');},'ulevlist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="ulevlist"></table>
 </div>
</div>