package com.rest.choice.survey.service;

import com.rest.choice.model.OptionBase;
import com.rest.choice.model.SurveyBase;
import com.rest.choice.surveyRest.OptionRepository;
import com.rest.choice.surveyRest.SurveyRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;

@Service
public class SurveyServiceImpl implements SurveyService{

    final SurveyRepository surveyRepository;
    final OptionRepository optionRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository, OptionRepository optionRepository) {
        this.surveyRepository = surveyRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public SurveyBase getSurvey(String surveyId) {
        return surveyRepository.findById(surveyId).orElseThrow(InvalidParameterException::new);
    }

    @Override
    public void updateOption(OptionBase option) {
        optionRepository.save(option);
    }

    @Override
    public void updateOptions(ArrayList<OptionBase> options) {
        optionRepository.saveAll(options);
    }
}
