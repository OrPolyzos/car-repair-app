package com.rcodingschool.carrepair.controller.admin;


import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.converter.RepairPartsConverter;
import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
import com.rcodingschool.carrepair.model.RepairPartForm;
import com.rcodingschool.carrepair.service.PartService;
import com.rcodingschool.carrepair.service.RepairPartService;
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
public class RepairPartsController extends BaseController {

    private static final String REPAIR_PARTS_VIEW = "admin/repair-part/repair-parts";

    private static final String REPAIR_PART_FORM_HOLDER = "repairPartsForm";
    private static final String REPAIR_PART_LIST_HOLDER = "repairPartList";
    private static final String ALL_PARTS_LIST_HOLDER = "allParts";

    private static final String CREATED_MESSAGE = "The part was added!";
    private static final String DELETED_MESSAGE = "The part was deleted!";

    private final PartService partService;
    private final RepairPartService repairPartService;

    @Autowired
    public RepairPartsController(PartService partService, RepairPartService repairPartService) {
        this.partService = partService;
        this.repairPartService = repairPartService;
    }

    @GetMapping("/repairs/{repairID}/parts")
    public String getRepairPartsView(@PathVariable Long repairID, Model model) {
        fillWithRepairPartForms(repairID, model);
        return REPAIR_PARTS_VIEW;
    }

    private void fillWithRepairPartForms(@PathVariable Long repairID, Model model) {
        if (!model.containsAttribute(REPAIR_PART_FORM_HOLDER)) {
            RepairPartForm repairPartForm = new RepairPartForm();
            repairPartForm.setRepairID(repairID);
            model.addAttribute(REPAIR_PART_FORM_HOLDER, repairPartForm);
        }
        model.addAttribute(REPAIR_PART_LIST_HOLDER, repairPartService.findAllByRepairID(repairID));
        model.addAttribute(ALL_PARTS_LIST_HOLDER, partService.findAll());
    }


    @PostMapping("/repairs/{repairID}/parts")
    public String createRepairPart(@PathVariable("repairID") Long repairId, @Valid @ModelAttribute(REPAIR_PART_FORM_HOLDER) RepairPartForm repairPartForm,
                                   BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, REPAIR_PART_FORM_HOLDER, repairPartForm);
            return redirectTo(String.format("/admin/repairs/%s/parts", repairId));
        }
        try {
            RepairPart repairPart = RepairPartsConverter.buildRepairPartObject(repairPartForm);
            repairPartService.save(repairPart);
            sendInfoMessage(model, CREATED_MESSAGE);
            fillWithRepairPartForms(repairId, model);
            return REPAIR_PARTS_VIEW;
        } catch (RepairNotFoundException | VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(String.format("/admin/repairs/%s/parts", repairId));
        }
    }

    @PostMapping(value = "/repairs/{repairID}/parts/{partID}/delete")
    public String processDeleteRepairPart(@PathVariable Long repairID, @PathVariable Long partID, Model model, RedirectAttributes redirectAttributes) {
        try {
            repairPartService.deleteByRepairIDAndPartID(repairID, partID);
            sendInfoMessage(model, DELETED_MESSAGE);
            fillWithRepairPartForms(repairID, model);
            return REPAIR_PARTS_VIEW;
        } catch (RepairNotFoundException | VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(String.format("/admin/repairs/%s/parts", repairID));
        }
    }

}
