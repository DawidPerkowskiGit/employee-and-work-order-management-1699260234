package dpapps.model.repository.service;

import dpapps.exception.ProjectNotFoundException;
import dpapps.model.Project;
import dpapps.model.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;


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
            //TODO logger
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
            System.out.println("Could not new Project to a database");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
