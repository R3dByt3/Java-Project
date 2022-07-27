package com.rest.choice.configuration.boundary;

import com.rest.choice.configuration.service.ConfigureSurveyService;
import com.rest.choice.model.ComplexSurvey;
import com.rest.choice.model.OptionBase;
import com.rest.choice.model.SimpleSurvey;
import com.rest.choice.model.SurveyBase;
import com.rest.choice.util.TypeHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ConfigureSurveyController {
    ArrayList<OptionBase> questions = new ArrayList<>();

    private final ConfigureSurveyService configureSurveyService;

    public ConfigureSurveyController(ConfigureSurveyService configureSurveyService) {
        this.configureSurveyService = configureSurveyService;
    }

    @RequestMapping(value = "/createSurvey", method = RequestMethod.GET)
    public String getSurveyCreationPage() {
        return "createSurvey";
    }

    @RequestMapping(value = "/addSurvey", method = RequestMethod.POST)
    public String createSurvey(@RequestParam String title) {
        SurveyBase surveyBase;

        if (questions.stream().count() == 1)
            surveyBase = new SimpleSurvey(title, questions.get(0));
        else
            surveyBase = new ComplexSurvey(title, questions);

        String id = configureSurveyService.createSurvey(surveyBase);
        return "redirect:configureSurvey?secretId=" + surveyBase.getSecret() + "&surveyId=" + id + "&title=" + title;
    }

    @RequestMapping(value = "/configureSurvey", method = RequestMethod.GET)
    public String getSurveyConfigurationPage(@RequestParam String secretId, @RequestParam String surveyId, @RequestParam String title, Model model) {
        if (!configureSurveyService.isAccessAllowed(secretId, surveyId)) return "whoops";
        model.addAttribute("secretId", secretId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("title", title);
        return "configureSurvey";
    }

    @RequestMapping(value = "/endSurvey", method = RequestMethod.POST)
    public String endSurvey(@RequestParam String surveyId, Model model) {
        configureSurveyService.endSurvey(surveyId);
        model.addAttribute("isCompleted", true);
        return "configureSurvey";
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(@RequestParam String question, @RequestParam String option, @RequestParam String values, Model model) {
        questions = configureSurveyService.addQuestion(question, option, values, questions);
        model.addAttribute("questions", questions);
        model.addAttribute("typeHelper", new TypeHelper());
        return "createSurvey";
    }
}