package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuickplayActivity extends AppCompatActivity {

    public Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickplay);
    }

    public void getRandomQuestion()
    {
    }

    public boolean isAnswerCorrect(int ans)
    {
        return false;
    }
}