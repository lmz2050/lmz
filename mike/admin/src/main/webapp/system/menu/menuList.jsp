<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div class="easyui-layout" fit="true">
<div region="north" border="false" style="BACKGROUND:#eee;height:30px; padding: 1px; overflow: hidden;">
     <a href="#" id="memuadd" onclick="api.toAdd('menu',function(){$('#ltree1').tree('reload');},'ltree1','tree')" class="easyui-linkbutton" iconcls="icon-add" plain="true">新增</a>
     <a href="#" class="easyui-linkbutton" onclick="api.toUpdate('menu',function(){$('#ltree1').tree('reload');},'ltree1','tree')" iconcls="icon-edit" plain="true">修改</a>
     <a href="#" class="easyui-linkbutton" onclick="api.del('menu',function(){$('#ltree1').tree('reload');},'ltree1','tree')" iconcls="icon-remove" plain="true">删除</a>
</div>

<div region="center" style="padding:10px;">
	<div style="width:180px;" data-options="region:'west',title:'所有菜单'" id="ttt"> 
	       <ul id="ltree1" class="easyui-tree" 
				 data-options=" 
	                 method:'get',
	                 lines:true,
	                 cache:false,
					 url:'${pageContext.request.contextPath}/menu/getUserMenu.action?url=json',
					 onLoadSuccess:function(){
					 	$('#ltree1').tree('expandAll');
					 }
			 "> 
		 </ul>
	</div>
</div>
</div>
