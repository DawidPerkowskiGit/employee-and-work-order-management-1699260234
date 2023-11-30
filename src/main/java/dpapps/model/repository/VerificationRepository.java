package dpapps.model.repository;

import dpapps.model.User;
import dpapps.model.Verification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {

    Optional<Verification> findByCode(String code);

    Optional<Verification> findByUser(User user);

    @Transactional
    void deleteByCode(String code);

}
