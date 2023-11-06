package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByFullName(String fullName);
}