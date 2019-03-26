package lt.bit.eshop.controller;


import lt.bit.eshop.form.FilterModel;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class RegistrationController {

    @Autowired
    private CustomUserDetailService userService;

    @GetMapping("/register")
    public String index(Model model) {

        model.addAttribute("userModel", new UserModel());

        return "registration-form";
    }

    @RequestMapping( value = "/register", method = RequestMethod.POST)
    public String newUser(@Valid @ModelAttribute UserModel userModel, BindingResult bindingResult, Model model )  {


        try {
            userService.createNewUser(userModel);
            model.addAttribute("userModel", new UserModel());
            return "index";
        } catch(Exception e) {
            model.addAttribute("error", "Username taken!" );
//            model.addAttribute("userModel", new UserModel());
            return "registration-form";
        }




    }


}
