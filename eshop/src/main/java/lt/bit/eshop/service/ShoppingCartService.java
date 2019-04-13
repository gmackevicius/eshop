package lt.bit.eshop.service;


import lt.bit.eshop.entity.CartItem;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.CartItemModel;
import lt.bit.eshop.form.CartModel;
import lt.bit.eshop.form.ProductModel;
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
    ProductService productService;
    @Autowired
    UserService userService;

    public CartModel buy(Long id, UserEntity user) {
        CartModel currentCart = null;

        if(user != null) {

        } else {
          currentCart = new CartModel();
          CartItemModel cartItem = new CartItemModel();
          cartItem.setProduct(productService.getById(id));
          cartItem.setQuantity(1);
          Set<CartItemModel> cart = new HashSet<>();
          cart.add(cartItem);
          currentCart.setCartItems(cart);
        }
        return currentCart;
    }
}
