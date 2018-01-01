package com.example.patrice.app.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Tegan on 12/31/2017.
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkTextIsValid(){
        onView(withId(R.id.button_tell_joke)).perform(click());
        onView(withId(R.id.tv_joke_question)).check(matches(withText("Why don't elephants ride tricycles?")));
    }
}
