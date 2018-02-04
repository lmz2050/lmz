<%@ page language="java" import="java.util.Map" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>

<style type="text/css">
td{border:1px solid #000;}
</style>

 
<% 
Map<String,String> bmap = org.lmz.web.base.util.Context.getBeans(false);
%> 
    
<table>
<tr>
    <td>key</td>
    <td>val</td>
</tr>
<% 
for(Map.Entry<String,String> en:bmap.entrySet()){
	String key = en.getKey();
	String val = en.getValue();
%>
<tr>
    <td><%=key%></td>
    <td><%=val%></td>
</tr>
<% 
}
%>

</table>

  </body>
</html>
