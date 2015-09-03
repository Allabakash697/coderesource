package com.cr.home.beans;

public class ResponseMessage {

	private UserAccount userAcc;
	private String message;
	public UserAccount getUserAcc() {
		return userAcc;
	}
	public void setUserAcc(UserAccount userAcc) {
		this.userAcc = userAcc;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
