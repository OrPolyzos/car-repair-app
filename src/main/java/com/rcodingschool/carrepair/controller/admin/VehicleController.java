package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.converter.VehicleConverter;
import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.exception.vehicle.DuplicateVehicleException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
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

import static com.rcodingschool.carrepair.security.SecurityConfig.ADMIN_URI;

@Controller
@RequestMapping(ADMIN_URI)
public class VehicleController extends BaseController {

    private static final String VEHICLES_VIEW = "admin/vehicle/vehicles";
    private static final String EDIT_VEHICLE_VIEW = "admin/vehicle/edit-vehicle";

    private static final String VEHICLE_FORM_HOLDER = "vehicleForm";
    private static final String VEHICLE_SEARCH_FORM_HOLDER = "vehicleSearchForm";
    private static final String VEHICLE_LIST_HOLDER = "vehicleList";

    private static final String VEHICLE_WAS_CREATED_MESSAGE = "Vehicle was created!";
    private static final String VEHICLE_WAS_DELETED_MESSAGE = "Vehicle was deleted!";
    private static final String VEHICLE_WAS_UPDATED_MESSAGE = "Vehicle was updated!";

    private final UserService userService;
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(UserService userService, VehicleService vehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public String getVehiclesView(Model model) {
        fillWithVehicleForms(model);
        return VEHICLES_VIEW;
    }

    private void fillWithVehicleForms(Model model) {
        if (!model.containsAttribute(VEHICLE_FORM_HOLDER)) {
            model.addAttribute(VEHICLE_FORM_HOLDER, new VehicleForm());
        }
        if (!model.containsAttribute(VEHICLE_SEARCH_FORM_HOLDER)) {
            model.addAttribute(VEHICLE_SEARCH_FORM_HOLDER, new VehicleSearchForm());
        }
    }

    @GetMapping("/users/{userId}/vehicles")
    public String getVehiclesViewForUser(@PathVariable("userId") Long userId, Model model, RedirectAttributes redirectAttributes) {
        VehicleForm vehicleForm = new VehicleForm();
        try {
            vehicleForm.setAfm(userService.findOne(userId).getAfm());
            model.addAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
            fillWithVehicleForms(model);
            return VEHICLES_VIEW;
        } catch (UserNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/users");
        }
    }

    @PostMapping("/vehicles/create")
    public String createVehicle(@Valid @ModelAttribute(VEHICLE_FORM_HOLDER) VehicleForm vehicleForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_FORM_HOLDER, vehicleForm);
            return redirectTo("/admin/vehicles");
        }

        try {
            User vehicleOwner = userService.findOneByAfm(vehicleForm.getAfm());
            Vehicle vehicleToCreate = VehicleConverter.buildVehicleObject(vehicleForm, vehicleOwner);
            vehicleService.insert(vehicleToCreate);
            sendInfoMessage(model, VEHICLE_WAS_CREATED_MESSAGE);
            fillWithVehicleForms(model);
            return VEHICLES_VIEW;
        } catch (UserNotFoundException | DuplicateVehicleException exception) {
            redirectAttributes.addFlashAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/vehicles");
        }
    }

    @PostMapping("/vehicles/{vehicleID}/delete")
    public String deleteVehicle(@PathVariable("vehicleID") String vehicleID, Model model, RedirectAttributes redirectAttributes) {
        try {
            vehicleService.deleteByVehicleID(vehicleID);
            sendInfoMessage(model, VEHICLE_WAS_DELETED_MESSAGE);
            fillWithVehicleForms(model);
            return VEHICLES_VIEW;
        } catch (VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/vehicles");
        }
    }

    @PostMapping("/vehicles/search")
    public String searchVehicle(@Valid @ModelAttribute(VEHICLE_SEARCH_FORM_HOLDER) VehicleSearchForm vehicleSearchForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_SEARCH_FORM_HOLDER, vehicleSearchForm);
            return redirectTo("/admin/vehicles");
        }

        model.addAttribute(VEHICLE_LIST_HOLDER, vehicleService.searchForVehicles(vehicleSearchForm));
        fillWithVehicleForms(model);
        return VEHICLES_VIEW;
    }

    @GetMapping("/vehicles/{vehicleID}/edit")
    public String getEditVehicleView(@PathVariable String vehicleID, Model model, RedirectAttributes redirectAttributes) {
        if (model.containsAttribute(VEHICLE_FORM_HOLDER)) {
            return EDIT_VEHICLE_VIEW;
        }
        try {
            Vehicle vehicle = vehicleService.findOne(vehicleID);
            VehicleForm vehicleForm = VehicleConverter.buildVehicleFormObject(vehicle);
            model.addAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
            return EDIT_VEHICLE_VIEW;
        } catch (VehicleNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/vehicles");
        }
    }

    @PostMapping("/vehicles/{id}/edit")
    public String editVehicle(@PathVariable("id") String id, @Valid @ModelAttribute(VEHICLE_FORM_HOLDER) VehicleForm vehicleForm,BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_FORM_HOLDER, vehicleForm);
            return redirectTo(String.format("/admin/vehicles/%s/edit", id));
        }

        try {
            Vehicle vehicle = VehicleConverter.buildVehicleObject(vehicleForm, userService.findOne(vehicleForm.getUserID()));
            vehicleService.update(vehicle);
            sendInfoMessage(model, VEHICLE_WAS_UPDATED_MESSAGE);
            fillWithVehicleForms(model);
            return VEHICLES_VIEW;
        } catch (UserNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(String.format("/admin/vehicles/%s/edit", id));
        }
    }
}
