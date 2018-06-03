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
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-zh_CN.js"></script>

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
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/drvices/update.action">
	    	<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
	    	<table cellpadding="5" >
	    		<tr>
	    			<td><s:text name="admin.drvices.cus_name" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.cus_name" data-options="required:true" value="<s:property value="info.cus_name"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.drvices.dev_name" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.dev_name" data-options="required:true" value="<s:property value="info.dev_name"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>MAC1:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.mac1" data-options="required:true" value="<s:property value="info.mac1"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>MAC2:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.mac2" data-options="required:true" value="<s:property value="info.mac2"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.drvices.first" /><s:text name="admin.drvices.active" />:</td>
	    			<td>
	    				<input type="radio" name="info.active" value="Y" <s:if test="info.active==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.active" value="N" <s:if test="info.active==\"N\"||info.active==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td><s:text name="admin.drvices.first" /><s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.active_time" value="<s:property value="info.active_time"/>" /></td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN1:</td>
	    			<td>
	    				<input type="radio" name="info.gn1" value="Y" <s:if test="info.gn1==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn1" value="N" <s:if test="info.gn1==\"N\"||info.gn1==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN1<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn1_time" value="<s:property value="info.gn1_time"/>" /></td>
	    		</tr>		
	    		<tr>
	    			<td>GN2:</td>
	    			<td>
	    				<input type="radio" name="info.gn2" value="Y" <s:if test="info.gn2==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn2" value="N" <s:if test="info.gn2==\"N\"||info.gn2==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN2<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn2_time" value="<s:property value="info.gn2_time"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>GN3:</td>
	    			<td>
	    				<input type="radio" name="info.gn3" value="Y" <s:if test="info.gn3==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn3" value="N" <s:if test="info.gn3==\"N\"||info.gn3==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN3<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn3_time" value="<s:property value="info.gn3_time"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>GN4:</td>
	    			<td>
	    				<input type="radio" name="info.gn4" value="Y" <s:if test="info.gn4==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn4" value="N" <s:if test="info.gn4==\"N\"||info.gn4==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN4<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn4_time" value="<s:property value="info.gn4_time"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>GN5:</td>
	    			<td>
	    				<input type="radio" name="info.gn5" value="Y" <s:if test="info.gn5==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn5" value="N" <s:if test="info.gn5==\"N\"||info.gn5==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN5<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn5_time" value="<s:property value="info.gn5_time"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>GN6:</td>
	    			<td>
	    				<input type="radio" name="info.gn6" value="Y" <s:if test="info.gn6==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn6" value="N" <s:if test="info.gn6==\"N\"||info.gn6==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN6<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn6_time" value="<s:property value="info.gn6_time"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>GN7:</td>
	    			<td>
	    				<input type="radio" name="info.gn7" value="Y" <s:if test="info.gn7==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn7" value="N" <s:if test="info.gn7==\"N\"||info.gn7==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN7<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn7_time" value="<s:property value="info.gn7_time"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>GN8:</td>
	    			<td>
	    				<input type="radio" name="info.gn8" value="Y" <s:if test="info.gn8==\"Y\"">checked</s:if> /><label>Y</label>
		    			<input type="radio" name="info.gn8" value="N" <s:if test="info.gn8==\"N\"||info.gn8==null">checked</s:if> /><label>N</label>
		    		</td>
	    		</tr>	    		
	    		<tr>
	    			<td>GN8<s:text name="admin.drvices.active_time" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.gn8_time" value="<s:property value="info.gn8_time"/>" /></td>
	    		</tr>	    			    			    			    		
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px;margin-bottom:0px;">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')">Clear</a>
	    </div>
	</body>
</html>
