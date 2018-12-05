package com.rcodingschool.carrepair.controller;

import com.rcodingschool.carrepair.converter.RepairConverter;
import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.model.RepairForm;
import com.rcodingschool.carrepair.model.RepairSearchForm;
import com.rcodingschool.carrepair.service.RepairService;
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
    private static final String SEARCH_FORM_HOLDER = "repairSearchForm";
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
        //If our Model does not contain a repairForm, add a new RepairForm()
        if (!model.containsAttribute(REPAIR_FORM_HOLDER)) {
            model.addAttribute(REPAIR_FORM_HOLDER, new RepairForm());
        }
        //if our Model does not contain a searchForm, add a new SearchForm()
        if (!model.containsAttribute(SEARCH_FORM_HOLDER)) {
            model.addAttribute(SEARCH_FORM_HOLDER, new RepairSearchForm());
        }
        return REPAIRS_VIEW;
    }

    @PostMapping(value = "/repairs/create")
    public String processCreateRepair(@Valid @ModelAttribute(REPAIR_FORM_HOLDER) RepairForm repairForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding repairForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + REPAIR_FORM_HOLDER, bindingResult);
            redirectAttributes.addFlashAttribute(REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs");
        }

        try {
            //Trying to build a repair from our RepairForm
            Repair repair = RepairConverter.buildInsertRepairObject(repairForm);
            //Save the repair
            repairService.save(repair);

            //Send information to the user
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, REPAIR_WAS_CREATED_MESSAGE);
        } catch (Exception exception) {
            //if an error occurs show it to the repair
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, PLEASE_CHECK_AGAIN_THE_FIELDS_MESSAGE);
        }
        return redirectTo("/admin/repairs");
    }

    //The processDeleteRerair() method will map "/admin/repairs/delete/{id}" GET requests and
    //will delete a repair and redirect to "/admin/repairs"
    @PostMapping(value = "/repairs/delete/{repairID}")
    public String processDeleteRepair(@PathVariable Long repairID,
                                      RedirectAttributes redirectAttributes) {
        //Delete the repair
        repairService.deleteByRepairID(repairID);
        //Send information to the user
        redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, REPAIR_WAS_DELETED_MESSAGE);
        return redirectTo("/admin/repairs");
    }

    //The processSearchUser() method will map "/repairs/search" GET requests and
    //will search for a reapair by either AFM or Email
    @GetMapping(value = "/repairs/search")
    public String processSearchRepair(@Valid @ModelAttribute(SEARCH_FORM_HOLDER) RepairSearchForm repairSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + SEARCH_FORM_HOLDER, bindingResult);
            //Send information to the user
            redirectAttributes.addFlashAttribute(SEARCH_FORM_HOLDER, repairSearchForm);
            return redirectTo("/admin/repairs");
        }

        //Initialize a new list of Repairs to hold the results of the search
        List<Repair> repairList = new ArrayList<>();
        //Getting the searchForm values and checking
        //If both are null
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
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, PLEASE_FILL_IN_BOTH_DATE_FIELDS_MESSAGE);
            return redirectTo("/admin/repairs");
        }
        //If the List is Empty
        if (repairList.isEmpty()) {
            //We send Information to the user
            redirectAttributes.addFlashAttribute(SEARCH_NOT_FOUND_MESSAGE_HOLDER, NO_RECORDS_WERE_FOUND_MESSAGE);
        } else {

            redirectAttributes.addFlashAttribute(REPAIR_LIST_HOLDER, repairList);
        }
        return redirectTo("/admin/repairs");
    }


    @GetMapping(value = "/repairs/edit/{repairID}")
    public String showEditRepair(@PathVariable Long repairID, RedirectAttributes redirectAttributes) {
        //Find the repair
        Repair repair = repairService.findOne(repairID);
        //Build a repairForm Object based on the repair we found
        RepairForm repairForm = RepairConverter.buildRepairFormObject(repair);

        redirectAttributes.addFlashAttribute(repairForm);
        return redirectTo("/admin/repairs/editRepair");
    }

    //the showEditRepairView will map "/repairs/editRepair" GET requests
    @GetMapping(value = "/repairs/editRepair")
    public String showEditRepairView(Model model) {
        //Get the model
        Map<String, Object> map = model.asMap();
        //If there is not already a RepairForm something went wrong so we redirect
        if (!map.containsKey(REPAIR_FORM_HOLDER)) {
            return redirectTo("/admin/repairs");
        }
        //If there is not RepairForm
        return EDIT_REPAIR_VIEW;
    }

    //The processEditRepair() method will map "/repairs/editRepair" POST requests
    //and will try to change the details of a Repair
    @PostMapping(value = "/repairs/editRepair")
    public String processEditRepair(@Valid @ModelAttribute(REPAIR_FORM_HOLDER) RepairForm repairForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + REPAIR_FORM_HOLDER, bindingResult);
            redirectAttributes.addFlashAttribute(REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs/editRepair");
        }
        try {
            //Trying to build a repair from our RepairForm
            //Full means we include repairID also
            Repair repair = RepairConverter.buildUpdateRepairObject(repairForm);
            //Save the repair
            repairService.save(repair);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, REPAIR_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/repairs");
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute("errorMessage", PLEASE_CHECK_AGAIN_THE_FIELDS_MESSAGE);
            redirectAttributes.addFlashAttribute(REPAIR_FORM_HOLDER, repairForm);
            return redirectTo("/admin/repairs/editRepair");
        }
    }


}
