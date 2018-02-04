<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
	<head>
		<title>菜单信息</title>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.js"></script>
	<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icon.css" ></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
$(function(){
    <s:if test="error=='success'">
    	top.api.closeU('<s:property value="fileFileName" escapeHtml="false"/>');
    </s:if>
    <s:elseif test="error!=null&&error!=''">
           top.api.alert("<s:property value="error" escapeHtml="false" />");
    </s:elseif>
})
</script>
	</head>
	<body style="overflow:hidden;width:260px;heght:60px;" scroll="no">
	    <form id="ff" class="easyui-form" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/<s:property value="#parameters.action"/>?ipath=<s:property value="#parameters.ipath"/>">
	    	<table cellpadding="5">
	    		<tr>
	    			<td><input class="easyui-filebox" id="file" name="file" data-options="required:true" value="" style="width:200px;"/></td>
	    			<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')">上传</a></td>
	    		</tr>
	    	</table>
	    </form>
	</body>
</html>
