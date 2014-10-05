package com.dms.pojo;

/**
 * 用户
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 上午10:12:54
 * 
 * @version 1.0
 */
public class User {
	private Integer id;
	/**
	 * 用户账号
	 */
	private String userAccount;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPwd;
	/**
	 * 用户类型
	 */
	private int userType;
	/**
	 * 寝室ID学生有，管理员无
	 */
	private Integer roomID;

	/**
	 * 无参数实例化
	 */
	public User() {
		super();
	}

	/**
	 * 有参数实例化
	 * 
	 * @param id
	 * @param userAccount
	 * @param userName
	 * @param userPwd
	 * @param userType
	 */
	public User(Integer id, String userAccount, String userName, String userPwd, int userType) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

}
