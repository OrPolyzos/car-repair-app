package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Repositories.UserRepository;
import com.rcodingschool.carrepair.SloppyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
//@RequestMapping("/admin")
public class AdminHomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminHome() {
        List<User> list = SloppyRepository.getAllUsers();
        return "adminIndex";
    }
}
