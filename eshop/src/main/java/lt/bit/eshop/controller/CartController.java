package lt.bit.eshop.controller;


import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.CartModel;
import lt.bit.eshop.form.UserModel;
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
    private ShoppingCartService cartService;

    @Autowired
    private UserService userService;



    @RequestMapping("/shopping-cart")
    public String index(Model model) {

        model.addAttribute("shoppingCart", cartService.getUserCart());


        return "shopping-cart";
    }


    @RequestMapping(value="/category-list/buy",  method = RequestMethod.POST)
    public String buy(@RequestParam Long id){

        cartService.buy(id);

        return "redirect:/category-list/id-DESC";
    }

}
