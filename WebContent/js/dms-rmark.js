/**
 * 寝室评分js
 */
$(function() {
	init();
});
/**
 * 初始化
 */
function init() {
	// 当前日期
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	defaultSelect(year, month);
	// 请求评分数据
	loadMonthMarkData(year, month);
	// 加载评分图
	loadLineChart(year, month);
	var changeYear = year;
	var changeMonth = month;
	// 年，月下拉框绑定change事件
	$('#yearse,#monthse').bind('change', function() {
		// 大于12判定年
		if ($(this).val() > 12) {
			changeYear = $(this).val();
		} else {
			changeMonth = $(this).val();
		}
		// 请求评分数据
		loadMonthMarkData(changeYear, changeMonth);
		// 加载评分图
		loadLineChart(changeYear, changeMonth);
	});
}
/**
 * 默认当前选择年月
 * 
 * @param year
 * @param month
 */
function defaultSelect(year, month) {
	if (year != '' && month != '') {
		$('#yearse option[value=' + year + ']').attr('selected', true);
		$('#monthse option[value=' + month + ']').attr('selected', true);
	}
}
/**
 * 评分数据加载显示
 * 
 * @param year
 * @param month
 */
function loadMonthMarkData(year, month) {
	if (year != '' && month != '') {
		var basePath = $.getBasePath();// 获取项目上下文路径
		$.getJSON(basePath + 'MarkController/getMonthMarkData.do', {
			year : year,
			month : month
		}, function(data) {
			// 请求成功
			if (data.success) {
				// 有请求数据
				if (data.totalCount > 0) {
					// 设置表格标题
					$('#markTitle').text(getTableTitle(year, month, data.dataList[0].dormName, data.dataList[0].roomNo));
					// 清空tr
					$('#tab tr').remove();
					// 遍历集合
					$.each(data.dataList, function(i, n) {
						var tabTr = '<tr><td>周次' + n.weekTS + '</td>' + '<td>' + n.sorceValue1 + '</td>' + '<td>' + n.sorceValue2 + '</td>' + '<td>'
								+ n.sorceValue3 + '</td>' + '<td>' + n.sorceValue4 + '</td>' + '<td>' + n.sorceCountValue + '</td></tr>';
						$('#tab').append(tabTr);
					});
					// 没有数据
				} else {
					// 清空tr
					$('#tab tr').remove();
					// 设置默认表格
					var tabTr = '<tr><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>'
							+ '<tr><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>'
							+ '<tr><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>'
							+ '<tr><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>';
					$('#tab').append(tabTr);
				}
			} else {
				// 输出错误信息
				alert(data.message);
			}
		});
	}
}
/**
 * 获取表格标题
 * 
 * @param year
 * @param month
 * @param dorm
 * @param room
 * @returns {String}
 */
function getTableTitle(year, month, dorm, room) {
	return year + "年" + month + "月份" + "-" + dorm + room + "寝室评分统计";
}
/**
 * @param dorm
 * @param room
 * @returns {String}
 */
function getChartTitle(dorm, room) {
	return dorm + room + "寝室评分统计直观图";
}
/**
 * @param year
 * @param month
 * @returns {String}
 */
function getChartSubTitle(year, month) {
	return year + "年" + month + "月份";
}
/**
 * 图表加载显示
 * 
 * @returns
 */
function loadLineChart(year, month) {
	var basePath = $.getBasePath();// 获取项目上下文路径
	$.getJSON(basePath + 'MarkController/getMonthMarkData.do', {
		year : year,
		month : month
	}, function(data) {
		if (data.success) {
			if (data.totalCount > 0) {
				var chartTitle = getChartTitle(data.dataList[0].dormName, data.dataList[0].roomNo);
				var chartSubTitle = getChartSubTitle(year, month);
				var sorceValue1Arr = new Array();
				var sorceValue2Arr = new Array();
				var sorceValue3Arr = new Array();
				var sorceValue4Arr = new Array();
				var weekTsArr = new Array();
				$.each(data.dataList, function(i, v) {
					sorceValue1Arr[i] = v.sorceValue1;
					sorceValue2Arr[i] = v.sorceValue2;
					sorceValue3Arr[i] = v.sorceValue3;
					sorceValue4Arr[i] = v.sorceValue4;
					weekTsArr[i] = '周次' + v.weekTS;
				});
				// 设置chart数据
				setChartData(chartTitle, chartSubTitle, sorceValue1Arr, sorceValue2Arr, sorceValue3Arr, sorceValue4Arr, weekTsArr);
				// chart显示
				$('#markgraph').show();
			} else {
				var chartTitle = '寝室评分统计直观图';
				var chartSubTitle = getChartSubTitle(year, month);
				setChartData(chartTitle, chartSubTitle, [], [], [], [], []);
			}
		} else {
			// chart隐藏
			$('#markgraph').hide();
		}
	});
}
/**
 * 设置chart显示
 * 
 * @param title
 * @param subtitle
 * @param dataArr1
 * @param dataArr2
 * @param dataArr3
 * @param dataArr4
 */
function setChartData(title, subtitle, dataArr1, dataArr2, dataArr3, dataArr4, weekTsArr) {
	$('#markgraph').highcharts({
		chart : {
			type : 'column'
		},
		credits : {
			enabled : false
		},
		title : {
			text : title
		},
		subtitle : {
			text : subtitle
		},
		xAxis : {
			categories : weekTsArr
		},
		yAxis : {
			max : 10,
			title : {
				text : '分值 (sorce)'
			}
		},
		plotOptions : {
			column : {
				pointPadding : 0.2,
				borderWidth : 0
			}
		},
		series : [ {
			name : '地面',
			data : dataArr1
		}, {
			name : '桌面',
			data : dataArr2
		}, {
			name : '阳台',
			data : dataArr3
		}, {
			name : '厕所',
			data : dataArr4
		} ]
	});
}
