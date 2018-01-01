package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.patrice.jokedisplayer.JokeActivity;


public class MainActivity extends AppCompatActivity
    implements EndpointsAsyncTask.EndpointsAsyncTaskCallback{
    private String mJokes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null && savedInstanceState.containsKey("jokes")){
            mJokes = savedInstanceState.getString("jokes");
            TextView button = findViewById(R.id.button_tell_joke);
            button.setVisibility(View.VISIBLE);
        }
        if(mJokes == null || mJokes.isEmpty()){
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            new EndpointsAsyncTask(this).execute();
        }
    }


    public void tellJoke(View view) {
        Context context = MainActivity.this;
        Class destinationActivity = JokeActivity.class;
        Intent jokeIntent = new Intent(context, destinationActivity);
        jokeIntent.putExtra("jokes", mJokes);
        startActivity(jokeIntent);
    }


    @Override
    public void onAsyncTaskComplete(String results) {
        TextView button = findViewById(R.id.button_tell_joke);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        button.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        mJokes = results;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("jokes", mJokes);
    }

}
