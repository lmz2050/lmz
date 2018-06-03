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
    <s:if test="r.success==true">
    	top.api.closeW();
    </s:if>
    <s:elseif test="r.msg!=''">
    	top.api.alert('<s:property value="r.msg" escapeHtml="false"/>');
    </s:elseif>
    
	$("#img").val('<s:property value="info.img"/>');
});
function sf(row){
	var csss='';
	if(row.attributes.lev<=0){
		csss+='font-weight:bold;';	
	}
	csss+="padding-left:"+(row.attributes.lev+1)+2+"px;";
	return '<span style="'+csss+'">' + row.text + '</span>';
}
</script>
	</head>
	<body style="overflow-y: hidden;width:500px;" scroll="no">
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/menu/update.action">
	    	<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
			<input type="hidden" name="info.isdev" value="<s:property value="info.isdev"/>" />
	    	<table cellpadding="5">
	    		<tr>
	    			<td>菜单名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.name" data-options="required:true" value="<s:property value="info.name"/>" /></td>
	    		</tr>
				<tr>
					<td>菜单英文名称:</td>
					<td><input class="easyui-textbox" type="text" name="info.ename" data-options="required:true" value="<s:property value="info.ename"/>" /></td>
				</tr>
	    		<tr>
	    			<td>上级菜单:</td>
	    			<td>
	    				<select id="pid" class="easyui-combobox" name="info.pid" data-options="
							url:'${pageContext.request.contextPath}/menu/findAll.action',
							method: 'get',
							valueField:'id',  
      						textField:'text',
							panelWidth: '200',
							panelHeight: '150',
							width:'200',
							formatter:sf,
				            onLoadSuccess: function () { 
				            	$('#pid').combobox('setValue', '<s:property value="info.pid"/>');
				            }
						">
						</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>URL:</td>
	    			<td><input id="uri" class="easyui-textbox" type="text" name="info.url" value="<s:property value="info.url"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>排序:</td>
	    			<td><input id="ord" class="easyui-numberbox" name="info.ord" data-options="required:true" style="width:80px" value="<s:property value="info.ord"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td>图标:</td>
	    			<td>
	    				<select id="img" class="easyui-combobox" name="info.img">
	    					<option value=""></option>
	    				</select>
	    			</td>
	    		</tr>
				<tr>
					<td>是否按钮:</td>
					<td>
						<input type="radio" name="info.type" value="1" <s:if test="info.type==1">checked</s:if> /><label>是</label>
						<input type="radio" name="info.type" value="0" <s:if test="info.type==0||info.type==null">checked</s:if> /><label>否</label>
					</td>
				</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')">Clear</a>
	    </div>
	</body>
</html>
