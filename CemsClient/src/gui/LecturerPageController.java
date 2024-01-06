package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ChatClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Exam;
import logic.LogInInfo;
import logic.Request;
import logic.Users;

/**
 * The class "LecturerPageController" contains a user object for a lecturer, a chat client, and an
 * array list of ongoing exams.
 */
public class LecturerPageController implements Initializable {
	private Users lecturer;
	private ChatClient client;
	private ArrayList<Exam> ongoingExams;

	@FXML
	private TableColumn<Exam, Integer> examId;

	@FXML
	private TableColumn<Exam, Integer> examCode;

	@FXML
	private TableColumn<Exam, Integer> timeRem;

	@FXML
	private TableView<Exam> ongoingExamsTable;

	@FXML
	private Button CreateExamBtn;

	@FXML
	private Button CloseExamBtn;

	@FXML
	private Button StartExamBtn;

	@FXML
	private Pane createQuestionForm;

	@FXML
	private Label lecturerName;

	@FXML
	private Button logOutBtn;

	@FXML
	private Text toolCreateExams;

	@FXML
	private Text toolGrade;

	@FXML
	private Text toolStatistics;


	@FXML
	private Text toolWriteQuestions;

	@FXML
	private Button writeQuestionBtn;

	@FXML
	private Button TimeRequestBtn;

	/**
	 * This function initializes the JavaFX GUI components and sets up event handlers for various tools.
	 * 
	 * @param location The location of the FXML file that contains the UI layout for this controller.
	 * @param resources A bundle of resources, such as strings or images, that can be used by the
	 * controller. It is typically used for internationalization and localization purposes.
	 */
	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		

