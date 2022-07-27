package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@JsonDeserialize(as = TextOption.class)
@Data
@Document("option")
public class TextOption extends OptionBase {
    private ArrayList<String> answers;

    public TextOption() {
        super();
        answers = new ArrayList<>();
    }

    public TextOption(String question) {
        super(question);
        answers = new ArrayList<>();
    }
}