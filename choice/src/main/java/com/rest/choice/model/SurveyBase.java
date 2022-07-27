package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest.choice.serializer.SurveyDeserializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonDeserialize(using = SurveyDeserializer.class)
@Data
@Document("survey")
public abstract class SurveyBase {
    @Id
    private String _id;

    private String title;
    private String secret;
    private Boolean completed;

    public SurveyBase(String title) {
        this.title = title;
        this.secret = java.util.UUID.randomUUID().toString();
        this.completed = false;
    }

    public SurveyBase() {
        this.completed = false;
    }
}