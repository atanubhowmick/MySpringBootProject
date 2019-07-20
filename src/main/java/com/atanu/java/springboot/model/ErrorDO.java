/**
 * 
 */
package com.atanu.java.springboot.model;

import java.io.Serializable;

/**
 * @author ATANU
 *
 */
public class ErrorDO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4947858162814897886L;

	private String errorCode;
	private String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
