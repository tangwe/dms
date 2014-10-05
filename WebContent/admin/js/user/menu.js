var userList;
var tabsObj;
$(function() {
	userList = $(".menu_li");
	tabsObj = $('#tt');
	userList.click(function() {
		//判断当前title是否已经存在，如果存在就用已经存在的，不存在就新加一个tab
			if (!tabsObj.tabs('exists', $(this).text())) {
				tabsObj.tabs('add', {
					id : $(this).attr('id'),
					title : $(this).text(),
					href : $(this).attr('href'),
					closable : true
				});
			} else {
				tabsObj.tabs('select', $(this).text());
			}
		});
});