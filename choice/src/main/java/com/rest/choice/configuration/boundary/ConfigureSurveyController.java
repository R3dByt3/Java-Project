package com.rest.choice.configuration.boundary;

import com.rest.choice.configuration.service.ConfigureSurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ConfigureSurveyController {
    private static final Logger log = LoggerFactory.getLogger(ConfigureSurveyController.class);

    @Autowired private ConfigureSurveyService configureSurveyService;

    @RequestMapping(value = "/configure" , method = RequestMethod.GET)
    public String getSurveyConfigurationPage(@RequestParam Optional<String> error, Model model) {
        return "configureSurvey";
    }

    @RequestMapping(value = "/addSurvey" , method = RequestMethod.POST)
    public String getSurveyConfigurationPage(@RequestParam String name, @RequestParam String email, @RequestParam String title, @RequestParam String description, @RequestParam String question, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("question", question);
        configureSurveyService.createSurvey(name, email, title, description, question);
        return "configureSurvey";
    }
}
