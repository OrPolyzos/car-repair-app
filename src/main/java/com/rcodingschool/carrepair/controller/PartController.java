package com.rcodingschool.carrepair.controller;

import com.rcodingschool.carrepair.converter.PartConverter;
import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.model.PartForm;
import com.rcodingschool.carrepair.model.PartSearchForm;
import com.rcodingschool.carrepair.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private static final String SEARCH_FORM_HOLDER = "partSearchForm";
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
        if (!model.containsAttribute(SEARCH_FORM_HOLDER)) {
            model.addAttribute(SEARCH_FORM_HOLDER, new PartSearchForm());
        }
        return PARTS_VIEW;
    }

    @PostMapping("/parts/create")
    public String processCreatePart(@Valid @ModelAttribute(PART_FORM_HOLDER) PartForm partForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + PART_FORM_HOLDER, bindingResult);
            redirectAttributes.addFlashAttribute(PART_FORM_HOLDER, partForm);
            return redirectTo("/admin/parts");
        }
        try {
            Part part = PartConverter.buildInsertPartObject(partForm);
            partService.save(part);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, PART_WAS_CREATED_MESSAGE);
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, GENERIC_ERROR_MESSAGE);
        }
        return redirectTo("/admin/parts");

    }

    //The processDeletePart() method will map "/admin/parts/delete/{id}" GET requests and
    //will delete a part and redirect to "/admin/parts"
    @PostMapping("/parts/delete/{id}")
    public String processDeletePart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        //Delete the part
        partService.deleteByPartID(id);
        //Send information to the user
        redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, PART_WAS_DELETED_MESSAGE);
        return redirectTo("/admin/parts");
    }

    //The processSearchPart() method will map "/parts/search" GET requests and
    //will search for a part by either partID or (partPrice1, partPrice2)
    @GetMapping("/parts/search")
    public String processSearchPart(@Valid @ModelAttribute(SEARCH_FORM_HOLDER) PartSearchForm partSearchForm,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + SEARCH_FORM_HOLDER, bindingResult);
            //Send information to the user
            redirectAttributes.addFlashAttribute(SEARCH_FORM_HOLDER, partSearchForm);
        }

        //Initialize a new list of Parts to hold the results of the search
        List<Part> partsList;
        //Getting the searchForm values and checking
        //If both are null
        if (partSearchForm.getPartID() == null && (partSearchForm.getPartPriceStart() == null || partSearchForm.getPartPriceEnd() == null)) {
            //Then we retrieve all the parts
            partsList = partService.findAll();
            //If the AFM is not null
        } else if (partSearchForm.getPartID() != null) {
            partsList = partService.findByPartID(partSearchForm.getPartID());
        } else {
            //We search for Parts based on PartPriceStart and PartPriceEnd
            partsList = partService.findAllByPartPriceBetween(partSearchForm.getPartPriceStart(), partSearchForm.getPartPriceEnd());
        }
        //If the List is Empty
        if (partsList.isEmpty()) {
            //We send Information to the user
            redirectAttributes.addFlashAttribute(NOT_FOUND_MESSAGE_HOLDER, NO_RECORDS_WERE_FOUND_MESSAGE);
        } else {
            //else we send the userList to our users.ftl
            redirectAttributes.addFlashAttribute(PART_LIST_HOLDER, partsList);
        }
        return redirectTo("/admin/parts");
    }

    //The showEditPpart() method will map "/parts/edit/{id} GET requests
    //and will try to find a part based on the id and show the editPart.ftl
    //so that the Admin can edit his details
    @GetMapping("/parts/edit/{id}")
    public String showEditPart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        //Find the part
        Part part = partService.findOne(id);
        //Build a partForm Object based on the user we found
        PartForm partForm = PartConverter.buildPartFormObject(part);
        //Send the partForm to the editPart.ftl
        redirectAttributes.addFlashAttribute(partForm);
        return redirectTo("/admin/parts/editPart");
    }

    //the showEditPartView will map "/parts/editPart" GET requests
    //and redirect to /admin/parts or show the "editPart".ftl
    @GetMapping("/parts/editPart")
    public String showEditPartView(Model model) {
        //If there is not already a PartForm something went wrong so we redirect
        if (!model.containsAttribute(PART_FORM_HOLDER)) {
            return redirectTo("/admin/parts");
        }
        //If there is not PartForm
        return EDIT_PART_VIEW;
    }

    //The processEditPart() method will map "/parts/editPart" POST requests
    //and will try to change the details of a Part
    @PostMapping("/parts/editPart")
    public String processEditPart(@Valid @ModelAttribute(PART_FORM_HOLDER) PartForm partForm,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the part again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding partForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + PART_FORM_HOLDER, bindingResult);
            redirectAttributes.addFlashAttribute(PART_FORM_HOLDER, partForm);
            return redirectTo("/admin/parts/editPart");
        }
        try {
            //Trying to build a part from our PartForm
            Part part = PartConverter.buildUpdatePartObject(partForm);
            //Save the user
            partService.save(part);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, PART_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/parts");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, exception.getMessage());
            return redirectTo("/admin/parts/editPart");
        }
    }

}










