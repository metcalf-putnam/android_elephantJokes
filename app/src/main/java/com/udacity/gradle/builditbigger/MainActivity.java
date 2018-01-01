package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.patrice.jokedisplayer.JokeActivity;



public class MainActivity extends AppCompatActivity
    implements EndpointsAsyncTask.EndpointsAsyncTaskCallback{
    private String mJokes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new EndpointsAsyncTask(this).execute();
        //new EndpointsAsyncTask(this).execute(new Pair<Context, String>(this, "Manfred"));
       // mJokes = new Jokes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //Toast.makeText(this, mJokes.getJoke(), Toast.LENGTH_SHORT).show();
        Context context = MainActivity.this;
        Class destinationActivity = JokeActivity.class;
        Intent jokeIntent = new Intent(context, destinationActivity);
        jokeIntent.putExtra("jokes", mJokes);
        startActivity(jokeIntent);
    }


    @Override
    public void onAsyncTaskComplete(String results) {
        //make "tell joke" button visible (default should be gone), so cannot press button without results
        TextView button = findViewById(R.id.button_tell_joke);
        button.setVisibility(View.VISIBLE);
        mJokes = results;
        //Toast.makeText(MainActivity.this, "I'm a puppy "  + results, Toast.LENGTH_SHORT).show();
    }
}
