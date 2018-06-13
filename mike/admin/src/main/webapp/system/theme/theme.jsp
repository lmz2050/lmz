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

function upload(ii){
	top.api.upload(function(name){
		$("#advimg").attr("src",'${pageContext.request.contextPath}'+name);
		$("#advipt").val(name);
	});
}
</script>

	</head>
	<body style="overflow-x:hidden;width:800px;" scroll="no" class="wbody">
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/theme/update.action">
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
	    			<td>图片:</td>
	    			<td>
	    				<input type="hidden" id="advipt" name="info.img" value="<s:property value="info.img"/>" />
	    				<img id="advimg" src="${pageContext.request.contextPath}<s:property value="info.img"/>" style="width:135px;height:100px;float:left;margin-left:10px;"/>
	    				<a id="sau" class="easyui-linkbutton" iconcls="icon-add" plain="true" href="javascript:void(0)" style="width:80px;float:left;margin-left:10px;height:35px;"  onclick="upload()" >上传图片</a>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>排序:</td>
	    			<td><input class="easyui-numberbox" type="text" name="info.ord" value="<s:property value="info.ord"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>是否默认:</td>
	    			<td>
	    				<input type="radio" name="info.def" value="Y" <s:if test="info.def==\"Y\"">checked</s:if> /><label>是</label>
		    			<input type="radio" name="info.def" value="N" <s:if test="info.def==\"N\"||info.def==null">checked</s:if> /><label>否</label>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px;margin-bottom:0px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')"><s:text name="system.btn.submit" /></a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')"><s:text name="system.btn.reset" /></a>
	    </div>
	</body>
</html>
