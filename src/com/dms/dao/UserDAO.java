package com.dms.dao;

import java.util.List;

import com.dms.pojo.Manager;
import com.dms.pojo.Student;
import com.dms.view.VIEW_MAN;
import com.dms.view.VIEW_STU;

/**
 * 用户DAO接口 学生接口和管理员接口 对应表 DMSTUDENT DMMANAGER
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午1:23:08
 * 
 * @version 1.0
 */
public interface UserDAO {
	/**
	 * 添加一条DMSTUDENT记录
	 * 
	 * @param stu
	 *            学生信息
	 * @return 添加信息
	 */
	public Student addStudent(Student stu);

	/**
	 * 添加一条DMMANAGER记录
	 * 
	 * @param man
	 *            管理员信息
	 * @return 添加信息
	 */
	public Manager addManager(Manager man);

	/**
	 * 删除一条DMSTUDENT记录
	 * 
	 * @param stuID
	 *            学生序列号
	 * @return 删除信息
	 */
	public Student delStudent(Integer stuID);

	/**
	 * 删除一条DMMANAGER记录
	 * 
	 * @param manID
	 *            管理员序列号
	 * @return 删除信息
	 */
	public Manager delManager(Integer manID);

	/**
	 * 更新一条DMSTUDENT记录
	 * 
	 * @param stu
	 *            学生信息
	 * @return 更新信息
	 */
	public Student updateStudent(Student stu);

	/**
	 * 更新一条DMSTUDENT SPWD记录
	 * 
	 * @param stuID
	 * @param newPwd
	 * @return
	 */
	public Student updateStudentPwd(Integer stuID, String newPwd);

	/**
	 * 更新一条DMMANAGER记录
	 * 
	 * @param man
	 *            管理员信息
	 * @return 更新信息
	 */
	public Manager updateManager(Manager man);

	/**
	 * 更新一条DMMANAGER MPWD记录
	 * 
	 * @param manID
	 * @param newPwd
	 * @return
	 */
	public Manager updateManagerPwd(Integer manID, String newPwd);

	/**
	 * 查询指定ID的DMSTUDENT记录
	 * 
	 * @param stuID
	 * @return Student
	 */
	public Student findStuByID(Integer stuID);

	/**
	 * 查询指定ID的DMMANAGER记录
	 * 
	 * @param manID
	 * @return Manager
	 */
	public Manager findManByID(Integer manID);

	/**
	 * 查询指定账号和密码的DMSTUDENT记录
	 * 
	 * @param sNo
	 * @param sPwd
	 * @return
	 */
	public Student findStuByAccountAndPwd(String sNo, String sPwd);

	/**
	 * 查询指定账号和密码的DMMANAGER记录
	 * 
	 * @param mNo
	 * @param mPwd
	 * @return
	 */
	public Manager findManByAccountAndPwd(String mNo, String mPwd);

	/**
	 * 分页查询DMSTUDENT记录
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord1
	 * @param searchWord2
	 * @return
	 */
	public List<Student> findPagenationStus(int pageNow, int pageSize, String searchWord1, String searchWord2);

	/**
	 * 分页查询DMMANAGER记录
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @return
	 */
	public List<Manager> findPagenationMans(int pageNow, int pageSize, String searchWord);

	/**
	 * 查询DMSTUDENT记录数
	 * 
	 * @return 总记录数
	 */
	public long getStuRowCount(String searchWord1, String searchWord2);

	/**
	 * 查询DMMANAGER记录数
	 * 
	 * @return 总记录数
	 */
	public long getManRowCount(String searchWord);

	/**
	 * 查询学生视图记录
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord1
	 * @param searchWord2
	 * @return
	 */
	public List<VIEW_STU> findPagenationStuView(int pageNow, int pageSize, String searchWord1, String searchWord2);

	/**
	 * 查询管理员视图记录
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @return
	 */
	public List<VIEW_MAN> findPaginationManView(int pageNow, int pageSize, String searchWord);
}
