package com.dms.service;

import java.util.List;

import com.dms.common.PaginationHelper;
import com.dms.pojo.SimpleMark;

/**
 * 评分Sevice
 * 
 * @author SemF
 * 
 * @create 2014年9月1日 上午12:11:11
 * 
 * @version 1.0
 */
public interface MarkService {
	/**
	 * 获取寝室月评分
	 * 
	 * @param roomID
	 * @param MarkMonth
	 * @return
	 */
	public List<SimpleMark> getMonthMark(Integer roomID, String markMonth);

	/**
	 * 分页获取评分信息
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param markMonth
	 * @param roomNo
	 * @param dormName
	 * @return
	 */
	public PaginationHelper getMarkView(int pageNow, int pageSize, String markMonth, String roomNo, String dormName);

	/**
	 * 寝室评分
	 * 
	 * @param dormID
	 * @param roomNo
	 * @param weekTs
	 * @param manageID
	 * @param desc
	 * @param sv
	 */
	public void monthMark(Integer dormID, String roomNo, String mMarkMonth, String weekTs, Integer managerID, String[] desc, Double... sv)
			throws Exception;

	/**
	 * 判断是否已经评分
	 * 
	 * @param roomID
	 * @param WeekTS
	 * @param markMonth
	 * @return
	 * @throws Exception 
	 */
	public boolean ifMarkExsit(Integer dormID, String roomNo, String WeekTS, String markMonth) throws Exception;
}
