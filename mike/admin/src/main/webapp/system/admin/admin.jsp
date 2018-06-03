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
 	 $.extend($.fn.validatebox.defaults.rules, {
       equalTo: { validator: function (value, param) {return $(param[0]).val() == value; }, message: 'not match' }
     });
     
    <s:if test="r.success==true">
    	top.api.closeW();
    </s:if>
    <s:elseif test="r.msg!=''">
    	top.api.alert('<s:property value="r.msg" escapeHtml="false"/>');
    </s:elseif>
})
</script>
	</head>
	<body style="overflow-y: hidden;width:500px;" scroll="no">
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/admin/update.action">
	    	<input type="hidden" id="id" name="info.id" value="<s:property value="info.id"/>" />
	    	<input type="hidden" id="type" name="info.type" value="<s:property value="info.type"/>" />
	    	<table cellpadding="5">
	    		<tr>
	    			<td><s:text name="admin.user.username"/>:</td>
	    			<td><input class="easyui-textbox" id="username" type="text" name="info.username" data-options="required:true" value="<s:property value="info.username"/>" <s:if test="info.id!=null">readonly</s:if>  /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.user.userpwd"/>:</td>
	    			<td><input class="easyui-textbox" id="password"  type="password" name="info.userpwd" data-options="required:true,validType:'safepass'" value=""  /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.user.confirm_pwd"/>:</td>
	    			<td><input class="easyui-textbox" id="password1"  type="password"  data-options="required:true" validType="equalTo['#password']"  value=""  /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.user.mobile"/>:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.mobile" data-options="validType:'mobile'" value="<s:property value="info.mobile"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.user.mail"/>:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.useremail" data-options="validType:'email'" value="<s:property value="info.useremail"/>" /></td>
	    		</tr>
	    		<!--  
	    		<tr>
	    			<td><s:text name="admin.user.user_type"/>:</td>
	    			<td>
		    			<input type="radio" name="info.type" value="2" checked /><label>管理员</label>
		    			<input type="radio" name="info.type" value="1" /><label>超级管理员</label>
	    			</td>
	    		</tr>	
	    		-->  			    			    			    		
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')">Clear</a>
	    </div>
	</body>
</html>
