package com.dms.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.dms.pojo.Dorm;

/**
 * 宿舍DAO 操作表DMDORM
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午2:00:56
 * 
 * @version 1.0
 */
public interface DormDAO {
	/**
	 * 添加一条DMDORM记录
	 * 
	 * @param d
	 * @return
	 */
	public Dorm addDorm(Dorm d) throws DataAccessException;

	/**
	 * 删除一条DMDORM记录
	 * 
	 * @param dormID
	 * @return
	 */
	public Dorm delDorm(Integer dormID);

	/**
	 * 修改一条DMDORM记录
	 * 
	 * @param d
	 * @return
	 */
	public Dorm updateDorm(Dorm d);

	/**
	 * 查询指定ＩＤDMDORM记录
	 * 
	 * @param dormID
	 * @return
	 */
	public Dorm findDormByID(Integer dormID);

	/**
	 * 查询所有DMDORM记录
	 * 
	 * @return
	 */

	public List<Dorm> findAllDorms(int pageNow, int pageSize);

	/**
	 * 查询所有STATE=1DMDORM记录
	 * 
	 * @return
	 */
	public List<Dorm> findAllDormsInUse();

	/**
	 * 获取DMDORM所有记录数
	 * 
	 * @return
	 */
	public long getDormCount();
}
