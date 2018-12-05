package com.rcodingschool.carrepair.controller;


import com.rcodingschool.carrepair.converter.RepairPartsConverter;
import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.model.RepairPartForm;
import com.rcodingschool.carrepair.service.PartService;
import com.rcodingschool.carrepair.service.RepairPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class RepairPartsController extends BaseController {

    private static final String REPAIR_PARTS_VIEW = "repairParts";

    private static final String REPAIR_PART_FORM_HOLDER = "repairPartsForm";
    private static final String REPAIR_PART_LIST_HOLDER = "repairPartList";
    private static final String WHOLE_PART_LIST_HOLDER = "wholePartList";

    private static final String THE_PART_WAS_DELETED_FROM_THE_REPAIR_MESSAGE = "The part was deleted from the repair!";

    private final PartService partService;
    private final RepairPartService repairPartService;

    @Autowired
    public RepairPartsController(PartService partService, RepairPartService repairPartService) {
        this.partService = partService;
        this.repairPartService = repairPartService;
    }

    @GetMapping("/repairs/parts/{repairID}")
    public String showRepairPartsView(@PathVariable Long repairID, Model model) {
        //If our Model does not contain a repairPartForm, add a new RepairPartForm()
        if (!model.containsAttribute(REPAIR_PART_FORM_HOLDER)) {
            RepairPartForm repairPartForm = new RepairPartForm();
            repairPartForm.setRepairID(repairID);
            model.addAttribute(REPAIR_PART_FORM_HOLDER, repairPartForm);
        }
        List<RepairPart> repairsCurrentPartsList = repairPartService.findAllByRepairID(repairID);
        if (!repairsCurrentPartsList.isEmpty()) {
            model.addAttribute(REPAIR_PART_LIST_HOLDER, repairsCurrentPartsList);
        }
        List<Part> wholePartsList = partService.findAll();
        if (!wholePartsList.isEmpty()) {
            model.addAttribute(WHOLE_PART_LIST_HOLDER, wholePartsList);
        }
        return REPAIR_PARTS_VIEW;
    }


    @PostMapping("/repairs/parts/add")
    public String processAddRepairPart(@Valid @ModelAttribute(REPAIR_PART_FORM_HOLDER) RepairPartForm repairPartForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding repairPartForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + REPAIR_PART_FORM_HOLDER, bindingResult);
            redirectAttributes.addFlashAttribute(REPAIR_PART_FORM_HOLDER, repairPartForm);
            return redirectTo("/admin/repairs/parts/" + repairPartForm.getRepairID());
        }
        try {
            //Trying to build a RepairPart object
            RepairPart repairPart = RepairPartsConverter.buildRepairPartObject(repairPartForm);
            repairPartService.save(repairPart);
            return redirectTo("/admin/repairs/parts/" + String.valueOf(repairPartForm.getRepairID()));

        } catch (Exception exception) {
            //if an error occurs show it to the user :(
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, exception.getMessage());
            return redirectTo("/admin/repairs/parts/" + String.valueOf(repairPartForm.getRepairID()));
        }
    }

    //The processDeleteUser() method will map "/admin/users/delete/{id}" GET requests and
    //will delete a user and redirect to "/admin/users"
    @RequestMapping(value = "/repairs/parts/delete/{repairID}/{partID}", method = RequestMethod.POST)
    public String processDeleteRepairPart(@PathVariable Long repairID, @PathVariable Long partID, RedirectAttributes redirectAttributes) {
        //Delete the user
        repairPartService.deleteByRepairIDAndPartID(repairID, partID);
        //Send information to the user
        redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, THE_PART_WAS_DELETED_FROM_THE_REPAIR_MESSAGE);
        return redirectTo("/admin/repairs/parts/" + String.valueOf(repairID));
    }

}
