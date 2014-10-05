package com.dms.dao;

import java.util.List;

import com.dms.pojo.Mark;
import com.dms.view.VIEW_MARK;

/**
 * MarkDAO 操作表DMMARK
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午2:55:28
 * 
 * @version 1.0
 */
public interface MarkDAO {
	/**
	 * 添加一条DMMARK记录
	 * 
	 * @param mark
	 * @param roomID
	 * @return
	 */
	public Mark addMark(Mark mark);

	/**
	 * 删除一条DMMARK记录
	 * 
	 * @param roomID
	 * @return
	 */
	public Mark delMark(Integer markID);

	/**
	 * 修改一条DMMARK记录
	 * 
	 * @param mark
	 * @return
	 */
	public Mark updateMark(Mark mark);

	/**
	 * 查询DMMARK一周次评分记录
	 * 
	 * @param roomID
	 * @param WeekTS
	 * @param Month
	 * @return
	 */
	public List<Mark> findWeekTSMarks(Integer roomID, String WeekTS, String markMonth);

	/**
	 * 查询DMMARK一周次是否存在评分记录
	 * 
	 * @param roomID
	 * @param WeekTS
	 * @param markMonth
	 * @return
	 */
	public boolean findWeekTSMarksExsit(Integer roomID, String WeekTS, String markMonth);

	/**
	 * 查询月评分记录
	 * 
	 * @param roomID
	 * @param Month
	 * @return
	 */
	public List<VIEW_MARK> findWeekTSMarkView(Integer roomID, String markMonth);

	/**
	 * 查询寝室DMMARK评分
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param roomID
	 * @return
	 */
	public List<Mark> findPaginationMarks(int pageNow, int pageSize, Integer roomID);

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public long getMaRowCount();

	/**
	 * 获取所有记录存在查询条件
	 * 
	 * @return
	 */
	public long getMarkViewRowCount(String searchWord1, String searchWord2, String searchWord3);

	/**
	 * 查询mark视图
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord1
	 * @param searchWord2
	 * @param searchWord3
	 * @return
	 */
	public List<VIEW_MARK> findPaginationMarkView(int pageNow, int pageSize, String searchWord1, String searchWord2, String searchWord3);

}
