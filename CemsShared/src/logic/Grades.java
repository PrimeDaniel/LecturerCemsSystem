package logic;

import java.io.Serializable;
import java.util.ArrayList;

// This class represent a Grade object after student's test.
// It has instance variables `examID`, `studentID`, `courseID`, `grade`, and `lectuerID`, and methods to get
// and set these variables.

public class Grades implements Serializable {
	int examID;
	int studentID;
	int courseID;
	int grade;
	int lectuerID;
	
	

	public String getLectuerID() {
		return String.valueOf(lectuerID);
	}

	public void setLectuerID(String lectuerID) {
		this.lectuerID = Integer.parseInt(lectuerID);
	}

	public String getExamID() {
		return String.valueOf(examID);
	}

	public void setExamID(String examID) {
		this.examID = Integer.parseInt(examID);
	}

	public String getStudentID() {
		return String.valueOf(courseID);
	}

	public String getCourseID() {
		return String.valueOf(courseID);
	}

	public void setCourseID(String courseID) {
		this.courseID = Integer.parseInt(courseID);
	}

	public int getGrade() {
		return grade;
	}
	

	public Grades(int examID, int studentID, int courseID, int grade, int lectuerID) {

		this.examID =(examID);
		this.studentID = (studentID);
		this.courseID = (courseID);
		this.grade = grade;
		this.lectuerID = (lectuerID);
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setStudentID(String studentID) {
		this.studentID = Integer.parseInt(studentID);
	}

	public String getCourseName() {
		return String.valueOf(courseID);
	}

	public void setCourseName(String courseName) {
		this.courseID = Integer.parseInt(courseName);
	}

	@Override
	public String toString() {
		return "Grades [examID=" + examID + ", studentID=" + studentID + ", courseID=" + courseID + ", grade=" + grade
				+ "]";
	}

	
}
