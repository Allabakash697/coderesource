package com.cr.home.Integration.interfaces;

import org.springframework.stereotype.Service;

import com.cr.home.beans.UserAccount;

@Service
public interface IHomeRepository {

	String saveDetails(UserAccount user);

}
