package com.example.quizapp;

public class Question {
    public String question;
    public String[] answers;
    public int correctAnswer;
//<<<<<<< Updated upstream

    //Question(){
        //this.answers = new String[4];
//=======
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
//>>>>>>> Stashed changes
    }
}
