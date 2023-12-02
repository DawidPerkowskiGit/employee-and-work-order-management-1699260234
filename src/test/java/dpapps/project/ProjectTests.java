package dpapps.project;

import dpapps.model.Project;
import dpapps.model.repository.service.ProjectService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProjectTests {

    private final ProjectService service;

    @Autowired
    public ProjectTests(ProjectService service) {
        this.service = service;
    }


    private boolean shouldAddCodingLanguage(String name) {
        Project project = new Project();
        project.setName(name);

        return service.add(project);
    }

    @Disabled
    @Test
    public void shouldAddAllProjects() {
        boolean result = true;

        List<String> projectList = new ArrayList<>();

        projectList.add("E-commerce Platform");
        projectList.add("Auction Platform");
        projectList.add("Advanced Blogging Platform");
        projectList.add("TimeOff Tracker");
        projectList.add("Employee and Work Order Management");


        for (String name : projectList
        ) {
            result = result & shouldAddCodingLanguage(name);
        }

        assertTrue(result);
    }
}
