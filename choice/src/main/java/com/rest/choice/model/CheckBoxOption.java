package com.rest.choice.model;

import java.util.ArrayList;

public class CheckBoxOption extends OptionBase {

    public ArrayList<Tuple<String, Long>> checkBoxOptions;

    public CheckBoxOption(String question, ArrayList<Tuple<String, Long>> checkBoxOptions) {
        super(question);
        this.checkBoxOptions = checkBoxOptions;
    }
}
