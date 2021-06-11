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
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagKey;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class Test4 {


    @Rule
    public ActivityScenarioRule<QuickplayActivity> activityRule =
            new ActivityScenarioRule<>(QuickplayActivity.class);

    @Test
    public void checkAnswerQuickplay() {
        onView(withId(R.id.quickplayAnswerA)).perform(click());
        onView(withText("CHECK")).perform(click());
        onView(withId(R.id.quickplayNext)).check(matches(isDisplayed()));
    }

    @Test
    public void nextQuestionQuickplay() {
        onView(withId(R.id.quickplayAnswerA)).perform(click());
        onView(withText("CHECK")).perform(click());
        onView(withId(R.id.quickplayNext)).perform(click());
        onView(withId(R.id.quickplayAnswerA)).check(matches(isDisplayed()));
    }
}



