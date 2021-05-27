package com.example.quizapp;

public class Question {
    public String question;
    public String[] answers;
    public int correctAnswer;

    Question(){
        this.answers = new String[4];
    }
}
