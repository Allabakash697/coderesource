package com.cr.home.Integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cr.home.Integration.interfaces.IHomeDao;
import com.cr.home.Integration.interfaces.IHomeRepository;
import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;

@Component
public class HomeRepositoryImpl implements IHomeRepository{

	@Autowired
	private IHomeDao homeDao;

	ResponseMessage message;

	public ResponseMessage saveDetails(UserAccount user) {

		message=homeDao.saveDetails(user);
		return message;
	}

	public ResponseMessage getDetails(UserAccount user) {

		message=homeDao.getDetails(user);
		return message;
	}
}
