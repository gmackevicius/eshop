package lt.bit.eshop.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinTable(name = "cart_cart_items", joinColumns = {@JoinColumn(name = "cart_id")}, inverseJoinColumns = {@JoinColumn(name = "cart_item_id")} )
    private Set<CartItem> cartItems;

//    private int cartQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

//    public int getCartQuantity() {
//        for(CartItem c : cartItems) {
//            cartQuantity += c.getQuantity();
//        }
//        return cartQuantity;
//    }
//
//    public void setCartQuantity(int cartQuantity) {
//        this.cartQuantity = cartQuantity;
//    }
}
