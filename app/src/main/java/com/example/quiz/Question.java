package com.example.quiz;

public class Question {
    private int QuestionId;
    private boolean AnswerId;
    private int ColorId;

    public Question(int questionId, boolean answerId, int colorId) {
        QuestionId = questionId;
        AnswerId = answerId;
        ColorId = colorId;
    }

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public boolean isAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(boolean answerId) {
        AnswerId = answerId;
    }

    public int getColorId() {
        return ColorId;
    }

    public void setColorId(int colorId) {
        ColorId = colorId;
    }
}