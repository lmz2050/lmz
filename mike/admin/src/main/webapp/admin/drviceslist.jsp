<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>



<script type="text/javascript">
    function importDrvices(){
        $('#batchimport').linkbutton('disable');
        top.api.upload(function(name){
                top.api.post('${pageContext.request.contextPath}/drvices/importxls.action',{id:name},function(d){
                    $('#batchimport').linkbutton('enable');
                    $("#drvices .dg_list").datagrid('reload');
                },function(d){
                    $('#batchimport').linkbutton('enable');
                    $("#drvices .dg_list").datagrid('reload');
                });
            },
            'tmp',
            'uploadFile.action');
    }

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = top.l.btn("drvices",top.tmap[title]);
        //alert('<s:property value="#session.admin.username" />');

        top.l.dg("drvices",{
            columns:[[
                {field:'ck',checkbox:'true'},
                {field:'id',title:'<s:text name="admin.bean.id" />',align: 'center'},
                {field:'cus_name',title:'<s:text name="admin.drvices.cus_name" />',align: 'center'},
                {field:'dev_name',title:'<s:text name="admin.drvices.dev_name" />',align: 'center'},
                {field:'mac1',title:'MAC1',align: 'center'},
                {field:'mac2',title:'MAC2',align: 'center'},
                f
                /*
                {field:'active',title:'<s:text name="admin.drvices.first" /> <s:text name="admin.drvices.active" />',width:'6%',align: 'center'},
                {field:'active_time',title:'<s:text name="admin.drvices.first" /> <s:text name="admin.drvices.active_time" />',width:'6%',align: 'center'},
                {field:'gn1',title:'gn1',width:'4%',align: 'center'},
                {field:'gn1_time',title:'gn1 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'},
                {field:'gn2',title:'gn2',width:'4%',align: 'center'},
                {field:'gn2_time',title:'gn2 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'},
                {field:'gn3',title:'gn3',width:'4%',align: 'center'},
                {field:'gn3_time',title:'gn3 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'},
                {field:'gn4',title:'gn4',width:'4%',align: 'center'},
                {field:'gn4_time',title:'gn4 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'},
                {field:'gn5',title:'gn5',width:'4%',align: 'center'},
                {field:'gn5_time',title:'gn5 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'},
                {field:'gn6',title:'gn6',width:'4%',align: 'center'},
                {field:'gn6_time',title:'gn6 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'},
                {field:'gn7',title:'gn7',width:'4%',align: 'center'},
                {field:'gn7_time',title:'gn7 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'},
                {field:'gn8',title:'gn8',width:'4%',align: 'center'},
                {field:'gn8_time',title:'gn8 <s:text name="admin.drvices.active_time" />',width:'4%',align: 'center'}
                */
            ]]
        });
    });

</script>
<div class="easyui-layout" fit="true" name="tb" id="drvices" >
    <div region="north" border="false" style="padding:5px;height:auto">
        <div style="margin-bottom:5px" class="dg_btn">
            <!--
            <a href="javascript:void(0)" onclick="l.toAdd(this);" name="add" class="easyui-linkbutton" iconCls="icon-add" plain="true"><s:text name="system.btn.add" /></a>
            <a href="javascript:void(0)" onclick="l.delItems(this);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"><s:text name="system.btn.remove" /></a>
            <a href="javascript:void(0)" onclick="importDrvices(this);" id="batchimport" class="easyui-linkbutton" iconCls="icon-undo" plain="true"><s:text name="system.btn.import" /></a>
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