package com.rcodingschool.carrepair.controller.admin.resource;

import com.rcodingschool.carrepair.converter.PartConverter;
import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.model.PartForm;
import com.rcodingschool.carrepair.model.PartSearchForm;
import com.rcodingschool.carrepair.service.resource.PartResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.web.initializr.base.controller.ResourceController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class PartResourceController extends ResourceController<Part, Long, PartForm, PartSearchForm> {

    private static final String RESOURCE_VIEW = "/admin/part/parts";
    private static final String EDIT_RESOURCE_VIEW = "/admin/part/edit-part";
    private static final String RESOURCE_BASE_URI = "/admin/parts";
    private static final String RESOURCE_FORM_HOLDER = "partForm";
    private static final String RESOURCE_SEARCH_FORM_HOLDER = "partSearchForm";
    private static final String RESOURCE_LIST_HOLDER = "partList";

    @Autowired
    public PartResourceController(PartResourceService partResourceService) {
        super(Part.class, PartForm.class, PartSearchForm.class,
                PartConverter::partFormToPart, PartConverter::partToPartForm,
                partResourceService);
    }

    @Override
    @GetMapping(RESOURCE_BASE_URI)
    public String getResourceView(Model model) {
        return super.getResourceView(model);
    }

    @Override
    @PostMapping(RESOURCE_BASE_URI)
    public String createResource(@Valid @ModelAttribute(RESOURCE_FORM_HOLDER) PartForm resourceForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        return super.createResource(resourceForm, bindingResult, model, redirectAttributes);
    }

    @Override
    @PostMapping(value = "/admin/parts/{resourceId}/delete")
    public String deleteResource(@PathVariable("resourceId") Long resourceId, Model model, RedirectAttributes redirectAttributes) {
        return super.deleteResource(resourceId, model, redirectAttributes);
    }


    @Override
    @GetMapping(value = "/admin/parts/{resourceId}/edit")
    public String getEditResourceView(@PathVariable("resourceId") Long resourceId, Model model, RedirectAttributes redirectAttributes) {
        return super.getEditResourceView(resourceId, model, redirectAttributes);
    }

    @Override
    @PostMapping(value = "/admin/parts/{resourceId}/edit")
    public String editResource(@PathVariable("resourceId") Long resourceId, @Valid @ModelAttribute(RESOURCE_FORM_HOLDER) PartForm resourceForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        return super.editResource(resourceId, resourceForm, bindingResult, model, redirectAttributes, httpServletRequest);
    }

    @Override
    @PostMapping(value = "/admin/parts/search")
    public String searchBy(@Valid @ModelAttribute(RESOURCE_SEARCH_FORM_HOLDER) PartSearchForm resourceSearchForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
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
