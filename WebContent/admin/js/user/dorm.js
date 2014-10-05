/**
 * 初始化宿舍数据
 */
$('#dormDataList').datagrid({
	url : getContext() + '/DormController/loadDorms.do',
	title : '宿舍基本数据',
	pagination : true,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	fit : true,
	fitColumns : true,
	nowrap : true,
	idField : 'id',
	columns : [ [ {
		field : 'id',
		title : '宿舍序列号',
		width : 100,
		checkbox : true
	}, {
		field : 'dName',
		title : '宿舍名称',
		width : 100
	}, {
		field : 'dDesc',
		title : '宿舍备注',
		width : 100
	}, {
		field : 'state',
		title : '宿舍状态',
		width : 100,
		formatter : function(value, row, index) {
			if (value == 0) {
				return '不可用';
			} else if (value == 1) {
				return '可用';
			}
		}
	} ] ],
	toolbar : [ {
		text : '新增',
		iconCls : 'icon-add',
		handler : function() {
			// 清理form数据
			resetDialogForm();
			// 添加新数据
			addNewData();
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			// 删除数据
			delData();
		}
	}, '-', {
		text : '修改',
		iconCls : 'icon-edit',
		handler : function() {
			// 修改数据
			editData();
		}
	} ]
});
/**
 * 添加新数据
 */
function addNewData() {
	$('#dormDialog').show();
	$('#dormDialog').dialog({
		title : '新增宿舍窗口',
		width : 400,
		height : 200,
		closed : false,
		cache : false,
		modal : true,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				$('#dormDialog form').form('submit', {
					url : getContext() + '/DormController/addDorm.do',
					success : function(data) {
						var _data = $.parseJSON(data);
						if (_data.success) {
							slide(_data.message);// 数据提示
							closeDialog('dormDialog');// 关闭窗口
							reloadGrid('dormDataList');// 刷新数据
						} else {
							$.messager.alert('提示', '添加发生错误！', 'error');
						}
					}
				});
			}
		} ]
	});
}
/**
 * 删除数据
 */
function delData() {
	var rows = $('#dormDataList').datagrid('getSelections');
	if (rows.length > 0) {
		$.messager.confirm('确认信息', '确定要删除当前选择的记录么？', function(b) {
			if (b) {
				var ids = [];
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : getContext() + '/DormController/batchDelDorms.do',
					method : 'post',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(_data) {
						if (_data.success) {
							slide(_data.message);
							$('#dormDataList').datagrid('unselectAll');// 取消选择
							reloadGrid('dormDataList');// 加载数据
						} else {
							slide(_data.message);
						}
					}
				});
			}
		});
	} else {
		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	}
}
/**
 * 修改数据
 */
function editData() {
	var rows = $('#dormDataList').datagrid('getSelections');
	var selected = $('#dormDataList').datagrid('getSelected');
	if (selected != null && rows.length == 1) {
		$('#dormDialog').show();
		$('#dormDialog form input[name=dName]').val(selected.dName);
		$('#dormDialog form input[name=dDesc]').val(selected.dDesc);
		$('#dormDialog form select').combobox('select', selected.state);
		$('#dormDialog').dialog({
			title : '修改宿舍窗口',
			width : 400,
			height : 200,
			closed : false,
			cache : false,
			modal : true,
			buttons : [ {
				text : '修改',
				iconCls : 'icon-save',
				handler : function() {
					$('#dormDialog form').form('submit', {
						url : getContext() + '/DormController/updateDorm.do?dormID=' + selected.id,
						success : function(data) {
							var _data = $.parseJSON(data);
							if (_data.success) {
								slide(_data.message);// 数据提示
								closeDialog('dormDialog');// 关闭窗口
								reloadGrid('dormDataList');// 刷新数据
							} else {
								$.messager.alert('提示', '修改发生错误！', 'error');
							}
						}
					});
				}
			} ]
		});
	} else {
		$.messager.alert('提示', '请选择一条要修改的记录！', 'error');
	}
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

/**
 * 刷新数据
 * 
 * @param grid
 *            数据表格名称
 */
function reloadGrid(grid) {
	$('#' + grid).datagrid('reload');
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
 * 清空form数据
 */
function resetDialogForm() {
	$('#dormDialog form input[name=dName]').val('');
	$('#dormDialog form input[name=dDesc]').val('');
	$('#dormDialog form select').find('option[value=1]').attr('selected', true);
}
