package com.dms.control;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.common.JsonAjaxData;
import com.dms.common.JsonAjaxResult;
import com.dms.common.PaginationHelper;
import com.dms.constant.CommonConstant;
import com.dms.pojo.LostFound;
import com.dms.pojo.User;
import com.dms.service.LostFoundService;
import com.dms.view.VIEW_LFSTU;

/**
 * 寻失控制器
 * 
 * @author SemF
 * 
 * @create 2014年9月2日 下午1:06:41
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/LostFoundController")
public class LostFoundController extends BaseController {
	@Autowired
	private LostFoundService lostFoundService;
	private JsonAjaxResult ajaxResult = new JsonAjaxResult();
	private JsonAjaxData<VIEW_LFSTU> ajaxDataViewLFStu = new JsonAjaxData<VIEW_LFSTU>();
	private JsonAjaxData<LostFound> ajaxDataLFStu = new JsonAjaxData<LostFound>();

	/**
	 * 发布寻物启事或者失物招领
	 * 
	 * @param lfTag
	 * @param lfType
	 * @param lfContent
	 * @param request
	 * @return
	 */
	@RequestMapping("/pubLostOrFound.do")
	@ResponseBody
	public JsonAjaxResult pubLostOrFound(String lfTag, Integer lfType, String lfContent, HttpServletRequest request) {
		User user = this.getSessionUser(request);
		if (user != null) {
			LostFound lf = new LostFound();
			lf.setLfContent(lfContent);
			lf.setLfDate(new Date());
			lf.setLfTag(lfTag);
			lf.setLfType(lfType);
			lf.setState(1);
			lf.setStuID(user.getId());
			try {
				LostFound lfRs = lostFoundService.addLostOrFound(lf);
				ajaxResult.setJsonResult(true, "发布标签为 <" + lfRs.getLfTag() + ">" + (lfType == 0 ? "寻物启事" : "失物招领") + "成功！");
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setJsonResult(false, "发布" + (lfType == 0 ? "寻物启事" : "失物招领") + "失败！请检查所填写内容或咨询管理员！");
			}
		}
		return ajaxResult;
	}

	/**
	 * 加载物品寻失记录（寻失大厅）
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadLostFoundViews.do")
	@ResponseBody
	public JsonAjaxData<VIEW_LFSTU> loadLostFoundViews(Integer pageNow, String searchWord) {
		if (!isANum(pageNow)) {
			pageNow = 1;
		}
		try {
			PaginationHelper ph = lostFoundService.getPaginationLFViews(CommonConstant.PAGE_SIZE, pageNow, searchWord);
			List<VIEW_LFSTU> dataList = (List<VIEW_LFSTU>) ph.getDataList();
			long totalCount = ph.getRowCount();
			int totalPage = ph.getTotalPage();
			ajaxDataViewLFStu.setJsonData(true, dataList, (int) totalCount, totalPage, "加载物品寻失记录成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxDataViewLFStu.setJsonData(false, null, null, null, "加载物品寻失记录失败！");
		}
		return ajaxDataViewLFStu;
	}

	/**
	 * 加载物品寻失记录（我的寻失）
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadStuLostFound.do")
	@ResponseBody
	public JsonAjaxData<LostFound> loadStuLostFound(Integer pageNow, String searchWord, HttpServletRequest request) {
		if (!isANum(pageNow)) {
			pageNow = 1;
		}
		try {
			User user = this.getSessionUser(request);
			PaginationHelper ph = lostFoundService.getPaginationLFByStuID(pageNow, searchWord, user.getId());
			List<LostFound> dataList = (List<LostFound>) ph.getDataList();
			long totalCount = ph.getRowCount();
			int totalPage = ph.getTotalPage();
			ajaxDataLFStu.setJsonData(true, dataList, (int) totalCount, totalPage, "加载我的物品寻失记录成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxDataLFStu.setJsonData(false, null, null, null, "加载我的物品寻失记录失败！");
		}
		return ajaxDataLFStu;
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
