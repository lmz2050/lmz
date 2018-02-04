<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#loglist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/log/apage.action',
    //queryParams:{path:'test'},  
    loadMsg:'数据加载中请稍后……',  
    pagination: true,
    rownumbers: true,
    columns:[[
        {field:'id',title:'序号',width:'10%',align: 'center'},
        {field:'cus_name',title:'客户名称',width:'10%',align: 'center'},
        {field:'dev_name',title:'设备名称',width:'10%',align: 'center'},
        {field:'version',title:'版本名称',width:'10%',align: 'center'},
        {field:'utm',title:'TIME',width:'20%',align: 'center'},
        {field:'content',title:'LOG',width:'30%',align: 'center'},
        {field:'action',title:'DETAIL',width:'10%',align:'center',
            formatter:function(value,row,index){
                var u = "<a href=\"#\" onclick=\"api.toUpdate('log',function(){$('#loglist').datagrid('reload');},'"+row.id+"')\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                return u;
            }
        }
    ]]
    });   
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('log',function(){$('#loglist').datagrid('reload');},'loglist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('log',function(){$('#loglist').datagrid('reload');},'loglist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="loglist"></table>
 </div>
</div>