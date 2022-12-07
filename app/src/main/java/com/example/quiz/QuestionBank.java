package com.example.quiz;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {
    ArrayList<Integer> listOfColors = new ArrayList<>();
    ArrayList<Question> listOfQuestions = new ArrayList<Question>(0);

    public QuestionBank() {

        listOfColors.add(R.color.LightGreen);
        listOfColors.add(R.color.SkyBlue);
        listOfColors.add(R.color.MediumPurple);
        listOfColors.add(R.color.PaleTurquoise);
        listOfColors.add(R.color.DarkSeaGreen);
        listOfColors.add(R.color.Orange);
        listOfColors.add(R.color.MistyRose);
        listOfColors.add(R.color.MediumAquamarine);
        listOfColors.add(R.color.Beige);
        listOfColors.add(R.color.YellowGreen);

        Collections.shuffle(listOfColors);

        Question question1 = new Question(R.string.question1, true, listOfColors.get(0));
        Question question2 = new Question(R.string.question2, false, listOfColors.get(1));
        Question question3 = new Question(R.string.question3, false, listOfColors.get(2));
        Question question4 = new Question(R.string.question4, false, listOfColors.get(3));
        Question question5 = new Question(R.string.question5, false, listOfColors.get(4));
        Question question6 = new Question(R.string.question6, true, listOfColors.get(5));
        Question question7 = new Question(R.string.question7, true, listOfColors.get(6));
        Question question8 = new Question(R.string.question8, true, listOfColors.get(7));
        Question question9 = new Question(R.string.question9, true, listOfColors.get(8));
        Question question10 = new Question(R.string.question10, true, listOfColors.get(9));

        listOfQuestions.add(question1);
        listOfQuestions.add(question2);
        listOfQuestions.add(question3);
        listOfQuestions.add(question4);
        listOfQuestions.add(question5);
        listOfQuestions.add(question6);
        listOfQuestions.add(question7);
        listOfQuestions.add(question8);
        listOfQuestions.add(question9);
        listOfQuestions.add(question10);

        Collections.shuffle(listOfQuestions);

    }
}
