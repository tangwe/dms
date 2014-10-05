package com.dms.pojo;

/**
 * 寝室实体
 * 
 * @author SemF
 * 
 * @create 2014年8月30日 下午11:23:26
 * 
 * @version 1.0
 */
public class Room {
	/**
	 * 寝室序列号
	 */
	private Integer id;
	/**
	 * 寝室序房间号
	 */
	private String rNo;
	/**
	 * 寝室序状态0不可用1可用2使用中
	 */
	private int state;
	/**
	 * 寝室序描述
	 */
	private String rDesc;
	/**
	 * 宿舍（苑）关联序列
	 */
	private Integer dormID;

	/**
	 * 无参数实例化
	 */
	public Room() {
		super();
	}

	/**
	 * 有参数实例化
	 * 
	 * @param id
	 * @param rNo
	 * @param state
	 * @param rDesc
	 * @param dormID
	 */
	public Room(Integer id, String rNo, int state, String rDesc, Integer dormID) {
		super();
		this.id = id;
		this.rNo = rNo;
		this.state = state;
		this.rDesc = rDesc;
		this.dormID = dormID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getrNo() {
		return rNo;
	}

	public void setrNo(String rNo) {
		this.rNo = rNo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getrDesc() {
		return rDesc;
	}

	public void setrDesc(String rDesc) {
		this.rDesc = rDesc;
	}

	public Integer getDormID() {
		return dormID;
	}

	public void setDormID(Integer dormID) {
		this.dormID = dormID;
	}

}
