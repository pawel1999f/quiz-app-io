package com.example.quizapp;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class QuickplayActivityTest {

    Question question;

    @Before
    public void setUp() {
        ActivityScenario<QuickplayActivity> scenario = ActivityScenario.launch(QuickplayActivity.class);
        scenario.onActivity(activity -> {
            activity.getRandomQuestion();
            question =  activity.question;
        });
    }

    @Test
    public void questionLoaded() {

        ActivityScenario<QuickplayActivity> scenario = ActivityScenario.launch(QuickplayActivity.class);
        scenario.onActivity(activity -> {
            activity.getRandomQuestion();
            assertNotNull(activity.question);
        });
    }

    @Test
    public void setEnteredQuickplay() {

        ActivityScenario<QuickplayActivity> scenario = ActivityScenario.launch(QuickplayActivity.class);
        scenario.onActivity(activity -> {
            boolean isCorrect = activity.isAnswerCorrect(0);
            boolean theSameWithQuestion = false;
            if(question.correctAnswer == 0)
                theSameWithQuestion = true;

            assertTrue(isCorrect == theSameWithQuestion);

        });
    }

}