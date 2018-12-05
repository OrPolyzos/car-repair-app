package com.rcodingschool.carrepair.controller;

import com.rcodingschool.carrepair.converter.UserConverter;
import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.exception.user.DuplicateUserException;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.model.UserForm;
import com.rcodingschool.carrepair.model.UserSearchForm;
import com.rcodingschool.carrepair.service.UserService;
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
public class UsersController extends BaseController {

    private static final String USERS_VIEW = "users";
    private static final String EDIT_USER_VIEW = "editUser";

    private static final String USER_FORM_HOLDER = "userForm";
    private static final String USER_LIST_HOLDER = "userList";
    private static final String SEARCH_FORM_HOLDER = "userSearchForm";

    private static final String NOT_FOUND_MESSAGE_HOLDER = "searchNotFoundMessage";

    private static final String USER_WAS_CREATED_MESSAGE = "User was created!";
    private static final String USER_WAS_UPDATED_MESSAGE = "User was updated";
    private static final String USER_WAS_DELETED_MESSAGE = "User was deleted!";
    private static final String NO_RECORDS_WERE_FOUND_MESSAGE = "No records were found!";

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //The showUsersView method which maps the "/admin/users/" GET requests and returns the users.ftl
    @GetMapping(value = "/users")
    public String showUsersView(Model model) {
        //If our Model does not contain a userForm, add a new UserForm()
        if (!model.containsAttribute(USER_FORM_HOLDER)) {
            model.addAttribute(USER_FORM_HOLDER, new UserForm());
        }
        //if our Model does not contain a searchForm, add a new SearchForm()
        if (!model.containsAttribute(SEARCH_FORM_HOLDER)) {
            model.addAttribute(SEARCH_FORM_HOLDER, new UserSearchForm());
        }
        return USERS_VIEW;
    }


    //The processCreateUser() method will map "/admin/users/create" POST requests
    //and eventually it will redirect to "/admin/users"
    @PostMapping(value = "/users/create")
    public String processCreateUser(@Valid @ModelAttribute(USER_FORM_HOLDER) UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + USER_FORM_HOLDER, bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM_HOLDER, userForm);
            return redirectTo("/admin/users");
        }

        try {
            //Trying to build a user from our UserForm
            User user = UserConverter.buildInsertUserObject(userForm);
            //Save the user
            userService.save(user);
            //Send information to the user
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, USER_WAS_CREATED_MESSAGE);
        } catch (DuplicateUserException exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute(USER_FORM_HOLDER, userForm);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, exception.getMessage());
        }
        return redirectTo("/admin/users");
    }

    //The processDeleteUser() method will map "/admin/users/delete/{id}" GET requests and
    //will delete a user and redirect to "/admin/users"
    @PostMapping(value = "/users/delete/{id}")
    public String processDeleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        //Delete the user
        userService.deleteByUserID(id);
        //Send information to the user
        redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, USER_WAS_DELETED_MESSAGE);
        return redirectTo("/admin/users");
    }

    //The processSearchUser() method will map "/users/search" GET requests and
    //will search for a user by either AFM or Email
    @GetMapping(value = "/users/search")
    public String processSearchUser(@Valid @ModelAttribute(SEARCH_FORM_HOLDER) UserSearchForm userSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + SEARCH_FORM_HOLDER, bindingResult);
            //Send information to the user
            redirectAttributes.addFlashAttribute(SEARCH_FORM_HOLDER, userSearchForm);
        }
        //Initialize a new list of Users to hold the results of the search
        List<User> usersList;
        //Getting the searchForm values and checking
        //If both are null
        if (userSearchForm.getAfm() == null && userSearchForm.getEmail() == null) {
            //Then we retrieve all the users
            usersList = userService.findAll();
            //If the AFM is not null
        } else if (userSearchForm.getAfm() != null) {
            //We search for Users based on AFM
            usersList = userService.findByAfm(userSearchForm.getAfm());
            //Else if AFM is null, means Email is not
        } else {
            //We search for Users based on Email
            usersList = userService.findByEmail(userSearchForm.getEmail());
        }
        //If the List is Empty
        if (usersList.isEmpty()) {
            //We send Information to the user
            redirectAttributes.addFlashAttribute(NOT_FOUND_MESSAGE_HOLDER, NO_RECORDS_WERE_FOUND_MESSAGE);
        } else {
            //else we send the userList to our users.ftl
            redirectAttributes.addFlashAttribute(USER_LIST_HOLDER, usersList);
        }
        return redirectTo("/admin/users");
    }

    //The showEditUser() method will map "/users/edit/{id} GET requests
    //and will try to find a user based on the id and show the editUser.ftl
    //so that the Admin can edit his details
    @GetMapping(value = "/users/edit/{id}")
    public String showEditUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        //Find the user
        try {
            User user = userService.findOne(id);
            //Build a userForm Object based on the user we found
            UserForm userForm = UserConverter.buildUserFormObject(user);
            //Send the userForm to the editUser.ftl
            redirectAttributes.addFlashAttribute(userForm);
        } catch (UserNotFoundException exception) {
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, exception.getMessage());
            return redirectTo("/admin/users");
        }
        return redirectTo("/admin/users/editUser");
    }

    //the showEditUserView will map "/users/editUser" GET requests
    //and redirect to /admin/users or show the "editUser".ftl
    @GetMapping(value = "/users/editUser")
    public String showEditUserView(Model model) {
        //Get the model
        Map<String, Object> map = model.asMap();
        //If there is not already a UserForm something went wrong so we redirect
        if (!map.containsKey(USER_FORM_HOLDER)) {
            return redirectTo("/admin/users");
        }
        //If there is not UserForm
        return EDIT_USER_VIEW;
    }

    //The processEditUser() method will map "/users/editUser" POST requests
    //and will try to change the details of a User
    @PostMapping(value = "/users/editUser")
    public String processEditUser(@Valid @ModelAttribute(USER_FORM_HOLDER) UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PREFIX + USER_FORM_HOLDER, bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM_HOLDER, userForm);
            return redirectTo("/admin/users/editUser");
        }
        try {
            //Trying to build a user from our UserForm
            //Full means we include userID also
            User user = UserConverter.buildUpdateUserObject(userForm);
            //Save the user
            userService.save(user);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, USER_WAS_UPDATED_MESSAGE);
            return redirectTo("/admin/users");
        } catch (DuplicateUserException duex) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute(USER_FORM_HOLDER, userForm);
            redirectAttributes.addFlashAttribute(MESSAGE_HOLDER, duex.getMessage());
            return redirectTo("/admin/users/editUser");
        }
    }

}
