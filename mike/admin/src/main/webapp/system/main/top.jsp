<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td align="left" style="vertical-align:text-top" >
		 <!--
		<img src="${pageContext.request.contextPath}/user/images/toplogo-main.png" />
		-->
     </td>
     <td align="right" nowrap>
      	<table>
       		<tr>
        		<td valign="top" height="50">
         			<span style="color: #CC33FF">当前用户:</span><span style="color: #666633">(<s:property value="#session.admin.username"/>)</span>
        		</td>
       		</tr>
       		<tr>
     			<td>
        			<div style="position: absolute; right: 0px; bottom: 0px;">
         				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a>
         				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
        			</div>
        			<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
         				<div onclick="api.toUpdate('admin',function(){},'<s:property value="#session.admin.id"/>')">个人信息</div>
         				<div class="menu-sep"></div>
         				<div onclick="api.toUrl('${pageContext.request.contextPath}/admin/toUppwd.action',function(){},'<s:property value="#session.admin.id"/>')">修改密码</div>
        			</div>
        			<div id="layout_north_zxMenu" style="width: 100px; display: none;">
         				<div class="menu-sep"></div>
         				<div onclick="api.confirm('确认退出?','${pageContext.request.contextPath}/user/loginout.action',1);">退出系统</div>
        			</div>
       			</td>
       		</tr>  
      	</table>
     </td>
     <td align="right">&nbsp;&nbsp;&nbsp;</td>
    </tr>
    <tr>
    	<td align="left" style="vertical-align:text-bottom" height="25">
	    	<div style="padding-left:170px;" id="ltreeT">
	    		<s:iterator value="infoL" var="tree">
	    			<a href="#" style="width:80px;" class="easyui-linkbutton" id='<s:property value="#tree.id" />' ><s:property value="#tree.text" /></a>
				</s:iterator>
	    	</div>
    	</td>
	    <td align="right">&nbsp;&nbsp;&nbsp;</td>
   </tr>
</table> 
