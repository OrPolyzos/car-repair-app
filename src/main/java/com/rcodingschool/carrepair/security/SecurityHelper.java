package com.rcodingschool.carrepair.security;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    private final UserService userService;

    @Autowired
    public SecurityHelper(UserService userService) {
        this.userService = userService;
    }

    public boolean checkForRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }

    public User getSessionUser() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findOne((Long) authentication.getPrincipal());
    }

    public String getSessionUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return String.valueOf(authentication.getPrincipal());
        }
        return null;
    }
}
