package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Converters.PartConverter;
import com.rcodingschool.carrepair.Domain.Part;
import com.rcodingschool.carrepair.Model.PartForm;
import com.rcodingschool.carrepair.Model.PartSearchForm;
import com.rcodingschool.carrepair.Services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class PartController {

    private static final String PART_FORM = "partForm";
    private static final String SEARCH_FORM = "partSearchForm";
    private static final String PART_LIST = "partList";
    private static final String NOT_FOUND = "searchNotFoundMessage";
    private static final String MESSAGE = "errorMessage";

    @Autowired
    private PartService partService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = "/parts", method = RequestMethod.GET)
    public String showPartsView(Model model) {
        Map<String, Object> map = model.asMap();

        if (!map.containsKey(PART_FORM)) {
            model.addAttribute(PART_FORM, new PartForm());
        }

        if (!map.containsKey(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new PartSearchForm());
        }
        return "parts";
    }

    @RequestMapping(value = "/parts/create", method = RequestMethod.POST)
    public String processCreatePart(@Valid @ModelAttribute(PART_FORM) PartForm partForm,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + PART_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(PART_FORM, partForm);
            return "redirect:/admin/parts";
        }
        try {

            Part part = PartConverter.buildInsertPartObject(partForm);

            partService.save(part);

            redirectAttributes.addFlashAttribute(MESSAGE, "Part was created!");
        } catch (Exception exception) {

            redirectAttributes.addFlashAttribute(MESSAGE, exception.getMessage());
        }

        return "redirect:/admin/parts";

    }

    //The processDeletePart() method will map "/admin/parts/delete/{id}" GET requests and
    //will delete a part and redirect to "/admin/parts"
    @RequestMapping(value = "/parts/delete/{id}", method = RequestMethod.POST)
    public String processDeletePart(@PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {
        //Delete the part
        partService.deleteByPartID(id);
        //Send information to the user
        redirectAttributes.addFlashAttribute(MESSAGE, "Part was deleted!");
        return "redirect:/admin/parts";
    }

    //The processSearchPart() method will map "/parts/search" GET requests and
    //will search for a part by either partID or (partPrice1, partPrice2)
    @RequestMapping(value = "/parts/search", method = RequestMethod.GET)
    public String processSearchPart(@Valid @ModelAttribute(SEARCH_FORM) PartSearchForm partSearchForm,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + SEARCH_FORM, bindingResult);
            //Send information to the user
            redirectAttributes.addFlashAttribute(SEARCH_FORM, partSearchForm);
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
            redirectAttributes.addFlashAttribute(NOT_FOUND, "No records were found!");
        } else {
            //else we send the userList to our users.ftl
            redirectAttributes.addFlashAttribute(PART_LIST, partsList);
        }
        return "redirect:/admin/parts";
    }

    //The showEditPpart() method will map "/parts/edit/{id} GET requests
    //and will try to find a part based on the id and show the editPart.ftl
    //so that the Admin can edit his details
    @RequestMapping(value = "/parts/edit/{id}", method = RequestMethod.GET)
    public String showEditPart(@PathVariable Long id,
                               RedirectAttributes redirectAttributes) {
        //Find the part
        Part part = partService.findOne(id);
        //Build a partForm Object based on the user we found
        PartForm partForm = PartConverter.buildPartFormObject(part);
        //Send the partForm to the editPart.ftl
        redirectAttributes.addFlashAttribute(partForm);
        return "redirect:/admin/parts/editPart";
    }

    //the showEditPartView will map "/parts/editPart" GET requests
    //and redirect to /admin/parts or show the "editPart".ftl
    @RequestMapping(value = "/parts/editPart", method = RequestMethod.GET)
    public String showEditPartView(Model model) {
        //Get the model
        Map<String, Object> map = model.asMap();
        //If there is not already a PartForm something went wrong so we redirect
        if (!map.containsKey(PART_FORM)) {
            return "redirect:/admin/parts";
        }
        //If there is not PartForm
        return "editPart";
    }

    //The processEditPart() method will map "/parts/editPart" POST requests
    //and will try to change the details of a Part
    @RequestMapping(value = "/parts/editPart", method = RequestMethod.POST)
    public String processEditPart(@Valid @ModelAttribute(PART_FORM) PartForm partForm,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the part again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding partForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + PART_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(PART_FORM, partForm);
            return "redirect:/admin/parts/editPart";
        }
        try {
            //Trying to build a part from our PartForm
            Part part = PartConverter.buildUpdatePartObject(partForm);
            //Save the user
            partService.save(part);
            redirectAttributes.addFlashAttribute(MESSAGE, "Part was updated!");
            return "redirect:/admin/parts";
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/parts/editPart";
        }
    }

}










