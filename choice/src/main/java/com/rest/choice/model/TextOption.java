package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@JsonDeserialize(as = TextOption.class)
@Data
@Document("option")
@NoArgsConstructor
public class TextOption extends OptionBase {
    private ArrayList<String> answers;

    public TextOption(String question) {
        super(question);
    }
}
