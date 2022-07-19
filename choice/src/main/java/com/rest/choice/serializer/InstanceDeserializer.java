package com.rest.choice.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rest.choice.model.*;

import java.io.IOException;
import java.util.ArrayList;

public class InstanceDeserializer extends JsonDeserializer<SurveyBase> {
    @Override
    public SurveyBase deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        if(root.findValue("_class").asText().equals(SimpleSurvey.class.getSimpleName())) {
            ObjectNode node = root.with("option");
            OptionBase option = getOption(node);
            return new SimpleSurvey(root.findValue("title").asText(), option);
        } else if(root.findValue("_class").asText().equals(ComplexSurvey.class.getSimpleName())){
            ArrayNode nodes = root.withArray("options");
            ArrayList<OptionBase> options = new ArrayList<>();
            nodes.forEach(x -> options.add(getOption(x)));
            return new ComplexSurvey(root.findValue("title").asText(), options);
        }

        return null;
    }

    private OptionBase getOption(JsonNode root) {
        String question = root.findValue("question").asText();
        //if(root.findValue("_class").asText().equals(CheckBoxOption.class.getSimpleName())) {
        //    return new CheckBoxOption(question);
        //}
        //else if (root.findValue("_class").asText().equals(RadioOption.class.getSimpleName())) {
        //    return new RadioOption(question);
        //}
        if (root.findValue("_class").asText().equals(TextOption.class.getSimpleName())) {
            return new TextOption(question);
        }
        else
            return null;
    }
}
