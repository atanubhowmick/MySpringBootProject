/**
 * 
 */
package com.atanu.java.springboot.exception;

/**
 * @author ATANU
 *
 */
public class DataSvcException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5677865972051310907L;

	private String errorCode;
	private String errorMsg;

	public DataSvcException(Throwable e) {
		super(e);
	}

	public DataSvcException(String errorCode, String errorMsg, Throwable e) {
		super(errorMsg, e);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public DataSvcException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public String getMessage() {
		if (null != this.errorMsg) {
			return this.errorMsg;
		} else {
			return super.getMessage();
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
