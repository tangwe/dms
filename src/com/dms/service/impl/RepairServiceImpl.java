package com.dms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.dms.common.PaginationHelper;
import com.dms.constant.CommonConstant;
import com.dms.constant.RepairConstant;
import com.dms.dao.RepairDAO;
import com.dms.dao.RoomDAO;
import com.dms.pojo.Repair;
import com.dms.pojo.Room;
import com.dms.service.RepairService;
import com.dms.view.VIEW_RE;

/**
 * 报修Service 实现
 * 
 * @author SemF
 * 
 * @create 2014年9月4日 上午10:30:17
 * 
 * @version 1.0
 */
@Service("repairService")
public class RepairServiceImpl implements RepairService {
	@Autowired
	private RepairDAO repairDAO;
	@Autowired
	private RoomDAO roomDAO;

	@Override
	public PaginationHelper getPaginationReViews(int pageNow, String searchWord, Integer stuID) throws Exception {
		try {
			List<VIEW_RE> dataList = repairDAO.findPaginationReView(pageNow, CommonConstant.PAGE_SIZE, searchWord, 0, stuID);
			long rowCount = repairDAO.getReRowCount(searchWord, stuID);
			PaginationHelper ph = new PaginationHelper(dataList, rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Repair addRepair(String reName, String roomNo, String reContent, Integer dormID, Integer stuID) {
		try {
			Room room = roomDAO.findRoomByRNo(roomNo, dormID);
			Integer roomID = room == null ? null : room.getId();
			if (roomID == null) {
				return null;
			}
			Repair re = new Repair();
			re.setReContent(reContent);
			re.setReName(reName);
			re.setReStuDate(new Date());
			re.setRoomID(roomID);
			re.setState(RepairConstant.RE_ASK);
			re.setStuID(stuID);
			return repairDAO.addRepair(re);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaginationHelper getPaginationReViewsWaitCheck(int pageNow, int pageSize, String searchWord) throws Exception {
		try {
			PaginationHelper ph = new PaginationHelper();
			List<VIEW_RE> dataList = repairDAO.findPaginationRepairView(pageNow, pageSize, searchWord, "'1'");
			long rowCount = repairDAO.getRepairRowCount(searchWord, "'1'");
			ph.setDataList(dataList);
			ph.setPageSize(pageSize);
			ph.setRowCount(rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public PaginationHelper getPaginationReViewsChecked(int pageNow, int pageSize, String searchWord) throws Exception {
		try {
			PaginationHelper ph = new PaginationHelper();
			List<VIEW_RE> dataList = repairDAO.findPaginationRepairView(pageNow, pageSize, searchWord, "'2','3'");
			long rowCount = repairDAO.getRepairRowCount(searchWord, "'2','3'");
			ph.setDataList(dataList);
			ph.setPageSize(pageSize);
			ph.setRowCount(rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public PaginationHelper getPaginationReViews(int pageNow, int pageSize, String searchWord) throws Exception {
		try {
			PaginationHelper ph = new PaginationHelper();
			List<VIEW_RE> dataList = repairDAO.findPaginationRepairView(pageNow, pageSize, searchWord, "'1','2','3'");
			long rowCount = repairDAO.getRepairRowCount(searchWord, "'1','2','3'");
			ph.setDataList(dataList);
			ph.setPageSize(pageSize);
			ph.setRowCount(rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void batchDelRe(String ids) throws Exception {
		try {
			String[] idsArr = ids.split(",");
			for (String id : idsArr) {
				repairDAO.delRe(Integer.valueOf(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Repair updateRe(Integer reID, String mReply, Integer manID, int state) throws Exception {
		try {
			Repair re = repairDAO.updateRe(reID, mReply, new Date(), manID, state);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
