<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#recvlist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/recv/apage.action',
    //queryParams:{path:'test'},  
    loadMsg:'数据加载中请稍后……',  
    pagination: true,
    rownumbers: true,
    columns:[[
        {field:'id',title:'序号',width:'5%',align: 'center'},
        {field:'cus_name',title:'客户名称',width:'5%',align: 'center'},
        {field:'dev_name',title:'设备名称',width:'5%',align: 'center'},
        {field:'mac1',title:'MAC1',width:'5%',align: 'center'},
        {field:'mac2',title:'MAC2',width:'5%',align: 'center'},
        {field:'version',title:'版本名称',width:'5%',align: 'center'},
        {field:'ol_time',title:'上线时间',width:'7%',align: 'center'},
        {field:'ol_total',title:'在线时长',width:'7%',align: 'center'},
        {field:'gn1_total',title:'gn1使用时长',width:'7%',align: 'center'},
        {field:'gn2_total',title:'gn2使用时长',width:'7%',align: 'center'},
        {field:'gn3_total',title:'gn3使用时长',width:'7%',align: 'center'},
        {field:'gn4_total',title:'gn4使用时长',width:'7%',align: 'center'},
        {field:'gn5_total',title:'gn5使用时长',width:'7%',align: 'center'},
        {field:'gn6_total',title:'gn6使用时长',width:'7%',align: 'center'},
        {field:'gn7_total',title:'gn7使用时长',width:'7%',align: 'center'},
        {field:'gn8_total',title:'gn8使用时长',width:'7%',align: 'center'}
    ]]
    });   
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('recv',function(){$('#recvlist').datagrid('reload');},'recvlist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('recv',function(){$('#recvlist').datagrid('reload');},'recvlist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="recvlist"></table>
 </div>
</div>