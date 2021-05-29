package com.example.quizapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ImportActivityTest {

    @Rule
    public ActivityScenarioRule<ImportActivity> activeRule =
            new ActivityScenarioRule<ImportActivity>(ImportActivity.class);

    @Test
    public void uploadFile() {

        activeRule.getScenario().onActivity(activity -> {

            File dir = new File (activity.getFilesDir(), "Zestawy");
            File dst = new File (dir, "UploadTest.txt");

            if(!dir.exists()){
                dir.mkdir();
            }

            if(dst.exists()){
                dst.delete();
            }

            int num = Objects.requireNonNull(dir.listFiles()).length;

            TestFileCreation temp = new TestFileCreation();
            temp.createTestFile(dst, 0);

            assertEquals(num+1, Objects.requireNonNull(dir.listFiles()).length);
        });
    }
}