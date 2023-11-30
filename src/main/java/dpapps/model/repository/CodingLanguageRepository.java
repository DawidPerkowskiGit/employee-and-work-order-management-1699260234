package dpapps.model.repository;

import dpapps.model.CodingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodingLanguageRepository extends JpaRepository<CodingLanguage, Long> {
    Optional<CodingLanguage> findByName(String name);

    boolean existsByName(String name);
}
