package com.rest.choice.configuration.boundary;

import com.rest.choice.configuration.service.ConfigureSurveyService;
import com.rest.choice.model.*;
import com.rest.choice.util.TypeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ConfigureSurveyController {
    ArrayList<OptionBase> questions = new ArrayList<OptionBase>();

    private static final Logger log = LoggerFactory.getLogger(ConfigureSurveyController.class);

    private final ConfigureSurveyService configureSurveyService;

    public ConfigureSurveyController(ConfigureSurveyService configureSurveyService) {
        this.configureSurveyService = configureSurveyService;
    }

    @RequestMapping(value = "/createSurvey", method = RequestMethod.GET)
    public String getSurveyCreationPage(@RequestParam Optional<String> error, Model model) {
        return "createSurvey";
    }

    @RequestMapping(value = "/addSurvey" , method = RequestMethod.POST)
    public String createSurvey(@RequestParam String name, @RequestParam String title, @RequestParam String description, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("title", title);
        model.addAttribute("description", description);

        SurveyBase surveyBase = null;

        if (questions.stream().count() == 1)
            surveyBase = new SimpleSurvey(title, questions.get(0));
        else
            surveyBase = new ComplexSurvey(title, questions);

        String id = configureSurveyService.createSurvey(surveyBase);
        return "redirect:configureSurvey?secretId=" + surveyBase.getSecret() + "&surveyId=" + id;
    }

    @RequestMapping(value = "/configureSurvey" , method = RequestMethod.GET)
    public String getSurveyConfigurationPage(@RequestParam String secretId, @RequestParam String surveyId, Model model) {
        if (!configureSurveyService.isAccessAllowed(secretId, surveyId)) return "whoops";
        model.addAttribute("secretId", secretId);
        model.addAttribute("surveyId", surveyId);
        return "configureSurvey";
    }

    @RequestMapping(value = "/endSurvey" , method = RequestMethod.POST)
    public String endSurvey(@RequestParam String surveyId, Model model) {
        configureSurveyService.endSurvey(surveyId);
        model.addAttribute("surveyId", surveyId);
        return "configureSurvey";
    }

    @RequestMapping(value = "/addQuestion" , method = RequestMethod.POST)
    public String addQuestion(@RequestParam String question, @RequestParam String options, @RequestParam String values, Model model) {
        model.addAttribute("question", question);
        model.addAttribute("option", options);
        model.addAttribute("values", values);

        if(options.equals("TextOption"))
            questions.add(new TextOption(question));
        else if(options.equals("RadioOption")) {
            RadioOption radioOption = new RadioOption();
            radioOption.setQuestion(question);
            String[] splittedValues = values.split(";");
            Map<String, Long> map = new HashMap<>();
            for(long i = 0; i < splittedValues.length; i++)
            {
                map.put(splittedValues[(int)i], 0L);
            }
            radioOption.setRadioOptions(map);
            questions.add(radioOption);
        }
        else if(options.equals("CheckBoxOption")) {
            CheckBoxOption checkBoxOption = new CheckBoxOption();
            checkBoxOption.setQuestion(question);
            String[] splittedValues = values.split(";");
            Map<String, Long> map = new HashMap<>();
            for(long i = 0; i < splittedValues.length; i++)
            {
                map.put(splittedValues[(int)i], 0L);
            }
            checkBoxOption.setCheckBoxOptions(map);
            questions.add(checkBoxOption);
        }

        model.addAttribute("questions", questions);
        model.addAttribute("typeHelper", new TypeHelper());
        return "createSurvey";
    }
}
