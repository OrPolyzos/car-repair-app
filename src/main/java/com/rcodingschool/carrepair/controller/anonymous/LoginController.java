package com.rcodingschool.carrepair.controller.anonymous;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.model.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.rcodingschool.carrepair.security.SecurityConfig.INDEX_URI;
import static com.rcodingschool.carrepair.security.SecurityConfig.LOGIN_URI;

@Controller
public class LoginController extends BaseController {

    private static final String LOGIN_VIEW = "anonymous/login";

    private static final String LOGIN_FORM = "loginForm";
    private static final String LOGIN_ERROR_MESSAGE = "Invalid credentials!";

    @GetMapping(value = {INDEX_URI, LOGIN_URI})
    public String showLoginView(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            sendErrorMessage(model, LOGIN_ERROR_MESSAGE);
        }
        model.addAttribute(LOGIN_FORM, new LoginForm());
        return decideForViewBasedOnAuth(LOGIN_VIEW);
    }

}
