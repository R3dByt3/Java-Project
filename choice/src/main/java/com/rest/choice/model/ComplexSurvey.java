package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@JsonDeserialize(as = ComplexSurvey.class)
@Data
@Document
@NoArgsConstructor
public class ComplexSurvey extends SurveyBase {
    @DBRef(lazy = true)
    private ArrayList<OptionBase> options;

    public ComplexSurvey(String title, ArrayList<OptionBase> options) {
        super(title);
        this.options = options;
    }
}