$(function() {
	// 设置Session用户信息
	setSessionUserInfo();
	$('#userLogout').bind('click', function() {
		doLogout();
	});
});
/**
 * 设置用户信息
 */
function setSessionUserInfo() {
	var basePath = $.getBasePath();// 获取项目上下文路径
	$.getJSON(basePath + 'UserController/loadSessionUserInfo.do', function(data) {
		if (data.success) {
			var userName = data.obj.userName;
			$('#loginUserInfo > p a').text(userName);
		} else {
			$('#loginUserInfo').hide();
		}
	});
}
/**
 * 用户注销
 */
function doLogout() {
	var basePath = $.getBasePath();// 获取项目上下文路径
	$.ajax({
		url : basePath + 'UserController/doLogout.do',
		success : function(_data) {
			var data = $.parseJSON(_data);
			if (data.success) {
				alert(data.message);
				location.href = basePath + "login.html";
			} else {
				alert(data.message);
			}
		}
	});
}