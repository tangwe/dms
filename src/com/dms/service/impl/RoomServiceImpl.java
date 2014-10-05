package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.common.PaginationHelper;
import com.dms.dao.RoomDAO;
import com.dms.pojo.Room;
import com.dms.service.RoomService;
import com.dms.view.VIEW_ROOM;

/**
 * RoomService实现
 * 
 * @author SemF
 * 
 * @create 2014年9月6日 下午5:19:43
 * 
 * @version 1.0
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDAO roomDAO;

	@Override
	public Room addRoom(String rNo, String rDesc, int state, Integer dormID) throws Exception {
		try {
			Room r = new Room(null, rNo, state, rDesc, dormID);
			Room room = roomDAO.addRoom(r);
			return room;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public PaginationHelper getRoomView(int pageNow, int pageSize, String searchWord, String dormName) throws Exception {
		try {
			PaginationHelper ph = new PaginationHelper();
			List<VIEW_ROOM> dataList = roomDAO.findPaginationRoomView(pageNow, pageSize, searchWord, dormName);
			long rowCount = roomDAO.getRoomRowCount(searchWord, dormName);
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
	public void batchDelRoom(String ids) throws Exception {
		try {
			String[] idsArr = ids.split(",");
			for (String id : idsArr) {
				roomDAO.delRoom(Integer.valueOf(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Room updateRoom(String rNo, String rDesc, Integer dormID, Integer state, Integer id) {
		Room r = new Room(id, rNo, state, rDesc, dormID);
		Room room = roomDAO.updateRoom(r);
		return room;
	}
}
