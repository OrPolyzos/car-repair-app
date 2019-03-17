package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.converter.RepairConverter;
import com.rcodingschool.carrepair.domain.Repair;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class RepairController extends BaseController {

    private static final String REPAIRS_VIEW = "repairs";
    private static final String EDIT_REPAIR_VIEW = "editRepair";

    private static final String REPAIR_FORM_HOLDER = "repairForm";
    private static final String REPAIR_SEARCH_FORM_HOLDER = "repairSearchForm";
    private static final String REPAIR_LIST_HOLDER = "repairsList";
    private static final String SEARCH_NOT_FOUND_MESSAGE_HOLDER = "searchNotFoundMessage";

    private static final String REPAIR_WAS_CREATED_MESSAGE = "Repair was created!";
    private static final String PLEASE_CHECK_AGAIN_THE_FIELDS_MESSAGE = "Please check again the fields!";
    private static final String REPAIR_WAS_DELETED_MESSAGE = "Repair was deleted!";
    private static final String PLEASE_FILL_IN_BOTH_DATE_FIELDS_MESSAGE = "Please fill in both date fields!";
    private static final String NO_RECORDS_WERE_FOUND_MESSAGE = "No records were found!";
    private static final String REPAIR_WAS_UPDATED_MESSAGE = "Repair was updated!";

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping(value = "/repairs")
    public String showRepairView(Model model) {
        if (!model.containsAttribute(REPAIR_FORM_HOLDER)) {
            model.addAttribute(REPAIR_FORM_HOLDER, new RepairForm());
        }
        if (!model.containsAttribute(REPAIR_SEARCH_FORM_HOLDER)) {
            model.addAttribute(REPAIR_SEARCH_FORM_HOLDER, new RepairSearchForm());
        }
        return REPAIRS_VIEW;
    }

    @PostMapping(value = "/repairs/create")
    public String processCreateRepair(@Valid @ModelAttribute(REPAIR_FORM_HOLDER) RepairForm repairForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs");
        }

        try {
            Repair repair = RepairConverter.buildInsertRepairObject(repairForm);
            repairService.save(repair);
            redirectErrorMessage(redirectAttributes, REPAIR_WAS_CREATED_MESSAGE);
        } catch (Exception exception) {
            redirectErrorMessage(redirectAttributes, PLEASE_CHECK_AGAIN_THE_FIELDS_MESSAGE);
        }
        return redirectTo("/admin/repairs");
    }

    @PostMapping(value = "/repairs/delete/{repairID}")
    public String processDeleteRepair(@PathVariable Long repairID, RedirectAttributes redirectAttributes) {
        repairService.deleteByRepairID(repairID);
        redirectErrorMessage(redirectAttributes, REPAIR_WAS_DELETED_MESSAGE);
        return redirectTo("/admin/repairs");
    }

    @GetMapping(value = "/repairs/search")
    public String processSearchRepair(@Valid @ModelAttribute(REPAIR_SEARCH_FORM_HOLDER) RepairSearchForm repairSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, REPAIR_SEARCH_FORM_HOLDER, repairSearchForm);
            return redirectTo("/admin/repairs");
        }

        List<Repair> repairList = new ArrayList<>();
        if (repairSearchForm.getRepairID() != null) {
            repairList = repairService.findByRepairID(repairSearchForm.getRepairID());
        } else {
            if (repairSearchForm.getRepairVehicleID() != null) {
                if (repairSearchForm.getRepairDateTimeStart() == null && repairSearchForm.getRepairDateTimeEnd() == null) {
                    repairList = repairService.findByVehicleID(repairSearchForm.getRepairVehicleID());
                } else {
                    if (repairSearchForm.getRepairDateTimeStart() != null && repairSearchForm.getRepairDateTimeEnd() != null) {
                        repairList = repairService.findAllByRepairDateTimeBetweenAndVehicleID(repairSearchForm.getRepairDateTimeStart(), repairSearchForm.getRepairDateTimeEnd(), repairSearchForm.getRepairVehicleID());
                    }
                }
            } else {
                if (repairSearchForm.getRepairDateTimeStart() != null && repairSearchForm.getRepairDateTimeEnd() != null) {
                    repairList = repairService.findAllByRepairDateTimeBetween(repairSearchForm.getRepairDateTimeStart(), repairSearchForm.getRepairDateTimeEnd());
                } else {
                    repairList = repairService.findAll();
                }
            }
        }
        if ((repairSearchForm.getRepairDateTimeStart() != null && repairSearchForm.getRepairDateTimeEnd() == null) ||
                (repairSearchForm.getRepairDateTimeStart() == null && repairSearchForm.getRepairDateTimeEnd() != null)) {
            redirectErrorMessage(redirectAttributes, PLEASE_FILL_IN_BOTH_DATE_FIELDS_MESSAGE);
            return redirectTo("/admin/repairs");
        }
        if (repairList.isEmpty()) {
            redirectAttributes.addFlashAttribute(SEARCH_NOT_FOUND_MESSAGE_HOLDER, NO_RECORDS_WERE_FOUND_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(REPAIR_LIST_HOLDER, repairList);
        }
        return redirectTo("/admin/repairs");
    }


    @GetMapping(value = "/repairs/edit/{repairID}")
    public String showEditRepair(@PathVariable Long repairID, RedirectAttributes redirectAttributes) {
        Repair repair = repairService.findOne(repairID);
        RepairForm repairForm = RepairConverter.buildRepairFormObject(repair);
        redirectAttributes.addFlashAttribute(repairForm);
        return redirectTo("/admin/repairs/editRepair");
    }

    @GetMapping(value = "/repairs/editRepair")
    public String showEditRepairView(Model model) {
        Map<String, Object> map = model.asMap();
        if (!map.containsKey(REPAIR_FORM_HOLDER)) {
            return redirectTo("/admin/repairs");
        }
        return EDIT_REPAIR_VIEW;
    }

    @PostMapping(value = "/repairs/editRepair")
    public String processEditRepair(@Valid @ModelAttribute(REPAIR_FORM_HOLDER) RepairForm repairForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes,bindingResult,REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs/editRepair");
        }
        try {
            Repair repair = RepairConverter.buildUpdateRepairObject(repairForm);
            repairService.save(repair);
            redirectErrorMessage(redirectAttributes, REPAIR_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/repairs");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("errorMessage", PLEASE_CHECK_AGAIN_THE_FIELDS_MESSAGE);
            redirectAttributes.addFlashAttribute(REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs/editRepair");
        }
    }


}
