/**
 * 
 */
package com.atanu.java.springboot.model;

import java.io.Serializable;

/**
 * @author ATANU
 *
 */
public class PreferredAncillaryRequest implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8841272737218591031L;

	private String originAirportCode;
	private String destAirportCode;
	private int ancillaryCount;
	private int ancillaryId;
	private String ancillaryName;
	private String ancillaryDesc;

	public String getOriginAirportCode() {
		return originAirportCode;
	}

	public void setOriginAirportCode(String originAirportCode) {
		this.originAirportCode = originAirportCode;
	}

	public String getDestAirportCode() {
		return destAirportCode;
	}

	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}
	
	public int getAncillaryCount() {
		return ancillaryCount;
	}

	public void setAncillaryCount(int ancillaryCount) {
		this.ancillaryCount = ancillaryCount;
	}
	
	public int getAncillaryId() {
		return ancillaryId;
	}

	public void setAncillaryId(int ancillaryId) {
		this.ancillaryId = ancillaryId;
	}

	public String getAncillaryName() {
		return ancillaryName;
	}

	public void setAncillaryName(String ancillaryName) {
		this.ancillaryName = ancillaryName;
	}

	public String getAncillaryDesc() {
		return ancillaryDesc;
	}

	public void setAncillaryDesc(String ancillaryDesc) {
		this.ancillaryDesc = ancillaryDesc;
	}

	@Override
	public String toString() {
		return "PreferredAncillaryRequest"
				+ " ["
				+ " originAirportCode : "+ originAirportCode
				+ " destAirportCode : " + destAirportCode
				+ " ]";
	}
}
