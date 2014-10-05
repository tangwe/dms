package com.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dms.dao.BaseDAO;
import com.dms.dao.MarkDAO;
import com.dms.pojo.Mark;
import com.dms.view.VIEW_MARK;

@Repository("markDAO")
public class MarkDAOImpl extends BaseDAO implements MarkDAO {

	@Override
	public Mark addMark(Mark mark) {
		try {
			String sql = "INSERT INTO DMRMARK(MNAME,MTYPE,MSORCEVALUE,MMARKDATE,MDESC,ROOMID,MMARK_MONTH,MMARK_WEEKTS,MANAGERID) VALUES(?,?,?,?,?,?,?,?,?)";
			this.jdbcTemplate.update(sql, mark.getmName(), mark.getmType(), mark.getmSorceValue(), mark.getmMarkDate(), mark.getmDesc(),
					mark.getRoomID(), mark.getmMarkMonth(), mark.getmMarkWeekTS(), mark.getManagerID());
			return mark;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Mark delMark(Integer markID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mark updateMark(Mark mark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mark> findWeekTSMarks(Integer roomID, String WeekTS, String Month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mark> findPaginationMarks(int pageNow, int pageSize, Integer roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getMaRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<VIEW_MARK> findWeekTSMarkView(Integer roomID, String markMonth) {
		try {
			String sql = "SELECT RM.MNAME MARKNAME,RM.MTYPE,RM.MSORCEVALUE,RM.MMARKDATE,RM.MDESC,RM.MMARK_MONTH,"
					+ "RM.MMARK_WEEKTS,RO.RNO,RO.RDESC,RO.ID RID,MA.ID MID,DR.ID DID," + "DR.DNAME DORMNAME,MA.MNAME MANAGERNAME "
					+ "FROM DMRMARK RM " + "INNER JOIN DMROOM RO ON RO.ID = RM.ROOMID " + "INNER JOIN DMMANAGER MA ON MA.ID = RM.MANAGERID "
					+ "INNER JOIN DMDORM DR ON DR.ID = RO.DORMID WHERE RO.ID=? AND RM.MMARK_MONTH=?";
			List<VIEW_MARK> dataList = jdbcTemplate.query(sql, new Object[] { roomID, markMonth }, new RowMapper<VIEW_MARK>() {

				@Override
				public VIEW_MARK mapRow(ResultSet rs, int rowNum) throws SQLException {
					VIEW_MARK vMark = new VIEW_MARK();
					vMark.setDID(rs.getInt("DID"));
					vMark.setDORMNAME(rs.getString("DORMNAME"));
					vMark.setMANAGERNAME(rs.getString("MANAGERNAME"));
					vMark.setMARKNAME(rs.getString("MARKNAME"));
					vMark.setMDESC(rs.getString("MDESC"));
					vMark.setMID(rs.getInt("MID"));
					vMark.setMMARK_MONTH(rs.getString("MMARK_MONTH"));
					vMark.setMMARK_WEEKTS(rs.getString("MMARK_WEEKTS"));
					vMark.setMMARKDATE(rs.getDate("MMARKDATE") == null ? "" : rs.getDate("MMARKDATE").toString());
					vMark.setMSORCEVALUE(rs.getDouble("MSORCEVALUE"));
					vMark.setMTYPE(rs.getInt("MTYPE"));
					vMark.setRDESC(rs.getString("RDESC"));
					vMark.setRID(rs.getInt("RID"));
					vMark.setRNO(rs.getString("RNO"));
					return vMark;
				}
			});
			return dataList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getMarkViewRowCount(String searchWord1, String searchWord2, String searchWord3) {
		String sql = "SELECT COUNT(*) FROM DMRMARK RM INNER JOIN DMROOM RO ON RO.ID = RM.ROOMID INNER JOIN DMMANAGER MA ON MA.ID = RM.MANAGERID "
				+ "INNER JOIN DMDORM DR ON DR.ID = RO.DORMID WHERE 1=1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (searchWord1 != null && !searchWord1.equals("")) {
			sb.append("AND RM.MMARK_MONTH LIKE " + "'%" + searchWord1 + "%'" + " ");
		}
		if (searchWord2 != null && !searchWord2.equals("")) {
			sb.append("AND RO.RNO LIKE " + "'%" + searchWord1 + "%'" + " ");
		}
		if (searchWord3 != null && !searchWord3.equals("")) {
			sb.append("AND DR.DNAME LIKE " + "'%" + searchWord1 + "%'" + " ");
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	@Override
	public List<VIEW_MARK> findPaginationMarkView(int pageNow, int pageSize, String searchWord1, String searchWord2, String searchWord3) {
		try {
			String sql = "SELECT RM.MNAME MARKNAME,RM.MTYPE,RM.MSORCEVALUE,RM.MMARKDATE,RM.MDESC,RM.MMARK_MONTH,"
					+ "RM.MMARK_WEEKTS,RO.RNO,RO.RDESC,RO.ID RID,MA.ID MID,DR.ID DID," + "DR.DNAME DORMNAME,MA.MNAME MANAGERNAME "
					+ "FROM DMRMARK RM " + "INNER JOIN DMROOM RO ON RO.ID = RM.ROOMID " + "INNER JOIN DMMANAGER MA ON MA.ID = RM.MANAGERID "
					+ "INNER JOIN DMDORM DR ON DR.ID = RO.DORMID WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord1 != null && !searchWord1.equals("")) {
				sb.append("AND RM.MMARK_MONTH LIKE " + "'%" + searchWord1 + "%'" + " ");
			}
			if (searchWord2 != null && !searchWord2.equals("")) {
				sb.append("AND RO.RNO LIKE " + "'%" + searchWord1 + "%'" + " ");
			}
			if (searchWord3 != null && !searchWord3.equals("")) {
				sb.append("AND DR.DNAME LIKE " + "'%" + searchWord1 + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<VIEW_MARK> dataList = jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<VIEW_MARK>() {

						@Override
						public VIEW_MARK mapRow(ResultSet rs, int rowNum) throws SQLException {
							VIEW_MARK vMark = new VIEW_MARK();
							vMark.setDID(rs.getInt("DID"));
							vMark.setDORMNAME(rs.getString("DORMNAME"));
							vMark.setMANAGERNAME(rs.getString("MANAGERNAME"));
							vMark.setMARKNAME(rs.getString("MARKNAME"));
							vMark.setMDESC(rs.getString("MDESC"));
							vMark.setMID(rs.getInt("MID"));
							vMark.setMMARK_MONTH(rs.getString("MMARK_MONTH"));
							vMark.setMMARK_WEEKTS(rs.getString("MMARK_WEEKTS"));
							vMark.setMMARKDATE(rs.getDate("MMARKDATE") == null ? "" : rs.getDate("MMARKDATE").toString());
							vMark.setMSORCEVALUE(rs.getDouble("MSORCEVALUE"));
							vMark.setMTYPE(rs.getInt("MTYPE"));
							vMark.setRDESC(rs.getString("RDESC"));
							vMark.setRID(rs.getInt("RID"));
							vMark.setRNO(rs.getString("RNO"));
							return vMark;
						}
					});
			return dataList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean findWeekTSMarksExsit(Integer roomID, String WeekTS, String markMonth) {
		String sql = "SELECT ID FROM DMRMARK WHERE 1=1 AND ROOMID=? AND MMARK_WEEKTS=? AND MMARK_MONTH=?";
		List<Integer> IDs = this.jdbcTemplate.query(sql, new Object[] { roomID, WeekTS, markMonth }, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowCount) throws SQLException {
				return rs.getInt("ID");
			}
		});
		if (IDs != null && IDs.size() == 4) {
			return true;
		}
		return false;
	}
}
