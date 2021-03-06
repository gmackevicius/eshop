package lt.bit.eshop.controller;


import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.*;
import lt.bit.eshop.service.ShoppingCartService;
import lt.bit.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.WebSession;

@Controller
//@RequestMapping("cart")
public class CartController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private UserService userService;



    @RequestMapping("/shopping-cart")
    public String index(Model model) {

        model.addAttribute("shoppingCart", new CartModel(cartService.getUserCart()));
        model.addAttribute("cartItem", new CartItemModel());


        return "shopping-cart";
    }


    @RequestMapping(value="/category-list/buy",  method = RequestMethod.POST)
    public String addToCart(@RequestParam Long id){

        cartService.buy(id);

        return "redirect:/category-list/id-DESC";
    }

    @RequestMapping(value="/shopping-cart/remove", method = RequestMethod.POST)
    public String removeFromCart(@RequestParam Long id) {
        cartService.deleteCartItem(id);

        return "redirect:/shopping-cart";
    }

    @RequestMapping(value = "/shopping-cart/checkout", method = RequestMethod.POST)
    public String order() {

        cartService.order();

        return "redirect:/shopping-cart/order";
    }

    @RequestMapping(value = "/shopping-cart/order")
    public String orderLanding(Model model) {

        model.addAttribute("orderModel", cartService.getLatestOrder());

        return "order-landing";
    }

    @RequestMapping(value = "/shopping-cart/order", method = RequestMethod.POST)
    public String confirmPayment(@RequestParam Long id) {

        cartService.orderStatusAndQuantityChange(id);

        return "redirect:/category-list/id-DESC";
    }

}
