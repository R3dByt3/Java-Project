package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest.choice.serializer.InstanceDeserializer;
import org.springframework.data.annotation.Id;

@JsonDeserialize(using = InstanceDeserializer.class)
public abstract class SurveyBase {
    @Id
    private String id;

    private String title;

    public SurveyBase(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
