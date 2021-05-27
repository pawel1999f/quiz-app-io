package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class QuickplayActivity extends AppCompatActivity {

    public Question question;

    private File questionsFile;
    private List<Question> questionsList;
    private ListIterator<Question> questionsListIterator;

    private TextView questionText;
    private Button answerAButton;
    private Button answerBButton;
    private Button answerCButton;
    private Button answerDButton;

    private Button nextButton;
    private Button returnButton;

    private int numberOfAnswers;
    private int numberOfCorrectAnswers;
    private int selectedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickplay);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("SET_NAME");

        Toast.makeText(QuickplayActivity.this, message, Toast.LENGTH_SHORT).show();

        //------------------------------------------------------------

        questionsList = new ArrayList<Question>();

        questionText = (TextView) findViewById(R.id.quickplayQuestion);
        answerAButton = (Button) findViewById(R.id.quickplayAnswerA);
        answerBButton = (Button) findViewById(R.id.quickplayAnswerB);
        answerCButton = (Button) findViewById(R.id.quickplayAnswerC);
        answerDButton = (Button) findViewById(R.id.quickplayAnswerD);

        nextButton = (Button) findViewById(R.id.quickplayNext);
        returnButton = (Button) findViewById(R.id.quickplayReturn);

        numberOfAnswers = 0;
        numberOfCorrectAnswers = 0;
        selectedAnswer = -1;

        //------------------------------------------------------------

        resetButtonsColors();

        // Setting the list up and changing the text on elements
        getRandomQuestion();
        setLayoutText();

        answerAButton.setOnClickListener(view -> selectAnswer(0));
        answerBButton.setOnClickListener(view -> selectAnswer(1));
        answerCButton.setOnClickListener(view -> selectAnswer(2));
        answerDButton.setOnClickListener(view -> selectAnswer(3));

        nextButton.setOnClickListener(view -> goToNextQuestion());
        returnButton.setOnClickListener(view -> returnToMainMenu());

    }

    protected void returnToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    protected void goToNextQuestion() {
        if (selectedAnswer != -1) {
            if (nextButton.getText() == "Sprawdź") {
                checkAnswer();

                nextButton.setText("Następny");
            } else {
                // Updating the statistics
                if (isAnswerCorrect(selectedAnswer)) {
                    numberOfCorrectAnswers++;
                }
                numberOfAnswers++;

                Toast.makeText(QuickplayActivity.this,
                        "Correct answers: " + numberOfCorrectAnswers + "/" + numberOfAnswers +
                                " - " + numberOfCorrectAnswers * 100 / numberOfAnswers + "%",
                        Toast.LENGTH_SHORT).show();

                selectedAnswer = -1;

                //Getting new question and updating layout
                getRandomQuestion();
                resetButtonsColors();
                setLayoutText();
                nextButton.setText("Sprawdź");

            }
        } else {
            Toast.makeText(QuickplayActivity.this,
                    "Select an answer",
                    Toast.LENGTH_SHORT).show();
        }
    }

    protected void selectAnswer(int selAns){
        nextButton.setText("Sprawdź");

        selectedAnswer = selAns;

        resetButtonsColors();
        switch(selectedAnswer) {
            case 0:
                answerAButton.setBackgroundColor(Color.CYAN);
                break;
            case 1:
                answerBButton.setBackgroundColor(Color.CYAN);
                break;
            case 2:
                answerCButton.setBackgroundColor(Color.CYAN);
                break;
            case 3:
                answerDButton.setBackgroundColor(Color.CYAN);
                break;
            default:
                Toast.makeText(QuickplayActivity.this,
                        "Something went wrong while changing color to cyan",
                        Toast.LENGTH_SHORT).show();
                break;
        }

    }

    protected void checkAnswer(){
        switch(selectedAnswer) {
            case 0:
                answerAButton.setBackgroundColor(Color.RED);
                break;
            case 1:
                answerBButton.setBackgroundColor(Color.RED);
                break;
            case 2:
                answerCButton.setBackgroundColor(Color.RED);
                break;
            case 3:
                answerDButton.setBackgroundColor(Color.RED);
                break;
            default:
                Toast.makeText(QuickplayActivity.this,
                        "Something went wrong while changing color to red",
                        Toast.LENGTH_SHORT).show();
                break;
        }

        switch(question.correctAnswer) {
            case 0:
                answerAButton.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                answerBButton.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                answerCButton.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                answerDButton.setBackgroundColor(Color.GREEN);
                break;
            default:
                Toast.makeText(QuickplayActivity.this,
                        "Something went wrong while changing color to green",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void getRandomQuestion()
    {
        // If the question list is empty we need to read data from the file
        if(questionsList.isEmpty()) {
            questionsFile = new File(getFilesDir(), "Zestawy/ZestawDoTestowania.txt");

            try {

                BufferedReader br = new BufferedReader(new FileReader(questionsFile));
                String line;

                // Reading data from file and putting it into a list
                while ((line = br.readLine()) != null) {

                    Question tempQuestion = new Question();

                    tempQuestion.question = line;

                    for (int i = 0; i < 4; i++)
                        tempQuestion.answers[i] = br.readLine();

                    tempQuestion.correctAnswer = Integer.parseInt(br.readLine());

                    questionsList.add(tempQuestion);

                }

                // Shuffling the list
                Collections.shuffle(questionsList);

                // Getting the ListIterator
                questionsListIterator = questionsList.listIterator();

                br.close();

            } catch (IOException e) {
                Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        // If the cursor points at last element - shuffle the list
        // and set the cursor at first element
        if(!questionsListIterator.hasNext()){
            Collections.shuffle(questionsList);
            questionsListIterator = questionsList.listIterator();
        }

        // Assigning the question to be the next element in the list
        question = questionsListIterator.next();

    }

    protected void setLayoutText(){

        questionText.setText(question.question);

        answerAButton.setText(question.answers[0]);
        answerBButton.setText(question.answers[1]);
        answerCButton.setText(question.answers[2]);
        answerDButton.setText(question.answers[3]);

    }

    public boolean isAnswerCorrect(int selectedAnswer)
    {
        return selectedAnswer == question.correctAnswer;
    }

    protected void resetButtonsColors(){
        answerAButton.setBackgroundColor(Color.BLUE);
        answerBButton.setBackgroundColor(Color.BLUE);
        answerCButton.setBackgroundColor(Color.BLUE);
        answerDButton.setBackgroundColor(Color.BLUE);
    }

    // Template for testing, not used
    public void readFileFromStorage() {

        questionsFile = new File(getFilesDir(), "Zestawy/ZestawDoTestowania.txt");

        try {

            BufferedReader br = new BufferedReader(new FileReader(questionsFile));
            String line;

            while ((line = br.readLine()) != null) {

                Question tempQuestion = new Question();

                tempQuestion.question = line;

                for (int i = 0; i < 4; i++)
                    tempQuestion.answers[i] = br.readLine();

                tempQuestion.correctAnswer = Integer.parseInt(br.readLine());

                questionsList.add(tempQuestion);

            }

            br.close();

        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}