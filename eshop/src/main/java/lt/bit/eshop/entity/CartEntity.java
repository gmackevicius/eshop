package lt.bit.eshop.entity;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "cart_cart_items", joinColumns = {@JoinColumn(name = "cart_id")}, inverseJoinColumns = {@JoinColumn(name = "cart_item_id")} )
    private Set<CartItem> cartItems;



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

    public double getSum() {
        double sum = 0;
        for(CartItem c : this.cartItems){
            sum += (c.getQuantity() * c.getProduct().getPrice());
        }

        return sum;
    }
}
