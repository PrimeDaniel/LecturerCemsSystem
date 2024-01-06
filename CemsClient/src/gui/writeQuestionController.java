package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import client.ChatClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Question;
import logic.Request;
import logic.Users;

/**
 * This is a Java class for a write question controller that handles user input for creating and saving
 * questions in a GUI.
 */
public class writeQuestionController {
	private ChatClient client;
	private Users lecturer;

	@FXML
	private TextField answer1;

	@FXML
	private TextField answer2;

	@FXML
	private TextField answer3;

	@FXML
	private TextField answer4;

	@FXML
	private Button backBtn;

	@FXML
	private ToggleGroup correctAnswer;

	@FXML
	private ToggleGroup correctAnswer1;

	@FXML
	private ComboBox<String> coursesComboBox;

	@FXML
	private Pane createQuestionForm;

	@FXML
	private ComboBox<String> professionsComboBox;

	@FXML
	private TextArea questionDescriptionField;

	@FXML
	private RadioButton radio1;

	@FXML
	private RadioButton radio2;

	@FXML
	private RadioButton radio3;

	@FXML
	private RadioButton radio4;

	@FXML
	private Button saveButton;

	@FXML
	private Text toolCreateExams;

	@FXML
	private Text toolGrade;

	@FXML
	private Text toolStatistics;


	@FXML
	private Text toolWriteQuestions;

	@FXML
	private Label saveLabelBtn;

	@FXML
	
	// This function is responsible for loading the main screen of the application when the user clicks on
	// the "back" button.
	void backToMainScreen(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LecturerPage.fxml")); // specify the path to the
																								// main screen FXML file
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene mainScene = new Scene(parent);

		// Get the main screen's controller and pass the ChatClient and lecturer
		// instances to it
		LecturerPageController controller = loader.getController();
		controller.setLecturerAndClient(lecturer, client);
		controller.getOngoingExamsTable();
		client.setController(controller);

		// Get the Stage information
		Stage window = (Stage) backBtn.getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}

	/**
	 * This function sets the client and lecturer for a chat session.
	 * 
	 * @param client The ChatClient object that represents the user who is setting the client and
	 * lecturer.
	 * @param lecturer The lecturer parameter is an instance of the Users class, which represents a user
	 * in the chat system. It likely contains information such as the user's name, ID, and other relevant
	 * details.
	 */
	public void setClientAndLecturer(ChatClient client, Users lecturer) {
		this.client = client;
		this.lecturer = lecturer;
	}

