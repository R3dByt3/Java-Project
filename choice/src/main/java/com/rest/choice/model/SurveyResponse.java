package com.rest.choice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SurveyResponse {
    private Map<String, ArrayList<String>> responses;

    public SurveyResponse() {
        responses = new HashMap<>();
    }

    public Map<String, ArrayList<String>> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, ArrayList<String>> responses) {
        this.responses = responses;
    }
}