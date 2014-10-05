package com.dms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.common.PaginationHelper;
import com.dms.constant.MarkConstant;
import com.dms.dao.MarkDAO;
import com.dms.dao.RoomDAO;
import com.dms.pojo.Mark;
import com.dms.pojo.Room;
import com.dms.pojo.SimpleMark;
import com.dms.service.MarkService;
import com.dms.view.VIEW_MARK;

/**
 * 评分Service实现
 * 
 * @author SemF
 * 
 * @create 2014年9月1日 上午10:09:15
 * 
 * @version 1.0
 */
@Service("markService")
public class MarkServiceImpl implements MarkService {
	@Autowired
	private MarkDAO markDAO;
	@Autowired
	private RoomDAO roomDAO;

	@Override
	public List<SimpleMark> getMonthMark(Integer roomID, String markMonth) {
		List<SimpleMark> list = null;
		// 取出数据库中评分视图数据
		List<VIEW_MARK> data = markDAO.findWeekTSMarkView(roomID, markMonth);
		if (data != null && data.size() > 0) {
			// 定义二维数组存放Object[周次][评分内容]
			VIEW_MARK[][] tmpObj = new VIEW_MARK[MarkConstant.WEEKTSNUM][MarkConstant.MARKITEMNUM];
			// 遍历视图数据
			for (int i = 0, j = 0, k = 0, m = 0, n = 0; i < data.size(); i++) {
				if (MarkConstant.WEEKTS_ONE.equals(data.get(i).getMMARK_WEEKTS())) {
					tmpObj[0][j] = data.get(i);
					j++;
					if (j == MarkConstant.MARKITEMNUM) {
						j = 0;// j=评分项数目归为0
					}
				} else if (MarkConstant.WEEKTS_TWO.equals(data.get(i).getMMARK_WEEKTS())) {
					tmpObj[1][k] = data.get(i);
					k++;
					if (k == MarkConstant.MARKITEMNUM) {
						k = 0;// k=评分项数目归为0
					}
				} else if (MarkConstant.WEEKTS_THREE.equals(data.get(i).getMMARK_WEEKTS())) {
					tmpObj[2][m] = data.get(i);
					m++;
					if (m == MarkConstant.MARKITEMNUM) {
						m = 0;// m=评分项数目归为0
					}
				} else if (MarkConstant.WEEKTS_FOUR.equals(data.get(i).getMMARK_WEEKTS())) {
					tmpObj[3][n] = data.get(i);
					n++;
					if (n == MarkConstant.MARKITEMNUM) {
						n = 0;// n=评分项数目归为0
					}
				}
			}
			// 遍历二维数据，为SimpleMark设置
			list = new ArrayList<SimpleMark>();
			for (int i = 0; i < MarkConstant.WEEKTSNUM; i++) {
				SimpleMark simpleMark = null;
				// 判断是空执行continue执行下一组
				if (tmpObj[i][0] == null) {
					continue;
				} else {
					// simpleMark设值不需要判断变量
					simpleMark = new SimpleMark();
					simpleMark.setDormName(tmpObj[i][0].getDORMNAME());
					simpleMark.setRoomNo(tmpObj[i][0].getRNO());
					simpleMark.setMonth(tmpObj[i][0].getMMARK_MONTH());
					simpleMark.setWeekTS(tmpObj[i][0].getMMARK_WEEKTS());
					// simpleMark设值需要判断变量
					for (int j = 0; j < MarkConstant.MARKITEMNUM; j++) {
						if (MarkConstant.MARKVALUE1 == tmpObj[i][j].getMTYPE()) {
							// 评分项1分值
							simpleMark.setSorceValue1(tmpObj[i][j].getMSORCEVALUE());
						} else if (MarkConstant.MARKVALUE2 == tmpObj[i][j].getMTYPE()) {
							// 评分项2分值
							simpleMark.setSorceValue2(tmpObj[i][j].getMSORCEVALUE());
						} else if (MarkConstant.MARKVALUE3 == tmpObj[i][j].getMTYPE()) {
							// 评分项3分值
							simpleMark.setSorceValue3(tmpObj[i][j].getMSORCEVALUE());
						} else if (MarkConstant.MARKVALUE4 == tmpObj[i][j].getMTYPE()) {
							// 评分项4分值
							simpleMark.setSorceValue4(tmpObj[i][j].getMSORCEVALUE());
						}
					}
				}
				// 每周次评分保存到simpleMark
				list.add(simpleMark);
			}
			return list;
		}
		return null;
	}

	@Override
	public PaginationHelper getMarkView(int pageNow, int pageSize, String markMonth, String roomNo, String dormName) {
		try {
			PaginationHelper ph = new PaginationHelper();
			List<VIEW_MARK> dataList = markDAO.findPaginationMarkView(pageNow, pageSize, markMonth, roomNo, dormName);
			long rowCount = markDAO.getMarkViewRowCount(markMonth, roomNo, dormName);
			ph.setDataList(dataList);
			ph.setPageSize(pageSize);
			ph.setRowCount(rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void monthMark(Integer dormID, String roomNo, String mMarkMonth, String weekTs, Integer managerID, String[] desc, Double... sv)
			throws Exception {
		Room r = roomDAO.findRoomByRNo(roomNo, dormID);
		if (r == null) {
			throw new Exception("寝室不存在！");
		}
		boolean marked = markDAO.findWeekTSMarksExsit(r.getId(), weekTs, mMarkMonth);
		if (marked) {
			throw new Exception("已存在评分！");
		}
		Date nowDate = new Date();
		Integer roomID = r.getId();
		Mark mark1 = new Mark(null, "地面", 1, sv[0], nowDate, desc[0], roomID, mMarkMonth, weekTs, managerID);
		Mark mark2 = new Mark(null, "桌面", 2, sv[1], nowDate, desc[1], roomID, mMarkMonth, weekTs, managerID);
		Mark mark3 = new Mark(null, "阳台", 3, sv[2], nowDate, desc[2], roomID, mMarkMonth, weekTs, managerID);
		Mark mark4 = new Mark(null, "厕所", 4, sv[3], nowDate, desc[3], roomID, mMarkMonth, weekTs, managerID);
		try {
			markDAO.addMark(mark1);
			markDAO.addMark(mark2);
			markDAO.addMark(mark3);
			markDAO.addMark(mark4);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public boolean ifMarkExsit(Integer dormID, String roomNo, String WeekTS, String markMonth) throws Exception {
		Room r = roomDAO.findRoomByRNo(roomNo, dormID);
		if (r == null) {
			throw new Exception("寝室不存在！");
		}
		return markDAO.findWeekTSMarksExsit(r.getId(), WeekTS, markMonth);
	}
}
