package com.sklearn.uimodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	@NotNull
	@Size(max = 10)
	private String fname;
	private String lname;
	@NotNull
	private String email;
	private String passwd;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
