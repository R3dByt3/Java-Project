package com.rest.choice.util;

import com.rest.choice.model.CheckBoxOption;
import com.rest.choice.model.RadioOption;
import com.rest.choice.model.TextOption;

public final class TypeHelper {
    public <T> String GetTypeName(T data){
        return data.getClass().getSimpleName();
    }

    public <T> RadioOption AsRadioOption(T data){
        return (RadioOption) data;
    }

    public <T> CheckBoxOption AsCheckBoxOption(T data){
        return (CheckBoxOption) data;
    }

    public <T> TextOption AsTextOption(T data){
        return (TextOption) data;
    }
}
