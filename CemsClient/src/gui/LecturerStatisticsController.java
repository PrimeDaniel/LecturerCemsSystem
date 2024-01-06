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
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Exam;
import logic.Request;
import logic.Users;

/**
 * The LecturerStatisticsController class is a Java class that represents the controller for a GUI
 * screen that displays statistics for exams taken by a lecturer.
 */
public class LecturerStatisticsController implements Initializable {
	private ChatClient client;
	private Users lecturer;
	private ArrayList<Exam> exams;
	private ArrayList<Exam> gradesArr;
	private float GPA;
	private float MEDIAN;
	@FXML
	private TableColumn<Exam, String> courseName;

	@FXML
	private TableColumn<Exam, Integer> examId;

	@FXML
	private TableView<Exam> examsTable;

	@FXML
	private TableColumn<Exam, String> lComments;

	@FXML
	private Label label;

	@FXML
	private TableColumn<Exam, String> sComments;

	@FXML
	private TableColumn<Exam, Integer> testTime;

	@FXML
	private Button backBtn;

	@FXML
	private TextField gpa;
	@FXML
	private TextField median;
	@FXML
	private Text toolCreateExams;

	@FXML
	private Text toolGrade;

	@FXML
	private Text toolStatistics;


	@FXML
	private Text toolWriteQuestions;

	@FXML
	// The above code is a method in Java that is triggered by an ActionEvent. It is likely part of a
	// larger program or application. The specific functionality of the method is not clear without seeing
	// the implementation code.
	void viewStats(ActionEvent event) {
		Exam exam = examsTable.getSelectionModel().getSelectedItem();
		if (exam == null) {
			label.setText("Please select an exam");
			return;
		}
		calculateGpa();
		calculateMedian();


		Platform.runLater(()->{
			gpa.setText(String.valueOf(GPA));
			median.setText(String.valueOf(MEDIAN));
		});

	}

	@Override
	// The `initialize` method is a method that is called automatically when the FXML file is loaded. It
	// is used to initialize the controller and set up any necessary components or data. In this specific
	// code, the method sets up the columns for the exams table, sets up the event handlers for the
	// toolbar buttons, and initializes the GPA and median text fields to be empty.
	public void initialize(URL location, ResourceBundle resources) {

		examId.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("examId"));
		courseName.setCellValueFactory(new PropertyValueFactory<Exam, String>("courseName"));
		lComments.setCellValueFactory(new PropertyValueFactory<Exam, String>("lecturerComments"));
		sComments.setCellValueFactory(new PropertyValueFactory<Exam, String>("studentComments"));
		testTime.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("testTime"));

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

	/**
	 * This function sets the items in an exams table to a given ArrayList of Exam objects.
	 * 
	 * @param exam An ArrayList of Exam objects that will be used to populate a table view called
	 * "examsTable". The method first clears any existing items in the table view and then adds all the
	 * Exam objects from the ArrayList to the table view.
	 */
	public void setExamsTable(ArrayList<Exam> exam) {
		examsTable.getItems().clear();
		examsTable.getItems().addAll(exam);
	}


	/**
	 * This function sends a request to the server to retrieve exams by a specific lecturer.
	 */
	public void getExamsTable() {

		Request request = new Request("getExamsByLecturer", lecturer);
		try {
			client.openConnection();
			client.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	/**
	 * This function sets the list of exams for a particular object.
	 * 
	 * @param exams The parameter "exams" is an ArrayList of objects of type "Exam". This method sets the
	 * value of the instance variable "exams" to the value of the parameter "exams".
	 */
	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

	/**
	 * This function returns an ArrayList of Exam objects.
	 * 
	 * @return An ArrayList of Exam objects is being returned.
	 */
	public ArrayList<Exam> getExams() {
		return exams;
	}

	/**
	 * This Java function sets the text of a label.
	 * 
	 * @param label The label parameter is a String that represents the text to be set on a label
	 * component. The method setLabel() takes this parameter and sets the text of the label component to
	 * the specified value.
	 */
	public void setLabel(String label) {
		this.label.setText(label);
	}

	@FXML
	// This function is called when the "back" button is clicked and it loads the main screen for the
	// lecturer. It uses FXMLLoader to load the FXML file for the main screen, sets the lecturer and
	// client instances for the controller of the main screen, gets the ongoing exams table, and sets the
	// controller for the ChatClient instance. Finally, it gets the Stage information and sets the scene
	// to the main screen.
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

	public void startExamSuccess() {
		Platform.runLater(() -> {
			label.setText("Exam started successfully");
		});

	}

	public void startExamFailed() {
		Platform.runLater(() -> {
			label.setText("Exam already started with this code");
		});
	}
/**
 * This function sends a request to the server to get grades and sets a flag for the lecturer.
 */

	public void getGrades() {
		lecturer.setFlag(1);
		Request request = new Request("getGrades", lecturer);

		try {
			client.openConnection();
			client.sendToServer(request);
			lecturer.setFlag(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setGradesArr(ArrayList<Exam> gradesArr) {
		this.gradesArr = gradesArr;
	}

	/**
	 * This function calculates the GPA by summing up the grades of all exams and dividing by the total
	 * number of exams.
	 */
	public void calculateGpa() {
		int sum = 0;
		for (Exam exam : gradesArr) {
			sum += exam.getGrade();
		}
		GPA = sum / gradesArr.size();
	}

	/**
	 * This function calculates the median of a list of grades.
	 */
	/**
	 * This function calculates the median of a list of grades.
	 */
	public void calculateMedian(){
		int medianIndex = gradesArr.size() / 2;
		if (gradesArr.size() % 2 == 0) {
			MEDIAN = (gradesArr.get(medianIndex).getGrade() + gradesArr.get(medianIndex - 1).getGrade()) / 2;
		} else {
			MEDIAN = gradesArr.get(medianIndex).getGrade();
		}
	}
	// update all toolsBar
		/**
		 * This function loads a new scene for writing a question and sets the controller and client for it.
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
		 * This function loads a new scene for a grade tool and sets the controller and client for the new
		 * scene.
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
		 * This function loads a new scene for creating an exam and sets the controller and client for the
		 * scene.
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
		 * This function loads a new scene for a statistics tool in a Java application.
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
		
		// end toolsBar

}
