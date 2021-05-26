package com.example.quizapp;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ImportActivityTest {

    @Test
    public void addedFile() {
        ActivityScenario<ImportActivity> scenario = ActivityScenario.launch(ImportActivity.class);
        scenario.onActivity(activity -> {
            int num = activity.getFilesDir().listFiles().length;
            activity.uploadFile(activity.findViewById(R.id.fileImportButton));
            assertEquals(num+1, activity.getFilesDir().listFiles().length);
        });
    }
}