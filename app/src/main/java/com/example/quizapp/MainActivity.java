package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSets(View view) {
        Intent intent = new Intent(this, SelectSetActivity.class);
        //startActivity(intent);
    }

    public void goToImport(View view) {
        Intent intent = new Intent(this, ImportActivity.class);
        //startActivity(intent);
    }
}