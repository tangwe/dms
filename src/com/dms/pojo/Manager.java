package com.dms.pojo;

/**
 * 宿舍管理员实体类
 * 
 * @author SemF
 * 
 * @create 2014年8月30日 下午11:12:03
 * 
 * @version 1.0
 */
public class Manager {
	/**
	 * 管理员序列号
	 */
	private Integer id;
	/**
	 * 管理员账号
	 */
	private String mNo;
	/**
	 * 管理员姓名
	 */
	private String mName;
	/**
	 * 管理员密码
	 */
	private String mPwd;
	/**
	 * 类型 管理员默认2
	 */
	private int type;

	/**
	 * 无参构造方法（ 无参数实例化）
	 */
	public Manager() {
		super();
	}

	/**
	 * 有参构造方法（有参数实例化）
	 * 
	 * @param id
	 * @param mNo
	 * @param mName
	 * @param mPwd
	 * @param type
	 */
	public Manager(Integer id, String mNo, String mName, String mPwd, int type) {
		super();
		this.id = id;
		this.mNo = mNo;
		this.mName = mName;
		this.mPwd = mPwd;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getmNo() {
		return mNo;
	}

	public void setmNo(String mNo) {
		this.mNo = mNo;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPwd() {
		return mPwd;
	}

	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
