<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
$(function() {

$('#rolelist').datagrid({
    width:'100%',
    striped: true,  
    singleSelect :true,
    pageSize: 10,
    fit: true,
    pageList: [5,10,15], 
    url:'${pageContext.request.contextPath}/role/apage.action',  
    //queryParams:{path:'test'},  
    loadMsg:'loadding......',
    pagination: true,
    rownumbers: true,
    columns:[[ 
    	{field:'id',title:'<s:text name="admin.role.serialno" />',align: 'center'},
    	{field:'name',title:'<s:text name="admin.role.name" />',align: 'center'},
    	{field:'action1',title:'<s:text name="admin.role.role_setting" />',align:'center',
            formatter:function(value,row,index){
            		var r = "<a href=\"#\" onclick=\"setMenubyrole('"+row.id+"','"+row.name+"')\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return r;
            }
        },
    	{field:'action',title:'<s:text name="system.btn.edit" />',align:'center',
            formatter:function(value,row,index){
                    var u = "<a href=\"#\" onclick=\"api.toUpdate('role',function(){$('#rolelist').datagrid('reload');},'"+row.id+"')\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/edit_add1.png\" /></a> ";
                    return u;
            }
        }                                                     
    ]]
    });  
});
var rid;
function setMenubyrole(id,roleName) {
	$("#function-panel").panel(
		{
			title :id+"-"+roleName+":<s:text name="admin.role.curr_permissions" />",
			tools:[{iconCls:'icon-save',handler:function(){
					var ids = getChecked();
                top.api.post('${pageContext.request.contextPath}/role/setRoleMenu.action',{id:rid,msg:ids},function(){
                    top.api.get('${pageContext.request.contextPath}/menu/getRoleMenu.action',{id:rid},function(d){
							showTree(d);
							alert("<s:text name="system.msg.saved" />");
						});
					});
			}}]
		}
	);
	rid=id;
    top.api.get('${pageContext.request.contextPath}/menu/getRoleMenu.action',{id:id},function(d){
		showTree(d);
		//$("#ltree").tree("expandAll");
	});
	$("#lbtn").show();
}

function showTree(tdata){
	$("#ltree").tree({
        data: tdata,
        checkbox: true,
        lines:true,
        animate:true,
        cascadeCheck: false,
        onCheck: function (node, checked) {
          if (checked) {
            var parentNode = $("#ltree").tree('getParent', node.target);
            if (parentNode != null) {
              $("#ltree").tree('check', parentNode.target);
            }
          } else {
            var childNode = $("#ltree").tree('getChildren', node.target);
            if (childNode.length > 0) {
              for (var i = 0; i < childNode.length; i++) {
                $("#ltree").tree('uncheck', childNode[i].target);
              }
            }
          }
        }
      });
}

function getChecked(){
	//var ooo = $("#ltree").tree('options');
	//alert(ooo);
	
     var nodes = $('#ltree').tree('getChecked');
     //var nodes = $('#ltree').tree('getChecked',['indeterminate']);
     var s = '';
     for(var i=0; i<nodes.length; i++){
         if (s != '') s += ',';
         s += nodes[i].id;
     }
     //alert(s);
     return s;
}
function selecrAll() {
	var node = $('#ltree').tree('getRoots');
	for ( var i = 0; i < node.length; i++) {
		var childrenNode =  $('#ltree').tree('getChildren',node[i].target);
		for ( var j = 0; j < childrenNode.length; j++) {
			$('#ltree').tree("check",childrenNode[j].target);
		}
		$('#ltree').tree("check",node[i].target);
    }
}
function reset() {
    top.api.get('${pageContext.request.contextPath}/menu/getRoleMenu.action',{id:rid},function(d){
		showTree(d);
		//$("#ltree").tree("expandAll");
	});
}

</script>
<div class="easyui-layout" fit="true" >
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" onclick="api.toAdd('role',function(){$('#rolelist').datagrid('reload');},'rolelist','datagrid')" class="easyui-linkbutton" iconcls="icon-add" plain="true"><s:text name="system.btn.add" /></a>
     <a href="#" onclick="api.del('role',function(){$('#rolelist').datagrid('reload');},'rolelist','datagrid')" class="easyui-linkbutton" iconcls="icon-remove" plain="true"><s:text name="system.btn.remove" /></a>
</div>
 <div region="center" style="padding:0px;">
  	<table id="rolelist"></table>
 </div>
 <div region="east"  id="rr" style="width:20%;" split="true">
  	<div tools="#tt" class="easyui-panel" title="<s:text name="admin.user.role_setting" />" style="padding: 10px;" fit="true" border="false" id="function-panel">
  		<div style="text-align: center;display:none;padding-bottom:10px;" id="lbtn">
  			<a style="width:50px;" class="easyui-linkbutton" onclick="selecrAll();"><s:text name="system.btn.selectAll"/></a>&nbsp;&nbsp;
  			<a style="width:50px;" class="easyui-linkbutton"  onclick="reset();"><s:text name="system.btn.reset"/></a>
  		</div>
  		<ul id="ltree" class="easyui-tree" > </ul>
  	</div>
 </div>
</div>