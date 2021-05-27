package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class QuickplayActivity extends AppCompatActivity {

    public Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickplay);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("SET_NAME");

        Toast.makeText(QuickplayActivity.this, message,
                Toast.LENGTH_SHORT).show();
    }

    public void getRandomQuestion()
    {
    }

    public boolean isAnswerCorrect(int ans)
    {
        return false;
    }
}