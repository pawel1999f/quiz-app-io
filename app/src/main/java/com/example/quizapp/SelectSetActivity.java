package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SelectSetActivity extends AppCompatActivity {

    public String sets[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_set);

        loadSets();
    }

    public void loadSets()
    {
        sets = new String[]{};
    }

    public void enterSet(int val)
    {

    }
}