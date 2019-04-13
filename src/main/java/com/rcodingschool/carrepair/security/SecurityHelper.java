package com.rcodingschool.carrepair.security;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.service.resource.UserResourceService;
import ore.spring.web.initializr.exception.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    private final UserResourceService userResourceService;

    @Autowired
    public SecurityHelper(UserResourceService userResourceService) {
        this.userResourceService = userResourceService;
    }

    public boolean checkForRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }

    public User getSessionUser() throws ResourceException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userResourceService.findOrThrow((Long) authentication.getPrincipal());
    }

    public String getSessionUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return String.valueOf(authentication.getPrincipal());
        }
        return null;
    }
}
