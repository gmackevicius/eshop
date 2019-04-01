package lt.bit.eshop.controller;


import lt.bit.eshop.form.FilterModel;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.service.CustomUserDetailService;
import lt.bit.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String index(Model model) {

        model.addAttribute("userModel", new UserModel());

        return "registration-form";
    }

    @RequestMapping( value = "/register", method = RequestMethod.POST)
    public String newUser(@Valid @ModelAttribute UserModel userModel, BindingResult bindingResult, Model model )  {

        if(!bindingResult.hasErrors()) {
            try {
                userService.createNewUser(userModel);
                model.addAttribute("userModel", new UserModel());
                return "index";
            } catch (Exception e) {
                model.addAttribute("error", "Username taken!");
            }
        }

        String matchError = bindingResult.getAllErrors().stream()
                .filter(e -> e.getObjectName().equals("userModel"))
                .map(e -> e.getDefaultMessage())
                .findFirst()
                .orElse("");

        model.addAttribute("error", matchError);

        return "registration-form";
    }


}
