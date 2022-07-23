package com.rest.choice.configuration.service;

public interface ConfigureSurveyService {
    void createSurvey(String name, String email, String title, String description, String question);
}
