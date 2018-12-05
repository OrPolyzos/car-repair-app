package com.rcodingschool.carrepair.controller;

import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.service.RepairService;
import com.rcodingschool.carrepair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import static com.rcodingschool.carrepair.controller.BaseController.MESSAGE_HOLDER;
import static com.rcodingschool.carrepair.security.SecurityConfig.USER_URI;

@Controller
public class SimpleUserController {

    private static final String DASHBOARD_VIEW = "dashboard";

    private static final String REPAIRS_LIST_HOLDER = "repairsList";

    private static final String USER_NOT_LOGGED_IN_ANYMORE_MESSAGE = "User not logged in anymore!";

    private final UserService userService;
    private final RepairService repairService;

    @Autowired
    public SimpleUserController(UserService userService, RepairService repairService) {
        this.userService = userService;
        this.repairService = repairService;
    }

    @RequestMapping(value = USER_URI, method = RequestMethod.GET)
    public String showDashBoard(Model model) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            User user = userService.findOne(userID);
            model.addAttribute("user", userService.findOne(userID));
            List<Repair> repairList = new ArrayList<>();
            for (Vehicle vehicle : user.getUserVehicles()) {
                repairList.addAll(repairService.findByVehicleID(vehicle.getVehicleID()));
            }
            model.addAttribute(REPAIRS_LIST_HOLDER, repairList);
        } else {
            model.addAttribute(MESSAGE_HOLDER, USER_NOT_LOGGED_IN_ANYMORE_MESSAGE);
        }
        return DASHBOARD_VIEW;
    }

}
