package dpapps.controller.service;

import dpapps.constants.MessageConstants;

public interface MainAppControllerService {

    /**
     * Returns health status of application
     */
    String getHealthCheck();

    /**
     * Returns homepage view
     */
    String getHomePage();

    /**
     * Returns expired URL view
     */
    String expiredUrl();
}
