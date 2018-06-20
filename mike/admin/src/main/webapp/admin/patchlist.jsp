<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = top.l.btn("patch",top.tmap[title]);

        top.l.dg("patch",{
            columns:[[
                {field:'ck',checkbox:'true'},
                {field:'id',title:'<s:text name="admin.bean.id" />',align: 'center'},
                {field:'vname',title:'<s:text name="admin.patch.vname" />',align: 'center'},
                {field:'okupdata',title:'<s:text name="admin.patch.okupdate" />',align: 'center'},
                {field:'url',title:'<s:text name="admin.patch.url" />',width:'20%',align: 'center'},
                {field:'remark',title:'<s:text name="admin.patch.remark" />',align: 'center'},
                {field:'cby',title:'<s:text name="admin.patch.cby" />',align: 'center'},
                {field:'ctm',title:'<s:text name="admin.patch.ctm" />',align: 'center'},
                f
            ]]
        });
    });

</script>
<div class="easyui-layout" fit="true" name="tb" id="patch" >
    <div region="north" border="false" style="padding:5px;height:auto">
        <div style="margin-bottom:5px" class="dg_btn">
            <!--
            <a href="javascript:void(0)" onclick="l.toAdd(this);" name="add" class="easyui-linkbutton" iconCls="icon-add" plain="true"><s:text name="system.btn.add" /></a>
            <a href="javascript:void(0)" onclick="l.delItems(this);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"><s:text name="system.btn.remove" /></a>
            -->
        </div>
        <div class="dg_search">
            <s:text name="admin.patch.vname" />: <input class="easyui-textbox" name="vname" value="" style="width:80px"/>
            <s:text name="admin.patch.cby" />: <input class="easyui-textbox" name="cby" value="" style="width:80px"/>
            <a href="javascript:void(0)" onclick="l.search(this);" class="easyui-linkbutton" iconCls="icon-search"><s:text name="system.btn.search" /></a>
        </div>
    </div>
    <div region="center" style="padding:0px;">
        <table class="dg_list"></table>
    </div>
</div>
