/**
 * 初始化宿舍数据
 */
$('#roomDataList').datagrid({
	url : getContext() + '/RoomController/loadRoomView.do',
	title : '寝室基本数据',
	pagination : true,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	fit : true,
	fitColumns : true,
	nowrap : true,
	idField : 'id',
	columns : [ [ {
		field : 'id',
		title : '寝室序列号',
		width : 100,
		checkbox : true
	}, {
		field : 'rno',
		title : '寝室名称',
		width : 100
	}, {
		field : 'rdesc',
		title : '寝室备注',
		width : 100
	}, {
		field : 'dname',
		title : '属于宿舍',
		width : 100
	}, {
		field : 'did',
		title : '宿舍ID',
		width : 100,
		hidden : true
	}, {
		field : 'state',
		title : '寝室状态',
		width : 100,
		formatter : function(value, row, index) {
			if (value == 0) {
				return '不可用';
			} else if (value == 1) {
				return '可用';
			} else if (value == 2) {
				return '使用中';
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

// 寝室查询
$('#searchBtn').bind('click', function() {
	$('#roomDataList').datagrid('load', {
		searchWord1 : $('#roomSearchBox [name=kw1]').val(),
		searchWord2 : $('#roomSearchBox [name=kw2]').val()
	});
});

/**
 * 添加新数据
 */
function addNewData() {
	$('#roomDialog').show();
	loadDormsInUse();// 加载宿舍数据
	$('#roomDialog').dialog({
		title : '新增寝室窗口',
		width : 400,
		height : 230,
		closed : false,
		cache : false,
		modal : true,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				$('#roomDialog form').form('submit', {
					url : getContext() + '/RoomController/addRoom.do',
					success : function(data) {
						var _data = $.parseJSON(data);
						if (_data.success) {
							slide(_data.message);// 数据提示
							closeDialog('roomDialog');// 关闭窗口
							reloadGrid('roomDataList');// 刷新数据
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
	var rows = $('#roomDataList').datagrid('getSelections');
	if (rows.length > 0) {
		$.messager.confirm('确认信息', '确定要删除当前选择的记录么？', function(b) {
			if (b) {
				var ids = [];
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : getContext() + '/RoomController/batchDelRooms.do',
					method : 'post',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(_data) {
						if (_data.success) {
							slide(_data.message);
							$('#roomDataList').datagrid('unselectAll');// 取消选择
							reloadGrid('roomDataList');// 加载数据
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
	var rows = $('#roomDataList').datagrid('getSelections');
	var selected = $('#roomDataList').datagrid('getSelected');
	if (selected != null && rows.length == 1) {
		$('#roomDialog').show();
		loadDormsInUse();// 加载宿舍数据
		$('#roomDialog form input[name=rNo]').val(selected.rno);
		$('#roomDialog form input[name=dDesc]').val(selected.rdesc);
		$('#roomDialog form input[name=dormID]').val(selected.did);
		$('#roomDialog form select[name=state]').combobox('select', selected.state);
		$('#roomDialog').dialog({
			title : '修改寝室窗口',
			width : 400,
			height : 230,
			closed : false,
			cache : false,
			modal : true,
			buttons : [ {
				text : '修改',
				iconCls : 'icon-save',
				handler : function() {
					$('#roomDialog form').form('submit', {
						url : getContext() + '/RoomController/updateRoom.do?roomID=' + selected.id,
						success : function(data) {
							var _data = $.parseJSON(data);
							if (_data.success) {
								slide(_data.message);// 数据提示
								closeDialog('roomDialog');// 关闭窗口
								reloadGrid('roomDataList');// 刷新数据
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
 * 加载可用宿舍
 */
function loadDormsInUse() {
	$('form input[name=dormID]').combobox({
		url : getContext() + '/DormController/loadDormInUseCombox.do',
		valueField : 'id',
		textField : 'dName'
	});
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
	$('#roomDialog form input[name=rNo]').val('');
	$('#roomDialog form input[name=rDesc]').val('');
	$('#roomDialog form input[name=dormID]').val('');
}
