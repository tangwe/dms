/**
 * 寻物启事失物招领js
 */
$(function() {
	init();
});
/**
 * 初始化
 */
function init() {
	// 发布按钮绑定事件
	$('#pubBtn').bind('click', function() {
		if (validate()) {
			requestPub();// 发布
		}
	});
	// 预览按钮绑定事件
	$('#revBtn').bind('click', function() {
		if (validate()) {
			reviewPub();// 预览
		}
	});
}
/**
 * 发布
 */
function requestPub() {
	var basePath = $.getBasePath();// 获取项目上下文路径
	var formData = $('form').serialize();// 表单序列化
	$.post(basePath + "LostFoundController/pubLostOrFound.do", formData, function(data) {
		alert(data.message);
		if (data.success) {
			resetFormData();// 还原表单
		}
	}, "json");
}
/**
 * 预览发布信息
 */
function reviewPub() {
	var lfTitle = '';
	var lfType = $('form input[name=lfType]:checked').val();
	if (lfType == 0) {
		lfTitle = '寻物启事';
	} else if (lfType == 1) {
		lfTitle = '失物招领';
	}
	var lfContent = $('form textarea[name=lfContent]').val();
	var lfDate = getNowDate(new Date());
	$('#lfDetail > h3').text(lfTitle);
	$('#lfDetail > p ').text(lfContent);
	$('#lfDetail > span ').text(lfDate + '	from	姓名');
	// div滑出
	$('.theme-popover-mask').fadeIn(100);
	$('.theme-popover').slideDown(200);
}
/**
 * 获取日期
 */
function getNowDate(d) {
	var year = d.getFullYear();// 获取年
	var month = d.getMonth() + 1;// 获取月
	var day = d.getDate();// 获取日
	return year + "年" + month + "月" + day + "日";
}
/**
 * 验证内容
 */
function validate() {
	var lfTag = $('form input[name=lfTag]').val();
	var lfType = $('form input[name=lfType]:checked').val();
	var lfContent = $('form textarea[name=lfContent]').val();
	if (lfTag == '' || lfType == '' || lfContent == '') {
		return false;
	}
	return true;
}
/**
 * 还原表单
 */
function resetFormData() {
	$('form input[name=lfTag]').val('');
	$('form input[name=lfType] :eq(0)').attr('checked', true);
	$('form textarea[name=lfContent]').val('');
	$('#lfDetail > h3').text('');
	$('#lfDetail > p ').text('');
	$('#lfDetail > span ').text('');
}
