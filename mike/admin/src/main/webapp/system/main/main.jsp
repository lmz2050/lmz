<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title><s:text name="admin.title" /></title>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/dataformat.js"></script>
	<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icon.css" ></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/accordion/css/accordion.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery.easyui.min.js"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/syUtil.js"></script>
     <!--
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/My97DatePicker/WdatePicker.js"></script>
-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/lhgDialog/lhgdialog.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/easyuiextend.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/curdtools.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/lmzt/lmzt.js"></script>

     <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/sutil.js?dd=11" id="sutil" path="${pageContext.request.contextPath}"></script>

  <script type="text/javascript">
      var tmap={};
      api.code= {
          edit:'<s:text name="system.btn.edit" />',
          add:'<s:text name="system.btn.add" />',
          remove:'<s:text name="system.btn.remove" />',
          import:'<s:text name="system.btn.import" />'
      };
      /*
	$(function() {
		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});
	});
	*/
</script>
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

 </body>
</html>