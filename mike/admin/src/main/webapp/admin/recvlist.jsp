<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = l.btn("recv",top.tmap[title]);

        l.dg("recv",{
            columns:[[
                {field:'ck',checkbox:'true'},
                {field:'id',title:'<s:text name="admin.bean.id" />',width:'5%',align: 'center'},
                {field:'cus_name',title:'<s:text name="admin.drvices.cus_name" />',width:'7%',align: 'center'},
                {field:'dev_name',title:'<s:text name="admin.drvices.dev_name" />',width:'7%',align: 'center'},
                {field:'mac1',title:'MAC1',width:'5%',align: 'center'},
                {field:'mac2',title:'MAC2',width:'5%',align: 'center'},
                {field:'version',title:'<s:text name="admin.recv.version" />',width:'5%',align: 'center'},
                {field:'ol_time',title:'<s:text name="admin.recv.ol_time" />',width:'5%',align: 'center'},
                {field:'ol_total',title:'<s:text name="admin.recv.ol_total" />',width:'5%',align: 'center'},
                {field:'gn1_total',title:'gn1 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'},
                {field:'gn2_total',title:'gn2 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'},
                {field:'gn3_total',title:'gn3 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'},
                {field:'gn4_total',title:'gn4 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'},
                {field:'gn5_total',title:'gn5 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'},
                {field:'gn6_total',title:'gn6 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'},
                {field:'gn7_total',title:'gn7 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'},
                {field:'gn8_total',title:'gn8 <s:text name="admin.recv.use_time" />',width:'7%',align: 'center'}
            ]]
        });
    });

</script>
<div class="easyui-layout" fit="true" name="tb" id="recv" >
    <div region="north" border="false" style="padding:5px;height:auto">
        <div style="margin-bottom:5px" class="dg_btn">
            <!--
            <a href="javascript:void(0)" onclick="l.delItems(this);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"><s:text name="system.btn.remove" /></a>
            -->
        </div>
        <div class="dg_search">
            <s:text name="admin.drvices.cus_name" />: <input class="easyui-textbox" name="cus_name" value="" style="width:80px"/>
            <s:text name="admin.drvices.dev_name" />: <input class="easyui-textbox" name="dev_name" value="" style="width:80px"/>
            <a href="javascript:void(0)" onclick="l.search(this);" class="easyui-linkbutton" iconCls="icon-search"><s:text name="system.btn.search" /></a>
        </div>
    </div>
    <div region="center" style="padding:0px;">
        <table class="dg_list"></table>
    </div>
</div>