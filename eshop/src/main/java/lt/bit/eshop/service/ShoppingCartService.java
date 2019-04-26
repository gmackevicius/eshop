package lt.bit.eshop.service;


import lt.bit.eshop.entity.*;
import lt.bit.eshop.form.CartItemModel;
import lt.bit.eshop.form.CartModel;
import lt.bit.eshop.form.OrderModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.CartItemRepository;
import lt.bit.eshop.repository.CartRepository;
import lt.bit.eshop.repository.OrderRepository;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static lt.bit.eshop.config.OrderStatus.COMPLETED;
import static lt.bit.eshop.config.OrderStatus.DELIVERY;

@Service
public class ShoppingCartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    private boolean quantityUpdated;

    public void buy(Long id) {
        UserEntity user = userService.getCurrentUser();


        if (user.getCart().getCartItems().size() != 0) {
            for (CartItem c : user.getCart().getCartItems()) {
                if (c.getProduct().getId() == id) {
                    c.setQuantity(c.getQuantity() + 1);
                    cartItemRepository.save(c);
                    quantityUpdated = true;
                    break;
                } else {
                    quantityUpdated = false;
                }
            }
        }
        if(user.getCart().getCartItems().size() == 0 || quantityUpdated == false) {
            CartEntity cart = user.getCart();

            CartItem cartItem = new CartItem(productService.getById(id), 1);
//            cartItemRepository.save(cartItem);

            cart.getCartItems().add(cartItem);
//            cartRepository.save(cart);

            user.setCart(cart);

            if(user.getId() != null) {
                userService.saveUser(user);
            }
        }
    }

    public CartEntity getUserCart() {
        UserEntity user = userService.getCurrentUser();
        return user.getCart();
    }

    public void deleteCartItem(Long id) {
        UserEntity user = userService.getCurrentUser();
        Set<CartItem> tempCart = null;
        for(CartItem item : user.getCart().getCartItems()) {
            if(item.getId() == id ) {
               tempCart = user.getCart().getCartItems();
               tempCart.remove(item);
               break;
            }
        }
        user.getCart().setCartItems(tempCart);
        userService.saveUser(user);
        cartItemRepository.deleteById(id);
    }

    public void order() {
        UserEntity user = userService.getCurrentUser();
        Set<CartItem> itemsCopy = user.getCart().getCartItems().stream().collect(Collectors.toSet());
        Order order = new Order(user.getCart());
        order.setItems(itemsCopy);


        if(user.getOrders() != null) {
            List<Order> tempList = user.getOrders();
            tempList.add(order);
            user.setOrders(tempList);
        } else {
            List<Order> orders = new ArrayList<>();
            orders.add(order);
            user.setOrders(orders);
        }

        user.getCart().getCartItems().removeAll(user.getCart().getCartItems());
        userService.saveUser(user);

//        return new OrderModel(order);
    }

    public void orderStatusAndQuantityChange(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if(orderOptional.isPresent()) {
           Order orderEntity = orderOptional.get();
           if(orderEntity.getStatus().toString().toLowerCase().equals("pending")){
               orderEntity.setStatus(DELIVERY);

               for(CartItem c : orderEntity.getItems()) {
                   if(c.getProduct().getQuantity() > c.getQuantity()){
                       c.getProduct().setQuantity(c.getProduct().getQuantity() - c.getQuantity());
                   }
               }

               orderRepository.save(orderEntity);
           }
           else if(orderEntity.getStatus().toString().toLowerCase().equals("delivery")) {
               orderEntity.setStatus(COMPLETED);
               orderRepository.save(orderEntity);
           }
        }
    }

    public OrderModel getLatestOrder() {
        return new OrderModel(userService.getCurrentUser().getOrders().get(userService.getCurrentUser().getOrders().size() - 1));
    }
}
