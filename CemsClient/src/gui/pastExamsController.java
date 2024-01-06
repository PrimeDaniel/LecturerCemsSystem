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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Exam;
import logic.Request;
import logic.Users;

/**
 * The "pastExamsController" class initializes a chat client, a lecturer user, and an array list of
 * past exams.
 */
public class pastExamsController implements Initializable {

    private ChatClient client;
    private Users lecturer;
    private ArrayList<Exam> exams;

    @FXML
    private Button backBtn;
    @FXML
    private TableColumn<Exam, Integer> cheat;

    @FXML
    private TableColumn<Exam, Integer> code;

    @FXML
    private TableColumn<Exam, String> dateEnd;

    @FXML
    private TableColumn<Exam, String> dateStart;

    @FXML
    private TableColumn<Exam, Integer> examId;

    @FXML
    private TableView<Exam> pastExamsTable;

    @FXML
    private TableColumn<Exam, Integer> studentsNumber;

    @FXML
    private TableColumn<Exam, Integer> testTime;
    
	@FXML
	private Text toolCreateExams;

	@FXML
	private Text toolGrade;

	@FXML
	private Text toolStatistics;


	@FXML
	private Text toolWriteQuestions;

    @Override
    // `public void initialize(URL location, ResourceBundle resources)` is a method that is called when
	// the FXML file is loaded. It initializes the GUI components and sets up event handlers for the
	// toolbar buttons. It also sets up the table columns for the past exams table.
	public void initialize(URL location, ResourceBundle resources) {
        examId.setCellValueFactory(new PropertyValueFactory<>("examId"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        testTime.setCellValueFactory(new PropertyValueFactory<>("testTime"));
        dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        dateEnd.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        cheat.setCellValueFactory(new PropertyValueFactory<>("cheat"));
        studentsNumber.setCellValueFactory(new PropertyValueFactory<>("studentsNumber"));
        
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

    // This method clears the existing items in the table and then sets the
	// new items using the `setAll()` method. 

	public void setExamsTable(ArrayList<Exam> exams) {
		Platform.runLater(() -> {
        this.exams = exams;
        pastExamsTable.getItems().clear();
        pastExamsTable.getItems().setAll(exams);
		});
    }
    public void getPastExams(){
        Request request = new Request("getPastExams", lecturer);
        try {
            client.openConnection();
            client.sendToServer(request);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    // `void backToMainScreen(ActionEvent event)` is a method that is called when the user clicks on
	// the "back" button in the GUI. It loads the main screen FXML file, sets the lecturer and client
	// instances to the main screen controller, gets the ongoing exams table, and sets the controller
	// for the client. Finally, it gets the Stage information and sets the scene to the main screen.
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
