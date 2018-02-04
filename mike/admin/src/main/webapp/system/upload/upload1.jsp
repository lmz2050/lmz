<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
    <title>upload.jsp</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
   body{background:#eee;height:50px;overflow:hidden;padding:0px;margin:0px;}
   input{border:1px solid black;background:#fff;}
   form{height:30px;padding:0px;margin:0px;}
</style>
  </head> 
  <body>
    <form id="upload" name="upload" action="upload.action" method="post" enctype="multipart/form-data">
       <input type="file" id="file" name="file" value=""/>&nbsp;&nbsp;
       <input type="submit" id="upload_submit" name="submit" value="提交"/>
    </form>
    <script type="text/javascript"> 
    	<s:if test="r.success==true">
	    	top.api.closeW();
	    </s:if>
	    <s:elseif test="r.msg!=''">
	    	top.api.alert('<s:property value="r.msg" escapeHtml="false"/>');
	    </s:elseif>
    
         
       <s:if test="error=='success'">
           window.parent.upcallback("<s:property value="tagid"/>","<s:property value="fileFileName" escapeHtml="false"/>");
       </s:if>
       <s:elseif test="error!=null&&error!=''">
           alert("<s:property value="error"/>");
       </s:elseif> 
       var s = window.location.href.split('?');
       if(s[1]){
          document.getElementById("upload").action=document.getElementById("upload").action+"?"+s[1];
       } 
    </script>
  </body>
</html>