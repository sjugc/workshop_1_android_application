package com.sjugc.millionaire.API;

import android.util.Log;

import com.sjugc.millionaire.models.ResponseGame;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by ivankocijan on 09.02.2014..
 */
public class ApiManager {

    private static final Object o = new Object();
    private static MillionaireService service;

    public static final String URL = "http://sjugc.com/Millionaire/";

    public static interface MillionaireService {

        @GET("/games")
        void getLinkToGame (Callback<Response> cb);

        @GET("/games/{gameId}")
        void getGame (@Path("gameId") String gameId,
                      Callback<ResponseGame> cb);

        @GET("/games/{gameId}/{answer}")
        void sendAnswer (@Path("gameId") String gameId,
                         @Path("answer") String answer,
                         Callback<ResponseGame> cb);


    }

    public static MillionaireService getMillionaireService () {

        if (service == null) {

            synchronized (o) {

                if (service == null) {

                    RestAdapter.Log log = new RestAdapter.Log() {
                        public void log (String message) {
                            Log.d("triviaApi", message);
                        }
                    };

                    RestAdapter restAdapter = new RestAdapter.Builder().setLog(log).setLogLevel(RestAdapter.LogLevel.FULL).
                            setEndpoint(URL).build();

                    service = restAdapter.create(MillionaireService.class);

                }

            }

        }

        return service;
    }


}
