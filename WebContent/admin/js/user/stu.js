// 加载学生数据
$('#stuDataList').datagrid({
	url : getContext() + '/UserController/loadStuView.do',
	title : '学生数据',
	pagination : true,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	fit : true,
	fitColumns : true,
	nowrap : true,
	idField : 'id',
	columns : [ [ {
		field : 'id',
		title : '学生序列号',
		width : 100,
		checkbox : true
	}, {
		field : 'sno',
		title : '学号',
		width : 100
	}, {
		field : 'sname',
		title : '学生姓名',
		width : 100
	}, {
		field : 'sfrom',
		title : '籍贯',
		width : 100
	}, {
		field : 'dname',
		title : '宿舍',
		width : 100
	}, {
		field : 'rno',
		title : '寝室',
		width : 100
	}, {
		field : 'stel',
		title : '联系电话',
		width : 100
	} ] ]
});

// 学生查询
$('#searchBtn').bind('click', function() {
	$('#stuDataList').datagrid('load', {
		searchWord1 : $('#stuSearchBox [name=kw1]').val(),
		searchWord2 : $('#stuSearchBox [name=kw2]').val()
	});
});
