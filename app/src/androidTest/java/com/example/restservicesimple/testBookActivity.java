package com.example.restservicesimple;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.rule.ActivityTestRule;


@RunWith(AndroidJUnit4.class)
public class testBookActivity {

    private int fromTime;


    @Rule
    public ActivityTestRule<bookActivity> activityRule
            = new ActivityTestRule<>(bookActivity.class);


    @Test
    public void test(){
        Espresso.onView(withId(R.id.fromTime)).perform(typeText("hello"));
        Espresso.onView(withId(R.id.bookButton)).perform(click());

   }



}