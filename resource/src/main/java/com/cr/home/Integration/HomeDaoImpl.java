package com.cr.home.Integration;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.cr.home.Integration.interfaces.IHomeDao;
import com.cr.home.beans.Constants;
import com.cr.home.beans.ResponseMessage;
import com.cr.home.beans.UserAccount;
import com.cr.home.beans.UserDetail;
import com.cr.home.beans.UserProfileCookie;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@Component
public class HomeDaoImpl implements IHomeDao {

	ResponseMessage message=new ResponseMessage();;

	Connection conn=null;
	Statement stmt=null;
	PreparedStatement preparestmt=null;
	public ResponseMessage saveDetails(UserAccount user,HttpServletResponse response) {
try{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/code_resource", "root", "root");
		String query1="INSERT INTO user_data (emailId,user_password)"+"VALUES(?,?)";
		
		
		preparestmt=conn.prepareStatement(query1);
		preparestmt.setString(1,user.getEmailId());
		preparestmt.setString(2, user.getPassword());
		preparestmt.execute();
		
		String query2="INSERT INTO users_list (name,city)"+"VALUES(?,?)";
		preparestmt=conn.prepareStatement(query2);
		preparestmt.setString(1,user.getName());
		preparestmt.setString(2, user.getCity());
		preparestmt.execute();
		
		preparestmt.close();
		setUserCookie(response,user);
		
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
	public ResponseMessage getDetails(UserAccount user,HttpServletResponse response) {
		 message.setAdmin(false);
String userEmail=user.getEmailId();
String pass=user.getPassword();
UserAccount userDb=new UserAccount();
		try{
			int id=0;
			String emailId=null;
			String password=null;
			
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/code_resource", "root", "root");
			/*List<UserAccount> paramList = new ArrayList<UserAccount>();*/
			/*paramList.add(getQtFlgParameterSource(user));*/
			stmt=conn.createStatement();
			
			if(!user.isSession()){
			String query1="SELECT * FROM user_data WHERE emailId="+"'"+userEmail+"'"+" AND user_password="+"'"+pass+"'";
			
			ResultSet rs=stmt.executeQuery(query1);
			while(rs.next()){

				 emailId=rs.getString("emailId");
				 password=rs.getString("user_password");
				id=rs.getInt("id");
				
			}
			rs.close();	
			}else{
				String query1="SELECT * FROM user_data WHERE emailId="+"'"+userEmail+"'";
				
				ResultSet rs=stmt.executeQuery(query1);
				while(rs.next()){

					 emailId=rs.getString("emailId");
					id=rs.getInt("id");
					
				}
				rs.close();	
			}

			if(!user.getEmailId().equalsIgnoreCase(Constants.ADMIN_ID)){
			String query2="SELECT * FROM users_list WHERE id="+id;
			
			ResultSet rs=stmt.executeQuery(query2);
			while(rs.next()){
				userDb.setName(rs.getString("name"));
				 userDb.setCity(rs.getString("city"));
			}
			rs.close();	
			}else{
				message.setAdmin(true);
				userDb.setName("Code Resource");
			}
			
			if(!user.isSession()){
					if(userEmail.equals(emailId) && pass.equals(password)){
						userDb.setEmailId(emailId);
					message.setMessage("Welcome");
					message.setUserAcc(userDb);
					
					setUserCookie(response,userDb);
					}
			else{
						message.setMessage("Incorrect Email Id or Password.. Try Again ");
						message.setUserAcc(userDb);
					}
			}else{
				message.setMessage("Welcome");
				message.setUserAcc(userDb);
			}
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
	private void setUserCookie(HttpServletResponse response, UserAccount userDb) {
		UserProfileCookie userCookie=new UserProfileCookie();
		userCookie.setEmailId(userDb.getEmailId());
		userCookie.setSession(true);
		if(userDb.getEmailId().equalsIgnoreCase(Constants.ADMIN_ID)){
		userCookie.setAdmin(true);
		}else{
			userCookie.setAdmin(false);
		}
		XStream xstream = new XStream(new StaxDriver());
		String userCookieModel = xstream.toXML(userCookie);
		Cookie cookieObject=new Cookie("userCookieModel",userCookieModel);
		cookieObject.setPath("/resource");
		response.addCookie(cookieObject);
		
	}
	/*private SqlParameterSource getQtFlgParameterSource(UserAccount user) {
		MapSqlParameterSource parameters =   new MapSqlParameterSource();
		parameters.addValue("emailId", user.getEmailId());
		parameters.addValue("user_password", user.getPassword());
		return parameters;
	}*/
	
	public List<UserDetail> getUsers(){
		
		List<UserDetail> paramList = new ArrayList<UserDetail>();
		
		List<UserDetail> userList = new ArrayList<UserDetail>();
		
		try{
			String emailId = null;
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/code_resource", "root", "root");
			stmt=conn.createStatement();
			String query1="SELECT * FROM user_data";
			ResultSet rs=stmt.executeQuery(query1);
			while(rs.next()){
				UserDetail users=new UserDetail();
				emailId=rs.getString("emailId");
				users.setEmailId(emailId);
				users.setId(rs.getString("id"));
				userList.add(users);
			}
			
			if(emailId!=Constants.ADMIN_ID){
			for(UserDetail user:userList){
			String id=user.getId();
			String query2="SELECT * FROM users_list where id="+id;
			rs=stmt.executeQuery(query2);
			while(rs.next()){
				UserDetail users=new UserDetail();
				users.setId(rs.getString("id"));
				 users.setName(rs.getString("name"));
				 users.setCity(rs.getString("city"));
				 users.setEmailId(user.getEmailId());
				 paramList.add(users);
			}
			}
			}
			
					rs.close();	
			System.out.println("paramList"+paramList);
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
		return paramList;
		
}
	/*public ResponseMessage reloadDetails(String emailId) {
		UserAccount userDb=new UserAccount();
				try{
					int id=0;
					String uname=null;
					Class.forName("com.mysql.jdbc.Driver");
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/code_resource", "root", "root");
					List<SqlParameterSource> paramList = new ArrayList<SqlParameterSource>();
					stmt=conn.createStatement();
					String query1="SELECT * FROM user_data WHERE emailId="+"'"+emailId+"'";
					
					ResultSet rs=stmt.executeQuery(query1);
					while(rs.next()){

						 emailId=rs.getString("emailId");
						userDb.setEmailId(emailId);
						id=rs.getInt("id");
						System.out.println(id);
						userDb.setId(id);;
						
					}
					
					String query2="SELECT * FROM users_list WHERE id="+id;
					
					 rs=stmt.executeQuery(query2);
					while(rs.next()){
						 uname=rs.getString("name");
						userDb.setName(uname);;
						userDb.setCity(rs.getString("city"));
					}
							message.setMessage("Welcome");
							message.setUserAcc(userDb);
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
	}*/
}