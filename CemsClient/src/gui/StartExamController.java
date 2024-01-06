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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Exam;
import logic.Request;
import logic.Users;

/**
 * This is a Java class that initializes and controls a GUI interface for displaying exams and related
 * information.
 */
public class StartExamController implements Initializable {
    private ChatClient client;
    private Users lecturer;
    private ArrayList<Exam> exams;
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
    private TextField fourDigitCode;
    @FXML
    private Text toolCreateExams;

    @FXML
    private Text toolGrade;

    @FXML
    private Text toolStatistics;

    @FXML
    private Text toolWriteQuestions;

    /**
     * This function checks if a user has selected an exam and entered a valid 4 digit code before
     * sending a request to start the exam to the server.
     * 
     * @param event An object representing the event that triggered the method call, such as a button
     * click or key press.
     */
    @FXML
    void startExamBtn(ActionEvent event) {
        Exam exam = examsTable.getSelectionModel().getSelectedItem();
        if (exam == null) {
            label.setText("Please select an exam");
            return;
        }
        if (!fourDigitCode.getText().matches("[0-9]+")) {
            label.setText("Please enter a 4 digit code only numbers");
            return;
        }
        if (fourDigitCode.getText().trim().length() != 4) {
            label.setText("Please enter a 4 digit code");
            return;
        }
        try {
            client.openConnection();
            Exam openExam = new Exam(exam.getExamId(), Integer.parseInt(fourDigitCode.getText()), exam.getTestTime());
            client.sendToServer(new Request("startExam", openExam));
        } catch (Exception e) {
            label.setText("Couldnt send to server");
        }
    }

    /**
     * The function initializes the table columns and sets mouse click events for various tools.
     * 
     * @param location The location of the FXML file that contains the UI layout for this controller.
     * @param resources A ResourceBundle object that contains the resources for the current locale. It
     * is used to retrieve localized strings and other resources.
     */
    @Override
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
    * This function sends a request to the server to retrieve a table of exams for a specific lecturer.
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

    public void setClientAndLecturer(ChatClient client, Users lecturer) {
        this.client = client;
        this.lecturer = lecturer;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    /**
     * This function loads the main screen FXML file and sets it as the current scene in the Stage.
     * 
     * @param event The event that triggered the method, which is an ActionEvent in this case. It is
     * not used in the method implementation.
     */
    @FXML
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
 * This function updates a label to display a success message when an exam is started.
 */

    public void startExamSuccess() {
        Platform.runLater(() -> {
            label.setText("Exam started successfully");
        });

    }

    /**
     * This function sets the text of a label to "Exam already started with this code" using JavaFX's
     * Platform.runLater() method.
     */
    public void startExamFailed() {
        Platform.runLater(() -> {
            label.setText("Exam already started with this code");
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
