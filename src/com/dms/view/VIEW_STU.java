package com.dms.view;

/**
 * 学生视图 关联表 学生表，寝室表，宿舍表
 * 
 * @author SemF
 * 
 * @create 2014年9月5日 下午3:52:06
 * 
 * @version 1.0
 */
public class VIEW_STU {
	private Integer ID;
	private String SNO;
	private String SNAME;
	private String SFROM;
	private String STEL;
	private String RNO;
	private String DNAME;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getSNO() {
		return SNO;
	}

	public void setSNO(String sNO) {
		SNO = sNO;
	}

	public String getSNAME() {
		return SNAME;
	}

	public void setSNAME(String sNAME) {
		SNAME = sNAME;
	}

	public String getSFROM() {
		return SFROM;
	}

	public void setSFROM(String sFROM) {
		SFROM = sFROM;
	}

	public String getSTEL() {
		return STEL;
	}

	public void setSTEL(String sTEL) {
		STEL = sTEL;
	}

	public String getRNO() {
		return RNO;
	}

	public void setRNO(String rNO) {
		RNO = rNO;
	}

	public String getDNAME() {
		return DNAME;
	}

	public void setDNAME(String dNAME) {
		DNAME = dNAME;
	}

}
