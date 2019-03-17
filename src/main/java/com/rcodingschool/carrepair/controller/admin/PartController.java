package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.converter.PartConverter;
import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.exception.part.PartNotFoundException;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
import com.rcodingschool.carrepair.model.PartForm;
import com.rcodingschool.carrepair.model.PartSearchForm;
import com.rcodingschool.carrepair.service.PartService;
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
public class PartController extends BaseController {

    private static final String PARTS_VIEW = "admin/part/parts";
    private static final String EDIT_PART_VIEW = "admin/part/edit-part";

    private static final String PART_FORM_HOLDER = "partForm";
    private static final String PART_SEARCH_FORM_HOLDER = "partSearchForm";
    private static final String PART_LIST_HOLDER = "partList";

    private static final String PART_WAS_CREATED_MESSAGE = "Part was created!";
    private static final String PART_WAS_UPDATED_MESSAGE = "Part was updated!";
    private static final String PART_WAS_DELETED_MESSAGE = "Part was deleted!";

    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping(value = "/parts")
    public String getPartsView(Model model) {
        fillWithPartForms(model);
        return PARTS_VIEW;
    }

    private void fillWithPartForms(Model model) {
        if (!model.containsAttribute(PART_FORM_HOLDER)) {
            model.addAttribute(PART_FORM_HOLDER, new PartForm());
        }
        if (!model.containsAttribute(PART_SEARCH_FORM_HOLDER)) {
            model.addAttribute(PART_SEARCH_FORM_HOLDER, new PartSearchForm());
        }
    }

    @PostMapping("/parts/create")
    public String createPart(@Valid @ModelAttribute(PART_FORM_HOLDER) PartForm partForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, PART_FORM_HOLDER, partForm);
            return redirectTo("/admin/parts");
        }
        try {
            Part part = PartConverter.buildInsertPartObject(partForm);
            partService.save(part);
            sendInfoMessage(model, PART_WAS_CREATED_MESSAGE);
            fillWithPartForms(model);
            return PARTS_VIEW;
        } catch (RepairNotFoundException | VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            redirectAttributes.addFlashAttribute(PART_FORM_HOLDER, partForm);
            return redirectTo("/admin/parts");
        }

    }

    @PostMapping("/parts/{partId}/delete")
    public String deletePart(@PathVariable("partId") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            partService.deleteByPartID(id);
            sendInfoMessage(model, PART_WAS_DELETED_MESSAGE);
            fillWithPartForms(model);
            return PARTS_VIEW;
        } catch (RepairNotFoundException | PartNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/parts");
        }
    }

    @PostMapping("/parts/search")
    public String searchParts(@Valid @ModelAttribute(PART_SEARCH_FORM_HOLDER) PartSearchForm partSearchForm,
                              Model model, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, PART_SEARCH_FORM_HOLDER, partSearchForm);
            return redirectTo("/admin/parts");
        }
        model.addAttribute(PART_LIST_HOLDER, partService.searchForParts(partSearchForm));
        fillWithPartForms(model);
        return PARTS_VIEW;
    }

    @GetMapping("/parts/{id}/edit")
    public String getEditPartView(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        if (model.containsAttribute(PART_FORM_HOLDER)) {
            return EDIT_PART_VIEW;
        }
        try {
            Part part = partService.findByPartID(id).orElseThrow(() -> new PartNotFoundException(id));
            PartForm partForm = PartConverter.buildPartFormObject(part);
            model.addAttribute(PART_FORM_HOLDER, partForm);
            return EDIT_PART_VIEW;
        } catch (PartNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/parts");
        }
    }

    @PostMapping("/parts/{partId}/edit")
    public String editPart(@PathVariable("partId") Long partId, @Valid @ModelAttribute(PART_FORM_HOLDER) PartForm partForm,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, PART_FORM_HOLDER, partForm);
            return redirectTo(String.format("/admin/parts/%s/edit", partId));
        }
        try {
            Part part = PartConverter.buildUpdatePartObject(partForm);
            partService.save(part);
            sendInfoMessage(model, PART_WAS_UPDATED_MESSAGE);
            fillWithPartForms(model);
            return PARTS_VIEW;
        } catch (RepairNotFoundException | VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            redirectAttributes.addFlashAttribute(PART_FORM_HOLDER, partForm);
            return redirectTo(String.format("/admin/parts/%s/edit", partId));
        }
    }
}


