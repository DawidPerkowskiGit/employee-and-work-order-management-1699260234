package dpapps.service;

import dpapps.constants.MessageConstants;

public interface MainAppControllerService {

    /**
     * Returns health status of application
     */
    public String getHealthCheck();

    /**
     * Returns homepage view
     */
    public String getHomePage();
}
