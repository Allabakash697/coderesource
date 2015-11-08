package com.cr.home.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cr.home.beans.Constants;
import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;
import com.cr.home.beans.UserDetail;
import com.cr.home.beans.temproray.URLReader;
import com.cr.home.business.interfaces.IHomeService;


@Configuration
@ComponentScan("com.cr.home")
@RestController
@SessionAttributes("emailId")
@RequestMapping("/home")
public class HomeRestEndPoint {

	@Autowired
	private IHomeService homeService;
	
	ResponseMessage message;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseMessage register(@RequestBody UserAccount user,HttpServletResponse response){
		/*if(user.getName()!=null){
			return "Success";
		}else{
		
			return "Failed";
		}*/
		message=homeService.saveDetails(user,response);
		return message;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseMessage login(@RequestBody UserAccount user,HttpServletResponse response){
		message=homeService.getDetails(user,response);
		/*request.getSession(true);*/
		return message;
	}
	
	@RequestMapping(value="/getUserDetails", method=RequestMethod.POST)
	public List<UserDetail> getUsers(){
		List<UserDetail> users=homeService.getUsers();
		return users;
	}
	
	
	// Testing purpose ***********************************
	@RequestMapping(value="/getHtml", method=RequestMethod.GET)
	public String getHtml(){
		URLReader urlReader=new URLReader();
		String url=urlReader.getHtml();
		return url;
	}
}
