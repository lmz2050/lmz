<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = top.l.btn("send",top.tmap[title]);

        top.l.dg("send",{
            columns:[[
                {field:'ck',checkbox:'true'},
                //{field:'id',title:'<s:text name="admin.bean.id" />',align: 'center'},
                {field:'cus_name',title:'<s:text name="admin.drvices.cus_name" />',align: 'center'},
                {field:'dev_name',title:'<s:text name="admin.drvices.dev_name" />',align: 'center'},
                {field:'sysname',title:'<s:text name="admin.send.sysname" />',align: 'center'},
                {field:'sysversion',title:'<s:text name="admin.send.sysversion" />',align: 'center'},
                {field:'appversion',title:'<s:text name="admin.send.appversion" />',align: 'center'},
                {field:'ol_time',title:'<s:text name="admin.recv.ol_time" />',align: 'center'},
                {field:'ol_total',title:'<s:text name="admin.recv.ol_total" />',align: 'center'}
            ]]
        });
    });

</script>
<div class="easyui-layout" fit="true" name="tb" id="send" >
    <div region="north" border="false" style="padding:5px;height:auto">
        <div style="margin-bottom:5px" class="dg_btn">
            <!--
            <a href="javascript:void(0)" onclick="l.delItems(this);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"><s:text name="system.btn.remove" /></a>
            -->
        </div>
        <div class="dg_search">
            <s:if test="#session.admin.type==1">
                <s:text name="admin.drvices.cus_name" />: <input class="easyui-textbox" name="cus_name" value="" style="width:80px"/>
            </s:if>
            <s:text name="admin.drvices.dev_name" />: <input class="easyui-textbox" name="dev_name" value="" style="width:80px"/>
            <a href="javascript:void(0)" onclick="l.search(this);" class="easyui-linkbutton" iconCls="icon-search"><s:text name="system.btn.search" /></a>
        </div>
    </div>
    <div region="center" style="padding:0px;">
        <table class="dg_list"></table>
    </div>
</div>
