package com.example.patrice.jokedisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JokeActivity extends AppCompatActivity {
    private JSONArray mJokes;
    private int mNumjokes;
    private int mCurrentJoke;
    private boolean mAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intentThatStartedThisActivity = getIntent();
        if(savedInstanceState != null){
            if(savedInstanceState.containsKey("jokes")){
                String jokesJson = savedInstanceState.getString("jokes");
                retrieveJokes(jokesJson);
            }
            if(savedInstanceState.containsKey("currentJoke")){
                mCurrentJoke = savedInstanceState.getInt("currentJoke");
            }
            if(savedInstanceState.containsKey("answered")){
                mAnswered = savedInstanceState.getBoolean("answered", false);
            }
        } else if(intentThatStartedThisActivity != null){
            String jokesJson = intentThatStartedThisActivity.getStringExtra("jokes");
            retrieveJokes(jokesJson);
            mCurrentJoke = 0;
            mAnswered = false;
        }

        setJoke();
    }
    public void navigateNext(View view){
        if(mCurrentJoke < mNumjokes){
            mCurrentJoke++;
            mAnswered = false;
            setJoke();
        }
    }

    public void navigatePrevious(View view){
        if(mCurrentJoke > 0){
            mCurrentJoke--;
            mAnswered = false;
            setJoke();
        }
    }

    private void retrieveJokes(String jokesJson){
        try {
            mJokes = new JSONArray(jokesJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mJokes != null) {
            mNumjokes = mJokes.length();
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
        }

        joke.setText(question);
        jokeAnswer.setText(answer);
    }

    private void checkVisibility(){
        Button previousButton = findViewById(R.id.button_previous_joke);
        Button nextButton = findViewById(R.id.button_next_joke);
        Button showAnswer = findViewById(R.id.button_show_answer);
        TextView answer = findViewById(R.id.tv_joke_answer);

        if(mAnswered){
            answer.setVisibility(View.VISIBLE);
            showAnswer.setVisibility(View.GONE);
        }else{
            answer.setVisibility(View.GONE);
            showAnswer.setVisibility(View.VISIBLE);
        }

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
        mAnswered = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String jsonString = mJokes.toString();
        outState.putString("jokes", jsonString);
        outState.putInt("currentJoke", mCurrentJoke);
        outState.putBoolean("answered", mAnswered);
        super.onSaveInstanceState(outState);
    }

}
