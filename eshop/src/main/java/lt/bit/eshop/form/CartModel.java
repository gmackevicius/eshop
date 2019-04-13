package lt.bit.eshop.form;

import lt.bit.eshop.entity.CartEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartModel {

    private Set<CartItemModel> cartItems;

//    private int cartQuantity;

    public CartModel(CartEntity cartEntity) {
       this.cartItems =  cartEntity.getCartItems().stream().map(CartItemModel::new).collect(Collectors.toSet());
//       this.cartQuantity = cartEntity.getCartQuantity();
    }

    public CartModel() {
    }

    public Set<CartItemModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItemModel> cartItems) {
        this.cartItems = cartItems;
    }

//    public int getCartQuantity() {
//        if(cartItems != null) {
//        for(CartItemModel c : cartItems) {
//            cartQuantity += c.getQuantity();
//        }
//        } else {
//            cartQuantity = 0;
//        }
//        return cartQuantity;
//    }
//
//    public void setCartQuantity(int cartQuantity) {
//        this.cartQuantity = cartQuantity;
//    }
}
