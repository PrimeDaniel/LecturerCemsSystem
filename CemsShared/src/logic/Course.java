package logic;

import java.io.Serializable;

/**
 * The Course class represents a course with a lecturer, course ID, course name, lecturer ID, and
 * grade
 */
public class Course implements Serializable{
	String lectuer;
	String courseID;
	String courseName;
	String lectuerID;
	public String getLectuerID() {
		return lectuerID;
	}

	public void setLectuerID(String lectuerID) {
		this.lectuerID = lectuerID;
	}

	public Course(String lectuer, String courseID, int grade,String courseName,String lectuerID) {
		super();
		this.lectuer = lectuer;
		this.lectuerID = lectuerID;
		this.courseID = courseID;
		this.grade = grade;
		this.courseName=courseName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	int grade;
	public String getLectuer() {
		return lectuer;
	}
	public void setLectuer(String lectuer) {
		this.lectuer = lectuer;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	

}
