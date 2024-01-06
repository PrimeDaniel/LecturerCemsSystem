package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.TreeSet;

import client.ChatClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.ExamHD;
import logic.Grades;
import logic.LogInInfo;
import logic.Request;
import logic.RequestTime;
import logic.Users;
import ocsf.client.*;

/**
 * The HDController class initializes variables for a chat client, request time, users, and arrays for
 * information selection and general grades.
 */
public class HDController implements Initializable {
	private ChatClient client;
	private RequestTime requestTime;
	private Users HOD;
	private TreeSet<String> infoSelecttionArray = new TreeSet<String>();
	private String whoToShow;
	private String test;

	private ArrayList<String> generalGradesArray = new ArrayList<String>();

	/**
	 * This function sets the head of department and chat client for a given user.
	 * 
	 * @param Hod This parameter is of type "Users" and represents the Head of Department that we want to
	 * set for a particular object instance.
	 * @param client The "client" parameter is an object of the "ChatClient" class, which is likely used
	 * to establish a connection between the user and the chat system. It could contain information such
	 * as the user's login credentials, chat history, and other settings related to the chat client.
	 */
	public void SetHeadOfDepartment(Users Hod, ChatClient client) {
		this.HOD = Hod;
		this.client = client;
	}

	/**
	 * This is a setter method in Java that sets the value of a variable named "test".
	 * 
	 * @param test The parameter "test" is a String variable that is being set to the value passed as an
	 * argument to the method. The "this" keyword is used to refer to the instance variable "test" of the
	 * current object. This method is a setter method that sets the value of the "test"
	 */
	public void setTest(String test) {
		this.test = test;
	}

	/**
	 * This function sets the value of a class variable "generalGradesArray" to an ArrayList of Strings
	 * passed as a parameter.
	 * 
	 * @param generalGradesArray An ArrayList of Strings that represents the general grades array that
	 * needs to be set for an object. This method sets the value of the generalGradesArray instance
	 * variable of the object to the value passed as a parameter.
	 */
	public void setGeneralArray(ArrayList<String> generalGradesArray) {
		this.generalGradesArray = generalGradesArray;
	}

	@FXML
	private Label emptyLecturerIDLabel;
	@FXML
	private Label emptyCourseIDLabel;
	@FXML
	private Label emptyStudentIDLabel;
	@FXML
	private ResourceBundle resources;
	@FXML
	private Label sdlabelStudent;
	@FXML
	private Label sdlabellecturer;
	@FXML
	private Label sdlabelCourse;
	@FXML
	private TextField sdlabelStudentnumber;
	@FXML
	private TextField sdlabelLecturerNumber;
	@FXML
	private TextField sdlabelCourseNumber;
	@FXML
	private TextField sdlabelStudentnumber1;

	@FXML
	private TextField sdlabelCourseNumber1;
	@FXML
	private TextField Median_LECTextArea1;

	@FXML
	private TextField GPA_STUtextArea1;
	@FXML
	private TextField median_STUtextArea1;

	@FXML
	private TextField GPA_GradestextArea1;
	@FXML
	private TextField Median_GradestextArea1;

	@FXML
	private Label testlbl;
	@FXML
	private Label gPAlbl;
	@FXML
	private Label medianlbl;
	@FXML
	private Label selectionlbl;
	@FXML
	private Label checkinfolLabel;
	@FXML
	private TextField gpaTxtField;
	@FXML
	private TextField medianTxtField;
	@FXML
	private TextField selectionNameTxtField;// use for the name of the selected parameter

	@FXML
	private URL location;

	@FXML
	private Button LogOutBTN;

	public Button getLogOutBTN() {
		return LogOutBTN;
	}

	@FXML
	private TableView<RequestTime> requestTable;

	@FXML
	private Button ApproveBTN;
	@FXML
	private Button studentResetBTN;
	@FXML
	private Button lecturerResetBTN;
	@FXML
	private Button courseResetBTN;
	@FXML
	private TextField RequestExamIDText;

	@FXML
	private Button refreshButton;

	@FXML
	private Button rejectBTN;

	public Button getRejectBTN() {
		return rejectBTN;
	}

	@FXML
	private Button ShowStudentBTN;
	@FXML
	private Button Showcoursetbl;

	@FXML
	private TextField GPA_LECtextArea;
	@FXML
	private TextField StudentName;
	@FXML
	private TextField LectuerName;

	@FXML
	private TextField LectuerID;
	@FXML
	private TextField Median_LECTextArea;

	@FXML
	private Button ShowLectuerBTN;
	@FXML
	private Button ShowCourseBTN;

	@FXML
	private TextField GPA_STUtextArea;

	@FXML
	private TextField median_STUtextArea;
	@FXML
	private TextField sdlabelLecturerNumber1;

	@FXML
	private TextField ID_STUtextArea;

	@FXML
	private TextField GPA_GradestextArea;

	@FXML
	private TextField median_GradesTextArea;
	@FXML
	private TextField median_GradesTextArea1;

