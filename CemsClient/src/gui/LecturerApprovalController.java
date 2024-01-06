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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Exam;
import logic.Request;
import logic.Users;

/**
 * The LecturerApprovalController class initializes a chat client and manages a list of exams for a
 * specific lecturer to approve.
 */
public class LecturerApprovalController implements Initializable {

    private ChatClient client;
    private Users lecturer;
    private ArrayList<Exam> exams;

    @FXML
    private Label errLabel;

    @FXML
    private Button backBtn;
    @FXML
    private Button approveBtn;

    @FXML
    private Button changeBtn;

    @FXML
    private TableColumn<Exam, String> courseName;

    @FXML
    private TableColumn<Exam, Integer> examId;

    @FXML
    private TableView<Exam> examsTable;

    @FXML
    private TableColumn<Exam, Integer> grade;

    @FXML
    private TableColumn<Exam, Integer> studentId;

    @FXML
    private TextField newGrade;

    @FXML
    private TextArea changeGradeReason;

    @FXML
	private Text toolCreateExams;

	@FXML
	private Text toolGrade;

	@FXML
	private Text toolStatistics;

	@FXML
	private Text toolWriteQuestions;
    @FXML
    // Gets the selected exam from the table view,
    // and if there is no selection, it displays an error message. If an exam is selected, it creates a
    // `Request` object with the action "approveGrade" and the selected exam as its parameter, and
    // sends it to the server.

    void approveGrade(ActionEvent event) {
        Exam exam = examsTable.getSelectionModel().getSelectedItem();
        if (exam == null) {
            errLabel.setText("Please select an exam");
            return;
        }
        Request request = new Request("approveGrade", exam);
        try {
            client.openConnection();
            client.sendToServer(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exams.remove(exam);

    }

   /**
    * This function changes the grade of a selected exam and sends a request to the server with the
    * updated information.
    * 
    * @param event An ActionEvent object that represents the user's action that triggered the method
    * call.
    */
    @FXML
    void changeGrade(ActionEvent event) {
        Exam exam = examsTable.getSelectionModel().getSelectedItem();
        String newGrade = this.newGrade.getText();
        if (exam == null) {
            errLabel.setText("Please select an exam");
            return;
        }
        if (newGrade == null || newGrade.isEmpty()) {
            errLabel.setText("Please enter a new grade");
            return;
        }
        if (newGrade.matches("[0-9]+") == false || Integer.parseInt(newGrade) > 100
                || Integer.parseInt(newGrade) < 0) {
            errLabel.setText("Please enter a valid grade");
            return;
        }
        if (changeGradeReason == null || changeGradeReason.getText().isEmpty()) {
            errLabel.setText("Please enter a reason for the change");
            return;
        }
        exam.setGrade(Integer.parseInt(newGrade));
        exam.setChangeGradeReason(changeGradeReason.getText());
        Request request = new Request("changeGrade", exam);
        try {
            client.openConnection();
            client.sendToServer(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exams.remove(exam);

    }

    /**
     * This function sends a request to the server to get grades for a specific lecturer.
     */
    public void getGrades() {
        Request request = new Request("getGrades", lecturer);

        try {
            client.openConnection();
            client.sendToServer(request);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This function sets the grades table with a list of exams and updates the UI.
     * 
     * @param exams An ArrayList of Exam objects that contains the data to be displayed in a table.
     */
    public void setGradesTable(ArrayList<Exam> exams) {
        Platform.runLater(() -> {
            this.exams = exams;
            examsTable.getItems().clear();
            examsTable.getItems().addAll(exams);
        });
    }

    /**
     * This function sets the client and lecturer for a chat session.
     * 
     * @param client An object of the ChatClient class that represents the client who is setting the
     * lecturer.
     * @param lecturer The lecturer parameter is an instance of the Users class, which represents a
     * user in the chat system. It likely contains information such as the user's name, ID, and other
     * relevant details.
     */
    public void setClientAndLecturer(ChatClient client, Users lecturer) {
        this.client = client;
        this.lecturer = lecturer;

    }

   /**
    * The function initializes the table columns and sets mouse click events for various tools.
    * 
    * @param location The location of the FXML file that contains the UI layout for the controller
    * class.
    * @param resources A bundle of resources, such as strings or images, that can be used by the
    * application. It is typically used for internationalization and localization purposes.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        examId.setCellValueFactory(new PropertyValueFactory<>("examId"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
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
     * This function loads the main screen FXML file and sets the scene to it, passing the ChatClient
     * and lecturer instances to the controller.
     * 
     * @param event The ActionEvent that triggered the method call. It contains information about the
     * event, such as the source of the event and any additional data associated with it.
     */
    @FXML
    public void goBack(ActionEvent event) {
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
   * The function returns an ArrayList of Exam objects.
   * 
   * @return An ArrayList of Exam objects is being returned.
   */
    public ArrayList<Exam> getExams() {
        return exams;
    }

    /**
     * This function sets the list of exams for a particular object.
     * 
     * @param exams an ArrayList of Exam objects that is being set as the value of the "exams" instance
     * variable in the current object.
     */
    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    /**
     * This function updates the error label and sets the grades table for a successful grade approval.
     */
    public void approveGradeSuccess() {
        Platform.runLater(() -> {
            errLabel.setText("Grade approved successfully");
            setGradesTable(exams);
        });

    }

    /**
     * This function updates the error label and sets the grades table for exams.
     */
    public void changeGradeSuccess() {
        Platform.runLater(() -> {
            errLabel.setText("Grade changed successfully");
            setGradesTable(exams);
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

}