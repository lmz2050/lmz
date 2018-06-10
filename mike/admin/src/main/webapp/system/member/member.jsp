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
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/My97DatePicker/skin/WdatePicker.css" ></link>
	
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
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/smember/update.action">
	    	<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
	    	<table cellpadding="5" >
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.username" validType="user" data-options="required:true" value="<s:property value="info.username"/>" <s:if test="info.id!=null">readonly</s:if> /></td>
	    			<td>密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.userpwd" data-options="required:true,validType:'safepass'" value="<s:property value="info.userpwd"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>email:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.useremail" validType="email" data-options="required:true" value="<s:property value="info.useremail"/>" /></td>
	    			<td>会员等级:</td>
	    			<td>
	    				<select id="lev" class="easyui-combobox" name="info.lev" data-options="
							url:'${pageContext.request.contextPath}/ulev/findAll.action',
							method: 'get',
							valueField:'id',  
      						textField:'name',
							panelWidth: '150',
							panelHeight: '100',
							width:'150',
				            onLoadSuccess: function () { 
				            	$('#lev').combobox('setValue', '<s:property value="info.lev"/>');
				            }
						">
						</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.name"  value="<s:property value="info.name"/>" /></td>
	    			<td>地区:</td>
	    			<td>
	    			 	<%@ include file="../../common/area.jsp"%>
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td>地址:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.address" value="<s:property value="info.address"/>" /></td>
	    			<td>出生日期:</td>
	    			<td><input id="birthday" class="Wdate" type="text" name="info.birthday"  value="<s:property value="info.birthday"/>" onClick="WdatePicker()"/></td>
	    		</tr>
	    		<tr>
	    			<td>手机:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.mobile"  value="<s:property value="info.mobile"/>" /></td>
	    			<td>电话:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.tel"  value="<s:property value="info.tel"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>姓别:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.sex"  value="<s:property value="info.sex"/>" /></td>
	    			<td>邮编:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.zip"  value="<s:property value="info.zip"/>" /></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px;margin-bottom:0px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')"><s:text name="system.btn.submit" /></a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')"><s:text name="system.btn.reset" /></a>
	    </div>
	</body>
</html>
