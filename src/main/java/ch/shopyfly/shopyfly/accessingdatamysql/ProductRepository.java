package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
