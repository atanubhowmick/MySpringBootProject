/**
 * 
 */
package com.atanu.java.springboot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.java.springboot.bo.AncillaryMgmtBO;
import com.atanu.java.springboot.constant.Constants;
import com.atanu.java.springboot.exception.DataSvcException;
import com.atanu.java.springboot.logger.ApplicationLogger;
import com.atanu.java.springboot.model.AncillaryDetails;
import com.atanu.java.springboot.model.FaultDO;
import com.atanu.java.springboot.model.PreferredAncillaryRequest;
import com.atanu.java.springboot.model.PreferredAncillaryResponse;
import com.atanu.java.springboot.utils.CommonUtils;
import com.atanu.java.springboot.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Atanu Bhowmick
 *
 */

@RestController
@RequestMapping(value = Constants.PATH_ANCILLARY)
@Api(value = "Ancillary Management System in DB")
public class AncillaryDataSvc {

	@Autowired
	private AncillaryMgmtBO ancillaryMgmtBO;

	private static final ApplicationLogger logger = new ApplicationLogger(AncillaryDataSvc.class);

	@ApiOperation(value = "Get ancilarry by Id", response = AncillaryDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the ancillary"),
			@ApiResponse(code = 404, message = Constants.ERROR_MSG_2006, response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_2005, response = FaultDO.class) 
	})
	@RequestMapping(value = "/{ancillaryId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AncillaryDetails> getAncillary(
			@ApiParam(value = "Ancillary Id in path param", required = true) @PathVariable("ancillaryId") Integer ancillaryId)
			throws DataSvcException {
		logger.debug("Inside getAncillary()");
		AncillaryDetails ancillaryDetails = ancillaryMgmtBO.getAncillaryDetailsById(ancillaryId);
		return new ResponseEntity<>(ancillaryDetails, HttpStatus.OK);
	}

	@ApiOperation(value = "Get ancilarry by Id", response = AncillaryDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the ancillary"),
			@ApiResponse(code = 404, message = Constants.ERROR_MSG_2006, response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_2005, response = FaultDO.class) })
	@RequestMapping(value = Constants.PATH_GET_ANCILLARY, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AncillaryDetails> getAncillaryById(
			@ApiParam(value = "Ancillary Id in request param", required = true) 
			@RequestParam(required = true) int ancillaryId) throws DataSvcException {
		logger.debug("Inside getAncillaryById()");
		AncillaryDetails ancillaryDetails = ancillaryMgmtBO.getAncillaryDetailsById(ancillaryId);
		return new ResponseEntity<>(ancillaryDetails, HttpStatus.OK);
	}

	@ApiOperation(value = "Get all ancilarries", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the ancillary"),
			@ApiResponse(code = 404, message = Constants.ERROR_MSG_2006, response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_2005, response = FaultDO.class) 
	})
	@RequestMapping(value = Constants.PATH_GET_ALL_ANCILLARY, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AncillaryDetails>> getAllAncillaries() {
		logger.debug("Inside getAllAncillaries()");
		List<AncillaryDetails> ancillaries = ancillaryMgmtBO.getAllAncillary();
		return new ResponseEntity<>(ancillaries, HttpStatus.OK);
	}

	@ApiOperation(value = "Get list of the avaliable ancilarries between two airports", response = PreferredAncillaryResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved ancillary list"),
			@ApiResponse(code = 404, message = Constants.ERROR_MSG_2006, response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_2005, response = FaultDO.class) 
	})
	@RequestMapping(value = Constants.PATH_GET_ANCILLARY_BY_AIRPORTS, method = RequestMethod.GET, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PreferredAncillaryResponse> getAllAncillaryByAirports(
			@ApiParam(value = "Send origin and destination airport code in the request", required = true) @RequestBody PreferredAncillaryRequest ancillaryRequest) {

		logger.debug("Inside getAllAncillary()");
		PreferredAncillaryResponse response = new PreferredAncillaryResponse();
		if (null == ancillaryRequest || StringUtils.isEmpty(ancillaryRequest.getOriginAirportCode())
				|| StringUtils.isEmpty(ancillaryRequest.getDestAirportCode())) {

			logger.debug("Invalid request. Origin/Destination airport can't be empty... {}", ancillaryRequest);

			if (ancillaryRequest != null && ancillaryRequest.getOriginAirportCode() != null) {
				response.setOriginAirportCode(ancillaryRequest.getOriginAirportCode());
			}
			if (ancillaryRequest != null && ancillaryRequest.getDestAirportCode() != null) {
				response.setDestAirportCode(ancillaryRequest.getDestAirportCode());
			}
			response.setFault(CommonUtils.createFaultDOForError(Constants.ERROR_CODE_2001, Constants.ERROR_MSG_2001));
		} else {
			try {
				response = ancillaryMgmtBO.getPreferredAncillariesByAirports(ancillaryRequest);
				if (null == response) {
					logger.debug("Airport details are not available in database: {}", ancillaryRequest);
					response = new PreferredAncillaryResponse();
					response.setOriginAirportCode(ancillaryRequest.getOriginAirportCode());
					response.setDestAirportCode(ancillaryRequest.getDestAirportCode());
					response.setFault(
							CommonUtils.createFaultDOForError(Constants.ERROR_CODE_2002, Constants.ERROR_MSG_2002));
				} else if (response.getPreferredAncillaries().isEmpty()) {
					logger.debug("No data found for the given airport: {}", ancillaryRequest);
					response.setOriginAirportCode(ancillaryRequest.getOriginAirportCode());
					response.setDestAirportCode(ancillaryRequest.getDestAirportCode());
					response.setFault(
							CommonUtils.createFaultDOForError(Constants.ERROR_CODE_2003, Constants.ERROR_MSG_2003));
				} else {
					logger.debug("Data processing successful.");
					response.setFault(CommonUtils.createFaultDOForSuccess());
				}
			} catch (Exception e) {
				response = new PreferredAncillaryResponse();
				response.setOriginAirportCode(ancillaryRequest.getOriginAirportCode());
				response.setDestAirportCode(ancillaryRequest.getDestAirportCode());
				response.setFault(
						CommonUtils.createFaultDOForError(Constants.ERROR_CODE_2004, Constants.ERROR_MSG_2004));
				logger.error("Exception occurred in getAllAncillaryByAirports: ", e);
			}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Save Ancilarry", response = AncillaryDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved the ancillary", response = AncillaryDetails.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_2005, response = FaultDO.class) 
	})
	@RequestMapping(value = Constants.PATH_SAVE_ANCILLARY, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AncillaryDetails> saveAncillary(
			@ApiParam(value = "Ancillary Details in RequestBody", required = true) 
			@RequestBody(required = true) AncillaryDetails ancillaryDetails) throws DataSvcException {
		logger.debug("Inside getAncillary()");
		ancillaryDetails = ancillaryMgmtBO.saveAncillaryDetails(ancillaryDetails);
		return new ResponseEntity<>(ancillaryDetails, HttpStatus.OK);
	}

	@ApiOperation(value = "Update Ancilarry", response = AncillaryDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved the ancillary", response = AncillaryDetails.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_2005, response = FaultDO.class) 
	})
	@RequestMapping(value = Constants.PATH_UPDATE_ANCILLARY, method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AncillaryDetails> updateAncillary(
			@ApiParam(value = "Ancillary Details in RequestBody", required = true) 
			@RequestBody(required = true) AncillaryDetails ancillaryDetails) {
		logger.debug("Inside getAncillary()");
		ancillaryMgmtBO.updateAncillaryDetails(ancillaryDetails);
		return new ResponseEntity<>(ancillaryDetails, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Ancilarry by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted the ancillary"),
			@ApiResponse(code = 404, message = Constants.ERROR_MSG_2006, response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_2005, response = FaultDO.class) 
	})
	@RequestMapping(value = Constants.PATH_DELETE_ANCILLARY
			+ "/{ancillaryId}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteAncillary(
			@ApiParam(value = "Ancillary Id in path param", required = true) @PathVariable("ancillaryId") int ancillaryId) throws DataSvcException {
		logger.debug("Inside getAncillary()");
		ancillaryMgmtBO.deleteAncillaryDetails(ancillaryId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
