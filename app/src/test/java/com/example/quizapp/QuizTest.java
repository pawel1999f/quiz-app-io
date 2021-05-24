package com.projektIO.quiz;

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
    public ActivityScenarioRule<Quiz> activeRule =
            new ActivityScenarioRule<Quiz>(Quiz.class);

    @Test
    public void questionIsDisplayed(){
        //checks if the question content is not empty
        onView(withId(R.id.questionText)).check(matches(not(withText(""))));
    }

    @Test
    public void nextQuestionIsProperlyDisplayed(){
        //finds a "next question" button and clicks it. Checks if the next question
        //is not empty
        onView(withId(R.id.nextQuestionButton)).perform(click());
        onView(withId(R.id.questionText)).check(matches(not(withText(""))));
    }

    @Test
    public void exitQuickplayToMainMenu(){
        //finds an "exit" button and clicks it. Checks if the statistics menu is displayed
        onView(withId(R.id.exitButton)).perform(click());
        onView(withId(R.id.statisticsMenuText)).check(matches(isDisplayed()));
    }
}
