package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.converter.PartConverter;
import com.rcodingschool.carrepair.domain.Part;
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
import java.util.List;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class PartController extends BaseController {

    private static final String PARTS_VIEW = "parts";
    private static final String EDIT_PART_VIEW = "editPart";

    private static final String PART_FORM_HOLDER = "partForm";
    private static final String PART_SEARCH_FORM_HOLDER = "partSearchForm";
    private static final String NOT_FOUND_MESSAGE_HOLDER = "searchNotFoundMessage";
    private static final String PART_LIST_HOLDER = "partList";

    private static final String PART_WAS_DELETED_MESSAGE = "Part was deleted!";
    private static final String PART_WAS_CREATED_MESSAGE = "Part was created!";
    private static final String NO_RECORDS_WERE_FOUND_MESSAGE = "No records were found!";
    private static final String PART_WAS_UPDATED_MESSAGE = "Part was updated!";
    private static final String GENERIC_ERROR_MESSAGE = "Something went wrong!";

    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping(value = "/parts")
    public String showPartsView(Model model) {
        if (!model.containsAttribute(PART_FORM_HOLDER)) {
            model.addAttribute(PART_FORM_HOLDER, new PartForm());
        }
        if (!model.containsAttribute(PART_SEARCH_FORM_HOLDER)) {
            model.addAttribute(PART_SEARCH_FORM_HOLDER, new PartSearchForm());
        }
        return PARTS_VIEW;
    }

    @PostMapping("/parts/create")
    public String processCreatePart(@Valid @ModelAttribute(PART_FORM_HOLDER) PartForm partForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, PART_FORM_HOLDER, partForm);
            return redirectTo("/admin/parts");
        }
        try {
            Part part = PartConverter.buildInsertPartObject(partForm);
            partService.save(part);
            redirectErrorMessage(redirectAttributes, PART_WAS_CREATED_MESSAGE);
        } catch (Exception exception) {
            redirectErrorMessage(redirectAttributes, GENERIC_ERROR_MESSAGE);
        }
        return redirectTo("/admin/parts");

    }

    @PostMapping("/parts/delete/{id}")
    public String processDeletePart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        partService.deleteByPartID(id);
        redirectErrorMessage(redirectAttributes, PART_WAS_DELETED_MESSAGE);
        return redirectTo("/admin/parts");
    }

    @GetMapping("/parts/search")
    public String processSearchPart(@Valid @ModelAttribute(PART_SEARCH_FORM_HOLDER) PartSearchForm partSearchForm,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, PART_SEARCH_FORM_HOLDER, partSearchForm);
        }

        List<Part> partsList;
        if (partSearchForm.getPartID() == null && (partSearchForm.getPartPriceStart() == null || partSearchForm.getPartPriceEnd() == null)) {
            partsList = partService.findAll();
        } else if (partSearchForm.getPartID() != null) {
            partsList = partService.findByPartID(partSearchForm.getPartID());
        } else {
            partsList = partService.findAllByPartPriceBetween(partSearchForm.getPartPriceStart(), partSearchForm.getPartPriceEnd());
        }
        if (partsList.isEmpty()) {
            redirectAttributes.addFlashAttribute(NOT_FOUND_MESSAGE_HOLDER, NO_RECORDS_WERE_FOUND_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(PART_LIST_HOLDER, partsList);
        }
        return redirectTo("/admin/parts");
    }

    @GetMapping("/parts/edit/{id}")
    public String showEditPart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Part part = partService.findOne(id);
        PartForm partForm = PartConverter.buildPartFormObject(part);
        redirectAttributes.addFlashAttribute(partForm);
        return redirectTo("/admin/parts/editPart");
    }

    @GetMapping("/parts/editPart")
    public String showEditPartView(Model model) {
        if (!model.containsAttribute(PART_FORM_HOLDER)) {
            return redirectTo("/admin/parts");
        }
        return EDIT_PART_VIEW;
    }

    @PostMapping("/parts/editPart")
    public String processEditPart(@Valid @ModelAttribute(PART_FORM_HOLDER) PartForm partForm,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes,bindingResult, PART_FORM_HOLDER, partForm);
            return redirectTo("/admin/parts/editPart");
        }
        try {
            Part part = PartConverter.buildUpdatePartObject(partForm);
            partService.save(part);
            redirectErrorMessage(redirectAttributes, PART_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/parts");
        } catch (Exception exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/parts/editPart");
        }
    }
}










