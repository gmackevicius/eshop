package lt.bit.eshop.repository;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findBySlug(String slug);
}
