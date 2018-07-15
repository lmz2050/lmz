<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/progress.js"></script>
		<script type="text/javascript">
			var prourl='<s:property value="#parameters.prourl"/>';

            $(function(){
                top.api.progress.init({barbox:$("#probarbox")});

                top.api.progress.getProgress(prourl);
			})
		</script>
	</head>
	<body style="overflow:hidden;width:260px;height:60px;padding:0px;margin:0px;border:none;" scroll="no">
		<table style="width:100%;height:100%;padding:0px;margin:0px;border:none;">
			<tr>
				<td id="probarbox"></td>
			</tr>
		</table>
	</body>
</html>
