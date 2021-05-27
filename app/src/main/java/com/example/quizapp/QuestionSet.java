package com.example.quizapp;

import java.util.ArrayList;

public final class QuestionSet {

    ArrayList<Question> questionSet;

    private static final QuestionSet INSTANCE = new QuestionSet();

    public QuestionSet() {
        questionSet = new ArrayList<Question>();
    }

    public static QuestionSet getInstance() {
        return INSTANCE;
    }

    public int countAnswers()
    {
        int counter = 0;
        for(Question question : questionSet)
        {
            if(question.answerPicked == question.correctAnswer)
                counter++;
        }
        return counter;
    }
}
