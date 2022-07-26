package com.rest.choice.survey.service;

import com.rest.choice.model.OptionBase;
import com.rest.choice.model.SurveyBase;

import java.util.ArrayList;

public interface SurveyService {
    public SurveyBase getSurvey(String surveyId);

    public void updateOption(OptionBase option);
    public void updateOptions(ArrayList<OptionBase> options);
}
