/**
 * 初始化评分数据
 */
// 当前日期
var d = new Date();
var year = d.getFullYear();
var month = d.getMonth() + 1;
// defaultSelect(year, month);
// 加载评分数据
$('#markDataList').datagrid({
	url : getContext() + '/MarkController/loadMarkView.do',
	title : '评分基本数据',
	pagination : true,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	fit : true,
	fitColumns : true,
	nowrap : true,
	idField : 'id',
	columns : [ [ {
		field : 'id',
		title : '评分序列号',
		width : 100,
		checkbox : true
	}, {
		field : 'rid',
		title : '寝室序列号',
		width : 100,
		hidden : true
	}, {
		field : 'mid',
		title : '管理员序列号',
		width : 100,
		hidden : true
	}, {
		field : 'did',
		title : '宿舍序列号',
		width : 100,
		hidden : true
	}, {
		field : 'markname',
		title : '评分项名称',
		width : 100
	}, {
		field : 'msorcevalue',
		title : '评分项分值',
		width : 100
	}, {
		field : 'mmarkdate',
		title : '评分时间',
		width : 100
	}, {
		field : 'rno',
		title : '评分寝室',
		width : 100
	}, {
		field : 'dormname',
		title : '评分宿舍',
		width : 100
	}, {
		field : 'mmark_WEEKTS',
		title : '评分周次',
		width : 100,
		formatter : function(value, row, index) {
			if (value == '1') {
				return '周次1';
			} else if (value == '2') {
				return '周次2';
			} else if (value == '3') {
				return '周次3';
			} else if (value == '4') {
				return '周次4';
			}
		}
	}, {
		field : 'mdesc',
		title : '评分备注',
		width : 100
	}, {
		field : 'managername',
		title : '评分管理员',
		width : 100
	} ] ],
});
// 初始化查询
// search();
// 绑定点击事件
$('#searchBtn').bind('click', function() {
	search();
});

/**
 * 默认当前选择年月
 * 
 * @param year
 * @param month
 */
function defaultSelect(year, month) {
	if (year != '' && month != '') {
		$('#markSearchBox input[name=year]').val(year);
		$('#markSearchBox input[name=month]').val(month);
	}
}
// 查询
function search() {
	$('#markDataList').datagrid('load', {
		year : $('#markSearchBox [name=year]').val(),
		month : $('#markSearchBox [name=month]').val(),
		searchWord2 : $('#markSearchBox [name=kw1]').val(),
		searchWord3 : $('#markSearchBox [name=kw2]').val()
	});
}
