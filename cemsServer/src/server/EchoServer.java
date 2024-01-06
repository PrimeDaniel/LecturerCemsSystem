package server;

// This file contains material supporting section 3.7 of the textbook:
import java.io.IOException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.io.IOException;

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;
import javax.swing.plaf.nimbus.State;

import com.mysql.cj.jdbc.Blob;
import javax.swing.plaf.nimbus.State;

import com.mysql.cj.jdbc.Blob;

import logic.Response;
import logic.StudentData;

import logic.Response;
import logic.StudentData;
import javax.swing.plaf.nimbus.State;
import javax.swing.plaf.nimbus.State;

import com.mysql.cj.jdbc.Blob;

import logic.Response;
import logic.StudentData;

import com.mysql.cj.jdbc.Blob;
import javax.swing.plaf.nimbus.State;

import com.mysql.cj.jdbc.Blob;

import logic.Response;
import logic.StudentData;
import gui.ServerStartScreenController;
import logic.Exam;

import logic.Exam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.AddedTime;


import logic.RequestTime;
import logic.Response;
import logic.StudentInTest;
import logic.Test;
import logic.TestApplyInfo;
import logic.TestCode;
import logic.TestSourceTime;
import logic.UploadFile;
import logic.Users;

import logic.LogInInfo;
import logic.LoggedUsers;
import logic.Question;
import logic.Request;
import logic.Response;
import logic.StudentInTest;
import logic.TestApplyInfo;
import logic.TestCode;
import logic.TestSourceTime;
import logic.UploadFile;
import logic.RequestTime;
import logic.Response;
import logic.StudentInTest;
import logic.Test;
import logic.TestApplyInfo;
import logic.TestCode;
import logic.TestSourceTime;
import logic.UploadFile;
import logic.Users;

