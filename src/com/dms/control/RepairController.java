package com.dms.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.common.DataGridResponse;
import com.dms.common.JsonAjaxData;
import com.dms.common.JsonAjaxResult;
import com.dms.common.PaginationHelper;
import com.dms.pojo.Repair;
import com.dms.pojo.User;
import com.dms.service.RepairService;
import com.dms.view.VIEW_RE;

/**
 * 报修控制器
 * 
 * @author SemF
 * 
 * @create 2014年9月4日 上午10:39:23
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/RepairController")
public class RepairController extends BaseController {
	@Autowired
	private RepairService repairService;
	private JsonAjaxData<VIEW_RE> ajaxDataViewRe = new JsonAjaxData<VIEW_RE>();
	private JsonAjaxResult ajaxResult = new JsonAjaxResult();
	private DataGridResponse dataGrid = new DataGridResponse();

	/**
	 * 加载报修记录
	 * 
	 * @param pageNow
	 * @param searchWord
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadRepairViews.do")
	@ResponseBody
	public JsonAjaxData<VIEW_RE> loadRepairViews(Integer pageNow, String searchWord, HttpServletRequest request) {
		if (!isANum(pageNow)) {
			pageNow = 1;
		}
		try {
			String _searchWord = searchWord != null && !searchWord.equals("") ? new String(searchWord.getBytes("ISO-8859-1"), "UTF-8") : "";
			System.out.println("searchWord=====" + _searchWord);
			User user = this.getSessionUser(request);
			PaginationHelper ph = repairService.getPaginationReViews(pageNow, _searchWord, user.getId());
			List<VIEW_RE> dataList = (List<VIEW_RE>) ph.getDataList();
			long totalCount = ph.getRowCount();
			int totalPage = ph.getTotalPage();
			ajaxDataViewRe.setJsonData(true, dataList, (int) totalCount, totalPage, "获取报修记录数据成功！ ");
		} catch (Exception e) {
			ajaxDataViewRe.setJsonData(false, null, null, null, "获取报修记录数据失败！ ");
			e.printStackTrace();
		}
		return ajaxDataViewRe;
	}

	/**
	 * 提交申请
	 * 
	 * @param reName
	 * @param dormID
	 * @param roomNo
	 * @param reContent
	 * @param request
	 * @return
	 */
	@RequestMapping("/reqRepair.do")
	@ResponseBody
	public JsonAjaxResult reqRepair(String reName, String roomNo, String reContent, Integer dormID, HttpServletRequest request) {
		User user = this.getSessionUser(request);
		Integer stuID = user == null ? null : user.getId();
		System.out.println("roomNo===" + roomNo);
		System.out.println("reContent===" + reContent);
		System.out.println("dormID===" + dormID);
		Repair re = repairService.addRepair(reName, roomNo, reContent, dormID, stuID);
		if (re != null) {
			ajaxResult.setJsonResult(true, "《" + re.getReName() + "》申请已在受理中，请等候管理员审核结果...");
		} else {
			ajaxResult.setJsonResult(false, "无法提交成功！输入不存在信息,请核实填写内容...");
		}
		return ajaxResult;
	}

	/**
	 * 加载未审核报修记录
	 * 
	 * @param page
	 * @param rows
	 * @param searchWord
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadReViewWaitCheck.do")
	@ResponseBody
	public DataGridResponse loadReViewWaitCheck(Integer page, Integer rows, String searchWord) {
		Integer pageNow = page;
		Integer pageSize = rows;
		if (searchWord == null || "".equals(searchWord)) {
			searchWord = "";
		}
		try {
			PaginationHelper ph = repairService.getPaginationReViewsWaitCheck(pageNow, pageSize, searchWord);
			List<VIEW_RE> dataList = (List<VIEW_RE>) ph.getDataList();
			Long total = ph.getRowCount();
			dataGrid.setDataGridResponse(dataList, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 加载已审核报修记录
	 * 
	 * @param page
	 * @param rows
	 * @param searchWord
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadReViewChecked.do")
	@ResponseBody
	public DataGridResponse loadReViewChecked(Integer page, Integer rows, String searchWord) {
		Integer pageNow = page;
		Integer pageSize = rows;
		if (searchWord == null || "".equals(searchWord)) {
			searchWord = "";
		}
		try {
			PaginationHelper ph = repairService.getPaginationReViewsChecked(pageNow, pageSize, searchWord);
			List<VIEW_RE> dataList = (List<VIEW_RE>) ph.getDataList();
			Long total = ph.getRowCount();
			dataGrid.setDataGridResponse(dataList, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 加载报修视图数据（管理员用）
	 * 
	 * @param page
	 * @param rows
	 * @param searchWord
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadReView.do")
	@ResponseBody
	public DataGridResponse loadReView(Integer page, Integer rows, String searchWord) {
		Integer pageNow = page;
		Integer pageSize = rows;
		if (searchWord == null || "".equals(searchWord)) {
			searchWord = "";
		}
		try {
			PaginationHelper ph = repairService.getPaginationReViews(pageNow, pageSize, searchWord);
			List<VIEW_RE> dataList = (List<VIEW_RE>) ph.getDataList();
			Long total = ph.getRowCount();
			dataGrid.setDataGridResponse(dataList, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 批量删除报修记录（管理员用）
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelRes.do")
	@ResponseBody
	public JsonAjaxResult batchDelRooms(String ids) {
		try {
			repairService.batchDelRe(ids);
			ajaxResult.setJsonResult(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "删除失败！");
		}
		return ajaxResult;
	}

	/**
	 * 审核报修内容
	 * 
	 * @param reID
	 * @param mReply
	 * @param state
	 * @param request
	 * @return
	 */
	@RequestMapping("/replyRe.do")
	@ResponseBody
	public JsonAjaxResult replyRe(Integer reID, String mReply, int state, HttpServletRequest request) {
		if (mReply == null || "".equals(mReply)) {
			mReply = "";
		}
		try {
			User man = this.getSessionUser(request);
			Repair re = repairService.updateRe(reID, mReply, man.getId(), state);
			ajaxResult.setJsonResult(true, "审核[" + re.getReName() + "]成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "审核失败！");
		}
		return ajaxResult;
	}

	/**
	 * 判断pageNow是否为数字
	 * 
	 * @param pageNow
	 * @return
	 */
	private boolean isANum(Integer pageNow) {
		try {
			int testNum = pageNow / 1;
			System.out.println(testNum);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
}
