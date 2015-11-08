package com.cr.home.Integration.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;
import com.cr.home.beans.UserDetail;

@Service
public interface IHomeRepository {

	ResponseMessage saveDetails(UserAccount user,HttpServletResponse response);

	ResponseMessage getDetails(UserAccount user,HttpServletResponse response);

	List<UserDetail> getUsers();

}
