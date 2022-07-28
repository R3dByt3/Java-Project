package com.rest.choice.util;

import com.rest.choice.model.CheckBoxOption;
import com.rest.choice.model.RadioOption;
import com.rest.choice.model.TextOption;
import org.springframework.data.mongodb.core.convert.LazyLoadingProxy;

public final class TypeHelper {
    public <T> String getTypeName(T data) {
        T tmp = unproxy(data);
        return tmp.getClass().getSimpleName();
    }

    public <T> RadioOption asRadioOption(T data) {
        return (RadioOption) data;
    }

    public <T> CheckBoxOption asCheckBoxOption(T data) {
        return (CheckBoxOption) data;
    }

    public <T> TextOption asTextOption(T data) {
        return (TextOption) data;
    }

    public static <T> T unproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        if (entity instanceof LazyLoadingProxy) {
            return (T) ((LazyLoadingProxy) entity).getTarget();
        }

        return entity;
    }
}
