package com.cr.home.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cr.home.Integration.interfaces.IHomeRepository;
import com.cr.home.beans.UserAccount;
import com.cr.home.business.interfaces.IHomeService;

@Component
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private IHomeRepository homeRepository;

	String message;

	public String saveDetails(UserAccount user) {
		message=homeRepository.saveDetails(user);
		return message;
	}

}