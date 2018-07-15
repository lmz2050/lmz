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


function upload(ii){
    try {
        $('#uploadbtn').linkbutton('disable');
        top.api.upload(function (name) {
            if (name != null && name != '') {
                $("#url").textbox('setValue', name);
                $("#url").textbox('textbox').focus();
                $('#uploadbtn').linkbutton('enable');
            } else {
                $("#url").textbox('setValue', name);
                $("#url").textbox('textbox').focus();
                $('#uploadbtn').linkbutton('enable');
            }
        }, "patch", "uploadPatch.action");
    }catch (e){console.log(e)}
}
</script>

	</head>
	<body style="overflow-x:hidden;width:800px;" scroll="no" class="wbody">
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/patch/update.action">
	    	<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
	    	<table cellpadding="5" >
	    		<tr>
	    			<td><s:text name="admin.patch.vname" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.vname" data-options="required:true" value="<s:property value="info.vname"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.patch.okupdate" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.okupdata" data-options="required:true" value="<s:property value="info.okupdata"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>Url:</td>
	    			<td>
	    			<input class="easyui-textbox" type="text" id="url" name="info.url" data-options="required:true,readonly:true" value="<s:property value="info.url"/>" />
	    			<a id="uploadbtn" class="easyui-linkbutton" iconcls="icon-add" plain="true" href="javascript:void(0)"  onclick="upload()" >upload</a>
	    			</td>
	    		</tr>    		
	    		<tr>
	    			<td><s:text name="admin.patch.remark" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.remark" value="<s:property value="info.remark"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.patch.cby" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.cby" data-options="readonly:true" value="<s:property value="info.cby"/>" /></td>
	    		</tr>	    		
	    		<tr>
	    			<td><s:text name="admin.patch.ctm" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.utm" data-options="readonly:true" value="<s:property value="info.utm"/>" /></td>
	    		</tr>	    			    			    			    		
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px;margin-bottom:0px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')"><s:text name="system.btn.submit" /></a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')"><s:text name="system.btn.reset" /></a>
	    </div>
	</body>
</html>
