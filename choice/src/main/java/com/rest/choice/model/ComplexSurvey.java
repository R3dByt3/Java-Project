package com.rest.choice.model;

import java.util.ArrayList;

public class ComplexSurvey extends SurveyBase {
    public ArrayList<OptionBase> options;

    public ComplexSurvey(String name, ArrayList<OptionBase> options) {
        super(name);
        this.options = options;
    }
}
