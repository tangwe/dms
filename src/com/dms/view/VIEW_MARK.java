package com.dms.view;

/**
 * 评分视图 关联表 ：评分表 寝室表 宿舍表 管理员表
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午10:59:32
 * 
 * @version 1.0
 */
public class VIEW_MARK {
	private String MARKNAME;
	private int MTYPE;
	private double MSORCEVALUE;
	private String MMARKDATE;
	private String MDESC;
	private String MMARK_MONTH;
	private String MMARK_WEEKTS;
	private String RNO;
	private String RDESC;
	private Integer RID;
	private Integer MID;
	private Integer DID;
	private String DORMNAME;
	private String MANAGERNAME;

	public String getMARKNAME() {
		return MARKNAME;
	}

	public void setMARKNAME(String mARKNAME) {
		MARKNAME = mARKNAME;
	}

	public int getMTYPE() {
		return MTYPE;
	}

	public void setMTYPE(int mTYPE) {
		MTYPE = mTYPE;
	}

	public double getMSORCEVALUE() {
		return MSORCEVALUE;
	}

	public void setMSORCEVALUE(double mSORCEVALUE) {
		MSORCEVALUE = mSORCEVALUE;
	}

	public String getMMARKDATE() {
		return MMARKDATE;
	}

	public void setMMARKDATE(String mMARKDATE) {
		MMARKDATE = mMARKDATE;
	}

	public String getMDESC() {
		return MDESC;
	}

	public void setMDESC(String mDESC) {
		MDESC = mDESC;
	}

	public String getMMARK_MONTH() {
		return MMARK_MONTH;
	}

	public void setMMARK_MONTH(String mMARK_MONTH) {
		MMARK_MONTH = mMARK_MONTH;
	}

	public String getMMARK_WEEKTS() {
		return MMARK_WEEKTS;
	}

	public void setMMARK_WEEKTS(String mMARK_WEEKTS) {
		MMARK_WEEKTS = mMARK_WEEKTS;
	}

	public String getRNO() {
		return RNO;
	}

	public void setRNO(String rNO) {
		RNO = rNO;
	}

	public String getRDESC() {
		return RDESC;
	}

	public void setRDESC(String rDESC) {
		RDESC = rDESC;
	}

	public Integer getRID() {
		return RID;
	}

	public void setRID(Integer rID) {
		RID = rID;
	}

	public Integer getMID() {
		return MID;
	}

	public void setMID(Integer mID) {
		MID = mID;
	}

	public Integer getDID() {
		return DID;
	}

	public void setDID(Integer dID) {
		DID = dID;
	}

	public String getDORMNAME() {
		return DORMNAME;
	}

	public void setDORMNAME(String dORMNAME) {
		DORMNAME = dORMNAME;
	}

	public String getMANAGERNAME() {
		return MANAGERNAME;
	}

	public void setMANAGERNAME(String mANAGERNAME) {
		MANAGERNAME = mANAGERNAME;
	}

}
