package com.example.quizapp;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
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

@RunWith(AndroidJUnit4.class)
public class Test7 {


        @Rule
        public ActivityScenarioRule<MainActivity> activityRule =
                new ActivityScenarioRule<>(MainActivity.class);

        @Test
        public void openImportFromMenu() {
            onView(withId(R.id.button2)).perform(click());
            onView(withId(R.id.fileImportButton)).check(matches(isDisplayed()));
        }

        @Test
        public void openSetChooseFromMenu() {
            onView(withId(R.id.button)).perform(click());
            onView(withId(R.id.linearSets)).check(matches(isDisplayed()));
        }

        @Test
        public void openQuickplayFromSetChoose() {
            onView(withId(R.id.button)).perform(click());
            onView(withText("PlikDoTestowania")).perform(click());
            onView(withText("quickplay")).perform(click());
            onView(withId(R.id.quickplayAnswerA)).check(matches(isDisplayed()));
        }
}



