package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.Exam;
import logic.Grades;
import logic.LogInInfo;
import logic.LoggedUsers;
import logic.Request;
import logic.RequestTime;
import logic.Response;
import logic.Users;
import ocsf.server.ConnectionToClient;

public class ServerCommandsHD {
    	/**
		 * This function retrieves the grades of two students from a database and sends them to a client.
		 * 
		 * @param requestParam An ArrayList of Strings containing two student IDs.
		 * @param client The "client" parameter is an instance of the "ConnectionToClient" class, which
		 * represents a connection to a client
		 */
		public void ImportTwoStudentsSGrades(ArrayList<String> requestParam, ConnectionToClient client) {
		ArrayList<Grades> firstIDGrades = new ArrayList<Grades>();
		ArrayList<Grades> secondIDGrades = new ArrayList<Grades>();
		ArrayList<ArrayList<Grades>> alldata = new ArrayList<ArrayList<Grades>>();
		System.out.println(
				"in echo server import2ids,first id: " + requestParam.get(0) + "second id: " + requestParam.get(1));

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");

			Statement stmt = conn.createStatement();
			String command = String.format("SELECT * FROM cems.grades WHERE studentID=%s", requestParam.get(0));
			ResultSet rs = stmt.executeQuery(command);
			while (rs.next()) {
				int examID = rs.getInt("examId");
				int studentID = rs.getInt("studentId");
				int courseID = rs.getInt("courseID");
				int grade = rs.getInt("grade");
				int lecturerID = rs.getInt("lecturerID");
				Grades grades = new Grades(examID, studentID, courseID, grade, lecturerID);
				firstIDGrades.add(grades);

			}
			Statement stmnt2 = conn.createStatement();
			String command1 = String.format("SELECT * FROM cems.grades WHERE studentID=%s", requestParam.get(1));
			ResultSet rs2 = stmnt2.executeQuery(command1);
			while (rs2.next()) {
				int examID = rs.getInt("examId");
				int studentID = rs.getInt("studentId");
				int courseID = rs.getInt("courseID");
				int grade = rs.getInt("grade");
				int lecturerID = rs.getInt("lecturerID");
				Grades grades2 = new Grades(examID, studentID, courseID, grade, lecturerID);
				secondIDGrades.add(grades2);

			}
			alldata.add(firstIDGrades);
			alldata.add(secondIDGrades);

			client.sendToClient(new Response("Get2IDSGradesStudent", alldata));
		} catch (Exception ex) {
			/* handle the error */
			ex.printStackTrace();
		}

	}

	/**
	 * This Java function retrieves grades for two different courses and sends them to a client.
	 * 
	 * @param requestParam An ArrayList of Strings containing two course IDs.
	 * @param client The client parameter is an instance of the ConnectionToClient class, which represents
	 * a connection to a client in a client-server communication.
	 */
	public void ImportTwoCourseGrades(ArrayList<String> requestParam, ConnectionToClient client) {
		ArrayList<Grades> firstIDGrades = new ArrayList<Grades>();
		ArrayList<Grades> secondIDGrades = new ArrayList<Grades>();
		ArrayList<ArrayList<Grades>> alldata = new ArrayList<ArrayList<Grades>>();
		System.out.println(
				"in echo server import2ids,first id: " + requestParam.get(0) + "second id: " + requestParam.get(1));

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");

			Statement stmt = conn.createStatement();
			String command = String.format("SELECT * FROM cems.grades WHERE courseID=%s", requestParam.get(0));
			ResultSet rs = stmt.executeQuery(command);
			while (rs.next()) {
				int examID = rs.getInt("examId");
				int studentID = rs.getInt("studentId");
				int courseID = rs.getInt("courseID");
				int grade = rs.getInt("grade");
				int lecturerID = rs.getInt("lecturerID");
				Grades grades = new Grades(examID, studentID, courseID, grade, lecturerID);
				firstIDGrades.add(grades);

			}
			Statement stmnt2 = conn.createStatement();
			String command1 = String.format("SELECT * FROM cems.grades WHERE courseID=%s", requestParam.get(1));
			ResultSet rs2 = stmnt2.executeQuery(command1);
			while (rs2.next()) {
				int examID = rs.getInt("examId");
				int studentID = rs.getInt("studentId");
				int courseID = rs.getInt("courseID");
				int grade = rs.getInt("grade");
				int lecturerID = rs.getInt("lecturerID");
				Grades grades2 = new Grades(examID, studentID, courseID, grade, lecturerID);
				secondIDGrades.add(grades2);

			}
			alldata.add(firstIDGrades);
			alldata.add(secondIDGrades);

			client.sendToClient(new Response("Get2IDSGradesCourse", alldata));
		} catch (Exception ex) {
			/* handle the error */
			ex.printStackTrace();
		}

	}

	/**
	 * This function retrieves grades data from a database for two different lecturer IDs and sends it to
	 * a client.
	 * 
	 * @param requestParam An ArrayList of Strings containing two elements, representing the IDs of two
	 * lecturers.
	 * @param client The "client" parameter is an object of type ConnectionToClient, which represents the
	 * client that is connected to the server and will receive the response.
	 */
	public void ImportTwoLectuerGrades(ArrayList<String> requestParam, ConnectionToClient client) {
		ArrayList<Grades> firstIDGrades = new ArrayList<Grades>();
		ArrayList<Grades> secondIDGrades = new ArrayList<Grades>();
		ArrayList<ArrayList<Grades>> alldata = new ArrayList<ArrayList<Grades>>();
		System.out.println(
				"in echo server import2ids,first id: " + requestParam.get(0) + "second id: " + requestParam.get(1));

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");

			Statement stmt = conn.createStatement();
			String command = String.format("SELECT * FROM cems.grades WHERE lecturerID=%s", requestParam.get(0));
			ResultSet rs = stmt.executeQuery(command);
			while (rs.next()) {
				int examID = rs.getInt("examId");
				int studentID = rs.getInt("studentId");
				int courseID = rs.getInt("courseID");
				int grade = rs.getInt("grade");
				int lecturerID = rs.getInt("lecturerID");
				Grades grades = new Grades(examID, studentID, courseID, grade, lecturerID);
				firstIDGrades.add(grades);

			}
			Statement stmnt2 = conn.createStatement();
			String command1 = String.format("SELECT * FROM cems.grades WHERE lecturerID=%s", requestParam.get(1));
			ResultSet rs2 = stmnt2.executeQuery(command1);
			while (rs2.next()) {
				int examID = rs.getInt("examId");
				int studentID = rs.getInt("studentId");
				int courseID = rs.getInt("courseID");
				int grade = rs.getInt("grade");
				int lecturerID = rs.getInt("lecturerID");
				Grades grades2 = new Grades(examID, studentID, courseID, grade, lecturerID);
				secondIDGrades.add(grades2);

			}
			alldata.add(firstIDGrades);
			alldata.add(secondIDGrades);

			client.sendToClient(new Response("Get2IDSGradesLectuer", alldata));
		} catch (Exception ex) {
			/* handle the error */
			ex.printStackTrace();
		}

	}

	/**
	 * This function retrieves information about extra time requests from a MySQL database and sends it to
	 * a client.
	 * 
	 * @param requestTime An object of the class RequestTime, which contains information about a request
	 * for extra time for an exam.
	 * @param client The client parameter is an instance of the ConnectionToClient class, which represents
	 * the connection between the server and a specific client. It is used to send responses back to the
	 * client.
	 */
	public void getRequestTimeInfo(RequestTime requestTime, ConnectionToClient client) {
		System.out.println("in request time");

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			ArrayList<RequestTime> requestList = new ArrayList<RequestTime>();
			Statement stmt = conn.createStatement();
			String command = String.format("SELECT * FROM cems.requests");
			ResultSet rs = stmt.executeQuery(command);
			while (rs.next()) {
				String examID = rs.getString("examID");
				String IDRequest = rs.getString("requestID");
				String RequestBy = rs.getString("Requestedby");
				String reason = rs.getString("Reason");
				int extraTime = rs.getInt("ExtraTime");

				RequestTime request = new RequestTime(examID, IDRequest, RequestBy, extraTime, reason);
				requestList.add(request);

			}
			client.sendToClient(new Response("EXTRA_TIME_INFO", requestList));
		} catch (Exception ex) {
			/* handle the error */
			ex.printStackTrace();
		}
	}

	/**
	 * This function searches for a specific exam request and either approves or rejects it, updating the
	 * exam time and sending a response to the client accordingly.
	 * 
	 * @param rq A Request object that contains the exam ID and the request type (approve or reject).
	 * @param client The client parameter is an instance of the ConnectionToClient class, which represents
	 * a connection to a client in a client-server communication system.
	 */
	public void searchExam(Request rq, ConnectionToClient client) {
		String examID = (String) rq.getRequestParam();
		String status = rq.getRequestType();// approve or reject

		try {
			// Update the time of the exam
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			Statement stmt = conn.createStatement();
			String command = String.format("SELECT * FROM requests WHERE examID = %s", examID);
			ResultSet rs = stmt.executeQuery(command);
			switch (status) {
				case "APPROVE":

					while (rs.next()) {
						int extraTime = rs.getInt("ExtraTime");
						String requestedBy = rs.getString("RequestedBy");

						Statement st = conn.createStatement();
						int updateStmt = st.executeUpdate(
								String.format("UPDATE open_exams SET test_time = test_time + %d WHERE exam_id = %s",
										extraTime,
										examID));
						Statement statment = conn.createStatement();
						int rowsAffected = statment
								.executeUpdate(String.format("DELETE FROM requests WHERE examID=%s", examID));

						Response response = new Response("Who Requested Extra Time", requestedBy);
						client.sendToClient(response);

					}
					break;
				case "REJECT":
					while (rs.next()) {
						Statement st = conn.createStatement();
						int rowsAffected = st
								.executeUpdate(String.format("DELETE FROM requests WHERE examID=%s", examID));

					}

			}

			// Close the resources
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This function retrieves student data from a MySQL database and sends it to a client, either for all
	 * students or for a specific student ID.
	 * 
	 * @param gID gID is a String parameter that represents the student ID. If it is null, the method
	 * retrieves all the grades from the database. If it is not null, the method retrieves only the grades
	 * of the student with the given ID.
	 * @param client The parameter "client" is of type ConnectionToClient, which represents a connection
	 * to a client in a client-server architecture. It is used to send data back to the client.
	 */
	public void importStudentData(String gID, ConnectionToClient client) throws IOException, SQLException {
		ArrayList<Grades> grades = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			Statement stmt = conn.createStatement();

			if (gID == null) {
				String command = String.format("SELECT * FROM cems.grades");
				ResultSet rs = stmt.executeQuery(command); // send student data to the client

				while (rs.next()) {
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));

				}
			} else {
				String command = String.format("SELECT * FROM cems.grades WHERE studentID=" + gID);
				ResultSet rs = stmt.executeQuery(command); // send student data to the client

				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));
				}
			}

			client.sendToClient(new Response("ImportStudentData", grades));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This Java function retrieves student grades data from a MySQL database and sends it to a client,
	 * either for all students or for a specific student ID.
	 * 
	 * @param gID gID is a String parameter that represents the ID of a student. If it is null, the method
	 * retrieves grades for all students. If it is not null, the method retrieves grades only for the
	 * student with the specified ID.
	 * @param client The "client" parameter is an object of type "ConnectionToClient", which represents a
	 * connection to a client in a client-server communication system.
	 */
	public void importStudentDataTenths(String gID, ConnectionToClient client) throws IOException, SQLException {
		ArrayList<Grades> grades = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			Statement stmt = conn.createStatement();

			if (gID == null) {
				String command = String.format("SELECT * FROM cems.grades");
				ResultSet rs = stmt.executeQuery(command); // send student data to the client

				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));

				}
			} else {
				String command = String.format("SELECT * FROM cems.grades WHERE studentID=" + gID);
				ResultSet rs = stmt.executeQuery(command); // send student data to the client

				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));
				}
			}

			client.sendToClient(new Response("ImportStudentDataTenths", grades));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This Java function imports grades data from a MySQL database based on a given lecturer ID and sends
	 * it to a client.
	 * 
	 * @param gID gID is a String parameter representing the ID of a lecturer. It is used to filter the
	 * results of the SQL query to only include grades that were given by that specific lecturer. If gID
	 * is null, then all grades are returned.
	 * @param client The parameter "client" is of type ConnectionToClient, which is a class representing a
	 * connection to a client
	 */
	public void importLectuerData(String gID, ConnectionToClient client) throws IOException, SQLException {
		ArrayList<Grades> grades = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			Statement stmt = conn.createStatement();
			if (gID == null) {
				String command = String.format("SELECT * FROM cems.grades");
				ResultSet rs = stmt.executeQuery(command); // send student data to the client

				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));
				}
			} else {
				String command = String.format("SELECT * FROM grades WHERE lecturerID=" + gID);
				ResultSet rs = stmt.executeQuery(command);// send lecture to the client
				////
				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));
				}
			}

			client.sendToClient(new Response("ImportLectuerData", grades));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This Java function retrieves grades data from a MySQL database based on a lecturer ID and sends it
	 * to a client.
	 * 
	 * @param gID gID is a String parameter that represents the ID of a lecturer. It is used to filter the
	 * results of the SQL query to retrieve only the grades that belong to the specified lecturer. If gID
	 * is null, then all grades are retrieved.
	 * @param client The "client" parameter is an object of type "ConnectionToClient", which represents a
	 * connection to a client
	 */
	public void importLectuerDataTenths(String gID, ConnectionToClient client) throws IOException, SQLException {
		ArrayList<Grades> grades = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			Statement stmt = conn.createStatement();
			if (gID == null) {
				String command = String.format("SELECT * FROM cems.grades");
				ResultSet rs = stmt.executeQuery(command); // send student data to the client

				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));

				}
			} else {
				String command = String.format("SELECT * FROM grades WHERE lecturerID=" + gID);
				ResultSet rs = stmt.executeQuery(command);// send lecture to the client
				////
				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));
				}
			}

			client.sendToClient(new Response("ImportLectuerDataTenths", grades));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This Java function imports course data from a MySQL database and sends it to a client.
	 * 
	 * @param gID gID is a String parameter that represents the course ID for which the grades are being
	 * imported. If gID is null, then all grades for all courses will be imported.
	 * @param client The parameter "client" is an object of type ConnectionToClient, which represents a
	 * connection to a client.
	 */
	public void importCourseData(String gID, ConnectionToClient client) throws IOException {

		ArrayList<Grades> grades = new ArrayList<>();
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");
			Statement stmt = conn.createStatement();
			if (gID == null) {
				String command = String.format("SELECT * FROM cems.grades");
				ResultSet rs = stmt.executeQuery(command); // send student data to the client

				while (rs.next()) {
					// get fields from resultSet
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));

				}
			} else {
				String command = String.format("SELECT *  FROM grades WHERE courseID=" + gID);
				ResultSet rs = stmt.executeQuery(command);// send lecture to the client
				while (rs.next()) {
					int examID1 = rs.getInt("examId");
					int studentID1 = rs.getInt("studentId");
					int courseID1 = rs.getInt("courseID");
					int grade1 = rs.getInt("grade");
					int lecturerID1 = rs.getInt("lecturerID");
					grades.add(new Grades(examID1, studentID1,
							courseID1, grade1, lecturerID1));

				}
			}

			client.sendToClient(new Response("ImportCourseData", grades));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This Java function retrieves grades data from a MySQL database for a specific course ID and sends
	 * it to a client.
	 * 
	 * @param gID gID is a String parameter that represents the course ID for which the grades are being
	 * imported.
	 * @param client The parameter "client" is of type ConnectionToClient, which is a class representing a
	 * connection to a client in a server-client architecture. It is used to send data to the client.
	 */
	public void importCourseDatatenths(String gID, ConnectionToClient client) throws IOException {
		System.out.println("in importcourseid " + gID);
		ArrayList<Grades> grades = new ArrayList<>();

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
					"Aa123456");

			Statement stmt = conn.createStatement();
			String command = String.format("SELECT *  FROM grades WHERE courseID=" + gID);
			ResultSet rs = stmt.executeQuery(command);// send lecture to the client
			while (rs.next()) {
				// get fields from resultSet
				int examID1 = rs.getInt("examId");
				int studentID1 = rs.getInt("studentId");
				int courseID1 = rs.getInt("courseID");
				int grade1 = rs.getInt("grade");
				int lecturerID1 = rs.getInt("lecturerID");
				grades.add(new Grades(examID1, studentID1,
						courseID1, grade1, lecturerID1));

				client.sendToClient(new Response("ImportCourseDatatenths", grades));
				System.out.println("sent to client");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	

	/**
	 * This Java function retrieves the course ID from a database based on the course name.
	 * 
	 * @param courseName A String representing the name of a course.
	 * @param conn The "conn" parameter is a Connection object that represents a connection to a database.
	 * It is used to execute SQL statements and retrieve results from the database.
	 * @return The method is returning an integer value which represents the course ID of a given course
	 * name. If the course name is not found in the database, the method returns -1.
	 */
	public int getCourseId(String courseName, Connection conn) {
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

	/**
	 * This Java function retrieves user information from a database based on a given login and
	 * connection.
	 * 
	 * @param login an object of type LogInInfo that contains the username and password of the user trying
	 * to log in.
	 * @param conn The "conn" parameter is a Connection object that represents a connection to a database.
	 * It is used to execute SQL statements and retrieve results from the database.
	 * @return The method is returning a Users object that contains the information of a user with the
	 * given username and password. If no user is found with the given credentials, it returns null.
	 */
	public Users getUserInfo(LogInInfo login, Connection conn) throws SQLException {
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
}
