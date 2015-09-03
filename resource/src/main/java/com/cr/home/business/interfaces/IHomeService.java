package com.cr.home.business.interfaces;

import org.springframework.stereotype.Service;

import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;

@Service
public interface IHomeService {

	ResponseMessage saveDetails(UserAccount user);

}
