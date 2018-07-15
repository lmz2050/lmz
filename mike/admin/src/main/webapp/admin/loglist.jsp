<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

    $(function() {

        var title = $('#maintabs .tabs-selected').text();
        var f = top.l.btn("log",top.tmap[title]);

        top.l.dg("log",{
            columns:[[
                {field:'ck',checkbox:'true'},
                //{field:'id',title:'<s:text name="admin.bean.id" />',align: 'center'},
                {field:'mac1',title:'MAC',align: 'center'},
                {field:'cus_name',title:'<s:text name="admin.drvices.cus_name" />',align: 'center'},
                {field:'dev_name',title:'<s:text name="admin.drvices.dev_name" />',align: 'center'},
                {field:'version',title:'<s:text name="admin.recv.version" />',align: 'center'},
                {field:'utm',title:'TIME',align: 'center'},
                {field:'path',title:'PATH',align: 'center'},
                {field:'action',title:'download',align:'center',
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
