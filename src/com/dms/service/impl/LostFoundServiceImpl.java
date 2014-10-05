package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.dms.common.PaginationHelper;
import com.dms.constant.CommonConstant;
import com.dms.dao.LostFoundDAO;
import com.dms.pojo.LostFound;
import com.dms.service.LostFoundService;
import com.dms.view.VIEW_LFSTU;

/**
 * 寻失Service实现
 * 
 * @author SemF
 * 
 * @create 2014年9月2日 下午1:00:21
 * 
 * @version 1.0
 */
@Service("lostFoundService")
public class LostFoundServiceImpl implements LostFoundService {
	@Autowired
	private LostFoundDAO lostFoundDAO;

	@Override
	public LostFound addLostOrFound(LostFound lf) throws Exception {
		try {
			LostFound lostFound = lostFoundDAO.addLostFound(lf);
			return lostFound;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public PaginationHelper getPaginationLFViews(int pageSize, int pageNow, String searchWord) throws Exception {
		try {
			// 查询数据
			List<VIEW_LFSTU> dataList = lostFoundDAO.findPaginationLFViews(CommonConstant.PAGE_SIZE, pageNow, searchWord);
			// 获取记录数
			long rowCount = lostFoundDAO.getLFRowCount(searchWord, null);
			PaginationHelper ph = new PaginationHelper(dataList, rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public PaginationHelper getPaginationLFByStuID(int pageNow, String searchWord, Integer stuID) throws Exception {
		try {
			// 查询数据
			List<LostFound> dataList = lostFoundDAO.findLFByStuID(CommonConstant.PAGE_SIZE, pageNow, searchWord, stuID);
			// 获取记录数
			long rowCount = lostFoundDAO.getLFRowCount(searchWord, stuID);
			PaginationHelper ph = new PaginationHelper(dataList, rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

}
