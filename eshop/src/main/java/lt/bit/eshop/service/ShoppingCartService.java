package lt.bit.eshop.service;


import lt.bit.eshop.entity.CartEntity;
import lt.bit.eshop.entity.CartItem;
import lt.bit.eshop.entity.Order;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.CartItemModel;
import lt.bit.eshop.form.CartModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.CartItemRepository;
import lt.bit.eshop.repository.CartRepository;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    private boolean quantityUpdated;

    public void buy(Long id) {
        UserEntity user = userService.getCurrentUser();


        if (user.getCart().getCartItems().size() != 0) {
            for (CartItem c : user.getCart().getCartItems()) {
                if (c.getProduct().getId() == id) {
                    c.setQuantity(c.getQuantity() + 1);
                    cartItemRepository.save(c);
                    quantityUpdated = true;
                    break;
                } else {
                    quantityUpdated = false;
                }
            }
        }
        if(user.getCart().getCartItems().size() == 0 || quantityUpdated == false) {
            CartEntity cart = user.getCart();

            CartItem cartItem = new CartItem(productService.getById(id), 1);
//            cartItemRepository.save(cartItem);

            cart.getCartItems().add(cartItem);
//            cartRepository.save(cart);

            user.setCart(cart);

            if(user.getId() != null) {
                userService.saveUser(user);
            }
        }
    }

    public CartEntity getUserCart() {
        UserEntity user = userService.getCurrentUser();
        return user.getCart();
    }

    public void deleteCartItem(Long id) {
        UserEntity user = userService.getCurrentUser();
        Set<CartItem> tempCart = null;
        for(CartItem item : user.getCart().getCartItems()) {
            if(item.getId() == id ) {
               tempCart = user.getCart().getCartItems();
               tempCart.remove(item);
               break;
            }
        }
        user.getCart().setCartItems(tempCart);
        userService.saveUser(user);
        cartItemRepository.deleteById(id);
    }

    public void order() {
        UserEntity user = userService.getCurrentUser();
        Set<CartItem> itemsCopy = user.getCart().getCartItems().stream().collect(Collectors.toSet());
        Order order = new Order(user.getCart());
        order.setItems(itemsCopy);


        if(user.getOrders() != null) {
            List<Order> tempList = user.getOrders();
            tempList.add(order);
            user.setOrders(tempList);
        } else {
            List<Order> orders = new ArrayList<>();
            orders.add(order);
            user.setOrders(orders);
        }

        user.getCart().getCartItems().removeAll(user.getCart().getCartItems());
        userService.saveUser(user);
    }
}
