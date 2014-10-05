package com.dms.service;

import com.dms.common.PaginationHelper;
import com.dms.pojo.Repair;

/**
 * 报修Service
 * 
 * @author SemF
 * 
 * @create 2014年9月4日 上午10:24:36
 * 
 * @version 1.0
 */
public interface RepairService {
	/**
	 * 获取报修记录(学生)
	 * 
	 * @param pageNow
	 * @param searchWord
	 * @param stuID
	 * @return
	 */
	public PaginationHelper getPaginationReViews(int pageNow, String searchWord, Integer stuID) throws Exception;

	/**
	 * 添加申请
	 * 
	 * @param reName
	 * @param roomNo
	 * @param reContent
	 * @return
	 */
	public Repair addRepair(String reName, String roomNo, String reContent, Integer dormID, Integer stuID);

	/**
	 * 获取待审核的报修记录（管理员用）
	 * 
	 * @param pageNow
	 * @param searchWord
	 * @param stuID
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getPaginationReViewsWaitCheck(int pageNow, int pageSize, String searchWord) throws Exception;

	/**
	 * 获取已经审核的报修记录（管理员用）
	 * 
	 * @param pageNow
	 * @param searchWord
	 * @param stuID
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getPaginationReViewsChecked(int pageNow, int pageSize, String searchWord) throws Exception;

	/**
	 * 获取审核的报修记录（管理员用）
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getPaginationReViews(int pageNow, int pageSize, String searchWord) throws Exception;

	/**
	 * 批量删除报修记录
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void batchDelRe(String ids) throws Exception;

	/**
	 * 审核回复修改报修记录
	 * 
	 * @param reID
	 * @param mReply
	 * @param manID
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public Repair updateRe(Integer reID, String mReply, Integer manID, int state) throws Exception;
}
