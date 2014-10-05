package com.dms.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.common.DataGridResponse;
import com.dms.common.JsonAjaxResult;
import com.dms.common.PaginationHelper;
import com.dms.pojo.Room;
import com.dms.service.RoomService;
import com.dms.view.VIEW_ROOM;

/**
 * 寝室控制器
 * 
 * @author SemF
 * 
 * @create 2014年9月6日 下午7:02:49
 * 
 * @version 1.0
 */

@Controller
@RequestMapping("/RoomController")
public class RoomController extends BaseController {
	@Autowired
	private RoomService roomService;
	private DataGridResponse dataGrid = new DataGridResponse();
	private JsonAjaxResult ajaxResult = new JsonAjaxResult();

	/**
	 * 加载寝室数据
	 * 
	 * @param page
	 * @param rows
	 * @param searchWord1
	 * @param searchWord2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadRoomView.do")
	@ResponseBody
	public DataGridResponse loadRoomView(Integer page, Integer rows, String searchWord1, String searchWord2) {
		Integer pageNow = page;
		Integer pageSize = rows;
		if (searchWord1 == null || "".equals(searchWord1)) {
			searchWord1 = "";
		}
		if (searchWord2 == null || "".equals(searchWord2)) {
			searchWord2 = "";
		}
		try {
			PaginationHelper ph = roomService.getRoomView(pageNow, pageSize, searchWord1, searchWord2);
			List<VIEW_ROOM> dataList = (List<VIEW_ROOM>) ph.getDataList();
			Long total = ph.getRowCount();
			dataGrid.setDataGridResponse(dataList, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 添加寝室
	 * 
	 * @param rNo
	 * @param rDesc
	 * @param dormID
	 * @param state
	 * @return
	 */
	@RequestMapping("/addRoom.do")
	@ResponseBody
	public JsonAjaxResult addRoom(String rNo, String rDesc, Integer dormID, Integer state) {
		try {
			Room r = roomService.addRoom(rNo, rDesc, state, dormID);
			ajaxResult.setJsonResult(true, "添加[" + r.getrNo() + "寝室]成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "添加寝室失败！");
		}
		return ajaxResult;
	}

	/**
	 * 批量删除寝室
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelRooms.do")
	@ResponseBody
	public JsonAjaxResult batchDelRooms(String ids) {
		try {
			roomService.batchDelRoom(ids);
			ajaxResult.setJsonResult(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setJsonResult(false, "删除失败！");
		}
		return ajaxResult;
	}

	/**
	 * 修改寝室信息
	 * 
	 * @param rNo
	 * @param rDesc
	 * @param dormID
	 * @param state
	 * @param roomID
	 * @return
	 */
	@RequestMapping("/updateRoom.do")
	@ResponseBody
	public JsonAjaxResult updateRoom(String rNo, String rDesc, Integer dormID, Integer state, Integer roomID) {
		System.out.println("====roomID" + roomID);
		Room r = roomService.updateRoom(rNo, rDesc, dormID, state, roomID);
		if (r != null) {
			ajaxResult.setJsonResult(true, "修改[" + r.getrNo() + "寝室]成功！");
		} else {
			ajaxResult.setJsonResult(false, "修改失败！");
		}
		return ajaxResult;
	}
}