import logic.RequestTime;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class EchoServer extends AbstractServer {
	private ServerStartScreenController serverScreenController;
	private ServerCommandsLecturer lecturerCommands = new ServerCommandsLecturer();
	private ServerCommandsStudent serverCommandsStudent = new ServerCommandsStudent();
	private ServerCommandsHD serverCommandsHD = new ServerCommandsHD();
	final public static int DEFAULT_PORT = 5555;

	public EchoServer(int port) {
		super(port);
	}

	public void setController(ServerStartScreenController controller2) {
		this.serverScreenController = controller2;
	}

	@Override
	public void handleMessageFromClient//
	(Object msg, ConnectionToClient client)

	{

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");
		}
		if (msg instanceof Request) {
			Request request = (Request) msg;

			switch (request.getRequestType()) {
				case "LOGIN":
					checkUserLogin((LogInInfo) request.getRequestParam(), client);
					break;
				case "LOGOUT":
					logOut((LogInInfo) request.getRequestParam(), client);
					break;
				case "writeQuestion":
					lecturerCommands.writeQuestion((Question) request.getRequestParam(), client);
					break;
				case "getSubjects":
					lecturerCommands.getSubjects(client);
					break;
				case "getCourses":
					lecturerCommands.getCourses(client, (String) request.getRequestParam());
					break;
				case "getQuestions":
					lecturerCommands.getQuestions(client, (String) request.getRequestParam());
					break;
				case "saveExam":
					lecturerCommands.saveExam((Exam) request.getRequestParam(), client);
					break;
				case "getExamsByLecturer":
					lecturerCommands.getExamsByLecturer(client, (Users) request.getRequestParam());
					break;
				case "getOngoingExams":
					lecturerCommands.getOngoingExams(client, (Users) request.getRequestParam());
					break;
				case "startExam":
					lecturerCommands.startExam(client, (Exam) request.getRequestParam());
					break;
				case "getGrades":
					lecturerCommands.getGrades(client, (Users) request.getRequestParam());
					break;
				case "approveGrade":
					approveGrade(client, (Exam) request.getRequestParam());
					break;
				case "changeGrade":
					changeGrade(client, (Exam) request.getRequestParam());
					break;
				case "RequestTime":
					lecturerCommands.requestTime(client, (RequestTime) request.getRequestParam());
					break;
				case "getPastExams":
					lecturerCommands.getPastExams(client, (Users) request.getRequestParam());
					break;
				case "GetExam":
					serverCommandsStudent.getExam((TestCode) request.getRequestParam(), client);
					break;
				case "SubmitExam":
					serverCommandsStudent.submitTest((StudentInTest) request.getRequestParam(), client);
					break;
				case "CheckIfDurationChanged":
					serverCommandsStudent.checkIfDurationChanged((TestSourceTime) request.getRequestParam(), client);
					break;
				case "DownloadManualExam":
					serverCommandsStudent.downloadManuelExam((int) request.getRequestParam(), client);
					break;
				case "AddStudentToInExamList":
					serverCommandsStudent.addStudentToInExamList((TestApplyInfo) request.getRequestParam(), client);
					break;
				case "GetStudentGrades":
					serverCommandsStudent.getStudentGrades((int) request.getRequestParam(), client);
					break;
				case "SubmitManualExam":
					serverCommandsStudent.submitManualExam((UploadFile) request.getRequestParam(), client);
					break;
				case "closeExam":
					closeExam(client, (Exam) request.getRequestParam());
					break;
				case "SearchExam":
					serverCommandsHD.searchExam((Request) request.getRequestParam(), client);
					break;
				case "GET-EXTRA-TIME-REQUEST":
					System.out.println("test");
					serverCommandsHD.getRequestTimeInfo(null, client);
					break;
				case "SendCourseIDtenth":
					try {

						serverCommandsHD.importCourseDatatenths((String) request.getRequestParam(), client);
						System.out.println(
								"in getcourseID in server got " + (String) request.getRequestParam().toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case "SendStudentIDtenth":

					try {
						serverCommandsHD.importStudentDataTenths((String) request.getRequestParam(), client);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case "SendLectuerIDtenth":

					try {
						serverCommandsHD.importLectuerDataTenths((String) request.getRequestParam(), client);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case "SendCourseID":

					try {
						serverCommandsHD.importCourseData((String) request.getRequestParam(), client);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case "SendStudentID":

					try {
						serverCommandsHD.importStudentData((String) request.getRequestParam(), client);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case "SendLectuerID":
					System.out.println("sendID:" + request.getRequestParam());

					try {
						serverCommandsHD.importLectuerData((String) request.getRequestParam(), client);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case "GetTwoStudentsGrades":
					serverCommandsHD.ImportTwoStudentsSGrades((ArrayList<String>) request.getRequestParam(), client);
					break;
				case "GetTwoLectureGrades":
					serverCommandsHD.ImportTwoLectuerGrades((ArrayList<String>) request.getRequestParam(), client);
					break;
				case "GetTwoCourseGrades":
					serverCommandsHD.ImportTwoCourseGrades((ArrayList<String>) request.getRequestParam(), client);
					break;

				// Add more case statements for other request types
			}
		}
	}

	private void changeGrade(ConnectionToClient client, Exam exam) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			System.out.println("SQL connection succeed");
			String command = "UPDATE grades SET status='approved',notes = ?,grade = ? WHERE examId = ? and studentId = ? and lecturerID = ?";
			PreparedStatement stmt = conn.prepareStatement(command);
			stmt.setString(1, exam.getNotesForChange());
			stmt.setInt(2, exam.getGrade());
			stmt.setInt(3, exam.getExamId());
			stmt.setInt(4, exam.getStudentId());
			stmt.setInt(5, exam.getLecturer().getId());
			stmt.executeUpdate();
			Response response = new Response("changeGradeSuccess", exam);
			this.sendToAllClients(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void approveGrade(ConnectionToClient client, Exam exam) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			System.out.println("SQL connection succeed");
			String command = "UPDATE grades SET status='approved' WHERE examId = ? and studentId = ? and lecturerID = ?";
			PreparedStatement stmt = conn.prepareStatement(command);
			stmt.setInt(1, exam.getExamId());
			stmt.setInt(2, exam.getStudentId());
			stmt.setInt(3, exam.getLecturer().getId());
			stmt.executeUpdate();
			Response response = new Response("approveGradeSuccess", exam);
			this.sendToAllClients(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeExam(ConnectionToClient client, Exam exam) {
		Response response = new Response("closeExam", exam.getExamId());
		try {
			this.sendToAllClients(response);
			Thread.sleep(3000);
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			String command1 = "DELETE FROM cems.open_exams WHERE exam_id = ?;";
			PreparedStatement stmt = conn.prepareStatement(command1);
			stmt.setInt(1, exam.getExamId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void checkUserLogin(LogInInfo loginInfo, ConnectionToClient client) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			System.out.println("SQL connection succeed");
			Users user = getUserInfo(loginInfo, conn);
			if (user == null) {

				client.sendToClient(new Response("LOGIN", null));
				return;
			}
			updateUserLoggedIn(loginInfo, conn);
			addUserToLoggedTable(conn);
			Response response = new Response("LOGIN", user);
			client.sendToClient(response);

		} catch (Exception ex) {
			/* handle the error */
			ex.printStackTrace();
		}
	}

	private int getCourseId(String courseName, Connection conn) {
		Statement stmt;
		String command;
		ResultSet rs;
		int courseId = -1;
		try {
			stmt = conn.createStatement();
			command = String.format("SELECT course_id FROM courses WHERE course_name='%s'", courseName);
			rs = stmt.executeQuery(command);
			if (rs.next())
				return rs.getInt("course_id");
		} catch (Exception ex) {
			/* handle the error */
			ex.printStackTrace();
		}
		return courseId;

	}

	// function to add all the users that are logged in to the logged to the table
	// in server
	private void addUserToLoggedTable(Connection conn) throws SQLException {
		// arraylist to hold info about all currently logged in users
		ArrayList<LoggedUsers> LoggedUsersArray = new ArrayList<>();

		// select relevant informatiom from DB to display in table
		Statement stmt2 = conn.createStatement();
		String command = "SELECT id,firstName,LastName,userName,role FROM users " + "WHERE islogged=1 ";
		ResultSet rs = stmt2.executeQuery(command);
		while (rs.next()) {
			// get fields from resultSet
			Integer id = rs.getInt("id");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("LastName");
			String userName = rs.getString("userName");
			int role = rs.getInt("role");

			LoggedUsers loggedUser = new LoggedUsers(id, firstName, lastName, userName, role);

			LoggedUsersArray.add(loggedUser);
		}
		// add the user to the logged in table through with controller function
		// 'UpadteOnlineUsers'
		serverScreenController.UpadteOnlineUsers(LoggedUsersArray);
	}

	// update the user as logged in DB , based on its username and password
	private void updateUserLoggedIn(LogInInfo login, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(String.format("UPDATE users SET isLogged=1 WHERE userName='%s' AND password ='%s'",
				login.getUserName(), login.getPassword()));
	}

	// return user info from DB, based on its username and password
	private Users getUserInfo(LogInInfo login, Connection conn) throws SQLException {
		// select user with username and password
		Statement stmt = conn.createStatement();
		String command = String.format("SELECT * FROM users" + " WHERE userName='%s' AND password ='%s'",
				login.getUserName(), login.getPassword());
		ResultSet rs = stmt.executeQuery(command);
		if (!rs.next())
			return (null);
		return new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
				rs.getInt(7));
	}


	private void logOut(LogInInfo login, ConnectionToClient client) {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(String.format("UPDATE users SET isLogged=0 WHERE userName='%s' AND password ='%s'",
					login.getUserName(), login.getPassword()));
			addUserToLoggedTable(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	@Override
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	@Override
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *                is entered.
	 */

	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
// End of EchoServer class
