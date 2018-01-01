package com.example.patrice.jokedisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import com.example.tegan.myapplication.backend.myApi.model.JokeBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JokeActivity extends AppCompatActivity {
    private JSONArray mJokes;
//    private ArrayList<HashMap>  mJokes;
    private int mNumjokes;
    private int mCurrentJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intentThatStartedThisActivity = getIntent();
        if(savedInstanceState == null && intentThatStartedThisActivity != null){
            String jokesJson = intentThatStartedThisActivity.getStringExtra("jokes");
            try {
                mJokes = new JSONArray(jokesJson);
            } catch (JSONException e) {
                e.printStackTrace();
                // rawr!!!! ish bad to eat exceptions grrr
            }
            if (mJokes != null) {
                mNumjokes = mJokes.length();
                mCurrentJoke = 0;
            }
//            mJokes = (ArrayList<HashMap>) intentThatStartedThisActivity.getSerializableExtra("jokes");
//            mNumjokes = mJokes.size();
//            mCurrentJoke = 0;
        }
        setJoke();
    }
    public void navigateNext(View view){
        if(mCurrentJoke < mNumjokes){
            mCurrentJoke++;
            setJoke();
        }
    }

    public void navigatePrevious(View view){
        if(mCurrentJoke > 0){
            mCurrentJoke--;
            setJoke();
        }
    }

    private void setJoke(){
        checkVisibility();

        TextView joke = findViewById(R.id.tv_joke_question);
        TextView jokeAnswer = findViewById(R.id.tv_joke_answer);

        String question = "";
        String answer = "";
        try {
            JSONObject jokeObj = mJokes.getJSONObject(mCurrentJoke);
            question = jokeObj.getString("question");
            answer = jokeObj.getString("answer");
        } catch (JSONException e) {
            e.printStackTrace();
            // gtrrrrr
        }

        joke.setText(question);
        jokeAnswer.setText(answer);
    }

    private void checkVisibility(){
        Button previousButton = findViewById(R.id.button_previous_joke);
        Button nextButton = findViewById(R.id.button_next_joke);
        Button showAnswer = findViewById(R.id.button_show_answer);
        TextView answer = findViewById(R.id.tv_joke_answer);

        answer.setVisibility(View.GONE);
        showAnswer.setVisibility(View.VISIBLE);


        if(mCurrentJoke == 0){
            previousButton.setVisibility(View.GONE);
        }else{
            previousButton.setVisibility(View.VISIBLE);
        }

        if(mCurrentJoke == mNumjokes - 1){
            nextButton.setVisibility(View.GONE);
        }else{
            nextButton.setVisibility(View.VISIBLE);
        }
    }

    public void showAnswer(View view){
        Button showAnswer = findViewById(R.id.button_show_answer);
        TextView answer = findViewById(R.id.tv_joke_answer);

        showAnswer.setVisibility(View.GONE);
        answer.setVisibility(View.VISIBLE);
    }
}
