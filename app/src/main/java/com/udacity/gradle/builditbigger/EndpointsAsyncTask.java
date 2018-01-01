package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.tegan.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;


/**
 * Created by Patrice on 12/27/2017.
 */

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    final EndpointsAsyncTaskCallback mCallback;


    public interface EndpointsAsyncTaskCallback{
        void onAsyncTaskComplete(String result);
    }

    public EndpointsAsyncTask(EndpointsAsyncTaskCallback callback){
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                     .setRootUrl("https://eternal-petal-190501.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.jokesList().execute().getJson();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onAsyncTaskComplete(result);
    }
}