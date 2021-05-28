package com.example.quizapp;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class ImportActivityTest {

    @Test
    public void uploadFile() {
        ActivityScenario<ImportActivity> scenario = ActivityScenario.launch(ImportActivity.class);
        scenario.onActivity(activity -> {
            int num = activity.getFilesDir().listFiles().length;

            File dst = new File (activity.getFilesDir().toString() + "/UploadTest.txt");

            if(dst.exists()){
                dst.delete();
            }

            TestFileCreation temp = new TestFileCreation();
            temp.createTestFile(dst, 0);

            assertEquals(num+1, activity.getFilesDir().listFiles().length);
        });
    }
}