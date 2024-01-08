package gui;

import java.io.IOException;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.MyFile;
import logic.Request;
import logic.StudentInTest;
import logic.Test;
import logic.UploadFile;
import logic.Users;



/**
 * The class "ApproveSubmitController" contains various variables related to a student's test
 * submission and communication with a chat client.
 */
public class ApproveSubmitController {
	private UploadFile answersFile;
	private Test test;
	private StudentInTest studentInTest;
	private ChatClient client;
	private Users student;
	private InExamController inExamController;
	private StudentManualTestController studentManualTest;
	private int DigOrMan;

	
	/** 
	 * @param answersFile The parameter "answersFile" is an object of type "UploadFile". It is  used
	 * to store a file that contains answers to  test. The "setAnswersFile" method is a setter
	 * method that sets the value of the "answersFile" object.
	 */
	public void setAnswersFile(UploadFile answersFile) {
		this.answersFile = answersFile;
	}

	/**
	 * returns if the test is digital or manual
	 */
	public int getDigOrMan() {
		return DigOrMan;
	}

	/**
	 * sets the test property to digital or manual
	 * @param digOrMan this paramater has value 0 if the test to submit is Digital, or the value 1 
	 * if the test to submit is manual
	 */
	public void setDigOrMan(int digOrMan) {
		DigOrMan = digOrMan;
	}


	/**
	 * This function sets the student, chat client, student in test, in exam controller, and test for a
	 * given user when the test tybe is Digital 
	 * 
	 * @param Student An object of the class Users representing a student.
	 * @param client The ChatClient object that represents the connection between the student and the
	 * server in the Digital exam.
	 * @param studentInTest It is an object that represents a student's information and progress in a
	 * test. It contain attributes such as the student's answers, time remaining, and current question
	 * number.
	 * @param inExamController It is an object of the InExamController class, which is responsible for
	 * controlling the user interface and logic during an Digital exam.
	 * @param test The test parameter is an object of the Test class, which contains information about the
	 * test that the student is taking. This information includes the test name, duration, number of
	 * questions, and other relevant details.
	 */
	public void setStudentAndClient(Users Student, ChatClient client, StudentInTest studentInTest,
			InExamController inExamController, Test test) {
		this.student = Student;
		this.client = client;
		this.studentInTest = studentInTest;
		this.inExamController = inExamController;
		this.test = test;
	}

	/**
	 * This function sets the student, chat client, and student manual test controller for a given object.
	 * when the test type is Manual
	 * 
	 * @param Student An object of the class Users representing a student.
	 * @param client TThe ChatClient object that represents the connection between the student and the
	 * server in the Manual exam.
	 * @param Controller The parameter "Controller" is an object of the class
	 * "StudentManualTestController". this controller is responsible for controlling the manual test screen.
	 */
	public void setStudentAndClient2(Users Student, ChatClient client, StudentManualTestController Controller) {
		this.student = Student;
		this.client = client;
		this.studentManualTest = Controller;
	}

	@FXML
	private Button clsBtn;

	@FXML
	private Label lbl;

	@FXML
	private Text mesTxt;
	@FXML
	private Button noBtn;

	@FXML
	private Button yesBtn;

	@FXML
	/**
	 * this method is called when the user clicks the "no" button to cancel the submission
	 */
	void noBtnclicked(ActionEvent event) {
		Stage currentStage = (Stage) noBtn.getScene().getWindow();
		currentStage.close();
	}

	@FXML
	/**
	 * this method is called when the user clicks the "close" button to close the submission window
	 */
	void clsBtnClicked(ActionEvent event) {
		Stage currentStage = (Stage) clsBtn.getScene().getWindow();
		currentStage.close();

	}

	@FXML

	/** The `yesBtnClicked` method is a function that is called when the user clicks the "yes" button on
	* the submission confirmation screen. It checks whether the test is digital or manual, and then
	* either sends the student's answers to the server for grading (in the case of a digital test) or
	* sends the student's uploaded file to the server (in the case of a manual test). It also disables
	* the "yes" and "no" buttons and displays a message indicating that the test has been submitted
	* successfully. Finally, it stops the timer and closes the exam window. */
	void yesBtnClicked(ActionEvent event) {
		if (DigOrMan == 0) { //tybe=0 -> test to submit is Digital
			try {
				int score = 0;
				for (int i = 0; i < test.getQuesSize(); i++) {
					if (test.getqLst().get(i).getcAns() == studentInTest.getAnswer(i))
						score += test.getqLst().get(i).getScore();
				}
				studentInTest.setScore(score);
				client.sendToServer(new Request("SubmitExam", studentInTest));
				System.out.println("Submitted");
				yesBtn.setDisable(true);
				noBtn.setDisable(true);
				lbl.setText("The Test is Submitted Succesfully!");
				mesTxt.setText("Good Luck!");
				inExamController.stopTimer();
				inExamController.CloseWindow();

			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			try {//tybe=1 -> test to submit is manual
				client.sendToServer(new Request("SubmitManualExam", answersFile));
				studentManualTest.CloseWindow();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// send new request to server "Submit Manual exam"
			yesBtn.setDisable(true);
			noBtn.setDisable(true);
			lbl.setText("The Test is Submitted Succesfully!");
			mesTxt.setText("Good Luck!");
			studentManualTest.stopTimer();
			studentManualTest.CloseWindow();
		}

	}

	/**
	 * returns the controller used for this form
	 */
	public ApproveSubmitController getController() {
		return this;
	}

	/**
	 * submits either a digital or manual exam the exam when the timer runs up or the lecturer closes the exam.
	 */
	public void forceSubmit() {
		yesBtn.setDisable(true);
		noBtn.setDisable(true);
		lbl.setText("Time out!");

		if (DigOrMan == 0) { // submiting the exam Digital test
			try {
				mesTxt.setText("your answers are submitted automatically, Good Luck!");
				client.sendToServer(new Request("SubmitExam", studentInTest));
				inExamController.CloseWindow();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else { // submiting the exam manual test
			try {
				client.openConnection();
				if (client.isConnected()) {
					mesTxt.setText("Good luck next time!");
					client.sendToServer(new Request("SubmitManualExam", answersFile));
					studentManualTest.CloseWindow();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * this function will be called when lectuer decides to lock exam,
	 * it disables the option to submit the exam after it was locked
	 */
	public void examIsLocked() {
		yesBtn.setDisable(true);
		noBtn.setDisable(true);
		lbl.setText("Sorry Exam is locked by lecturer!");

		if (DigOrMan == 0) { //Digital exam case
			try {
				mesTxt.setText("Your answers are submitted automatically, Good Luck!");
				forceSubmit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {//Manual exam case
				mesTxt.setText("Good luck next time!");
				forceSubmit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
