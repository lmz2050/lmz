<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
$(function(){
	var provi=0;
    var prov = $('#prov').combobox({
        url: '${pageContext.request.contextPath}/prov.action',
        editable: false,
        valueField: 'id',
        textField: 'name',
        panelWidth: '100',
        width:'100',
        onSelect: function (record) {
            city.combobox({
                disabled: false,
                url: '${pageContext.request.contextPath}/city.action?id=' + record.id,
                valueField: 'id',
                textField: 'name',
                onLoadSuccess: function () { 
					
				}
            }).combobox('clear');
            region.combobox('clear');
        },
        onLoadSuccess: function () { 
			$('#prov').combobox('setValue', '<s:property value="info.prov"/>');
		}
    });
    var city = $('#city').combobox({
    	url: '${pageContext.request.contextPath}/city.action?id='+'<s:property value="info.prov"/>',
        valueField: 'id',
        textField: 'name',
        panelWidth: '100',
        width:'100',
        onSelect: function (record) {
            region.combobox({
                disabled: false,
                url: '${pageContext.request.contextPath}/region.action?id=' + record.id,
                valueField: 'id',
                textField: 'name',
                onLoadSuccess: function () { 
					
				}
            }).combobox('clear');
        },
        onLoadSuccess: function () { 
			$('#city').combobox('setValue', '<s:property value="info.city"/>');
		}
    });
    var region = $('#region').combobox({
    	url: '${pageContext.request.contextPath}/region.action?id=<s:property value="info.city"/>',
        valueField: 'id',
        textField: 'name',
        panelWidth: '100',
        width:'100',
        onLoadSuccess: function () { 
			$('#region').combobox('setValue', '<s:property value="info.region"/>');
		}
    }); 
})
</script>

<select id="prov" name="info.prov" style="height:30px;width:auto;"></select>
<select id="city" name="info.city" style="height:30px;width:auto;"/></select>
<select id="region"  name="info.region" style="height:30px;width:auto;"/></select>