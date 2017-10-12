package com.rcodingschool.carrepair.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/admin")
public class AdminHomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminHome() {
        return "adminIndex";
    }
}
