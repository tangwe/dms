package com.dms.service;

import java.util.List;

import com.dms.common.PaginationHelper;
import com.dms.pojo.Dorm;

/**
 * 宿舍Service
 * 
 * @author SemF
 * 
 * @create 2014年9月4日 下午4:17:40
 * 
 * @version 1.0
 */
public interface DormService {
	/**
	 * 获取可使用的宿舍
	 * 
	 * @return
	 */
	public List<Dorm> getDormsInUse();

	/**
	 * 获取所有宿舍
	 * 
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getDorms() throws Exception;

	/**
	 * 添加宿舍
	 * 
	 * @param dName
	 * @param dDesc
	 * @param state
	 * @return
	 */
	public Dorm addDorm(String dName, String dDesc, int state);

	/**
	 * 批量删除宿舍
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public void batchDelDorm(String ids) throws Exception;

	/**
	 * 修改宿舍
	 * 
	 * @param dName
	 * @param dDesc
	 * @param state
	 * @param dormID
	 * @return
	 * @throws Exception
	 */
	public Dorm updateDorm(String dName, String dDesc, int state, Integer dormID) throws Exception;
}
