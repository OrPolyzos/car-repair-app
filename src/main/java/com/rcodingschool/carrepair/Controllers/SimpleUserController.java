package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Domain.Vehicle;
import com.rcodingschool.carrepair.Services.RepairService;
import com.rcodingschool.carrepair.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RepairService repairService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashBoard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            User user = userService.findOne(userID);
            model.addAttribute("user", userService.findOne(userID));
            List<Repair> repairList = new ArrayList<>();
            for (Vehicle vehicle : user.getUserVehicles()) {
                repairList.addAll(repairService.findByVehicleID(vehicle.getVehicleID()));
            }
            model.addAttribute("repairsList", repairList);
        } else {
            model.addAttribute("errorMessage", "User not logged in anymore!");
        }
        return "dashboard";
    }

}
