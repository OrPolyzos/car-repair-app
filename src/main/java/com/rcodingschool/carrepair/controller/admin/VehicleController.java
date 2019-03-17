package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;

//@Controller
//@RequestMapping(ADMIN_URI)
public class VehicleController /*extends BaseController */{

//    private static final String VEHICLES_VIEW = "admin/vehicle/vehicles";
//    private static final String EDIT_VEHICLE_VIEW = "admin/vehicle/edit-vehicle";
//
//    private static final String VEHICLE_FORM_HOLDER = "vehicleForm";
//    private static final String VEHICLE_SEARCH_FORM_HOLDER = "vehicleSearchForm";
//    private static final String VEHICLE_LIST_HOLDER = "vehicleList";
//
//    private static final String VEHICLE_WAS_CREATED_MESSAGE = "Vehicle was created!";
//    private static final String VEHICLE_WAS_DELETED_MESSAGE = "Vehicle was deleted!";
//    private static final String VEHICLE_WAS_UPDATED_MESSAGE = "Vehicle was updated!";
//
//    private final UserResourceService userResourceService;
//    private final VehicleResourceService vehicleService;
//
//    @Autowired
//    public VehicleController(UserResourceService userResourceService, VehicleResourceService vehicleService) {
//        this.userResourceService = userResourceService;
//        this.vehicleService = vehicleService;
//    }
//
//    @GetMapping("/vehicles")
//    public String getVehiclesView(Model model) {
//        fillWithVehicleForms(model);
//        return VEHICLES_VIEW;
//    }
//
//    private void fillWithVehicleForms(Model model) {
//        if (!model.containsAttribute(VEHICLE_FORM_HOLDER)) {
//            model.addAttribute(VEHICLE_FORM_HOLDER, new VehicleForm());
//        }
//        if (!model.containsAttribute(VEHICLE_SEARCH_FORM_HOLDER)) {
//            model.addAttribute(VEHICLE_SEARCH_FORM_HOLDER, new VehicleSearchForm());
//        }
//    }
//
//    @PostMapping("/vehicles/create")
//    public String createVehicle(@Valid @ModelAttribute(VEHICLE_FORM_HOLDER) VehicleForm vehicleForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_FORM_HOLDER, vehicleForm);
//            return redirectTo("/admin/vehicles");
//        }
//
//        try {
//            User vehicleOwner = userResourceService.findOptionalByAfm(vehicleForm.getAfm()).orElseThrow(() -> new UserNotFoundException(vehicleForm.getAfm()));
//            Vehicle vehicleToCreate = VehicleConverter.vehicleFormToVehicle(vehicleForm, vehicleOwner);
//            vehicleService.insert(vehicleToCreate);
//            sendInfoMessage(model, VEHICLE_WAS_CREATED_MESSAGE);
//            fillWithVehicleForms(model);
//            return VEHICLES_VIEW;
//        } catch (ResourceException exception) {
//            redirectAttributes.addFlashAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
//            redirectErrorMessage(redirectAttributes, exception.getMessage());
//            return redirectTo("/admin/vehicles");
//        }
//    }
//
//    @PostMapping("/vehicles/{vehicleID}/delete")
//    public String deleteVehicle(@PathVariable("vehicleID") String vehicleID, Model model, RedirectAttributes redirectAttributes) {
//        try {
//            vehicleService.deleteByVehicleID(vehicleID);
//            sendInfoMessage(model, VEHICLE_WAS_DELETED_MESSAGE);
//            fillWithVehicleForms(model);
//            return VEHICLES_VIEW;
//        } catch (VehicleNotFoundException exception) {
//            redirectErrorMessage(redirectAttributes, exception.getMessage());
//            return redirectTo("/admin/vehicles");
//        }
//    }
//
//
//    @GetMapping("/vehicles/{vehicleID}/edit")
//    public String getEditVehicleView(@PathVariable String vehicleID, Model model, RedirectAttributes redirectAttributes) {
//        if (model.containsAttribute(VEHICLE_FORM_HOLDER)) {
//            return EDIT_VEHICLE_VIEW;
//        }
//        try {
//            Vehicle vehicle = vehicleService.findOne(vehicleID);
//            VehicleForm vehicleForm = VehicleConverter.vehicleToVehicleForm(vehicle);
//            model.addAttribute(VEHICLE_FORM_HOLDER, vehicleForm);
//            return EDIT_VEHICLE_VIEW;
//        } catch (VehicleNotFoundException exception) {
//            redirectErrorMessage(redirectAttributes, exception.getMessage());
//            return redirectTo("/admin/vehicles");
//        }
//    }
//
//    @PostMapping("/vehicles/{id}/edit")
//    public String editVehicle(@PathVariable("id") String id, @Valid @ModelAttribute(VEHICLE_FORM_HOLDER) VehicleForm vehicleForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            sendBindingErrors(redirectAttributes, bindingResult, VEHICLE_FORM_HOLDER, vehicleForm);
//            return redirectTo(String.format("/admin/vehicles/%s/edit", id));
//        }
//
//        try {
//            Vehicle vehicle = VehicleConverter.vehicleFormToVehicle(vehicleForm, userResourceService.findOrThrow(vehicleForm.getUserID()));
//            vehicleService.update(vehicle);
//            sendInfoMessage(model, VEHICLE_WAS_UPDATED_MESSAGE);
//            fillWithVehicleForms(model);
//            return VEHICLES_VIEW;
//        } catch (ResourceException exception) {
//            redirectErrorMessage(redirectAttributes, exception.getMessage());
//            return redirectTo(String.format("/admin/vehicles/%s/edit", id));
//        }
//    }
}
