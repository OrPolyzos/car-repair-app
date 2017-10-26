package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Converters.UserConverter;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Model.UserForm;
import com.rcodingschool.carrepair.Model.UserSearchForm;
import com.rcodingschool.carrepair.Services.UserService;
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
public class UsersController {
    private static final String USER_FORM = "userForm";
    private static final String SEARCH_FORM = "userSearchForm";
    private static final String USER_LIST = "userList";
    private static final String NOT_FOUND = "searchNotFoundMessage";
    private static final String MESSAGE = "errorMessage";


    @Autowired
    private UserService userService;

    //We will use the @InitBinder annotation and the initBinder method to
    //trim all the user's input from spaces
    //For example if the user enters "    John     " it will be trimmed to "John"
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //The showUsersView method which maps the "/admin/users/" GET requests and returns the users.ftl
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsersView(Model model) {
        Map<String, Object> map = model.asMap();
        //If our Model does not contain a userForm, add a new UserForm()
        if (!map.containsKey(USER_FORM)) {
            model.addAttribute(USER_FORM, new UserForm());
        }
        //if our Model does not contain a searchForm, add a new SearchForm()
        if (!map.containsKey(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new UserSearchForm());
        }
        return "users";
    }


    //The processCreateUser() method will map "/admin/users/create" POST requests
    //and eventually it will redirect to "/admin/users"
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String processCreateUser(@Valid @ModelAttribute(USER_FORM) UserForm userForm,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + USER_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
            return "redirect:/admin/users";
        }

        try {
            //Trying to build a user from our UserForm
            User user = UserConverter.buildInsertUserObject(userForm);
            //Save the user
            userService.save(user);
            //Send information to the user
            redirectAttributes.addFlashAttribute(MESSAGE, "User was created!");
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute(MESSAGE, exception.getMessage());
        }
        return "redirect:/admin/users";
    }

    //The processDeleteUser() method will map "/admin/users/delete/{id}" GET requests and
    //will delete a user and redirect to "/admin/users"
    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
    public String processDeleteUser(@PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {
        //Delete the user
        userService.deleteByUserID(id);
        //Send information to the user
        redirectAttributes.addFlashAttribute(MESSAGE, "User was deleted!");
        return "redirect:/admin/users";
    }

    //The processSearchUser() method will map "/users/search" GET requests and
    //will search for a user by either AFM or Email
    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
    public String processSearchUser(@Valid @ModelAttribute(SEARCH_FORM) UserSearchForm userSearchForm,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + SEARCH_FORM, bindingResult);
            //Send information to the user
            redirectAttributes.addFlashAttribute(SEARCH_FORM, userSearchForm);
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
            redirectAttributes.addFlashAttribute(NOT_FOUND, "No records were found!");
        } else {
            //else we send the userList to our users.ftl
            redirectAttributes.addFlashAttribute(USER_LIST, usersList);
        }
        return "redirect:/admin/users";
    }

    //The showEditUser() method will map "/users/edit/{id} GET requests
    //and will try to find a user based on the id and show the editUser.ftl
    //so that the Admin can edit his details
    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String showEditUser(@PathVariable Long id,
                               RedirectAttributes redirectAttributes) {
        //Find the user
        User user = userService.findOne(id);
        //Build a userForm Object based on the user we found
        UserForm userForm = UserConverter.buildUserFormObject(user);
        //Send the userForm to the editUser.ftl
        redirectAttributes.addFlashAttribute(userForm);
        return "redirect:/admin/users/editUser";
    }

    //the showEditUserView will map "/users/editUser" GET requests
    //and redirect to /admin/users or show the "editUser".ftl
    @RequestMapping(value = "/users/editUser", method = RequestMethod.GET)
    public String showEditUserView(Model model) {
        //Get the model
        Map<String, Object> map = model.asMap();
        //If there is not already a UserForm something went wrong so we redirect
        if (!map.containsKey(USER_FORM)) {
            return "redirect:/admin/users";
        }
        //If there is not UserForm
        return "editUser";
    }

    //The processEditUser() method will map "/users/editUser" POST requests
    //and will try to change the details of a User
    @RequestMapping(value = "/users/editUser", method = RequestMethod.POST)
    public String processEditUser(@Valid @ModelAttribute(USER_FORM) UserForm userForm,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + USER_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
            return "redirect:/admin/users/editUser";
        }
        try {
            //Trying to build a user from our UserForm
            //Full means we include userID also
            User user = UserConverter.buildUpdateUserObject(userForm);
            //Save the user
            userService.save(user);
            redirectAttributes.addFlashAttribute(MESSAGE, "User was updated");
            return "redirect:/admin/users";
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/users/editUser";
        }
    }

}
