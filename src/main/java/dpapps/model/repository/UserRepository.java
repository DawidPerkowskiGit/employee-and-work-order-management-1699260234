package dpapps.model.repository;

import dpapps.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    void deleteByLogin(String login);

    Boolean existsByLogin(String login);

    User findByLogin(String login);

    User findByLoginAndEmail(String login, String email);
}
