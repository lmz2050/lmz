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
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/admin/uppwd.action">
	    	<input type="hidden" id="id" name="info.id" value="<s:property value="info.id"/>" />
	    	<table cellpadding="5">
	    		<tr>
	    			<td><s:text name="admin.user.old_userpwd"/>:</td>
	    			<td><input class="easyui-textbox" id="password2"  type="password" name="info.username" data-options="required:true,validType:'safepass'" value=""  /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.user.new_userpwd"/>:</td>
	    			<td><input class="easyui-textbox" id="password"  type="password" name="info.userpwd" data-options="required:true,validType:'safepass'" value=""  /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.user.confirm_pwd"/>:</td>
	    			<td><input class="easyui-textbox" id="password1"  type="password"  data-options="required:true" validType="equalTo['#password']"  value=""  /></td>
	    		</tr>    			    			    			    		
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')"><s:text name="system.btn.submit" /></a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')"><s:text name="system.btn.reset" /></a>
	    </div>
	</body>
</html>
