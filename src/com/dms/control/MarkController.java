package com.dms.control;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.common.CommonUtil;
import com.dms.common.DataGridResponse;
import com.dms.common.JsonAjaxData;
import com.dms.common.JsonAjaxResult;
import com.dms.common.PaginationHelper;
import com.dms.pojo.SimpleMark;
import com.dms.pojo.User;
import com.dms.service.MarkService;
import com.dms.view.VIEW_MARK;

/**
 * MarkController评分控制器
 * 
 * @author SemF
 * 
 * @create 2014年9月1日 上午11:17:40
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/MarkController")
public class MarkController extends BaseController {
	private JsonAjaxData<SimpleMark> ajaxData = new JsonAjaxData<SimpleMark>();
	private DataGridResponse dataGrid = new DataGridResponse();
	private JsonAjaxResult ajaxResult = new JsonAjaxResult();
	@Autowired
	private MarkService markService;

	/**
	 * 寝室评分月数据展示
	 * 
	 * @param year
	 * @param month
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMonthMarkData.do")
	@ResponseBody
	public JsonAjaxData<SimpleMark> getMonthMarkData(String year, String month, HttpServletRequest request) {
		User user = this.getSessionUser(request);// 获取Session中学生信息
		if (user != null && user.getRoomID() != null) {
			String markMonth = getMarkMonth(year, month);// 评分月
			List<SimpleMark> dataList = markService.getMonthMark(user.getRoomID(), markMonth);
			if (dataList != null && dataList.size() > 0) {
				ajaxData.setJsonData(true, dataList, dataList.size(), null, "请求数据成功！");
			} else {
				ajaxData.setJsonData(true, dataList, 0, null, "没有请求数据！");
			}
		} else {
			ajaxData.setJsonData(false, null, null, null, "请求数据失败！用户未登录或者不存在登陆用户信息！");
		}
		return ajaxData;
	}

	/**
	 * 加载寝室评分数据（管理）
	 * 
	 * @param page
	 * @param rows
	 * @param searchWord1
	 * @param searchWord2
	 * @param searchWord3
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadMarkView.do")
	@ResponseBody
	public DataGridResponse loadMarkView(Integer page, Integer rows, String year, String month, String searchWord2, String searchWord3) {
		Integer pageNow = page;
		Integer pageSize = rows;
		String searchWord1 = "";
		if (year == null || "".equals(year) || month == null || "".equals(month)) {
			searchWord1 = "";
		} else {
			searchWord1 = getMarkMonth(year, month);// 评分月
		}
		if (searchWord2 == null || "".equals(searchWord2)) {
			searchWord2 = "";
		}
		if (searchWord3 == null || "".equals(searchWord3)) {
			searchWord3 = "";
		}
		try {
			PaginationHelper ph = markService.getMarkView(pageNow, pageSize, searchWord1, searchWord2, searchWord3);
			List<VIEW_MARK> dataList = (List<VIEW_MARK>) ph.getDataList();
			Long total = ph.getRowCount();
			dataGrid.setDataGridResponse(dataList, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 评分
	 * 
	 * @param dormID
	 * @param roomName
	 * @param weekTs
	 * @param sv1
	 * @param sv2
	 * @param sv3
	 * @param sv4
	 * @param desc1
	 * @param desc2
	 * @param desc3
	 * @param desc4
	 * @param request
	 * @return
	 */
	@RequestMapping("/mark.do")
	@ResponseBody
	public JsonAjaxResult mark(Integer dormID, String roomNo, String weekTs, Double sv1, Double sv2, Double sv3, Double sv4, String desc1,
			String desc2, String desc3, String desc4, HttpServletRequest request) {
		User u = this.getSessionUser(request);
		String mMarkMonth = this.getMarkMonth();
		String[] desc = { desc1, desc2, desc3, desc4 };
		try {
			markService.monthMark(dormID, roomNo, mMarkMonth, weekTs, u.getId(), desc, sv1, sv2, sv3, sv4);
			ajaxResult.setJsonResult(true, "评分成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "评分失败！");
		}
		return ajaxResult;
	}

	/**
	 * 判断是否已经评分
	 * 
	 * @param dormID
	 * @param roomNo
	 * @param WeekTS
	 * @param markMonth
	 * @return
	 */
	@RequestMapping("/ifMarked.do")
	@ResponseBody
	public JsonAjaxResult ifMarked(Integer dormID, String roomNo, String WeekTS) {
		System.out.println(dormID + "======");
		System.out.println(roomNo + "======");
		System.out.println(WeekTS + "======");
		String mMarkMonth = this.getMarkMonth();
		try {
			boolean flag = markService.ifMarkExsit(dormID, roomNo, WeekTS, mMarkMonth);
			if (flag) {
				ajaxResult.setJsonResult(false, "该周次已经评分！");
			} else {
				ajaxResult.setJsonResult(true, "未评分！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "寝室不存在！");
		}
		return ajaxResult;
	}

	/**
	 * 获得评分月
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private String getMarkMonth(String year, String month) {
		if (month.indexOf("0") == -1) {
			month = "0" + month;
		}
		return year + month;
	}

	/**
	 * 获得评分月
	 * 
	 * @return
	 */
	private String getMarkMonth() {
		Date d = new Date();
		String date = CommonUtil.formatDate(d);
		return date.substring(0, 4) + date.substring(5, 7);
	}
}
