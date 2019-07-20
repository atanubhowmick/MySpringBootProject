/**
 * 
 */
package com.atanu.java.springboot.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.java.springboot.model.Address;
import com.atanu.java.springboot.model.Employee;

/**
 * @author Jax
 *
 */

@RestController
@RequestMapping(value = "/employee")
public class EmployeeService {
	@RequestMapping(value = "/{empId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("empId") String empId) {
		Log log = LogFactory.getLog(getClass());
		Employee employee = new Employee(empId, "Atanu Bhowmick", 30, null,
				new Address("A1/2", "H Street", "Hooghly", "WB"));
		log.debug("Inside getEmployee... empId = " + empId);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
}
