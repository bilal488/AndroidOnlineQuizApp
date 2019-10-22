package com.example.bilal.androidonlinequizapp.Model;

/**
 * Created by Bilal on 2/19/2018.
 */

//T3-->Start//
public class QuestionScore {
    private String Question_Score;
    private String User;
    private String Score;
    //T5-->Start//
    private String CategoryId;
    private String CategoryName;
    //T5-->End//

    public QuestionScore() {
    }

    //T5-->Start//
    public QuestionScore(String question_Score, String user, String score, String categoryId, String categoryName) {
        Question_Score = question_Score;
        User = user;
        Score = score;
        CategoryId = categoryId;
        CategoryName = categoryName;
    }

    public String getQuestion_Score() {
        return Question_Score;
    }

    public void setQuestion_Score(String question_Score) {
        Question_Score = question_Score;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
    //T5-->End//
}
//T3-->End//