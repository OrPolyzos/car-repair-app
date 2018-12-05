package com.rcodingschool.carrepair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends BaseController {

    private static final String INDEX_VIEW = "index";

    @GetMapping("/")
    public String showIndex() {
        return decideForNotAuthenticatedView(INDEX_VIEW);

    }
}
