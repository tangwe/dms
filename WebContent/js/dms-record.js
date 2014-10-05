/**
 * 报修记录js
 */
$(function() {
	init();
});
/**
 * 初始化
 */
function init() {
	// 加载报修记录
	loadReRecord(1, '');
	// search 按钮绑定 click事件
	$('#search').bind('click', function() {
		var searchWord = $('#searchText').val() == '' ? '' : $('#searchText').val();
		// 查询加载
		loadReRecord(1, searchWord);
	});
}
/**
 * 加载报修记录
 * 
 * @param pageNow
 * @param searchWord
 */
function loadReRecord(pageNow, searchWord) {
	var basePath = $.getBasePath();// 获取项目上下文路径
	var _pageNow = pageNow;
	var _searchWord = searchWord;
	$.getJSON(basePath + 'RepairController/loadRepairViews.do', {
		pageNow : _pageNow,
		searchWord : _searchWord
	}, function(data) {
		if (data.success) {
			var dataList = data.dataList;
			if (dataList != null && dataList != "" && dataList.length > 0) {
				$('#tab tr').remove();
				$('#pagination li').remove();
				$.each(dataList, function(i, n) {
					var repairtrs = '<tr>' + '<td>' + (n.drename == null ? '-' : n.drename) + '</td>' + '<td>'
							+ (n.resdate_toS == null ? '-' : n.resdate_toS) + '</td>' + '<td>'
							+ (n.dname == null || n.rno == null ? '-' : (n.dname + n.rno)) + '</td>' + '<td>' + (n.sname == null ? '-' : n.sname)
							+ '</td>' + '<td>' + (n.remreply == null ? '-' : n.remreply) + '</td>' + '<td>' + (n.mname == null ? '-' : n.mname)
							+ '</td>' + '<td>' + (n.state == 1 ? '审核中' : (n.state == 2 ? '审核通过' : (n.state == 3 ? '审核未通过' : '-'))) + '</td></tr>';
					$('#tab').append(repairtrs);
				});
				var totalPage = data.totalPage == 0 ? 1 : data.totalPage;
				pagination(totalPage, _pageNow, _searchWord);// 分页
			} else {
				alert('您想获取的数据不存在！');
			}
		} else {
			alert(data.message);
		}
	});
}
/**
 * 分页
 * 
 * @param totalPage
 * @param _searchWord
 * @returns
 */
function pagination(totalPage, _pageNow, _searchWord) {
	$('#pagination').append('<li><a> << </a></li>');
	$('#pagination').append('<li><a> >> </a></li>');
	for ( var i = 1; i <= totalPage; i++) {
		if (i == _pageNow) {
			$('#pagination li:last').before('<li class="active"><a>' + i + '</a></li>');
		} else {
			$('#pagination li:last').before('<li><a>' + i + '</a></li>');
		}
	}
	// 绑定click事件
	$('#pagination li a').bind('click', function() {
		var pageNow_ = 1;
		if ($.trim($(this).text()) == '<<') {
			pageNow_ = 1;
		} else if ($.trim($(this).text()) == '>>') {
			pageNow_ = totalPage;
		} else {
			pageNow_ = $(this).text() == '' ? 1 : $(this).text();
		}
		loadReRecord(pageNow_, _searchWord);
	});
}
