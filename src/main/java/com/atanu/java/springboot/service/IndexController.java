/**
 * 
 */
package com.atanu.java.springboot.service;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Atanu Bhowmick
 *
 */

@RestController
public class IndexController implements ErrorController {
	private static final String PATH = "/error";

	@Override
	public String getErrorPath() {
		return PATH;
	}

	@RequestMapping(value = PATH)
	public String getError() {
		return "Error page has been taken care! Please look into IndexController for details.";
	}
}
