// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import logic.Response;
import logic.StudentData;
import client.*;
import gui.ConnectToServerScreenController;
import gui.LecturerApprovalController;
import gui.LecturerPageController;
import gui.LecturerStatisticsController;
import gui.InExamController;
import gui.HDController;
import gui.LoginScreenController;
import gui.ReviewExamController;
import gui.StartExamController;
import gui.writeQuestionController;
import gui.createExamController;
import gui.pastExamsController;
import gui.TimeRequestController;
import gui.StudentHistoryController;
import gui.StudentManualTestController;
import gui.StudentPageController;
import gui.TakeExamController;
import gui.writeQuestionController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Exam;
import logic.LogInInfo;
import logic.Response;
import logic.AddedTime;

import logic.MyFile;
import logic.Test;
import logic.Users;
import logic.Grades;
import logic.LogInInfo;
import logic.RequestTime;
import logic.Question;
import java.io.*;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import javax.security.auth.Subject;

import ocsf.client.*;

public class ChatClient extends AbstractClient

{
	private StudentManualTestController stdManController;
	private LoginScreenController loginScreecontroller;
	private writeQuestionController writeQuestionController;
	private createExamController createExamController;
	private ReviewExamController reviewExamController;
	private TimeRequestController TimeRequestController;
	private StartExamController startExamController;
	private LecturerPageController lecturerPageController;
	private LecturerApprovalController lecturerApprovalController;
	private TakeExamController takeExamController;
	private InExamController inExamController;
	private StudentHistoryController studentHistoryController;
	private LecturerStatisticsController lecturerStatisticsController;
	private pastExamsController pastExamsController;
	private HDController hdcontroller;
	private StudentPageController studentPageController;
	private String ip = "";
	private int portServer;
	// Instance variables **********************************************

