/**
 * 
 */
package com.cr.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ballab
 *
 */
@Controller
@RequestMapping("/")
public class AbstractController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHome(){
		System.out.println("Controller here");
		return "home";
	}
}
