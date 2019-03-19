package lt.bit.eshop.repository;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByCategory(CategoryEntity entity);

//    @Query("SELECT p FROM product p WHERE p.category = ?1 ")
//    List<Product> filterProducts(CategoryEntity entity);

}
