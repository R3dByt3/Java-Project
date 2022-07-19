package com.rest.choice.model;

import java.util.ArrayList;

public class ComplexSurvey extends SurveyBase {
    private ArrayList<OptionBase> options;

    public ComplexSurvey(String title, ArrayList<OptionBase> options) {
        super(title);
        this.options = options;
    }

    public ArrayList<OptionBase> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<OptionBase> options) {
        this.options = options;
    }
}
