package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndScreenActivity extends AppCompatActivity {

    String setName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        Intent intent = getIntent();
        setName = intent.getStringExtra("SET_NAME");
        int points = intent.getIntExtra("POINTS", -1);
        int maxPoints = intent.getIntExtra("MAX_POINTS", -1);

        ((TextView)findViewById(R.id.textView)).setText("Zdobyłeś " + points + "/" + maxPoints + " punktów (" + (points*100/maxPoints) + "%)!");
    }

    public void backToMenu(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void tryAgain(View v)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("SET_NAME", setName);
        startActivity(intent);
    }
}