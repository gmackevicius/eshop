package lt.bit.eshop.form;

import lt.bit.eshop.entity.CartEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartModel {

    private Set<CartItemModel> cartItems;

    public CartModel(CartEntity cartEntity) {
        if(cartEntity.getCartItems().size() > 0) {
            this.cartItems = cartEntity.getCartItems().stream().map(CartItemModel::new).collect(Collectors.toSet());
        } else {
            this.cartItems = new HashSet<>();
        }
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for(CartItemModel c : this.cartItems){
            totalQuantity += c.getQuantity();
        }

        return totalQuantity;
    }

    public CartModel() {
    }

    public Set<CartItemModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItemModel> cartItems) {
        this.cartItems = cartItems;
    }


}
