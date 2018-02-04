<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#drviceslist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/drvices/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'数据加载中请稍后……',  
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'id',title:'序号',width:'4%',align: 'center'},
    	{field:'cus_name',title:'客户名称',width:'5%',align: 'center'},
    	{field:'dev_name',title:'设备名称',width:'5%',align: 'center'},
    	{field:'mac1',title:'MAC1',width:'5%',align: 'center'},
    	{field:'mac2',title:'MAC2',width:'5%',align: 'center'},
    	{field:'active',title:'首次激活',width:'4%',align: 'center'},
    	{field:'active_time',title:'首次激活时间',width:'4%',align: 'center'},
    	{field:'gn1',title:'gn1',width:'4%',align: 'center'},
    	{field:'gn1_time',title:'gn1激活时间',width:'4%',align: 'center'},
    	{field:'gn2',title:'gn2',width:'4%',align: 'center'},
    	{field:'gn2_time',title:'gn2激活时间',width:'4%',align: 'center'},
    	{field:'gn3',title:'gn3',width:'4%',align: 'center'},
    	{field:'gn3_time',title:'gn3激活时间',width:'4%',align: 'center'},
    	{field:'gn4',title:'gn4',width:'4%',align: 'center'},
    	{field:'gn4_time',title:'gn4激活时间',width:'4%',align: 'center'},
    	{field:'gn5',title:'gn5',width:'4%',align: 'center'},
    	{field:'gn5_time',title:'gn5激活时间',width:'4%',align: 'center'},
    	{field:'gn6',title:'gn6',width:'4%',align: 'center'},
    	{field:'gn6_time',title:'gn6激活时间',width:'4%',align: 'center'},
    	{field:'gn7',title:'gn7',width:'4%',align: 'center'},
    	{field:'gn7_time',title:'gn7激活时间',width:'4%',align: 'center'},
    	{field:'gn8',title:'gn8',width:'4%',align: 'center'},
    	{field:'gn8_time',title:'gn8激活时间',width:'4%',align: 'center'},
    	{field:'action',title:'修改',width:'4%',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('drvices',function(){$('#drviceslist').datagrid('reload');},'"+row.id+"')\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });   
});

function importDrvices(){
	$('#batchimport').linkbutton('disable');
	top.api.upload(function(name){
		top.api.post('${pageContext.request.contextPath}/drvices/importxls.action',{id:name},function(d){
			$('#batchimport').linkbutton('enable');
			$("#drviceslist").datagrid('reload');
		},function(d){
			$('#batchimport').linkbutton('enable');
			$("#drviceslist").datagrid('reload');
		});
	},
	'tmp',
	'uploadFile.action');
}

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('drvices',function(){$('#drviceslist').datagrid('reload');},'drviceslist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('drvices',function(){$('#drviceslist').datagrid('reload');},'drviceslist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
     <a href="#" onclick="importDrvices()" class="easyui-linkbutton" iconcls="icon-remove" plain="true" id="batchimport">批量导入</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="drviceslist"></table>
 </div>
</div>