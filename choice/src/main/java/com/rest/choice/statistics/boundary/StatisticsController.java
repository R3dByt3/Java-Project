package com.rest.choice.statistics.boundary;

import com.rest.choice.model.ComplexSurvey;
import com.rest.choice.model.SimpleSurvey;
import com.rest.choice.model.SurveyBase;
import com.rest.choice.surveyRest.SurveyRepository;
import com.rest.choice.util.TypeHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatisticsController {

    final SurveyRepository surveyRepository;

    public StatisticsController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.POST)
    public String getStatistics(@RequestParam String surveyId, Model model) {
        model.addAttribute("surveyId", surveyId);

        SurveyBase survey = surveyRepository.findById(surveyId).orElseThrow(IllegalStateException::new);

        model.addAttribute("typeHelper", new TypeHelper());

        if (survey instanceof SimpleSurvey) {
            SimpleSurvey simpleSurvey = (SimpleSurvey) survey;
            model.addAttribute("options", simpleSurvey.getOption());
        } else if (survey instanceof ComplexSurvey) {
            ComplexSurvey complexSurvey = (ComplexSurvey) survey;
            model.addAttribute("options", complexSurvey.getOptions());
        } else
            throw new IllegalStateException("Unknown type");

        return "statistics";
    }
}