package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class VehicleController extends BaseController {

    private static final String VEHICLES_VIEW = "vehicles";
    private static final String EDIT_VEHICLE_VIEW = "editVehicle";

    private static final String VEHICLE_FORM_HOLDER = "vehicleForm";
    private static final String VEHICLE_SEARCH_FORM_HOLDER = "vehicleSearchForm";
    private static final String VEHICLE_LIST_HOLDER = "vehicleList";
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
    public VehicleController(UserService userService, VehicleService vehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public String showVehiclesView(Model model) {
        if (!model.containsAttribute(VEHICLE_FORM_HOLDER)) {
            model.addAttribute(VEHICLE_FORM_HOLDER, new VehicleForm());
        }
        if (!model.containsAttribute(VEHICLE_SEARCH_FORM_HOLDER)) {
            model.addAttribute(VEHICLE_SEARCH_FORM_HOLDER, new VehicleSearchForm());
        }
        return VEHICLES_VIEW;
    }

    @GetMapping("/users/{userId}vehicles/")
    public String showVehiclesViewForSpecificUser(@PathVariable("userId") Long userId, Model model) {
        if (!model.containsAttribute(VEHICLE_FORM_HOLDER)) {
            VehicleForm vehicleForm = new VehicleForm();
            try {
                vehicleForm.setAfm(userService.findOne(userId).getAfm());
            } catch (UserNotFoundException unfex) {
                sendInfoMessage(model, unfex.getMessage());
            }
            model.addAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
        }

        if (!model.containsAttribute(VEHICLE_SEARCH_FORM_HOLDER)) {
            model.addAttribute(VEHICLE_SEARCH_FORM_HOLDER, new VehicleSearchForm());
        }
        return VEHICLES_VIEW;
    }

    @PostMapping("/vehicles/create")
    public String processCreateVehicle(@Valid @ModelAttribute(VEHICLE_FORM_HOLDER) VehicleForm
                                               vehicleForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_FORM_HOLDER, vehicleForm);
            return redirectTo("/admin/vehicles");
        }
        try {
            if (vehicleService.findByVehicleID(vehicleForm.getVehicleID()).isEmpty()) {
                Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userService.findByAfm(vehicleForm.getAfm()).get(0));
                vehicleService.save(vehicle);
                redirectErrorMessage(redirectAttributes, VEHICLE_WAS_CREATED_MESSAGE);
            } else {
                vehicleForm.setVehicleID(EMPTY_STRING);
                redirectAttributes.addFlashAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
                redirectErrorMessage(redirectAttributes, THIS_PLATE_NUMBER_ALREADY_EXISTS_MESSAGE);
            }
        } catch (Exception exception) {
            vehicleForm.setAfm(EMPTY_STRING);
            redirectAttributes.addFlashAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
            redirectErrorMessage(redirectAttributes, exception.getMessage());
        }
        return redirectTo("/admin/vehicles");
    }

    @PostMapping("/vehicles/delete/{vehicleID}")
    public String processDeleteVehicle(@PathVariable String vehicleID, RedirectAttributes redirectAttributes) {
        vehicleService.deleteByVehicleID(vehicleID);
        redirectErrorMessage(redirectAttributes, VEHICLE_WAS_DELETED_MESSAGE);
        return redirectTo("/admin/vehicles");
    }

    @GetMapping("/vehicles/search")
    public String processSearchVehicle(@Valid @ModelAttribute(VEHICLE_SEARCH_FORM_HOLDER) VehicleSearchForm
                                               vehicleSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_SEARCH_FORM_HOLDER, vehicleSearchForm);
            return redirectTo("/admin/vehicles");
        }

        List<Vehicle> vehiclesList;
        if (vehicleSearchForm.getAfm() == null && vehicleSearchForm.getVehicleID() == null) {
            vehiclesList = vehicleService.findAll();
        } else if (vehicleSearchForm.getVehicleID() != null) {
            vehiclesList = vehicleService.findByVehicleID(vehicleSearchForm.getVehicleID());
        } else {
            vehiclesList = userService.findByAfm(vehicleSearchForm.getAfm()).get(0).getVehicles();
        }
        if (vehiclesList.isEmpty()) {
            redirectAttributes.addFlashAttribute(NOT_FOUND, NO_RECORDS_WERE_FOUND_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(VEHICLE_LIST_HOLDER, vehiclesList);
        }
        return redirectTo("/admin/vehicles");
    }

    @GetMapping("/vehicles/edit/{vehicleID}")
    public String showEditVehicle(@PathVariable String vehicleID, RedirectAttributes redirectAttributes) {
        Vehicle vehicle = vehicleService.findByVehicleID(vehicleID).get(0);
        VehicleForm vehicleForm = VehicleConverter.buildVehicleFormObject(vehicle);
        redirectAttributes.addFlashAttribute(vehicleForm);
        return redirectTo("/admin/vehicles/editVehicle");
    }

    @GetMapping("/vehicles/editVehicle")
    public String showEditVehicleView(Model model) {
        if (!model.containsAttribute(VEHICLE_FORM_HOLDER)) {
            return redirectTo("/admin/vehicles");
        }
        return EDIT_VEHICLE_VIEW;
    }

    @PostMapping("/vehicles/editVehicle")
    public String processEditVehicle(@Valid @ModelAttribute(VEHICLE_FORM_HOLDER) VehicleForm
                                             vehicleForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_FORM_HOLDER, vehicleForm);
            return redirectTo("/admin/vehicles/editVehicle");
        }

        try {
            Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userService.findOne(vehicleForm.getUserID()));
            vehicleService.save(vehicle);
            redirectErrorMessage(redirectAttributes, VEHICLE_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/vehicles");
        } catch (Exception exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/vehicles/editVehicle");
        }
    }
}
