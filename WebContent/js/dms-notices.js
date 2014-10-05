/**
 * 物品寻失大厅
 */
$(function() {
	init();
});
/**
 * 初始化
 */
function init() {
	// 初始化查询
	loadLFData(1, '');
}
/**
 * 加载物品寻失记录
 */
function loadLFData(pageNow, searchWord) {
	var basePath = $.getBasePath();// 获取项目上下文路径
	var _searchWord = searchWord;
	var _pageNow = pageNow;
	$.getJSON(basePath + "LostFoundController/loadLostFoundViews.do", {
		pageNow : _pageNow,
		searchWord : _searchWord
	}, function(data) {
		if (data.success) {
			var dataList = data.dataList;
			if (dataList != null && dataList != '' && dataList.length > 0) {
				$('#noteul li').remove();
				$('#pagination li').remove();
				$.each(dataList, function(i, n) {
					var notelis = '<li><a><h3>' + (n.lftype == 0 ? '寻物启事' : '失物招领') + '</h3><p>' + n.lfcontent + '</p>' + '<span>' + n.lfdate_tsS
							+ '	from ' + n.sname + '</span></a></li>';
					$('#noteul').append(notelis);
				});
				var totalPage = data.totalPage == 0 ? 1 : data.totalPage;
				pagination(totalPage, _pageNow, _searchWord);// 分页
			} else {
				alert('您获取的数据不存在！');
			}
		} else {
			// 输出错误信息
			alert(data.message);
		}
	}, "json");
}
/**
 * 分页
 * 
 * @param totalPage
 * @param _pageNow
 * @param _searchWord
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
		loadLFData(pageNow_, _searchWord);
	});
}
