<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = l.btn("patch",top.tmap[title]);

        l.dg("patch",{
            columns:[[
                {field:'ck',checkbox:'true'},
                {field:'<s:text name="admin.patch.id" />',title:'ID',width:'8%',align: 'center'},
                {field:'vname',title:'<s:text name="admin.patch.vname" />',width:'8%',align: 'center'},
                {field:'okupdata',title:'<s:text name="admin.patch.okupdata" />',width:'10%',align: 'center'},
                {field:'url',title:'Url',width:'20%',align: 'center'},
                {field:'<s:text name="admin.patch.remark" />',title:'Explain',width:'30%',align: 'center'},
                {field:'cby',title:'<s:text name="admin.patch.cby" />',width:'10%',align: 'center'},
                {field:'ctm',title:'<s:text name="admin.patch.ctm" />',width:'10%',align: 'center'},
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
