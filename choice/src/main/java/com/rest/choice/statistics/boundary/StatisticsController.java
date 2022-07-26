package com.rest.choice.statistics.boundary;

import com.rest.choice.model.ComplexSurvey;
import com.rest.choice.model.SimpleSurvey;
import com.rest.choice.model.SurveyBase;
import com.rest.choice.surveyRest.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StatisticsController {

    private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

    final SurveyRepository surveyRepository;

    public StatisticsController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public String getStatistics(@RequestParam String surveyId, Model model) {
        SurveyBase survey = surveyRepository.findById(surveyId).orElseThrow(IllegalStateException::new);

        if (survey instanceof SimpleSurvey){
            SimpleSurvey simpleSurvey = (SimpleSurvey) survey;
            model.addAttribute("option", simpleSurvey.getOption());
        } else if (survey instanceof ComplexSurvey) {
            ComplexSurvey complexSurvey = (ComplexSurvey) survey;
            model.addAttribute("options", complexSurvey.getOptions());
        }
        else
            throw new IllegalStateException("Unknown type");

        model.addAttribute("surveyId", surveyId);
        return "statistics";
    }
}
