package com.cr.home.Integration;



import org.springframework.stereotype.Component;

import com.cr.home.Integration.interfaces.IHomeDao;
import com.cr.home.beans.Dummy;
import com.cr.home.beans.UserAccount;

@Component
public class HomeDaoImpl implements IHomeDao {

	String message;
	Dummy dummy=new Dummy();

	public String saveDetails(UserAccount user) {

		dummy.setName(user.getName());
		dummy.setEmailId(user.getEmailId());
		dummy.setPassword(user.getPassword());
		
		System.out.println(dummy);
		if(dummy.getName()==null){
			message="Registration Success";
		}else
		{
			message="Registration Failed";
		}
		return message;
	}

}
