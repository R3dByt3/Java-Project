package com.rest.choice.model;

public class SimpleSurvey extends SurveyBase {
    private OptionBase option;

    public SimpleSurvey(String title, OptionBase option) {
        super(title);
        this.option = option;
    }

    public OptionBase getOption() {
        return option;
    }

    public void setOption(OptionBase option) {
        this.option = option;
    }
}
