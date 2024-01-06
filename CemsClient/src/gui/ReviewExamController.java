package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Exam;
import logic.Question;
import logic.Request;

/**
 * This is a Java class for a review exam controller that initializes a chat client and an exam object.
 */
public class ReviewExamController implements Initializable {

    private ChatClient client;
    private Exam exam;
    @FXML
    private TableView<Question> ExamQuestionTable;

    @FXML
    private TableColumn<Question, String> answer1;

    @FXML
    private TableColumn<Question, String> answer2;

    @FXML
    private TableColumn<Question, String> answer3;

    @FXML
    private TableColumn<Question, String> answer4;

    @FXML
    private Text course;

    @FXML
    private Pane createQuestionForm;

    @FXML
    private TextArea lecturetComments;

    @FXML
    private TableColumn<Question, Integer> questionId;

    @FXML
    private TableColumn<Question, String> questionText;

    @FXML
    private Button saveExamBtn;

    @FXML
    private TableColumn<Question, Integer> score;

    @FXML
    private TextArea studentComments;

    @FXML
    private Text subject;

    @FXML
    private Label testIdLabel;

    @FXML
    private Text testTiime;
    @FXML
    private Label errLabel;
    @FXML
    private Button backBtn;


   /**
    * This function loads the create_exam.fxml file and sets it as the main scene when the goBack
    * button is clicked.
    * 
    * @param event The event parameter is an object that represents the event that triggered the method
    * call. In this case, it is most likely a button click event on the "back" button.
    */
    @FXML
    public void goBack(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/create_exam.fxml")); // specify the path to the
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
		createExamController controller = loader.getController();
		controller.setClientAndLecturer(client,exam.getLecturer());
        client.setController(controller);
		// Get the Stage information
		Stage window = (Stage) backBtn.getScene().getWindow();
		window.setScene(mainScene);
		window.show();

    }


   /**
    * This function sets the text of a label to "Exam saved successfully".
    */
    public void setLabel() {
        Platform.runLater(() -> {
        errLabel.setText("Exam saved successfully");
        });
    }

   /**
    * This function saves the lecturer and student comments for an exam and sends a request to the
    * server to save the exam.
    * 
    * @param event The event that triggered the method, which is an ActionEvent in this case.
    */
    @FXML
    void saveExam(ActionEvent event) {
        exam.setLecturerComments(lecturetComments.getText());
        exam.setStudentComments(studentComments.getText());
        try {
            client.sendToServer(new Request("saveExam", exam));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * This function sets the data table with information about an exam.
     */
    public void setDataTable() {
        Platform.runLater(() -> {
        ExamQuestionTable.getItems().addAll(exam.getQuestions());
        course.setText(exam.getCourseName());
        testTiime.setText(String.valueOf(exam.getTestTime()));
        subject.setText(exam.getSubject());
        });

    }
   /**
    * This function initializes the cell value factories for a table view in a Java program.
    * 
    * @param location The location of the FXML file that contains the UI layout for the controller
    * class.
    * @param resources The ResourceBundle object that contains the localized resources for the current
    * user's locale. It can be used to retrieve localized strings, images, and other resources.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        questionId.setCellValueFactory(new PropertyValueFactory<>("questionID"));
        questionText.setCellValueFactory(new PropertyValueFactory<>("questionDescription"));
        answer1.setCellValueFactory(new PropertyValueFactory<>("answer1"));
        answer2.setCellValueFactory(new PropertyValueFactory<>("answer2"));
        answer3.setCellValueFactory(new PropertyValueFactory<>("answer3"));
        answer4.setCellValueFactory(new PropertyValueFactory<>("answer4"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

    }

    /**
     * This function sets the ChatClient object for the current instance.
     * 
     * @param client The parameter "client" is an object of the class ChatClient. The method
     * "setClient" sets the value of the instance variable "client" to the value passed as a parameter.
    
     */
    public void setClient(ChatClient client) {
        this.client = client;
    }
    // The `setExam` method is a setter method that sets the value of the instance variable `exam` to
    // the value passed as a parameter. It takes an object of the class `Exam` as a parameter and
    // assigns it to the instance variable `exam`.
    public void  setExam(Exam exam) {
        this.exam = exam;
    }
}
