<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
	<head>
		<title>详细信息</title>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.js"></script>
	<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icon.css" ></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
$(function(){
    <s:if test="r.success==true">
    	top.api.closeW();
    </s:if>
    <s:elseif test="r.msg!=''">
    	top.api.alert('<s:property value="r.msg" escapeHtml="false"/>');
    </s:elseif>
})

</script>

	</head>
	<body style="overflow-x:hidden;width:800px;" scroll="no" class="wbody">
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/tpl/update.action">
	    	<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
	    	<table cellpadding="5" >
	    		<tr>
	    			<td>name:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.name" data-options="required:true" value="<s:property value="info.name"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.cname" data-options="required:true" value="<s:property value="info.cname"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>action:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.action" value="<s:property value="info.action"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>atype:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.atype" value="<s:property value="info.atype"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>url:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.url" data-options="required:true" value="<s:property value="info.url"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>utype:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.utype" value="<s:property value="info.utype"/>" /></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px;margin-bottom:0px;">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')">Clear</a>
	    </div>
	</body>
</html>
