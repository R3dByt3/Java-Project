package com.rest.choice.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StartController {

    private static final Logger log = LoggerFactory.getLogger(StartController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Model model) {
        log.debug("hallo bei evaChatApp");
        return "start";
    }
}
