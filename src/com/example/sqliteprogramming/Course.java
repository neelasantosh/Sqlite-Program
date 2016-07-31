package com.example.sqliteprogramming;

public class Course {
	int id;
	String title;
	int duration;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	public Course(int id, String title, int duration) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", duration="
				+ duration + "]";
	}

	
	
	
}
