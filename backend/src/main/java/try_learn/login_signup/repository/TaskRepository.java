package try_learn.login_signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import try_learn.login_signup.model.Tasks;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    Optional<Tasks> findTasksByTitle(String title);
}
