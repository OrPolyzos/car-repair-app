package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Model.LoginForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class LoginController {

    private static final String LOGIN_FORM = "loginForm";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model,
                        @RequestParam(name = "error", required = false) String error,
                        HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<? extends GrantedAuthority>  authorities =  auth.getAuthorities();
            if (authorities.stream().findFirst().get().toString().equals("Admin")){
                return "redirect:/admin";
            }
            else if (authorities.stream().findFirst().get().toString().equals("User")){
                return "redirect:/dashboard";
            }
        }

        if (error != null) {
            model.addAttribute("errorMessage", "User not found! Please try again");
        }

        model.addAttribute(LOGIN_FORM, new LoginForm());
        return "login";
    }

}
