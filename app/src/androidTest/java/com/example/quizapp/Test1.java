package com.example.quizapp;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.File;
import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Test1 {

    private static final String QUIZAPP_PACKAGE
            = "com.example.quizapp";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final int TIMEOUT = 5000;
    private UiDevice device;


    private static final String INCORRECT_FILENAME
            = "testingfile.txt";
    private static final String CORRECT_FILENAME
            = "TestingFileFinal.txt";

    @Before
    public void startImportActivity(){
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        device.pressHome();

        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(QUIZAPP_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(QUIZAPP_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);


        device.wait(Until.hasObject(By.textContains("Import a new set")), TIMEOUT);

        UiObject importButton = device.findObject(new UiSelector()
                .textContains("Import a new set"));

        try {
            if(importButton.exists() && importButton.isEnabled()) {
                importButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void importIncorrectFile(){

        device.wait(Until.hasObject(By.textContains("Browse")), TIMEOUT);

        UiObject browseButton = device.findObject(new UiSelector()
                .textContains("Browse"));

        try {
            if(browseButton.exists() && browseButton.isEnabled()) {
                browseButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.textContains(INCORRECT_FILENAME)), TIMEOUT);

        UiObject fileButton = device.findObject(new UiSelector()
                .textContains(INCORRECT_FILENAME));

        try {
            if(fileButton.exists() && fileButton.isEnabled()) {
                fileButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"filePathText")), TIMEOUT);

        UiObject2 fileName = device.findObject(By.res(QUIZAPP_PACKAGE,"filePathText"));

        assertEquals("File name", fileName.getText());
    }


    @Test
    public void importCorrectFile(){

        device.wait(Until.hasObject(By.textContains("Browse")), TIMEOUT);

        UiObject browseButton = device.findObject(new UiSelector()
                .textContains("Browse"));

        try {
            if(browseButton.exists() && browseButton.isEnabled()) {
                browseButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.textContains(CORRECT_FILENAME)), TIMEOUT);

        UiObject fileButton = device.findObject(new UiSelector()
                .textContains(CORRECT_FILENAME));

        try {
            if(fileButton.exists() && fileButton.isEnabled()) {
                fileButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"filePathText")), TIMEOUT);

        UiObject2 fileName = device.findObject(By.res(QUIZAPP_PACKAGE,"filePathText"));

        assertEquals(CORRECT_FILENAME, fileName.getText());
    }


    @Test
    public void importingCorrectFilename(){

        device.wait(Until.hasObject(By.textContains("Browse")), TIMEOUT);

        UiObject browseButton = device.findObject(new UiSelector()
                .textContains("Browse"));

        try {
            if(browseButton.exists() && browseButton.isEnabled()) {
                browseButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.textContains(CORRECT_FILENAME)), TIMEOUT);

        UiObject fileButton = device.findObject(new UiSelector()
                .textContains(CORRECT_FILENAME));

        try {
            if(fileButton.exists() && fileButton.isEnabled()) {
                fileButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"fileNameText")), TIMEOUT);

        UiObject2 newFileName = device.findObject(By.res(QUIZAPP_PACKAGE,"fileNameText"));

        newFileName.setText("CorrectFileName");


        UiObject importButton = device.findObject(new UiSelector()
                .textContains("Import"));

        try {
            if(importButton.exists() && importButton.isEnabled()) {
                importButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void importingIncorrectFilename(){

        device.wait(Until.hasObject(By.textContains("Browse")), TIMEOUT);

        UiObject browseButton = device.findObject(new UiSelector()
                .textContains("Browse"));

        try {
            if(browseButton.exists() && browseButton.isEnabled()) {
                browseButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.textContains(CORRECT_FILENAME)), TIMEOUT);

        UiObject fileButton = device.findObject(new UiSelector()
                .textContains(CORRECT_FILENAME));

        try {
            if(fileButton.exists() && fileButton.isEnabled()) {
                fileButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"filePathText")), TIMEOUT);

        UiObject2 newFileName = device.findObject(By.res(QUIZAPP_PACKAGE,"fileNameText"));

        newFileName.setText("Incorrect File Name");


        UiObject importButton = device.findObject(new UiSelector()
                .textContains("Import"));

        try {
            if(importButton.exists() && importButton.isEnabled()) {
                importButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
}
