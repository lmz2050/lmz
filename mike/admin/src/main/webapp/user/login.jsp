<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title></title>
  <!--[if lt IE 9]>
   <script src="${pageContext.request.contextPath}/user/js/html5.js"></script>
  <![endif]-->
  <!--[if lt IE 7]>
  <script src="${pageContext.request.contextPath}/user/js/iepng.js" type="text/javascript"></script>
  <script type="text/javascript">
	EvPNG.fix('div, ul, img, li, input'); //EvPNG.fix('包含透明PNG图片的标签'); 多个标签之间用英文逗号隔开。
</script>
  <![endif]-->
  <link href="${pageContext.request.contextPath}/user/css/zice.style.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/user/css/buttons.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/user/css/icon.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/user/css/tipsy.css" media="all" rel="stylesheet" type="text/css" />
  <style type="text/css">
html {
	background-image: none;
}

label.iPhoneCheckLabelOn span {
	padding-left: 0px
}

#versionBar {
	background-color: #212121;
	position: fixed;
	width: 100%;
	height: 35px;
	bottom: 0;
	left: 0;
	text-align: center;
	line-height: 35px;
	z-index: 11;
	-webkit-box-shadow: black 0px 10px 10px -10px inset;
	-moz-box-shadow: black 0px 10px 10px -10px inset;
	box-shadow: black 0px 10px 10px -10px inset;
}

.copyright {
	text-align: center;
	font-size: 10px;
	color: #CCC;
}

.copyright a {
	color: #A31F1A;
	text-decoration: none
}


/*update-begin--Author:tanghong  Date:20130419 for：【是否】按钮错位*/
.on_off_checkbox{
	width:0px;
}
/*update-end--Author:tanghong  Date:20130419 for：【是否】按钮错位*/

#login .logo {
	width: 500px;
	height: 51px;
}
</style>
 </head>
 <body>
  <div id="alertMessage"></div>
  <div id="successLogin"></div>
  <div class="text_success">
   <img src="${pageContext.request.contextPath}/user/images/loader_green.gif" alt="Please wait" />
   <span><s:text name="login.success" /></span>
  </div>
  <div id="login">
   <div class="ribbon" style="background-image:url(${pageContext.request.contextPath}/user/images/typelogin.png);"></div>
   <div class="inner">
    <div class="logo">
     <!--<img src="${pageContext.request.contextPath}/user/images/toplogo-jeeez.png"/>-->
     <span style="font-size:20px;font-weight: bold;padding-top:100px;"><s:text name="admin.title" /></span>
    </div>
    <div class="formLogin">
     <form name="formLogin" id="formLogin" action="${pageContext.request.contextPath}/user/login.action" check="${pageContext.request.contextPath}/user/check.action" method="post">
      <input name="userKey" type="hidden" id="userKey" value="D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900"/>
      <div class="tip">
       <input class="userName" name="info.username" type="text" id="userName" title="<s:text name="login.ipt.name" />" iscookie="true" value=""  nullmsg="<s:text name="login.msg.name" />"/>
      </div>
      <div class="tip">
       <input class="password" name="info.userpwd" type="password" id="password" title="<s:text name="login.ipt.pwd" />" value="" nullmsg="<s:text name="login.msg.pwd" />"/>
      </div>
      <div class="loginButton">
       <div style="float: left; margin-left: -9px;">
        <input type="checkbox" id="on_off" name="remember" checked="ture" class="on_off_checkbox" value="0" />
        <!-- <span class="f_help">是否记住用户名 ?</span>  -->
       </div>
       
       <div style="float: right; padding: 3px 0; margin-right: -12px;">
        <div>
         <ul class="uibutton-group">
          <li>
           <a class="uibutton normal" href="#" id="but_login"><s:text name="login.login" /></a>
          </li>
          <li>
           <a class="uibutton normal" href="#" id="forgetpass"><s:text name="login.reset" /></a>
          </li>
         </ul>
        </div>
        <div style="float: left; margin-left: 30px;">
       </div>
       </div>
       <div class="clear"></div>
      </div>
     </form>
    </div>
   </div>
   <div class="shadow"></div>
  </div>
  <!--Login div-->
  <div class="clear"></div>
  <div id="versionBar">
   <div class="copyright">
   <span class="tip"><s:text name="admin.title" /></span>
   </div>
  </div>
    <!-- Link JScript-->
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery.cookie.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery-jrumble.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery.tipsy.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/user/js/iphone.check.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/user/js/login.js"></script>
 </body>
</html>