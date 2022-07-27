package com.rest.choice.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rest.choice.model.ComplexSurvey;
import com.rest.choice.model.SimpleSurvey;
import com.rest.choice.model.SurveyBase;

import java.io.IOException;

public class SurveyDeserializer extends JsonDeserializer<SurveyBase> {
    @Override
    public SurveyBase deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        Class<? extends SurveyBase> instanceClass = null;
        if (root.findValue("_class").asText().equals(SimpleSurvey.class.getSimpleName())) {
            instanceClass = SimpleSurvey.class;
        } else if (root.findValue("_class").asText().equals(ComplexSurvey.class.getSimpleName())) {
            instanceClass = ComplexSurvey.class;
        }

        return mapper.treeToValue(root, instanceClass);
    }
}