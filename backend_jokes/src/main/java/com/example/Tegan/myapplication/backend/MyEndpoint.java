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

    private static final Jokes mJokesList = new Jokes();

    /**
     * endpoint method that returns a json string of hilarious elephant jokes
     */

    @ApiMethod(name = "jokesList")
    public JokesJSON jokes() {
        JSONArray jokeArray = mJokesList.getJokes();
        JokesJSON ret = new JokesJSON();
        ret.setJson(jokeArray.toJSONString());
        return ret;

    }
}
