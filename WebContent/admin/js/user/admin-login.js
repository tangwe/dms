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
	// 登陆按钮绑定事件
	$('#loginBtn').bind('click', function() {
		$('form').attr('action', getContext()+ '/UserController/manLogin.do');
		$('form').submit();// 提交表单
	});
}