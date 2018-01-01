/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Tegan.myapplication.backend;

import com.example.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Tegan.example.com",
                ownerName = "backend.myapplication.Tegan.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    private static Jokes mJokesList = new Jokes();

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
    @ApiMethod(name = "jokesList")
    public JokesJSON jokes() {
        JSONArray jokeArray = mJokesList.getJokes();
        JokesJSON ret = new JokesJSON();
        ret.setJson(jokeArray.toJSONString());
        return ret;
//        List<JokeBean> jokes = new ArrayList<>();
//        for (int i = 0; i < jokeArray.size(); i++) {
//            JSONObject j = jokeArray.
//            String q = j.get("question");
//
//        }
//        JokeBean joke = new JokeBean();
//        joke.setAnswer("testaNSWER");
//        joke.setQuestion("testQuestion");
//        jokes.add(joke);

//        return jokes;
    }
}
