/**
 * 登陆js
 */
$(function() {
	init();
});

/**
 * 初始化
 */
function init() {
	var basePath = $.getBasePath();// 获取项目上下文路径
	// 登陆按钮绑定事件
	$('#loginBtn').bind('click', function() {
		$('form').attr('action', basePath + 'UserController/stuLogin.do');
		$('form').submit();// 提交表单
	});
}