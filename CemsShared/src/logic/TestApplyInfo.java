package logic;

import java.io.Serializable;
/**
 * this class for saving students id and test id for server check if he can do the exam
 */
public class TestApplyInfo  implements Serializable {
	private int studentId;
	private int testId;
	public TestApplyInfo(int studentId, int testId) {
		super();
		this.studentId = studentId;
		this.testId = testId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}

	

}