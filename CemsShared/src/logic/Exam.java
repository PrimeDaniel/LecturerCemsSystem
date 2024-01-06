package logic;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;

/**
 * The Exam class represents an exam with all of exams info plus other fields , which will be used 
 * for the lecturer screens  .
 */
public class Exam implements Serializable {
    private int examId;
    private String courseName;
    private String subject;
    ArrayList<Question> questions; //exam questions
    private Users lecturer;
    private int testTime;
    private String lecturerComments;  
    private String studentComments;
    private int code;
    private int time_remaning;
    private String lecturer_name;
    private int studentId;
    private int grade;
    private String ChangeGradeReason;
    private int cheat;
    private String dateStart;
    private String dateEnd;
    private int studentsNumber;

    public Exam(int examId, int code, int testTime, String dateStart, String dateEnd, int cheat, int studentsNumber) {
        this.examId = examId;
        this.code = code;
        this.testTime = testTime;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.cheat = cheat;
        this.studentsNumber = studentsNumber;
    }

    public Exam (int examId, int studentId,String courseName, int grade,Users lecturer) {
        this.examId = examId;
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
        this.lecturer = lecturer;
    }

    public Exam(int examId, int code, int testTime) {
        this.examId = examId;
        this.code = code;
        this.testTime = testTime;
    }

    public Exam(String courseName, ObservableList<Question> questions, Users lecturer, int testTime, String subject) {
        this.courseName = courseName;
        this.questions = new ArrayList<>(questions);
        ;
        this.lecturer = lecturer;
        this.testTime = testTime;
        this.subject = subject;
    }

    public Exam(int exam_id, String course_name, String lecturer_comments, String student_comments, int test_time) {
        this.examId = exam_id;
        this.courseName = course_name;
        this.lecturerComments = lecturer_comments;
        this.studentComments = student_comments;
        this.testTime = test_time;

    }

    public Exam(int exam_id, String course_name, String lecturer_name, int code, int time_remaining) {
        this.examId = exam_id;
        this.courseName = course_name;
        this.lecturer_name = lecturer_name;
        this.code = code;
        this.time_remaning = time_remaining;

    }

    public String getCourseName() {
        return courseName;
    }

    public String getLecturerName() {
        return lecturer_name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Users getLecturer() {
        return lecturer;
    }

    public int getTestTime() {
        return testTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setLecturerComments(String lecturerComments) {
        this.lecturerComments = lecturerComments;
    }

    public void setStudentComments(String studentComments) {
        this.studentComments = studentComments;
    }

    public String getLecturerComments() {
        return lecturerComments;
    }

    public String getStudentComments() {
        return studentComments;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getExamId() {
        return examId;
    }

    public int getExamCode() {
        return code;

    }

    public int getTimeRemaining() {
        return time_remaning;
    }

    public int getCode() {
        return code;
    }
    public int getStudentId() {
        return studentId;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public void setChangeGradeReason(String ChangeGradeReason) {
        this.ChangeGradeReason = ChangeGradeReason;
    }
    public String getNotesForChange() {
        return ChangeGradeReason;
    }

    public int getCheat() {
        return cheat;
    }
    public String getDateStart() {
        return dateStart;
    }
    public String getDateEnd() {
        return dateEnd;
    }
    public int getStudentsNumber() {
        return studentsNumber;
    }

}
