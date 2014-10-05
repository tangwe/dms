// 加载报修数据
$('#repairCDataList').datagrid({
	url : getContext() + '/RepairController/loadReViewChecked.do',
	title : '审核报修基本数据',
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
			if (value == 2) {
				return '<span style="color:green">审核通过</span>';
			} else if (value == 3) {
				return '<span style="color:red">审核未通过</span>';
			} else {
				return '<span>-</span>';
			}

		}
	} ] ],
});
// 绑定点击事件
$('#searchCBtn').bind('click', function() {
	$('#repairCDataList').datagrid('load', {
		searchWord : $('#repairCSearchBox input[name=kw1]').val()
	});
});
