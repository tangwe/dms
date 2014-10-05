$(function() {
	$('#updatePwd').bind('click', function() {
		updatePwd();
	});
});

/**
 * 修改密码
 */
function updatePwd() {
	$('#updatePwdDialog').show();
	$('#updatePwdDialog').dialog({
		title : '修改密码窗口',
		width : 400,
		height : 180,
		closed : false,
		cache : false,
		modal : true,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				$('#updatePwdDialog form').form('submit', {
					url : getContext() + '/UserController/updatePwd.do',
					success : function(data) {
						var _data = $.parseJSON(data);
						if (_data.success) {
							slide(_data.message);// 数据提示
							closeDialog('updatePwdDialog');// 关闭窗口
							location.href = getContext() + "/admin/login.html";
						} else {
							$.messager.alert('提示', '旧密码输入有误！', 'error');
						}
					}
				});
			}
		} ]
	});
}

/**
 * 关闭窗口
 * 
 * @param dialog
 *            窗口名称
 */
function closeDialog(dialog) {
	$('#' + dialog).dialog('close');
	$('#' + dialog).hide();
}

/**
 * 滑出提示
 * 
 * @param info
 */
function slide(info) {
	$.messager.show({
		title : '提示',
		msg : info,
		timeout : 3000,
		showType : 'slide'
	});
}
