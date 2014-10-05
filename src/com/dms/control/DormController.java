package com.dms.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.common.DataGridResponse;
import com.dms.common.JsonAjaxData;
import com.dms.common.JsonAjaxResult;
import com.dms.common.PaginationHelper;
import com.dms.pojo.Dorm;
import com.dms.service.DormService;

/**
 * 宿舍控制器
 * 
 * @author SemF
 * 
 * @create 2014年9月4日 下午4:21:27
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/DormController")
public class DormController extends BaseController {
	@Autowired
	private DormService dormService;
	private JsonAjaxData<Dorm> ajaxData = new JsonAjaxData<Dorm>();
	private DataGridResponse dataGrid = new DataGridResponse();
	private JsonAjaxResult ajaxResult = new JsonAjaxResult();

	/**
	 * 加载使用中宿舍
	 * 
	 * @return
	 */
	@RequestMapping("/loadDormInUse.do")
	@ResponseBody
	public JsonAjaxData<Dorm> loadDormInUse() {
		List<Dorm> dataList = dormService.getDormsInUse();
		if (dataList != null && dataList.size() > 0) {
			ajaxData.setJsonData(true, dataList, null, null, "加载宿舍成功！");
		} else {
			ajaxData.setJsonData(false, null, null, null, "加载宿舍失败！");
		}
		return ajaxData;
	}

	/**
	 * 加载使用中宿舍
	 * 
	 * @return
	 */
	@RequestMapping("/loadDormInUseCombox.do")
	@ResponseBody
	public List<Dorm> loadDormInUseCombox() {
		List<Dorm> list = dormService.getDormsInUse();
		return list;
	}

	/**
	 * 加载所有宿舍
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadDorms.do")
	@ResponseBody
	public DataGridResponse loadDorms() {
		try {
			PaginationHelper ph = dormService.getDorms();
			List<Dorm> rows = (List<Dorm>) ph.getDataList();
			long total = ph.getRowCount();
			dataGrid.setDataGridResponse(rows, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;

	}

	/**
	 * 添加宿舍
	 * 
	 * @param dName
	 * @param dDesc
	 * @param state
	 * @return
	 */
	@RequestMapping("/addDorm.do")
	@ResponseBody
	public JsonAjaxResult addDorm(String dName, String dDesc, Integer state) {
		Dorm d = dormService.addDorm(dName, dDesc, state);
		if (d != null) {
			ajaxResult.setJsonResult(true, "添加[" + d.getdName() + "]成功！");
		} else {
			ajaxResult.setJsonResult(false, "添加失败！");
		}
		return ajaxResult;
	}

	/**
	 * 批量删除宿舍
	 * 
	 * @param ids
	 * @return
	 */

	@RequestMapping("/batchDelDorms.do")
	@ResponseBody
	public JsonAjaxResult batchDelDorms(String ids) {
		try {
			dormService.batchDelDorm(ids);
			ajaxResult.setJsonResult(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "删除失败！");
		}
		return ajaxResult;
	}

	/**
	 * 修改宿舍
	 * 
	 * @param dName
	 * @param dDesc
	 * @param state
	 * @param dormID
	 * @return
	 */
	@RequestMapping("/updateDorm.do")
	@ResponseBody
	public JsonAjaxResult updateDorm(String dName, String dDesc, Integer state, Integer dormID) {
		try {
			Dorm d = dormService.updateDorm(dName, dDesc, state, dormID);
			ajaxResult.setJsonResult(true, "修改[" + d.getdName() + "]成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "修改失败！");
		}
		return ajaxResult;
	}
}
