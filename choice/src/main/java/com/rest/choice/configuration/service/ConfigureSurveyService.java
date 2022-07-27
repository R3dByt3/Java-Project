package com.rest.choice.configuration.service;

import com.rest.choice.model.OptionBase;
import com.rest.choice.model.SurveyBase;

import java.util.ArrayList;

public interface ConfigureSurveyService {
    public String createSurvey(SurveyBase surveyBase);

    public SurveyBase endSurvey(String surveyId);

    public boolean isAccessAllowed(String secretId, String surveyId);

    public ArrayList<OptionBase> addQuestion(String question, String option, String values, ArrayList<OptionBase> questions);
}
