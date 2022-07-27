package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonDeserialize(as = SimpleSurvey.class)
@Data
@Document
@NoArgsConstructor
public class SimpleSurvey extends SurveyBase {
    @DBRef(lazy = true)
    private OptionBase option;

    public SimpleSurvey(String title, OptionBase optionBase) {
        super(title);
        this.option = optionBase;
    }
}