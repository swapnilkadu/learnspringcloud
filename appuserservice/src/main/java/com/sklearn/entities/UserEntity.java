package com.sklearn.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblusers")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 146776870006007930L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 50)
	private String fname;

	@Column(nullable = false, length = 50)
	private String lname;

	@Column(nullable = false, length = 150, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String usrId;

	@Column(nullable = false, unique = true)
	private String encPasswd;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

}
