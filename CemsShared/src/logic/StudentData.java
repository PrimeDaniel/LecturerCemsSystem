package logic;

import java.io.Serializable;

/**
 * this class for saving the grades to show it in grades screen
 */
public class StudentData implements Serializable {

	private String CourseName;
	private String grade;
	private String status;

	public StudentData(String cname, String grade) {
		super();
		this.CourseName = cname;
		this.grade = grade;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
