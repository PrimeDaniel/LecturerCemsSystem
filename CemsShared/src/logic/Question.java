package logic;

import java.io.Serializable;

//class for question ,it helps to create a question and save it in the database
//it is used on writeQuestionController
public class Question implements Serializable {
    private int questionID;
    private String questionDescription;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctAnswer;
    private int courseId;
    private String courseName;
    private String subject;
    private String author;
    private int authorID;
    private int score;

    public Question(String questionDescription, String answer1, String answer2, String answer3, String answer4, int correctAnswer, int courseId,int authorID ,String author) {
        this.questionDescription = questionDescription;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.courseId = courseId;
        this.author = author;
        this.authorID = authorID;
    }

    public Question(int questionID,String questionDescription, String answer1, String answer2, String answer3, String answer4, int correctAnswer, int courseId,int authorID ,String author){
        this.questionID = questionID;
        this.questionDescription = questionDescription;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.courseId = courseId;
        this.author = author;
        this.authorID = authorID;
    }
    //constructor for question,it initilize ALL fields
public Question(int questionID, String questionDescription, String answer1, String answer2, String answer3, String answer4, int correctAnswer, int courseId, String courseName, String subject, String author, int authorID) {
    this.questionID = questionID;
    this.questionDescription = questionDescription;
    this.answer1 = answer1;
    this.answer2 = answer2;
    this.answer3 = answer3;
    this.answer4 = answer4;
    this.correctAnswer = correctAnswer;
    this.courseId = courseId;
    this.courseName = courseName;
    this.subject = subject;
    this.author = author;
    this.authorID = authorID;
}

public Question(String questionDescription, String answer1, String answer2, String answer3, String answer4, int correctAnswer,
                String courseName, int authorID, String author){
    this.questionDescription = questionDescription;
    this.answer1 = answer1;
    this.answer2 = answer2;
    this.answer3 = answer3;
    this.answer4 = answer4;
    this.correctAnswer = correctAnswer;
    this.courseName = courseName;
    this.author = author;
    this.authorID = authorID;
    
}

    //getters for all fields
    public String getQuestionDescription() {
        return questionDescription;
    }
    public String getAnswer1() {
        return answer1;
    }
    public String getAnswer2() {
        return answer2;
    }
    public String getAnswer3() {
        return answer3;
    }
    public String getAnswer4() {
        return answer4;
    }
    public int getCorrectAnswer() {
        return correctAnswer;
    }
    public int getCourseId() {
        return courseId;
    }
    public String getSubject() {
        return subject;
    }
    public String getAuthor() {
        return author;
    }
    public int getAuthorID() {
        return authorID;
    }
    public int getQuestionID() {
        return questionID;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getScore() {
        return score;
    }
    public int setScore(int score) {
        return this.score = score;
    }


}
