package com.coursecatalog.models;


import java.util.ArrayList;
import java.util.List;



public class Course {
	
	private String cId;
	private String cName;
	private double rating, duration;
	
	private List<String> studentsList = new ArrayList<>();
	
	

	public Course() {
	}
	
	public Course(String cId, String cName, double rating, double duration, List<String> studentsList) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.rating = rating;
		this.duration = duration;
		this.studentsList = studentsList;
	}



	public List<String> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<String> studentsList) {
		this.studentsList = studentsList;
	}


	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return this.cName + " and List = " + this.studentsList;
	}
	
	
	
}
