package com.rcodingschool.carrepair.controller.base;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import static com.rcodingschool.carrepair.security.SecurityConfig.*;

public abstract class BaseController {

    private static final String INFO_MESSAGE_HOLDER = "infoMessage";
    private static final String ERROR_MESSAGE_HOLDER = "errorMessage";
    private static final String BINDING_RESULT_PREFIX = "org.springframework.validation.BindingResult.";

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    protected String redirectTo(String uri) {
        return "redirect:" + uri;
    }

    protected String decideForViewBasedOnAuth(String defaultView) {
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

    protected void sendInfoMessage(Model model, String message) {
        model.addAttribute(INFO_MESSAGE_HOLDER, message);
    }

    protected void sendErrorMessage(Model model, String message) {
        model.addAttribute(ERROR_MESSAGE_HOLDER, message);
    }

    protected void redirectInfoMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(INFO_MESSAGE_HOLDER, message);
    }

    protected void redirectErrorMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE_HOLDER, message);
    }

    protected void sendBindingErrors(RedirectAttributes redirectAttributes, BindingResult bindingResult, String itemHolder, Object actualObject) {
        redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + itemHolder, bindingResult);
        redirectAttributes.addFlashAttribute(itemHolder, actualObject);
    }

    protected Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (Long) authentication.getPrincipal();
    }
}
