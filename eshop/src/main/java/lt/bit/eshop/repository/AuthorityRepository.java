package lt.bit.eshop.repository;

import lt.bit.eshop.entity.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    List<Authority> findByName(String name);
}
