package com.cr.home.beans;

public class UserProfileCookie {

	private String emailId;
	
	private boolean session;
	
	private boolean isAdmin;
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isSession() {
		return session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}
}
