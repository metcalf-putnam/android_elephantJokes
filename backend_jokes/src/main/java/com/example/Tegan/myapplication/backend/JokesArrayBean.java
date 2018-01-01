package com.example.Tegan.myapplication.backend;

import com.example.Jokes;

import org.json.simple.JSONArray;

import java.util.List;

/**
 * Created by Tegan on 12/30/2017.
 */

public class JokesArrayBean {
    public List<JokeBean> getJokes() {
        return jokes;
    }

    public void setJokes(List<JokeBean> jokes) {
        this.jokes = jokes;
    }

    private List<JokeBean> jokes = null;
    public JokesArrayBean(){
    }
}
