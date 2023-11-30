package dpapps.controller.service.templateservice;

public interface MainViewsTemplateService {

    /**
     * Returns health status of application
     */
    String getHealthCheckView();

    /**
     * Returns homepage view
     */
    String getHomePageView();

    /**
     * Returns expired URL view
     */
    String expiredUrlView();
}
