package dpapps.model.repository.service;

import dpapps.model.Project;

import java.util.List;

public interface ProjectService {

    /**
     * Returns Project based on ID value
     */
    Project findById(Long id);

    /**
     * Find if Project exists based on id
     */

    boolean existsById(Long id);

    /**
     * Returns Project based on name
     */
    Project findByName(String name);

    /**
     * Find if Project exists based on name
     */

    boolean existsByName(String name);


    /**
     * Persists a Project
     */
    boolean add(Project project);

    /**
     * Gets all Projects
     */
    List<Project> findAll();

}
