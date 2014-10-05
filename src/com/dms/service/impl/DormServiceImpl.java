package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.dms.common.PaginationHelper;
import com.dms.dao.DormDAO;
import com.dms.pojo.Dorm;
import com.dms.service.DormService;

/**
 * 宿舍Service实现
 * 
 * @author SemF
 * 
 * @create 2014年9月4日 下午4:19:07
 * 
 * @version 1.0
 */
@Service("dormService")
public class DormServiceImpl implements DormService {
	@Autowired
	private DormDAO dormDAO;

	@Override
	public List<Dorm> getDormsInUse() {
		try {
			return dormDAO.findAllDormsInUse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaginationHelper getDorms() throws Exception {
		try {
			PaginationHelper ph = new PaginationHelper();
			ph.setPageSize(100);
			List<Dorm> dataList = dormDAO.findAllDorms(1, 100);
			ph.setDataList(dataList);
			long rowCount = dormDAO.getDormCount();
			ph.setRowCount(rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Dorm addDorm(String dName, String dDesc, int state) {
		try {
			Dorm d = new Dorm(null, dName, dDesc, state);
			Dorm dorm = dormDAO.addDorm(d);
			return dorm;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void batchDelDorm(String ids) throws Exception {
		try {
			String[] idsArr = ids.split(",");
			for (String id : idsArr) {
				dormDAO.delDorm(Integer.valueOf(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Dorm updateDorm(String dName, String dDesc, int state, Integer dormID) throws Exception {
		try {
			Dorm d = new Dorm(dormID, dName, dDesc, state);
			Dorm dorm = dormDAO.updateDorm(d);
			return dorm;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
}
