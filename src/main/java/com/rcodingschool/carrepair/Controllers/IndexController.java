package com.rcodingschool.carrepair.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String showIndex() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities =  auth.getAuthorities();
            if (authorities.stream().findFirst().get().toString().equals("Admin")){
                return "redirect:/admin";
            }
            else if (authorities.stream().findFirst().get().toString().equals("User")){
                return "redirect:/dashboard";
            }
        }

        return "index";
    }
}
