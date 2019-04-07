package lt.bit.eshop.form;

import java.util.List;

public class CartModel {

    private List<ProductModel> cart;

    private double sum;

    public CartModel(List<ProductModel> cart) {
        this.cart = cart;
        int tempSum = 0;
        for(ProductModel p : cart) {
            tempSum += (p.getQuantity() * p.getPrice());
        }
        this.sum = tempSum;
    }

    public CartModel() {
    }

    public List<ProductModel> getCart() {
        return cart;
    }

    public void setCart(List<ProductModel> cart) {
        this.cart = cart;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        int tempSum = 0;
        for(ProductModel p : cart) {
            tempSum += (p.getQuantity() * p.getPrice());
        }
        this.sum = tempSum;
    }
}
