$.ajax({
	url : getContext() + '/UserController/doLogout.do',
	success : function(_data) {
		var data = $.parseJSON(_data);
		if (data.success) {
			alert(data.message);
			location.href = getContext() + "/admin/login.html";
		} else {
			alert(data.message);
		}
	}
});