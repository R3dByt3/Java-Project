package com.rest.choice.model;

import java.util.ArrayList;

public class TextOption extends OptionBase {
    public ArrayList<String> Answers;

    public TextOption(String question) {
        super(question);
        Answers = new ArrayList<>();
    }
}
