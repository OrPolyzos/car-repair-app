package com.rcodingschool.carrepair.controller.admin;

import com.rcodingschool.carrepair.controller.base.BaseController;
import com.rcodingschool.carrepair.converter.UserConverter;
import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.model.UserForm;
import com.rcodingschool.carrepair.model.UserSearchForm;
import com.rcodingschool.carrepair.service.UserResourceService;
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
public class UserController extends BaseController {

    private static final String USERS_VIEW = "admin/user/users";
    private static final String EDIT_USER_VIEW = "admin/user/edit-user";

    private static final String USER_FORM_HOLDER = "userForm";
    private static final String USER_LIST_HOLDER = "userList";
    private static final String USER_SEARCH_FORM_HOLDER = "userSearchForm";

    private static final String USER_WAS_CREATED_MESSAGE = "User was created!";
    private static final String USER_WAS_UPDATED_MESSAGE = "User was updated!";
    private static final String USER_WAS_DELETED_MESSAGE = "User was deleted!";

    private final UserResourceService userService;

    @Autowired
    public UserController(UserResourceService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getUsersView(Model model) {
        fillWithUserForms(model);
        return USERS_VIEW;
    }

    private void fillWithUserForms(Model model) {
        if (!model.containsAttribute(USER_FORM_HOLDER)) {
            model.addAttribute(USER_FORM_HOLDER, new UserForm());
        }
        if (!model.containsAttribute(USER_SEARCH_FORM_HOLDER)) {
            model.addAttribute(USER_SEARCH_FORM_HOLDER, new UserSearchForm());
        }
    }


    @PostMapping(value = "/users/create")
    public String createUser(@Valid @ModelAttribute(USER_FORM_HOLDER) UserForm userForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, USER_FORM_HOLDER, userForm);
            return redirectTo("/admin/users");
        }

        try {
            User userToSave = UserConverter.buildInsertUserObject(userForm);
            userService.insert(userToSave);
            sendInfoMessage(model, USER_WAS_CREATED_MESSAGE);
            fillWithUserForms(model);
            return USERS_VIEW;
        } catch (ResourceException exception) {
            redirectAttributes.addFlashAttribute(USER_FORM_HOLDER, userForm);
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/users");
        }
    }

    @PostMapping(value = "/users/search")
    public String searchUser(@Valid @ModelAttribute(USER_SEARCH_FORM_HOLDER) UserSearchForm userSearchForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, USER_SEARCH_FORM_HOLDER, userSearchForm);
            redirectTo("/admin/users");
        }
        fillWithUserForms(model);
        model.addAttribute(USER_LIST_HOLDER, userService.searchUsersBy(userSearchForm));
        return USERS_VIEW;
    }

    @PostMapping(value = "/users/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long userId, Model model, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteById(userId);
            sendInfoMessage(model, USER_WAS_DELETED_MESSAGE);
            fillWithUserForms(model);
            return USERS_VIEW;
        } catch (ResourceException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/users");
        }

    }

    @GetMapping(value = "/users/{userId}/edit")
    public String getEditUserView(@PathVariable("userId") Long userId, Model model, RedirectAttributes redirectAttributes) {
        if (model.containsAttribute(USER_FORM_HOLDER)) {
            return EDIT_USER_VIEW;
        }
        try {
            User user = userService.findOrThrow(userId);
            UserForm userForm = UserConverter.buildUserFormObject(user);
            model.addAttribute(USER_FORM_HOLDER, userForm);
            return EDIT_USER_VIEW;
        } catch (ResourceException exception) {
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo("/admin/users");
        }
    }

    @PostMapping(value = "/users/{userId}/edit")
    public String editUser(@PathVariable("userId") Long userId, @Valid @ModelAttribute(USER_FORM_HOLDER) UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            sendBindingErrors(redirectAttributes, bindingResult, USER_FORM_HOLDER, userForm);
            return redirectTo(String.format("/admin/users/%s/edit", userId));
        }
        try {
            User user = UserConverter.buildUpdateUserObject(userForm);
            userService.update(user);
            redirectInfoMessage(redirectAttributes, USER_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/users");
        } catch (ResourceException exception) {
            redirectAttributes.addFlashAttribute(USER_FORM_HOLDER, userForm);
            redirectErrorMessage(redirectAttributes, exception.getMessage());
            return redirectTo(String.format("/admin/users/%s/edit", userId));
        }
    }

}
