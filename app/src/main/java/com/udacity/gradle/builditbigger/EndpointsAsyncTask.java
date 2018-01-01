package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.example.tegan.myapplication.backend.myApi.MyApi;
//import com.example.tegan.myapplication.backend.myApi.model.JokeBean;
//import com.example.tegan.myapplication.backend.myApi.model.JokeBeanCollection;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Tegan on 12/27/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private EndpointsAsyncTaskCallback mCallback;


    public interface EndpointsAsyncTaskCallback{
        public void onAsyncTaskComplete(String result);
    }

    public EndpointsAsyncTask(EndpointsAsyncTaskCallback callback){
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once
            // Test against localhost app server
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            // Test against deployed app server
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
            //         .setRootUrl("https://eternal-petal-190501.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

//        context = params[0].first;
//        String name = params[0].second;

        try {
            return myApiService.jokesList().execute().getJson();
            //return myApiService.sayHi(name).execute().getData();
//            MyApi.JokesList j = myApiService.jokesList();
//            JokeBeanCollection c = j.execute();
//            List<JokeBean> jokes = c.getItems();
//            return jokes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        mCallback.onAsyncTaskComplete(result);
    }
}