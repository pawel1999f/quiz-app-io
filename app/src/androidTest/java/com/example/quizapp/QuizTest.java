package com.example.quizapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class QuizTest {

    @Rule
    public ActivityScenarioRule<QuickplayActivity> activeRule =
            new ActivityScenarioRule<QuickplayActivity>(QuickplayActivity.class);

    @Test
    public void questionIsDisplayed(){
        //checks if the question content is not empty
        onView(withId(R.id.quickplayQuestion)).check(matches(not(withText(""))));
    }

    @Test
    public void nextQuestionIsProperlyDisplayed(){
        //finds a "next question" button and clicks it. Checks if the next question
        //is not empty
        onView(withId(R.id.quickplayAnswerA)).perform(click());
        onView(withId(R.id.quickplayNext)).perform(click());
        onView(withId(R.id.quickplayNext)).perform(click());
        onView(withId(R.id.quickplayQuestion)).check(matches(not(withText(""))));
    }

    @Test
    public void exitQuickplayToMainMenu(){
        //finds an "exit" button and clicks it. Checks if the statistics menu is displayed
        onView(withId(R.id.quickplayReturn)).perform(click());
        onView(withId(R.id.button)).check(matches(isDisplayed()));
    }
}
