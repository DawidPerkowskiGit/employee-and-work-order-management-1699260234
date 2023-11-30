package dpapps.controller.service.templateservice;

import dpapps.constants.MessageConstants;
import org.springframework.stereotype.Service;

@Service
public class MainViewsTemplateServiceImpl implements MainViewsTemplateService{
    @Override
    public String getHealthCheckView() {
        return MessageConstants.APP_IS_RUNNING;
    }

    @Override
    public String getHomePageView() {
        return "index";
    }

    @Override
    public String expiredUrlView() {
        return "expiredurl";
    }
}
