package com.example;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jokes {
    private JSONArray mJokes = null;

    public Jokes(){
        ClassLoader classLoader = getClass().getClassLoader();
        JSONParser parser = new JSONParser();

        try {
            mJokes = (JSONArray) parser.parse(new InputStreamReader(classLoader.getResourceAsStream("jokes.json")));
            //mJokes = (JSONArray) parser.parse(new FileReader(classLoader.getResource("jokes.json").getFile()));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public JSONArray getJokes(){
        return mJokes;
    }
}
