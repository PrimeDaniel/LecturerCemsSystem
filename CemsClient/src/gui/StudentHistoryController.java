package gui;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import client.ChatClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.Request;
import logic.StudentData;

import logic.Users;

/**
 * this class is responsble  for controlling the students Grade screen 
 */

public class StudentHistoryController {

    private ArrayList<StudentData> studentGradesInfo = new ArrayList<>();
	private Users student;
	private ChatClient client;
    
	/**
	 * sets the grade data array that would be used later
	 * @param data represents the grade detail includes course name and grade
	 */
	public void setStudentDataList(ArrayList<StudentData>  data) {
		this.studentGradesInfo=data ;
	   }
	
    @FXML
    private TableView<StudentData> TestTableView;

    @FXML
    private Button btnBacktoHome;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblGPA;

    @FXML
    private TableColumn<StudentData, String> tbcCourse;

    @FXML
    private TableColumn<StudentData, String> tbcGrade;

    
    
    @FXML
	/**
	 * closes the current screen and returns to the home page
	 * @param event
	 */
    void ReturnBack(ActionEvent event) {
    	Stage currentStage = (Stage) btnBacktoHome.getScene().getWindow();
        currentStage.close();
    }

    /**
	 * sets the student,client and controller that would be used in this form
	 * 
	 * @param Student An object of the class Users representing a student.
	 * @param client  represents the connection between user and server
	 * @param SH SH is an instance of the StudentHistoryController class, 
	 * which responsible for controlling the student's grade screen 
	 */
	public void setStudentAndClient(Users Student,ChatClient client,StudentHistoryController SH) {
    	this.student=Student;
    	this.client=client;
    	this.client.setStudentHistoryController(SH);
    }
    
  
   


	/**
	 * method the sets up the table view in the page, gets from the server all the grade data
	 * of a student and organized it in the table
	 */
     public void viewPage() {
    	
    	
    		Platform.runLater(() -> {
		     //System.out.println("client is not connected");
				 ObservableList<StudentData> odata= FXCollections.observableArrayList();
		    	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");  
		          LocalDateTime now = LocalDateTime.now();  
		          lblDate.setText("Date : " + dtf.format(now));
		          tbcCourse.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
		          tbcGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
				  for(int i=0 ; i<studentGradesInfo.size();i++) {
						odata.add(studentGradesInfo.get(i));
				  }
		    	  TestTableView.setItems(odata);
		    	  CalculateGPA();
    		 });
		    	     
			
		
    	 
    }
    
    
/**
 * based on the data in the table, calculates the average grade of the logged user
 */
    public void CalculateGPA()
    {
    	 ObservableList<StudentData> data=TestTableView.getItems();
    	 double total=0;
    	 int count=0;
    	 int tempGrade=0;
    	 
    	 for (StudentData sd : data)
    	 {
    		 if (sd.getGrade()!="---")
    		 { 
    			 if( !sd.getGrade().matches("[0-9]+")) {
    				 tempGrade=0;
    			 }
    			 else {
    				 tempGrade=Integer.parseInt(sd.getGrade());
    			 }
    			 total+=tempGrade;
    			 count++;
    		 }
    	 }
    	 double average = (count > 0) ? total / count : 0.0;
    	    lblGPA.setText("Average GPA: " + average);
    }
    
   /**
	* The function returns an instance of the StudentHistoryController class.
	* 
	* @return An instance of the `StudentHistoryController` class is being returned.
	*/
    public StudentHistoryController getController() {
		return this;
	}
    
}
