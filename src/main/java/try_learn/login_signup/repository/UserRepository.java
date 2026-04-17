package try_learn.login_signup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import try_learn.login_signup.model.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String userName);
}