	// sends the question to the server
	// uses the Question class from CemsShared
	// creates a Request object with the question and sends it to the server
	@FXML
	public void btnSaveQuestion(ActionEvent event) {
		String questionDescription = questionDescriptionField.getText();
		String answer1 = this.answer1.getText();
		String answer2 = this.answer2.getText();
		String answer3 = this.answer3.getText();
		String answer4 = this.answer4.getText();
		int correctAnswer = 0;
		if (radio1.isSelected()) {
			correctAnswer = 1;
		} else if (radio2.isSelected()) {
			correctAnswer = 2;
		} else if (radio3.isSelected()) {
			correctAnswer = 3;
		} else if (radio4.isSelected()) {
			correctAnswer = 4;
		}

		String course = coursesComboBox.getValue();
		String subject = professionsComboBox.getValue();
		if (questionDescription.equals("") || answer1.equals("") || answer2.equals("") || answer3.equals("")
				|| answer4.equals("") || correctAnswer == 0 || course == null || subject == null) {
			saveLabelBtn.setText("Please fill all the fields");
			return;
		}

		String author = lecturer.getFirstName() + " " + lecturer.getLastName();
		int authorID = lecturer.getId();
		// create a Question object
		Question question = new Question(questionDescription, answer1, answer2, answer3, answer4, correctAnswer, course,
				authorID, author);
		// create a Request object
		Request request = new Request("writeQuestion", question);
		// send the request to the server
		try {
			client.sendToServer(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveLabelBtn.setText("Question saved successfully");

	}

	/**
	 * This function updates a JavaFX ComboBox with a list of subjects in a separate thread
	 * 
	 * @param subjects An ArrayList of Strings representing the subjects that will be displayed in a
	 * ComboBox.
	 */
	public void updateSubjectsComboBox(ArrayList<String> subjects) {
		Platform.runLater(() -> {
			ObservableList<String> list = FXCollections.observableArrayList(subjects);
			professionsComboBox.setItems(list);
		});
	}

	public void updateCoursesComboBox(ArrayList<String> courses) {
		Platform.runLater(() -> {
			ObservableList<String> list = FXCollections.observableArrayList(courses);
			coursesComboBox.setItems(list);
		});

	}

	 /**
	 * This function loads a new scene for a questions tool  and sets the controller and client for the
	 * new scene.
	 */
	private void questionTool() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/write_question.fxml")); // specify

		Parent parent = null;
		try {
			parent = loader.load();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		writeQuestionController controller = loader.getController();
		controller.setClientAndLecturer(this.client, lecturer);
		client.setController(controller);

		// Get the Stage information
		Stage window = (Stage) toolGrade.getScene().getWindow();
		window.setScene(nextScene);
		window.show();

	}

     /**
	 * This function loads a new scene for a grades tool  and sets the controller and client for the
	 * new scene.
	 */

	private void GradeTool() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/lecturerApproval.fxml")); // specify

		Parent parent = null;
		try {
			parent = loader.load();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		LecturerApprovalController controller = loader.getController();
		controller.setClientAndLecturer(this.client, lecturer);
		controller.getGrades();
		client.setController(controller);

		// Get the Stage information
		Stage window = (Stage) toolGrade.getScene().getWindow();
		window.setScene(nextScene);
		window.show();

	}

     /**
	 * This function loads a new scene for a exams tool  and sets the controller and client for the
	 * new scene.
	 */

	private void ExamsTool() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/create_exam.fxml")); // specify

		Parent parent = null;
		try {
			parent = loader.load();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		createExamController controller = loader.getController();
		controller.setClientAndLecturer(this.client, lecturer);
		client.setController(controller);

		// Get the Stage information
		Stage window = (Stage) toolGrade.getScene().getWindow();
		window.setScene(nextScene);
		window.show();
	}

	/**
	 * This function loads a new scene for a statistics tool and sets the controller and client for the
	 * new scene.
	 */
	private void StatisticsTool() {
		Platform.runLater(() -> {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/lecturerStatistics.fxml")); // specify

			Parent parent = null;
			try {
				parent = loader.load();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Scene nextScene = new Scene(parent);

			// Get the new scene's controller and pass the ChatClient instance to it
			LecturerStatisticsController controller = loader.getController();
			controller.setClientAndLecturer(this.client, lecturer);
			controller.getExamsTable();
			controller.getGrades();
			client.setController(controller);

			// Get the Stage information
			Stage window = (Stage) toolGrade.getScene().getWindow();
			window.setScene(nextScene);
			window.show();
		});
	}

// The `initialize()` function is function in JavaFX that is called when the FXML file is
// loaded and the controller is initialized. 
	@FXML
	public void initialize() {
		radio1.setToggleGroup(correctAnswer);
		radio2.setToggleGroup(correctAnswer);
		radio3.setToggleGroup(correctAnswer);
		radio4.setToggleGroup(correctAnswer);

		professionsComboBox.setOnMouseClicked(event -> {
			// add some subjects to the comboBox
			Request request = new Request("getSubjects", null);
			try {
				client.openConnection();
				client.sendToServer(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		coursesComboBox.setOnMouseClicked(event -> {
			// add some courses to the comboBox depending on the subject currently selected
			String subject = professionsComboBox.getValue();
			if (subject != null) {
				Request request = new Request("getCourses", subject);
				try {
					client.openConnection();
					client.sendToServer(request);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		toolWriteQuestions.setOnMouseClicked(e -> {
			Platform.runLater(() -> {
				questionTool();
			});

		});
		toolCreateExams.setOnMouseClicked(e -> {
			Platform.runLater(() -> {
				ExamsTool();
			});

		});

		toolGrade.setOnMouseClicked(e -> {
			Platform.runLater(() -> {
				GradeTool();
			});
		});
		toolStatistics.setOnMouseClicked(e -> {
			Platform.runLater(() -> {
				StatisticsTool();
			});

		});

	}

}