		examId.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("examId"));
		examCode.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("code"));
		timeRem.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("testTime"));
		Platform.runLater(() -> {
			lecturerName.setText(lecturer.getFirstName() + " " + lecturer.getLastName());
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

	// update all toolsBar
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
	
	// end toolsBar

	
	/**
	 * This function loads a new FXML file for past exams and sets the scene to display it.
	 * 
	 * @param event The event that triggered the method, which is an ActionEvent in this case. It is not
	 * used in the method implementation.
	 */
	@FXML
	public void pastExams(ActionEvent event) {
		Platform.runLater(() -> {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/pastExams.fxml")); // specify

			Parent parent = null;
			try {
				parent = loader.load();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Scene nextScene = new Scene(parent);

			// Get the new scene's controller and pass the ChatClient instance to it
			pastExamsController controller = loader.getController();
			controller.setClientAndLecturer(this.client, lecturer);
			controller.getPastExams();
			client.setController(controller);

			// Get the Stage information
			Stage window = (Stage) toolGrade.getScene().getWindow();
			window.setScene(nextScene);
			window.show();
		});
	}

	/**
	 * This function closes an ongoing exam selected from a table and sends a request to the server to
	 * close it.
	 * 
	 * @param event An ActionEvent object that represents the user's action of clicking a button or menu
	 * item that triggers the closeExam() method.
	 */
	@FXML
	public void closeExam(ActionEvent event) {
		Exam exam = ongoingExamsTable.getSelectionModel().getSelectedItem();
		if (exam == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No exam selected");
			alert.setContentText("Please select an exam to close");
			alert.showAndWait();
			return;
		}
		Request request = new Request("closeExam", exam);
		try {
			client.openConnection();
			client.sendToServer(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setLecturerAndClient(Users lecturer, ChatClient client) {
		this.lecturer = lecturer;
		this.client = client;
	}

	/**
	 * This function sends a request to the server to retrieve ongoing exams for a lecturer.
	 */
	@FXML
	public void getOngoingExamsTable() {

		Request request = new Request("getOngoingExams", lecturer);
		try {
			client.openConnection();
			client.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * This Java function updates an ongoing exams table with a given ArrayList of Exam objects.
	 * 
	 * @param exam An ArrayList of Exam objects that is used to update the data displayed in the
	 * ongoingExamsTable
	 */
	@FXML
	public void setOngoingExamsTable(ArrayList<Exam> exam) {
		System.out.println("worked should update");
		ongoingExamsTable.getItems().clear();
		ongoingExamsTable.getItems().addAll(exam);
	}

	/**
	 * This Java function sets the ongoing exams to the given ArrayList of Exam objects.
	 * 
	 * @param exams The parameter "exams" is an ArrayList of objects of type "Exam". This method sets the
	 * value of the instance variable "ongoingExams" to the value of the "exams" parameter.
	 */
	public void setExams(ArrayList<Exam> exams) {
		this.ongoingExams = exams;
	}

	public ArrayList<Exam> getExams() {
		return ongoingExams;
	}

	/**
	 * This function loads a new FXML file for starting an exam and sets the scene for the new window.
	 * 
	 * @param event An ActionEvent object that represents the user's action that triggered the method
	 * call.
	 */
	@FXML
	public void startExam(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/start_exam.fxml")); // specify the path to the
		// new fxml file
		Parent parent = null;
		try {
			parent = loader.load();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		StartExamController controller = loader.getController();
		controller.setClientAndLecturer(client, lecturer);
		controller.getExamsTable();
		client.setController(controller);

		// This line gets the Stage information
		Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		window.setScene(nextScene);
		window.show();
	}

	/**
	 * This function logs out the user, sends a request to the server, loads the login screen, sets the
	 * client and controller, and displays the new scene.
	 * 
	 * @param event The ActionEvent that triggered the logOut method, which could be a button click or
	 * other user interaction.
	 */
	@FXML
	public void logOut(ActionEvent event) {
		LogInInfo loginData = new LogInInfo(lecturer.getUserName(), lecturer.getPassword());
		try {
			client.openConnection();
			if (client.isConnected()) {
				client.sendToServer(new Request("LOGOUT", loginData));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Not connected to server.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScreen.fxml")); // specify the path to the
		// new fxml file
		Parent parent = null;
		try {
			parent = loader.load();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		LoginScreenController controller = loader.getController();
		controller.setClient(this.client, controller);

		// Get the Stage information
		Stage window = (Stage) logOutBtn.getScene().getWindow();
		window.setScene(nextScene);
		window.show();
	}

	/**
	 * This function loads a new FXML file and sets it as the scene for the current stage, passing a
	 * ChatClient instance to the new scene's controller.
	 * 
	 * @param event The ActionEvent that triggered the method, which is usually a button click or key
	 * press event.
	 */
	@FXML
	public void showCreateQuestionForm(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/write_question.fxml")); // specify the path to
		// the new fxml file
		Parent parent = null;
		try {
			parent = loader.load();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		writeQuestionController controller = loader.getController();
		controller.setClientAndLecturer(this.client, lecturer);
		client.setController(controller);
		// Get the Stage information
		Stage window = (Stage) writeQuestionBtn.getScene().getWindow();
		window.setScene(nextScene);
		window.show();

	}

	/**
	 * This function loads a new FXML file for creating an exam and sets the scene to display it.
	 * 
	 * @param event The ActionEvent that triggered the method, which is usually a button click or key
	 * press event.
	 */
	@FXML
	public void showCreateExamForm(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/create_exam.fxml")); // specify the path to
		// the new fxml file
		Parent parent = null;

		try {
			parent = loader.load();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		createExamController controller = loader.getController();
		controller.setClientAndLecturer(this.client, lecturer);
		client.setController(controller);
		// Get the Stage information
		Stage window = (Stage) CreateExamBtn.getScene().getWindow();
		window.setScene(nextScene);
		window.show();

	}

	/**
	 * This function loads a new FXML file and sets up a new scene with a TimeRequestController, passing
	 * in necessary data.
	 * 
	 * @param event The event that triggered the method, which is an ActionEvent in this case. It is not
	 * used in the method implementation.
	 */
	@FXML
	public void showTimeRequestForm(ActionEvent event) {
		Exam exam = ongoingExamsTable.getSelectionModel().getSelectedItem();
		if (exam == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No exam selected");
			alert.setContentText("Please select an exam from the table");
			alert.showAndWait();
			return;
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/time_request.fxml")); // specify the path to
		// the new fxml file
		Parent parent = null;

		try {
			parent = loader.load();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Scene nextScene = new Scene(parent);

		// Get the new scene's controller and pass the ChatClient instance to it
		TimeRequestController controller = loader.getController();
		controller.setClientAndLecturer(this.client, lecturer);
		controller.setExam(exam);
		client.setController(controller);
		// Get the Stage information
		Stage window = (Stage) TimeRequestBtn.getScene().getWindow();
		window.setScene(nextScene);
		window.show();
	}

}
