package com.example.quizapp;

import java.util.ArrayList;

// SOLID
// SRP - klasa ma za zadanie jedynie odwzorowywać jeden zestaw z pytaniami
// OCP - klasa z pytaniami jest otwarta na rozszerzenia i zamknięta na modyfikacje

// Wzorzec projektowy Singletona.
// W jednym momencie, w naszej aplikacji może być wybrany jedynie jeden zestaw.
// Nigdy nie powinna zajść sytuacja kiedy będziemy mieli musieli mieć dwie listy.
// Lista jest czyszczona i zapełniania przy każdym wejściu do trybu.

public final class QuestionSet {

    ArrayList<Question> questionSet;

    private static final QuestionSet INSTANCE = new QuestionSet();

    private QuestionSet() {questionSet = new ArrayList<Question>();}

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
