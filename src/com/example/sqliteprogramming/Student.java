package com.example.sqliteprogramming;

public class Student {

	int id;
	String name;
	int courseID;
	String email;

	public Student() {
	}

	public Student(int id, String name, int courseID, String email) {
		super();
		this.id = id;
		this.name = name;
		this.courseID = courseID;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", courseID="
				+ courseID + ", email=" + email + "]";
	}

}
