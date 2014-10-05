package com.dms.view;

/**
 * 寝室视图 关联表 寝室表，宿舍表
 * 
 * @author SemF
 * 
 * @create 2014年9月6日 下午6:34:41
 * 
 * @version 1.0
 */
public class VIEW_ROOM {
	private Integer ID;
	private String RNO;
	private int STATE;
	private String RDESC;
	private Integer DID;
	private String DNAME;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getRNO() {
		return RNO;
	}

	public void setRNO(String rNO) {
		RNO = rNO;
	}

	public int getSTATE() {
		return STATE;
	}

	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}

	public String getRDESC() {
		return RDESC;
	}

	public void setRDESC(String rDESC) {
		RDESC = rDESC;
	}

	public String getDNAME() {
		return DNAME;
	}

	public void setDNAME(String dNAME) {
		DNAME = dNAME;
	}

	public Integer getDID() {
		return DID;
	}

	public void setDID(Integer dID) {
		DID = dID;
	}

}
