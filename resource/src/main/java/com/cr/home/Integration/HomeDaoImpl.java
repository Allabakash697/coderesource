package com.cr.home.Integration;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.cr.home.Integration.interfaces.IHomeDao;
import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;

@Component
public class HomeDaoImpl implements IHomeDao {

	ResponseMessage message=new ResponseMessage();;

	Connection conn=null;
	Statement stmt=null;
	PreparedStatement preparestmt=null;
	public ResponseMessage saveDetails(UserAccount user) {
try{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "root");
		String query="INSERT INTO user_data (user_name,user_email,user_password)"+"VALUES(?,?,?)";
		
		
		preparestmt=conn.prepareStatement(query);
		preparestmt.setString(1, user.getName());
		preparestmt.setString(2,user.getEmailId());
		preparestmt.setString(3, user.getPassword());
		preparestmt.execute();
		preparestmt.close();
}catch (Exception e) {
	e.printStackTrace();
}

try{
	if(user.getName()!=null){
		message.setMessage("Registration Success");
		message.setUserAcc(user);
		}else
		{
			message.setMessage("Registration Failed");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(stmt!=null)
		try {
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
return message;
	}
	public ResponseMessage getDetails(UserAccount user) {
String userEmail=user.getEmailId();
String pass=user.getPassword();
UserAccount userDb=new UserAccount();
		try{
			String uname=null;
			String emailId=null;
			String password=null;
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "root");
			List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
			paramList.add(getQtFlgParameterSource(user));
			stmt=conn.createStatement();
			String query="SELECT * FROM user_data WHERE user_email="+"'"+userEmail+"'"+" AND user_password="+"'"+pass+"'";
			
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()){
				 uname=rs.getString("user_name");
				userDb.setName(uname);
				 emailId=rs.getString("user_email");
				userDb.setEmailId(emailId);
				 password=rs.getString("user_password");
				userDb.setPassword(password);
			}
					if(userEmail.equals(emailId) && pass.equals(password)){
					message.setMessage("Login Success");
					message.setUserAcc(userDb);
					}
			else{
						message.setMessage("Incorrect Email Id or Password ");
						message.setUserAcc(user);
					}
					rs.close();	
			
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		if(stmt!=null)
		try {
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		return message;

}
	private SqlParameterSource getQtFlgParameterSource(UserAccount user) {
		MapSqlParameterSource parameters =   new MapSqlParameterSource();
		parameters.addValue("user_email", user.getEmailId());
		parameters.addValue("user_password", user.getPassword());
		return parameters;
	}
}
