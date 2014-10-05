$('#repairDataList').datagrid({
	url : getContext() + '/RepairController/loadReView.do',
	title : '报修基本数据',
	pagination : true,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	fit : true,
	fitColumns : true,
	nowrap : true,
	idField : 'id',
	columns : [ [ {
		field : 'id',
		title : '已审核报修序列号',
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
		field : 'remreply',
		title : '审核回复',
		width : 200
	}, {
		field : 'remdate',
		title : '审核时间',
		width : 100
	}, {
		field : 'mname',
		title : '审核管理员',
		width : 100
	}, {
		field : 'state',
		title : '审核结果',
		width : 100,
		formatter : function(value, row, index) {
			if (value == 1) {
				return '<span style="color:blue">待审核</span>';
			} else if (value == 2) {
				return '<span style="color:green">审核通过</span>';
			} else if (value == 3) {
				return '<span style="color:red">审核未通过</span>';
			} else {
				return '<span>-</span>';
			}

		}
	} ] ],
	toolbar : [ {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			// 删除数据
			delData();
		}
	} ]
});

// 查询
$('#searchBtn').bind('click', function() {
	$('#repairDataList').datagrid('load', {
		searchWord : $('#repairSearchBox [name=kw1]').val()
	});
});

/**
 * 删除数据
 */
function delData() {
	var rows = $('#repairDataList').datagrid('getSelections');
	if (rows.length > 0) {
		$.messager.confirm('确认信息', '确定要删除当前选择的记录么？', function(b) {
			if (b) {
				var ids = [];
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : getContext() + '/RepairController/batchDelRes.do',
					method : 'post',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(_data) {
						if (_data.success) {
							slide(_data.message);
							$('#repairDataList').datagrid('unselectAll');// 取消选择
							reloadGrid('repairDataList');// 加载数据
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
