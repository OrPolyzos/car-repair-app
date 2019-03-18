package com.rcodingschool.carrepair.controller.anonymous;

import com.rcodingschool.carrepair.controller.base.InformativeController;
import com.rcodingschool.carrepair.model.LoginForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import static com.rcodingschool.carrepair.security.SecurityConfig.*;

@Controller
public class LoginController implements InformativeController {

    private static final String LOGIN_VIEW = "anonymous/login";

    private static final String LOGIN_FORM = "loginForm";
    private static final String LOGIN_ERROR_MESSAGE = "Invalid credentials!";

    @GetMapping(value = {INDEX_URI, LOGIN_URI})
    public String showLoginView(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            sendErrorMessage(model, LOGIN_ERROR_MESSAGE);
        }
        model.addAttribute(LOGIN_FORM, new LoginForm());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::toString).collect(Collectors.toList());
            if (authorities.contains(ADMIN_ROLE)) {
                return redirectTo(ADMIN_URI);
            } else if (authorities.contains(USER_ROLE)) {
                return redirectTo(USER_URI);
            }
        }
        return LOGIN_VIEW;
    }
}
