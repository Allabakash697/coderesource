package com.cr.home.Integration;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

import com.cr.home.Integration.interfaces.IHomeDao;
import com.cr.home.beans.UserAccount;

@Component
public class HomeDaoImpl implements IHomeDao {

	String message;

	Connection conn=null;
/*	Statement pst=null;
*/	PreparedStatement preparestmt=null;
	public String saveDetails(UserAccount user) {
try{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "root");
		String query="INSERT INTO user_data (user_name,user_email,user_password)"+"VALUES(?,?,?)";
		
		preparestmt=conn.prepareStatement(query);
		preparestmt.setString(1, user.getName());
		preparestmt.setString(2,user.getEmailId());
		preparestmt.setString(3, user.getPassword());
		preparestmt.execute();
		conn.close();
}catch (Exception e) {
	e.printStackTrace();
}
	if(user.getName()!=null){
		message="Registration Success";
		}else
		{
			message="Registration Failed";
		}
		return message;
	}

}
