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
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Test2 {

    private static final String QUIZAPP_PACKAGE
            = "com.example.quizapp";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final int TIMEOUT = 5000;
    private UiDevice device;

    @Before
    public void startQuickplayActivity(){
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


        device.wait(Until.hasObject(By.textContains("Choose a set and play")), TIMEOUT);

        UiObject chooseSetButton = device.findObject(new UiSelector()
                .textContains("Choose a set and play"));

        try {
            if(chooseSetButton.exists() && chooseSetButton.isEnabled()) {
                chooseSetButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        device.wait(Until.hasObject(By.textContains("plikdotestowania")), TIMEOUT);

        UiObject setButton = device.findObject(new UiSelector()
                .textContains("plikdotestowania"));

        try {
            if(setButton.exists() && setButton.isEnabled()) {
                setButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }


        device.wait(Until.hasObject(By.textContains("quickplay")), TIMEOUT);

        UiObject modeButton = device.findObject(new UiSelector()
                .textContains("quickplay"));

        try {
            if(modeButton.exists() && modeButton.isEnabled()) {
                modeButton.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileLoaded(){
        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"quickplayQuestion")), TIMEOUT);

        UiObject2 question = device.findObject(By.res(QUIZAPP_PACKAGE,"quickplayQuestion"));

        assertFalse(question.getText().isEmpty());
    }

    @Test
    public void changeSelectedAnswer(){
        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"quickplayQuestion")), TIMEOUT);

        UiObject2 answerA = device.findObject(By.res(QUIZAPP_PACKAGE,"quickplayAnswerA"));

        answerA.click();

        UiObject2 answerC = device.findObject(By.res(QUIZAPP_PACKAGE,"quickplayAnswerC"));

        assertTrue(answerC.isClickable());
    }

    @Test
    public void exitQuickplay(){
        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"quickplayQuestion")), TIMEOUT);

        UiObject2 exitButton = device.findObject(By.res(QUIZAPP_PACKAGE,"quickplayReturn"));

        exitButton.click();

        device.wait(Until.hasObject(By.res(QUIZAPP_PACKAGE,"textView8")), TIMEOUT);

        UiObject2 check = device.findObject(By.res(QUIZAPP_PACKAGE,"textView8"));

        assertEquals("Quiz App", check.getText());
    }

}
