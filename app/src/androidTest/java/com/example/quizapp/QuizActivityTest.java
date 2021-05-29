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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class QuizActivityTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activeRule =
            new ActivityScenarioRule<QuizActivity>(QuizActivity.class);

    @Test
    public void questionLoaded(){
        final int[] q = new int[1];
        activeRule.getScenario().onActivity(activity -> {
            q[0] = activity.currentQuestion;
        });
        assertEquals(0,q[0]);
    }

    @Test
    public void questionSetLoaded(){
        final QuestionSet[] q = new QuestionSet[1];
        activeRule.getScenario().onActivity(activity -> {
            q[0] = activity.questionSet;
        });
        assertNotNull(q[0]);
    }
    
    @Test
    public void questionIsDisplayed(){
        //checks if the question content is not empty
        onView(withId(R.id.textView3)).check(matches(not(withText(""))));
    }

    @Test
    public void nextQuestionIsProperlyDisplayed(){
        //finds a "next question" button and clicks it. Checks if the next question
        //is not empty
        onView(withId(R.id.textView7)).perform(click());
        onView(withId(R.id.button6)).perform(click());
        onView(withId(R.id.textView3)).check(matches(not(withText(""))));

    }

    @Test
    public void exitQuiz(){
        //finds an "exit" button and clicks it. Checks if the menu is displayed
        onView(withText("end quiz")).perform(click());
        onView(withText("yes")).perform(click());
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }
}
