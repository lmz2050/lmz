<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#adminlist').datagrid({
    width:'100%',  
    height:300,               
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/admin/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'loadding……',
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'id',title:'<s:text name="admin.user.serialno"/>',align: 'center'},
    	{field:'username',title:'<s:text name="admin.user.username"/>',align: 'center'},
    	{field:'mobile',title:'<s:text name="admin.user.mobile"/>',align: 'center'},
    	{field:'useremail',title:'<s:text name="admin.user.mail"/>',align: 'center'},
    	{field:'lastlogin',title:'<s:text name="admin.user.last_login_time"/>',align: 'center'},
    	{field:'type',title:'<s:text name="admin.user.user_type"/>',align: 'center'},
    	{field:'action1',title:'<s:text name="admin.user.user_role"/>',align:'center',
            formatter:function(value,row,index){
            		var r = "<a href=\"#\" onclick=\"setUserrole('"+row.id+"','"+row.username+"')\"  ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return r;
            }
        },
    	{field:'action',title:'<s:text name="system.btn.edit"/>',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('admin',function(){$('#adminlist').datagrid('reload');},'"+row.id+"')\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });   
});
var uid;
function setUserrole(id,name) {
	$("#role-panel").panel(
		{
			title :id+"-"+name+":<s:text name="admin.user.curr_user_role"/>",
			tools:[{iconCls:'icon-save',handler:function(){
				var ids = getCheckedu();
                top.api.post('${pageContext.request.contextPath}/role/setUserRole.action',{id:uid,msg:ids},function(){
					api.get('${pageContext.request.contextPath}/role/findUserRole.action',{id:id},function(d){
						$("#ltreeuser").tree("loadData",d);
						$("#ltreeuser").tree("expandAll");
						alert('<s:text name="system.msg.saved" />');
					});
				});
			}}]
		}
	);
	uid=id;
    top.api.get('${pageContext.request.contextPath}/role/findUserRole.action',{id:id},function(d){
		$("#ltreeuser").tree("loadData",d);
		$("#ltreeuser").tree("expandAll");
	});
	$("#lbtn1").show();
}

function getCheckedu(){
     var nodes = $('#ltreeuser').tree('getChecked');
     var s = '';
     for(var i=0; i<nodes.length; i++){
         if (s != '') s += ',';
         s += nodes[i].id;
     }
     return s;
}
function selecrAllu() {
	var node = $('#ltreeuser').tree('getRoots');
	for ( var i = 0; i < node.length; i++) {
		var childrenNode =  $('#ltreeuser').tree('getChildren',node[i].target);
		for ( var j = 0; j < childrenNode.length; j++) {
			$('#ltreeuser').tree("check",childrenNode[j].target);
		}
		$('#ltreeuser').tree("check",node[i].target);
    }
}
function resetu() {
    top.api.get('${pageContext.request.contextPath}/role/findUserRole.action',{id:rid},function(d){
		$("#ltreeuser").tree("loadData",d);
		$("#ltreeuser").tree("expandAll");
	});
}

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('admin',function(){$('#adminlist').datagrid('reload');},'adminlist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true"><s:text name="system.btn.add"/></a>
     <a href="#" onclick="api.del('admin',function(){$('#adminlist').datagrid('reload');},'adminlist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true"><s:text name="system.btn.remove"/></a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="adminlist"></table>
 </div>
 <div region="east"  id="rr" style="width:15%;" split="true">
  	<div tools="#t1" class="easyui-panel" title="<s:text name="admin.user.role_setting" />" style="padding: 10px;" fit="true" border="false" id="role-panel">
  		<div style="text-align: center;display:none;padding-bottom:10px;" id="lbtn1">
  			<a style="width:50px;" class="easyui-linkbutton" onclick="selecrAllu();"><s:text name="system.btn.selectAll"/></a>&nbsp;&nbsp;
  			<a style="width:50px;" class="easyui-linkbutton"  onclick="resetu();"><s:text name="system.btn.reset"/></a>
  		</div>
  		<ul id="ltreeuser" class="easyui-tree" 
				 data-options=" 
	                 method:'get',
	                 lines:true,
	                 cache:false,
	                 animate:true,
	                 checkbox:true
			 "> 
		 </ul>
  	</div>
 </div>
</div>