package com.rest.choice.model;

import java.util.HashMap;
import java.util.Map;

public class RadioOption extends OptionBase {
    public Map<String, Long> radioOptions;

    public RadioOption(String question) {
        super(question);
        radioOptions = new HashMap<String, Long>();
    }
}
