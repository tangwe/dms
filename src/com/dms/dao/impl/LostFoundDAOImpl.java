package com.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dms.dao.BaseDAO;
import com.dms.dao.LostFoundDAO;
import com.dms.pojo.LostFound;
import com.dms.view.VIEW_LFSTU;

/**
 * @author SemF
 * 
 * @create 2014年9月2日 上午10:50:14
 * 
 * @version 1.0
 */
@Repository("lostFoundDAO")
public class LostFoundDAOImpl extends BaseDAO implements LostFoundDAO {

	@Override
	public LostFound addLostFound(LostFound lf) throws DataAccessException {
		try {
			String sql = "INSERT INTO DMLOSFOU(LFTAG,LFCONTENT,LFDATE,LFTYPE,STATE,STUID) VALUES(?,?,?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { lf.getLfTag(), lf.getLfContent(), lf.getLfDate(), lf.getLfType(), lf.getState(), lf.getStuID() });
			return lf;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public LostFound delLostFound(Integer lfID) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LostFound updateLFState(Integer lfID) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LostFound updateLostFound(LostFound lf) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LostFound findLFByID(Integer lfID) {
		try {
			String sql = "SELECT ID,LFTAG,LFCONTENT,LFDATE,LFTYPE,STATE,STUID FROM DMLOSFOU WHERE ID=?";
			LostFound ls = jdbcTemplate.queryForObject(sql, new Object[] { lfID }, new RowMapper<LostFound>() {

				@Override
				public LostFound mapRow(ResultSet rs, int rowNum) throws SQLException {
					LostFound lostFound = new LostFound();
					lostFound.setId(rs.getInt("ID"));
					lostFound.setLfContent(rs.getString("LFCONTENT"));
					lostFound.setLfDate(rs.getDate("LFDATE"));
					lostFound.setLfTag(rs.getString("LFTAG"));
					lostFound.setLfType(rs.getInt("LFTYPE"));
					lostFound.setState(rs.getInt("STATE"));
					lostFound.setStuID(rs.getInt("STUID"));
					return lostFound;
				}
			});
			return ls;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LostFound> findLFByStuID(int pageSize, int pageNow, String searchWord, Integer stuID) {
		try {
			String sql = "SELECT ID,LFTAG,LFCONTENT,LFDATE,LFTYPE,STATE,STUID FROM DMLOSFOU WHERE 1=1 AND STUID=" + stuID + " ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord != null && !searchWord.equals("")) {
				sb.append("AND LFTAG LIKE " + "'%" + searchWord + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			System.out.println("sql:===" + sb.toString());
			List<LostFound> lfs = this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<LostFound>() {

						@Override
						public LostFound mapRow(ResultSet rs, int rowNum) throws SQLException {
							LostFound lf = new LostFound();
							lf.setId(rs.getInt("ID"));
							lf.setLfContent(rs.getString("LFCONTENT"));
							lf.setLfDate(rs.getDate("LFDATE"));
							lf.setLfTag(rs.getString("LFTAG"));
							lf.setLfType(rs.getInt("LFTYPE"));
							lf.setState(rs.getInt("STATE"));
							lf.setStuID(rs.getInt("STUID"));
							return lf;
						}
					});
			return lfs;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LostFound> findPaginationLFs(int pageSize, int pageNow, String searchWord) {
		try {
			String sql = "SELECT ID,LFTAG,LFCONTENT,LFDATE,LFTYPE,STATE,STUID FROM DMLOSFOU WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord != null && !searchWord.equals("")) {
				sb.append("AND LFTAG LIKE " + "'%" + searchWord + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<LostFound> lfs = this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<LostFound>() {

						@Override
						public LostFound mapRow(ResultSet rs, int rowNum) throws SQLException {
							LostFound lf = new LostFound();
							lf.setId(rs.getInt("ID"));
							lf.setLfContent(rs.getString("LFCONTENT"));
							lf.setLfDate(rs.getDate("LFDATE"));
							lf.setLfTag(rs.getString("LFTAG"));
							lf.setLfType(rs.getInt("LFTYPE"));
							lf.setState(rs.getInt("STATE"));
							lf.setStuID(rs.getInt("STUID"));
							return lf;
						}
					});
			return lfs;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getLFRowCount(String searchWord, Integer stuID) {
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM DMLOSFOU WHERE 1=1 ");
		if (searchWord != null && !searchWord.equals("")) {
			sb.append("AND LFTAG LIKE " + "'%" + searchWord + "%'" + " ");
		}
		if (stuID != null && !stuID.equals("")) {
			sb.append("AND STUID=" + stuID);
		}
		try {
			return this.jdbcTemplate.queryForLong(sb.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public VIEW_LFSTU findLFViewByID(Integer lfID) {
		try {
			String sql = "SELECT DLF.ID AS ID,DLF.LFTAG AS LFTAG,DLF.LFCONTENT AS LFCONTENT,DLF.LFDATE AS LFDATE,DLF.LFTYPE AS LFTYPE,DLF.STATE AS STATE,"
					+ "DSTU.SNO AS SNO,DSTU.SNAME AS SNAME,DSTU.SFROM AS SFROM,DSTU.STEL AS STEL "
					+ "FROM (DMLOSFOU DLF JOIN DMSTUDENT DSTU ON((DSTU.ID = DLF.STUID))) WHERE DLF.ID=?";
			VIEW_LFSTU view = jdbcTemplate.queryForObject(sql, new Object[] { lfID }, new RowMapper<VIEW_LFSTU>() {

				@Override
				public VIEW_LFSTU mapRow(ResultSet rs, int rowNum) throws SQLException {
					VIEW_LFSTU _view = new VIEW_LFSTU();
					_view.setID(rs.getInt("ID"));
					_view.setLFCONTENT(rs.getString("LFCONTENT"));
					_view.setLFDATE(rs.getDate("LFDATE"));
					_view.setLFTAG(rs.getString("LFTAG"));
					_view.setLFTYPE(rs.getInt("LFTYPE"));
					_view.setSFROM(rs.getString("SFROM"));
					_view.setSNAME(rs.getString("SNAME"));
					_view.setSNO(rs.getString("SNO"));
					_view.setSTATE(rs.getInt("STATE"));
					_view.setSTEL(rs.getString("STEL"));
					return _view;
				}
			});
			return view;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VIEW_LFSTU> findPaginationLFViews(int pageSize, int pageNow, String searchWord) {
		try {
			String sql = "SELECT DLF.ID AS ID,DLF.LFTAG AS LFTAG,DLF.LFCONTENT AS LFCONTENT,DLF.LFDATE AS LFDATE,DLF.LFTYPE AS LFTYPE,DLF.STATE AS STATE,"
					+ "DSTU.SNO AS SNO,DSTU.SNAME AS SNAME,DSTU.SFROM AS SFROM,DSTU.STEL AS STEL "
					+ "FROM (DMLOSFOU DLF JOIN DMSTUDENT DSTU ON((DSTU.ID = DLF.STUID))) WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord != null && searchWord != "") {
				sb.append("AND LFTAG LIKE " + "'%" + searchWord + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<VIEW_LFSTU> viewList = this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<VIEW_LFSTU>() {

						@Override
						public VIEW_LFSTU mapRow(ResultSet rs, int rowNum) throws SQLException {
							VIEW_LFSTU _view = new VIEW_LFSTU();
							_view.setID(rs.getInt("ID"));
							_view.setLFCONTENT(rs.getString("LFCONTENT"));
							_view.setLFDATE(rs.getDate("LFDATE"));
							_view.setLFTAG(rs.getString("LFTAG"));
							_view.setLFTYPE(rs.getInt("LFTYPE"));
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
	public List<VIEW_LFSTU> findPaginationLFViews(int pageSize, int pageNow, String searchWord, Integer stuID) {
		// TODO Auto-generated method stub
		return null;
	}

}
