package gui;

import java.io.IOException;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.InTestQuestion;
import logic.Request;
import logic.Test;
import logic.TestApplyInfo;
import logic.TestCode;
import logic.Users;

// This is a Java class that serves as a controller for a GUI form. It contains methods for setting
// the student and client, setting the test, checking if a given ID is valid, displaying a test screen
// for a student, and setting lecture notes and test label for a course. It also has a field for the
// DigOrMan value, which determines whether the test is digital or manual. The class is used to handle
// events and actions related to entering an ID for a test.
public class EnterIdForTestController {
	private int DigOrMan;

	/**
	 * return if the test is manual (DigOrMan =1) or digital (DigOrMan=0)
	 * 
	 * @return
	 */
	public int getDigOrMan() {
		return DigOrMan;
	}

	/**
	 * sets the exam to be manual or digital
	 * manual (DigOrMan =1), digital (DigOrMan=0)
	 * @param digOrMan
	 */
	public void setDigOrMan(int digOrMan) {
		DigOrMan = digOrMan;
	}

	private ChatClient client;
	private Users student;
	private Test test;


	/**
	 * This function sets the student applying to test and client that currently using the form
	 * 
	 * @param Student A variable of type "Users" that represents a student user in cems app applying for test.
	 * @param client TThe ChatClient object that represents the connection between the student and the
	 * server.
	 */
	public void setStudentAndClient(Users Student, ChatClient client) {
		this.student = Student;
		this.client = client;
	}

	/**
	 * sets the test that we are going to initialize
	 * 
	 * @param test The "test" parameter is an object of the class "Test"
	 * whuch includes all te test info such as testid, questions , score of each , duration,
	 * list of students id's that can take the exam
	 */
	public void setTest(Test test) {
		this.test = test;

	}

	@FXML
	private Text errTxt;

	@FXML
	private TextField idField;

	@FXML
	private TextArea lecnotesArea;

	@FXML
	private Button starttestBtn;

	@FXML
	private Label testLbl;

	@FXML

	/**
	 * sends to server the ID of the student, checks if he's eligiable for the test.
	 * if yes, procceed to test form.
	 * 
	 * @param event
	 */
	void StartTestClicked(ActionEvent event) {
		String id = idField.getText();
		if (CheckApplyingInfo(id)) {
			try {
				int studentId = Integer.parseInt(id);
				TestApplyInfo testApplyInfo = new TestApplyInfo(student.getId(), test.getTestId());
				//if current student is in the list of students id then he can start the exam
				if (test.getStudentsIdForTest().contains(studentId)) {
					errTxt.setText("Correct Id");
					client.sendToServer(new Request("AddStudentToInExamList", testApplyInfo));
					ShowStudentTestScreen(test);
				} else {
					errTxt.setText("Already submitted!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			errTxt.setText("Wrong Id, please try again");
		}

	}


	/**
	 * This function checks if a given code is valid and matches a student's ID.
	 * 
	 * @param code a String variable representing an ID code that needs to be checked.
	 * @return A boolean value is being returned.
	 */
	public boolean CheckApplyingInfo(String code) {
		boolean ret = false;
		errTxt.setText(" ");
		if (code.isEmpty()) {
			errTxt.setText("Please Enter id!");
			ret = false;
		} else if (!code.matches("[0-9]+")) {
			errTxt.setText("Please Enter Numbers only for id.");
			ret = false;
		} else if (!code.equals(String.valueOf(student.getId()))) {
			errTxt.setText("Wrong Id, please try again");
			ret = false;
		}

		else {
			ret = true;
		}
		return ret;
	}

	
	/**
	 * This function displays a test screen for a student, either for a digital or manual exam, based on 
	 * DigOrMan value in this class , if DigOrMan=0 then test is Digital , if DigOrMan=1 then test is Manual
	 * 
	 * @param test The test object that contains information about the exam that the student is going to
	 * take.
	 */
	public void ShowStudentTestScreen(Test test) {
		if (test == null) {
			errTxt.setText("Id or Code is wrong!, please try again!");
		}

		else if (DigOrMan == 0) { //Digital exam case
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InExam.fxml"));
			errTxt.setText("");
			Parent root;
			try {

				root = loader.load();
				Stage window = (Stage) starttestBtn.getScene().getWindow();
				InExamController inExamController = loader.getController();
				inExamController.setTest(test);
				inExamController.setStudentAndClient(student, client, inExamController);
				inExamController.setStudentInTest();
				inExamController.setFirstPage();
				window.setScene(new Scene(root));
				window.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (DigOrMan == 1) { //Manual exam case
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Take_manual_exam.fxml"));
				Parent root = loader.load();
				Stage window = (Stage) starttestBtn.getScene().getWindow();
				StudentManualTestController controller = loader.getController();
				controller.setStudentAndClient(student, client, controller);
				controller.setTest(test);
				controller.setStudentInTest();
				controller.setWelcomeLabel();
				window.setScene(new Scene(root));
				window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	
	/**
	 * This function sets the lecture notes and test label for a course.
	 */
	public void SetLectureNotes() {
		lecnotesArea.setText(test.getStudentNotes());
		testLbl.setText("Welcome to " + test.getCourseName() + " Test");
	}

	
	/**
	 * The function returns an instance of the EnterIdForTestController class.
	 * 
	 * @return An instance of the class `EnterIdForTestController` is being returned.
	 */
	public EnterIdForTestController getController() {
		return this;
	}

}
