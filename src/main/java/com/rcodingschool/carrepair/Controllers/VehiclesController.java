package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Converters.VehicleConverter;
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
    private static final String MESSAGE = "errorMessage";

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


    //The showVehiclesView method which maps the "/admin/vehicles/" GET requests and returns the vehiclesOrestes.ftl
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public String showVehiclesView(Model model) {
        Map<String, Object> map = model.asMap();
        //If our Model does not contain a vehicleForm, add a new VehicleForm()
        if (!map.containsKey(VEHICLE_FORM)) {
            model.addAttribute(VEHICLE_FORM, new VehicleForm());
        }
        //if our Model does not contain a searchForm, add a new VehicleSearchForm()
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

    //The processCreateVehicle() method will map "/admin/vehicle/create" POST requests
    //and eventually it will redirect to "/admin/vehicles"
    @RequestMapping(value = "/vehicles/create", method = RequestMethod.POST)
    public String processCreateVehicle(@Valid @ModelAttribute(VEHICLE_FORM) VehicleForm vehicleForm,
                                       BindingResult bindingResult, Model model,
                                       RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding vehicleForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + VEHICLE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            return "redirect:/admin/vehicles";
        }
        try {
            //Trying to build a vehicle from our VehicleForm
            if (vehicleService.findByVehicleID(vehicleForm.getVehicleID()).isEmpty()) {
                Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userService.findByAfm(vehicleForm.getAfm()).get(0));
                vehicleService.save(vehicle);
                redirectAttributes.addFlashAttribute(MESSAGE, "Vehicle was created!");
            } else {
                vehicleForm.setVehicleID("");
                redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
                redirectAttributes.addFlashAttribute(MESSAGE, "This plate number already exists!");
            }
        } catch (Exception exception) {
            //if an error occurs show it to the user :(
            vehicleForm.setAfm("");
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            redirectAttributes.addFlashAttribute(MESSAGE, exception.getMessage());
        }
        return "redirect:/admin/vehicles";
    }

    //The processDeleteVehicle method will map "/admin/vehicles/delete/{id}" GET requests and
    //will delete a vehicle and redirect to "/admin/vehicles"
    @RequestMapping(value = "/vehicles/delete/{vehicleID}", method = RequestMethod.POST)
    public String processDeleteVehicle(@PathVariable String vehicleID,
                                       RedirectAttributes redirectAttributes) {
        //Delete the vehicle depending on its vehicleID
        vehicleService.deleteByVehicleID(vehicleID);
        redirectAttributes.addFlashAttribute(MESSAGE, "Vehicle was deleted successfully!");
        return "redirect:/admin/vehicles";
    }

    //The processSearchVehicle() method will map "/vehicles/search" GET requests and
    //will search for a vehicle by either AFM or VehicleID
    @RequestMapping(value = "/vehicles/search", method = RequestMethod.GET)
    public String processSearchVehicle(@Valid @ModelAttribute(SEARCH_FORM) VehicleSearchForm vehicleSearchForm,
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

    //The showEditVehicle() method will map "/vehicles/edit/{vehicleID} GET requests
    //and will try to find a vehicle based on the id and show the editVehicle.ftl
    //so that the Admin can edit the vehicle details
    @RequestMapping(value = "/vehicles/edit/{vehicleID}", method = RequestMethod.GET)
    public String showEditVehicle(@PathVariable String vehicleID,
                                  RedirectAttributes redirectAttributes) {
        //Find the vehicle
        Vehicle vehicle = vehicleService.findByVehicleID(vehicleID).get(0);
        //Create a new vehicleForm based on the vehicle
        VehicleForm vehicleForm = VehicleConverter.buildVehicleFormObject(vehicle);
        redirectAttributes.addFlashAttribute(vehicleForm);
        return "redirect:/admin/vehicles/editVehicle";
    }

    //the showEditUserVehicle will map "/vehicles/editVehicle" GET requests
    //and redirect to /admin/users or show the "editVehicle.ftl"
    @RequestMapping(value = "/vehicles/editVehicle", method = RequestMethod.GET)
    public String showEditVehiceView(Model model) {
        Map<String, Object> map = model.asMap();
        //If we ended up here without a vehicleForm then something went wrong so we redirect to vehicles
        if (!map.containsKey(VEHICLE_FORM)) {
            model.addAttribute(VEHICLE_FORM, new VehicleForm());
            return "redirect:/admin/vehicles";
        }
        return "editVehicle";
    }

    //The processEditVehicle() method will map "/vehicles/editVehicle" POST requests
    //and will try to change the details of a Vehicle
    @RequestMapping(value = "/vehicles/editVehicle", method = RequestMethod.POST)
    public String processEditUser(@Valid @ModelAttribute(VEHICLE_FORM) VehicleForm vehicleForm,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding vehicleForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + VEHICLE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            return "redirect:/admin/vehicles/editVehicle";
        }
        try {
            //Trying to build a Vehicle from our VehicleForm
            Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userService.findOne(vehicleForm.getUserID()));
            vehicleService.save(vehicle);
            redirectAttributes.addFlashAttribute(MESSAGE, "Vehicle was updated!");
            return "redirect:/admin/vehicles";
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute(MESSAGE, exception.getMessage());
            return "redirect:/admin/vehicles/editVehicle";
        }
    }
}
