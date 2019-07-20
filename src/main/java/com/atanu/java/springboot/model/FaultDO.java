/**
 * 
 */
package com.atanu.java.springboot.model;

import java.io.Serializable;

/**
 * @author ATANU
 *
 */
public class FaultDO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6346004910126077137L;
	
	private String status;
	private ErrorDO error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorDO getError() {
		return error;
	}

	public void setError(ErrorDO error) {
		this.error = error;
	}

}
