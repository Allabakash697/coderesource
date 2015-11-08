package com.cr.home.Integration;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.cr.home.Integration.interfaces.IHomeDao;
import com.cr.home.Integration.interfaces.IHomeRepository;
import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;
import com.cr.home.beans.UserDetail;

@Component
public class HomeRepositoryImpl implements IHomeRepository{

	@Autowired
	private IHomeDao homeDao;

	ResponseMessage message;

	public ResponseMessage saveDetails(UserAccount user,HttpServletResponse response) {

		message=homeDao.saveDetails(user,response);
		return message;
	}

	public ResponseMessage getDetails(UserAccount user,HttpServletResponse response) {

		message=homeDao.getDetails(user,response);
		return message;
	}
	
	public List<UserDetail> getUsers(){
		List<UserDetail> users=homeDao.getUsers();
		return users;
	}
}
