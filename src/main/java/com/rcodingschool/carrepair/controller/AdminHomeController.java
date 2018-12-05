package com.rcodingschool.carrepair.controller;

import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.service.RepairService;
import com.rcodingschool.carrepair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class AdminHomeController extends BaseController {

    private static final String ADMIN_INDEX_VIEW = "adminIndex";

    private static final String USER_HOLDER = "user";
    private static final String REPAIRS_LIST_HOLDER = "repairsList";

    private static final String USER_NOT_LOGGED_IN_ANYMORE_MESSAGE = "User not logged in anymore!";

    private final UserService userService;
    private final RepairService repairService;

    @Autowired
    public AdminHomeController(UserService userService, RepairService repairService) {
        this.userService = userService;
        this.repairService = repairService;
    }

    @GetMapping
    public String adminHome(Model model) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            model.addAttribute(USER_HOLDER, userService.findOne(userID));
            List<Repair> repairList = repairService.findTop10ByOrderByRepairDateTimeDesc();
            model.addAttribute(REPAIRS_LIST_HOLDER, repairList);
        } else {
            model.addAttribute(MESSAGE_HOLDER, USER_NOT_LOGGED_IN_ANYMORE_MESSAGE);
        }
        return ADMIN_INDEX_VIEW;
    }
}
