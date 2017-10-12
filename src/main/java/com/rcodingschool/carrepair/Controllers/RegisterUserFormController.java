package com.rcodingschool.carrepair.Controllers;

import com.rcodingschool.carrepair.Converters.UserConverter;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Model.UserForm;
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
public class RegisterUserFormController {
    private static final String USER_FORM = "userForm";
    private static List<User> myList = new ArrayList<User>();

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
    public String registerUserForm(Model model) {
        Map<String, Object> map = model.asMap();
        if (!map.containsKey(USER_FORM)) {
            model.addAttribute(USER_FORM, new UserForm());
        }
        return "users";
    }


    //The registerUserForm method which maps the registerUserForm.ftl for POST request
    //This means when the user has submitted data in the input fields
    //We will bind each one of the user's inputs in the registerUserForm.ftl
    // to the corresponding fields of the userForm object
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String registerUserForm(@Valid @ModelAttribute(USER_FORM) UserForm userForm,
                                   BindingResult bindingResult, Model model,
                                   RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding userForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            String err = "Some fields were incorrect!";
            redirectAttributes.addFlashAttribute("errorMessage", err);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm", bindingResult);
            redirectAttributes.addFlashAttribute("userForm", userForm);
            return "redirect:/admin/users";
        }

        try {
            //Trying to build a user from our UserForm
            User user = UserConverter.buildUserObject(userForm);
            //Just printing the user in console for debugging
            System.err.println(user.toString());
            myList.add(user);
            //Adding the user to redirectAttributes so we can show its fields to the TestTestTest.ftl
            redirectAttributes.addFlashAttribute(user);
            redirectAttributes.addFlashAttribute("userList", myList);
            return "redirect:/admin/users";
            //accountService.register(user);
            //session.setAttribute("username", registrationForm.getUsername());
        } catch (Exception exception) {
            //if an error occurs show it to the user
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/users";
        }


    }


    @RequestMapping(value = "/users/delete/{afm}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable String afm,
                      RedirectAttributes redirectAttributes) {
        System.err.println(afm);
        //Delete the user
        //Simple logic just for debugging
        for (User user : myList){
            if (user.getAfm().equals(afm)){
                myList.remove(user);
            }
        }
        redirectAttributes.addFlashAttribute("userList", myList);
        return "redirect:/admin/users";
    }


}
