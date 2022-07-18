package com.rest.choice.model;

import org.springframework.data.annotation.Id;

public abstract class SurveyBase {
    @Id
    public String id;

    public String title;

    public SurveyBase(String name) {
        this.title = name;
    }
}
