package dpapps.model.repository.service;

import dpapps.exception.ProjectNotFoundException;
import dpapps.model.Project;
import dpapps.model.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public Project findById(Long id) {
        return this.projectRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return this.projectRepository.existsById(id);
    }

    @Override
    public Project findByName(String name) {
        try {
            projectRepository.findByName(name).orElseThrow(() -> new ProjectNotFoundException());
        }
        catch (ProjectNotFoundException e) {
            logger.warn("Could not find Project with name '" + name + "' in the database.");
        }
        return new Project();
    }

    @Override
    public boolean existsByName(String name) {
        return this.projectRepository.existsByName(name);
    }

    @Override
    public boolean add(Project project) {
        try {
            this.projectRepository.save(project);
            return true;
        } catch (Exception e) {
            logger.warn("Could not save new Project in the database");
        }
        return false;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
