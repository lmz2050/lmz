<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = l.btn("log",top.tmap[title]);

        l.dg("log",{
            columns:[[
                {field:'ck',checkbox:'true'},
                {field:'id',title:'<s:text name="admin.bean.id" />',width:'10%',align: 'center'},
                {field:'mac1',title:'MAC',width:'10%',align: 'center'},
                {field:'cus_name',title:'<s:text name="admin.drvices.cus_name" />',width:'15%',align: 'center'},
                {field:'dev_name',title:'<s:text name="admin.drvices.dev_name" />',width:'15%',align: 'center'},
                {field:'version',title:'<s:text name="admin.recv.version" />',width:'10%',align: 'center'},
                {field:'utm',title:'TIME',width:'10%',align: 'center'},
                {field:'path',title:'PATH',width:'20%',align: 'center'},
                {field:'action',title:'download',width:'10%',align:'center',
                    formatter:function(value,row,index){
                        var u = "<a href=\"#\" onclick=\"downlog('"+row.path+"')\" ><img src=\"${pageContext.request.contextPath}/plug-in/easyui1.5/themes/icons/filter.png\" /></a> ";
                        return u;
                    }
                }
            ]]
        });
    });


    function downlog(fname){
        fname = encodeURIComponent(fname);
        window.location.href='${pageContext.request.contextPath}/api/download.action?name='+fname;
    }
</script>
<div class="easyui-layout" fit="true" name="tb" id="log" >
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
