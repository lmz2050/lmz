<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <title><s:text name="admin.title" /></title>
     <meta http-equiv = "X-UA-Compatible" content ="IE=edge,chrome=1" />
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
     <link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/default/easyui.css" type="text/css" />
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/admin.css" />
 </head>
 <body class="easyui-layout" style="overflow-y: hidden" scroll="no">
  <!-- 顶部-->
  <div region="north" border="false" href="${pageContext.request.contextPath}/menu/getUserMenu.action?url=top"  title="<s:text name="admin.title" />" style="BACKGROUND:#fff;height: 117px; padding: 1px; overflow: hidden;"></div>
  <!-- 左侧-->
  <div region="west" id="left" split="true" href="${pageContext.request.contextPath}/menu/getUserMenu.action?url=left" title="<s:text name="main.left_menu" />" style="width: 170px; padding: 1px;"></div>
  <!-- 中间-->
  <div id="mainPanle" region="center" style="overflow: hidden;">
   <div id="maintabs" class="easyui-tabs" fit="true" border="false">
    <div class="easyui-tab" title="<s:text name="main.home" />" href="${pageContext.request.contextPath}/admin/home.action" style="padding:2px; overflow: hidden;">
   				 
    </div>
   </div>
  </div>
  <!-- 右侧 -->
  <!--
  <div collapsed="true"  region="east" iconCls="icon-reload" title="" split="true" style="width: 190px;">
   <div id="tabs"  class="easyui-tabs" border="false" style="height: 240px">
    <div title="" style="padding: 0px; overflow: hidden; color: red;">
     <div id="layout_east_calendar"></div>
    </div>
   </div>
   <div id="tabs" class="easyui-tabs" border="false">
    <div title="" style="padding: 20px; overflow: hidden; color: red;">
    </div>
   </div>
  </div>
  -->
  <!-- 底部 -->
  <div region="south" border="false" style="height: 25px; overflow: hidden;">
    <div align="center" style="color: #CC99FF; padding-top: 2px">
  &copy; <s:text name="main.copyright" />
    <span class="tip">@lmz</span>
   </div>
  </div>

  <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose"><s:text name="main.close"/></div>
        <div id="mm-tabcloseall"><s:text name="main.closeAll"/></div>
        <div id="mm-tabcloseother"><s:text name="main.closeOther"/></div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright"><s:text name="main.closeRight"/></div>
        <div id="mm-tabcloseleft"><s:text name="main.closeLeft"/></div>

</div>

  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/fix/jquery-migrate-1.2.1.js"></script>
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/plug-in/easyui1.5/fix/IE9.js"></script>
  <![endif]-->

  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery.easyui.min.js"></script>
  <s:if test="#session.lan=='en_US'">
      <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-en.js"></script>
  </s:if>
  <s:else>
      <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-zh_CN.js"></script>
  </s:else>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/leftmenu.js"></script>

  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/lhgDialog/lhgdialog.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/lmzt/lmzt.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/sutil.js?dd=11" id="sutil" path="${pageContext.request.contextPath}"></script>

  <script type="text/javascript">
      $(function(){
          top.api.code= {
              edit:'<s:text name="system.btn.edit" />',
              add:'<s:text name="system.btn.add" />',
              remove:'<s:text name="system.btn.remove" />',
              "importXls":'<s:text name="system.btn.import" />'
          };
      })
  </script>


 </body>
</html>