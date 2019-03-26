package lt.bit.eshop.repository;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(CategoryEntity entity, Sort sort);

    List<Product> findByCategoryAndNameContaining(CategoryEntity entity, Sort sort, String name);

    List<Product> findByNameContaining(String name);

//    @Query("SELECT p FROM product p WHERE p.category = ?1 ")
//    List<Product> filterProducts(CategoryEntity entity);

}
