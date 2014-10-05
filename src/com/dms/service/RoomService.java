package com.dms.service;

import com.dms.common.PaginationHelper;
import com.dms.pojo.Room;

/**
 * 寝室Service接口
 * 
 * @author SemF
 * 
 * @create 2014年9月6日 下午5:15:03
 * 
 * @version 1.0
 */
public interface RoomService {
	/**
	 * 添加寝室
	 * 
	 * @param rNo
	 * @param rDesc
	 * @param state
	 * @param dormID
	 * @return
	 * @throws Exception
	 */
	public Room addRoom(String rNo, String rDesc, int state, Integer dormID) throws Exception;

	/**
	 * 分页查询寝室视图
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @param dormName
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getRoomView(int pageNow, int pageSize, String searchWord, String dormName) throws Exception;

	/**
	 * 批量删除寝室
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public void batchDelRoom(String ids) throws Exception;

	/**
	 * 修改寝室
	 * 
	 * @param rNo
	 * @param rDesc
	 * @param dormID
	 * @param state
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Room updateRoom(String rNo, String rDesc, Integer dormID, Integer state, Integer id);
}
