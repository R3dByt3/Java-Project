package com.rest.choice.survey.boundary;

import com.rest.choice.model.*;
import com.rest.choice.util.TypeHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SurveyController {

    @RequestMapping(value = "/survey" , method = RequestMethod.GET)
    public String getSurveyConfigurationPage(@RequestParam String surveyId, Model model) {
        model.addAttribute("surveyId", surveyId);
        ArrayList<OptionBase> list = new ArrayList<>();
        Map<String, Long> list2 = new HashMap<>();
        Map<String, Long> map = new HashMap<>();
        map.put("hallo", 1L);
        map.put("hallo2", 2L);
        list2.put("hallo", 1L);
        list2.put("hallo2", 2L);
        list.add(new TextOption("Wie alt bist du?"));
        list.add(new RadioOption("Wie alt bist du?", map));
        list.add(new CheckBoxOption("Wie alt bist du?", list2));
        model.addAttribute("listSurveyQuestions", list);
        model.addAttribute("typeHelper", new TypeHelper());
        return "survey";
    }

    @RequestMapping(value = "/addSurveyAnswers" , method = RequestMethod.POST)
    public String addSurveyAnswers(@RequestParam String surveyId, Model model) {
        return "surveySuccessfull";
    }
}
