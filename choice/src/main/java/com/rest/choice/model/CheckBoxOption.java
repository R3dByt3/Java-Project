package com.rest.choice.model;

import java.util.ArrayList;

public class CheckBoxOption extends OptionBase {

    private ArrayList<Tuple<String, Long>> checkBoxOptions;

    public CheckBoxOption(String question, ArrayList<Tuple<String, Long>> checkBoxOptions) {
        super(question);
        this.checkBoxOptions = checkBoxOptions;
    }

    public ArrayList<Tuple<String, Long>> getCheckBoxOptions() {
        return checkBoxOptions;
    }

    public void setCheckBoxOptions(ArrayList<Tuple<String, Long>> checkBoxOptions) {
        this.checkBoxOptions = checkBoxOptions;
    }

    public String getType(){
        return super.getType();
    }
}
