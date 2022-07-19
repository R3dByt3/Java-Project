package com.rest.choice.model;

public abstract class OptionBase {
    private String question;

    public OptionBase(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
