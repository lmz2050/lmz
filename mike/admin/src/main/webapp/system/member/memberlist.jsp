<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#memberlist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/smember/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'loadding......',
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'username',title:'用户',width:'10%',align: 'center'},
    	{field:'mobile',title:'手机',width:'10%',align: 'center'},
    	{field:'levname',title:'会员等级',width:'10%',align: 'center'},
    	{field:'useremail',title:'电子邮件',width:'10%',align: 'center'},
    	{field:'lastlogin',title:'上次登录日期',width:'10%',align: 'center'},
    	{field:'logincount',title:'本月登录次数',width:'10%',align: 'center'},
    	{field:'cityname',title:'城市',width:'10%',align: 'center'},
    	{field:'sex',title:'性别',width:'10%',align: 'center'},
    	{field:'login',title:'登录 ',width:'10%',align: 'center',
    		formatter:function(value,row,index){
                    var u = "<a href=\"${pageContext.request.contextPath}/smember/signin.action?id="+row.id+"\" target=\"_blank\" class=\"easyui-linkbutton\" iconcls=\"icon-add\" >登录</a> ";
                    return u;
            }
    	},
    	{field:'action',title:'修改',width:'10%',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('smember',function(){$('#memberlist').datagrid('reload');},'"+row.id+"')\" class=\"easyui-linkbutton\" iconcls=\"icon-add\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });  
});

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('smember',function(){$('#memberlist').datagrid('reload');},'memberlist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" onclick="api.del('smember',function(){$('#memberlist').datagrid('reload');},'memberlist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="memberlist"></table>
 </div>
</div>