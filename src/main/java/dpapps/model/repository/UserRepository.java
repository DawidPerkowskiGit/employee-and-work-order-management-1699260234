package dpapps.model.repository;

import dpapps.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    public void deleteByLogin(String login);

    public Boolean existsByLogin(String login);

    public User findByLogin(String login);
}
