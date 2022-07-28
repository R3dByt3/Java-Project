package com.rest.choice.configuration.service;

import com.rest.choice.model.*;
import com.rest.choice.surveyRest.OptionRepository;
import com.rest.choice.surveyRest.SurveyRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        if (surveyBase instanceof SimpleSurvey) {
            SimpleSurvey simpleSurvey = (SimpleSurvey) surveyBase;
            optionRepository.insert(simpleSurvey.getOption());
        } else if (surveyBase instanceof ComplexSurvey) {
            ComplexSurvey complexSurvey = (ComplexSurvey) surveyBase;
            optionRepository.insert(complexSurvey.getOptions());
        } else
            throw new IllegalStateException("Unknown type");

        surveyRepository.insert(surveyBase);
        return surveyBase.get_id();
    }

    @Override
    public SurveyBase endSurvey(String surveyId) {
        SurveyBase survey = surveyRepository.findById(surveyId).orElseThrow(InvalidParameterException::new);
        survey.setCompleted(true);
        surveyRepository.save(survey);
        return survey;
    }

    @Override
    public boolean isAccessAllowed(String secretId, String surveyId) {
        SurveyBase survey = surveyRepository.findById(surveyId).orElseThrow(InvalidParameterException::new);
        if (!survey.getSecret().equals(secretId)) return false;
        if (survey.getCompleted()) return false;
        return true;
    }

    @Override
    public ArrayList<OptionBase> addQuestion(String question, String option, String values, ArrayList<OptionBase> questions) {
        if (!question.isEmpty()) {
            if (option.equals("TextOption")) {
                TextOption optionValue = new TextOption(question);
                questions.add(optionValue);
            } else if (option.equals("RadioOption") && !values.isEmpty()) {
                RadioOption radioOption = new RadioOption();
                radioOption.setQuestion(question);
                String[] splittedValues = values.split(";");
                if (Arrays.stream(splittedValues).filter(x -> !x.isBlank()).count() <= 1)
                    return questions;
                Map<String, Long> map = new HashMap<>();
                for (long i = 0; i < splittedValues.length; i++) {
                    map.put(splittedValues[(int) i], 0L);
                }
                radioOption.setRadioOptions(map);
                questions.add(radioOption);
            } else if (option.equals("CheckBoxOption") && !values.isEmpty()) {
                CheckBoxOption checkBoxOption = new CheckBoxOption();
                checkBoxOption.setQuestion(question);
                String[] splittedValues = values.split(";");
                if (Arrays.stream(splittedValues).filter(x -> !x.isBlank()).count() <= 1)
                    return questions;
                Map<String, Long> map = new HashMap<>();
                for (long i = 0; i < splittedValues.length; i++) {
                    map.put(splittedValues[(int) i], 0L);
                }
                checkBoxOption.setCheckBoxOptions(map);
                questions.add(checkBoxOption);
            }
        }
        return questions;
    }
}