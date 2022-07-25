package com.rest.choice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest.choice.serializer.SurveyDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonDeserialize(using = SurveyDeserializer.class)
@Data
@Document("survey")
@NoArgsConstructor
public abstract class SurveyBase {
    @Id
    private String _id;

    private String title;
}