	@FXML
	private TextField ID_GradetextArea;
	@FXML
	private TextField EnterLectuerName;
	@FXML
	private TextField CourseName;
	@FXML
	private TextField EnterCourseID;
	@FXML
	private TextField secondStudentID;
	@FXML
	private TextField secondLecturerID;
	@FXML
	private TextField secondCourseID;
	@FXML
	private TextField GPA_LECtextArea1;

	@FXML
	private TableColumn<RequestTime, String> IDColumn;
	@FXML
	private TableColumn<RequestTime, String> ExamIdColumn;
	@FXML
	private TableColumn<RequestTime, String> RequestedByColumn;
	@FXML
	private TableColumn<RequestTime, Integer> ExtraTimeColumn;
	@FXML
	private TableColumn<RequestTime, String> ReasonColumn;
	@FXML
	private BarChart<String, Number> barChartLec;
	@FXML
	private BarChart<String, Number> barChartStud;
	@FXML
	private BarChart<String, Number> barChartCourse;
	@FXML
	private BarChart<String, Number> generalScreenBarChart;
	@FXML
	private BarChart<String, Number> testBarChart;
	@FXML
	private TextField NumberOfTestLectuer;
	@FXML
	private TextField NumberOfTestStudent;
	@FXML
	private TextField NumberOfTestCourse;
	@FXML
	private Button comparebtn;
	@FXML
	private TextField id1;
	@FXML
	private TextField id2;
	@FXML
	private Label tt22;
	@FXML
	private AnchorPane anchorpane;
	@FXML
	private Button showAllLecbtn;
	@FXML
	private Button showAllcoursebtn;

	@FXML
	private Button showAllstudentbtn;
	@FXML
	private Button showLecturertentsButton;
	@FXML
	private Button showStudentTenthsButton;
	@FXML
	private Button compareinLecturer;
	@FXML
	private Button compareinCourse;
	@FXML
	private Button compareinStudent;

	@FXML
	private Button showCourseTenthsButton;
	private int StudentCount = 0;
	private int LectuerCount = 0;
	private int CourseCount = 0;

