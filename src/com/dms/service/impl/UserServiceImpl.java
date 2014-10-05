package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.common.PaginationHelper;
import com.dms.constant.CommonConstant;
import com.dms.dao.UserDAO;
import com.dms.pojo.Manager;
import com.dms.pojo.Student;
import com.dms.pojo.User;
import com.dms.service.UserService;
import com.dms.view.VIEW_MAN;
import com.dms.view.VIEW_STU;

/**
 * 用户Service实现
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午5:01:28
 * 
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public User login(String account, String pwd, int type) {
		User user = null;
		// type=0执行管理员登陆,type=1执行学生登陆
		if (CommonConstant.ADMIN == type) {
			Manager man = userDAO.findManByAccountAndPwd(account, pwd);
			if (man != null) {
				// 管理员信息设置到user中
				user = new User();
				user.setId(man.getId());
				user.setUserAccount(man.getmNo());
				user.setUserName(man.getmName());
				user.setUserPwd(man.getmPwd());
				user.setUserType(man.getType());
			}
		} else if (CommonConstant.STUDENT == type) {
			Student stu = userDAO.findStuByAccountAndPwd(account, pwd);
			if (stu != null) {
				// 学生信息设置到user中
				user = new User();
				user.setId(stu.getId());
				user.setUserAccount(stu.getsNo());
				user.setUserName(stu.getsName());
				user.setUserPwd(stu.getsPwd());
				user.setUserType(stu.getType());
				user.setRoomID(stu.getRoomID());
			}
		}
		return user;
	}

	@Override
	public PaginationHelper getStuView(int pageNow, int pageSize, String searchWord1, String searchWord2) throws Exception {
		try {
			PaginationHelper ph = new PaginationHelper();
			ph.setPageSize(pageSize);// 自定义pageSize
			List<VIEW_STU> dataList = userDAO.findPagenationStuView(pageNow, pageSize, searchWord1, searchWord2);
			long rowCount = userDAO.getStuRowCount(searchWord1, searchWord2);
			ph.setDataList(dataList);
			ph.setRowCount(rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public PaginationHelper getManView(int pageNow, int pageSize, String searchWord) throws Exception {
		try {
			PaginationHelper ph = new PaginationHelper();
			ph.setPageSize(pageSize);// 自定义pageSize
			List<VIEW_MAN> dataList = userDAO.findPaginationManView(pageNow, pageSize, searchWord);
			long rowCount = userDAO.getManRowCount(searchWord);
			ph.setDataList(dataList);
			ph.setRowCount(rowCount);
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public User updatePwd(User user, String oldPwd, String newPwd) {
		if (!oldPwd.equals(user.getUserPwd())) {
			return null;
		}
		User u = null;
		if (CommonConstant.ADMIN == user.getUserType()) {
			Manager man = userDAO.updateManagerPwd(user.getId(), newPwd);
			if (man != null) {
				u = new User();
				u.setId(man.getId());
				u.setUserAccount(man.getmNo());
				u.setUserName(man.getmName());
				u.setUserPwd(man.getmPwd());
				u.setUserType(man.getType());
			}
		} else if (CommonConstant.STUDENT == user.getUserType()) {
			Student stu = userDAO.updateStudentPwd(user.getId(), newPwd);
			if (stu != null) {
				u = new User();
				u.setId(stu.getId());
				u.setUserAccount(stu.getsNo());
				u.setUserName(stu.getsName());
				u.setUserPwd(stu.getsPwd());
				u.setUserType(stu.getType());
				u.setRoomID(stu.getRoomID());
			}
		}
		return u;
	}

}
