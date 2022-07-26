package com.rest.choice.configuration.service;

import com.rest.choice.model.OptionBase;

import java.util.ArrayList;

public interface ConfigureSurveyService {
    void createSurvey(String name, String title, String description, ArrayList<OptionBase> questions);
}
