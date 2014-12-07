package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andrei on 11/25/2014.
 */
@Controller
@RequestMapping("/option1")
public class Option1 {

    @RequestMapping(method = RequestMethod.GET)
    public String option1(ModelMap model){
        model.addAttribute("option", 1);
        model.addAttribute("option1", "coloredOption");
        model.addAttribute("option2", "option");
        model.addAttribute("option3", "option");
        model.addAttribute("option4", "option");
        model.addAttribute("option5", "option");
        model.addAttribute("option6", "option");
        return "index";
    }
}
