package com.example.quizapp;

// SOLID
// SRP - klasa ma za zadanie jedynie odwzorowywać jedno pytanie
// OCP - klasa z pytaniami jest otwarta na rozszerzenia i zamknięta na modyfikacje

public class Question {
    public String question;
    public String[] answers;
    public int correctAnswer;

    public int answerPicked;

    public Question()
    {
        this.answers = new String[4];
        answerPicked = -1;
    }

    public Question(int correct, String q)
    {
        this.answers = new String[4];
        answerPicked = -1;
        correctAnswer = correct;
        question = q;
    }
}
