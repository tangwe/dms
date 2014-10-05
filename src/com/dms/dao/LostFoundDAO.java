package com.dms.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.dms.pojo.LostFound;
import com.dms.view.VIEW_LFSTU;

/**
 * 寻物启事，失物招领DAO 操作表DMLOSTFOU
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午3:29:06
 * 
 * @version 1.0
 */
public interface LostFoundDAO {
	/**
	 * 添加一条DMLOSTFOU记录
	 * 
	 * @param lf
	 * @return
	 * @throws DataAccessException
	 */
	public LostFound addLostFound(LostFound lf) throws DataAccessException;

	/**
	 * 删除 一条DMLOSTFOU记录
	 * 
	 * @param lfID
	 * @throws DataAccessException
	 * @return
	 */
	public LostFound delLostFound(Integer lfID) throws DataAccessException;

	/**
	 * 修改一条DMLOSTFOU记录状态值
	 * 
	 * @param lfID
	 * @throws DataAccessException
	 * @return
	 */
	public LostFound updateLFState(Integer lfID) throws DataAccessException;

	/**
	 * 修改一条DMLOSTFOU记录
	 * 
	 * @param lf
	 * @throws DataAccessException
	 * @return
	 */
	public LostFound updateLostFound(LostFound lf) throws DataAccessException;

	/**
	 * 查询指定ID的DMLOSTFOU记录
	 * 
	 * @param lfID
	 * @return
	 */
	public LostFound findLFByID(Integer lfID);

	/**
	 * 查询指定ID的关联视图
	 * 
	 * @param lfID
	 * @return
	 */
	public VIEW_LFSTU findLFViewByID(Integer lfID);

	/**
	 * 分页查询指定学生ID的DMLOSTFOU记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param searchWord
	 * @param stuID
	 * @return
	 */
	public List<LostFound> findLFByStuID(int pageSize, int pageNow, String searchWord, Integer stuID);

	/**
	 * 分页查询DMLOSTFOU记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param searchWord
	 * @return
	 */
	public List<LostFound> findPaginationLFs(int pageSize, int pageNow, String searchWord);

	/**
	 * 分页查询LFView记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param searchWord
	 * @return
	 */
	public List<VIEW_LFSTU> findPaginationLFViews(int pageSize, int pageNow, String searchWord);

	/**
	 * 分页查询个人LFView记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param searchWord
	 * @param stuID
	 * @return
	 */
	public List<VIEW_LFSTU> findPaginationLFViews(int pageSize, int pageNow, String searchWord, Integer stuID);

	/**
	 * 获取DMLOSTFOU总记录数
	 * 
	 * @return
	 */
	public long getLFRowCount(String searchWord,Integer stuID);
}
