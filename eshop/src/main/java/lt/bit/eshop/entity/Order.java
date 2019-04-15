//package lt.bit.eshop.entity;
//
//
//import lt.bit.eshop.form.CartItemModel;
//import lt.bit.eshop.form.CartModel;
//import sun.util.calendar.LocalGregorianCalendar;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.util.Date;
//import java.util.Set;
//
// enum Status {
//    PENDING, COMPLETED;
//}
//
//@Entity
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String date;
//
//    private Set<CartItemModel> items;
//
//    private double sum;
//
//    private Status status;
//
//    public Order(CartModel cartModel) {
//        Date date = new Date();
//        this.date = date.toString();
//        this.items = cartModel.getCartItems();
//        this.sum = cartModel.getSum();
//        this.status = Status.PENDING;
//    }
//
//    public Order() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public Set<CartItemModel> getItems() {
//        return items;
//    }
//
//    public void setItems(Set<CartItemModel> items) {
//        this.items = items;
//    }
//
//    public double getSum() {
//        return sum;
//    }
//
//    public void setSum(double sum) {
//        this.sum = sum;
//    }
//}
