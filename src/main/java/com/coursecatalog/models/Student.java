package com.coursecatalog.models;

import java.util.List;


public class Student {
	
	private String sId;
	private String sName;
	private String email;


	public Student() {
		
	}

	
	public Student(String sId, String sName, String email) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.email = email;
	}
	

	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
