package com.rest.choice.configuration.service;

import com.rest.choice.model.SurveyBase;

public interface ConfigureSurveyService {
    public String createSurvey(SurveyBase surveyBase);
    public void endSurvey(String surveyId);
    public boolean isAccessAllowed(String secretId, String surveyId);
}
