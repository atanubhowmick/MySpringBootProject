/**
 * 
 */
package com.atanu.java.springboot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ATANU
 *
 */
@ApiModel(value = "PreferredAncillaryRequest", 
		description = "All details about Preferred Ancillary Request ")
public class PreferredAncillaryRequest implements Serializable {

	private static final long serialVersionUID = 8841272737218591031L;

	@ApiModelProperty(value = "Origin Airport Code", example = "ATL")
	private String originAirportCode;
	
	@ApiModelProperty(value = "Origin Airport Code", example = "MSP")
	private String destAirportCode;
	
	@ApiModelProperty(value = "Expected Ancillaries count present in the Response", example = "5")
	private int ancillaryCount;
	
	@ApiModelProperty(value = "Ancillary Id", example = "101")
	private Integer ancillaryId;
	
	@ApiModelProperty(value = "Ancillary Name", example = "Wifi")
	private String ancillaryName;
	
	@ApiModelProperty(value = "Ancillary Description", example = "WiFi available here")
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
	
	public Integer getAncillaryId() {
		return ancillaryId;
	}

	public void setAncillaryId(Integer ancillaryId) {
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
