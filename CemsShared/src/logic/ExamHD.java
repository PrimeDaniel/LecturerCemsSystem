package logic;

import java.io.Serializable;

// This class is used to create objects that
// represent an exam in the system. It has instance variables to store information about the exam such as exam ID,
// written by, test subject, course name, exam number, and time. It also has methods to get and set
// these variables.

public class ExamHD implements Serializable {
String examID;
String writtenBy;
String testSubject;
String courseName;
String examNumber;
int time;

public ExamHD(String examID, String writtenBy, String testSubject, String courseName, String examNumber){
    this.examID = testSubject+courseName+examNumber;//"020306"
    this.writtenBy = writtenBy;
    this.testSubject = testSubject;//"02"
    this.courseName = courseName;//"03"
    this.examNumber = examNumber;//"06"
    this.time = 0;
}

public String getexamID() {return  examID  ;   }

public int gettime() {return  time ;   }

public String getwrittenBy() {return  writtenBy  ;   }

public String gettestSubject() {return  testSubject  ;   }

public String getcourseName() {return  courseName  ;   }

public String getexamNumber() {return  examNumber  ;   }

public void setexamID(String examID) {this.examID = examID;}  

public void settime(int time) {this.time = time;}  

public void setwrittenBy(String writtenBy) {this.writtenBy = writtenBy;}

public void settestSubject(String testSubject) {this.testSubject = testSubject;}

public void setcourseName(String courseName) {this.courseName = courseName;}

public void setexamNumber(String examNumber) {this.examNumber = examNumber;}
public String toString(){return "exam id:"+examID;}









}