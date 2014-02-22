package com.sjugc.millionaire.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ivankocijan on 16.02.2014..
 */
public class Question {

    @SerializedName("text")
    private String questionText;

    @SerializedName("answers")
    private ArrayList<String> answers;

    public String getQuestionText () {
        return questionText;
    }

    public void setQuestionText (String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<String> getAnswers () {
        return answers;
    }

    public void setAnswers (ArrayList<String> answers) {
        this.answers = answers;
    }

}
