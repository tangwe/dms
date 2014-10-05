/**
 * 我的物品寻失记录js
 */
$(function() {
	init();
});
/**
 * 初始化
 */
function init() {
	// 加载我的寻失记录
	loadStuLF(1, '');
	// search 按钮绑定 click事件
	$('#search').bind('click', function() {
		var searchWord = $('#searchText').val() == '' ? '' : $('#searchText').val();
		// 查询加载
		loadStuLF(1, searchWord);
	});
}

function loadStuLF(pageNow, searchWord) {
	var basePath = $.getBasePath();// 获取项目上下文路径
	var _pageNow = pageNow;
	var _searchWord = searchWord;
	$.getJSON(basePath + 'LostFoundController/loadStuLostFound.do', {
		pageNow : _pageNow,
		searchWord : _searchWord
	}, function(data) {
		if (data.success) {
			var dataList = data.dataList;
			if (dataList != null && dataList != "" && dataList.length > 0) {
				$('#tab tr').remove();
				$('#pagination li').remove();
				$.each(dataList, function(i, n) {
					var noticetrs = '<tr><td>' + n.lfTag + '</td><td>' + (n.lfType == 0 ? '寻物启事' : (n.lfType == 1 ? '失物招领' : '-')) + '</td><td>'
							+ n.lfDate_toS + '</td><td><button id="detail' + i + '" class="btn btn-primary review">查看内容</button></td></tr>';
					$('#tab').append(noticetrs);
					// 按钮绑定事件
					$('#detail' + i).bind('click', function() {
						$('#lfDetail > h3').text((n.lfType == 0 ? '寻物启事' : (n.lfType == 1 ? '失物招领' : '')) + '内容');
						$('#lfDetail > p ').text(n.lfContent);
						$('#lfDetail > span ').text(n.lfDate);
						// div滑出
						$('.theme-popover-mask').fadeIn(100);
						$('.theme-popover').slideDown(200);
					});
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
		loadStuLF(pageNow_, _searchWord);
	});
}