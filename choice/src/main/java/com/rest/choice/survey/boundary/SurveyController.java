package com.rest.choice.survey.boundary;

import com.rest.choice.model.*;
import com.rest.choice.survey.service.SurveyService;
import com.rest.choice.util.TypeHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public String getSurveyConfigurationPage(@RequestParam String surveyId, Model model) {
        SurveyBase survey = surveyService.getSurvey(surveyId);
        if (survey.getCompleted())
            return "whoops";
        SurveyResponse answers = new SurveyResponse();
        ArrayList<OptionBase> options = new ArrayList<>();

        if (survey instanceof ComplexSurvey) {
            ComplexSurvey complexSurvey = (ComplexSurvey) survey;
            options.addAll(complexSurvey.getOptions());
            for (OptionBase option : options) {
                answers.getResponses().put(option.get_id(), new ArrayList<>());
            }
        } else if (survey instanceof SimpleSurvey) {
            SimpleSurvey simpleSurvey = (SimpleSurvey) survey;
            options.add(simpleSurvey.getOption());
            answers.getResponses().put(simpleSurvey.getOption().get_id(), new ArrayList<>());
        } else
            throw new IllegalStateException("Unknown type");

        model.addAttribute("answers", answers);
        model.addAttribute("survey", survey);
        model.addAttribute("surveyId", survey.get_id());
        model.addAttribute("listSurveyQuestions", options);
        model.addAttribute("typeHelper", new TypeHelper());
        model.addAttribute("title", survey.getTitle());
        return "survey";
    }

    @RequestMapping(value = "/addSurveyAnswers", method = RequestMethod.POST)
    public String addSurveyAnswers(@RequestParam String surveyId, @ModelAttribute(value = "answers") SurveyResponse answers, Model model) {
        SurveyBase survey = surveyService.getSurvey(surveyId);
        if (survey.getCompleted())
            return "whoops";

        if (survey instanceof SimpleSurvey) {
            SimpleSurvey simpleSurvey = (SimpleSurvey) survey;
            AddOptions(simpleSurvey.getOption(), answers);
        } else if (survey instanceof ComplexSurvey) {
            ComplexSurvey complexSurvey = (ComplexSurvey) survey;
            for (OptionBase option : complexSurvey.getOptions()) {
                AddOptions(option, answers);
            }
        } else
            throw new IllegalStateException("Unknown type");

        return "surveySuccessfull";
    }

    private void AddOptions(OptionBase option, SurveyResponse answers) {
        option = TypeHelper.unproxy(option);
        if (option instanceof TextOption) {
            TextOption textOption = (TextOption) option;
            textOption.getAnswers().add(answers.getResponses().get(option.get_id()).get(0));
        } else if (option instanceof CheckBoxOption) {
            CheckBoxOption checkBoxOption = (CheckBoxOption) option;
            ArrayList<String> selectedAnswers = answers.getResponses().get(option.get_id());
            for (String answer : selectedAnswers) {
                checkBoxOption.getCheckBoxOptions().put(answer, checkBoxOption.getCheckBoxOptions().get(answer) + 1);
            }
        } else if (option instanceof RadioOption) {
            RadioOption radioOption = (RadioOption) option;
            ArrayList<String> selectedAnswers = answers.getResponses().get(option.get_id());
            for (String answer : selectedAnswers) {
                radioOption.getRadioOptions().put(answer, radioOption.getRadioOptions().get(answer) + 1);
            }
        } else
            throw new IllegalStateException("Unknown type");
        surveyService.updateOption(option);
    }
}
