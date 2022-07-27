package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@JsonDeserialize(as = RadioOption.class)
@Data
@Document("option")
@NoArgsConstructor
public class RadioOption extends OptionBase {
    private Map<String, Long> radioOptions;

    public RadioOption(String question, Map<String, Long> options) {
        super(question);
        this.radioOptions = options;
    }
}