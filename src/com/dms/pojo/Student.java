package com.dms.pojo;

/**
 * 学生实体类 学生基本信息
 * 
 * @author SemF
 * 
 * @create 2014年8月30日 下午10:57:01
 * 
 * @version 1.0
 */
public class Student {
	/**
	 * 序列号
	 */
	private Integer id;
	/**
	 * 学号
	 */
	private String sNo;
	/**
	 * 姓名
	 */
	private String sName;
	/**
	 * 密码
	 */
	private String sPwd;
	/**
	 * 籍贯
	 */
	private String sFrom;
	/**
	 * 联系电话
	 */
	private String sTel;
	/**
	 * 寝室序列号（与寝室实体关联）
	 */
	private Integer roomID;
	/**
	 * 类型 （默认1标识学生）
	 */
	private int type;

	/**
	 * 无参构造方法(用于无参数实例化)
	 */
	public Student() {
		super();
	}

	/**
	 * 有参构造方法(用于有参数实例化)
	 * 
	 * @param id
	 * @param sNo
	 * @param sName
	 * @param sPwd
	 * @param sFrom
	 * @param sTel
	 * @param roomID
	 * @param type
	 */
	public Student(Integer id, String sNo, String sName, String sPwd, String sFrom, String sTel, Integer roomID, int type) {
		super();
		this.id = id;
		this.sNo = sNo;
		this.sName = sName;
		this.sPwd = sPwd;
		this.sFrom = sFrom;
		this.sTel = sTel;
		this.roomID = roomID;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPwd() {
		return sPwd;
	}

	public void setsPwd(String sPwd) {
		this.sPwd = sPwd;
	}

	public String getsFrom() {
		return sFrom;
	}

	public void setsFrom(String sFrom) {
		this.sFrom = sFrom;
	}

	public String getsTel() {
		return sTel;
	}

	public void setsTel(String sTel) {
		this.sTel = sTel;
	}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
