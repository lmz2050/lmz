<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#cuslist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/cus/apage.action',
    //queryParams:{path:'test'},  
    loadMsg:'数据加载中请稍后……',  
    pagination: true,
    rownumbers: true,
    columns:[[
        {field:'id',title:'id',width:'10%',align: 'center'},
        {field:'phone',title:'phone',width:'10%',align: 'center'},
        {field:'name',title:'name',width:'20%',align: 'center'},
        {field:'sex',title:'sex',width:'10%',align: 'center'},
        {field:'wechat',title:'wechat',width:'10%',align: 'center'},
        {field:'mail',title:'mail',width:'20%',align: 'center'},
        {field:'job',title:'job',width:'20%',align: 'center'}
    ]]
    });   
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('cus',function(){$('#cuslist').datagrid('reload');},'cuslist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('cus',function(){$('#cuslist').datagrid('reload');},'cuslist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="cuslist"></table>
 </div>
</div>