package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest.choice.serializer.OptionDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonDeserialize(using = OptionDeserializer.class)
@Data
@Document("option")
@NoArgsConstructor
public abstract class OptionBase {
    @Id
    private String _id;
    private String question;

    public OptionBase(String question){
        this.question = question;
    }
}
