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
         			<span style="color: #CC33FF"><s:text name="main.cur_user" />:</span><span style="color: #666633">(<s:property value="#session.admin.username"/>)</span>
        		</td>
       		</tr>
       		<tr>
     			<td>
        			<div style="position: absolute; right: 0px; bottom: 0px;">
						<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_lanMenu" iconCls="icon-back"><s:text name="main.language" /></a>
         				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help"><s:text name="main.control_panel" /></a>
         				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back"><s:text name="main.logout" /></a>
        			</div>
					<div id="layout_north_lanMenu" style="width: 100px; display: none;">
						<div onclick="l.setLan('zh_CN')">中文</div>
						<div class="menu-sep"></div>
						<div onclick="l.setLan('en_US')">English</div>
					</div>
        			<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
         				<div onclick="api.toUpdate('admin',function(){},'<s:property value="#session.admin.id"/>')"><s:text name="main.personal_information" /></div>
         				<div class="menu-sep"></div>
         				<div onclick="api.toUrl('${pageContext.request.contextPath}/admin/toUppwd.action',function(){},'<s:property value="#session.admin.id"/>')"><s:text name="main.change_password" /></div>
        			</div>
        			<div id="layout_north_zxMenu" style="width: 100px; display: none;">
         				<div class="menu-sep"></div>
         				<div onclick="api.confirm('<s:text name="main.confirm_quit" />','${pageContext.request.contextPath}/user/loginout.action',1);"><s:text name="main.logout_system" /></div>
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
