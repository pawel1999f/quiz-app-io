package com.example.quizapp;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoActivityResumedException;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagKey;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class Test5 {


    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    @Test
    public void showExitDialogue() {
        onView(withId(R.id.mybutton)).perform(click());
        onView(withText("yes")).check(matches(isDisplayed()));
    }

    @Test
    public void quizExit() {
        onView(withId(R.id.mybutton)).perform(click());
        onView(withText("yes")).perform(click());
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }

    @Test
    public void finalWindowExit() {
        onView(withId(R.id.mybutton)).perform(click());
        onView(withText("yes")).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button)).check(matches(isDisplayed()));
    }

    @Test
    public void startQuizAgain() {
        onView(withId(R.id.mybutton)).perform(click());
        onView(withText("yes")).perform(click());
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
    }
}



