package com.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dms.dao.BaseDAO;
import com.dms.dao.UserDAO;
import com.dms.pojo.Manager;
import com.dms.pojo.Student;
import com.dms.view.VIEW_MAN;
import com.dms.view.VIEW_STU;

@Repository("userDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {

	@Override
	public Student addStudent(Student stu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager addManager(Manager man) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student delStudent(Integer stuID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager delManager(Integer manID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudent(Student stu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager updateManager(Manager man) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findStuByID(Integer stuID) {
		try {
			String sql = "SELECT ID,SNO,SNAME,SPWD,SFROM,STEL,ROOMID,TYPE FROM DMSTUDENT WHERE ID=?";
			Student stu = jdbcTemplate.queryForObject(sql, new Object[] { stuID }, new RowMapper<Student>() {

				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student s = new Student();
					s.setId(rs.getInt("ID"));
					s.setsNo(rs.getString("SNO"));
					s.setsName(rs.getString("SNAME"));
					s.setsPwd(rs.getString("SPWD"));
					s.setsFrom(rs.getString("SFROM"));
					s.setsTel(rs.getString("STEL"));
					s.setRoomID(rs.getInt("ROOMID"));
					s.setType(rs.getInt("TYPE"));
					return s;
				}

			});
			return stu;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Manager findManByID(Integer manID) {
		try {
			String sql = "SELECT ID,MNO,MNAME,MPWD,DORMID,TYPE FROM DMMANAGER WHERE ID=?";
			Manager man = jdbcTemplate.queryForObject(sql, new Object[] { manID }, new RowMapper<Manager>() {

				@Override
				public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
					Manager m = new Manager();
					m.setId(rs.getInt("ID"));
					m.setmNo(rs.getString("MNO"));
					m.setmName(rs.getString("MNAME"));
					m.setmPwd(rs.getString("MPWD"));
					m.setType(rs.getInt("TYPE"));
					return m;
				}
			});
			return man;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student findStuByAccountAndPwd(String sNo, String sPwd) {
		try {
			String sql = "SELECT ID,SNO,SNAME,SPWD,SFROM,STEL,ROOMID,TYPE FROM DMSTUDENT WHERE SNO=? AND SPWD=?";
			Student stu = jdbcTemplate.queryForObject(sql, new Object[] { sNo, sPwd }, new RowMapper<Student>() {

				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student s = new Student();
					s.setId(rs.getInt("ID"));
					s.setsNo(rs.getString("SNO"));
					s.setsName(rs.getString("SNAME"));
					s.setsPwd(rs.getString("SPWD"));
					s.setsFrom(rs.getString("SFROM"));
					s.setsTel(rs.getString("STEL"));
					s.setRoomID(rs.getInt("ROOMID"));
					s.setType(rs.getInt("TYPE"));
					return s;
				}

			});
			return stu;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Manager findManByAccountAndPwd(String mNo, String mPwd) {
		try {
			String sql = "SELECT ID,MNO,MNAME,MPWD,TYPE FROM DMMANAGER WHERE MNO=? AND MPWD=?";
			Manager man = jdbcTemplate.queryForObject(sql, new Object[] { mNo, mPwd }, new RowMapper<Manager>() {

				@Override
				public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
					Manager m = new Manager();
					m.setId(rs.getInt("ID"));
					m.setmNo(rs.getString("MNO"));
					m.setmName(rs.getString("MNAME"));
					m.setmPwd(rs.getString("MPWD"));
					m.setType(rs.getInt("TYPE"));
					return m;
				}
			});
			return man;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> findPagenationStus(int pageNow, int pageSize, String searchWord1, String searchWord2) {
		try {
			String sql = "SELECT ID,SNO,SNAME,SFROM,STEL,ROOMID,TYPE FROM DMSTUDENT WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord1 != null && !searchWord1.equals("")) {
				sb.append("AND SNO LIKE " + "'%" + searchWord1 + "%'" + " ");
			}
			if (searchWord2 != null && !searchWord2.equals("")) {
				sb.append("AND SNAME LIKE " + "'%" + searchWord2 + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<Student> stuList = jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize }, new RowMapper<Student>() {

				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student s = new Student();
					s.setId(rs.getInt("ID"));
					s.setsNo(rs.getString("SNO"));
					s.setsName(rs.getString("SNAME"));
					s.setsFrom(rs.getString("SFROM"));
					s.setsTel(rs.getString("STEL"));
					s.setType(rs.getInt("TYPE"));
					return s;
				}

			});
			return stuList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Manager> findPagenationMans(int pageNow, int pageSize, String searchWord) {
		try {
			String sql = "SELECT ID,MNO,MNAME,TYPE FROM DMMANAGER WHERE ID<>1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord != null && !searchWord.equals("")) {
				sb.append("AND MNAME LIKE " + "'%" + searchWord + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<Manager> manList = jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize }, new RowMapper<Manager>() {

				@Override
				public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
					Manager man = new Manager();
					man.setId(rs.getInt("ID"));
					man.setmName(rs.getString("MNAME"));
					man.setmNo(rs.getString("MNO"));
					man.setType(rs.getInt("TYPE"));
					return man;
				}

			});
			return manList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getStuRowCount(String searchWord1, String searchWord2) {
		String sql = "SELECT COUNT(*) FROM DMSTUDENT WHERE 1=1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (searchWord1 != null && !searchWord1.equals("")) {
			sb.append("AND SNO LIKE " + "'%" + searchWord1 + "%'" + " ");
		}
		if (searchWord2 != null && !searchWord2.equals("")) {
			sb.append("AND SNAME LIKE " + "'%" + searchWord2 + "%'" + " ");
		}
		return this.jdbcTemplate.queryForLong(sql);
	}

	@Override
	public long getManRowCount(String searchWord) {
		String sql = "SELECT COUNT(*) FROM DMMANAGER WHERE ID<>1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (searchWord != null && !searchWord.equals("")) {
			sb.append("AND MNAME LIKE " + "'%" + searchWord + "%'" + " ");
		}
		return this.jdbcTemplate.queryForLong(sql);
	}

	@Override
	public List<VIEW_STU> findPagenationStuView(int pageNow, int pageSize, String searchWord1, String searchWord2) {
		try {
			String sql = "SELECT STU.ID,STU.SNO,STU.SNAME,STU.SFROM,STU.STEL,ROO.RNO,DOR.DNAME FROM DMSTUDENT STU "
					+ "INNER JOIN DMROOM ROO ON ROO.ID = STU.ROOMID INNER JOIN DMDORM DOR ON DOR.ID = ROO.DORMID WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord1 != null && !searchWord1.equals("")) {
				sb.append("AND SNO LIKE " + "'%" + searchWord1 + "%'" + " ");
			}
			if (searchWord2 != null && !searchWord2.equals("")) {
				sb.append("AND SNAME LIKE " + "'%" + searchWord2 + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<VIEW_STU> view = jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize }, new RowMapper<VIEW_STU>() {

				@Override
				public VIEW_STU mapRow(ResultSet rs, int rowNum) throws SQLException {
					VIEW_STU _view = new VIEW_STU();
					_view.setDNAME(rs.getString("DNAME"));
					_view.setID(rs.getInt("ID"));
					_view.setRNO(rs.getString("RNO"));
					_view.setSFROM(rs.getString("SFROM"));
					_view.setSNAME(rs.getString("SNAME"));
					_view.setSNO(rs.getString("SNO"));
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
	public List<VIEW_MAN> findPaginationManView(int pageNow, int pageSize, String searchWord) {
		try {
			String sql = "SELECT MAN.ID,MAN.MNO,MAN.MNAME,DOR.DNAME FROM DMMANAGER MAN INNER JOIN DMDORM DOR ON DOR.ID = MAN.DORMID WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord != null && !searchWord.equals("")) {
				sb.append("AND MNAME LIKE " + "'%" + searchWord + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<VIEW_MAN> view = jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize }, new RowMapper<VIEW_MAN>() {

				@Override
				public VIEW_MAN mapRow(ResultSet rs, int rowNum) throws SQLException {
					VIEW_MAN _view = new VIEW_MAN();
					_view.setDNAME(rs.getString("DNAME"));
					_view.setID(rs.getInt("ID"));
					_view.setMNAME(rs.getString("MNAME"));
					_view.setMNO(rs.getString("MNO"));
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
	public Student updateStudentPwd(Integer stuID, String newPwd) {
		try {
			String sql = "UPDATE DMSTUDENT SET SPWD=? WHERE ID=?";
			this.jdbcTemplate.update(sql, newPwd, stuID);
			return this.findStuByID(stuID);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Manager updateManagerPwd(Integer manID, String newPwd) {
		try {
			String sql = "UPDATE DMMANAGER SET MPWD=? WHERE ID=?";
			this.jdbcTemplate.update(sql, newPwd, manID);
			return this.findManByID(manID);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
