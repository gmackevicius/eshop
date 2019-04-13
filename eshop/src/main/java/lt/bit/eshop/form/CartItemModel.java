package lt.bit.eshop.form;


import lt.bit.eshop.entity.CartItem;

public class CartItemModel {

    private ProductModel product;

    private int quantity;

    public CartItemModel(CartItem cartItem) {
        this.product = new ProductModel(cartItem.getProduct());
        this.quantity = cartItem.getQuantity();
    }

    public CartItemModel() {
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
