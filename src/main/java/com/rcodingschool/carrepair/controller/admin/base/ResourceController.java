package com.rcodingschool.carrepair.controller.admin.base;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import com.rcodingschool.carrepair.service.base.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.function.Function;

public abstract class ResourceController<T, ID extends Serializable, RF, RSF> extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class.getName());

    protected final Class<T> resourceClass;
    protected final Class<RF> resourceFormClass;
    protected final Class<RSF> resourceSearchFormClass;

    protected Function<RF, T> resourceConverter;
    protected Function<T, RF> reverseResourceConverter;

    protected ResourceService<T, ID> resourceService;

    public ResourceController(Class<T> resourceClass, Class<RF> resourceFormClass, Class<RSF> resourceSearchFormClass,
                              Function<RF, T> resourceConverter, Function<T, RF> reverseResourceConverter,
                              ResourceService<T, ID> resourceService) {
        this.resourceClass = resourceClass;
        this.resourceFormClass = resourceFormClass;
        this.resourceSearchFormClass = resourceSearchFormClass;
        this.resourceConverter = resourceConverter;
        this.reverseResourceConverter = reverseResourceConverter;
        this.resourceService = resourceService;
    }

    public String getResourceView(Model model) {
        if (!model.containsAttribute(getResourceFormHolder())) {
            model.addAttribute(getResourceFormHolder(), createInstanceOf(resourceFormClass));
        }
        if (!model.containsAttribute(getResourceSearchFormHolder())) {
            model.addAttribute(getResourceSearchFormHolder(), createInstanceOf(resourceSearchFormClass));
        }
        return getResourceViewPath();
    }

    public String createResource(RF resourceForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, getResourceFormHolder(), resourceForm);
            return redirectTo(getResourceBaseUri());
        }

        try {
            T resourceToSave = resourceConverter.apply(resourceForm);
            resourceService.insert(resourceToSave);
            sendInfoMessage(model, getResourceCreatedMessage());
            model.asMap().remove(getResourceFormHolder());
            return getResourceView(model);
        } catch (ResourceException exception) {
            redirectAttributes.addFlashAttribute(getResourceFormHolder(), resourceForm);
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(getResourceBaseUri());
        }
    }

    public String deleteResource(ID resourceId, Model model, RedirectAttributes redirectAttributes) {
        try {
            resourceService.deleteById(resourceId);
            sendInfoMessage(model, getResourceDeletedMessage());
            return getResourceView(model);
        } catch (ResourceNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(getResourceBaseUri());
        }
    }

    public String getEditResourceView(ID resourceId, Model model, RedirectAttributes redirectAttributes) {
        if (model.containsAttribute(getResourceFormHolder())) {
            return getEditResourceViewPath();
        }
        try {
            T resource = resourceService.findOrThrow(resourceId);
            RF resourceForm = reverseResourceConverter.apply(resource);
            model.addAttribute(getResourceFormHolder(), resourceForm);
            return getEditResourceViewPath();
        } catch (ResourceNotFoundException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(getResourceBaseUri());
        }
    }

    public String editResource(ID resourceId, RF resourceForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, getResourceFormHolder(), resourceForm);
            return redirectTo(httpServletRequest.getRequestURI());
        }
        try {
            T user = resourceConverter.apply(resourceForm);
            resourceService.update(user);
            sendInfoMessage(model, getResourceUpdatedMessage());
            return getResourceView(model);
        } catch (ResourceException exception) {
            redirectAttributes.addFlashAttribute(getResourceFormHolder(), resourceForm);
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(httpServletRequest.getRequestURI());
        }
    }

    private <E> E createInstanceOf(Class<E> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Failed to instantiate: {} | Message: {}", clazz.getName(), e.getMessage());
            return null;
        }
    }

    protected String getResourceCreatedMessage() {
        return "Resource was created";
    }

    protected String getResourceUpdatedMessage() {
        return "Resource was updated";
    }

    protected String getResourceDeletedMessage() {
        return "Resource was deleted";
    }

    protected abstract String getResourceBaseUri();

    protected abstract String getResourceViewPath();

    protected abstract String getEditResourceViewPath();

    protected abstract String getResourceFormHolder();

    protected abstract String getResourceSearchFormHolder();

    protected abstract String getResourceListHolder();

}
