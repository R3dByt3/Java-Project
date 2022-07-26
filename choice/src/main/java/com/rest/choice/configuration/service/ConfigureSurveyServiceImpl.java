package com.rest.choice.configuration.service;

import com.rest.choice.model.ComplexSurvey;
import com.rest.choice.model.SimpleSurvey;
import com.rest.choice.model.SurveyBase;
import com.rest.choice.surveyRest.OptionRepository;
import com.rest.choice.surveyRest.SurveyRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class ConfigureSurveyServiceImpl implements ConfigureSurveyService {

    final SurveyRepository surveyRepository;
    final OptionRepository optionRepository;

    public ConfigureSurveyServiceImpl(SurveyRepository surveyRepository, OptionRepository optionRepository) {
        this.surveyRepository = surveyRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public String createSurvey(SurveyBase surveyBase) {
        if (surveyBase instanceof SimpleSurvey){
            SimpleSurvey simpleSurvey = (SimpleSurvey) surveyBase;
            optionRepository.insert(simpleSurvey.getOption());
        }
        else if (surveyBase instanceof ComplexSurvey){
            ComplexSurvey complexSurvey = (ComplexSurvey) surveyBase;
            optionRepository.insert(complexSurvey.getOptions());
        }
        else
            throw new IllegalStateException("Unknown type");

        surveyRepository.insert(surveyBase);
        return surveyBase.get_id();
    }

    @Override
    public void endSurvey(String surveyId) {
        SurveyBase survey = surveyRepository.findById(surveyId).orElseThrow(InvalidParameterException::new);
        survey.setCompleted(true);
        surveyRepository.save(survey);
    }

    @Override
    public boolean isAccessAllowed(String secretId, String surveyId){
        SurveyBase survey = surveyRepository.findById(surveyId).orElseThrow(InvalidParameterException::new);
        if (!survey.getSecret().equals(secretId)) return false;
        if (survey.getCompleted()) return false;
        return true;
    }
}
