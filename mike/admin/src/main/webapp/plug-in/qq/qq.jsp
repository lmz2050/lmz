<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="l" uri="/lmz-tags" %>

<link href="${pageContext.request.contextPath}/plug-in/qq/lrtk.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/qq/start_v5.js"></script>

<!-- 代码 开始 -->
<div id="goto_top"></div>
	<div id="online_qq_layer" show="1" style="right: 0px; display: block;">
	    <div id="online_qq_tab">
	        <div class="online_icon">
	            <a title="Contact Us" id="floatTrigger" href="javascript:void(0);">在线客服</a>
	        </div>
	    </div>
  <div id="onlineService">
    <div class="online_windows overz">
      <div class="online_w_top"> </div>
      <!--online_w_top end-->
      <div class="online_w_c overz">
        <div class="online_bar collapse" id="onlineSort1">
          <h2> <a href="javascript:">在线客服</a> </h2>
          <div class="online_content overz" id="onlineType1" style="display: block;">
            <ul class="overz">
               <l:list tab="Sonline" ord=" ord ">
	           <li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=<s:property value="code"/>&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:<s:property value="code"/>:51" /></a></li>
	           </l:list>
	           <li>周一至周日全天</li>
              <li style="text-align:left;text-indent:0px;">电话：<s:property value="#webinfo.phone"/></li>
            </ul>
          </div>
          <!--online_content end-->
        </div>
      </div>
      <!--online_w_c end-->
      <div class="online_w_bottom"></div>
      <!--online_w_bottom end-->
    </div>
    <!--online_windows end-->
  </div>
</div>
<!-- 代码 结束 -->