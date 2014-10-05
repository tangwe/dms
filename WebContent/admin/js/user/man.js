// 加载学生数据
$('#manDataList').datagrid({
	url : getContext() + '/UserController/loadManView.do',
	title : '寝室管理员数据',
	pagination : true,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	fit : true,
	fitColumns : true,
	nowrap : true,
	idField : 'id',
	columns : [ [ {
		field : 'id',
		title : '管理员序列号',
		width : 100,
		checkbox : true
	}, {
		field : 'mno',
		title : '管理员账号',
		width : 100
	}, {
		field : 'mname',
		title : '管理员姓名',
		width : 100
	}, {
		field : 'dname',
		title : '管理宿舍',
		width : 100
	} ] ]
});

// 查询
$('#searchBtn').bind('click', function() {
	$('#manDataList').datagrid('load', {
		searchWord : $('#manSearchBox [name=kw1]').val(),
	});
});
