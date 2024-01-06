package logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class for saving test informatin from data base to show it in student's test screen
 */
public class Test implements Serializable{
	private ArrayList<InTestQuestion> qLst ; //test question list
	private String courseName; 
	private int testId;
	private int lecturerId;
	private int courseId;
	private ArrayList<Integer> studentsIdForTest = new ArrayList<>();
	public ArrayList<Integer> getStudentsIdForTest() {
		return studentsIdForTest;
	}
	public void setStudentsIdForTest(ArrayList<Integer> studentsIdForTest) {
		this.studentsIdForTest = studentsIdForTest;
	}
	public int getTestId() {
		return testId;
	}
	public ArrayList<InTestQuestion> getqLst() {
		return qLst;
	}
	public String getCourseName() {
		return courseName;
	}

	public int getDuration() {
		return duration;
	}
	public int getQuesSize() {
		return quesSize;
	}
	public int getLecturerId() {
        return lecturerId;
    }
	public String getStudentNotes() {
		return studentNotes;
	}
	private int duration;
	private String studentNotes;
	private int quesSize;
	public Test( String courseName, int duration, String studentNotes, int testId , int lecturerId,int courseId) {
		super();
		this.courseName = courseName;
		this.duration = duration;
		this.studentNotes = studentNotes;
		this.testId=testId;
		this.lecturerId=lecturerId;
		this.courseId=courseId;
	}
	public void setQLst(ArrayList<InTestQuestion> qLst) {
		this.qLst=qLst;
		
	}
	public void setquesSize(int quesSize) {
		this.quesSize=quesSize;
		
	}
	public int getCourseId() {
        return courseId;
    }
   
	

}
