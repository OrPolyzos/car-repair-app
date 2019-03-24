package com.rcodingschool.carrepair.controller.user;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.web.initializr.base.controller.InformativeController;
import spring.web.initializr.base.exception.ResourceException;

import static com.rcodingschool.carrepair.security.SecurityConfig.USER_URI;

@Controller
@RequestMapping(USER_URI)
public class UserHomeController implements InformativeController {

    private static final String HOME_VIEW = "user/home";

    private final SecurityHelper securityHelper;

    @Autowired
    public UserHomeController(SecurityHelper securityHelper) {
        this.securityHelper = securityHelper;
    }

    @GetMapping(value = "")
    public String showUserHome(Model model) throws ResourceException {
        User user = securityHelper.getSessionUser();
        model.addAttribute("user", user);
        return HOME_VIEW;
    }
}
