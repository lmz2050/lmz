<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
	<head>
		<title><s:text name="admin.detail.title" /></title>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.js"></script>
	<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icon.css" ></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery.easyui.min.js"></script>
		<s:if test="#session.lan=='en_US'">
			<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-en.js"></script>
		</s:if>
		<s:else>
			<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-zh_CN.js"></script>
		</s:else>
<script type="text/javascript">
$(function(){
    <s:if test="r.success==true">
    	top.api.closeW();
    </s:if>
    <s:elseif test="r.msg!=''">
    	top.api.alert('<s:property value="r.msg" escapeHtml="false"/>');
    </s:elseif>
    
});

</script>

	</head>
	<body style="overflow-x:hidden;width:800px;" scroll="no" class="wbody">
		<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
		<table cellpadding="5" >
			<tr>
				<td>客户名称:</td>
				<td><s:property value="info.cus_name"/></td>
			</tr>
			<tr>
				<td>设备名称:</td>
				<td><s:property value="info.dev_name"/></td>
			</tr>
			<tr>
				<td>版本:</td>
				<td>
					<s:property value="info.version"/>
				</td>
			</tr>
			<tr>
				<td>时间:</td>
				<td>
					<s:property value="info.utm"/>
				</td>
			</tr>
			<tr>
				<td>LOG:</td>
				<td><s:property value="info.content"/></td>
			</tr>

		</table>
	</body>
</html>
