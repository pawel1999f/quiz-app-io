package com.example.quizapp;

import android.app.Activity;
import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class SelectSetActivityTest {

    @Rule
    public ActivityScenarioRule<SelectSetActivity> activeRule =
            new ActivityScenarioRule<SelectSetActivity>(SelectSetActivity.class);

    /*@Before
    public void initialize(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                TestFileCreation tfc = new TestFileCreation();
                tfc.createDefaultFile();
                // java.lang.NullPointerException: Attempt to invoke virtual method
                // 'java.io.File android.content.Context.getFilesDir()' on a null object reference
            }
        });
    }*/

    @Test
    public void readAllSets(){

        activeRule.getScenario().onActivity(activity -> {
            activity.loadSets();
            File dir = new File (activity.getFilesDir(),"Zestawy");
            assertEquals(dir.listFiles().length, activity.sets.length);
        });

    }

    @Test
    public void selectSetEnteredQuickplay(){

        onView(withText("PlikDoTestowania")).perform(click());
        onView(withText("quickplay")).perform(click());

        onView(withId(R.id.quickplayQuestion)).check(matches(isDisplayed()));

    }

    @Test
    public void selectSetEnteredQuiz(){

        onView(withText("PlikDoTestowania")).perform(click());
        onView(withText("quiz")).perform(click());

        onView(withId(R.id.textView3)).check(matches(isDisplayed()));

    }
}
