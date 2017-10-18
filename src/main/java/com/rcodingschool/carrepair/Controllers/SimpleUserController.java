package com.rcodingschool.carrepair.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleUserController {

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashBoard() {
        return "dashboard";
    }

}
