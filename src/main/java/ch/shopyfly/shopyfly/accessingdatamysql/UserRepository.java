package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import ch.shopyfly.shopyfly.accessingdatamysql.User;
import java.util.Optional;





public interface UserRepository extends CrudRepository<User, Integer> {


    // ..
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

    Optional<User> findByFullname(String fullname);
}