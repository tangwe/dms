//加载宿舍
loadDormsInUse();
// 设置评分标题
setMarkTitle();
// 绑定点击事件
$('#subBtn').bind('click', function() {
	var flag = checkForm();
	if (flag) {
		mark();// 评分
	} else {
		$.messager.alert('提示', '输入有误，请核实你填写内容！', 'error');
	}
});
/**
 * 加载可用宿舍
 */
function loadDormsInUse() {
	$('#markSet form input[name=dormID]').combobox({
		url : getContext() + '/DormController/loadDormInUseCombox.do',
		valueField : 'id',
		textField : 'dName'
	});
}
/**
 * 设置title
 */
function setMarkTitle() {
	var title = getTitle();
	$('#markpanel > p').text(title);
}
/**
 * 标题比如：2014年9月寝室评分
 */
function getTitle() {
	// 当前日期
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	return year + '年' + month + '月寝室评分'
}
/**
 * 提交表单后台评分
 */
function mark() {
	var _dormID = $('#markSet form input[name=dormID]').val();
	var _roomNo = $('#markSet form input[name=roomNo]').val();
	var _weekTs = $('#markSet form input[name=weekTs]').val();
	// 提交验证是否已经评分
	$.post(getContext() + '/MarkController/ifMarked.do', {
		dormID : _dormID,
		roomNo : _roomNo,
		WeekTS : _weekTs
	}, function(data) {
		if (data.success) {
			$('#markSet form').form('submit', {
				url : getContext() + '/MarkController/mark.do',
				success : function(data) {
					var _data = $.parseJSON(data);
					if (_data.success) {
						slide(_data.message);// 数据提示
						clearForm();// 清空form
					} else {
						$.messager.alert('提示', '评分发生错误，请核实你填写内容！', 'error');
					}
				}
			});
		} else {
			$.messager.alert('提示', '不存在该寝室或已对该周次进行评分！', 'error');
		}
	}, 'json');
}
/**
 * 是否未评分
 * 
 * @returns {Boolean}
 */
function ifNoMark() {
	var flag = false;
	var _dormID = $('#markSet form input[name=dormID]').val();
	var _roomNo = $('#markSet form input[name=roomNo]').val();
	var _weekTs = $('#markSet form input[name=weekTs]').val();
	$.post(getContext() + '/MarkController/ifMarked.do', {
		dormID : _dormID,
		roomNo : _roomNo,
		WeekTS : _weekTs
	}, function(data) {
		if (data.success) {
			flag = true;
		} else {
			flag = false;
		}
	}, 'json');
	return flag;
}
/**
 * 验证表单
 * 
 * @returns {Boolean}
 */
function checkForm() {
	var _dormID = $('#markSet form input[name=dormID]').val();
	var _roomNo = $('#markSet form input[name=roomNo]').val();
	var _weekTs = $('#markSet form input[name=weekTs]').val();
	var _sv1 = $('#markSet form input[name=sv1]').val();
	var _sv2 = $('#markSet form input[name=sv2]').val();
	var _sv3 = $('#markSet form input[name=sv3]').val();
	var _sv4 = $('#markSet form input[name=sv4]').val();
	if (_dormID == '' || _roomNo == '' || _weekTs == '' || _sv1 == '' || _sv2 == '' || _sv3 == '' || _sv4 == '') {
		return false;
	}
	return true;
}
/**
 * 清空表單
 */
function clearForm() {
	$('#markSet form input[name=dormID]').val('');
	$('#markSet form input[name=roomNo]').val('');
	$('#markSet form select[name=weekTs]').combobox('select', '1');
	$('#markSet form input[name=sv1]').val('');
	$('#markSet form input[name=sv2]').val('');
	$('#markSet form input[name=sv3]').val('');
	$('#markSet form input[name=sv4]').val('');
}
/**
 * 滑出提示
 * 
 * @param info
 */
function slide(info) {
	$.messager.show({
		title : '提示',
		msg : info,
		timeout : 3000,
		showType : 'slide'
	});
}