package com.dms.pojo;

/**
 * 评分统计
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午11:19:17
 * 
 * @version 1.0
 */
public class SimpleMark {
	private String dormName;
	private String roomNo;
	private String month;
	private String weekTS;
	private double sorceValue1;
	private double sorceValue2;
	private double sorceValue3;
	private double sorceValue4;
	private double sorceCountValue;

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWeekTS() {
		return weekTS;
	}

	public void setWeekTS(String weekTS) {
		this.weekTS = weekTS;
	}

	public double getSorceValue1() {
		return sorceValue1;
	}

	public void setSorceValue1(double sorceValue1) {
		this.sorceValue1 = sorceValue1;
	}

	public double getSorceValue2() {
		return sorceValue2;
	}

	public void setSorceValue2(double sorceValue2) {
		this.sorceValue2 = sorceValue2;
	}

	public double getSorceValue3() {
		return sorceValue3;
	}

	public void setSorceValue3(double sorceValue3) {
		this.sorceValue3 = sorceValue3;
	}

	public double getSorceValue4() {
		return sorceValue4;
	}

	public void setSorceValue4(double sorceValue4) {
		this.sorceValue4 = sorceValue4;
	}

	public double getSorceCountValue() {
		sorceCountValue = sorceValue1 + sorceValue2 + sorceValue3 + sorceValue4;
		return sorceCountValue;
	}

}
