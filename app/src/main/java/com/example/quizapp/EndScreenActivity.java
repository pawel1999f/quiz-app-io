package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// SOLID
// SRP - klasa implementuje jedno Activity
// OCP - metody odpowiedzialne są za jedną, prostą czynność

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

        String text = "Zdobyłeś " + points + "/" + maxPoints +
                " punktów (" + (points*100/maxPoints) + "%)!";
        ((TextView)findViewById(R.id.textView)).setText(text);
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