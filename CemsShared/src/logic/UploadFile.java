package logic;

import java.io.Serializable;

// This class is used to represent
// an uploaded file by a student for a particular test. It contains information about the file, the
// student who uploaded it, and the test for which it was uploaded. It also has methods to get and set
// the values of these attributes.
public class UploadFile implements Serializable  {
	private StudentInTest studentInTest;
	private MyFile myfile;
	private int studentId; 
	private int testId;
	public UploadFile(MyFile myfile, int studentId, int testId) {
		super();
		this.myfile = myfile;
		this.studentId = studentId;
		this.testId = testId;
	}
	public MyFile getMyfile() {
		return myfile;
	}
	public int getStudentId() {
		return studentId;
	}
	public int getTestId() {
		return testId;
	}
	public void setStudentInTest(StudentInTest studentInTest) {
		this.studentInTest = studentInTest;
	}
	
	public StudentInTest getStudentInTest() {
        return studentInTest;
    }
		

}