	/**
	 * Data initialization on screens
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		barChartCourse.setTitle("test course");
		CategoryAxis xAxisCourse = (CategoryAxis) barChartCourse.getXAxis();
		xAxisCourse.setLabel("exam id");
		NumberAxis yAxisCourse = (NumberAxis) barChartCourse.getYAxis();
		yAxisCourse.setLabel("grade");

		IDColumn.setCellValueFactory(new PropertyValueFactory<>("requestID"));

		ExamIdColumn.setCellValueFactory(new PropertyValueFactory<>("examID"));

		RequestedByColumn.setCellValueFactory(new PropertyValueFactory<>("requestedBy"));

		ExtraTimeColumn.setCellValueFactory(new PropertyValueFactory<>("extraTime"));

		ReasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));

	}

	public void setClient(ChatClient client, HDController controller) {
		this.client = client;
		this.client.setController(controller); // also set the controller on the client
	}

	/**
	 * The function logs out the user and sends a logout request to the server.
	 * 
	 * @param event The event that triggered the method, which is an ActionEvent in this case. It is not
	 * used in the method implementation.
	 */
	@FXML
	public void ToLogOut(ActionEvent event) {
		Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScreen.fxml"));
			Parent root;
			try {
				root = loader.load();
				Stage window = (Stage) getLogOutBTN().getScene().getWindow();
				window.setScene(new Scene(root));
				window.show();
				LogInInfo loginData = new LogInInfo(HOD.getUserName(), HOD.getPassword());
				try {
					client.openConnection();
					if (client.isConnected()) {
						client.sendToServer(new Request("LOGOUT", loginData));
						try {
							Thread.sleep(2000);
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	/**
	 * The function updates the additional time for the test
	 * after the department head approves the request
	 * 
	 * @param requestList ArrayList<RequestTime>
	 * @return void
	 */

	@FXML
	public void UpdateRequestTime(ArrayList<RequestTime> requestList) {
		Platform.runLater(() -> {
			requestTable.getItems().clear();
			requestTable.getItems().addAll(requestList);
		});

	}

	/**
	 * The function is activated when the head of the department approves the time
	 * request,
	 * pops up a confirmation message and deletes the information from the DB
	 * 
	 * @param ActionEvent event
	 */
	@FXML
	public void ApproveRequestTime(ActionEvent event) {
		Request request = new Request("APPROVE", RequestExamIDText.getText());
		try {
			client.openConnection();
			if (client.isConnected()) {
				client.sendToServer(new Request("SearchExam", request));
				refreshTable(event);
				System.out.println("after approve refresh");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		refreshTable1();
		Platform.runLater(() -> {
			if (requestTable.getItems().size() == 1)
				requestTable.getItems().clear();
			refreshTable1();

		});

	}

	/**
	 * The function is activated when the head of the department rejects the time
	 * request,
	 * pops up a rejection message and deletes the information from the DB
	 */
	@FXML
	public void RejectRequestTime(ActionEvent event) {
		Request request = new Request("REJECT", RequestExamIDText.getText());
		try {
			client.openConnection();
			client.sendToServer(new Request("SearchExam", request));
			refreshTable(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.runLater(() -> {
			if (requestTable.getItems().size() == 1)
				requestTable.getItems().clear();
			refreshTable1();
			showpPopupReject();

		});
	}
	/*
	 * Show window with email\phone of the le cturer-the request rejected
	 */

	@FXML
	public void refreshTable(ActionEvent event) {

		refreshTable1();
	}

	/**
	 * while pressing refresh table ,connect to server and get the current time
	 * requests,and put them in the table
	 */
	public void refreshTable1() {

		try {

			client.openConnection();
			if (client.isConnected()) {

				client.sendToServer(new Request("GET-EXTRA-TIME-REQUEST", null));

			} else {
				System.out.println("Not connected to server.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * The function pops a message on the screen when the
	 * department head approves the request
	 * 
	 * @param String name that will be displayed in the poopup
	 */
	public void showpPopupApprove(String username) {

		System.out.println("in popupApprove");
		String lblstring = "Request Approve! " + username + "@braude.ac.il";
		Platform.runLater(() -> {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/approvePopup.fxml"));
				Parent popupContent = loader.load();
				ApprovePopupController popupController = loader.getController(); // Get the controller instance

				Popup popup = new Popup();
				popupController.setPopup(popup);
				popupController.setStr(lblstring);
				popupController.initialize(null, null);

				popup.getContent().add(popupContent);
				popup.show(ApproveBTN.getScene().getWindow());

			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	/**
	 * The function pops up a message on the screen that the request has
	 * been rejected as the head of the department clicks on rejecting a request
	 */
	public void showpPopupReject() {
		Platform.runLater(() -> {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/rejectpopup.fxml"));
				RejectPopUpController popupController = new RejectPopUpController();

				loader.setController(popupController);

				Parent popupContent = loader.load();
				Popup popup = new Popup();
				popupController.setPopup(popup);
				popup.getContent().add(popupContent);

				popup.show(rejectBTN.getScene().getWindow());
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	/**
	 * Display the information in a histogram according to division into tenths
	 * checks for input rror(empty fields)
	 * 
	 * @param ActionEvent event
	 *                    send to server the request for the grades of the required
	 *                    info' and then this will activate another function that
	 *                    will divide the grades into tenths
	 *                    using import__NAME__statisticstenths method
	 *                    the function wll call correct function according to the
	 *                    fxid of the button that calls it
	 */
	@FXML
	public void showtenths(ActionEvent event) {
		System.out.println("showtenths");
		Platform.runLater(() -> {
			try {
				client.openConnection();
				if (event.getSource() == showLecturertentsButton) {
					if (LectuerID.getText().isEmpty()) {// error handeling
						LectuerID.setStyle("-fx-border-color: rgb(255,0,0);-fx-border-width: 2px;");
						LectuerID.setPromptText("please enter lecturer ID");
						emptyLecturerIDLabel.setText("Please enter lecturer ID!");
						emptyLecturerIDLabel.setStyle("-fx-background-color:#F24444;-fx-background-radius: 100px;");
						emptyLecturerIDLabel.setVisible(true);
					} else {
						emptyLecturerIDLabel.setText("");// reset the style
						emptyLecturerIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
						emptyLecturerIDLabel.setVisible(false);
						LectuerID.setStyle("-fx-border-width: 0px;");
						if (client.isConnected()) {
							System.out.println("chose lecturer tenths");
							client.sendToServer(new Request("SendLectuerIDtenth", LectuerID.getText()));
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							System.out.println("Not connected to server.");
						}
					}
				}

				if (event.getSource() == showCourseTenthsButton) {
					if (ID_GradetextArea.getText().isEmpty()) {

						ID_GradetextArea
								.setStyle("-fx-border-color: rgb(255,0,0);-fx-border-width: 2px;");
						ID_GradetextArea.setPromptText("please enter Course ID");

						emptyCourseIDLabel.setText("Please enter Course ID!");
						emptyCourseIDLabel.setStyle("-fx-background-color:#F24444;-fx-background-radius: 100px;");
						emptyCourseIDLabel.setVisible(true);

					} else {
						emptyCourseIDLabel.setText("");
						emptyCourseIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
						emptyCourseIDLabel.setVisible(false);
						ID_GradetextArea.setStyle("-fx-border-width: 0px;");

						System.out.println("chose button correctly");
						client.openConnection();
						if (client.isConnected()) {
							client.sendToServer(new Request("SendCourseIDtenth", ID_GradetextArea.getText()));
							System.out.println("sent to server");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							System.out.println("Not connected to server.");
						}
					}
				}

				if (event.getSource() == showStudentTenthsButton) {
					if (ID_STUtextArea.getText().isEmpty()) {

						ID_STUtextArea
								.setStyle("-fx-border-color: rgb(255,0,0);-fx-border-width: 2px;");
						ID_STUtextArea.setPromptText("please enter Student ID");
						emptyStudentIDLabel.setText("Please enter Student ID!");
						emptyStudentIDLabel.setStyle("-fx-background-color:#F24444;-fx-background-radius: 100px;");
						emptyStudentIDLabel.setVisible(true);

					} else {
						emptyStudentIDLabel.setText("");
						emptyStudentIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
						emptyStudentIDLabel.setVisible(false);
						ID_STUtextArea.setStyle("-fx-border-width: 0px;");

						if (client.isConnected()) {
							client.sendToServer(new Request("SendStudentIDtenth", ID_STUtextArea.getText()));
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else {
							System.out.println("Not connected to server.");

						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	/**
	 * Delete all information from the course's information screen
	 * when you click the "Reset table" button
	 * reset styles
	 */
	@FXML
	public void resetTableCourse(ActionEvent event) {
		barChartCourse.getData().clear();
		// coureseIDsArray.clear();
		CourseCount = 0;
		median_GradesTextArea.setText("");
		GPA_GradestextArea.setText("");
		sdlabelCourseNumber.setText("");
		ID_GradetextArea.setText("");
		CourseName.setText("");
		emptyCourseIDLabel.setText("");
		emptyCourseIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
		emptyCourseIDLabel.setVisible(false);
		ID_GradetextArea.setStyle("-fx-border-width: 0px;");
		median_GradesTextArea1.setText("");
		GPA_GradestextArea1.setText("");
		sdlabelCourseNumber1.setText("");

		NumberOfTestCourse.setText("");

	}

	/**
	 * Delete all information from the lecturer's information screen
	 * when you click the "Reset table" button
	 * reset styles
	 */
	@FXML
	public void resetTableLecturer(ActionEvent event) {
		LectuerCount = 0;
		barChartLec.getData().clear();
		// lecturerIDsArray.clear();
		LectuerName.setText("");// show empty file text
		GPA_LECtextArea.setText("");// show empty file text
		Median_LECTextArea.setText("");// show empty file text
		LectuerID.setText("");
		sdlabelLecturerNumber.setText("");
		emptyLecturerIDLabel.setText("");
		emptyLecturerIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
		emptyLecturerIDLabel.setVisible(false);
		LectuerID.setStyle("-fx-border-width: 0px;");

		sdlabelLecturerNumber1.setText("");
		GPA_LECtextArea1.setText("");

		Median_LECTextArea1.setText("");
		NumberOfTestLectuer.setText("");

	}

	/**
	 * Delete all information from the student's information screen
	 * when you click the "Reset table" button
	 * reset styles
	 * 
	 * @param ActionEvent event
	 */
	@FXML
	public void resetTableStudent(ActionEvent event) {
		StudentCount = 0;
		barChartStud.getData().clear();
		// studentsIDsArray.clear();
		StudentName.setText("");// show empty file text
		GPA_STUtextArea.setText("");// show empty file text
		median_STUtextArea.setText("");// show empty file text
		sdlabelStudentnumber.setText("");
		ID_STUtextArea.setText("");
		emptyStudentIDLabel.setText("");
		emptyStudentIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
		emptyStudentIDLabel.setVisible(false);
		ID_STUtextArea.setStyle("-fx-border-width: 0px;");
		sdlabelStudentnumber.setText("");
		sdlabelStudentnumber1.setText("");
		GPA_STUtextArea.setText("");
		GPA_STUtextArea1.setText("");
		median_STUtextArea.setText("");
		median_STUtextArea1.setText("");
		NumberOfTestStudent.setText("");

	}

	/**
	 * The function is called by the "show statistics" button on
	 * each screen. The function brings information about grades according to
	 * the screen from which it was called
	 * 
	 * @param ActionEvent event
	 */
	@FXML
	public void ShowGradeStatistics(ActionEvent event) throws InterruptedException, IOException {

		try {
			client.openConnection();
			if (event.getSource() == ShowLectuerBTN) {
				if (LectuerID.getText().isEmpty()) {

					LectuerID.setStyle("-fx-border-color: rgb(255,0,0);-fx-border-width: 2px;");
					LectuerID.setPromptText("please enter lecturer ID");
					emptyLecturerIDLabel.setText("Please enter lecturer ID!");
					emptyLecturerIDLabel.setStyle("-fx-background-color:#F24444;-fx-background-radius: 100px;");
					emptyLecturerIDLabel.setVisible(true);

				}

				else {
					emptyLecturerIDLabel.setText("");
					emptyLecturerIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
					emptyLecturerIDLabel.setVisible(false);
					LectuerID.setStyle("-fx-border-width: 0px;");
					if (client.isConnected()) {
						client.sendToServer(new Request("SendLectuerID", LectuerID.getText()));
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("Not connected to server.");
					}
				}
			}
			if (event.getSource() == showAllcoursebtn) { // AllDataToCourse
				ID_GradetextArea.setText("");
				if (client.isConnected()) {
					client.sendToServer(new Request("SendCourseID", null));
					Thread.sleep(1000);

				} else {
					System.out.println("Not connected to server.");

				}
			}
			if (event.getSource() == showAllstudentbtn) { // AllDataToCourse
				ID_STUtextArea.setText("");
				if (client.isConnected()) {
					client.sendToServer(new Request("SendStudentID", null));
					Thread.sleep(1000);

				} else {
					System.out.println("Not connected to server.");

				}
			}
			if (event.getSource() == showAllLecbtn) { // AllDataToCourse
				LectuerID.setText("");
				if (client.isConnected()) {
					client.sendToServer(new Request("SendLectuerID", null));
					Thread.sleep(1000);

				} else {
					System.out.println("Not connected to server.");

				}
			}
			// ! check if fied is epmty if yes the put error
			if (event.getSource() == ShowCourseBTN) {
				if (ID_GradetextArea.getText().isEmpty()) {

					ID_GradetextArea
							.setStyle("-fx-border-color: rgb(255,0,0);-fx-border-width: 2px;");
					ID_GradetextArea.setPromptText("please enter Course ID");

					emptyCourseIDLabel.setText("Please enter Course ID!");
					emptyCourseIDLabel.setStyle("-fx-background-color:#F24444;-fx-background-radius: 100px;");
					emptyCourseIDLabel.setVisible(true);

				} else {
					emptyCourseIDLabel.setText("");
					emptyCourseIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
					emptyCourseIDLabel.setVisible(false);
					ID_GradetextArea.setStyle("-fx-border-width: 0px;");

					System.out.println("chose button correctly");
					client.openConnection();
					if (client.isConnected()) {
						client.sendToServer(new Request("SendCourseID", ID_GradetextArea.getText()));
						System.out.println("sent to server");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("Not connected to server.");
					}
				}
			}

			if (event.getSource() == ShowStudentBTN) {
				if (ID_STUtextArea.getText().isEmpty()) {

					ID_STUtextArea
							.setStyle("-fx-border-color: rgb(255,0,0);-fx-border-width: 2px;");
					ID_STUtextArea.setPromptText("please enter Student ID");
					emptyStudentIDLabel.setText("Please enter Student ID!");
					emptyStudentIDLabel.setStyle("-fx-background-color:#F24444;-fx-background-radius: 100px;");
					emptyStudentIDLabel.setVisible(true);

				} else {
					emptyStudentIDLabel.setText("");
					emptyStudentIDLabel.setStyle("-fx-background-color:#000000;-fx-background-radius: 100px;");
					emptyStudentIDLabel.setVisible(false);
					ID_STUtextArea.setStyle("-fx-border-width: 0px;");

					if (client.isConnected()) {
						client.sendToServer(new Request("SendStudentID", ID_STUtextArea.getText()));
						Thread.sleep(1000);

					} else {
						System.out.println("Not connected to server.");

					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Receives information about lecturer grades and displays on the screen
	 * 
	 * @param msg
	 */
	public void ImportLectuerStatistics(ArrayList<Grades> grades) {
	
		Platform.runLater(() -> {
		handleAddDataToChart(grades, barChartLec,LectuerID);
		LectuerName.setText(grades.get(0).getCourseName());// show course name
		GPA_LECtextArea.setText(String.valueOf(calcAVG(grades)));// show the avarge
		Median_LECTextArea.setText(String.valueOf(calcMedian(grades)));// shoe median
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formatNumber = decimalFormat.format(calculateStandardDeviation(grades));
		sdlabelLecturerNumber.setText(formatNumber);
		NumberOfTestLectuer.setText(String.valueOf(grades.size()));// show the number of test
		});


	}

	/**
	 * Receives information about lecturer grades and displays on the screen,
	 * divided to tenths
	 * 
	 * @param msg
	 */
	public void ImportLectuerStatisticsTenths(ArrayList<Grades> msg) {
		ArrayList<Integer> justGrades = new ArrayList<>();
		int i = 0;
		int currentGrade = 0;
		for (Grades grade : msg) {

			currentGrade = (int) grade.getGrade();
			justGrades.add(currentGrade);
		}
		Platform.runLater(() -> {
		handleAddDataToChartTents(justGrades, barChartLec);
		LectuerName.setText(msg.get(0).getCourseName());// show course name
		GPA_LECtextArea.setText(String.valueOf(calcAVG(msg)));// show the avarge
		Median_LECTextArea.setText(String.valueOf(calcMedian(msg)));// shoe median
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formatNumber = decimalFormat.format(calculateStandardDeviation(msg));
		sdlabelLecturerNumber.setText(formatNumber);
		});
	}

	/**
	 * Receives information about student grades and displays on the screen
	 * 
	 * @param msg
	 */
	public void ImportStudentGradeStatistics(ArrayList<Grades> msg) {
		StudentCount++;
		

		handleAddDataToChart(msg, barChartStud,ID_STUtextArea);
		System.out.println("BDIKAAA" + StudentCount);
		if (StudentCount == 1) {// if we take data about one student
			StudentName.setText(msg.get(0).getCourseName());// show course name
		}
		GPA_STUtextArea.setText(String.valueOf(calcAVG(msg)));// show the avarge
		median_STUtextArea.setText(String.valueOf(calcMedian(msg)));// shoe median
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formatNumber = decimalFormat.format(calculateStandardDeviation(msg));
		sdlabelStudentnumber.setText(formatNumber);
		NumberOfTestStudent.setText(String.valueOf(msg.size()));// show the number of test

	}

	/**
	 * Receives information about student grades and displays on the screen,divided
	 * to tenths
	 * 
	 * @param msg
	 */
	public void ImportStudentGradeStatisticsTenths(ArrayList<Grades> msg) {
		StudentCount++;

		ArrayList<Integer> justGrades = new ArrayList<>();
		int i = 0;
		int currentGrade = 0;
		for (Grades grade : msg) {

			currentGrade = (int) msg.get(i++).getGrade();
			justGrades.add(currentGrade);
		}

		handleAddDataToChartTents(justGrades, barChartStud);
		System.out.println("BDIKAAA" + StudentCount);
		if (StudentCount == 1) {// if we take data about one student
			StudentName.setText(msg.get(0).getCourseName());// show course name
		}
		GPA_STUtextArea.setText(String.valueOf(calcAVG(msg)));// show the avarge
		median_STUtextArea.setText(String.valueOf(calcMedian(msg)));// shoe median
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formatNumber = decimalFormat.format(calculateStandardDeviation(msg));
		sdlabelStudentnumber.setText(formatNumber);

	}

	/**
	 * receives information about course grades and displays on the screen
	 * 
	 * @param msg
	 */
	public void ImportCourseGradeStatistics(ArrayList<Grades> msg) {
		
		CourseName.setText(msg.get(0).getCourseName());// show course name
		GPA_GradestextArea.setText(String.valueOf(calcAVG(msg)));// show the avarge
		median_GradesTextArea.setText(String.valueOf(calcMedian(msg)));// shoe median
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formatNumber = decimalFormat.format(calculateStandardDeviation(msg));
		sdlabelCourseNumber.setText(formatNumber);
		NumberOfTestCourse.setText(String.valueOf(msg.size()));// show the number of test

		handleAddDataToChart(msg, barChartCourse,ID_GradetextArea);
	}

	/**
	 * receives information about course grades and displays on the screen,divided
	 * to tenths
	 * 
	 * @param msg
	 */
	public void ImportCourseGradeStatisticstenths(ArrayList<Grades> msg) {
		ArrayList<Integer> justGrades = new ArrayList<>();
		int currentGrade = 0;

		int i = 0;
		CourseName.setText(msg.get(0).getCourseName());// show course name
		GPA_GradestextArea.setText(String.valueOf(calcAVG(msg)));// show the avarge
		median_GradesTextArea.setText(String.valueOf(calcMedian(msg)));// shoe median
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formatNumber = decimalFormat.format(calculateStandardDeviation(msg));
		sdlabelCourseNumber.setText(formatNumber);

		for (Grades grade : msg) {

			currentGrade = (int) msg.get(i++).getGrade();
			justGrades.add(currentGrade);
		}
		handleAddDataToChartTents(justGrades, barChartCourse);
	}

	/**
	 * get the array of the grades of the exams
	 * that we want to calculate and return the standard deviation
	 * 
	 * @param ArrayList<Grades> grades
	 * @return standard deviation as double
	 */
	public double calculateStandardDeviation(ArrayList<Grades> grades) {
		double avg, mean;
		double sum = 0;
		double standard_deviation = 0;
		for (Grades grade : grades) {// caculate the mean
			sum += grade.getGrade();
		}
		avg = sum / grades.size();
		sum = 0;
		for (Grades grade : grades) { // calculate the new mean
			sum = sum + Math.pow(grade.getGrade() - avg, 2);
		}
		mean = sum / grades.size();
		standard_deviation = Math.sqrt(mean);

		return standard_deviation;
	}

	/**
	 * get the array of the grades of the exams
	 * that we want to calculate and return the avarage of then
	 * 
	 * @param ArrayList<Grades> msg
	 * @returns avg of grades as double
	 */

	public double calcAVG(ArrayList<Grades> msg) {
		int sum = 0;
		for (Grades number : msg) {
			sum += number.getGrade();
		}
		double avg = sum / msg.size();
		return avg;
	}

	/**
	 * get the array of the grades of the exams
	 * that we want to calculate and return the median
	 * 
	 * @param ArrayList<Grades> grades
	 * @returns median of the grades as double
	 */
	public double calcMedian(ArrayList<Grades> grades) {/// 1 2 3 6 7 8
		int[] justgrades = new int[grades.size()];
		int i = 0;
		for (Grades grade : grades) {
			justgrades[i] = grades.get(i).getGrade();
			i++;
		}
		Arrays.sort(justgrades);

		double medianIndex = 0;
		if (justgrades.length % 2 == 0) {
			int val1 = justgrades[justgrades.length / 2 - 1];
			int val2 = justgrades[(justgrades.length / 2)];
			medianIndex = (val1 + val2) / 2;

		}

		else {
			medianIndex = justgrades[justgrades.length / 2];
		}
		return medianIndex;

	}

	/***
	 * updaute the bar chart of the grade statistics
	 * 
	 * @param ArrayList<Integer> grades, BarChart chart
	 **/
	private void handleAddDataToChart(ArrayList<Grades> grades, BarChart chart,TextField textField) {
		// Create data series
		Platform.runLater(() -> {
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName(textField.getText());
		
			// Add data points to series
			for (Grades grade :grades){

				series.getData().add(new XYChart.Data<>(grade.getExamID(), grade.getGrade()));
			
			}
			// Add series to the bar chart
			ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
			data.add(series);
			chart.setStyle("-fx-bar-fill:red;");

			chart.setData(data);

		});

	}

	/**
	 * Add data to the bar chart accourding to the chart we want to change
	 * 
	 * @param ArrayList<Integer> grades, BarChart chart
	 */
	private void handleAddDataToChartTents(ArrayList<Integer> grades, BarChart chart) {
		// Create data series
		Platform.runLater(() -> {
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("tenths");
			Map<String, Integer> gradeCountMap = divideToTenths(grades);

			for (Map.Entry<String, Integer> entry : gradeCountMap.entrySet()) {
				series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
			}

			// Add series to the bar chart
			ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
			data.add(series);
			chart.setData(data);
			chart.setStyle("-fx-bar-fill:white;");

			if (chart == barChartLec) {
				chart.setData(data);
			}
		});

	}

	/**
	 * this function gets arraylist of grades and divides them to tenths
	 * stores in dictionery for example:
	 * "1-10":2
	 * "11-20":3
	 * and so on according to the grades
	 * 
	 * @param grades
	 * @return Map<String, Integer>
	 *         dictionery as described above
	 */

	public Map<String, Integer> divideToTenths(ArrayList<Integer> grades) {
		Map<String, Integer> gradeCountMap = new TreeMap<>();// creating a dictionery to save the grades tenths
		for (int i = 0; i < 10; i++) {// initializing the dictionery with ranges and 0's
			gradeCountMap.put(((i * 10) + 1) + "-" + ((i + 1) * 10), 0);
		}

		for (int grade : grades) {
			for (Map.Entry<String, Integer> entry : gradeCountMap.entrySet()) {
				String[] bounds = entry.getKey().split("-");// to get lower bound and upper bound
				int lowerBound = Integer.parseInt(bounds[0]);// converting the lower bound to integer
				int upperBound = Integer.parseInt(bounds[1]);//

				if (grade >= lowerBound && grade <= upperBound) {
					gradeCountMap.put(entry.getKey(), entry.getValue() + 1);
					break; // No need to check the remaining ranges
				}
			}
		}

		return gradeCountMap;
	}

	/**
	 * import data for the compare action
	 * when the head of department press on ""compare to:" button
	 * 
	 * @param ActionEvent event
	 */
	@FXML
	public void CompareTwoIDS(ActionEvent event) {
		ArrayList<String> idsToCompare = new ArrayList<>();

		if (event.getSource() == compareinStudent) {// btnCompareTwoStudents) {
			if (secondStudentID.getText().isEmpty()) {
				secondStudentID.setStyle("-fx-border-color:red;-fx-border-width:2px;");
			} else {
				secondStudentID.setStyle("-fx-border-width:0px;");
			}
			try {
				client.openConnection();
				if (client.isConnected()) {

					idsToCompare.add(ID_STUtextArea.getText());
					idsToCompare.add(secondStudentID.getText());
					System.out.println(idsToCompare.toString());

					client.sendToServer(new Request("GetTwoStudentsGrades", idsToCompare));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("Not connected to server.");

				}

			} catch (IOException exception) {
			}

		}
		if (event.getSource() == compareinCourse) {// btnCompareTwoStudents) {
			if (secondCourseID.getText().isEmpty()) {
				secondCourseID.setStyle("-fx-border-color:red;-fx-border-width:2px;");
			} else {
				secondCourseID.setStyle("-fx-border-width:0px;");
			}
			try {
				client.openConnection();
				if (client.isConnected()) {

					idsToCompare.add(ID_GradetextArea.getText());
					idsToCompare.add(secondCourseID.getText());
					System.out.println(idsToCompare.toString());

					client.sendToServer(new Request("GetTwoCourseGrades", idsToCompare));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("Not connected to server.");

				}

			} catch (IOException exception) {
			}

		}
		if (event.getSource() == compareinLecturer) {// btnCompareTwoStudents) {
			if (secondLecturerID.getText().isEmpty()) {
				secondLecturerID.setStyle("-fx-border-color:red;-fx-border-width:2px;");
			} else {
				secondLecturerID.setStyle("-fx-border-width:0px;");
			}
			try {
				client.openConnection();
				if (client.isConnected()) {

					idsToCompare.add(LectuerID.getText());
					idsToCompare.add(secondLecturerID.getText());
					System.out.println(idsToCompare.toString());

					client.sendToServer(new Request("GetTwoLectureGrades", idsToCompare));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("Not connected to server.");
				}

			} catch (IOException exception) {
			}

		}

	}

	/**
	 * Gets information about the two students we want to compare,
	 * compares their data and displays on the screen
	 * 
	 * @param ArrayList<Grades> grades, ArrayList<Grades> grades2
	 */
	public void compareTwoStudents(ArrayList<Grades> grades, ArrayList<Grades> grades2) {

		for (Grades g : grades) {

			System.out.println("back in controller1" + g.toString());
		}
		for (Grades g2 : grades2) {
			System.out.println("back in controller2" + g2.toString());
		}

		Platform.runLater(() -> {
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName(grades.get(0).getStudentID());
			XYChart.Series<String, Number> series2 = new XYChart.Series<>();
			series2.setName(grades2.get(0).getStudentID());

			for (Grades grade : grades) {
				series.getData().add(new XYChart.Data<>(grade.getExamID(), grade.getGrade()));
			}
			for (Grades grade : grades2) {
				series2.getData().add(new XYChart.Data<>(grade.getExamID(), grade.getGrade()));
			}
			// Add series to the bar chart
			ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
			data.add(series);
			data.add(series2);

			barChartStud.setData(data);
			barChartStud.setData(data);
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String formatNumber = decimalFormat.format(calculateStandardDeviation(grades));
			sdlabelStudentnumber.setText(formatNumber);
			String formatNumber1 = decimalFormat.format(calculateStandardDeviation(grades2));
			sdlabelStudentnumber1.setText(formatNumber1);
			GPA_STUtextArea.setText(String.valueOf(calcAVG(grades)));
			GPA_STUtextArea1.setText(String.valueOf(calcAVG(grades2)));
			median_STUtextArea.setText(String.valueOf(calcMedian(grades)));
			median_STUtextArea1.setText(String.valueOf(calcMedian(grades2)));
			if (grades.size() < grades2.size()) {
				NumberOfTestStudent.setText(String.valueOf(grades.size()));
			} else {
				NumberOfTestStudent.setText(String.valueOf(grades2.size()));
			}

		});

	}

	/**
	 * Gets information about the two lectuers we want to compare,
	 * compares their data and displays on the screen
	 * 
	 * @param ArrayList<Grades> grades, ArrayList<Grades> grades2
	 */
	public void compareTwoLectuers(ArrayList<Grades> grades, ArrayList<Grades> grades2) {

		for (Grades g : grades) {

			System.out.println("back in controller1" + g.toString());
		}
		for (Grades g2 : grades2) {
			System.out.println("back in controller2" + g2.toString());
		}

		Platform.runLater(() -> {
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName(grades.get(0).getStudentID());
			XYChart.Series<String, Number> series2 = new XYChart.Series<>();
			series2.setName(grades2.get(0).getStudentID());

			for (Grades grade : grades) {
				series.getData().add(new XYChart.Data<>(grade.getExamID(), grade.getGrade()));
			}
			for (Grades grade : grades2) {
				series2.getData().add(new XYChart.Data<>(grade.getExamID(), grade.getGrade()));
			}
			// Add series to the bar chart
			ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
			data.add(series);
			data.add(series2);
			barChartLec.setData(data);
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String formatNumber = decimalFormat.format(calculateStandardDeviation(grades));
			sdlabelLecturerNumber.setText(formatNumber);
			String formatNumber1 = decimalFormat.format(calculateStandardDeviation(grades2));
			sdlabelLecturerNumber1.setText(formatNumber1);
			GPA_LECtextArea.setText(String.valueOf(calcAVG(grades)));
			GPA_LECtextArea1.setText(String.valueOf(calcAVG(grades2)));
			Median_LECTextArea.setText(String.valueOf(calcMedian(grades)));
			Median_LECTextArea1.setText(String.valueOf(calcMedian(grades2)));
			if (grades.size() < grades2.size()) {
				NumberOfTestLectuer.setText(String.valueOf(grades.size()));
			} else {
				NumberOfTestLectuer.setText(String.valueOf(grades2.size()));
			}

		});

	}

	/**
	 * Gets information about the two courses we want to compare,
	 * compares their data and displays on the screen
	 * 
	 * @param ArrayList<Grades> grades, ArrayList<Grades> grades2
	 */
	public void compareTwoCourses(ArrayList<Grades> grades, ArrayList<Grades> grades2) {

		for (Grades g : grades) {

			System.out.println("back in controller1" + g.toString());
		}
		for (Grades g2 : grades2) {
			System.out.println("back in controller2" + g2.toString());
		}

		Platform.runLater(() -> {
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName(grades.get(0).getStudentID());
			XYChart.Series<String, Number> series2 = new XYChart.Series<>();
			series2.setName(grades2.get(0).getStudentID());

			for (Grades grade : grades) {
				series.getData().add(new XYChart.Data<>(grade.getExamID(), grade.getGrade()));
			}
			for (Grades grade : grades2) {
				series2.getData().add(new XYChart.Data<>(grade.getExamID(), grade.getGrade()));
			}
			// Add series to the bar chart
			ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
			data.add(series);
			data.add(series2);
			barChartCourse.setData(data);
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String formatNumber = decimalFormat.format(calculateStandardDeviation(grades));
			sdlabelCourseNumber.setText(formatNumber);
			String formatNumber1 = decimalFormat.format(calculateStandardDeviation(grades2));
			sdlabelCourseNumber1.setText(formatNumber1);
			GPA_GradestextArea.setText(String.valueOf(calcAVG(grades)));
			GPA_GradestextArea1.setText(String.valueOf(calcAVG(grades2)));
			median_GradesTextArea.setText(String.valueOf(calcMedian(grades)));
			median_GradesTextArea1.setText(String.valueOf(calcMedian(grades2)));
			if (grades.size() < grades2.size()) {
				NumberOfTestCourse.setText(String.valueOf(grades.size()));
			} else {
				NumberOfTestCourse.setText(String.valueOf(grades2.size()));
			}

		});

	}

}
