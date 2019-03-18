package com.rcodingschool.carrepair.controller.admin.resource;

import com.rcodingschool.carrepair.controller.admin.base.ResourceController;
import com.rcodingschool.carrepair.converter.VehicleConverter;
import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.model.VehicleForm;
import com.rcodingschool.carrepair.model.VehicleSearchForm;
import com.rcodingschool.carrepair.service.resource.UserResourceService;
import com.rcodingschool.carrepair.service.resource.VehicleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class VehicleResourceController extends ResourceController<Vehicle, String, VehicleForm, VehicleSearchForm> {

    private static final String RESOURCE_VIEW = "/admin/vehicle/vehicles";
    private static final String EDIT_RESOURCE_VIEW = "/admin/vehicle/edit-vehicle";
    private static final String RESOURCE_BASE_URI = "/admin/vehicles";
    private static final String RESOURCE_FORM_HOLDER = "vehicleForm";
    private static final String RESOURCE_SEARCH_FORM_HOLDER = "vehicleSearchForm";
    private static final String RESOURCE_LIST_HOLDER = "vehicleList";

    private UserResourceService userResourceService;

    @Autowired
    public VehicleResourceController(VehicleResourceService vehicleResourceService, UserResourceService userResourceService) {
        super(Vehicle.class, VehicleForm.class, VehicleSearchForm.class,
                VehicleConverter::vehicleFormToVehicle, VehicleConverter::vehicleToVehicleForm,
                vehicleResourceService);
        this.userResourceService = userResourceService;
    }

    @Override
    @GetMapping(RESOURCE_BASE_URI)
    public String getResourceView(Model model) {
        return super.getResourceView(model);
    }

    @Override
    @PostMapping(RESOURCE_BASE_URI)
    public String createResource(@Valid @ModelAttribute(RESOURCE_FORM_HOLDER) VehicleForm resourceForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        try {
            User owner = userResourceService.findOptionalByAfm(resourceForm.getAfm()).orElseThrow(() -> new UserNotFoundException(resourceForm.getAfm()));
            resourceForm.setUserID(owner.getId());
            return super.createResource(resourceForm, bindingResult, model, redirectAttributes);
        } catch (UserNotFoundException e) {
            sendErrorMessage(model, e.getMessage());
            return getResourceView(model);
        }
    }

    @Override
    @PostMapping(value = "/admin/vehicles/{resourceId}/delete")
    public String deleteResource(@PathVariable("resourceId") String resourceId, Model model, RedirectAttributes redirectAttributes) {
        return super.deleteResource(resourceId, model, redirectAttributes);
    }


    @Override
    @GetMapping(value = "/admin/vehicles/{resourceId}/edit")
    public String getEditResourceView(@PathVariable("resourceId") String resourceId, Model model, RedirectAttributes redirectAttributes) {
        return super.getEditResourceView(resourceId, model, redirectAttributes);
    }

    @Override
    @PostMapping(value = "/admin/vehicles/{resourceId}/edit")
    public String editResource(@PathVariable("resourceId") String resourceId, @Valid @ModelAttribute(RESOURCE_FORM_HOLDER) VehicleForm resourceForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        return super.editResource(resourceId, resourceForm, bindingResult, model, redirectAttributes, httpServletRequest);
    }

    @GetMapping("/admin/users/{userId}/vehicles")
    public String getVehiclesViewForUser(@PathVariable("userId") Long userId, Model model, RedirectAttributes redirectAttributes) {
        VehicleForm vehicleForm = new VehicleForm();
        try {
            vehicleForm.setAfm(userResourceService.findOrThrow(userId).getAfm());
            model.addAttribute(getResourceFormHolder(), vehicleForm);
            return getResourceView(model);
        } catch (ResourceException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(RESOURCE_BASE_URI);
        }
    }

    @Override
    @PostMapping("/admin/vehicles/search")
    public String searchBy(@Valid @ModelAttribute(RESOURCE_SEARCH_FORM_HOLDER) VehicleSearchForm resourceSearchForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        return super.searchBy(resourceSearchForm, bindingResult, model, redirectAttributes);
    }

    @Override
    protected String getResourceBaseUri() {
        return RESOURCE_BASE_URI;
    }

    @Override
    protected String getResourceViewPath() {
        return RESOURCE_VIEW;
    }

    @Override
    protected String getEditResourceViewPath() {
        return EDIT_RESOURCE_VIEW;
    }

    @Override
    protected String getResourceFormHolder() {
        return RESOURCE_FORM_HOLDER;
    }

    @Override
    protected String getResourceSearchFormHolder() {
        return RESOURCE_SEARCH_FORM_HOLDER;
    }

    @Override
    protected String getResourceListHolder() {
        return RESOURCE_LIST_HOLDER;
    }
}
