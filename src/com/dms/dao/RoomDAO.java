package com.dms.dao;

import java.util.List;

import com.dms.pojo.Room;
import com.dms.view.VIEW_ROOM;

/**
 * 寝室接口 操作DMROOM
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午2:08:22
 * 
 * @version 1.0
 */
public interface RoomDAO {
	/**
	 * 添加一条DMROOM记录
	 * 
	 * @param dormID
	 * @return
	 */
	public Room addRoom(Room room);

	/**
	 * 删除一条DMROOM记录
	 * 
	 * @param roomID
	 * @return
	 */
	public Room delRoom(Integer roomID);

	/**
	 * 修改一条DMROOM记录
	 * 
	 * @param roomID
	 * @return
	 */
	public Room updateRoom(Room room);

	/**
	 * 查询指定ID的DMROOM记录
	 * 
	 * @param roomID
	 * @return
	 */
	public Room findRoomByID(Integer roomID);

	/**
	 * 查询指定roomINo的DMROOM记录
	 * 
	 * @param roomNo
	 * @return
	 */
	public Room findRoomByRNo(String roomNo, Integer dormID);

	/**
	 * 分页 查询DMROOM记录
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @param dormID
	 * @return
	 */
	public List<Room> findPaginationRooms(int pageNow, int pageSize, String searchWord, Integer dormID);

	/**
	 * 查询DMROOM总记录数
	 * 
	 * @return
	 */
	public long getRoomRowCount(String searchWord, String dormName);

	/**
	 * 分页查询DMROOM视图
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @param dormID
	 * @return
	 */
	public List<VIEW_ROOM> findPaginationRoomView(int pageNow, int pageSize, String searchWord, String dormName);
}
