package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Converters.UserConverter;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Model.UserForm;
import com.rcodingschool.carrepair.Model.UserSearchForm;
import com.rcodingschool.carrepair.SloppyRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UsersController {
    private static final String USER_FORM = "userForm";
    private static final String SEARCH_FORM = "userSearchForm";
    private static final String USER_LIST = "userList";

    //We will use the @InitBinder annotation and the initBinder method to
    //trim all the user's input from spaces
    //For example if the user enters "    John     " it will be trimmed to "John"
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //The registerUserForm method which maps the registerUserForm.ftl for GET requests
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsersView(Model model) {
        Map<String, Object> map = model.asMap();
        if (!map.containsKey(USER_FORM)) {
            model.addAttribute(USER_FORM, new UserForm());
        }
        if (!map.containsKey(SEARCH_FORM)){
            model.addAttribute(SEARCH_FORM, new UserSearchForm());
        }
        return "users";
    }


    //The registerUserForm method which maps the registerUserForm.ftl for POST request
    //This means when the user has submitted data in the input fields
    //We will bind each one of the user's inputs in the registerUserForm.ftl
    // to the corresponding fields of the userForm object
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String processCreateUser(@Valid @ModelAttribute(USER_FORM) UserForm userForm,
                                   BindingResult bindingResult, Model model,
                                   RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            String err = "Some fields were incorrect!";
            redirectAttributes.addFlashAttribute("errorMessage", err);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult."+USER_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
            return "redirect:/admin/users";
        }
        try {
            //Trying to build a user from our UserForm
            User user = UserConverter.buildUserObject(userForm);
            return "redirect:/admin/users";
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/users";
        }
    }

    @RequestMapping(value = "/users/delete/{afm}", method = RequestMethod.POST)
    public String processDeleteUser(@PathVariable String afm,
                      RedirectAttributes redirectAttributes) {
        System.err.println(afm);
        //Delete the user
        //Simple logic just for debugging
        //redirectAttributes.addFlashAttribute("userList", myList);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String processSearchUser(@Valid @ModelAttribute(SEARCH_FORM) UserSearchForm userSearchForm,
                             BindingResult bindingResult, Model model,
                             RedirectAttributes redirectAttributes){

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult."+SEARCH_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(SEARCH_FORM, userSearchForm);
            return "redirect:/admin/users";
        }
        List<User> tempList = SloppyRepository.getAllUsers();


        if (userSearchForm.getAfm() == null && userSearchForm.getEmail() == null){
            redirectAttributes.addFlashAttribute(USER_LIST, tempList);
        }
        else{
            boolean found = false;
            List<User> finalList = new ArrayList<>();
            for (User user : tempList){
                if (user.getAfm().equals(userSearchForm.getAfm())){
                    finalList.add(user);
                }
            }
            if (!finalList.isEmpty()){
                redirectAttributes.addFlashAttribute(USER_LIST, finalList);
            }
        }
        return "redirect:/admin/users";
    }
}
