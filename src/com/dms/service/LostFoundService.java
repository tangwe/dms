package com.dms.service;

import com.dms.common.PaginationHelper;
import com.dms.pojo.LostFound;

/**
 * 寻失Service
 * 
 * @author SemF
 * 
 * @create 2014年9月2日 下午12:57:57
 * 
 * @version 1.0
 */
public interface LostFoundService {
	/**
	 * 发布寻物启事或失物招领
	 * 
	 * @param lf
	 * @return
	 * @throws Exception
	 */
	public LostFound addLostOrFound(LostFound lf) throws Exception;

	/**
	 * 获取大厅寻物启事和失物招领
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getPaginationLFViews(int pageSize, int pageNow, String searchWord) throws Exception;

	/**
	 * 获取学生寻物启事和失物招领
	 * 
	 * @param pageNow
	 * @param searchWord
	 * @param stuID
	 * @return
	 */
	public PaginationHelper getPaginationLFByStuID(int pageNow, String searchWord, Integer stuID) throws Exception;
}
