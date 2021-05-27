package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          goToImport();
                                      }
                                  }
        );

        Button quickplayButton = (Button) findViewById(R.id.quickplayChooseButton);
        quickplayButton.setOnClickListener(view -> goToQuickplay());
    }

    public void goToSets(View view) {
        Intent intent = new Intent(this, SelectSetActivity.class);
        startActivity(intent);
    }

    public void goToQuickplay() {
        Intent intent = new Intent(this, QuickplayActivity.class);
        startActivity(intent);
    }

    public void goToImport() {
        Intent intent = new Intent(this, ImportActivity.class);
        startActivity(intent);
    }
}