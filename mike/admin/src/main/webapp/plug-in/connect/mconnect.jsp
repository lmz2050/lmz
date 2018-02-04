<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="l" uri="/lmz-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<body>
<link  href="${pageContext.request.contextPath}/plug-in/connect/connect.css"  rel="stylesheet" type="text/css" />

<div class="connect">
  <a class="qq_c" href="${pageContext.request.contextPath}/mobile/qq/login.action" title="QQ登录"></a>
  <a class="sina_c" href="${pageContext.request.contextPath}/mobile/sina/login.action" title="新浪登录"></a>
  <a class="alipay_c" href="${pageContext.request.contextPath}/mobile/alipay/login.action" title="支付宝登录"></a>
  <a class="wx_c" href="${pageContext.request.contextPath}/mobile/wx/login.action" title="微信登录"></a>
</div>  
</body>
</html>
