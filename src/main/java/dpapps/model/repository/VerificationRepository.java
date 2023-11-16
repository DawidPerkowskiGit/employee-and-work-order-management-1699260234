package dpapps.model.repository;

import dpapps.model.User;
import dpapps.model.Verification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {

    Verification findByCode(String code);

    Verification findByUser(User user);

    @Transactional
    void deleteByCode(String code);

}
