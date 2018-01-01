package com.example.Tegan.myapplication.backend;

import com.example.Jokes;

import org.json.simple.JSONArray;

import java.io.Serializable;

/**
 * Created by Tegan on 12/30/2017.
 */

public class JokeBean implements Serializable {


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private String question = null;
    private String answer = null;

    public JokeBean(){
    }

}
