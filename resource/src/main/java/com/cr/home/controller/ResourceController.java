package com.cr.home.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResourceController {

	@Autowired
	protected ServletContext servletContext;
	
/*	@RequestMapping(value="/getHome", method=RequestMethod.GET)
	public String getHome(){
		
		System.out.println("Controller here");
		return "home";
	}*/
	
	@RequestMapping(value = "/partials/**", method = RequestMethod.GET)
	public ModelAndView getPartial(Model m, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView((String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		return modelAndView;
	}
	
}
