package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.InformativeController;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.security.SecurityHelper;
import com.rcodingschool.carrepair.service.resource.RepairResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class AdminHomeController implements InformativeController {

    private static final String HOME_VIEW = "admin/home";

    private static final String USER_HOLDER = "user";
    private static final String REPAIRS_LIST_HOLDER = "repairList";

    private final SecurityHelper securityHelper;
    private final RepairResourceService repairResourceService;

    @Autowired
    public AdminHomeController(SecurityHelper securityHelper, RepairResourceService repairResourceService) {
        this.securityHelper = securityHelper;
        this.repairResourceService = repairResourceService;
    }

    @GetMapping
    public String showAdminHome(Model model) throws ResourceException {
        model.addAttribute(USER_HOLDER, securityHelper.getSessionUser());
        model.addAttribute(REPAIRS_LIST_HOLDER, repairResourceService.findTop10ByOrderByRepairDateTimeDesc());
        return HOME_VIEW;
    }
}
