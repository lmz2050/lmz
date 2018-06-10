<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = top.l.btn("cus",top.tmap[title]);

        top.l.dg("cus",{
            columns:[[
                {field:'ck',checkbox:'true'},
                {field:'id',title:'<s:text name="admin.bean.id" />',align: 'center'},
                {field:'phone',title:'phone',align: 'center'},
                {field:'name',title:'name',align: 'center'},
                {field:'sex',title:'sex',align: 'center'},
                {field:'wechat',title:'wechat',align: 'center'},
                {field:'mail',title:'mail',align: 'center'},
                {field:'job',title:'job',align: 'center'}
            ]]
        });
    });

</script>
<div class="easyui-layout" fit="true" name="tb" id="cus" >
    <div region="north" border="false" style="padding:5px;height:auto">
        <div style="margin-bottom:5px" class="dg_btn">
            <!--
            <a href="javascript:void(0)" onclick="l.delItems(this);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"><s:text name="system.btn.remove" /></a>
            -->
        </div>
        <div class="dg_search">
            phone: <input class="easyui-textbox" name="phone" value="" style="width:80px"/>
            <a href="javascript:void(0)" onclick="l.search(this);" class="easyui-linkbutton" iconCls="icon-search"><s:text name="system.btn.search" /></a>
        </div>
    </div>
    <div region="center" style="padding:0px;">
        <table class="dg_list"></table>
    </div>
</div>

