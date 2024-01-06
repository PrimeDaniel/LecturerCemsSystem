package logic;

import java.io.Serializable;

/**
 * this class for saving the student finished the exam details,
 * it includes student info, test info, list of his answers
 */
public class StudentInTest implements Serializable {
	private int studentId; 
	private int testId;   
	private String CourseName;
	private int [] ansArr; //list of his answers number
	private int score=0;  // student score
	private int qesSize; //number of questions
	private int [] quesIdArr; //question ids array 
	private int lecturerId;
	private int courseId;
    private Test test;//test info
    public void setTest (Test test) {
    	this.test=test;
    }
    public Test getTest() {
    	return this.test;
    }
	public int[] getQuesIdArr() {
		return quesIdArr;
	}

	public void setQuesId(int[] quesIdArr) {
		this.quesIdArr = quesIdArr;
	}

	public StudentInTest( int studentId ,String CourseName,int qesSize,int testId ,int lecturerId,int courseId) {
		this.studentId = studentId;
		this.CourseName = CourseName;
		this.qesSize = qesSize;
		this.testId= testId;
		this.lecturerId=lecturerId;
		this.courseId=courseId;
		ansArr= new int [qesSize];
		for(int i=0 ; i< qesSize; i++) {
			ansArr[i]=0;
		}
		quesIdArr = new int [qesSize];
	}
	public int getCourseId() {
			return courseId;
		}
	public int getScore() {
			return score;
		}
	public void setScore(int score) {
		this.score=score;
	}
	public int getLecturerId() {
        return lecturerId;
    }

	public int getStudentId() {
		return studentId;
	}

	public int getTestId() {
		return testId;
	}

	public String getCourseName() {
		return CourseName;
	}

	public int[] getAnsArr() {
		return ansArr;
	}

	public int getQesSize() {
		return qesSize;
	}

	public void SetAnswer(int index, int answerNum) {
		this.ansArr[index]=answerNum;
		
	}
	public int getAnswer(int index) {
		return this.ansArr[index];
		
	}
	
}
