package com.rest.choice.model;

import java.util.Map;

public class RadioOption extends OptionBase {
    private Map<String, Long> radioOptions;

    public RadioOption(String question, Map<String, Long> radioOptions) {
        super(question);
        this.radioOptions = radioOptions;
    }

    public Map<String, Long> getRadioOptions() {
        return radioOptions;
    }

    public void setRadioOptions(Map<String, Long> radioOptions) {
        this.radioOptions = radioOptions;
    }

    public String getType(){
        return super.getType();
    }
}
