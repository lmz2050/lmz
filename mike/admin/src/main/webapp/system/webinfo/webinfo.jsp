<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<script type="text/javascript">

<s:if test="r.success==true">
	top.api.alert('已保存');
</s:if>

function uploadw(){
	top.api.upload(function(name){
		$("#webinfoimg").attr("src",'${pageContext.request.contextPath}/api/download.action?name='+name);
		$("#webinfoipt").val(name);
    },"patch","uploadPatch.action");
}

</script>



<div class="easyui-layout" fit="true" >
	<div region="center" style="padding:0px;">
	    <form id="ff" class="easyui-form" method="post" action="${pageContext.request.contextPath}/webinfo/update.action">
	    	<input type="hidden" name="info.id" value="<s:property value="info.id"/>" />
	    	<table cellpadding="5" >
	    		<tr>
	    			<td><s:text name="admin.web.web_name" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.name" data-options="required:true" value="<s:property value="info.name"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.web.domain" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.domain" data-options="required:true" value="<s:property value="info.domain"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.web.app_domain" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.mdomain" data-options="required:true" value="<s:property value="info.mdomain"/>" /></td>
	    		</tr>	    		
	    		<tr>
	    			<td><s:text name="admin.web.web" /> logo:</td>
	    			<td>	
	    					<input type="hidden" id="webinfoipt" name="info.logo" value="<s:property value="info.logo"/>" />
	    					<img id="webinfoimg" src="${pageContext.request.contextPath}/api/download.action?name=<s:property value="info.logo"/>" style="width:135px;height:100px;float:left;margin-left:10px;"/>
	    					<a id="sau" class="easyui-linkbutton" iconcls="icon-add" plain="true" href="javascript:void(0)" style="width:80px;float:left;margin-left:10px;height:35px;"  onclick="uploadw()" >上传</a>
					</td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.web.mobile" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.phone" value="<s:property value="info.phone"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.web.tel" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.tel" value="<s:property value="info.tel"/>" /></td>
	    		</tr>	    			    		
				<tr>
	    			<td><s:text name="admin.web.addr" />:</td>
	    			<td >
						<textarea id="addr" name="info.addr" style="width: 600px; height:50px;"><s:property value="info.addr"/></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.web.web_title" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.titles" value="<s:property value="info.titles"/>" /></td>
	    		</tr>
	    		<tr>
	    			<td><s:text name="admin.web.web_key" />:</td>
	    			<td><input class="easyui-textbox" type="text" name="info.keywords" value="<s:property value="info.keywords"/>" /></td>
	    		</tr>	    			    		
				<tr>
	    			<td><s:text name="admin.web.web_des" />:</td>
	    			<td >
						<textarea id="explains" name="info.explains" style="width: 600px; height:50px;"><s:property value="info.explains"/></textarea>
	    			</td>
	    		</tr>
				<tr>
					<td><s:text name="admin.web.mail_service" />:</td>
					<td><input class="easyui-textbox" type="text" name="info.mailfromhost" value="<s:property value="info.mailfromhost"/>" /></td>
				</tr>
				<tr>
					<td><s:text name="admin.web.mail_sender_user" />:</td>
					<td><input class="easyui-textbox" type="text" name="info.mailfromuname" value="<s:property value="info.mailfromuname"/>" /></td>
				</tr>
				<tr>
					<td><s:text name="admin.web.mail_sender_pwd" />:</td>
					<td><input class="easyui-textbox" type="password" name="info.mailfrompwd" value="<s:property value="info.mailfrompwd"/>" /></td>
				</tr>
				<tr>
					<td><s:text name="admin.web.mail_receive_user" />:</td>
					<td><input class="easyui-textbox" type="text" name="info.mailtohost" value="<s:property value="info.mailtohost"/>" /></td>
				</tr>
				<tr>
	    			<td colspan="2">
		    			<div style="text-align:center;padding:5px;margin-bottom:0px;">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('submit')"><s:text name="system.btn.submit" /></a>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#ff').form('clear')"><s:text name="system.btn.reset" /></a>
					    </div>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</div>	    