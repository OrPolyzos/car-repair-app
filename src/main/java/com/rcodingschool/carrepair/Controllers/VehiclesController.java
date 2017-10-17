package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Converters.VehicleConverter;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Domain.Vehicle;
import com.rcodingschool.carrepair.Model.VehicleForm;
import com.rcodingschool.carrepair.Model.VehicleSearchForm;
import com.rcodingschool.carrepair.Services.UserService;
import com.rcodingschool.carrepair.Services.VehicleService;
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
public class VehiclesController {
    private static final String VEHICLE_FORM = "vehicleForm";
    private static final String SEARCH_FORM = "vehicleSearchForm";
    private static final String VEHICLE_LIST = "vehicleList";
    private static final String NOT_FOUND = "searchNotFoundMessage";

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    //We will use the @InitBinder annotation and the initBinder method to
    //trim all the user's input from spaces
    //For example if the user enters "    John     " it will be trimmed to "John"
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public String showVehiclesView(Model model) {
        Map<String, Object> map = model.asMap();
        if (!map.containsKey(VEHICLE_FORM)) {
            model.addAttribute(VEHICLE_FORM, new VehicleForm());
        }
        if (!map.containsKey(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new VehicleSearchForm());
        }
        return "vehicles";
    }

    //The registerUserForm method which maps the registerUserForm.ftl for GET requests
    @RequestMapping(value = "/vehicles/{id}", method = RequestMethod.GET)
    public String showVehiclesViewForSpecificUser(@PathVariable Long id, Model model) {
        Map<String, Object> map = model.asMap();
        List<Vehicle> vehiclesList = vehicleService.findByUserID(id);
        model.addAttribute(VEHICLE_LIST, vehiclesList);
        if (!map.containsKey(VEHICLE_FORM)) {
            VehicleForm vehicleForm = new VehicleForm();
            vehicleForm.setAfm(userService.findOne(id).getAfm());
            model.addAttribute(VEHICLE_FORM, vehicleForm);
        }
        if (!map.containsKey(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new VehicleSearchForm());
        }
        return "vehicles";
    }

    //The registerUserForm method which maps the registerUserForm.ftl for POST request
    //This means when the user has submitted data in the input fields
    //We will bind each one of the user's inputs in the registerUserForm.ftl
    // to the corresponding fields of the userForm object
    @RequestMapping(value = "/vehicles/create", method = RequestMethod.POST)
    public String processCreateUser(@Valid @ModelAttribute(VEHICLE_FORM) VehicleForm vehicleForm,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + VEHICLE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            return "redirect:/admin/vehicles";
        }
        try {
            //Trying to build a user from our UserForm
            List<User> userList = userService.findByAfm(vehicleForm.getAfm());
            if (userList.size() != 0) {
                vehicleForm.setAfm(userList.get(0).getAfm());
                Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userList.get(0));
                vehicleService.save(vehicle);
                redirectAttributes.addFlashAttribute("errorMessage", "Vehicle was added!");
                return "redirect:/admin/vehicles";
            } else {
                vehicleForm.setAfm(null);
                redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
                redirectAttributes.addFlashAttribute("errorMessage", "The provided AFM does not correspond to a user! Please create a user first!");
                return "redirect:/admin/vehicles";
            }

        } catch (Exception exception) {
            //if an error occurs show it to the user :(
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/vehicles";
        }
    }

    @RequestMapping(value = "/vehicles/delete/{vehicleID}", method = RequestMethod.GET)
    public String processDeleteUser(@PathVariable String vehicleID,
                                    RedirectAttributes redirectAttributes) {
        vehicleService.deleteByVehicleID(vehicleID);
        redirectAttributes.addFlashAttribute("errorMessage", "Vehicle was deleted successfully!");
        return "redirect:/admin/vehicles";
    }

    @RequestMapping(value = "/vehicles/search", method = RequestMethod.GET)
    public String processSearchUser(@Valid @ModelAttribute(SEARCH_FORM) VehicleSearchForm vehicleSearchForm,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + SEARCH_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(SEARCH_FORM, vehicleSearchForm);
            return "redirect:/admin/vehicles";
        }

        List<Vehicle> vehiclesList;
        if (vehicleSearchForm.getAfm() == null && vehicleSearchForm.getVehicleID() == null) {
            vehiclesList = vehicleService.findAll();
        } else if (vehicleSearchForm.getVehicleID() != null) {
            vehiclesList = vehicleService.findByVehicleID(vehicleSearchForm.getVehicleID());
        } else {
            vehiclesList = userService.findByAfm(vehicleSearchForm.getAfm()).get(0).getUserVehicles();
        }
        if (vehiclesList.isEmpty()) {
            redirectAttributes.addFlashAttribute(NOT_FOUND, "No records were found!");
        } else {
            redirectAttributes.addFlashAttribute(VEHICLE_LIST, vehiclesList);
        }
        return "redirect:/admin/vehicles";
    }

    @RequestMapping(value = "/vehicles/edit/{vehicleID}", method = RequestMethod.GET)
    public String showEditUser(@PathVariable String vehicleID,
                               RedirectAttributes redirectAttributes) {
        //Find the user
        Vehicle vehicle = vehicleService.findByVehicleID(vehicleID).get(0);
        VehicleForm vehicleForm = VehicleConverter.buildVehicleFormObject(vehicle);
        redirectAttributes.addFlashAttribute(vehicleForm);
        return "redirect:/admin/vehicles/editVehicle";
    }

    @RequestMapping(value = "/vehicles/editVehicle", method = RequestMethod.GET)
    public String showEditUserView(Model model) {
        Map<String, Object> map = model.asMap();
        if (!map.containsKey(VEHICLE_FORM)) {
            model.addAttribute(VEHICLE_FORM, new VehicleForm());
            return "redirect:/admin/vehicles";
        }
        return "editVehicle";
    }

    @RequestMapping(value = "/vehicles/editVehicle", method = RequestMethod.POST)
    public String processEditUser(@Valid @ModelAttribute(VEHICLE_FORM) VehicleForm vehicleForm,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + VEHICLE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            return "redirect:/admin/vehicles/editVehicle";
        }
        try {
            //Trying to build a user from our UserForm
            User user = userService.findOne(vehicleForm.getUserID());
            Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, user);
            vehicleService.save(vehicle);

            return "redirect:/admin/vehicles";
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/vehicles/editVehicle";
        }
    }
}
