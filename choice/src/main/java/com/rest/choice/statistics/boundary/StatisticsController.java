package com.rest.choice.statistics.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatisticsController {

    private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public String getStatistics(Model model) {
        Object secretId = model.getAttribute("secretId");
        model.addAttribute("secretId", secretId);
        return "statistics";
    }
}
