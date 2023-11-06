package dpapps.service;

import dpapps.constants.MessageConstants;
import org.springframework.stereotype.Component;

@Component
public class MainAppControllerServiceImpl implements MainAppControllerService{
    public String getHealthCheck() {
        return MessageConstants.APP_IS_RUNNING;
    }

    @Override
    public String getHomePage() {
        return "index";
    }
}
