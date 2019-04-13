package lt.bit.eshop.form;

import lt.bit.eshop.entity.CartEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartModel {

    private Set<CartItemModel> cartItems;

    public CartModel(CartEntity cartEntity) {
       this.cartItems =  cartEntity.getCartItems().stream().map(CartItemModel::new).collect(Collectors.toSet());
    }

    public CartModel() {
    }

    public Set<CartItemModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItemModel> cartItems) {
        this.cartItems = cartItems;
    }

    //    public double getSum() {
//        return sum;
//    }
//
//    public void setSum(double sum) {
//        int tempSum = 0;
//        for(ProductModel p : cart) {
//            tempSum += (p.getQuantity() * p.getPrice());
//        }
//        this.sum = tempSum;
//    }

    //    private double sum;
//
//    public CartModel(List<ProductModel> cart) {
//        this.cart = cart;
//        int tempSum = 0;
//        for(ProductModel p : cart) {
//            tempSum += (p.getQuantity() * p.getPrice());
//        }
//        this.sum = tempSum;
//    }
}
