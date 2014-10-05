// 加载报修数据
$('#repairUCDataList').datagrid({
	url : getContext() + '/RepairController/loadReViewWaitCheck.do',
	title : '未审核报修基本数据',
	pagination : true,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	fit : true,
	fitColumns : true,
	nowrap : true,
	idField : 'id',
	columns : [ [ {
		field : 'id',
		title : '未审核报修序列号',
		width : 100,
		checkbox : true
	}, {
		field : 'drename',
		title : '报修名称',
		width : 100,
	}, {
		field : 'recontent',
		title : '报修详细内容',
		width : 200,
	}, {
		field : 'resdate',
		title : '学生报修时间',
		width : 100,
	}, {
		field : 'rno',
		title : '报修寝室',
		width : 100
	}, {
		field : 'dname',
		title : '报修宿舍',
		width : 100
	}, {
		field : 'sname',
		title : '报修学生',
		width : 100
	}, {
		field : 'state',
		title : '报修状态',
		width : 100,
		formatter : function(value, row, index) {
			if (value == 1) {
				return '<span style="color:blue">待审核</span>';
			} else {
				return '<span>-</span>';
			}

		}
	} ] ],
	toolbar : [ {
		text : '审核',
		iconCls : 'icon-help',
		handler : function() {
			checkRe();
		}
	} ]
});
// 绑定点击事件
$('#searchUCBtn').bind('click', function() {
	$('#repairUCDataList').datagrid('load', {
		searchWord : $('#repairUCSearchBox input[name=kw1]').val()
	});
});

/**
 * 审核
 */
function checkRe() {
	var rows = $('#repairUCDataList').datagrid('getSelections');
	var selected = $('#repairUCDataList').datagrid('getSelected');
	if (selected != null && rows.length == 1) {
		$('#repairUCDialog').show();
		$('#repairUCDialog form input[name=reName]').val(selected.drename);
		$('#repairUCDialog form textarea[name=reContent]').val(selected.recontent);
		$('#repairUCDialog form select[name=state]').combobox('select', selected.state);
		$('#repairUCDialog').dialog({
			title : '审核窗口',
			width : 400,
			height : 400,
			closed : false,
			cache : false,
			modal : true,
			buttons : [ {
				text : '审核',
				iconCls : 'icon-save',
				handler : function() {
					$('#repairUCDialog form').form('submit', {
						url : getContext() + '/RepairController/replyRe.do?reID=' + selected.id,
						success : function(data) {
							var _data = $.parseJSON(data);
							if (_data.success) {
								slide(_data.message);// 数据提示
								closeDialog('repairUCDialog');// 关闭窗口
								reloadGrid('repairUCDataList');// 刷新数据
							} else {
								$.messager.alert('提示', '审核发生错误！', 'error');
								reloadGrid('repairUCDataList');// 刷新数据
							}
						}
					});
				}
			} ]
		});
	} else {
		$.messager.alert('提示', '请选择一条要审核的记录！', 'error');
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
 * 刷新数据
 * 
 * @param grid
 *            数据表格名称
 */
function reloadGrid(grid) {
	$('#' + grid).datagrid('reload');
}
