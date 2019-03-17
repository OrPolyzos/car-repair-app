package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.converter.RepairConverter;
import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
import com.rcodingschool.carrepair.model.RepairForm;
import com.rcodingschool.carrepair.model.RepairSearchForm;
import com.rcodingschool.carrepair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class RepairController extends BaseController {

    private static final String REPAIRS_VIEW = "admin/repair/repairs";
    private static final String EDIT_REPAIR_VIEW = "admin/repair/edit-repair";

    private static final String REPAIR_FORM_HOLDER = "repairForm";
    private static final String REPAIR_SEARCH_FORM_HOLDER = "repairSearchForm";
    private static final String REPAIR_LIST_HOLDER = "repairsList";

    private static final String REPAIR_WAS_CREATED_MESSAGE = "Repair was created!";
    private static final String REPAIR_WAS_UPDATED_MESSAGE = "Repair was updated!";
    private static final String REPAIR_WAS_DELETED_MESSAGE = "Repair was deleted!";

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping(value = "/repairs")
    public String getRepairsView(Model model) {
        fillWithRepairForms(model);
        return REPAIRS_VIEW;
    }

    private void fillWithRepairForms(Model model) {
        if (!model.containsAttribute(REPAIR_FORM_HOLDER)) {
            model.addAttribute(REPAIR_FORM_HOLDER, new RepairForm());
        }
        if (!model.containsAttribute(REPAIR_SEARCH_FORM_HOLDER)) {
            model.addAttribute(REPAIR_SEARCH_FORM_HOLDER, new RepairSearchForm());
        }
    }

    @PostMapping(value = "/repairs/create")
    public String createRepair(@Valid @ModelAttribute(REPAIR_FORM_HOLDER) RepairForm repairForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs");
        }

        try {
            Repair repair = RepairConverter.buildInsertRepairObject(repairForm);
            repairService.save(repair);
            sendInfoMessage(model, REPAIR_WAS_CREATED_MESSAGE);
            fillWithRepairForms(model);
            return REPAIRS_VIEW;
        } catch (VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            redirectAttributes.addFlashAttribute(REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs");
        }
    }

    @PostMapping(value = "/repairs{repairID}/delete")
    public String deleteRepair(@PathVariable("repairID") Long repairID, Model model, RedirectAttributes redirectAttributes) {
        try {
            repairService.deleteByRepairID(repairID);
            sendInfoMessage(model, REPAIR_WAS_DELETED_MESSAGE);
            fillWithRepairForms(model);
            return REPAIRS_VIEW;
        } catch (RepairNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/repairs");
        }
    }

    @PostMapping(value = "/repairs/search")
    public String searchRepair(@Valid @ModelAttribute(REPAIR_SEARCH_FORM_HOLDER) RepairSearchForm repairSearchForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, REPAIR_SEARCH_FORM_HOLDER, repairSearchForm);
            return redirectTo("/admin/repairs");
        }
        model.addAttribute(REPAIR_LIST_HOLDER, repairService.searchRepairs(repairSearchForm));
        fillWithRepairForms(model);
        return REPAIRS_VIEW;
    }


    @GetMapping(value = "/repairs/{repairID}/edit")
    public String getEditRepairView(@PathVariable("repairID") Long repairID, Model model, RedirectAttributes redirectAttributes) {
        if (model.containsAttribute(REPAIR_FORM_HOLDER)) {
            return EDIT_REPAIR_VIEW;
        }
        try {
            Repair repair = repairService.findByRepairID(repairID);
            RepairForm repairForm = RepairConverter.buildRepairFormObject(repair);
            model.addAttribute(REPAIR_FORM_HOLDER, repairForm);
            return EDIT_REPAIR_VIEW;
        } catch (RepairNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/repairs");
        }
    }

    @PostMapping(value = "/repairs/{repairId}/edit")
    public String editRepair(@PathVariable("repairId") Long repairId, @ModelAttribute(REPAIR_FORM_HOLDER) RepairForm repairForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, REPAIR_FORM_HOLDER, repairForm);
            return redirectTo(String.format("/admin/repairs/%s/edit", repairId));
        }

        try {
            Repair repair = RepairConverter.buildUpdateRepairObject(repairForm);
            repairService.save(repair);
            sendInfoMessage(model, REPAIR_WAS_UPDATED_MESSAGE);
            fillWithRepairForms(model);
            return REPAIRS_VIEW;
        } catch (VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            redirectAttributes.addFlashAttribute(REPAIR_FORM_HOLDER, repairForm);
            return redirectTo(String.format("/admin/repairs/%s/edit", repairId));
        }
    }

}
