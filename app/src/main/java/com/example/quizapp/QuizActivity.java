package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

// SOLID
// SRP - klasa implementuje jedno Activity
// OCP - metody odpowiedzialne są za jedną, prostą czynność

public class QuizActivity extends AppCompatActivity {

    String setName;
    QuestionSet questionSet;
    int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        setName = intent.getStringExtra("SET_NAME");
        questionSet = QuestionSet.getInstance();
        questionSet.questionSet.clear();
        currentQuestion = 0;

        drawQuestions();
        loadQuestion();
    }

    public void drawQuestions()
    {
        try
        {
            /*InputStream instream = openFileInput(getFilesDir().toString() + "/Sets/" + setName + ".txt");
            FileInputStream fis = this.openFileInput(getFilesDir().toString() + "/Sets/" + setName + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);*/

            File file;
            if (setName != null) {
                // If we have the set's filename (standard)
                file = new File(getFilesDir().toString() + "/Sets/" + setName + ".txt");
            } else {
                // If we don't have the set's filename (when testing)
                file = new File(getFilesDir().toString() + "/Sets/PlikDoTestowania.txt");
                if(!file.exists()){
                    TestFileCreation temp = new TestFileCreation();
                    temp.createTestFile(file, 0);
                }
            }


            BufferedReader buffreader = new BufferedReader(new FileReader(file));

            String line;
            try
            {
                while ((line = buffreader.readLine()) != null && questionSet.questionSet.size() < 40)
                {
                    Question question = new Question();
                    question.question = line;
                    String [] answers = new String[4];
                    for (int i = 0; i < 4; i++)
                        answers[i] = buffreader.readLine();
                    question.answers = answers;
                    question.correctAnswer = buffreader.readLine().charAt(0) - '0';

                    questionSet.questionSet.add(question);
                    //System.out.println(question.answers[0] + " " + question.correctAnswer + " " + question.question);
                    //Toast.makeText(QuizActivity.this, question.answers[0] + " " + question.correctAnswer + " " + question.question,
                            //Toast.LENGTH_SHORT).show();

                }

                while(questionSet.questionSet.size() > 40)
                {
                    Random r = new Random();
                    int rand = r.nextInt(questionSet.questionSet.size());
                    questionSet.questionSet.remove(rand);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            String error="";
            error=e.getMessage();
        }
    }

    public void loadQuestion()
    {
        ((TextView)findViewById(R.id.textView3)).setText(questionSet.questionSet.get(currentQuestion).question);
        ((TextView)findViewById(R.id.textView7)).setText("A. " + questionSet.questionSet.get(currentQuestion).answers[0]);
        ((TextView)findViewById(R.id.textView6)).setText("B. " + questionSet.questionSet.get(currentQuestion).answers[1]);
        ((TextView)findViewById(R.id.textView5)).setText("C. " + questionSet.questionSet.get(currentQuestion).answers[2]);
        ((TextView)findViewById(R.id.textView4)).setText("D. " + questionSet.questionSet.get(currentQuestion).answers[3]);

        ((TextView)findViewById(R.id.textView2)).setText((currentQuestion+1) + " out of " + questionSet.questionSet.size());
    }

    public void prevQuestion(View v)
    {
        if(currentQuestion > 0)
        {
            currentQuestion--;
            loadQuestion();
            setColors();
        }
    }

    public void nextQuestion(View v)
    {
        if(currentQuestion < questionSet.questionSet.size()-1)
        {
            currentQuestion++;
            loadQuestion();
            setColors();
        }
    }

    public void clickAnswer(View v)
    {
        questionSet.questionSet.get(currentQuestion).answerPicked = (v.getTag().toString().charAt(0)-'0');
        System.out.println(questionSet.questionSet.get(currentQuestion).answerPicked);

        //((TextView)v).setTextColor(getResources().getColor(R.color.design_default_color_primary));
        setColors();

        //ColorDrawable buttonColor = (ColorDrawable) findViewById(R.id.button5).getBackground();
        //((TextView)v).setBackgroundColor(buttonColor.getColor());

    }

    public void setColors()
    {
        ((TextView)findViewById(R.id.textView7)).setTextColor(0xFF808080);
        ((TextView)findViewById(R.id.textView6)).setTextColor(0xFF808080);
        ((TextView)findViewById(R.id.textView5)).setTextColor(0xFF808080);
        ((TextView)findViewById(R.id.textView4)).setTextColor(0xFF808080);

        if((questionSet.questionSet.get(currentQuestion).answerPicked) == -1)
            return;

        TextView tv = ((LinearLayout)findViewById(R.id.linearAnswers)).findViewWithTag(questionSet.questionSet.get(currentQuestion).answerPicked + "");
        tv.setTextColor(getResources().getColor(R.color.design_default_color_primary));
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml
        // which should be inside res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            DialogFragment ptdf = new EndGameDialogFragment(setName, questionSet.countAnswers(), questionSet.questionSet.size());
            ptdf.show(getSupportFragmentManager(), "lol");
        }
        return super.onOptionsItemSelected(item);
    }
}