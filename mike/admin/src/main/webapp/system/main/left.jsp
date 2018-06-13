<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/leftmenu.js"></script>

<div id="nav" class="easyui-accordion" border="false" >
	<s:iterator value="infoL" var="tree0" > 
			<s:iterator value="#tree0.children" var="tree" >
				<div id="ltree<s:property value="#tree.id"/>" pid="<s:property value="#tree.pid"/>" title="<s:property value="#tree.text"/>" iconCls="<s:property value="#tree.attributes.iconCls"/>" idx="<s:property value="#tree.attributes.idx"/>">
					<ul>
						<s:iterator value="#tree.children" var="ctree">
							<li>
								<div title="<s:property value="#ctree.text"/>" tid="<s:property value="#ctree.id"/>" url="${pageContext.request.contextPath}/<s:property value="#ctree.attributes.url"/>" iconCls="<s:property value="#ctree.attributes.iconCls"/>">
									<a class="<s:property value="#ctree.text"/>" href="#" >
										<span class="icon <s:property value="#ctree.attributes.iconCls"/>" >&nbsp;</span> 
										<span class="nav" ><s:property value="#ctree.text"/></span>
									</a>
								</div>
							</li>
						</s:iterator>
					</ul>
				</div>
			</s:iterator>
	</s:iterator>	
</div>
  
