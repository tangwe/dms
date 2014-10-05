package com.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dms.dao.BaseDAO;
import com.dms.dao.RoomDAO;
import com.dms.pojo.Room;
import com.dms.view.VIEW_ROOM;

@Repository("roomDAO")
public class RoomDAOImpl extends BaseDAO implements RoomDAO {

	@Override
	public Room addRoom(Room room) {
		try {
			String sql = "INSERT INTO DMROOM(RNO,STATE,RDESC,DORMID) VALUES(?,?,?,?)";
			this.jdbcTemplate.update(sql, new Object[] { room.getrNo(), room.getState(), room.getrDesc(), room.getDormID() });
			return room;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Room delRoom(Integer roomID) {
		try {
			String sql = "DELETE FROM DMROOM WHERE ID=?";
			this.jdbcTemplate.update(sql, new Object[] { roomID });
			return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Room updateRoom(Room room) {
		try {
			String sql = "UPDATE DMROOM SET RNO=?,STATE=?,RDESC=?,DORMID=? WHERE ID=?";
			this.jdbcTemplate.update(sql, new Object[] { room.getrNo(), room.getState(), room.getrDesc(), room.getDormID(), room.getId() });
			return room;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Room findRoomByID(Integer roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> findPaginationRooms(int pageNow, int pageSize, String searchWord, Integer dormID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getRoomRowCount(String searchWord, String dormName) {
		String sql = "SELECT COUNT(*) FROM DMROOM ROO INNER JOIN DMDORM DOR ON ROO.DORMID = DOR.ID WHERE 1=1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (searchWord != null && !searchWord.equals("")) {
			sb.append("AND RNO LIKE " + "'%" + searchWord + "%'" + " ");
		}
		if (dormName != null && !dormName.equals("")) {
			sb.append("AND DNAME LIKE " + "'%" + dormName + "%'" + " ");
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	@Override
	public Room findRoomByRNo(String roomNo, Integer dormID) {
		try {
			Room room = this.jdbcTemplate.queryForObject("SELECT ID,RNO,STATE,RDESC FROM DMROOM WHERE RNO=? AND DORMID=?", new Object[] { roomNo,
					dormID }, new RowMapper<Room>() {

				@Override
				public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
					Room _room = new Room();
					_room.setId(rs.getInt("ID"));
					_room.setrDesc(rs.getString("RDESC"));
					_room.setrNo(rs.getString("RNO"));
					_room.setState(rs.getInt("STATE"));
					return _room;
				}

			});
			return room;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VIEW_ROOM> findPaginationRoomView(int pageNow, int pageSize, String searchWord, String dormName) {
		try {
			String sql = "SELECT ROO.ID,ROO.RNO,ROO.STATE,ROO.RDESC,DOR.DNAME,DOR.ID DID FROM DMROOM ROO INNER JOIN DMDORM DOR ON ROO.DORMID = DOR.ID WHERE 1=1 ";
			StringBuffer sb = new StringBuffer(sql);
			if (searchWord != null && !searchWord.equals("")) {
				sb.append("AND RNO LIKE " + "'%" + searchWord + "%'" + " ");
			}
			if (dormName != null && !dormName.equals("")) {
				sb.append("AND DNAME LIKE " + "'%" + dormName + "%'" + " ");
			}
			sb.append("LIMIT ?,?");
			List<VIEW_ROOM> view = this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize },
					new RowMapper<VIEW_ROOM>() {

						@Override
						public VIEW_ROOM mapRow(ResultSet rs, int rowNum) throws SQLException {
							VIEW_ROOM _view = new VIEW_ROOM();
							_view.setDNAME(rs.getString("DNAME"));
							_view.setID(rs.getInt("ID"));
							_view.setRDESC(rs.getString("RDESC"));
							_view.setRNO(rs.getString("RNO"));
							_view.setSTATE(rs.getInt("STATE"));
							_view.setDID(rs.getInt("DID"));
							return _view;
						}
					});
			return view;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
