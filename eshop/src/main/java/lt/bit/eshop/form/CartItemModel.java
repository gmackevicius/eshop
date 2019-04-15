package lt.bit.eshop.form;


import lt.bit.eshop.entity.CartItem;

public class CartItemModel {

    private Long id;

    private ProductModel product;

    private int quantity;

    public CartItemModel(CartItem cartItem) {
        this.id = cartItem.getId();
        this.product = new ProductModel(cartItem.getProduct());
        this.quantity = cartItem.getQuantity();
    }

    public CartItemModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