	public ChatClient(String host, int port, Object clientUI) throws IOException {
		super(host, port);
		ip = host;
		this.portServer = port;
	}
	// this is new line
	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {

		if (msg instanceof Response) {
			Response response = (Response) msg;
			switch (response.getResponseType()) {
				case "LOGIN":
					login((Users) response.getResponseParam());
					break;
				case "Subjects":
					ArrayList<String> subjectsArr = (ArrayList<String>) response.getResponseParam();
					if (writeQuestionController != null)
						writeQuestionController.updateSubjectsComboBox(subjectsArr);
					if (createExamController != null)
						createExamController.updateSubjectsComboBox(subjectsArr);

					System.out.println("hello");
					break;

				case "Courses":
					ArrayList<String> coursesArr = (ArrayList<String>) response.getResponseParam();
					if (writeQuestionController != null)
						writeQuestionController.updateCoursesComboBox(coursesArr);
					if (createExamController != null)
						createExamController.updateCoursesComboBox(coursesArr);
					System.out.println("hello2");
					break;

				case "QuestionsArray":
					ArrayList<Question> questionsArr = (ArrayList<Question>) response.getResponseParam();
					createExamController.upadteQuestionViewTable(questionsArr);
					System.out.println("hello3");
					break;

				case "ExamSaved":
					reviewExamController.setLabel();
					break;
				case "getExamsByLecturer":
					ArrayList<Exam> examsArr = (ArrayList<Exam>) response.getResponseParam();
					if (startExamController != null)
						startExamController.setExamsTable(examsArr);
					if (lecturerStatisticsController != null)
						lecturerStatisticsController.setExamsTable(examsArr);
					break;
				case "getOngoingExams":
					ArrayList<Exam> ongoingExamsArr = (ArrayList<Exam>) response.getResponseParam();
					lecturerPageController.setOngoingExamsTable(ongoingExamsArr);
					break;
				case "startExamSuccess":
					startExamController.startExamSuccess();
					break;
				case "startExamFailed":
					startExamController.startExamFailed();
					break;
				case "getGrades":
					ArrayList<Exam> gradesArr = (ArrayList<Exam>) response.getResponseParam();
					if (lecturerApprovalController != null)
						lecturerApprovalController.setGradesTable(gradesArr);
					if (lecturerStatisticsController != null)
						lecturerStatisticsController.setGradesArr(gradesArr);
					break;
				case "approveGradeSuccess":
					if (lecturerApprovalController != null)
						lecturerApprovalController.approveGradeSuccess();
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (studentPageController != null)
						studentPageController.showNewApprovedgrade((Exam) response.getResponseParam());
					break;
				case "changeGradeSuccess":
					lecturerApprovalController.changeGradeSuccess();
					if (studentPageController != null)
						studentPageController.showNewApprovedgrade((Exam) response.getResponseParam());
					break;
				case "requestTimeSuccess":
					TimeRequestController.requestTimeSuccess();
					break;
				case "getPastExams":
					ArrayList<Exam> pastExamsArr = (ArrayList<Exam>) response.getResponseParam();
					pastExamsController.setExamsTable(pastExamsArr);
					break;
				case "GetExam":
					getExam((Test) response.getResponseParam());
					break;
				case "AddedTime":
					AddedTime added = (AddedTime) response.getResponseParam();
					inExamController.setAdded(added);
					break;
				case "GetStudentGrades":
					ArrayList<StudentData> data = (ArrayList<StudentData>) response.getResponseParam();
					sendStudentGrades(data);
					break;
				case "DownloadManualExam":
					MyFile file = (MyFile) response.getResponseParam();
					stdManController.setDownloadFile(file);
					break;
				case "closeExam":
					
					if (inExamController != null) {
						inExamController.LockExam((int) response.getResponseParam());
					}
					if (stdManController != null) {
						stdManController.LockExam((int) response.getResponseParam());
					}
					lecturerPageController.getOngoingExamsTable(); // refresh table
					break;
				case "LOGIN_success":
					login((Users) response.getResponseParam());
					break;

				case "Who Requested Extra Time":
					hdcontroller.setTest((String) response.getResponseParam());
					hdcontroller.refreshTable1();
					hdcontroller.showpPopupApprove((String) response.getResponseParam());
					break;
				case "RETURN-STUDENT-GRADES":
					ArrayList<String> returnarr = (ArrayList<String>) response.getResponseParam();
					for (String str : returnarr)
						System.out.println(str.toString());
					hdcontroller.setGeneralArray(returnarr);
					break;
				case "ImportCourseData":
					System.out.println("in import course data: " + (ArrayList<Grades>) response.getResponseParam());
					ArrayList<Grades> grade = (ArrayList<Grades>) response.getResponseParam();
					hdcontroller.ImportCourseGradeStatistics(grade);

					break;

				case "ImportLectuerData":
					ArrayList<Grades> grade1 = (ArrayList<Grades>) response.getResponseParam();
					hdcontroller.ImportLectuerStatistics(grade1);

					break;
				case "ImportStudentData":
					ArrayList<Grades> grade2 = (ArrayList<Grades>) response.getResponseParam();
					hdcontroller.ImportStudentGradeStatistics(grade2);
					break;
				case "ImportCourseDatatenths":
					System.out.println("in import course data: " + (ArrayList<Grades>) response.getResponseParam());
					ArrayList<Grades> grade3 = (ArrayList<Grades>) response.getResponseParam();
					hdcontroller.ImportCourseGradeStatisticstenths(grade3);

					break;
				case "ImportLectuerDataTenths":
					System.out.println("in import course data: " + (ArrayList<Grades>) response.getResponseParam());
					ArrayList<Grades> grade4 = (ArrayList<Grades>) response.getResponseParam();
					hdcontroller.ImportLectuerStatisticsTenths(grade4);

					break;
				case "ImportStudentDataTenths":
					System.out.println("in import course data: " + (ArrayList<Grades>) response.getResponseParam());
					ArrayList<Grades> grade5 = (ArrayList<Grades>) response.getResponseParam();
					hdcontroller.ImportStudentGradeStatisticsTenths(grade5);

					break;
				case "EXTRA_TIME_INFO":
					System.out.println("in chatClient");
					ArrayList<RequestTime> requestList = (ArrayList<RequestTime>) response.getResponseParam();
					hdcontroller.UpdateRequestTime((ArrayList<RequestTime>) response.getResponseParam());
					System.out.println("in chatClient111");
					for (RequestTime rt : (ArrayList<RequestTime>) response.getResponseParam()) {

					}

					// ************************************************ */
					break;
				case "Get2IDSGradesStudent":
					System.out.println("in chatCLient testing 2ids");
					ArrayList<ArrayList<Grades>> gradeList = (ArrayList<ArrayList<Grades>>) response.getResponseParam();
					ArrayList<Grades> grades = gradeList.get(0);
					ArrayList<Grades> grades1 = gradeList.get(1);

					// ArrayList<Grades> gradeList = (ArrayList<Grades>)
					// response.getResponseParam();
					// System.out.println(gradeList.toString());
					// ArrayList<ArrayList<Grades>> gradeList = (ArrayList<ArrayList<Grades>>)
					// response.getResponseParam();
					for (Grades g1 : grades) {
						System.out.println(g1.toString());

						System.out.println("in for loop" + g1.toString());
					}
					for (Grades g2 : grades1) {
						System.out.println(g2.toString());

						System.out.println("in for loop" + g2.toString());

					}

					hdcontroller.compareTwoStudents(grades, grades1);
					break;
				case "Get2IDSGradesLectuer":
					System.out.println("in chatCLient testing 2ids");
					gradeList = (ArrayList<ArrayList<Grades>>) response.getResponseParam();
					grades = gradeList.get(0);
					grades1 = gradeList.get(1);

					// ArrayList<Grades> gradeList = (ArrayList<Grades>)
					// response.getResponseParam();
					// System.out.println(gradeList.toString());
					// ArrayList<ArrayList<Grades>> gradeList = (ArrayList<ArrayList<Grades>>)
					// response.getResponseParam();
					for (Grades g1 : grades) {
						System.out.println(g1.toString());

						System.out.println("in for loop" + g1.toString());
					}
					for (Grades g2 : grades1) {
						System.out.println(g2.toString());

						System.out.println("in for loop" + g2.toString());

					}

					hdcontroller.compareTwoLectuers(grades, grades1);
					break;
				case "Get2IDSGradesCourse":
					System.out.println("in chatCLient testing 2ids");
					gradeList = (ArrayList<ArrayList<Grades>>) response.getResponseParam();
					grades = gradeList.get(0);
					grades1 = gradeList.get(1);

					for (Grades g1 : grades) {
						System.out.println(g1.toString());

						System.out.println("in for loop" + g1.toString());
					}
					for (Grades g2 : grades1) {
						System.out.println(g2.toString());

						System.out.println("in for loop" + g2.toString());

					}

					hdcontroller.compareTwoCourses(grades, grades1);
					break;
			}

		}

	}

