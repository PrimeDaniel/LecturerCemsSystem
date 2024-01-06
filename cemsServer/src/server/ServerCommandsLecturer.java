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
import logic.Question;
import logic.RequestTime;
import logic.Response;
import logic.Users;
import ocsf.server.ConnectionToClient;

public class ServerCommandsLecturer {

    /**
     * This Java function retrieves past exams from a MySQL database and sends them to a client.
     * 
     * @param client The client parameter is an instance of the ConnectionToClient class.
     * @param lecturer The parameter "lecturer" is an object of type "Users" which represents a user
     * who is a lecturer in the system.
     */
    public void getPastExams(ConnectionToClient client, Users lecturer) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed Ongoing Exams!");
            // create Table, if exist skip
            String command1 = "SELECT exam_id, code, test_time ,date_start,date_end,cheat,students_number "
                    + "FROM closed_exams "
                    + "WHERE closed_exams.lecturer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(command1);
            stmt.setInt(1, lecturer.getId());

            ResultSet rs = stmt.executeQuery();
            ArrayList<Exam> exams = new ArrayList<>();
            while (rs.next()) {
                int examId = rs.getInt("exam_id");
                int code = rs.getInt("code");
                int testTime = rs.getInt("test_time");
                String dateStart = rs.getString("date_start");
                String dateEnd = rs.getString("date_end");
                int cheat = rs.getInt("cheat");
                int studentsNumber = rs.getInt("students_number");
                Exam exam = new Exam(examId, code, testTime, dateStart, dateEnd, cheat, studentsNumber);
                exams.add(exam);
            }
            Response response = new Response("getPastExams", exams);
            client.sendToClient(response);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function inserts a new row into a MySQL database table with information about a time request
     * for an exam.
     * 
     * @param client A ConnectionToClient object representing the client who sent the request.
     * @param requestTime 
     */
    public void requestTime(ConnectionToClient client, RequestTime requestTime) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            String command = "INSERT INTO requests (examID,RequestedBy,Reason,extraTime) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setString(1, requestTime.getExamID());
            stmt.setString(2, requestTime.getRequestedBy());
            stmt.setString(3, requestTime.getReason());
            stmt.setInt(4, requestTime.getExtraTime());
            stmt.executeUpdate();
            Response response = new Response("requestTimeSuccess", null);
            client.sendToClient(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * This Java function retrieves grades from a MySQL database and sends
     * them to a client.
     * 
     * @param client The client object represents the connection to the client who requested the grades
     * information.
     * @param lecturer The parameter "lecturer" is an object of the class "Users".
     */
    public void getGrades(ConnectionToClient client, Users lecturer) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            String command=null;
            if (lecturer.getFlag()==1){

                command = "SELECT examId,studentId,grade,courseName FROM grades WHERE status='approved' and lecturerID = ?";
            }else{
                command = "SELECT examId,studentId,grade,courseName FROM grades WHERE status!='approved' and lecturerID = ?";
            }
            
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setInt(1, lecturer.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Exam> exams = new ArrayList<>();
            while (rs.next()) {
                Exam exam = new Exam(rs.getInt("examId"), rs.getInt("studentId"), rs.getString("courseName"),
                        rs.getInt("grade"), lecturer);
                exams.add(exam);
            }
            Response response = new Response("getGrades", exams);
            client.sendToClient(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    /**
     * The function starts an exam for a client by checking if the exam code is valid.
     * 
     * @param client The client object represents the connection to the client who requested to start
     * the exam.
     * @param exam an object of type Exam, which contains information about the exam such as exam ID,
     * code, and test time.
     */
    public void startExam(ConnectionToClient client, Exam exam) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            String checkCodeCommand = "SELECT code FROM open_exams WHERE exam_id = ?";
            PreparedStatement stmt = conn.prepareStatement(checkCodeCommand);
            stmt.setInt(1, exam.getExamId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Response response = new Response("startExamFailed", null);
                client.sendToClient(response);
                return;

            }
            String command = "INSERT INTO open_exams VALUES(?,?,?)";
            stmt = conn.prepareStatement(command);
            stmt.setInt(1, exam.getExamId());
            stmt.setInt(2, exam.getCode());
            stmt.setInt(3, exam.getTestTime());
            stmt.executeUpdate();
            Response response = new Response("startExamSuccess", null);
            client.sendToClient(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This Java function retrieves ongoing exams from a MySQL database and sends them to a client.
     * 
     * @param client The client parameter is an instance of the ConnectionToClient class, which
     * represents a connection to a client in a client-server application.
     * @param lecturer The parameter "lecturer" is an object of the class "Users" representing a user
     * who is a lecturer in the system.
     */
    public void getOngoingExams(ConnectionToClient client, Users lecturer) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed Ongoing Exams!");
            // create Table, if exist skip
            String command1 = "SELECT open_exams.exam_id, open_exams.code, open_exams.test_time "
                    + "FROM open_exams "
                    + "JOIN exams ON open_exams.exam_id = exams.exam_id "
                    + "WHERE exams.lecturer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(command1);
            stmt.setInt(1, lecturer.getId());

            ResultSet rs = stmt.executeQuery();
            ArrayList<Exam> exams = new ArrayList<>();
            while (rs.next()) {
                Exam exam = new Exam(rs.getInt("exam_id"), rs.getInt("code"), rs.getInt("test_time"));
                exams.add(exam);
            }
            Response response = new Response("getOngoingExams", exams);
            client.sendToClient(response);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Java function retrieves exams from a database based on a given lecturer's ID and sends the
     * results to a client.
     * 
     * @param client The client parameter is an instance of the ConnectionToClient class, which
     * represents a connection to a client.
     * @param lecturer The lecturer parameter is an instance of the Users class, which represents a
     * user in the system.
     */
    public void getExamsByLecturer(ConnectionToClient client, Users lecturer) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            Statement stmt = conn.createStatement();
            String command = "SELECT exam_id,course_name,lecturer_comments,student_comments,test_time FROM exams WHERE lecturer_id = '"
                    + lecturer.getId() + "'";
            ResultSet rs = stmt.executeQuery(command);
            ArrayList<Exam> exams = new ArrayList<>();
            while (rs.next()) {
                Exam exam = new Exam(rs.getInt("exam_id"), rs.getString("course_name"),
                        rs.getString("lecturer_comments"), rs.getString("student_comments"), rs.getInt("test_time"));
                exams.add(exam);
            }
            Response response = new Response("getExamsByLecturer", exams);
            client.sendToClient(response);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * This Java function saves an exam object and its associated questions to a MySQL database and
     * sends a response to a client.
     * 
     * @param exam an object of type Exam, which contains information about the exam being saved,
     * including the course name, lecturer, comments, student comments, test time, and a list of
     * questions.
     * @param client The client parameter is an instance of the ConnectionToClient class, which
     * represents the connection between the server and the client that requested to save the exam. It
     * is used to send a response back to the client.
     */
    public void saveExam(Exam exam, ConnectionToClient client) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            Statement stmt = conn.createStatement();
            String command = "INSERT INTO exams (course_name,lecturer_id,lecturer_comments,student_comments,test_time) VALUES ('"
                    + exam.getCourseName() + "','" + exam.getLecturer().getId() + "','" + exam.getLecturerComments()
                    + "','" + exam.getStudentComments() + "','" + exam.getTestTime() + "')";
            stmt.executeUpdate(command, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int examId = rs.getInt(1);

            PreparedStatement stmt2;
            //insert the exam's questions into the exam_questions table
            for (Question question : exam.getQuestions()) {
                command = "INSERT INTO exam_questions (exam_id, question_id, score) VALUES (?, ?, ?)";
                stmt2 = conn.prepareStatement(command);

                // set the parameters
                stmt2.setInt(1, examId);
                stmt2.setInt(2, question.getQuestionID());
                stmt2.setInt(3, question.getScore());
                // execute the INSERT statement
                stmt2.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            client.sendToClient(new Response("ExamSaved", null));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

   /**
    * This Java function retrieves questions from a MySQL database based on a given course and sends
    * them to a client.
    * 
    * @param client A ConnectionToClient object representing the client who requested the questions.
    * @param course The name of the course for which the questions are being requested.
    */
    public void getQuestions(ConnectionToClient client, String course) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            Statement stmt = conn.createStatement();
            String command = "SELECT course_id FROM courses WHERE course_name = '" + course + "'";
            ResultSet rs = stmt.executeQuery(command);
            rs.next();
            int courseId = rs.getInt("course_id");
            command = "SELECT * FROM questions WHERE course_id = '" + courseId + "'";
            rs = stmt.executeQuery(command);
            ArrayList<Question> questions = new ArrayList<Question>();
            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                String questionText = rs.getString("question_text");
                String answer1 = rs.getString("answer1");
                String answer2 = rs.getString("answer2");
                String answer3 = rs.getString("answer3");
                String answer4 = rs.getString("answer4");
                int correctAnswer = rs.getInt("correct_answer");
                int authorId = rs.getInt("lecturer_id");
                String authorName = rs.getString("lecturer_name");
                Question question = new Question(questionId, questionText, answer1, answer2, answer3, answer4,
                        correctAnswer, courseId, authorId, authorName);
                questions.add(question);
            }
            Response response = new Response("QuestionsArray", questions);
            client.sendToClient(response);
        } catch (Exception ex) {
            /* handle the error */
            ex.printStackTrace();
        }
    }

    
   /**
    * This Java function retrieves a list of courses based on a given subject from a MySQL database and
    * sends it to a client.
    * 
    * @param client The client parameter is an instance of the ConnectionToClient class, which
    * represents a connection to a client in a client-server application.
    * @param subject The subject for which the courses are being requested.
    */
    public void getCourses(ConnectionToClient client, String subject) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            Statement stmt = conn.createStatement();
            String command = "SELECT subject_id FROM subjects WHERE subject_name = '" + subject + "'";
            ResultSet rs = stmt.executeQuery(command);
            rs.next();
            String subjectId = rs.getString("subject_id");
            command = "SELECT course_name FROM courses WHERE subject_id = '" + subjectId + "'";
            rs = stmt.executeQuery(command);
            ArrayList<String> courses = new ArrayList<String>();
            while (rs.next()) {
                courses.add(rs.getString("course_name"));
            }
            Response response = new Response("Courses", courses);
            client.sendToClient(response);
        } catch (Exception ex) {
            /* handle the error */
            ex.printStackTrace();
        }
    }

    /**
     * The function retrieves a list of subject names from a MySQL database and sends it as a response
     * to a client.
     * 
     * @param client The parameter "client" is an object of type ConnectionToClient, which represents a
     * client connected to the server through a socket connection.
     */
    public void getSubjects(ConnectionToClient client) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            Statement stmt = conn.createStatement();
            String command = "SELECT subject_name FROM subjects";
            ResultSet rs = stmt.executeQuery(command);
            ArrayList<String> subjects = new ArrayList<String>();
            while (rs.next()) {
                subjects.add(rs.getString("subject_name"));
            }
            for (String subject : subjects) {
                System.out.println(subject);
            }
            Response response = new Response("Subjects", subjects);
            client.sendToClient(response);
        } catch (Exception ex) {
            /* handle the error */
            ex.printStackTrace();
        }
    }


    /**
     * The function writes a new question to a MySQL database.
     * 
     * @param question an object of the class Question, which contains the details of the question to
     * be written to the database.
     * @param client The client parameter is an object of type ConnectionToClient, which represents the
     * connection to the client who sent the question to be written to the database.
     */
    public void writeQuestion(Question question, ConnectionToClient client) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root",
                    "Aa123456");
            System.out.println("SQL connection succeed");
            int courseId = getCourseId(question.getCourseName(), conn);
            Statement stmt = conn.createStatement();
            String command = "INSERT INTO questions (question_text,answer1,answer2,answer3,answer4,correct_answer,course_id,lecturer_id,lecturer_name) VALUES ('"
                    + question.getQuestionDescription() + "','" + question.getAnswer1() + "','" + question.getAnswer2()
                    + "','" + question.getAnswer3() + "','" + question.getAnswer4() + "','"
                    + question.getCorrectAnswer() + "','" + courseId + "','" + question.getAuthorID() + "','"
                    + question.getAuthor() + "')";
            stmt.executeUpdate(command);

        } catch (Exception ex) {
            /* handle the error */
            ex.printStackTrace();
        }
    }

   /**
    * This Java function retrieves the course ID from a database based on the course name.
    * 
    * @param courseName A string representing the name of a course.
    * @param conn The "conn" parameter is a Connection object that represents a connection to a
    * database.
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
}
