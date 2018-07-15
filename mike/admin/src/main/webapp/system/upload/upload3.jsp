<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>菜单信息</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/progress.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery.form.min.js"></script>
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
            function saveDate() {
                var Jform = $("#ff");
                var proaction = Jform.attr("proaction");
                var action = Jform.attr("action");
                top.api.progress.getProgress(proaction);

                var formData = new FormData();
                formData.append('file', $("input[name='file']")[0].files[0]);
                //调用apicontroller后台action方法，将form数据传递给后台处理。contentType必须设置为false,否则chrome和firefox不兼容
                $.ajax({
                    url: action,
                    type: 'POST',
                    data: formData,
                    async: true,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        alert(data);
                        data = eval('(' + data + ')');
                        if (data.success) {
                            top.api.closeU(data.obj);
                        } else {
                            console.log("error:" + data.msg);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log(XMLHttpRequest + "---" + textStatus + "---" + errorThrown);
                    }
                });
            }
            $(function(){
                top.api.progress.init({barbox:$("#probarbox")});	``
			})
		</script>
	</head>
	<body style="overflow:hidden;width:260px;heght:60px;" scroll="no">
	    <form id="ff" method="post" enctype="multipart/form-data" proaction="${pageContext.request.contextPath}/getProgress.action"
			  action="${pageContext.request.contextPath}/<s:property value="#parameters.action"/>?ipath=<s:property value="#parameters.ipath"/>">
		<table >
			<tr>
				<td><input class="easyui-filebox" id="file" name="file" data-options="required:true" value="" style="width:200px;"/></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveDate()">upload</a></td>
			</tr>
			<tr>
				<td id="probarbox"></td>
			</tr>
		</table>
		</form>
	</body>
</html>
