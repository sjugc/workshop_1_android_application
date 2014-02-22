package com.sjugc.millionaire.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ivankocijan on 09.02.2014..
 */
public class Game {

    @SerializedName("gameStatus")
    private int gameStatus;

    @SerializedName("question")
    private Question question;

    @SerializedName("treshold")
    private String treshold;

    public int getGameStatus () {
        return gameStatus;
    }

    public void setGameStatus (int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Question getQuestion () {
        return question;
    }

    public void setQuestion (Question question) {
        this.question = question;
    }

    public String getTreshold () {
        return treshold;
    }

    public void setTreshold (String treshold) {
        this.treshold = treshold;
    }
}
