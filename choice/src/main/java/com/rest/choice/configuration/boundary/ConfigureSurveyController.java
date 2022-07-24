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

    @RequestMapping(value = "/createSurvey", method = RequestMethod.GET)
    public String getSurveyCreationPage(@RequestParam Optional<String> error, Model model) {
        return "createSurvey";
    }

    @RequestMapping(value = "/addSurvey" , method = RequestMethod.POST)
    public String createSurvey(@RequestParam String name, @RequestParam String email, @RequestParam String title, @RequestParam String description, @RequestParam String question, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("question", question);
        configureSurveyService.createSurvey(name, email, title, description, question);
        return "redirect:configureSurvey?secretId=" + 3L;
    }

    @RequestMapping(value = "/configureSurvey" , method = RequestMethod.GET)
    public String getSurveyConfigurationPage(@RequestParam Long secretId, Model model) {
        model.addAttribute("secretId", secretId);
        return "configureSurvey";
    }

    @RequestMapping(value = "/endSurvey" , method = RequestMethod.POST)
    public String endSurvey(@RequestParam Long secretId, Model model) {
        model.addAttribute("secretId", secretId);
        return "configureSurvey";
    }
}
