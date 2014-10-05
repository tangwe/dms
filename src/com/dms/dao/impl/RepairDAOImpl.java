package com.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dms.dao.BaseDAO;
import com.dms.dao.RepairDAO;
import com.dms.pojo.Repair;
import com.dms.view.VIEW_RE;

@Repository("repairDAO")
public class RepairDAOImpl extends BaseDAO implements RepairDAO {

	@Override
	public Repair addRepair(Repair re) throws DataAccessException {
		try {
			String sql = "INSERT INTO DMREPAIR(DMREPAIR.`RENAME`,RECONTENT,RESDATE,ROOMID,STUID,STATE,MANID) VALUES(?,?,?,?,?,?,?)";
			this.jdbcTemplate.update(sql,
					new Object[] { re.getReName(), re.getReContent(), re.getReStuDate(), re.getRoomID(), re.getStuID(), re.getState(), 1 });
			return re;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Repair updateReState(Integer reID, int state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repair updateRe(Integer reID, String mReply, Date mDate, Integer manID, int state) {
		try {
			String sql = "UPDATE DMREPAIR SET REMREPLY=?,REMDATE=?,MANID=?,STATE=? WHERE ID=?";
			this.jdbcTemplate.update(sql, new Object[] { mReply, mDate, manID, state, reID });
			return this.findReByID(reID);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Repair> findPaginationRes(int pageNow, int pageSize, String searchWord, Integer state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repair findReByID(Integer reID) {
		String sql = "SELECT ID,DMREPAIR.`RENAME`,RECONTENT,RESDATE,ROOMID,STUID,REMREPLY,REMDATE,MANID,STATE FROM DMREPAIR WHERE ID=" + reID;
		Repair re = this.jdbcTemplate.queryForObject(sql, new RowMapper<Repair>() {

			@Override
			public Repair mapRow(ResultSet rs, int rowNum) throws SQLException {
				Repair _re = new Repair();
				_re.setId(rs.getInt("ID"));
				_re.setManID(rs.getInt("MANID"));
				_re.setReContent(rs.getString("RECONTENT"));
				_re.setReManDate(rs.getDate("REMDATE"));
				_re.setReName(rs.getString("RENAME"));
				_re.setReReply(rs.getString("REMREPLY"));
				_re.setReStuDate(rs.getDate("RESDATE"));
				_re.setRoomID(rs.getInt("ROOMID"));
				_re.setState(rs.getInt("STATE"));
				_re.setStuID(rs.getInt("STUID"));
				return _re;
			}

		});
		return re;
	}

	@Override
	public long getReRowCount(String searchWord, Integer stuID) {
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM DMREPAIR RE WHERE STUID=" + stuID + " ");
		if (searchWord != null && !searchWord.equals("")) {
			sb.append("WHERE RE.RENAME LIKE " + "'%" + searchWord + "%'");
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	@Override
	public List<VIEW_RE> findPaginationReView(int pageNow, int pageSize, String searchWord, Integer state, Integer stuID) {
		try {
			String sql = "SELECT RE.ID AS ID,RE.RENAME AS DRENAME,RE.RECONTENT AS RECONTENT,RE.RESDATE AS RESDATE,RE.REMREPLY AS REMREPLY,RE.REMDATE AS REMDATE,RE.STATE AS STATE,"
					+ "RO.RNO AS RNO,RO.RDESC AS RDESC,DOR.DNAME AS DNAME,DOR.DDESC AS DDESC,"
					+ "MAN.MNO AS MNO,MAN.MNAME AS MNAME,STU.SNO AS SNO,STU.SNAME AS SNAME,STU.SFROM AS SFROM,STU.STEL AS STEL "
					+ "FROM ((((DMREPAIR RE JOIN DMROOM RO ON((RO.ID = RE.ROOMID))) "
					+ "JOIN DMSTUDENT STU ON((STU.ID = RE.STUID))) "
					+ "JOIN DMMANAGER MAN ON(( MAN.ID = RE.MANID))) "
					+ "JOIN DMDORM DOR ON((DOR.ID = RO.DORMID))) WHERE 1=1 "
					+ "AND RE.STATE<>"
					+ state + " AND STUID=" + stuID + " ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord != null && !searchWord.equals("")) {
				sb.append("AND RE.RENAME LIKE " + "'%" + searchWord + "%'" + " ");
			}
			sb.append("ORDER BY RE.RESDATE DESC ");
			sb.append("LIMIT ?,?");
			List<VIEW_RE> viewList = this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<VIEW_RE>() {

						@Override
						public VIEW_RE mapRow(ResultSet rs, int rowNum) throws SQLException {
							VIEW_RE _view = new VIEW_RE();
							_view.setDDESC(rs.getString("DDESC"));
							_view.setDNAME(rs.getString("DNAME"));
							_view.setDRENAME(rs.getString("DRENAME"));
							_view.setID(rs.getInt("ID"));
							_view.setMNAME(rs.getString("MNAME"));
							_view.setMNO(rs.getString("MNO"));
							_view.setRDESC(rs.getString("RDESC"));
							_view.setRECONTENT(rs.getString("RECONTENT"));
							_view.setREMDATE(rs.getDate("REMDATE"));
							_view.setREMREPLY(rs.getString("REMREPLY"));
							_view.setRESDATE(rs.getDate("RESDATE"));
							_view.setRNO(rs.getString("RNO"));
							_view.setSFROM(rs.getString("SFROM"));
							_view.setSNAME(rs.getString("SNAME"));
							_view.setSNO(rs.getString("SNO"));
							_view.setSTATE(rs.getInt("STATE"));
							_view.setSTEL(rs.getString("STEL"));
							return _view;
						}
					});
			return viewList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VIEW_RE> findPaginationRepairView(int pageNow, int pageSize, String searchWord, String state) {
		try {
			String sql = "SELECT RE.ID AS ID,RE.RENAME AS DRENAME,RE.RECONTENT AS RECONTENT,RE.RESDATE AS RESDATE,RE.REMREPLY AS REMREPLY,RE.REMDATE AS REMDATE,RE.STATE AS STATE,"
					+ "RO.RNO AS RNO,RO.RDESC AS RDESC,DOR.DNAME AS DNAME,DOR.DDESC AS DDESC,"
					+ "MAN.MNO AS MNO,MAN.MNAME AS MNAME,STU.SNO AS SNO,STU.SNAME AS SNAME,STU.SFROM AS SFROM,STU.STEL AS STEL "
					+ "FROM ((((DMREPAIR RE JOIN DMROOM RO ON((RO.ID = RE.ROOMID))) "
					+ "JOIN DMSTUDENT STU ON((STU.ID = RE.STUID))) "
					+ "JOIN DMMANAGER MAN ON(( MAN.ID = RE.MANID))) " + "JOIN DMDORM DOR ON((DOR.ID = RO.DORMID))) WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (state != null && !state.equals("")) {
				sb.append("AND RE.STATE in(" + state + ")" + " ");
			}
			if (searchWord != null && !searchWord.equals("")) {
				sb.append("AND RO.RNO LIKE " + "'%" + searchWord + "%'" + " ");
			}
			sb.append("ORDER BY RE.RESDATE DESC ");
			sb.append("LIMIT ?,?");
			List<VIEW_RE> viewList = this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<VIEW_RE>() {

						@Override
						public VIEW_RE mapRow(ResultSet rs, int rowNum) throws SQLException {
							VIEW_RE _view = new VIEW_RE();
							_view.setDDESC(rs.getString("DDESC"));
							_view.setDNAME(rs.getString("DNAME"));
							_view.setDRENAME(rs.getString("DRENAME"));
							_view.setID(rs.getInt("ID"));
							_view.setMNAME(rs.getString("MNAME"));
							_view.setMNO(rs.getString("MNO"));
							_view.setRDESC(rs.getString("RDESC"));
							_view.setRECONTENT(rs.getString("RECONTENT"));
							_view.setREMDATE(rs.getDate("REMDATE"));
							_view.setREMREPLY(rs.getString("REMREPLY"));
							_view.setRESDATE(rs.getDate("RESDATE"));
							_view.setRNO(rs.getString("RNO"));
							_view.setSFROM(rs.getString("SFROM"));
							_view.setSNAME(rs.getString("SNAME"));
							_view.setSNO(rs.getString("SNO"));
							_view.setSTATE(rs.getInt("STATE"));
							_view.setSTEL(rs.getString("STEL"));
							return _view;
						}
					});
			return viewList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getRepairRowCount(String searchWord, String state) {
		String sql = "SELECT COUNT(*) FROM ((((DMREPAIR RE JOIN DMROOM RO ON((RO.ID = RE.ROOMID))) JOIN DMSTUDENT STU ON((STU.ID = RE.STUID))) JOIN DMMANAGER MAN ON(( MAN.ID = RE.MANID))) "
				+ "JOIN DMDORM DOR ON((DOR.ID = RO.DORMID))) WHERE 1=1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (state != null && !state.equals("")) {
			sb.append("AND RE.STATE in(" + state + ")" + " ");
		}
		if (searchWord != null && !searchWord.equals("")) {
			sb.append("AND RO.RNO LIKE " + "'%" + searchWord + "%'" + " ");
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	@Override
	public List<VIEW_RE> findPaginationRepairView(int pageNow, int pageSize, String searchWord1, String searchWord2, String state) {
		try {
			String sql = "SELECT RE.ID AS ID,RE.RENAME AS DRENAME,RE.RECONTENT AS RECONTENT,RE.RESDATE AS RESDATE,RE.REMREPLY AS REMREPLY,RE.REMDATE AS REMDATE,RE.STATE AS STATE,"
					+ "RO.RNO AS RNO,RO.RDESC AS RDESC,DOR.DNAME AS DNAME,DOR.DDESC AS DDESC,"
					+ "MAN.MNO AS MNO,MAN.MNAME AS MNAME,STU.SNO AS SNO,STU.SNAME AS SNAME,STU.SFROM AS SFROM,STU.STEL AS STEL "
					+ "FROM ((((DMREPAIR RE JOIN DMROOM RO ON((RO.ID = RE.ROOMID))) "
					+ "JOIN DMSTUDENT STU ON((STU.ID = RE.STUID))) "
					+ "JOIN DMMANAGER MAN ON(( MAN.ID = RE.MANID))) " + "JOIN DMDORM DOR ON((DOR.ID = RO.DORMID))) WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (state != null && !state.equals("")) {
				sb.append("AND RE.STATE in(" + state + ")" + " ");
			}
			if (searchWord1 != null && !searchWord1.equals("")) {
				sb.append("AND RO.RNO LIKE " + "'%" + searchWord1 + "%'" + " ");
			}
			if (searchWord2 != null && !searchWord2.equals("")) {
				sb.append("AND DOR.DNAME LIKE " + "'%" + searchWord2 + "%'" + " ");
			}
			sb.append("ORDER BY RE.RESDATE DESC ");
			sb.append("LIMIT ?,?");
			List<VIEW_RE> viewList = this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<VIEW_RE>() {

						@Override
						public VIEW_RE mapRow(ResultSet rs, int rowNum) throws SQLException {
							VIEW_RE _view = new VIEW_RE();
							_view.setDDESC(rs.getString("DDESC"));
							_view.setDNAME(rs.getString("DNAME"));
							_view.setDRENAME(rs.getString("DRENAME"));
							_view.setID(rs.getInt("ID"));
							_view.setMNAME(rs.getString("MNAME"));
							_view.setMNO(rs.getString("MNO"));
							_view.setRDESC(rs.getString("RDESC"));
							_view.setRECONTENT(rs.getString("RECONTENT"));
							_view.setREMDATE(rs.getDate("REMDATE"));
							_view.setREMREPLY(rs.getString("REMREPLY"));
							_view.setRESDATE(rs.getDate("RESDATE"));
							_view.setRNO(rs.getString("RNO"));
							_view.setSFROM(rs.getString("SFROM"));
							_view.setSNAME(rs.getString("SNAME"));
							_view.setSNO(rs.getString("SNO"));
							_view.setSTATE(rs.getInt("STATE"));
							_view.setSTEL(rs.getString("STEL"));
							return _view;
						}
					});
			return viewList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getRepairRowCount(String searchWord1, String searchWord2, String state) {
		String sql = "SELECT COUNT(*) FROM ((((DMREPAIR RE JOIN DMROOM RO ON((RO.ID = RE.ROOMID))) JOIN DMSTUDENT STU ON((STU.ID = RE.STUID))) JOIN DMMANAGER MAN ON(( MAN.ID = RE.MANID))) "
				+ "JOIN DMDORM DOR ON((DOR.ID = RO.DORMID))) WHERE 1=1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (state != null && !state.equals("")) {
			sb.append("AND RE.STATE in(" + state + ")" + " ");
		}
		if (searchWord1 != null && !searchWord1.equals("")) {
			sb.append("AND RO.RNO LIKE " + "'%" + searchWord1 + "%'" + " ");
		}
		if (searchWord2 != null && !searchWord2.equals("")) {
			sb.append("AND DOR.DNAME LIKE " + "'%" + searchWord2 + "%'" + " ");
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	@Override
	public Repair delRe(Integer reID) {
		try {
			String sql = "DELETE FROM DMREPAIR WHERE ID=?";
			this.jdbcTemplate.update(sql, new Object[] { reID });
			return this.findReByID(reID);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
