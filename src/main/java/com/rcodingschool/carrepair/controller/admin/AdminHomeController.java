package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.security.SecurityHelper;
import com.rcodingschool.carrepair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class AdminHomeController extends BaseController {

    private static final String HOME_VIEW = "admin/home";

    private static final String USER_HOLDER = "user";
    private static final String REPAIRS_LIST_HOLDER = "repairsList";

    private final SecurityHelper securityHelper;
    private final RepairService repairService;

    @Autowired
    public AdminHomeController(SecurityHelper securityHelper, RepairService repairService) {
        this.securityHelper = securityHelper;
        this.repairService = repairService;
    }

    @GetMapping
    public String showAdminHome(Model model) throws ResourceException {
        model.addAttribute(USER_HOLDER, securityHelper.getSessionUser());
        model.addAttribute(REPAIRS_LIST_HOLDER, repairService.findTop10ByOrderByRepairDateTimeDesc());
        return HOME_VIEW;
    }
}
