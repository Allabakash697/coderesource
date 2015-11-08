package com.cr.home.service.temproray;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cr.home.beans.Numbers;
import com.cr.home.beans.temproray.SerialNumber;
import com.cr.home.business.temproray.SerialNumberServiceImpl;


@Configuration
@RestController
@RequestMapping("/numb")
public class SerialNumberRestEndPoint {
	
	@Autowired
	private SerialNumberServiceImpl service;

	/*@RequestMapping(value="/getNext", method=RequestMethod.GET)
	public String getNext(@RequestBody String number,@RequestParam(value = "numb", required = false) String serialNumber){
		String nextNumber=service.getNext(serialNumber);
		return nextNumber;
	}*/
	
	@RequestMapping(value="/getNumbersList",method=RequestMethod.POST)
	public List<Numbers> getNumbersList(@RequestBody SerialNumber numbers){
		List<Numbers> nextNumbers=service.getNumbersList(numbers);
		return nextNumbers;
	}
	
	/*for temporary use. for checking phone number*/
	@RequestMapping(value="/getNext", method=RequestMethod.GET)
	public void getNext(@RequestParam String serialNumber){
		serialNumber=serialNumber.replaceAll(" ", "");
		String regex = "^[0-9+()-]*$";
		System.out.printf("serialNumber",serialNumber);
		/*serialNumber=serialNumber.replaceAll("-", "");*/
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(serialNumber);
	    if (matcher.matches()) {
		System.out.println("valid");
	    }else{
	    	System.out.println("Invalid");
	    }
	}
	
	
}
