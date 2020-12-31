package com.sklearn.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 6436822575415824737L;

	private String fname;
	private String lname;
	private String email;
	private String passwd;
	private String usrId;
	private String encPasswd;

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

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getEncPasswd() {
		return encPasswd;
	}

	public void setEncPasswd(String encPasswd) {
		this.encPasswd = encPasswd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
