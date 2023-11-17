package dpapps.model.repository;

import dpapps.model.User;
import dpapps.model.UserAndRolePair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAndRolePairRepository extends JpaRepository<UserAndRolePair, Long> {

    public List<UserAndRolePair> findUserAndRolePairByUser(User user);
}
