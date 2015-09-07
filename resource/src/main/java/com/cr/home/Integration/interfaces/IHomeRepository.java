package com.cr.home.Integration.interfaces;

import org.springframework.stereotype.Service;

import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;

@Service
public interface IHomeRepository {

	ResponseMessage saveDetails(UserAccount user);

	ResponseMessage getDetails(UserAccount user);

}
