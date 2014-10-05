/**
 * dms自定义插件 用法 var v= $.name()
 * 
 */
(function($) {
	// 获取项目路径 如： http://localhost:8080/dms/
	$.getBasePath = function() {
		// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
		var curWwwPath = window.document.location.href;
		// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		// 获取主机地址，如： http://localhost:8083
		var localhostPaht = curWwwPath.substring(0, pos);
		// 获取带"/"的项目名，如：/uimcardprj
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return (localhostPaht + projectName + '/');
	}

	// 获取项目路径 如： /dms
	$.getContextPath = function() {
		// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		var pathName = window.document.location.pathname;
		// 获取带"/"的项目名，如：/uimcardprj
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return (projectName);
	}

	// 获取URL参数 http://localhost:8080/dms/index.html?pageNow=1 输入pageNow 返回1
	$.getUrlParam = function(paramName) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	$.getNowDate = function() {
		var d = new Date();
		return d;
	}

})(jQuery);
