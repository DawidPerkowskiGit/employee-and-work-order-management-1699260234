package dpapps.controller.service;

import dpapps.controller.service.templateservice.MainViewsTemplateService;
import org.springframework.stereotype.Component;

@Component
public class MainAppControllerServiceImpl implements MainAppControllerService {

    private final MainViewsTemplateService templateService;

    public MainAppControllerServiceImpl(MainViewsTemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Returns application health status http code 200 - OK
     */
    @Override
    public String getHealthCheck() {
        return templateService.getHealthCheckView();
    }

    /**
     * Returns Homepage view
     */
    @Override
    public String getHomePage() {
        return templateService.getHomePageView();
    }

    /**
     * Returns session expired endpoint view
     */
    @Override
    public String expiredUrl() {
        return templateService.expiredUrlView();
    }
}
