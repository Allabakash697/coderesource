package com.cr.home.business;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.cr.home.Integration.interfaces.IHomeRepository;
import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;
import com.cr.home.beans.UserDetail;
import com.cr.home.business.interfaces.IHomeService;

@Component
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private IHomeRepository homeRepository;

	ResponseMessage message;

	public ResponseMessage saveDetails(UserAccount user,HttpServletResponse response) {
		message=homeRepository.saveDetails(user,response);
		return message;
	}
	public ResponseMessage getDetails(UserAccount user,HttpServletResponse response) {
		message=homeRepository.getDetails(user,response);
		return message;
	}
	
	public List<UserDetail> getUsers(){
		List<UserDetail> users=homeRepository.getUsers();
		return users;
	}

}
