package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestTestTestController {

    @RequestMapping(value = "/TestTestTest", method = RequestMethod.GET)
    public String showTestTestTest(){
        return "TestTestTest";
    }

}
