package com.rcodingschool.carrepair.controller;

import com.rcodingschool.carrepair.converter.VehicleConverter;
import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.model.VehicleForm;
import com.rcodingschool.carrepair.model.VehicleSearchForm;
import com.rcodingschool.carrepair.service.UserService;
import com.rcodingschool.carrepair.service.VehicleService;
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
import java.util.Map;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class VehiclesController extends BaseController {

    private static final String VEHICLES_VIEW = "vehicles";
    private static final String EDIT_VEHICLE_VIEW = "editVehicle";

    private static final String VEHICLE_FORM = "vehicleForm";
    private static final String SEARCH_FORM = "vehicleSearchForm";
    private static final String VEHICLE_LIST = "vehicleList";
    private static final String NOT_FOUND = "searchNotFoundMessage";

    private static final String VEHICLE_WAS_CREATED_MESSAGE = "Vehicle was created!";
    private static final String THIS_PLATE_NUMBER_ALREADY_EXISTS_MESSAGE = "This plate number already exists!";
    private static final String VEHICLE_WAS_DELETED_MESSAGE = "Vehicle was deleted successfully!";
    private static final String NO_RECORDS_WERE_FOUND_MESSAGE = "No records were found!";
    private static final String VEHICLE_WAS_UPDATED_MESSAGE = "Vehicle was updated!";

    private static final String EMPTY_STRING = "";

    private final UserService userService;
    private final VehicleService vehicleService;

    @Autowired
    public VehiclesController(UserService userService, VehicleService vehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    //The showVehiclesView method which maps the "/admin/vehicles/" GET requests and returns the vehiclesOrestes.ftl
    @GetMapping("/vehicles")
    public String showVehiclesView(Model model) {
        //If our Model does not contain a vehicleForm, add a new VehicleForm()
        if (!model.containsAttribute(VEHICLE_FORM)) {
            model.addAttribute(VEHICLE_FORM, new VehicleForm());
        }
        //if our Model does not contain a searchForm, add a new VehicleSearchForm()
        if (!model.containsAttribute(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new VehicleSearchForm());
        }
        return VEHICLES_VIEW;
    }

    //The registerUserForm method which maps the registerUserForm.ftl for GET requests
    @GetMapping("/vehicles/{id}")
    public String showVehiclesViewForSpecificUser(@PathVariable Long id, Model model) {
        List<Vehicle> vehiclesList = vehicleService.findByUserID(id);
        model.addAttribute(VEHICLE_LIST, vehiclesList);
        if (!model.containsAttribute(VEHICLE_FORM)) {
            VehicleForm vehicleForm = new VehicleForm();
            try {
                vehicleForm.setAfm(userService.findOne(id).getAfm());
            } catch (UserNotFoundException unfex) {
                model.addAttribute(MESSAGE_HOLDER, unfex.getMessage());
            }
            model.addAttribute(VEHICLE_FORM, vehicleForm);
        }
        if (!model.containsAttribute(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new VehicleSearchForm());
        }
        return VEHICLES_VIEW;
    }

    //The processCreateVehicle() method will map "/admin/vehicle/create" POST requests
    //and eventually it will redirect to "/admin/vehicles"
    @PostMapping("/vehicles/create")
    public String processCreateVehicle(@Valid @ModelAttribute(VEHICLE_FORM) VehicleForm vehicleForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding vehicleForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + VEHICLE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            return redirectTo("/admin/vehicles");
        }
        try {
            //Trying to build a vehicle from our VehicleForm
            if (vehicleService.findByVehicleID(vehicleForm.getVehicleID()).isEmpty()) {
                Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userService.findByAfm(vehicleForm.getAfm()).get(0));
                vehicleService.save(vehicle);
                redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, VEHICLE_WAS_CREATED_MESSAGE);
            } else {
                vehicleForm.setVehicleID(EMPTY_STRING);
                redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
                redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, THIS_PLATE_NUMBER_ALREADY_EXISTS_MESSAGE);
            }
        } catch (Exception exception) {
            //if an error occurs show it to the user :(
            vehicleForm.setAfm(EMPTY_STRING);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, exception.getMessage());
        }
        return redirectTo("/admin/vehicles");
    }

    //The processDeleteVehicle method will map "/admin/vehicles/delete/{id}" GET requests and
    //will delete a vehicle and redirect to "/admin/vehicles"
    @PostMapping("/vehicles/delete/{vehicleID}")
    public String processDeleteVehicle(@PathVariable String vehicleID,
                                       RedirectAttributes redirectAttributes) {
        //Delete the vehicle depending on its vehicleID
        vehicleService.deleteByVehicleID(vehicleID);
        redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, VEHICLE_WAS_DELETED_MESSAGE);
        return redirectTo("/admin/vehicles");
    }

    //The processSearchVehicle() method will map "/vehicles/search" GET requests and
    //will search for a vehicle by either AFM or VehicleID
    @GetMapping("/vehicles/search")
    public String processSearchVehicle(@Valid @ModelAttribute(SEARCH_FORM) VehicleSearchForm vehicleSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + SEARCH_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(SEARCH_FORM, vehicleSearchForm);
            return redirectTo("/admin/vehicles");
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
            redirectAttributes.addFlashAttribute(NOT_FOUND, NO_RECORDS_WERE_FOUND_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(VEHICLE_LIST, vehiclesList);
        }
        return redirectTo("/admin/vehicles");
    }

    //The showEditVehicle() method will map "/vehicles/edit/{vehicleID} GET requests
    //and will try to find a vehicle based on the id and show the editVehicle.ftl
    //so that the Admin can edit the vehicle details
    @GetMapping("/vehicles/edit/{vehicleID}")
    public String showEditVehicle(@PathVariable String vehicleID,
                                  RedirectAttributes redirectAttributes) {
        //Find the vehicle
        Vehicle vehicle = vehicleService.findByVehicleID(vehicleID).get(0);
        //Create a new vehicleForm based on the vehicle
        VehicleForm vehicleForm = VehicleConverter.buildVehicleFormObject(vehicle);
        redirectAttributes.addFlashAttribute(vehicleForm);
        return redirectTo("/admin/vehicles/editVehicle");
    }

    //the showEditUserVehicle will map "/vehicles/editVehicle" GET requests
    //and redirect to /admin/users or show the "editVehicle.ftl"
    @GetMapping("/vehicles/editVehicle")
    public String showEditVehiceView(Model model) {
        Map<String, Object> map = model.asMap();
        //If we ended up here without a vehicleForm then something went wrong so we redirect to vehicles
        if (!map.containsKey(VEHICLE_FORM)) {
            model.addAttribute(VEHICLE_FORM, new VehicleForm());
            return redirectTo("/admin/vehicles");
        }
        return EDIT_VEHICLE_VIEW;
    }

    //The processEditVehicle() method will map "/vehicles/editVehicle" POST requests
    //and will try to change the details of a Vehicle
    @PostMapping("/vehicles/editVehicle")
    public String processEditUser(@Valid @ModelAttribute(VEHICLE_FORM) VehicleForm vehicleForm,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding vehicleForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + VEHICLE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM, vehicleForm);
            return redirectTo("/admin/vehicles/editVehicle");
        }
        try {
            //Trying to build a Vehicle from our VehicleForm
            Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userService.findOne(vehicleForm.getUserID()));
            vehicleService.save(vehicle);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, VEHICLE_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/vehicles");
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, exception.getMessage());
            return redirectTo("/admin/vehicles/editVehicle");
        }
    }
}
