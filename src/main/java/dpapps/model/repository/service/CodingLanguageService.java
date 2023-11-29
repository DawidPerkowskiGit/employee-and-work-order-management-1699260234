package dpapps.model.repository.service;

import dpapps.model.CodingLanguage;
import dpapps.model.Project;

import java.util.List;

public interface CodingLanguageService {
    /**
     * Returns Coding Language based on ID value
     */
    CodingLanguage findById(Long id);

    /**
     * Find if Coding Language exists based on id
     */

    boolean existsById(Long id);

    /**
     * Returns Coding Language based on name
     */
    CodingLanguage findByName(String name);

    /**
     * Find if Coding Language exists based on name
     */

    boolean existsByName(String name);

    /**
     * Persists a Coding Language
     */
    boolean add(CodingLanguage codingLanguage);

    /**
     * Returns all Coding Languages
     */

    List<CodingLanguage> findAll();
}
