package lt.bit.eshop.service;


import lt.bit.eshop.entity.CartEntity;
import lt.bit.eshop.entity.CartItem;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.CartItemModel;
import lt.bit.eshop.form.CartModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.CartItemRepository;
import lt.bit.eshop.repository.CartRepository;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    public CartModel buy(Long id, UserEntity user) {
        CartModel currentCart = null;

        if(user != null) {
            if(user.getCart() == null) {
                CartEntity cart = new CartEntity();
                CartItem cartItem = new CartItem(productService.getById(id), 1);
                cartItemRepository.save(cartItem);
                Set<CartItem> cartItems = new HashSet<>();
                cartItems.add(cartItem);
                cart.setCartItems(cartItems);
                cartRepository.save(cart);
                user.setCart(cart);

                userRepository.save(user);
            }
            else {
                for(CartItem c : user.getCart().getCartItems()) {
                    if(c.getProduct().getId() == id) {
                        c.setQuantity(c.getQuantity() + 1);
                        cartItemRepository.save(c);
                    } else {
                        CartItem cartItem = new CartItem(productService.getById(id), 1);
                        cartItemRepository.save(cartItem);
                        CartEntity cart = user.getCart();
                        cart.getCartItems().add(cartItem);
                        cartRepository.save(cart);
                        user.setCart(cart);
                        userRepository.save(user);
                    }
                }
            }
        } else {
          currentCart = new CartModel();
          CartItemModel cartItem = new CartItemModel();
          cartItem.setProduct(new ProductModel(productService.getById(id)));
          cartItem.setQuantity(1);
          Set<CartItemModel> cart = new HashSet<>();
          cart.add(cartItem);
          currentCart.setCartItems(cart);
        }
        return currentCart;
    }
}
