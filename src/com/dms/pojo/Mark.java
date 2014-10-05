package com.dms.pojo;

import java.util.Date;

import com.dms.common.CommonUtil;

/**
 * 评分实体
 * 
 * @author SemF
 * 
 * @create 2014年8月30日 下午11:30:06
 * 
 * @version 1.0
 */
public class Mark {
	/**
	 * 评分序列号
	 */
	private Integer id;
	/**
	 * 评分项名称 如地面 床 阳台
	 */
	private String mName;
	/**
	 * 评分项标识 如：0地面1床2桌子3阳台
	 */
	private int mType;
	/**
	 * 评分分值
	 */
	private double mSorceValue;
	/**
	 * 评分时间
	 */
	private Date mMarkDate;
	/**
	 * 评分描述
	 */
	private String mDesc;
	/**
	 * 寝室关联序列
	 */
	private Integer roomID;
	/**
	 * 评分月
	 */
	private String mMarkMonth;
	/**
	 * 评分周
	 */
	private String mMarkWeekTS;
	/**
	 * 管理员关联序列
	 */
	private Integer managerID;

	/**
	 * 无参数实例化
	 */
	public Mark() {
		super();
	}

	/**
	 * 有参数实例化
	 * 
	 * @param id
	 * @param mName
	 * @param mType
	 * @param mSorceValue
	 * @param mMarkDate
	 * @param mDesc
	 * @param roomID
	 * @param mMarkMonth
	 * @param mMarkWeekTS
	 * @param managerID
	 */
	public Mark(Integer id, String mName, int mType, double mSorceValue, Date mMarkDate, String mDesc, Integer roomID, String mMarkMonth,
			String mMarkWeekTS, Integer managerID) {
		super();
		this.id = id;
		this.mName = mName;
		this.mType = mType;
		this.mSorceValue = mSorceValue;
		this.mMarkDate = mMarkDate;
		this.mDesc = mDesc;
		this.roomID = roomID;
		this.mMarkMonth = mMarkMonth;
		this.mMarkWeekTS = mMarkWeekTS;
		this.managerID = managerID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public double getmSorceValue() {
		return mSorceValue;
	}

	public void setmSorceValue(double mSorceValue) {
		this.mSorceValue = mSorceValue;
	}

	public Date getmMarkDate() {
		return mMarkDate;
	}

	public String getmMarkDate_ToS() {
		return CommonUtil.formatDate(mMarkDate);
	}

	public void setmMarkDate(Date mMarkDate) {
		this.mMarkDate = mMarkDate;
	}

	public String getmDesc() {
		return mDesc;
	}

	public void setmDesc(String mDesc) {
		this.mDesc = mDesc;
	}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	public String getmMarkMonth() {
		return mMarkMonth;
	}

	public void setmMarkMonth(String mMarkMonth) {
		this.mMarkMonth = mMarkMonth;
	}

	public String getmMarkWeekTS() {
		return mMarkWeekTS;
	}

	public void setmMarkWeekTS(String mMarkWeekTS) {
		this.mMarkWeekTS = mMarkWeekTS;
	}

	public Integer getManagerID() {
		return managerID;
	}

	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

}
