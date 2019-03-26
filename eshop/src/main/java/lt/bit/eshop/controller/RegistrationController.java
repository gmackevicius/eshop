package lt.bit.eshop.controller;


import lt.bit.eshop.form.FilterModel;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RegistrationController {

    @Autowired
    private CustomUserDetailService userService;

    @GetMapping("/register}")
    public String index(Model model) {

        model.addAttribute("userModel", new UserModel());

        return "registration-form";
    }
}
