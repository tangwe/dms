/**
 * 将form表单中的值序列化成对象
 * 如果有多个name值相同则以逗号分开
 * 返回序列化对象
 */
serializeFormObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * 获取web上下文
 */
getContext = function () { 
	var contextPath = document.location.pathname; 
	var index =contextPath.substr(1).indexOf("/"); 
	contextPath = contextPath.substr(0,index+1); 
	delete index; 
	return contextPath; 
};


