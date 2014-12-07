package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andrei on 11/25/2014.
 */
@Controller
@RequestMapping("/option5")
public class Option5 {

    @RequestMapping(method = RequestMethod.GET)
    public String option1(ModelMap model){
        model.addAttribute("option", 5);
        model.addAttribute("option1", "option");
        model.addAttribute("option2", "option");
        model.addAttribute("option3", "option");
        model.addAttribute("option4", "option");
        model.addAttribute("option5", "coloredOption");
        model.addAttribute("option6", "option");
        return "index";
    }
}
