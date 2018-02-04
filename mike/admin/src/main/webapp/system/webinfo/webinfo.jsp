<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<script type="text/javascript">

<s:if test="r.success==true">
	top.api.alert('已保存');
</s:if>

function uploadw(ii){
	top.api.upload(function(name){
		$("#webinfoimg").attr("src",'${pageContext.request.contextPath}'+name);
		$("#webinfoipt").val(name);
	});
}

</script>

<div class="easyui-layout" fit="true" >
	<div region="center" style="padding:0px;">
	    <form id="ffw" class="easyui-form" method="post" action="${pageContext.request.contextPath}/webinfo/update.action">
	    	<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
	    	<table cellpadding="5" >
	    		<tr>
	    			<td>网站名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.name" data-options="required:true" value="<s:property value="info.name"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>域名:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.domain" data-options="required:true" value="<s:property value="info.domain"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>移动域名:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.mdomain" data-options="required:true" value="<s:property value="info.mdomain"/>" /></td>
	    		</tr>	    		
	    		<tr>
	    			<td>网站logo:</td>
	    			<td>	
	    					<input type="hidden" id="webinfoipt" name="info.logo" value="<s:property value="info.logo"/>" />
	    					<img id="webinfoimg" src="${pageContext.request.contextPath}<s:property value="info.logo"/>" style="width:135px;height:100px;float:left;margin-left:10px;"/>
	    					<a id="sau" class="easyui-linkbutton" iconcls="icon-add" plain="true" href="javascript:void(0)" style="width:80px;float:left;margin-left:10px;height:35px;"  onclick="uploadw()" >上传</a>
					</td>
	    		</tr>
	    		<tr>
	    			<td>手机号码:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.phone" value="<s:property value="info.phone"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>电话号码:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.tel" value="<s:property value="info.tel"/>" /></td>
	    		</tr>	    			    		
				<tr>
	    			<td>地址:</td>
	    			<td >
						<textarea id="content" name="info.addr" style="width: 600px; height:50px;"><s:property value="info.addr"/></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>网站标题:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.titles" value="<s:property value="info.titles"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>网站关键字:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.keywords" value="<s:property value="info.keywords"/>" /></td>
	    		</tr>	    			    		
				<tr>
	    			<td>网站说明:</td>
	    			<td >
						<textarea id="content" name="info.explains" style="width: 600px; height:50px;"><s:property value="info.explains"/></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2">
		    			<div style="text-align:center;padding:5px;margin-bottom:0px;">
					    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ffw').form('submit')">Submit</a>
					    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ffw').form('clear')">Clear</a>
					    </div>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</div>	    