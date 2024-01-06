package gui;

import java.io.IOException;

import javafx.scene.control.TextArea;

import client.ChatClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.LogInInfo;
import logic.InTestQuestion;
import logic.Request;
import logic.StudentInTest;
import logic.Test;
import logic.TestApplyInfo;
import logic.TestCode;
import logic.Users;

/**
 * the take exam controller mange the process of student takeing exam in our
 * cems app
 * ,it valid the exam code input by the sstudent
 * ,ensure the confirm of the chrckbox and comunict with the server to run the
 * test and then it show to test in exam screen where the student enters is ID
 * the start the test .
 */
public class TakeExamController {
	private int DigOrMan;

	public int getDigOrMan() {
		return DigOrMan;
	}

	/**
	 * if student starts digital exam then DigOrMan=0 , if student starts manual
	 * exam then DigOrMan=1
	 */
	public void setDigOrMan(int digOrMan) {
		DigOrMan = digOrMan;
	}

	private ChatClient client;
	private Users student;
	private TestCode testCode;
	private Test test;
	private InTestQuestion[] qLst;

	/**
	 * This function sets the student and client objects and also sets the TakeExamController for the
	 * client object.
	 * 
	 * @param Student An object of the class Users representing a student who is taking an exam.
	 * @param client connection between server and client
	 * @param controller the object of current class wich responsible for the "enter code " screen
	 */
	public void setStudentAndClient(Users Student, ChatClient client, TakeExamController controller) {
		this.student = Student;
		this.client = client;
		this.client.setTakeExamController(controller);
	}

	@FXML
	private Text lbl;

	@FXML
	private Text errMes1;

	@FXML
	private Text errMes2;

	@FXML
	private Text errMes3;
	@FXML
	private CheckBox ConfirmCheckB;

	@FXML
	private Button backBtn;

	@FXML
	private TextField codeTxt;

	@FXML
	private Button startBtn;

	private boolean checked = false;

	/**
	 * This function toggles a boolean variable "checked" between true and false when a checkbox is
	 * checked or unchecked.
	 * 
	 * @param event The event parameter in this code is an instance of the ActionEvent class, which
	 * represents a user action, such as clicking a button or selecting a checkbox. It is passed to the
	 * ConfirmCheckBChecked method when the corresponding event occurs.
	 */
	@FXML
	void ConfirmCheckBChecked(ActionEvent event) {
		if (!checked)
			checked = true;
		else
			checked = false;
	}

	/**
	 * The function closes the current stage when the back button is clicked in a JavaFX application.
	 * 
	 * @param event The event parameter in this code is an instance of the ActionEvent class, which is
	 * passed to the method when the backBtn is clicked. It contains information about the event that
	 * occurred, such as the source of the event (in this case, the backBtn button) and any additional
	 * data related to
	 */
	@FXML
	void backBtnClicked(ActionEvent event) {
		Stage currentStage = (Stage) backBtn.getScene().getWindow();
		currentStage.close();
	}

	/**
	 * when the student clicks the start button the metued checks if the exam code
	 * is valid or not
	 * . is send request to the server to fetch the exam ,if the client is not
	 * connected to the server desplay the error message.
	 * 
	 * @param event
	 */
	@FXML
	void startBtnClicked(ActionEvent event) {
		String code = codeTxt.getText();
		if (CheckApplyingInfo(code)) {
			TestCode testCode = new TestCode(Integer.valueOf(code), DigOrMan);
			if (DigOrMan == 0) { // Digital exam path
				try {
					client.openConnection();
					if (client.isConnected()) {
						client.sendToServer(new Request("GetExam", testCode));
						errMes1.setText("Code is wrong!, please try again!");
					} else {
						System.out.println("Not connected to server.");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {// Manual exam path
				try {
					client.openConnection();
					if (client.isConnected()) {
						client.sendToServer(new Request("GetExam", testCode));
						errMes1.setText("Code is wrong!, please try again!");
					} else {
						System.out.println("Not connected to server.");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	public void setLabel(String str) {
		lbl.setText(str);

	}

	/**
	 * This function shows the screen for entering a student ID for a test
	 * 
	 * @param test The test object info that the student is trying to enter.
	 * it includes the students can enter id list and test info .
	 */
	public void ShowStudentEnterIdScreen(Test test)  {
		// System.out.println("I arruved at show student test");
		Platform.runLater(() -> {
			if (test == null) {
				// show error text
				errMes1.setText("Code is wrong!, please try again!");
			}

			else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EnterIdForTest.fxml")); // specify the
																										// path to the
																										// new fxml file
				Parent root;
				try {
					root = loader.load();
					Stage window = (Stage) getstartBtn().getScene().getWindow();
					EnterIdForTestController controller = loader.getController();
					// controller.setClient(this.client,controller);
					// Get the Stage information
					controller.setTest(test);
					controller.setStudentAndClient(student, client);
					controller.setDigOrMan(DigOrMan);
					// controller.setStudentInTest();
					// controller.setFirstPage();
					controller.SetLectureNotes();
					window.setScene(new Scene(root));
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Checks if id and code not empty and contains number only ,
	 * //checks also if CheckBox is checked
	 * //returns True if yes /
	 * 
	 * @param code presents the code has been entered
	 * @return value = true if the code is legal , false if the code is illegal
	 */

	public boolean CheckApplyingInfo(String code) {
		boolean ret = false;
		String mes1 = "Please Click on Check Box!";
		String mes3 = "Please Enter Code!";
		errMes1.setText(" ");
		errMes2.setText(" ");
		if (code.isEmpty() || !checked) {
			if (!checked)
				errMes1.setText(mes1);
			if (code.isEmpty())
				errMes2.setText(mes3);
			ret = false;
		} else if (!code.matches("[0-9]+")) {
			errMes1.setText("Please Enter Numbers only for code.");
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}

	/**
	 * The function returns the current instance of the TakeExamController class.
	 * 
	 * @return An instance of the `TakeExamController` class is being returned.
	 */
	public TakeExamController getController() {
		return this;
	}

	/**
	 * The function returns a Button object named startBtn.
	 * 
	 * @return The method is returning a Button object named "startBtn".
	 */
	public Button getstartBtn() {
		return startBtn;
	}

}
