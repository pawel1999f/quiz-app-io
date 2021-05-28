/*
package com.example.quizapp;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SelectSetActivityTest {

    @Before
    public void setUp() {
        ActivityScenario<ImportActivity> preScenario = ActivityScenario.launch(ImportActivity.class);
        preScenario.onActivity(activity -> {
            activity.uploadFile(activity.findViewById(R.id.fileImportButton));
        });
    }

    @Test
    public void readAllSets() {
        ActivityScenario<SelectSetActivity> scenario = ActivityScenario.launch(SelectSetActivity.class);
        scenario.onActivity(activity -> {
            activity.loadSets();
            assertEquals(activity.sets.length, activity.getFilesDir().listFiles().length);
        });
    }

    @Test
    public void setEnteredQuickplay() {
        String files[] = new String[]{"example.txt"};

        ActivityScenario<SelectSetActivity> scenario = ActivityScenario.launch(SelectSetActivity.class);
        scenario.onActivity(activity -> {
            activity.enterSet(0);

            Intent expectedIntent = new Intent(activity, QuickplayActivity.class);
            expectedIntent.putExtra("msg", files[0]);

            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
            assertEquals(expectedIntent.getStringExtra("msg"), actual.getStringExtra("msg"));

        });
    }

    @Test
    public void setEnteredQuiz() {
        String files[] = new String[]{"example.txt"};

        ActivityScenario<SelectSetActivity> scenario = ActivityScenario.launch(SelectSetActivity.class);
        scenario.onActivity(activity -> {
            activity.enterSet(0);

            Intent expectedIntent = new Intent(activity, QuizActivity.class);
            expectedIntent.putExtra("msg", files[0]);

            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
            assertEquals(expectedIntent.getStringExtra("msg"), actual.getStringExtra("msg"));

        });
    }
}
*/
