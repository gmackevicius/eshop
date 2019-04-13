package lt.bit.eshop.repository;

import lt.bit.eshop.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
