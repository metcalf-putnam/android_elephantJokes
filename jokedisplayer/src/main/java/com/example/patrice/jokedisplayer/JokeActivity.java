package com.example.patrice.jokedisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JokeActivity extends AppCompatActivity {
private String mJoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        TextView joke = findViewById(R.id.tv_joke);
        Intent intentThatStartedThisActivity = getIntent();
        if(savedInstanceState == null && intentThatStartedThisActivity != null){
                mJoke = intentThatStartedThisActivity.getStringExtra("joke");
                joke.setText(mJoke);
        }else{
            joke.setText(R.string.error_no_joke);
        }
    }
}
