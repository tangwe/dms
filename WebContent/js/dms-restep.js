/**
 * 报修申请js
 */
$(function() {
	init();
});
/**
 * 初始化
 */
function init() {
	// 加载宿舍
	loadDormsInUse();
	// 下一步按钮绑定事件
	$('#nextBtn').bind('click', function() {
		var reName = $('#f_step > form input[name=reName]').val();
		var roomNo = $('#f_step > form input[name=roomNo]').val();
		var dormName = $('#f_step > form select').find('option:selected').text();
		var reContent = $('#f_step > form textarea[name=reContent]').val();
		// step2赋值
		$('#s_step > form input[name=reName]').val(reName == '' ? '' : reName);
		$('#s_step > form input[name=roomNo]').val(roomNo == '' ? '' : dormName + roomNo);
		$('#s_step > form textarea[name=reContent]').val(reContent == '' ? '' : reContent);
	});
	// 最后一步按钮绑定事件
	$('#lastBtn').bind('click', function() {
		var reName = $('#f_step > form input[name=reName]').val();
		var roomNo = $('#f_step > form input[name=roomNo]').val();
		var dormID = $('#f_step > form select').find('option:selected').val();
		var reContent = $('#f_step > form textarea[name=reContent]').val();
		if (validate(reName, roomNo, dormID, reContent)) {
			var basePath = $.getBasePath();// 获取项目上下文路径
			var formData = $('#f_step > form').serialize();// 表单序列化
			// 提交后天处理
			$.post(basePath + 'RepairController/reqRepair.do', formData, function(data) {
				if (data.success) {
					$('#l_step h2').text(data.message);
				} else {
					$('#l_step h2').text('提交不能完成!你填写的内容存在问题，请重新填写...');
					$('#l_step div').append('<a href="repair_step.html" class="next btn btn-primary">重新填写</a>');
				}
			}, 'json');
		} else {
			$('#l_step h2').text('提交不能完成!你填写的内容存在问题，请重新填写...');
			$('#l_step div').append('<a href="repair_step.html" class="next btn btn-primary">重新填写</a>');
		}
	});
}
/**
 * 验证空值
 * 
 * @param reName
 * @param dormID
 * @param roomNo
 * @param reContent
 * @returns {Boolean}
 */
function validate(reName, roomNo, dormID, reContent) {
	if (reName == '' || dormID == '' || roomNo == '' || reContent == '') {
		return false;
	}
	return true;
}
/**
 * 加载宿舍 <option value="4">梅苑</option> <option value="3">兰苑</option> <option
 * value="2">竹苑</option> <option value="1">菊苑</option>
 */
function loadDormsInUse() {
	var basePath = $.getBasePath();// 获取项目上下文路径
	$.getJSON(basePath + 'DormController/loadDormInUse.do', function(data) {
		var dataList = data.dataList;
		if (dataList != null && dataList != '' && dataList.length > 0) {
			$('#f_step > form select').find('option').remove();
			$.each(dataList, function(i, n) {
				var option = '<option value=' + n.id + '>' + n.dName + '</option>';
				$('#f_step > form select').append(option);
			});
		} else {
			// 打印加载错误信息
			alert(data.message);
		}
	});
}
