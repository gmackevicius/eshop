package lt.bit.eshop.controller;


import lt.bit.eshop.form.CartModel;
import lt.bit.eshop.service.ShoppingCartService;
import lt.bit.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("cart")
public class CartController {

    @Autowired
    ShoppingCartService cartService;

    @Autowired
    UserService userService;



    @RequestMapping(value="/category-list/buy",  method = RequestMethod.POST)
    public String buy(@RequestParam Long id, Model model){

        if(userService.getCurrentUser() != "anonymousUser") {
            model.addAttribute("shoppingCart", cartService.buy(id, userService.getUserByUsername(userService.getCurrentUser())));
        } else {
            model.addAttribute("shoppingCart", cartService.buy(id, null));
        }



        return "redirect:/category-list/id-DESC";
    }
}
