package lt.bit.eshop.entity;


import lt.bit.eshop.config.OrderStatus;
import lt.bit.eshop.form.CartItemModel;
import lt.bit.eshop.form.CartModel;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_items", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<CartItem> items;

    private double sum;

    private OrderStatus status;

    public Order(CartEntity cart) {
        Date date = new Date();
        String dateToSet = date.toString();
        this.date = dateToSet;
        this.sum = cart.getSum();
        this.status = OrderStatus.PENDING;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
