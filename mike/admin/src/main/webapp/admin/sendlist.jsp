<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#sendlist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/send/apage.action',
    //queryParams:{path:'test'},  
    loadMsg:'数据加载中请稍后……',  
    pagination: true,
    rownumbers: true,
    columns:[[
        {field:'id',title:'序号',width:'10%',align: 'center'},
        {field:'cus_name',title:'客户名称',width:'10%',align: 'center'},
        {field:'dev_name',title:'设备名称',width:'10%',align: 'center'},
        {field:'sysname',title:'系统名称',width:'10%',align: 'center'},
        {field:'sysversion',title:'系统版本',width:'10%',align: 'center'},
        {field:'appversion',title:'应用版本',width:'10%',align: 'center'},
        {field:'ol_time',title:'上线时间',width:'20%',align: 'center'},
        {field:'ol_total',title:'在线时长',width:'20%',align: 'center'}
    ]]
    });   
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('send',function(){$('#sendlist').datagrid('reload');},'sendlist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('send',function(){$('#sendlist').datagrid('reload');},'sendlist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="sendlist"></table>
 </div>
</div>