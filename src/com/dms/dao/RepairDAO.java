package com.dms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.dms.pojo.Repair;
import com.dms.view.VIEW_RE;

/**
 * 报修DAO 操作表DMREPAIR
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午2:33:08
 * 
 * @version 1.0
 */
public interface RepairDAO {
	/**
	 * 添加一条DMREPAIR记录
	 * 
	 * @param re
	 * @return
	 */
	public Repair addRepair(Repair re) throws DataAccessException;

	/**
	 * 更新一条DMREPAIR 状态
	 * 
	 * @param reID
	 * @param state
	 * @return
	 */
	public Repair updateReState(Integer reID, int state);

	/**
	 * 更新一条DMREPAIR 审核回复，审核时间，管理员 ID，状态
	 * 
	 * @param reID
	 * @param mReply
	 * @param mDate
	 * @param manID
	 * @param state
	 * @return
	 */
	public Repair updateRe(Integer reID, String mReply, Date mDate, Integer manID, int state);

	/**
	 * 删除一条DMREPAIR记录
	 * 
	 * @param reID
	 * @return
	 */
	public Repair delRe(Integer reID);

	/**
	 * 分页查询VIEW_RE视图记录
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @param state
	 * @param stuID
	 * @return
	 */
	public List<VIEW_RE> findPaginationReView(int pageNow, int pageSize, String searchWord, Integer state, Integer stuID);

	/**
	 * 分页查询DMREPAIR记录
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @param state
	 * @return
	 */
	public List<Repair> findPaginationRes(int pageNow, int pageSize, String searchWord, Integer state);

	/**
	 * 查询指定ID的DMREPAIR记录
	 * 
	 * @param reID
	 * @return
	 */
	public Repair findReByID(Integer reID);

	/**
	 * 查询DMREPAIR总记录数
	 * 
	 * @return
	 */
	public long getReRowCount(String searchWord, Integer stuID);

	/**
	 * 分页查询VIEW_RE视图记录（用于管理员）
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @param state
	 * @return
	 */
	public List<VIEW_RE> findPaginationRepairView(int pageNow, int pageSize, String searchWord, String state);

	/**
	 * 分页查询VIEW_RE视图记录（用于管理员）
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord1
	 * @param searchWord2
	 * @param state
	 * @return
	 */
	public List<VIEW_RE> findPaginationRepairView(int pageNow, int pageSize, String searchWord1, String searchWord2, String state);

	/**
	 * 查询VIEW_RE总记录数(管理员用)
	 * 
	 * @param searchWord
	 * @param state
	 * @return
	 */
	public long getRepairRowCount(String searchWord, String state);

	/**
	 * 查询VIEW_RE总记录数(管理员用)
	 * 
	 * @param searchWord1
	 * @param searchWord2
	 * @param state
	 * @return
	 */
	public long getRepairRowCount(String searchWord1, String searchWord2, String state);

}
