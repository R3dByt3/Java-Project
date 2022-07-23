package com.rest.choice.model;

import java.util.ArrayList;

public class TextOption extends OptionBase {
    private ArrayList<String> answers;

    public TextOption(String question) {
        super(question);
        this.answers = new ArrayList<>();
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String getType(){
        return super.getType();
    }
}
