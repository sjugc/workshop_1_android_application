package com.sjugc.millionaire.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sjugc.millionaire.API.ApiManager;
import com.sjugc.millionaire.R;
import com.sjugc.millionaire.models.ResponseGame;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by ivankocijan on 15.02.2014..
 */
public class GameActivity extends Activity {

    private TextView questionText;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    private String gameId;

    private ProgressDialog pd;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

        answer1 = (Button) findViewById(R.id.answer_1);
        answer2 = (Button) findViewById(R.id.answer_2);
        answer3 = (Button) findViewById(R.id.answer_3);
        answer4 = (Button) findViewById(R.id.answer_4);
        questionText = (TextView) findViewById(R.id.question_text);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

            }
        });

        ApiManager.getMillionaireService().getLinkToGame(callback);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                pd.cancel();
                ApiManager.getMillionaireService().sendAnswer(gameId, "0", callbackGame);


            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                pd.cancel();
                ApiManager.getMillionaireService().sendAnswer(gameId, "1", callbackGame);
            }
        });


        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                pd.cancel();
                ApiManager.getMillionaireService().sendAnswer(gameId, "2", callbackGame);
            }
        });


        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                pd.cancel();
                ApiManager.getMillionaireService().sendAnswer(gameId, "3", callbackGame);
            }
        });

    }


    Callback<Response> callback = new Callback<Response>() {
        @Override
        public void success (Response response, Response response2) {

            List<Header> headers = response.getHeaders();

            for (Header header : headers) {

                if (header != null && header.getName() != null && !header.getName().isEmpty()) {

                    if (header.getName().equals("Location")) {

                        String headerLink = header.getValue();

                        gameId = headerLink.substring(headerLink.lastIndexOf("/") + 1);

                        ApiManager.getMillionaireService().getGame(gameId, callbackGame);
                        break;

                    }

                }

            }

        }

        @Override
        public void failure (RetrofitError retrofitError) {

            pd.cancel();
        }
    };

    Callback<ResponseGame> callbackGame = new Callback<ResponseGame>() {
        @Override
        public void success (ResponseGame responseGame, Response response) {

            pd.cancel();

            if (responseGame.getGame() != null) {

                switch (responseGame.getGame().getGameStatus()) {
                    case 1:
                        questionText.setText(responseGame.getGame().getQuestion().getQuestionText());

                        List<String> answers = responseGame.getGame().getQuestion().getAnswers();

                        answer1.setText(answers.get(0));
                        answer2.setText(answers.get(1));
                        answer3.setText(answers.get(2));
                        answer4.setText(answers.get(3));
                        break;

                    case 0:
                        showAlertDialog(responseGame.getGame().getTreshold());
                        break;

                }
            }

        }

        @Override
        public void failure (RetrofitError retrofitError) {

            pd.cancel();

        }
    };

    private void showAlertDialog (String treshold) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Game is over. Your treshold = " + treshold);
        builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {


                pd.cancel();
                finish();

            }
        });
        builder.create().show();


    }

}
