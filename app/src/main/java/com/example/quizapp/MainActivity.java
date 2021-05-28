package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// SOLID
// SRP - klasa implementuje jedno Activity
// OCP - metody odpowiedzialne są za jedną, prostą czynność

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSets(View view) {
        Intent intent = new Intent(this, SelectSetActivity.class);
        startActivity(intent);
    }

    public void goToImport(View view) {
        Intent intent = new Intent(this, ImportActivity.class);
        startActivity(intent);
    }

}