package com.cr.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cr.home.beans.UserAccount;
import com.cr.home.business.interfaces.IHomeService;


@Configuration
@ComponentScan("com.cr.home")
@RestController
@RequestMapping("/home")
public class HomeRestEndPoint {

	@Autowired
	private IHomeService homeService;
	
	String message;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@RequestBody UserAccount user){
		/*if(user.getName()!=null){
			return "Success";
		}else{
		
			return "Failed";
		}*/
		message=homeService.saveDetails(user);
		return message;
	}
}
