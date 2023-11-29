package dpapps.model.repository;

import dpapps.model.Role;
import dpapps.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    void deleteByLogin(String login);

    Boolean existsByLogin(String login);

    User findByLogin(String login);

    User findByLoginAndEmail(String login, String email);

    List<User> findAllByRoles(Role role);
}
