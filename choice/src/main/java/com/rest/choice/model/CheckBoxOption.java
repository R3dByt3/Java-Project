package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@JsonDeserialize(as = CheckBoxOption.class)
@Data
@Document("option")
@NoArgsConstructor
public class CheckBoxOption extends OptionBase {

    private Map<String, Long> checkBoxOptions;

    public CheckBoxOption(String question, Map<String, Long> options) {
        super(question);
        this.checkBoxOptions = options;
    }
}
