package com.rcodingschool.carrepair.controller;

import com.rcodingschool.carrepair.model.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.rcodingschool.carrepair.security.SecurityConfig.LOGIN_URI;

@Controller
public class LoginController extends BaseController {

    private static final String LOGIN_VIEW = "login";

    private static final String LOGIN_FORM = "loginForm";
    private static final String LOGIN_ERROR_MESSAGE = "Invalid credentials!";

    @GetMapping(LOGIN_URI)
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute(MESSAGE_HOLDER, LOGIN_ERROR_MESSAGE);
        }
        model.addAttribute(LOGIN_FORM, new LoginForm());
        return decideForNotAuthenticatedView(LOGIN_VIEW);
    }

}
