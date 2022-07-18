package com.rest.choice.model;

public class SimpleSurvey extends SurveyBase {
    public OptionBase option;

    public SimpleSurvey(String name, OptionBase option) {
        super(name);
        this.option = option;
    }
}
