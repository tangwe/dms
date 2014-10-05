package com.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dms.dao.BaseDAO;
import com.dms.dao.DormDAO;
import com.dms.pojo.Dorm;

@Repository("dormDAO")
public class DormDAOImpl extends BaseDAO implements DormDAO {

	@Override
	public Dorm addDorm(Dorm d) throws DataAccessException {
		try {
			String sql = "INSERT INTO DMDORM(DNAME,STATE,DDESC) VALUES(?,?,?)";
			this.jdbcTemplate.update(sql, new Object[] { d.getdName(), d.getState(), d.getdDesc() });
			return d;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Dorm delDorm(Integer dormID) {
		try {
			String sql = "DELETE FROM DMDORM WHERE ID=?";
			this.jdbcTemplate.update(sql, new Object[] { dormID });
			return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Dorm updateDorm(Dorm d) {
		try {
			String sql = "UPDATE DMDORM SET DNAME=?,STATE=?,DDESC=? WHERE ID=?";
			this.jdbcTemplate.update(sql, new Object[] { d.getdName(), d.getState(), d.getdDesc(), d.getId() });
			return d;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Dorm findDormByID(Integer dormID) {
		Dorm dorm;
		try {
			String sql = "SELECT ID,DNAME,STATE,DDESC FROM DMDORM WHERE ID=?";
			dorm = this.jdbcTemplate.queryForObject(sql, new Object[] { dormID }, new RowMapper<Dorm>() {

				@Override
				public Dorm mapRow(ResultSet rs, int rowNum) throws SQLException {
					Dorm _dorm = new Dorm();
					_dorm.setdDesc(rs.getString("DDESC"));
					_dorm.setdName(rs.getString("DNAME"));
					_dorm.setId(rs.getInt("ID"));
					_dorm.setState(rs.getInt("STATE"));
					return _dorm;
				}

			});
			return dorm;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dorm> findAllDorms(int pageNow, int pageSize) {
		try {
			String sql = "SELECT ID,DNAME,STATE,DDESC FROM DMDORM LIMIT ?,?";
			List<Dorm> dorms = this.jdbcTemplate.query(sql, new Object[] { (pageNow - 1) * pageSize, pageSize }, new RowMapper<Dorm>() {

				@Override
				public Dorm mapRow(ResultSet rs, int rowNum) throws SQLException {
					Dorm _dorm = new Dorm();
					_dorm.setdDesc(rs.getString("DDESC"));
					_dorm.setdName(rs.getString("DNAME"));
					_dorm.setId(rs.getInt("ID"));
					_dorm.setState(rs.getInt("STATE"));
					return _dorm;
				}

			});
			return dorms;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dorm> findAllDormsInUse() {
		try {
			List<Dorm> dorms = this.jdbcTemplate.query("SELECT ID,DNAME,STATE,DDESC FROM DMDORM WHERE STATE=1", new RowMapper<Dorm>() {

				@Override
				public Dorm mapRow(ResultSet rs, int rowNum) throws SQLException {
					Dorm _dorm = new Dorm();
					_dorm.setdDesc(rs.getString("DDESC"));
					_dorm.setdName(rs.getString("DNAME"));
					_dorm.setId(rs.getInt("ID"));
					_dorm.setState(rs.getInt("STATE"));
					return _dorm;
				}

			});
			return dorms;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getDormCount() {
		return this.jdbcTemplate.queryForLong("SELECT COUNT(*) FROM DMDORM");
	}

}
