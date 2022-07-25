package com.rest.choice.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rest.choice.model.*;

import java.io.IOException;

public class OptionDeserializer extends JsonDeserializer<OptionBase> {
    @Override
    public OptionBase deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        Class<? extends OptionBase> instanceClass = null;
        if(root.findValue("_class").asText().equals(TextOption.class.getSimpleName())) {
            instanceClass = TextOption.class;
        } else if(root.findValue("_class").asText().equals(CheckBoxOption.class.getSimpleName())){
            instanceClass = CheckBoxOption.class;
        } else if(root.findValue("_class").asText().equals(RadioOption.class.getSimpleName())){
            instanceClass = RadioOption.class;
        }

        return mapper.treeToValue(root, instanceClass);
    }
}
