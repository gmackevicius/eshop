package lt.bit.eshop.form;



import lt.bit.eshop.config.OrderStatus;
import lt.bit.eshop.entity.Order;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderModel {

    private Long id;

    private String date;

    private Set<CartItemModel> items;

    private double sum;

    private OrderStatus status;



    public OrderModel(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.items = order.getItems().stream().map(CartItemModel::new).collect(Collectors.toSet());
        this.sum = order.getSum();
        this.status = order.getStatus();
    }

    public OrderModel() {
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

    public Set<CartItemModel> getItems() {
        return items;
    }

    public void setItems(Set<CartItemModel> items) {
        this.items = items;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
