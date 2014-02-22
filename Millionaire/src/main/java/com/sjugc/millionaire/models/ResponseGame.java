package com.sjugc.millionaire.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ivankocijan on 09.02.2014.
 *
 * Server response example:
 * {
     "stauts": 0,
     "response": {
         "gameStatus": 1,
         "question": {
         "text": "questionText",
         "answers": [
             "answer 1",
             "answer 2",
             "answer 3",
             "answer 4"
         ]
         }
      }
    }
 *
 */
public class ResponseGame {

    @SerializedName("stauts")
    private int responseStatus;

    @SerializedName("response")
    private Game game;

    public Game getGame () {
        return game;
    }

    public void setGame (Game game) {
        this.game = game;
    }

    public int getResponseStatus () {
        return responseStatus;
    }

    public void setResponseStatus (int status) {
        this.responseStatus = status;
    }


}
