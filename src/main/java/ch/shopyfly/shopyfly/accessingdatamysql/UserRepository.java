package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import ch.shopyfly.shopyfly.accessingdatamysql.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    // ...
}