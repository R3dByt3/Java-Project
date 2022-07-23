package com.rest.choice.model;

import java.util.ArrayList;
import java.util.Map;

public abstract class OptionBase {
    private ArrayList<Tuple<String, Long>> checkBoxOptions;
    private String question;
    private Map<String, Long> radioOptions;
    private String type;

    public OptionBase(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType(){
        type=this.getClass().getSimpleName();
        return type;
    }

    public Map<String, Long> getRadioOptions() {
        return radioOptions;
    }

    public ArrayList<Tuple<String, Long>> getCheckBoxOptions() {
        return checkBoxOptions;
    }
}
