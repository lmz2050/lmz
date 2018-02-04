/**
 * 刪除請求
 * 
 * @param url
 * @param index
 */
function Del(url, index) {
	$.messager.confirm('删除提示', '你确定永久删除该数据吗?', function(r) {
		if (r) {
			delSubmit(url, index);
		}
	});
}
$(function() {
	$.messager.defaults = {
		ok : "确定",
		cancel : "取消"
	};
});
/**
 * 执行方法前询问
 * 
 * @param url
 * @param index
 */
function confirm(url, message, index) {
	$.messager.confirm('提示信息', message, function(r) {
		if (r) {
			changeStatus(url, index);
		}

	});
}
/**
 * 執行保存
 * 
 * @param url
 * @param gridname
 */
function saveDeclare(url) {
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(r) {
			$("#test").dialog("close");
			reloadTable();

		}
	});
}

/**
 * 添加
 * 
 * @param title
 * @param addurl
 * @param saveurl
 * @param id
 * @param gridname
 */
function add(title, addurl, saveurl) {
	openaddorupwin(title, addurl, saveurl);
}
/**
 * 更新
 * 
 * @param title
 * @param addurl
 * @param saveurl
 * @param id
 * @param gridname
 */
function update(title, addurl, saveurl, id) {
	var val = getSelected(id);
	if (val == '') {
		$.messager.show({
			title : title,
			msg : '请选择编辑项目',
			timeout : 2000,
			showType : 'slide'
		});
		return;
	}
	addurl += '&' + id + '=' + val;
	openaddorupwin(title, addurl, saveurl);
}
/**
 * 添加更新打開窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 * @param id
 * @param gridname
 */

/**
 * 添加活更新打開窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 * @param id
 * @param gridname
 */

/**
 * 执行询问方法
 * 
 * @param url
 * @param index
 */
function changeStatus(url, index) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				$.messager.show({
					title : '提示消息',
					msg : '提交成功',
					timeout : 2000,
					showType : 'slide'
				});
				reloadTable();
			}
		}
	});
}
/**
 * 删除执行
 * 
 * @param url
 * @param index
 */
function delSubmit(url, index) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				$.messager.show({
					title : '提示消息',
					msg : '删除成功',
					timeout : 2000,
					showType : 'slide'
				});
				reloadTable();
			}
		}
	});
}
/**
 * 窗口打开
 */
$(function() {
	$('body').append(
			'<div id="myWindow" class="easyui-dialog" closed="true"></div>');
});

/**
 * 模板页面ajax提交
 * 
 * @param url
 * @param gridname
 */
function ajaxdoSub(url, formname) {
	$('#' + formname).form('submit', {
		url : url,
		onSubmit : function() {
			$("#infocontent").val($('#content').html());
		},
		success : function(r) {
			$.messager.show({
				title : '提示消息',
				msg : '保存成功',
				timeout : 2000,
				showType : 'slide'
			});
		}
	});
}
/**
 * ajax提交FORM
 * 
 * @param url
 * @param gridname
 */
function ajaxdoForm(url, formname) {
	$('#' + formname).form('submit', {
		url : url,
		onSubmit : function() {
		},
		success : function(r) {
			$.messager.show({
				title : '提示消息',
				msg : '保存成功',
				timeout : 2000,
				showType : 'slide'
			});
		}
	});
}