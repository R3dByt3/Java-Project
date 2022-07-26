package com.rest.choice.configuration.service;

import com.rest.choice.model.OptionBase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConfigureSurveyServiceImpl implements ConfigureSurveyService{
    @Override
    public void createSurvey(String name, String title, String description, ArrayList<OptionBase> questions) {

    }
}
