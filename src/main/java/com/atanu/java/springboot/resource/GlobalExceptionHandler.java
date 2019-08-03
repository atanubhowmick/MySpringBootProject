/**
 * 
 */
package com.atanu.java.springboot.resource;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atanu.java.springboot.constant.Constants;
import com.atanu.java.springboot.exception.DataSvcException;
import com.atanu.java.springboot.logger.ApplicationLogger;
import com.atanu.java.springboot.model.FaultDO;
import com.atanu.java.springboot.utils.CommonUtils;

/**
 * @author Atanu Bhowmick
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final ApplicationLogger logger = new ApplicationLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(DataSvcException.class)
	public ResponseEntity<FaultDO> handleDataSvcException(DataSvcException ex) {
		logger.error("Handling DataSvcException... ", ex);
		FaultDO fault = CommonUtils.createFaultDOForError(ex.getErrorCode(), ex.getErrorMsg());
		return new ResponseEntity<>(fault, ex.getHttpStatus());
	}

	@ExceptionHandler(HibernateException.class)
	public ResponseEntity<FaultDO> handleHibernateExceptionException(HibernateException ex) {
		logger.error("Handling HibernateException... ", ex);
		FaultDO fault = CommonUtils.createFaultDOForError(Constants.ERROR_CODE_2001, ex.getMessage());
		return new ResponseEntity<>(fault, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<FaultDO> handleJDBCConnectionException(TransactionException ex) {
		logger.error("Handling TransactionException... ", ex);
		FaultDO fault = CommonUtils.createFaultDOForError(Constants.ERROR_CODE_2005, ex.getMessage());
		return new ResponseEntity<>(fault, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<FaultDO> handleGenericException(Exception ex) {
		logger.error("Handling Exception... ", ex);
		FaultDO fault = CommonUtils.createFaultDOForError(Constants.ERROR_CODE_2005, Constants.ERROR_MSG_2005);
		return new ResponseEntity<>(fault, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
