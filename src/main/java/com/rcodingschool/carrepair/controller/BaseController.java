package com.rcodingschool.carrepair.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;
import java.util.stream.Collectors;

import static com.rcodingschool.carrepair.security.SecurityConfig.*;

public abstract class BaseController {

    static final String MESSAGE_HOLDER = "errorMessage";
    static final String BINDING_RESULT_PREFIX = "org.springframework.validation.BindingResult.";
    static final String NOT_AUTHENTICATED = "NOT_AUTHENTICATED";

    //We will use the @InitBinder annotation and the initBinder method to
    //trim all the user's input from spaces
    //For example if the user enters "    John     " it will be trimmed to "John"
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    String redirectTo(String uri) {
        return "redirect:" + uri;
    }

    String decideForNotAuthenticatedView(String defaultView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::toString).collect(Collectors.toList());
            if (authorities.contains(ADMIN_ROLE)) {
                return redirectTo(ADMIN_URI);
            } else if (authorities.contains(USER_ROLE)) {
                return redirectTo(USER_URI);
            }
        }
        return defaultView;
    }
}
