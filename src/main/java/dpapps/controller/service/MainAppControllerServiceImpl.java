package dpapps.controller.service;

import dpapps.constants.MessageConstants;
import org.springframework.stereotype.Component;

@Component
public class MainAppControllerServiceImpl implements MainAppControllerService{

    /**
     * Returns application health status http code 200 - OK
     */
    public String getHealthCheck() {
        return MessageConstants.APP_IS_RUNNING;
    }

    /**
     * Returns Homepage view
     */
    @Override
    public String getHomePage() {
        return "index";
    }

    /**
     * Returns session expired endpoint view
     */
    @Override
    public String expiredUrl() { return "expiredurl";}
}