	private void login(Users user) {

		System.out.println(user);
		try {
			loginScreecontroller.ShowUserWelcomeScreen(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user.getRole());
	}

	public void setController(LecturerApprovalController controller) {
		this.lecturerApprovalController = controller;
	}

	public void setController(StudentPageController controller) {
		this.studentPageController = controller;
	}

	public void setController(LoginScreenController controller) {
		this.loginScreecontroller = controller;
	}

	public void setController(createExamController controller) {
		this.createExamController = controller;
	}

	public void setController(ReviewExamController controller) {
		this.reviewExamController = controller;
	}

	public void setController(TimeRequestController controller) {
		this.TimeRequestController = controller;
	}

	public void setController(StartExamController controller) {
		this.startExamController = controller;
	}

	public void setController(LecturerPageController controller) {
		this.lecturerPageController = controller;
	}

	public void setController(LecturerStatisticsController controller) {
		this.lecturerStatisticsController = controller;
	}

	public void setController(pastExamsController controller) {
		this.pastExamsController = controller;
	}

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */

	private void sendStudentGrades(ArrayList<StudentData> data) {
		studentHistoryController.setStudentDataList(data);
	}

	private void getExam(Test test) {
			takeExamController.ShowStudentEnterIdScreen(test);
		
	}

	public void setStudentManualTestController(StudentManualTestController controller) {
		this.stdManController = controller;
	}

	public void setTakeExamController(TakeExamController controller) {
		this.takeExamController = controller;
	}

	public void setInExamController(InExamController controller) {
		this.inExamController = controller;
	}

	public void setStudentHistoryController(StudentHistoryController controller) {
		this.studentHistoryController = controller;
	}

	public void setController(writeQuestionController controller) {
		this.writeQuestionController = controller;
	}

	public void setController(HDController controller) {
		this.hdcontroller = controller;
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {

		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	public String getIp() {
		return ip;
	}

	public int getPortServer() {
		return portServer;
	}
}
// End of ChatClient class